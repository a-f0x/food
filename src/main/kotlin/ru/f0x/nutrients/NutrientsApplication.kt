package ru.f0x.nutrients

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NutrientsApplication

fun main(args: Array<String>) {
    runApplication<NutrientsApplication>(*args)
}
