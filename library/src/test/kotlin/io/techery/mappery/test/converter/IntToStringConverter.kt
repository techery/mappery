package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class IntToStringConverter : Converter<Int, String> {
    override fun convert(context: MapperyContext, source: Int): String = source.toString()
}
