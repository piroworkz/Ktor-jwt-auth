package davidluna.com.app.routes

//
//import com.google.common.truth.Truth.assertThat
//import davidluna.com.app.model.SerializedResponse
//import davidluna.com.app.model.SerializedUser
//import davidluna.com.domain.AppError
//import io.ktor.client.*
//import io.ktor.client.call.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.request.*
//import io.ktor.http.*
//import io.ktor.http.HttpStatusCode.Companion.OK
//import io.ktor.serialization.kotlinx.json.*
//import io.ktor.server.testing.*
//import kotlinx.serialization.json.Json
//import org.junit.After
//import org.koin.core.context.GlobalContext.stopKoin
//import kotlin.random.Random
//import kotlin.test.Test
//
//class AuthRoutesTest {
//
//    @After
//    fun tearDown() {
//        stopKoin()
//    }
//
//    @Test
//    fun `given a valid loginRequest when service is called should return expected Success message`() = testApplication {
//        val httpClient = initClient()
//        val expected = "Success"
//        val response = httpClient.post("/auth/login") {
//            contentType(ContentType.Application.Json)
//            setBody(loginRequest)
//        }
//        val actual = response.body<SerializedResponse<SerializedUser>>()
//        assertThat(actual.code.value).isEqualTo(OK.value)
//        assertThat(actual.message).isEqualTo(expected)
//    }
//
//    @Test
//    fun `given a non valid loginRequest when service is called should return expected Failure message`() =
//        testApplication {
//            val httpClient = initClient()
//            val expected = AppError.UnAuthorized(401)
//            val response = httpClient.post("/auth/login") {
//                contentType(ContentType.Application.Json)
//                setBody(loginRequest.copy(password = "wrong password"))
//            }
//            val actual = response.body<SerializedResponse<String>>()
//            assertThat(actual.code.value).isEqualTo(expected.code)
//            assertThat(actual.message).isEqualTo(expected.description)
//        }
//
//    @Test
//    fun `given a non existent loginRequest username when service is called should return expected Failure message`() =
//        testApplication {
//            val httpClient = initClient()
//            val expected = AppError.UserNotFound(400)
//            val response = httpClient.post("/auth/login") {
//                contentType(ContentType.Application.Json)
//                setBody(loginRequest.copy(username = "non existent user"))
//            }
//            val actual = response.body<SerializedResponse<String>>()
//            assertThat(actual.code.value).isEqualTo(expected.code)
//            assertThat(actual.message).isEqualTo(expected.description)
//        }
//
//    @Test
//    fun `given a valid registerRequest when service is called should return expected Success message`() =
//        testApplication {
//            val httpClient = initClient()
//            val expected = "Success"
//            val response = httpClient.post("/auth/register") {
//                contentType(ContentType.Application.Json)
//                setBody(loginRequest.copy(username = "voxel@gmail.com${Random.nextInt()}"))
//            }
//            val actual = response.body<SerializedResponse<Boolean>>()
//            assertThat(actual.code.value).isEqualTo(OK.value)
//            assertThat(actual.message).isEqualTo(expected)
//        }
//
//    @Test
//    fun `given an already registered username when service is called should return expected Failure message`() =
//        testApplication {
//            val httpClient = initClient()
//            val expected = AppError.AccountExists(400)
//            val response = httpClient.post("/auth/register") {
//                contentType(ContentType.Application.Json)
//                setBody(loginRequest)
//            }
//            val actual = response.body<SerializedResponse<String>>()
//            assertThat(actual.code.value).isEqualTo(expected.code)
//            assertThat(actual.message).isEqualTo(expected.description)
//        }
//
//    private fun ApplicationTestBuilder.initClient(): HttpClient {
//        return createClient {
//            install(ContentNegotiation) {
//                json(
//                    json = Json {
//                        prettyPrint = true
//                        isLenient = true
//                        ignoreUnknownKeys = true
//                    }
//                )
//            }
//        }
//    }
//}
