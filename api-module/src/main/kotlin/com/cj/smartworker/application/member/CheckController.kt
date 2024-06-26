package com.cj.smartworker.application.member

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.security.MemberContext
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "권한 테스트용 API")
@WebAdapter
@RestController
class CheckController {

    @Operation(summary = "EMPLOYEE 이상의 권한을 가진 사람이 사용 가능", description = "Member 자기 자신을 반환합니다.")
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/api/check/employee")
    fun check(): Member {
        val member = MemberContext.MEMBER
        return member
    }

    @Operation(summary = "ADMIN 이상의 권한을 가진 사람이 사용 가능", description = "Member 자기 자신을 반환합니다.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/check/admin")
    fun checkAdmin(): Member {
        val member = MemberContext.MEMBER
        return member
    }

}
