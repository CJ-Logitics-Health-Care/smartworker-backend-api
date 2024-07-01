package com.cj.smartworker.business.member.service

import com.cj.smartworker.business.member.dto.command.SignupCommand
import com.cj.smartworker.business.member.port.`in`.SaveMemberPort
import com.cj.smartworker.business.member.port.`in`.SignupUseCase
import com.cj.smartworker.business.member.port.out.FindMemberPort
import com.cj.smartworker.business.member.port.out.IsFirstMemberPort
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.exception.MemberDomainException
import com.cj.smartworker.domain.member.valueobject.*
import com.cj.smartworker.domain.util.toKstLocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
internal class SignupService(
    private val isFirstMemberPort: IsFirstMemberPort,
    private val findMemberPort: FindMemberPort,
    private val saveMemberPort: SaveMemberPort,
) : SignupUseCase {

    /**
     * 실제 production에선 휴대폰 인증 추가 필요
     */
    @Transactional
    override fun signup(command: SignupCommand) {
        findMemberPort.findByLoginId(LoginId(command.loginId))
            ?.let {
                throw MemberDomainException("이미 가입된 회원입니다.")
            }
        if (command.email != null) {
            findMemberPort.findByEmail(Email(command.email)) ?.let {
                    throw MemberDomainException("이미 존재하는 이메일 입니다.")
                }
        }

        val isFirst = isFirstMemberPort.isFirstMember()

        val authority = if (isFirst) Authority.ADMIN else Authority.EMPLOYEE

        Member(
            _memberId = null,
            _loginId = LoginId(command.loginId),
            _password = Password(command.password),
            _gender = command.gender,
            _createdAt = Instant.now().toKstLocalDateTime(),
            _deleted = Deleted.NOT_DELETED,
            _authorities = setOf(authority),
            _email = command.email?.let { Email(it) },
            _employeeName = EmployeeName(command.employeeName),
            _year = Year(command.year),
            _month = Month(command.month),
            _day = Day(command.day),
            _phone = Phone(command.phone),
        ).let {
            saveMemberPort.saveMember(it)
        }
    }
}
