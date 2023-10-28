package davidluna.com.app.di

import davidluna.com.app.di.interfaces.AppDataModule
import davidluna.com.app.di.interfaces.AppModule
import davidluna.com.app.di.interfaces.DataModule

object Inject {
    lateinit var appModule: AppModule
    lateinit var appDataModule: AppDataModule
    lateinit var dataModule: DataModule
}

inline fun <reified T, B> inject(factory: () -> B): T {
    return T::class.java.declaredConstructors.first().newInstance(factory()) as T
}