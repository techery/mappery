package io.techery.mappery.test.converter

import io.techery.mappery.Context
import io.techery.mappery.Converter

class DoubleToStringConverter : Converter<Double, String> {
    override fun convert(context: Context, source: Double) = source.toString()
}
