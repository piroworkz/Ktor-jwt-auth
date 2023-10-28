package davidluna.com.app.di.interfaces

import davidluna.com.data.UserRepository

interface DataModule {
    val userRepository: UserRepository
}