package davidluna.com.app.di

import davidluna.com.app.framework.local.sources.LocalHashDataSource
import davidluna.com.app.framework.local.sources.LocalJWTDataSource
import davidluna.com.app.framework.remote.sources.RemoteUserDataSource
import davidluna.com.data.sources.HashService
import davidluna.com.data.sources.JWTService
import davidluna.com.data.sources.UserDataSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appDataModule = module {
    singleOf(::LocalHashDataSource) { bind<HashService>() }
    singleOf(::LocalJWTDataSource) { bind<JWTService>() }
    singleOf(::RemoteUserDataSource) { bind<UserDataSource>() }
}