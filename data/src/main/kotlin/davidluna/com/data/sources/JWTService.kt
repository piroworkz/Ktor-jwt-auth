package davidluna.com.data.sources

import arrow.core.Either
import davidluna.com.domain.AppError
import davidluna.com.domain.JWTClaim

interface JWTService {
    suspend fun generateToken(vararg claims: JWTClaim): Either<AppError, String>
}