package davidluna.com.data.sources

import arrow.core.Either
import davidluna.com.domain.AppError
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.SaltedHash
import davidluna.com.domain.User

interface UserDataSource {
    suspend fun getUserByUserName(userName: String): Either<AppError, User?>
    suspend fun saveUser(request: AuthRequest, saltedHash: SaltedHash): Either<AppError, Boolean>
    suspend fun updateUser(request: AuthRequest, saltedHash: SaltedHash): Either<AppError, Boolean>
}