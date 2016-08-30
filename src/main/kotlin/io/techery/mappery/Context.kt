package io.techery.mappery

interface Context {

    fun <T> convert(source: Any, clazzTo: Class<T>): T
}