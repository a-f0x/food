package ru.f0x.food.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("web.auth")
class AuthProperties {

    /**Время жизни аксесс токена **/
    var accessTokenValiditySeconds: Int = 15768000 // полгода

    /**Время жизни рефреш токена **/
    var refreshTokenValiditySeconds: Int = 31536000 // год

    lateinit var secret: String

    override fun toString(): String {
        return "AuthProperties(accessTokenValiditySeconds=$accessTokenValiditySeconds, refreshTokenValiditySeconds=$refreshTokenValiditySeconds, secret='$secret')"
    }

}
