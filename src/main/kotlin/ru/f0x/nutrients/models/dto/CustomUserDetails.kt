package ru.f0x.nutrients.models.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.f0x.nutrients.models.entity.UserEntity
import java.util.stream.Collectors


class CustomUserDetails(user: UserEntity) : UserEntity(user), UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {

        return roles!!
                .stream()
                .map { role -> SimpleGrantedAuthority("ROLE_" + role.role!!) }
                .collect(Collectors.toList())
    }

    override fun getUsername(): String? {
        return super.email
    }

    override fun getPassword(): String? {
        return super.pwd
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}