package com.uca.bigdreamscoders.bigcinema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.uca.bigdreamscoders.bigcinema.repositories"])
@EnableTransactionManagement
@EntityScan(basePackages = ["com.uca.bigdreamscoders.bigcinema.domain"])
class BigcinemaApplication

fun main(args: Array<String>) {
    runApplication<BigcinemaApplication>(*args)
}
