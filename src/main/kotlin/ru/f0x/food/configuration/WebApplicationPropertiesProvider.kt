package ru.f0x.food.configuration

import org.springframework.stereotype.Service


@Service
class WebApplicationPropertiesProvider(authProperties: AuthProperties) : IWebApplicationPropertiesProvider {

    override val accessTokenValiditySeconds: Int = authProperties.accessTokenValiditySeconds

    override val refreshTokenValiditySeconds: Int = authProperties.refreshTokenValiditySeconds

    override val secretAuthKey: String = authProperties.secret
}