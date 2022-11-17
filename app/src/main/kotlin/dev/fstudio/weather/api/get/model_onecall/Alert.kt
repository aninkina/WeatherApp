package dev.fstudio.weather.api.get.model_onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Alert(
    val description: String,
    val end: Int,
    val event: String,
    @SerialName("sender_name")
    val senderName: String,
    val start: Int,
    val tags: List<String>
)