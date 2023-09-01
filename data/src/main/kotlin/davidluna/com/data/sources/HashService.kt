package davidluna.com.data.sources

import davidluna.com.domain.SaltedHash

interface HashService {
    fun hash(value: String, length: Int = 32): SaltedHash
    fun check(plainPassword: String, hashed: SaltedHash): Boolean
}