package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class StringToIntConverter : Converter<String, Int> {
    override fun convert(context: MapperyContext, source: String): Int = source.toInt()
}
