package com.cj.smartworker.dataaccess.day_report.adapter

import com.cj.smartworker.annotation.PersistenceAdapter
import com.cj.smartworker.business.day_report.dto.response.DayReportResponse
import com.cj.smartworker.business.day_report.port.out.PagingDayReportPort
import com.cj.smartworker.business.day_report.port.out.SearchByNamePort
import com.cj.smartworker.dataaccess.day_report.entity.QDayReportJpaEntity.dayReportJpaEntity
import com.cj.smartworker.domain.day_report.entity.ReportFilter
import com.cj.smartworker.domain.day_report.entity.ReportSorting
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory

@PersistenceAdapter
internal class DayReportAdapter(
    private val queryFactory: JPAQueryFactory,
) : PagingDayReportPort,
    SearchByNamePort {

    private fun reportingSorting(
        reportSorting: Set<ReportSorting>,
        list: MutableList<OrderSpecifier<*>>,
    ): List<OrderSpecifier<*>> {
        reportSorting.forEach { sorting ->
            val orderSpecifier = when (sorting) {
                ReportSorting.KM_ASC -> OrderSpecifier(Order.ASC, dayReportJpaEntity.km)
                ReportSorting.KM_DESC -> OrderSpecifier(Order.DESC, dayReportJpaEntity.km)
                ReportSorting.MOVE_ASC -> OrderSpecifier(Order.ASC, dayReportJpaEntity.moveWork)
                ReportSorting.MOVE_DESC -> OrderSpecifier(Order.DESC, dayReportJpaEntity.moveWork)
                ReportSorting.HEART_RATE_ASC -> OrderSpecifier(Order.ASC, dayReportJpaEntity.heartRate)
                ReportSorting.HEART_RATE_DESC -> OrderSpecifier(Order.DESC, dayReportJpaEntity.heartRate)
                ReportSorting.NONE -> OrderSpecifier(Order.ASC, dayReportJpaEntity.createdAt)
            }
            list.add(orderSpecifier)
        }
        return list
    }

    override fun paging(
        page: Long,
        offset: Long,
        reportSorting: Set<ReportSorting>,
        reportFilter: Set<ReportFilter>,
    ): List<DayReportResponse> {
        val orderSpecifiers = mutableListOf<OrderSpecifier<*>>()
        if (reportSorting.isEmpty() || !reportSorting.contains(ReportSorting.NONE)) {
            orderSpecifiers.add(OrderSpecifier(Order.DESC, dayReportJpaEntity.createdAt))
        }
        reportingSorting(reportSorting, orderSpecifiers)

        val whereList = mutableListOf<BooleanExpression?>() // where ReportFilter 조건 리스트
        reportCondition(reportFilter, whereList)


        val data: MutableList<DayReportResponse> = queryFactory.select(
            Projections.constructor(
                DayReportResponse::class.java,
                dayReportJpaEntity.id,
                dayReportJpaEntity.memberName,
                dayReportJpaEntity.moveWork,
                dayReportJpaEntity.heartRate,
                dayReportJpaEntity.km,
                dayReportJpaEntity.createdAt,
                dayReportJpaEntity.heartRate.gt(dayReportJpaEntity.memberJpaEntity.heartRateThreshold)
            )
        )
            .from(dayReportJpaEntity)
            .where(*whereList.toTypedArray())
            .orderBy(*orderSpecifiers.toTypedArray())
            .offset((page - 1) * offset)
            .limit(offset)
            .fetch()

        return data
    }

    private fun reportCondition(reportFilters: Set<ReportFilter>, list: MutableList<BooleanExpression?>) {
        reportFilters.forEach { filter ->
            val booleanExpression = when (filter) {
                ReportFilter.KM_FILTER -> dayReportJpaEntity.km.gt(1.2)
                ReportFilter.MOVE_FILTER -> dayReportJpaEntity.moveWork.gt(7000)
                ReportFilter.HEART_RATE_FILTER -> dayReportJpaEntity.heartRate.gt(dayReportJpaEntity.memberJpaEntity.heartRateThreshold)
                ReportFilter.NONE -> null
            }
            list.add(booleanExpression)
        }
    }

    override fun countPage(reportFilter: Set<ReportFilter>): Long {
        val whereList = mutableListOf<BooleanExpression?>() // where ReportFilter 조건 리스트
        reportCondition(reportFilter, whereList)
        return queryFactory.select(dayReportJpaEntity.count())
            .from(dayReportJpaEntity)
            .where(*whereList.toTypedArray())
            .fetchOne() ?: 0
    }

    override fun searchByName(name: String): DayReportResponse? {
        val fetchOne = queryFactory.select(
            Projections.constructor(
                DayReportResponse::class.java,
                dayReportJpaEntity.id,
                dayReportJpaEntity.memberName,
                dayReportJpaEntity.moveWork,
                dayReportJpaEntity.heartRate,
                dayReportJpaEntity.km,
                dayReportJpaEntity.createdAt,
                dayReportJpaEntity.memberJpaEntity.heartRateThreshold.gt(dayReportJpaEntity.heartRate)
            )
        )
            .from(dayReportJpaEntity)
            .where(dayReportJpaEntity.memberName.like("$name%"))
            .orderBy(dayReportJpaEntity.createdAt.desc())
            .limit(1)
            .fetchOne()
        return fetchOne
    }
}
