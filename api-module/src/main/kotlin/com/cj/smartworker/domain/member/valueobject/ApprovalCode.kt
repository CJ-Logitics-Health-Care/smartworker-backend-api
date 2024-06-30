package com.cj.smartworker.domain.member.valueobject

import com.cj.smartworker.domain.member.exception.MemberDomainException
import kotlin.random.Random

@JvmInline
value class ApprovalCode private constructor(
    val approvalCode: String,
) {
    companion object {
        fun generateCode(): ApprovalCode {
            val codeBuilder: StringBuilder = StringBuilder()
            (1..6).forEach { _ ->
                val nextInt = Random.nextInt(1, 9)
                codeBuilder.append(nextInt.toString())
            }
            return ApprovalCode(codeBuilder.toString())
        }

        operator fun invoke(value: String): ApprovalCode {
            if (value.length != 6) {
                throw MemberDomainException("승인 코드는 6글자")
            }
            return ApprovalCode(
                approvalCode = value,
            )
        }
    }
}
