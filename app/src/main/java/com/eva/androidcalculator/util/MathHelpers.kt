package com.eva.androidcalculator.util

fun Int.factorial(): Double {
    var result = 1.0
    for (i in 1..this) {
        result *= i
    }
    return result
}