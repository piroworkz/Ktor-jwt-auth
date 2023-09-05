package davidluna.com.domain

data class Response<T>(
    val code: StatusCode,
    val message: String,
    val token: String,
    val body: T,
)