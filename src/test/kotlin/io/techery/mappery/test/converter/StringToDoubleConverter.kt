package io.techery.mappery.test.converter

import io.techery.mappery.MapperyContext
import io.techery.mappery.Converter

class StringToDoubleConverter : Converter<String, Double> {
    override fun convert(context: MapperyContext, source: String) = source.toDouble()
}
