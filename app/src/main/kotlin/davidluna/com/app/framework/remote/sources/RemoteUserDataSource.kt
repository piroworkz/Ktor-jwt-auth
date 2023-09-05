package davidluna.com.app.framework.remote.sources

import arrow.core.Either
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import davidluna.com.app.framework.remote.entities.MongoUser
import davidluna.com.app.framework.remote.toDomain
import davidluna.com.app.framework.remote.toRemote
import davidluna.com.app.framework.utils.tryCatchSuspended
import davidluna.com.data.sources.UserDataSource
import davidluna.com.domain.AppError
import davidluna.com.domain.AuthRequest
import davidluna.com.domain.SaltedHash
import davidluna.com.domain.User
import kotlinx.coroutines.flow.firstOrNull

class RemoteUserDataSource(dataBase: MongoDatabase) : UserDataSource {

    private val collection = dataBase.getCollection<MongoUser>("users")

    override suspend fun getUserByUserName(userName: String): Either<AppError, User?> =
        tryCatchSuspended { collection.find(eq(MongoUser::username.name, userName)).firstOrNull().toDomain() }

    override suspend fun saveUser(request: AuthRequest, saltedHash: SaltedHash): Either<AppError, Boolean> =
        tryCatchSuspended { collection.insertOne(request.toRemote(saltedHash)).wasAcknowledged() }

    override suspend fun updateUser(request: AuthRequest, saltedHash: SaltedHash): Either<AppError, Boolean> =
        tryCatchSuspended {
            collection.replaceOne(eq(MongoUser::username.name, request.username), request.toRemote(saltedHash))
                .wasAcknowledged()
        }
}