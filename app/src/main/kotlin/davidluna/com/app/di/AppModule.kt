package davidluna.com.app.di

import davidluna.com.app.di.providers.provideDatabase
import davidluna.com.app.di.providers.provideJwtConfiguration
import davidluna.com.app.di.providers.provideMongoClientSettings
import davidluna.com.app.di.providers.provideServerApi
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule: Module = module {
    factory { provideJwtConfiguration() }
    single(named("MONGO_DB_STRING")) { getProperty<String>("MONGODB") }
    single { provideMongoClientSettings(get(named("MONGO_DB_STRING")), get()) }
    single { provideServerApi() }
    single { provideDatabase(get()) }
}