package davidluna.com.app.di

import davidluna.com.usecases.GetUserByUsernameUseCase
import davidluna.com.usecases.LoginUseCase
import davidluna.com.usecases.SaveUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCasesModule = module {
    factoryOf(::GetUserByUsernameUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::SaveUserUseCase)
}