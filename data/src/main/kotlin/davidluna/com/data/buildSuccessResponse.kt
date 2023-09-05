package davidluna.com.data

import davidluna.com.domain.Response
import davidluna.com.domain.StatusCode

fun <T> buildSuccessResponse(response: T, token: String = ""): Response<T> = Response(
    code = StatusCode(value = 200, description = "Success"),
    message = "Success",
    token = token,
    body = response
)