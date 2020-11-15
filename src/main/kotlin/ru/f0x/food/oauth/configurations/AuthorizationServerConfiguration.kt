package ru.f0x.food.oauth.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.token.TokenStore
import ru.f0x.food.oauth.IWebApplicationPropertiesProvider

@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var webApplicationPropertiesProvider: IWebApplicationPropertiesProvider

    @Autowired
    lateinit var tokenStore: TokenStore

    @Autowired
    lateinit var userApprovalHandler: UserApprovalHandler

    @Autowired
    @Qualifier("authenticationManagerBean")
    lateinit var authenticationManager: AuthenticationManager

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("trusted-client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .secret(webApplicationPropertiesProvider.secretAuthKey)
                .accessTokenValiditySeconds(webApplicationPropertiesProvider.accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(webApplicationPropertiesProvider.refreshTokenValiditySeconds)
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
    }

    @Throws(Exception::class)
    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
        oauthServer.allowFormAuthenticationForClients()
    }

}