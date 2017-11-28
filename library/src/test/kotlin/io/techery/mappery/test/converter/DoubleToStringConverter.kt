package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class DoubleToStringConverter : Converter<Double, String> {
    override fun convert(context: MapperyContext, source: Double): String = source.toString()
}
