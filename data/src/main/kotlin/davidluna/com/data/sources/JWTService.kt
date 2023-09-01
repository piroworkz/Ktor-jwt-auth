package davidluna.com.data.sources

import davidluna.com.domain.JWTClaim

interface JWTService {
    fun generateToken(vararg claims: JWTClaim): String
}