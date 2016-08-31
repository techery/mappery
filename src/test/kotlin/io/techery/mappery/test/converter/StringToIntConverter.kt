package io.techery.mappery.test.converter

import io.techery.mappery.MapperyContext
import io.techery.mappery.Converter

class StringToIntConverter : Converter<String, Int> {
    override fun convert(context: MapperyContext, source: String) = source.toInt()
}
