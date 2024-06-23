package com.cj.smartworker.domain.member.entity

import com.cj.smartworker.domain.common.RootAggregate
import com.cj.smartworker.domain.member.valueobject.*
import java.time.LocalDateTime


/**
 * 아이디 4~12 글자, 영어와 숫자 조합만 가능
 * 비밀빈호 8글자 이상, 영문 숫자 특수문자 모두 사용해야함
 */
class Member(
    private val _memberId: MemberId,
    private val _loginId: LoginId,
    private val _password: Password,
    private val _phone: Phone,
    private val _createdAt: LocalDateTime,
    private val _gender: Gender,
    private val _email: Email,
    private val _authorities: Set<Authority>,
    private val _deleted: Deleted,
): RootAggregate<MemberId>(_memberId) {
    val memberId: MemberId
        get() = _memberId
    val loginId: LoginId
        get() = _loginId
    val password: Password
        get() = _password
    val phone: Phone
        get() = _phone
    val createdAt: LocalDateTime
        get() = _createdAt
    val gender: Gender
        get() = _gender
    val email: Email
        get() = _email
    val authorities: Set<Authority>
        get() = _authorities
    val deleted: Deleted
        get() = _deleted
}
