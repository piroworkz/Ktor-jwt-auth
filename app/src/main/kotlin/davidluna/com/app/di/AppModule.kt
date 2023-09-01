package davidluna.com.app.di

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import davidluna.com.app.framework.local.sources.LocalHashDataSource
import davidluna.com.app.framework.local.sources.LocalJWTDataSource
import davidluna.com.app.framework.remote.sources.RemoteUserDataSource
import davidluna.com.data.UserRepository
import davidluna.com.data.sources.HashService
import davidluna.com.data.sources.JWTService
import davidluna.com.data.sources.UserDataSource
import davidluna.com.domain.JwtConfiguration
import davidluna.com.usecases.GetUserByUsernameUseCase
import davidluna.com.usecases.LoginUseCase
import davidluna.com.usecases.SaveUserUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory {
        provideJWTProperties(
            getProperty("issuer"),
            getProperty("audience"),
            getProperty("realm"),
            getProperty("domain"),
        )
    }
    single(named("MONGO_DB_STRING")) { provideConnectionString() }
    single { provideServerApi() }
    single { provideMongoClientSettings(get(named("MONGO_DB_STRING")), get()) }
    single { provideDatabase(get()) }
    singleOf(::LocalHashDataSource) { bind<HashService>() }
    singleOf(::LocalJWTDataSource) { bind<JWTService>() }
    singleOf(::RemoteUserDataSource) { bind<UserDataSource>() }
    factoryOf(::GetUserByUsernameUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::SaveUserUseCase)
    singleOf(::UserRepository)
}

private fun provideConnectionString(): String =
    System.getenv("MONGODB") ?: ""


private fun provideServerApi(): ServerApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()

private fun provideMongoClientSettings(
    connectionString: String,
    serverApi: ServerApi,
): MongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()


private fun provideDatabase(mongoClientSettings: MongoClientSettings): MongoDatabase =
    MongoClient.create(mongoClientSettings).getDatabase("piroworkz")

private fun provideJWTProperties(
    issuer: String,
    audience: String,
    realm: String,
    domain: String,
): JwtConfiguration {
    return JwtConfiguration(
        secret = System.getenv("SECRET") ?: "",
        issuer = issuer,
        audience = audience,
        realm = realm,
        domain = domain,
        expiration = 600L * 1000L
    )
}
