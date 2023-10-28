package davidluna.com.app.di

import davidluna.com.app.di.interfaces.AppDataModule
import davidluna.com.app.di.interfaces.AppModule
import davidluna.com.app.framework.local.sources.LocalHashDataSource
import davidluna.com.app.framework.local.sources.LocalJWTDataSource
import davidluna.com.app.framework.remote.sources.RemoteUserDataSource
import davidluna.com.data.sources.HashService
import davidluna.com.data.sources.JWTService
import davidluna.com.data.sources.UserDataSource

class AppDataModuleDI(appModule: AppModule) : AppDataModule {

    override val hashService: HashService by lazy {
        LocalHashDataSource()
    }
    override val jwtService: JWTService by lazy {
        LocalJWTDataSource(appModule.jwtConfiguration)
    }
    override val userDataSource: UserDataSource by lazy {
        RemoteUserDataSource(appModule.database)
    }

    companion object {
        fun build(appModule: AppModule): AppDataModuleDI = AppDataModuleDI(appModule)
    }
}