package ru.f0x.nutrients.oauth

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler

@Configuration
@EnableResourceServer
open class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests()
                .antMatchers("/api/v1/target**").access("hasRole('USER')")
                .antMatchers("/api/v1/user/**").access("hasRole('USER')")
                .antMatchers(HttpMethod.POST, "/api/v1/register").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/nutrients/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/nutrients").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers(HttpMethod.PUT, "/api/v1/nutrients").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers(HttpMethod.DELETE, "/api/v1/nutrients").access("hasRole('ADMIN')")
                .antMatchers("/**").access("hasRole" + "('ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }
}