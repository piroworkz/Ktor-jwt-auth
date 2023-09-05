package davidluna.com.app.routes

import davidluna.com.app.framework.local.model.SerializedAuthRequest
import davidluna.com.app.framework.utils.buildFailResponse
import davidluna.com.app.framework.utils.toDomain
import davidluna.com.app.framework.utils.toRemote
import davidluna.com.app.framework.utils.toRemoteUser
import davidluna.com.domain.AppError
import davidluna.com.domain.Response
import davidluna.com.domain.Role
import davidluna.com.domain.User
import davidluna.com.usecases.LoginUseCase
import davidluna.com.usecases.SaveUserUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRouting() {

    val loginUseCase by inject<LoginUseCase>()
    val saveUserUseCase by inject<SaveUserUseCase>()

    route("/auth") {
        post<SerializedAuthRequest>("/login") {
            loginUseCase(it.copy(role = Role.ADMIN).toDomain()).fold(
                ifLeft = { e: AppError -> call.respond(buildFailResponse(e)) },
                ifRight = { r: Response<User> -> call.respond(HttpStatusCode.OK, r.toRemoteUser()) })
        }

        post<SerializedAuthRequest>("/register") {
            saveUserUseCase(it.toDomain()).fold(
                ifLeft = { e: AppError -> call.respond(buildFailResponse(e)) },
                ifRight = { r: Response<Boolean> -> call.respond(HttpStatusCode.OK, r.toRemote()) })

        }

        authenticate("auth-jwt") {
            get("/me") {
                val (principal, user, role) = getCredentials()
                call.respondText("Welcome $user, your assigned role is: $role Token expires: ${principal.payload.expiresAt}")
            }
        }
    }
}