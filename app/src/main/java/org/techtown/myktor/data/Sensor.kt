package org.techtown.myktor.data

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage (
    val id: String,
    val content: String
)