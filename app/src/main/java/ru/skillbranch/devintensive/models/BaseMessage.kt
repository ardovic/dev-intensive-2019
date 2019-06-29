package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {

    abstract fun formatMessage(): String

    companion object AbstractFactory {
        private var messageId = -1

        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date,
            type: String,
            payload: String,
            isIncoming: Boolean = false
        ): BaseMessage? {
            messageId++
            return when (type) {
                "text" -> TextMessage(messageId.toString(), from, chat, isIncoming, date, payload)
                else -> ImageMessage(messageId.toString(), from, chat, isIncoming, date, payload)
            }
        }
    }
}
