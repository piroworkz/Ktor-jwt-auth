package davidluna.com.app.framework.remote

import davidluna.com.app.framework.remote.entities.MongoUser
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.Role
import davidluna.com.domain.SaltedHash
import davidluna.com.domain.User

fun AuthRequest.toRemote(saltedHash: SaltedHash): MongoUser = MongoUser(
    username = username,
    password = saltedHash.hash,
    salt = saltedHash.salt,
    role = role.name
)

fun MongoUser?.toDomain(): User? = if (this != null) {
    User(
        username = username,
        password = password,
        salt = salt,
        role = Role.valueOf(role)
    )
} else {
    null
}