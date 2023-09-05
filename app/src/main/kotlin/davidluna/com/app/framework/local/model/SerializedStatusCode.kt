package davidluna.com.app.framework.local.model

import kotlinx.serialization.Serializable

@Serializable
data class SerializedStatusCode(
    val value: Int,
    val description: String,
)
