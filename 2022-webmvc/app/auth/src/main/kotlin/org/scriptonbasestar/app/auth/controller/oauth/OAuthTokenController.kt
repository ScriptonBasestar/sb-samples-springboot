package org.scriptonbasestar.app.auth.controller.oauth

import org.scriptonbasestar.domain.auth.type.AuthorizedGrantType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/oauth/token")
class OAuthTokenController @Autowired constructor(
    private val allowGrantTypes: List<AuthorizedGrantType>,
){

    @GetMapping
    fun tokenGet(){
        // no
    }

    @PostMapping
    fun tokenPost(@RequestParam("grant_type") grantType: AuthorizedGrantType): ResponseEntity<String> {
        return ResponseEntity.ok("")
    }

}
