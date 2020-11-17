package ru.f0x.food.services.users

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.f0x.food.models.dto.CustomUserDetails
import ru.f0x.food.repository.UsersRepository


@Service
class CustomUserDetailsService(private val usersRepository: UsersRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = usersRepository.findByLogin(username) ?: throw UsernameNotFoundException("Username not found!")
        return CustomUserDetails(user)
    }
}