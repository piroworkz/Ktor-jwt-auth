package davidluna.com.usecases

import davidluna.com.data.UserRepository
import davidluna.com.domain.AuthRequest

class SaveUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(request: AuthRequest) = repository.saveUser(request)
}