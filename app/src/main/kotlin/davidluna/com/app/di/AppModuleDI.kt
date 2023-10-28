package davidluna.com.app.di

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import davidluna.com.app.di.interfaces.AppModule
import davidluna.com.domain.JwtConfiguration
import io.ktor.server.application.*
import org.slf4j.Logger

class AppModuleDI(application: Application) : AppModule {

    override val context: Application = application

    override val logger: Logger by lazy {
        application.log
    }

    override val jwtConfiguration: JwtConfiguration by lazy {
        JwtConfiguration(
            secret = getProperty("SECRET"),
            issuer = getProperty("issuer"),
            audience = getProperty("audience"),
            realm = getProperty("realm"),
            domain = getProperty("domain"),
            expiration = 600L * 1000L
        )
    }

    override val mongoDb: String by lazy { getProperty("MONGODB") }

    override val mongoDbServerApi: ServerApi by lazy {
        ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()
    }

    override val mongoClientSettings: MongoClientSettings by lazy {
        MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(mongoDb))
            .serverApi(mongoDbServerApi)
            .build()
    }

    override val database: MongoDatabase by lazy {
        MongoClient.create(mongoClientSettings).getDatabase("piroworkz")
    }

    private fun getProperty(name: String): String {
        return try {
            javaClass.getResourceAsStream("/koin.properties").use { stream ->
                java.util.Properties().apply { load(stream) }.getProperty(name)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    companion object {
        fun build(application: Application): AppModuleDI = AppModuleDI(application)
    }
}