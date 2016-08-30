package io.techery.mappery.test.converter

import io.techery.mappery.Context
import io.techery.mappery.Converter

class StringToDoubleConverter : Converter<String, Double> {
    override fun convert(context: Context, source: String) = source.toDouble()
}