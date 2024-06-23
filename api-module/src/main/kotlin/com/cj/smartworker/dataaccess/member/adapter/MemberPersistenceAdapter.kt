package com.cj.smartworker.dataaccess.member.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.member.port.`in`.SaveMemberPort
import com.cj.smartworker.business.member.port.out.FindMemberPort
import com.cj.smartworker.dataaccess.member.entity.QMemberJpaEntity.memberJpaEntity
import com.cj.smartworker.dataaccess.member.mapper.toDomainEntity
import com.cj.smartworker.dataaccess.member.mapper.toJpaEntity
import com.cj.smartworker.dataaccess.member.repository.MemberJpaRepository
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.domain.member.valueobject.Deleted
import com.cj.smartworker.domain.member.valueobject.LoginId
import com.querydsl.jpa.impl.JPAQueryFactory

@PersistenceAdapter
internal class MemberPersistenceAdapter(
    private val queryFactory: JPAQueryFactory,
    private val memberJpaRepository: MemberJpaRepository,
): SaveMemberPort,
    FindMemberPort {
    override fun saveMember(member: Member): Member {
        return memberJpaRepository.save(member.toJpaEntity()).toDomainEntity()
    }

    override fun findById(id: Long): Member? {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(
                memberJpaEntity.id.eq(id)
                .and(memberJpaEntity.deleted.eq(Deleted.NOT_DELETED)))
            .fetchOne()
            ?.let { return it.toDomainEntity() }
    }

    override fun findByLoginId(loginId: LoginId): Member? {
        return queryFactory.select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(
                memberJpaEntity.loginId.eq(loginId.loginId)
                .and(memberJpaEntity.deleted.eq(Deleted.NOT_DELETED)))
            .fetchOne()
            ?.let { return it.toDomainEntity() }
    }
}
