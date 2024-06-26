package com.cj.smartworker.dataaccess.member.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.member.port.`in`.SaveMemberPort
import com.cj.smartworker.business.member.port.out.FindMemberPort
import com.cj.smartworker.business.member.port.out.IsFirstMemberPort
import com.cj.smartworker.business.member.port.out.SearchMemberPort
import com.cj.smartworker.dataaccess.member.entity.AuthorityJpaEntity
import com.cj.smartworker.dataaccess.member.entity.QMemberJpaEntity.memberJpaEntity
import com.cj.smartworker.dataaccess.member.mapper.toDomainEntity
import com.cj.smartworker.dataaccess.member.mapper.toJpaEntity
import com.cj.smartworker.dataaccess.member.repository.AuthorityJpaRepository
import com.cj.smartworker.dataaccess.member.repository.MemberJpaRepository
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.Deleted
import com.cj.smartworker.domain.member.valueobject.Email
import com.cj.smartworker.domain.member.valueobject.LoginId
import com.cj.smartworker.domain.member.valueobject.MemberId
import com.querydsl.jpa.impl.JPAQueryFactory

@PersistenceAdapter
internal class MemberPersistenceAdapter(
    private val queryFactory: JPAQueryFactory,
    private val memberJpaRepository: MemberJpaRepository,
    private val authorityJpaRepository: AuthorityJpaRepository,
) : SaveMemberPort,
    FindMemberPort,
    IsFirstMemberPort, SearchMemberPort {
    override fun saveMember(member: Member): Member {

        val authorityJpaEntities = member.authorities.map { authority ->
            authorityJpaRepository.findByAuthority(authority)
                ?: let { authorityJpaRepository.save(AuthorityJpaEntity(id = null, authority = authority)) }
        }.toSet()

        return memberJpaRepository.save(member.toJpaEntity(authorityJpaEntities)).toDomainEntity()
    }

    override fun findById(id: MemberId): Member? {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(
                memberJpaEntity.id.eq(id.id)
                    .and(memberJpaEntity.deleted.eq(Deleted.NOT_DELETED))
            )
            .fetchOne()
            ?.let { return it.toDomainEntity() }
    }

    override fun findByLoginId(loginId: LoginId): Member? {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(
                memberJpaEntity.loginId.eq(loginId.loginId)
                    .and(memberJpaEntity.deleted.eq(Deleted.NOT_DELETED))
            )
            .fetchOne()
            ?.let { return it.toDomainEntity() }
    }

    override fun findByEmail(email: Email): Member? {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(
                memberJpaEntity.email.eq(email.email)
                    .and(memberJpaEntity.deleted.eq(Deleted.NOT_DELETED))
            )
            .fetchOne()?.toDomainEntity()
    }

    override fun isFirstMember(): Boolean {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(memberJpaEntity.deleted.eq(Deleted.NOT_DELETED))
            .fetchFirst()
            ?.let { return false }
            ?: true
    }

    override fun searchByLoginId(loginId: LoginId): Member? {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(
                memberJpaEntity.loginId.eq(loginId.loginId),
                memberJpaEntity.deleted.eq(Deleted.NOT_DELETED),
            )
            .fetchOne()
            ?.let { return it.toDomainEntity() }
    }
}
