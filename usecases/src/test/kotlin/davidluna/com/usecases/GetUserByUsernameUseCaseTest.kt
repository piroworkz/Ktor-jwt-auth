package davidluna.com.usecases

import arrow.core.Either
import com.google.common.truth.Truth.assertThat
import davidluna.com.data.UserRepository
import davidluna.com.domain.AppError
import davidluna.com.testshared.fakeUser
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetUserByUsernameUseCaseTest {

    @Mock
    lateinit var repository: UserRepository

    private val getUserByUsernameUseCase by lazy { GetUserByUsernameUseCase(repository) }

    @Test
    fun `given an existing username when invoke then return a User on the right side of either`() = runTest {
        // Given

        whenever(repository.getUserByUserName(fakeUser.username)).thenReturn(Either.Right(fakeUser))
        // When
        val actual = getUserByUsernameUseCase(fakeUser.username)
        // Then
        assertThat(actual.isRight()).isTrue()
    }

    @Test
    fun `given a non existing username when invoke then return an AppError on the left side of either`() = runTest {
        // Given
        whenever(repository.getUserByUserName(fakeUser.username)).thenReturn(Either.Left(AppError.AccountExists(400)))
        // When
        val actual = getUserByUsernameUseCase(fakeUser.username)
        // Then
        assertThat(actual.isLeft()).isTrue()
    }

}