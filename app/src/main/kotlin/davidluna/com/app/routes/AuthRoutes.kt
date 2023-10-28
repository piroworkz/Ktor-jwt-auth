package davidluna.com.app.routes

import davidluna.com.app.di.Inject
import davidluna.com.app.di.Inject.dataModule
import davidluna.com.app.di.inject
import davidluna.com.app.framework.utils.buildFailResponse
import davidluna.com.app.framework.utils.toDomain
import davidluna.com.app.framework.utils.toRemote
import davidluna.com.app.framework.utils.toRemoteUser
import davidluna.com.app.model.SerializedAuthRequest
import davidluna.com.domain.AppError
import davidluna.com.domain.Response
import davidluna.com.domain.User
import davidluna.com.usecases.LoginUseCase
import davidluna.com.usecases.SaveUserUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.Logger

fun Route.authRouting() {
    val loginUseCase: LoginUseCase = inject(dataModule::userRepository)
    val saveUserUseCase: SaveUserUseCase = inject(dataModule::userRepository)
    val log: Logger = Inject.appModule.logger

    route("/auth") {
        post<SerializedAuthRequest>("/login") {
            log.info("<----------------Received login request")
            loginUseCase(it.toDomain()).fold(
                ifLeft = { e: AppError -> call.respond(buildFailResponse(e)) },
                ifRight = { r: Response<User> -> call.respond(HttpStatusCode.OK, r.toRemoteUser()) })
        }

        post<SerializedAuthRequest>("/register") {
            log.info("<----------------Received register request")
            saveUserUseCase(it.toDomain()).fold(
                ifLeft = { e: AppError -> call.respond(buildFailResponse(e)) },
                ifRight = { r: Response<Boolean> -> call.respond(HttpStatusCode.OK, r.toRemote()) })

        }

        authenticate("auth-jwt") {
            get("/me") {
                log.info("<------------------Received me request")
                val (principal, user, role) = getCredentials()
                call.respondText("Welcome $user, your assigned role is: $role Token expires: ${principal.payload.expiresAt}")
            }
        }
    }
}