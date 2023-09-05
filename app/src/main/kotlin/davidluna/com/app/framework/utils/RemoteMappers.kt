package davidluna.com.app.framework.utils

import davidluna.com.app.framework.local.model.SerializedAuthRequest
import davidluna.com.app.framework.local.model.SerializedResponse
import davidluna.com.app.framework.local.model.SerializedStatusCode
import davidluna.com.app.framework.local.model.SerializedUser
import davidluna.com.domain.AppError
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.Response
import davidluna.com.domain.User


fun buildFailResponse(error: AppError, token: String = ""): SerializedResponse<String> = SerializedResponse(
    code = SerializedStatusCode(
        value = error.code,
        description = error.description
    ),
    message = error.description,
    token = token,
    body = ""
)

fun <T> Response<T>.toRemote(): SerializedResponse<T> = SerializedResponse(
    code = SerializedStatusCode(
        value = code.value,
        description = code.description
    ),
    message = message,
    token = token,
    body = body
)

fun Response<User>.toRemoteUser(): SerializedResponse<SerializedUser> = SerializedResponse(
    code = SerializedStatusCode(
        value = code.value,
        description = code.description
    ),
    message = message,
    token = token,
    body = body.toRemote()
)

private fun User.toRemote(): SerializedUser = SerializedUser(
    username = username,
    password = password,
    salt = salt,
    role = role.name
)

fun SerializedAuthRequest.toDomain(): AuthRequest = AuthRequest(
    username = username,
    password = password,
    role = role
)