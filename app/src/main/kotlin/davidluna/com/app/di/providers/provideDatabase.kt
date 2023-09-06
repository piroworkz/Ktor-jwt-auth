package davidluna.com.app.di.providers

import com.mongodb.MongoClientSettings
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

fun provideDatabase(mongoClientSettings: MongoClientSettings): MongoDatabase =
    MongoClient.create(mongoClientSettings).getDatabase("piroworkz")