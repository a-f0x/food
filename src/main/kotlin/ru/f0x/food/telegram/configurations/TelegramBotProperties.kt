package ru.f0x.food.telegram.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties("bot")
class TelegramBotProperties {

    lateinit var token: String

    lateinit var botName: String

    override fun toString(): String {
        return "TelegramBotProperties(token='$token', botName='$botName')"
    }

}
