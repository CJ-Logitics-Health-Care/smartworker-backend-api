package com.cj.smartworker.application.heathcheck

import com.cj.smartworker.annotation.WebAdapter
import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.security.MemberContext.MEMBER
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
@RequestMapping("/api/v1/check")
internal class HealthCheckController {

    @GetMapping("/employee")
    fun checkEmployee(): Member {
        val member = MEMBER
        return member
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun checkAdmin(): Member {
        val member = MEMBER
        return member
    }
}
