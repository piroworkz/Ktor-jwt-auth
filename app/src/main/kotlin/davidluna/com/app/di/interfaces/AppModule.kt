package davidluna.com.app.di.interfaces

import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import davidluna.com.domain.JwtConfiguration
import io.ktor.server.application.*

interface AppModule {
    val context: Application
    val logger: org.slf4j.Logger
    val jwtConfiguration: JwtConfiguration
    val mongoDb: String
    val mongoDbServerApi: ServerApi
    val mongoClientSettings: MongoClientSettings
    val database: MongoDatabase
}

