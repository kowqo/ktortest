package com.genshin.features.registration

import InMemoryCache
import TokenCache
import com.genshin.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val receive = call.receive<RegistrationReceiveRemote>()
            if (!receive.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            }
            val token = UUID.randomUUID().toString()

            if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }

            InMemoryCache.userList.add(receive)
            InMemoryCache.token.add(TokenCache(login = receive.login, token = token))

            call.respond(RegistrationResponseRemote(token = token))
        }
    }
}
