package davidluna.com.app.framework.model

import davidluna.com.domain.AppError
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.Response
import davidluna.com.domain.User


fun buildFailResponse(error: AppError, token: String = ""): RemoteResponse<String> = RemoteResponse(
    code = RemoteStatusCode(
        code = error.code.value,
        description = error.code.description
    ),
    message = error.description,
    token = token,
    response = ""
)

fun <T> Response<T>.toRemote(): RemoteResponse<T> = RemoteResponse(
    code = RemoteStatusCode(
        code = code.value,
        description = code.description
    ),
    message = message,
    token = token,
    response = response
)

fun Response<User>.toRemoteUser(): RemoteResponse<SerializedUser> = RemoteResponse(
    code = RemoteStatusCode(
        code = code.value,
        description = code.description
    ),
    message = message,
    token = token,
    response = response.toRemote()
)

private fun User.toRemote(): SerializedUser = SerializedUser(
    username = username,
    password = password,
    salt = salt
)

fun RemoteAuthRequest.toDomain(): AuthRequest = AuthRequest(
    username = username,
    password = password
)