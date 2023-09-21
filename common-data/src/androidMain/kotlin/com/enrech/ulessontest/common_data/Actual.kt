package com.enrech.ulessontest.common_data

import java.net.UnknownHostException
actual fun isOffline(throwable: Throwable): Boolean = throwable is UnknownHostException
