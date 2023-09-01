package davidluna.com.app.framework.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import davidluna.com.domain.AppError
import io.ktor.network.sockets.*
import io.ktor.utils.io.errors.*
import java.util.logging.Logger

suspend fun <T> tryCatchSuspended(action: suspend () -> T): Either<AppError, T> = try {
    action().right()
} catch (e: Exception) {
    e.toAppError().left()
}

fun Throwable.toAppError(): AppError {
    val error = when (this) {
        is AppError -> this
        is SocketTimeoutException -> AppError.IOError(errorMessage = "Socket Timeout")
        is IOException -> AppError.IOError(errorMessage = "IO Error")
        is Exception -> AppError.UnknownError(errorMessage = this.message ?: "Unknown Error")
        else -> AppError.UnknownError()
    }

    Logger.getLogger("AppError").warning("<--" + error.description)
    return error
}
