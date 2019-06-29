package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    class Builder {
        var id: String = ""
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

        fun build(): User = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun firstName(firstName: String?): Builder {
            this.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            this.lastName = lastName
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        fun rating(rating: Int): Builder {
            this.rating = rating
            return this
        }

        fun respect(respect: Int): Builder {
            this.respect = respect
            return this
        }

        fun lastVisit(date: Date?): Builder {
            this.lastVisit = date
            return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            this.isOnline = isOnline
            return this
        }
    }

    companion object Factory {
        private var userId = -1

        fun makeUser(fullName: String?): User {
            ++userId
            val nameParts = fullName?.split(' ')
            nameParts?.let {
                return when (it.size) {
                    1 -> User(userId.toString(), it[0], null)
                    2 -> User(userId.toString(), it[0], it[1])
                    else -> User(userId.toString(), null, null)
                }
            } ?: return User(userId.toString(), null, null)
        }
    }
}