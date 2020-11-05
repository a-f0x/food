package ru.f0x.nutrients.oauth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

@Configuration
open class AuthBeanConfig {

    @Bean
    open fun passwordEncode(): PasswordEncoder {
//        return BCryptPasswordEncoder()
        return NoOpPasswordEncoder.getInstance()

    }

    @Bean
    @Autowired
    open fun tokenStore(dataSource: DataSource): TokenStore {
        val store = JdbcTokenStore(dataSource)
        store.setAuthenticationKeyGenerator(DefaultAuthenticationKeyGenerator())
        return store
    }
}