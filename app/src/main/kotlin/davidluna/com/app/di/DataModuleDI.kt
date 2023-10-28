package davidluna.com.app.di

import davidluna.com.app.di.interfaces.AppDataModule
import davidluna.com.app.di.interfaces.DataModule
import davidluna.com.data.UserRepository

class DataModuleDI(
    appDataModule: AppDataModule
) : DataModule {

    override val userRepository: UserRepository by lazy {
        UserRepository(
            appDataModule.userDataSource,
            appDataModule.hashService,
            appDataModule.jwtService
        )
    }

    companion object {
        fun build(appDataModule: AppDataModule) = DataModuleDI(appDataModule)
    }
}