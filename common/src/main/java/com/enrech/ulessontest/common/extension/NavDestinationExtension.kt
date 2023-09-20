package com.enrech.ulessontest.common.extension

import androidx.navigation.NavDestination

val NavDestination.routeName get() = this.route?.let { it.split(Separator).firstOrNull() ?: this.route }

private const val Separator = "?"