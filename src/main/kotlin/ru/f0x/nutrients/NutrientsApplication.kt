package ru.f0x.nutrients

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["ru.f0x.nutrients"])
class NutrientsApplication

fun main(args: Array<String>) {
    runApplication<NutrientsApplication>(*args)
}
