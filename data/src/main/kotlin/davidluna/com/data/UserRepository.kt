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


    suspend fun saveUser(request: AuthRequest): Either<AppError, Response<Boolean>> = either {
        buildSuccessResponse(isUserSaved(request), createJWTToken(request))
    }

    suspend fun login(request: AuthRequest): Either<AppError, Response<User>> = either {
        buildSuccessResponse(user(request), createJWTToken(request))
    }


    private suspend fun Raise<AppError>.isUserSaved(request: AuthRequest): Boolean {
        val user: User? = remote.getUserByUserName(request.username).bind()
        if (user != null) raise(AppError.AccountExists())
        val saltedHash = hashService.hash(request.password)
        val isSaved = remote.saveUser(request, saltedHash).bind()
        if (!isSaved) raise(AppError.IOError())
        return isSaved
    }

    private suspend fun Raise<AppError>.user(request: AuthRequest): User {
        val user = remote.getUserByUserName(request.username).bind() ?: raise(AppError.UserNotFound())
        val saltedHash = SaltedHash(user.salt, user.password)
        if (!hashService.check(request.password, saltedHash)) {
            raise(AppError.UnAuthorized())
        }
        return user
    }

    private fun createJWTToken(user: AuthRequest): String =
        tokenService.generateToken(
            JWTClaim(User::username.name, user.username),
            JWTClaim(User::role.name, user.role.name)
        )
}