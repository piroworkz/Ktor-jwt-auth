package davidluna.com.app.routes


import davidluna.com.app.framework.local.model.SerializedResponse
import davidluna.com.app.framework.local.model.SerializedUser
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.After
import org.koin.core.context.GlobalContext.stopKoin
import kotlin.test.Test

class AuthRoutesTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testSuccessLoginRoute() = testApplication {
        val httpClient = initClient()
        val expected = "Success"
        val response = httpClient.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }
        val actual = response.body<SerializedResponse<SerializedUser>>()

        assert(response.status == OK)
        assert(actual.message == expected)
    }

    @Test
    fun testFailedLoginRoute() = testApplication {
        val httpClient = initClient()
        val expected = "Please check your password and try again."
        val response = httpClient.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest.copy(password = "wrong password"))
        }
        val actual = response.body<SerializedResponse<String>>()

        assert(actual.code.value == Unauthorized.value)
        assert(actual.message == expected)
    }


    private fun ApplicationTestBuilder.initClient(): HttpClient {
        return createClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}
