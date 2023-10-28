package davidluna.com.app.di

import davidluna.com.app.di.interfaces.DataModule
import davidluna.com.usecases.GetUserByUsernameUseCase
import davidluna.com.usecases.LoginUseCase
import davidluna.com.usecases.SaveUserUseCase

interface UseCaseModule {
    val loginUseCase: LoginUseCase
    val getUserByUsernameUseCase: GetUserByUsernameUseCase
    val saveUserUseCase: SaveUserUseCase
}

class UseCaseModuleDI(
    dataModule: DataModule
) : UseCaseModule {

    override val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(dataModule.userRepository)
    }

    override val getUserByUsernameUseCase: GetUserByUsernameUseCase by lazy {
        GetUserByUsernameUseCase(dataModule.userRepository)
    }

    override val saveUserUseCase: SaveUserUseCase by lazy {
        SaveUserUseCase(dataModule.userRepository)
    }

    companion object {
        fun build(dataModule: DataModule) = UseCaseModuleDI(dataModule)
    }
}

