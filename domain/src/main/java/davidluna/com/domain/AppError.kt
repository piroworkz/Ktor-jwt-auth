package davidluna.com.domain

import io.ktor.http.*

sealed class AppError(
    val code: HttpStatusCode,
    val description: String
) : Throwable() {

    data class UnknownError(
        val statusCode: HttpStatusCode = HttpStatusCode.InternalServerError,
        val errorMessage: String = "Unknown Error"
    ) : AppError(statusCode, errorMessage)

    data class IOError(
        val statusCode: HttpStatusCode = HttpStatusCode.InternalServerError,
        val errorMessage: String = "Could not write to database"
    ) : AppError(statusCode, errorMessage)

    data class UserNotFound(
        val statusCode: HttpStatusCode = HttpStatusCode.NotFound,
        val errorMessage: String = "Account does not exist, please register"
    ) : AppError(statusCode, errorMessage)

    data class AccountExists(
        val statusCode: HttpStatusCode = HttpStatusCode.Conflict,
        val errorMessage: String = "Account already exists, please Login."
    ) : AppError(statusCode, errorMessage)

    data class UnAuthorized(
        val statusCode: HttpStatusCode = HttpStatusCode.Unauthorized,
        val errorMessage: String = "Please check your password and try again."
    ) : AppError(statusCode, errorMessage)
}