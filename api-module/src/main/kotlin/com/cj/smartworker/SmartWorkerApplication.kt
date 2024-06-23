package com.cj.smartworker

import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.cj.smartworker.dataaccess"])
class SmartWorkerApplication

fun main(args: Array<String>) {
    runApplication<SmartWorkerApplication>(*args)
}
