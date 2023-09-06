package davidluna.com.usecases

import arrow.core.Either
import com.google.common.truth.Truth
import davidluna.com.data.UserRepository
import davidluna.com.domain.AppError
import davidluna.com.domain.AppError.UserNotFound
import davidluna.com.testshared.fakeAuthRequest
import davidluna.com.testshared.fakeSuccessUserResponse
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.Test

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    private val loginUseCase by lazy { LoginUseCase(repository) }

    @Test
    fun `given a valid request when invoke then return a User on the right side of either`() = runTest {
        // Given
        whenever(repository.login(fakeAuthRequest)).thenReturn(Either.Right(fakeSuccessUserResponse))
        // When
        val actual = loginUseCase(fakeAuthRequest)
        // Then
        Truth.assertThat(actual.isRight()).isTrue()
    }

    @Test
    fun `given a non existent username when invoke then return an AppError on the left side of either`() = runTest {
        // Given
        val request = fakeAuthRequest.copy(username = "non existent username")
        whenever(repository.login(request)).thenReturn(Either.Left(UserNotFound(400)))
        // When
        val actual = loginUseCase(request)
        // Then
        Truth.assertThat(actual.isLeft()).isTrue()
    }

    @Test
    fun `given a wrong password when invoke then return an AppError on the left side of either`() = runTest {
        // Given
        val request = fakeAuthRequest.copy(password = "wrong password")
        whenever(repository.login(request)).thenReturn(Either.Left(AppError.UnAuthorized(401)))
        // When
        val actual = loginUseCase(request)
        // Then
        Truth.assertThat(actual.isLeft()).isTrue()
    }

}