package org.scriptonbasestar.app.auth.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/.well-known")
class WellKnownController {

    @GetMapping("/openid-configuration")
    fun openidConfiguration(){

    }

}
