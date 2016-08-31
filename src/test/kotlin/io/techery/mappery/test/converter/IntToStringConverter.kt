package io.techery.mappery.test.converter

import io.techery.mappery.MapperyContext
import io.techery.mappery.Converter

class IntToStringConverter : Converter<Int, String> {
    override fun convert(context: MapperyContext, source: Int) = source.toString()
}
