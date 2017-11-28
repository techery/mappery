package io.techery.mappery

interface MapperyContext {

    fun <T> convert(source: Any, clazzTo: Class<T>): T

    fun <T> convert(source: Iterable<*>, clazzTo: Class<T>): List<T>
}
