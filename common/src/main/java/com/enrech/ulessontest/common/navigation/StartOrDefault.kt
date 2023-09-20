package com.enrech.ulessontest.common.navigation

fun <T: Destination> startOrDefault(startDestination: T, default: T): T =
    if (startDestination.javaClass.name == default.javaClass.name) {
        startDestination
    } else {
        default
    }