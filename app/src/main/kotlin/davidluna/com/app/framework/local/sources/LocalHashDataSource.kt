package davidluna.com.app.framework.local.sources

import davidluna.com.data.sources.HashService
import davidluna.com.domain.SaltedHash
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class LocalHashDataSource : HashService {

    override fun hash(value: String, length: Int): SaltedHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(length)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")
        return SaltedHash(salt = saltAsHex, hash = hash)
    }


    override fun check(plainPassword: String, hashed: SaltedHash): Boolean {
        val hash = DigestUtils.sha256Hex("${hashed.salt}$plainPassword")
        return hash == hashed.hash
    }

}