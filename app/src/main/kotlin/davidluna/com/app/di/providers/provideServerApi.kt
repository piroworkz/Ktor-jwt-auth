package davidluna.com.app.di.providers

import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion

fun provideServerApi(): ServerApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()