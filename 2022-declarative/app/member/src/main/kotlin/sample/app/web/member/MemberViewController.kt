// package sample.app.web.member
//
// import org.springframework.security.core.context.SecurityContextHolder
// import org.springframework.stereotype.Controller
// import org.springframework.ui.Model
// import org.springframework.web.bind.annotation.PathVariable
// import org.springframework.web.bind.annotation.RequestMapping
// import org.springframework.web.bind.annotation.RequestMethod
// import sample.app.security.SimpleUser
//
// @Controller
// @RequestMapping("/member")
// class MemberViewController {
//
//    @RequestMapping(value = [""], method = [RequestMethod.GET])
//    fun list(): String = "member-list.html"
//
//    @RequestMapping(value = ["/my"], method = [RequestMethod.GET])
//    fun my(model: Model): String {
//        val simpleUser = SecurityContextHolder.getContext().authentication.principal as SimpleUser
//        model.addAttribute("username", simpleUser.username)
//        return "member-detail.html"
//    }
//
//    @RequestMapping(value = ["/{username}"], method = [RequestMethod.GET])
//    fun detail(@PathVariable username: String, model: Model): String {
//        model.addAttribute("username", username)
//        return "member-detail.html"
//    }
// }
