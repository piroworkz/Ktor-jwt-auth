package davidluna.com.domain

data class SaltedHash(
    val salt: String,
    val hash: String
)