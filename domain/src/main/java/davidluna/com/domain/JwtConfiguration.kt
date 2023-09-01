package davidluna.com.domain

data class JwtConfiguration(
    val secret: String,
    val issuer: String,
    val audience: String,
    val realm: String,
    val domain: String,
    val expiration: Long
)