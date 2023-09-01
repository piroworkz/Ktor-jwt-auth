package davidluna.com.app.framework.remote.sources

import arrow.core.Either
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import davidluna.com.app.framework.local.tryCatchSuspended
import davidluna.com.app.framework.model.RemoteUser
import davidluna.com.app.framework.remote.toDomain
import davidluna.com.app.framework.remote.toRemote
import davidluna.com.data.sources.UserDataSource
import davidluna.com.domain.AppError
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.SaltedHash
import davidluna.com.domain.User
import kotlinx.coroutines.flow.firstOrNull

class RemoteUserDataSource(dataBase: MongoDatabase) : UserDataSource {

    private val collection = dataBase.getCollection<RemoteUser>("users")
    override suspend fun getUserByUserName(userName: String): Either<AppError, User?> =
        tryCatchSuspended { findUserByName(userName) }

    override suspend fun saveUser(request: AuthRequest, saltedHash: SaltedHash): Either<AppError, Boolean> =
        tryCatchSuspended {
            val user: User? = findUserByName(request.username)
            if (user != null) throw AppError.AccountExists()
            if (!insertUser(request, saltedHash)) throw AppError.IOError()
            true
        }

    private suspend fun insertUser(request: AuthRequest, saltedHash: SaltedHash): Boolean {
        return collection.insertOne(request.toRemote(saltedHash)).wasAcknowledged()
    }

    private suspend fun findUserByName(username: String): User? {
        return collection.find(eq(RemoteUser::username.name, username)).firstOrNull().toDomain()
    }
}