package davidluna.com.app.di

import davidluna.com.data.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module { singleOf(::UserRepository) }
