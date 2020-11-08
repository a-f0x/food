package ru.f0x.food.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
open class CommonBeanProvider {

    @Bean
    open fun clock(): Clock {
        return Clock.systemUTC()
    }

}