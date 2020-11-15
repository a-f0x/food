package ru.f0x.food.oauth

import org.springframework.stereotype.Service
import ru.f0x.food.oauth.configurations.AuthProperties


@Service
class WebApplicationPropertiesProvider(authProperties: AuthProperties) : IWebApplicationPropertiesProvider {

    override val accessTokenValiditySeconds: Int = authProperties.accessTokenValiditySeconds

    override val refreshTokenValiditySeconds: Int = authProperties.refreshTokenValiditySeconds

    override val secretAuthKey: String = authProperties.secret
}