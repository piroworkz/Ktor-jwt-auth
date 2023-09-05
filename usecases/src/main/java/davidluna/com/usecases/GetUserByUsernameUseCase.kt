package davidluna.com.usecases

import arrow.core.Either
import davidluna.com.data.UserRepository
import davidluna.com.domain.AppError
import davidluna.com.domain.User

class GetUserByUsernameUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userName: String): Either<AppError, User?> =
        repository.getUserByUserName(userName)
}