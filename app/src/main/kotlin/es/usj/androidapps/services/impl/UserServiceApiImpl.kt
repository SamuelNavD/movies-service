package es.usj.androidapps.services.impl

import es.usj.androidapps.model.dto.LoginRequestDTO
import es.usj.androidapps.model.dto.LoginResponseDTO
import es.usj.androidapps.repositories.UserRepository
import es.usj.androidapps.services.UserServiceApi
import es.usj.androidapps.utils.DataConverter
import es.usj.androidapps.utils.OffsetBasedPageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

class UserServiceApiImpl : UserServiceApi {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun login(request: LoginRequestDTO): LoginResponseDTO {
        TODO("Not yet implemented")
    }


}