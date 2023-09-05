package davidluna.com.usecases

import arrow.core.Either
import davidluna.com.data.UserRepository
import davidluna.com.domain.AppError
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.Response
import davidluna.com.domain.User

class LoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(request: AuthRequest): Either<AppError, Response<User>> =
        repository.login(request)
}