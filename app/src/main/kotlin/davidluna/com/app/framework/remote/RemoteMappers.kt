package davidluna.com.app.framework.remote

import davidluna.com.app.framework.model.RemoteUser
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.SaltedHash
import davidluna.com.domain.User

fun AuthRequest.toRemote(saltedHash: SaltedHash): RemoteUser = RemoteUser(
    username = username,
    password = saltedHash.hash,
    salt = saltedHash.salt
)

fun RemoteUser?.toDomain(): User? = if (this != null) {
    User(
        username = username,
        password = password,
        salt = salt
    )
} else {
    null
}