package davidluna.com.app.di.interfaces

import davidluna.com.data.sources.HashService
import davidluna.com.data.sources.JWTService
import davidluna.com.data.sources.UserDataSource

interface AppDataModule {
    val hashService: HashService
    val jwtService: JWTService
    val userDataSource: UserDataSource
}