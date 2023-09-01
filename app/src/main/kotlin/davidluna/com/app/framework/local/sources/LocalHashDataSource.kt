package davidluna.com.app.framework.local.sources

import davidluna.com.domain.SaltedHash
import davidluna.com.data.sources.HashService
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class LocalHashDataSource : HashService {
    override fun hash(value: String, length: Int): SaltedHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(length)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")
        println("<-- received value: $value \n salt: $salt \n saltAsHex: $saltAsHex \n hash: $hash")
        return SaltedHash(salt = saltAsHex, hash = hash)
    }

    override fun check(plainPassword: String, hashed: SaltedHash): Boolean {
        val hash = DigestUtils.sha256Hex("${hashed.salt}$plainPassword")
        println("<-- received Hashed: $hashed \n hash: $hash")
        return hash == hashed.hash
    }
}