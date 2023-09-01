package davidluna.com.domain

import io.ktor.http.*

data class Response<T>(
    val code: HttpStatusCode,
    val message: String,
    val token: String,
    val body: T,
)
