package davidluna.com.data

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import davidluna.com.data.sources.HashService
import davidluna.com.data.sources.JWTService
import davidluna.com.data.sources.UserDataSource
import davidluna.com.domain.*

class UserRepository(
    private val remote: UserDataSource,
    private val hashService: HashService,
    private val tokenService: JWTService,
) {
    suspend fun getUserByUserName(userName: String): Either<AppError, User?> =
        remote.getUserByUserName(userName)


    suspend fun saveUser(request: AuthRequest): Either<AppError, Response<Boolean>> =
        remote.saveUser(request, hashService.hash(request.password))
            .map { buildSuccessResponse(it, createJWTToken(request.username)) }

    suspend fun login(request: AuthRequest): Either<AppError, Response<User>> = either {
        buildSuccessResponse(user(request), createJWTToken(request.username))
    }

    private fun createJWTToken(user: String): String =
        tokenService.generateToken(JWTClaim(User::username.name, user))

    private suspend fun Raise<AppError>.user(request: AuthRequest): User {
        val user = remote.getUserByUserName(request.username).bind() ?: raise(AppError.UserNotFound())
        val saltedHash = SaltedHash(user.salt, user.password)
        if (!hashService.check(request.password, saltedHash)) {
            raise(AppError.UnAuthorized())
        }
        return user
    }
}