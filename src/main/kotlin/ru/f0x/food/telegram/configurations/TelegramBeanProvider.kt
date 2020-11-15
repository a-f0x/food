package ru.f0x.food.telegram.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.f0x.food.telegram.cases.CaseType
import ru.f0x.food.telegram.cases.ITelegramCase

@Configuration
open class TelegramBeanProvider {

    @Bean
    fun provideTelegramCases(@Autowired list: List<ITelegramCase>): Map<CaseType, ITelegramCase> {
        return list.map {
            it.type to it
        }.toMap()
    }

}