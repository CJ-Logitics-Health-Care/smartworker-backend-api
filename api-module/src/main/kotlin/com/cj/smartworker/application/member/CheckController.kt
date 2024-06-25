package com.cj.smartworker.application.member

import com.cj.smartworker.domain.member.entity.Member
import com.cj.smartworker.security.MemberContext
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckController {

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/check/employee")
    fun check(): Member {
        val member = MemberContext.MEMBER
        return member
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/check/admin")
    fun checkAdmin(): Member {
        val member = MemberContext.MEMBER
        return member
    }

}
