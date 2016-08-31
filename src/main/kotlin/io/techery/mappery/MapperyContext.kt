package io.techery.mappery

interface MapperyContext {

    fun <T> convert(source: Any, clazzTo: Class<T>): T
}
