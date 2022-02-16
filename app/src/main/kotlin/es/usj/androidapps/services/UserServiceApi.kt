package es.usj.androidapps.services

import es.usj.androidapps.model.User
import es.usj.androidapps.model.dto.LoginRequestDTO
import es.usj.androidapps.model.dto.LoginResponseDTO

interface UserServiceApi {
    fun login(request: LoginRequestDTO) : LoginResponseDTO
}