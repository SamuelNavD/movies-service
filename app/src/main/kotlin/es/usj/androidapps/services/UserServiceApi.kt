package es.usj.androidapps.services

import es.usj.androidapps.model.dto.LoginRequestDTO
import es.usj.androidapps.model.dto.LoginResponseDTO
import org.springframework.stereotype.Service

@Service
interface UserServiceApi {
    fun login(request: LoginRequestDTO): LoginResponseDTO
}