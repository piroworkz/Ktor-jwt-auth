package davidluna.com.data

import davidluna.com.domain.Response
import io.ktor.http.*

fun <T> buildSuccessResponse(response: T, token: String = ""): Response<T> = Response(
    code = HttpStatusCode.OK,
    message = "Success",
    token = token,
    response = response
)