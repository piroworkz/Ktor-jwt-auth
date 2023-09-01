package davidluna.com.usecases

import davidluna.com.data.UserRepository
import davidluna.com.domain.AuthRequest

class LoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(request: AuthRequest) = repository.login(request)
}