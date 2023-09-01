package davidluna.com.domain

data class User(
    val username: String,
    val password: String,
    val salt: String,
)
