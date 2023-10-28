package davidluna.com.data
//
//import arrow.core.Either
//import com.google.common.truth.Truth.assertThat
//import davidluna.com.data.sources.HashService
//import davidluna.com.data.sources.JWTService
//import davidluna.com.data.sources.UserDataSource
//import davidluna.com.testshared.fakeAuthRequest
//import davidluna.com.testshared.fakeSaltedHash
//import davidluna.com.testshared.fakeUser
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//import org.mockito.kotlin.anyVararg
//import org.mockito.kotlin.whenever
//
//@RunWith(MockitoJUnitRunner::class)
//class UserRepositoryUnitTest {
//
//    @Mock
//    lateinit var remote: UserDataSource
//
//    @Mock
//    lateinit var hashService: HashService
//
//    @Mock
//    lateinit var tokenService: JWTService
//
//    private lateinit var userRepository: UserRepository
//    private val request = fakeAuthRequest
//    private val user = fakeUser
//
//    @Before
//    fun setUp() {
//        userRepository = UserRepository(remote, hashService, tokenService)
//    }
//
//    @Test
//    fun `given a valid authRequest when saveUser then return a success response`() = runTest {
//        // Given
//        whenever(remote.getUserByUserName(request.username)).thenReturn(Either.Right(null))
//        whenever(hashService.hash(request.password)).thenReturn(fakeSaltedHash)
//        whenever(remote.saveUser(request, fakeSaltedHash)).thenReturn(Either.Right(true))
//        whenever(tokenService.generateToken(anyVararg())).thenReturn(Either.Right("FAKE JWT TOKEN"))
//        // When
//        val actual = userRepository.saveUser(request)
//        // Then
//        assertThat(actual.isRight()).isTrue()
//    }
//
//    @Test
//    fun `given an existing authRequest when saveUser then return an error response`() = runTest {
//        // Given
//        whenever(remote.getUserByUserName(request.username)).thenReturn(Either.Right(user))
//        // When
//        val actual = userRepository.saveUser(request)
//        // Then
//        assertThat(actual.isLeft()).isTrue()
//    }
//
//    @Test
//    fun `given a valid authRequest when login then return a success response`() = runTest {
//        // Given
//        whenever(remote.getUserByUserName(request.username)).thenReturn(Either.Right(user))
//        whenever(hashService.check(request.password, fakeSaltedHash)).thenReturn(true)
//        whenever(tokenService.generateToken(anyVararg())).thenReturn(Either.Right("FAKE JWT TOKEN"))
//        // When
//        val actual = userRepository.login(request)
//        // Then
//        assertThat(actual.isRight()).isTrue()
//    }
//
//    @Test
//    fun `given a non existing authRequest when login then return an error response`() = runTest {
//        // Given
//        whenever(remote.getUserByUserName(request.username)).thenReturn(Either.Right(null))
//        // When
//        val actual = userRepository.login(request)
//        // Then
//        assertThat(actual.isLeft()).isTrue()
//    }
//
//    @Test
//    fun `given an invalid password when login then return an error response`() = runTest {
//        // Given
//        whenever(remote.getUserByUserName(request.username)).thenReturn(Either.Right(user))
//        whenever(hashService.check(request.password, fakeSaltedHash)).thenReturn(false)
//        // When
//        val actual = userRepository.login(request)
//        // Then
//        assertThat(actual.isLeft()).isTrue()
//    }
//
//}