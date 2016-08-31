package io.techery.mappery

interface Converter<in S, out T> {

    fun convert(context: MapperyContext, source: S): T
}
