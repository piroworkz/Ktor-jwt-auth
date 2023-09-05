package davidluna.com.app.framework.utils

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import davidluna.com.domain.AppError
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.network.sockets.*
import io.ktor.utils.io.errors.*

suspend fun <T> tryCatchSuspended(action: suspend () -> T): Either<AppError, T> = try {
    action().right()
} catch (e: Exception) {
    e.toAppError().left()
}

//suspend fun <T> tryCatch(action: suspend () -> T): Either<AppError, T> = try {
//    action().right()
//} catch (e: Exception) {
//    e.toAppError().left()
//}


fun Throwable.toAppError(): AppError = when (this) {
    is AppError -> this
    is SocketTimeoutException -> AppError.IOError(statusCode = InternalServerError.value)
    is IOException -> AppError.IOError(statusCode = InternalServerError.value)
    is Exception -> AppError.UnknownError(InternalServerError.value)
    else -> AppError.UnknownError(InternalServerError.value)
}