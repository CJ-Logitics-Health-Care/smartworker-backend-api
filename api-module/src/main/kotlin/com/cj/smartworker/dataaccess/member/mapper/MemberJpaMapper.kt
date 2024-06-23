package com.cj.smartworker.dataaccess.member.mapper

import com.cj.smartworker.dataaccess.member.entity.AuthorityJpaEntity
import com.cj.smartworker.dataaccess.member.entity.MemberJpaEntity
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.*

fun Member.toJpaEntity(): MemberJpaEntity = let {
    MemberJpaEntity(
        id = it.id.id,
        email = it.email.email,
        password = it.password.password,
        loginId = it.loginId.loginId,
        phone = it.phone.phone,
        authorities = it.authorities.map { authority ->  AuthorityJpaEntity(id = null, authority = authority) }.toSet(),
        deleted = it.deleted,
        createdAt = it.createdAt,
        gender = it.gender,
    )
}

fun MemberJpaEntity.toDomainEntity(): Member = let {
    Member(
        _memberId = MemberId(it.id!!),
        _loginId = LoginId(it.loginId),
        _password = Password(it.password),
        _email = Email(it.email),
        _phone = Phone(it.phone),
        _authorities = it.authorities.map { authority -> authority.authority }.toSet(),
        _deleted = it.deleted,
        _createdAt = it.createdAt,
        _gender = it.gender,
    )
}
