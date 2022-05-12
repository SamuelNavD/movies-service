package es.usj.androidapps.controllers.impl

import es.usj.androidapps.controllers.HomeApi
import es.usj.androidapps.model.dto.HealthResponseDTO
import es.usj.androidapps.model.dto.VersionDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class HomeApiController : HomeApi {

    override fun healthCheck(): ResponseEntity<HealthResponseDTO> {
        return ResponseEntity.ok().body(HealthResponseDTO("OK"))
    }

    override fun index(): String {
        return "redirect:swagger-ui/index.html"
    }

    override fun version(): ResponseEntity<VersionDTO> {
        return ResponseEntity.ok().body(VersionDTO("1.0.2"))
    }
}