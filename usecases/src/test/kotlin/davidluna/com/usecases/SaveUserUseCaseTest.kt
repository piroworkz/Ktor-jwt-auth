package davidluna.com.usecases

import arrow.core.Either
import com.google.common.truth.Truth.assertThat
import davidluna.com.data.UserRepository
import davidluna.com.domain.AppError
import davidluna.com.testshared.fakeAuthRequest
import davidluna.com.testshared.fakeSuccessBooleanResponse
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.test.Test

@RunWith(MockitoJUnitRunner::class)
class SaveUserUseCaseTest {

    @Mock
    lateinit var repository: UserRepository

    private val saveUserUseCase by lazy { SaveUserUseCase(repository) }

    @Test
    fun `given a valid request when invoke then return true on the right side of either`() = runTest {
        // Given
        whenever(repository.saveUser(fakeAuthRequest)).thenReturn(Either.Right(fakeSuccessBooleanResponse))
        // When
        val actual = saveUserUseCase(fakeAuthRequest)
        // Then
        assertThat(actual.isRight()).isTrue()
    }

    @Test
    fun `given an existing account request when invoke then return an AppError on the left side of either`() = runTest {
        // Given
        val request = fakeAuthRequest.copy(username = "invalid username")
        whenever(repository.saveUser(request)).thenReturn(Either.Left(AppError.AccountExists(400)))
        // When
        val actual = saveUserUseCase(request)
        // Then
        assertThat(actual.isLeft()).isTrue()
    }
}