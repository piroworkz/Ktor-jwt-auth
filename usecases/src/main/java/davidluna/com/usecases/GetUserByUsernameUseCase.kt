package davidluna.com.usecases

import davidluna.com.data.UserRepository

class GetUserByUsernameUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userName: String) = repository.getUserByUserName(userName)
}