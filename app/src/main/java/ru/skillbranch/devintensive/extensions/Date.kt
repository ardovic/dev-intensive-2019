package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnit: TimeUnits): Date {
    this.time = when (timeUnit) {
        TimeUnits.SECOND -> this.time + value * SECOND
        TimeUnits.MINUTE -> this.time + value * MINUTE
        TimeUnits.HOUR -> this.time + value * HOUR
        TimeUnits.DAY -> this.time + value * DAY
    }
    return this
}

fun Date.humanizeDiff(): String {
    var result = ""
    var timeDiff = Date().time - this.time
    if (timeDiff > 0) {
        result = when (timeDiff) {
            in 0..SECOND -> "только что"
            in SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE -> {
                when (timeDiff / MINUTE) {
                    2L, 3L, 4L -> "${timeDiff / MINUTE} минуты назад"
                    else -> "${timeDiff / MINUTE} минут назад"
                }
            }
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> {
                when (timeDiff / HOUR) {
                    2L, 3L, 4L -> "${timeDiff / HOUR} часа назад"
                    else -> "${timeDiff / HOUR} часов назад"
                }
            }
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> {
                when (timeDiff / DAY) {
                    2L, 3L, 4L -> "${timeDiff / DAY} дня назад"
                    else -> "${timeDiff / DAY} дней назад"
                }
            }
            else -> "более года назад"
        }
    } else {
        timeDiff = abs(timeDiff)
        result = when (timeDiff) {
            in 0..SECOND -> "только что"
            in SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> {
                when (timeDiff / MINUTE) {
                    2L, 3L, 4L -> "через ${timeDiff / MINUTE} минуты"
                    else -> "через ${timeDiff / MINUTE} минут"
                }
            }
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> {
                when (timeDiff / HOUR) {
                    2L, 3L, 4L -> "через ${timeDiff / HOUR} часа"
                    else -> "через ${timeDiff / HOUR} часов"
                }
            }
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> {
                when (timeDiff / DAY) {
                    2L, 3L, 4L -> "через ${timeDiff / DAY} дня"
                    else -> "через ${timeDiff / DAY} дней"
                }
            }
            else -> "более чем через год"
        }
    }
    return result
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}