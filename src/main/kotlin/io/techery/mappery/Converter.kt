package io.techery.mappery

interface Converter<in S, out T> {

    fun convert(context: Context, source: S): T
}