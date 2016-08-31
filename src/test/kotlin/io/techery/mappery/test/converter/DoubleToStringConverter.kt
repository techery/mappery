package io.techery.mappery.test.converter

import io.techery.mappery.MapperyContext
import io.techery.mappery.Converter

class DoubleToStringConverter : Converter<Double, String> {
    override fun convert(context: MapperyContext, source: Double) = source.toString()
}
