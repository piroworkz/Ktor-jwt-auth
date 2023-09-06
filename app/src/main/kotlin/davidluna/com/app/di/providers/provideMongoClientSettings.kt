package davidluna.com.app.di.providers

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi

fun provideMongoClientSettings(
    connectionString: String,
    serverApi: ServerApi,
): MongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()