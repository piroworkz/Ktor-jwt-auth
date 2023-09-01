package davidluna.com.app.routes

import davidluna.com.app.framework.model.*
import davidluna.com.domain.AppError
import davidluna.com.domain.Response
import davidluna.com.domain.User
import davidluna.com.usecases.LoginUseCase
import davidluna.com.usecases.SaveUserUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRouting() {

    val loginUseCase by inject<LoginUseCase>()
    val saveUserUseCase by inject<SaveUserUseCase>()

    route("/auth") {
        post<RemoteAuthRequest>("/login") {
            loginUseCase(it.toDomain()).fold(
                ifLeft = { e: AppError -> call.respond(e.code, buildFailResponse(e)) },
                ifRight = { r: Response<User> -> call.respond(HttpStatusCode.OK, r.toRemoteUser()) })
        }

        post<RemoteAuthRequest>("/register") {
            saveUserUseCase(it.toDomain()).fold(
                ifLeft = { e: AppError -> call.respond(e.code, buildFailResponse(e)) },
                ifRight = { r: Response<Boolean> -> call.respond(HttpStatusCode.OK, r.toRemote()) })

        }

        authenticate("auth-jwt") {
            get("/me") {
                val principal = call.principal<JWTPrincipal>() ?: error("No principal")
                call.respondText("Welcome ${principal.payload.claims.values} Token expires: ${principal.payload.expiresAt}")
            }
        }

    }
}
