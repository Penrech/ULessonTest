package com.enrech.ulessontest.common_domain

abstract class BaseMapper<in From, To> {

    abstract fun mapFrom(from: From): To

    fun mapFromList(from: List<From?>?) = from?.filterNotNull()?.map { mapFrom(it) } ?: emptyList()
}
