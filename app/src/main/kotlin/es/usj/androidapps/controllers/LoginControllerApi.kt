package es.usj.androidapps.controllers

import es.usj.androidapps.model.dto.ActorDTO
import es.usj.androidapps.model.dto.LoginRequestDTO
import es.usj.androidapps.model.dto.LoginResponseDTO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Api(value = "Existing Building Book Service", description = "Existing Building Book API", basePath = "/login")
interface LoginControllerApi {

    @ApiOperation(
        value = "Login.",
        nickname = "login",
        notes = "Login.",
        response = ActorDTO::class,
        tags = ["Login"]
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK.", response = LoginResponseDTO::class),
            ApiResponse(code = 400, message = "Invalid Credentials.", response = Error::class),
            ApiResponse(code = 401, message = "Unauthorized.", response = Error::class),
            ApiResponse(code = 403, message = "Forbidden.", response = Error::class),
            ApiResponse(code = 404, message = "Not found.", response = Error::class),
            ApiResponse(code = 500, message = "Server error.", response = Error::class)
        ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@Valid @RequestBody body: LoginRequestDTO): ResponseEntity<LoginResponseDTO>

}