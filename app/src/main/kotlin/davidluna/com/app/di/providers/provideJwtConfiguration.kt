package davidluna.com.app.di.providers

import davidluna.com.domain.JwtConfiguration
import org.koin.core.scope.Scope

fun Scope.provideJwtConfiguration() = JwtConfiguration(
    secret = getProperty("SECRET"),
    issuer = getProperty("issuer"),
    audience = getProperty("audience"),
    realm = getProperty("realm"),
    domain = getProperty("domain"),
    expiration = 600L * 1000L
)