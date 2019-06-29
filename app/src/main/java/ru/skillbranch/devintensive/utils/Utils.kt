package ru.skillbranch.devintensive.utils

object Utils {
    private val transliterationMap = mutableMapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName.isNullOrBlank()) return null to null
        val nameParts = fullName.split(' ')
        return when (nameParts.size) {
            1 -> nameParts[0] to null
            2 -> nameParts[0] to nameParts[1]
            else -> null to null
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank()) return null
        return if (lastName.isNullOrBlank()) {
            firstName.first().toUpperCase().toString()
        } else {
            firstName.first().toUpperCase().toString() + lastName.first().toUpperCase().toString()
        }
    }

    fun transliteration(payload: String?, divider: String = " "): String? {
        if (payload == null) return null
        var result = ""
        for (c in payload) {
            val current = c.toString()
            if (current == divider || current == " ") {
                result += divider
                continue
            }
            result += if (c.isUpperCase()) {
                transliterationMap.getOrElse(current.toLowerCase(), { current.toLowerCase() }).toUpperCase()
            } else {
                transliterationMap.getOrElse(current, { current })
            }
        }
        return result
    }
}