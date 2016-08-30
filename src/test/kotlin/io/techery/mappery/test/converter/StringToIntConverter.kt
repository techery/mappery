package io.techery.mappery.test.converter

import io.techery.mappery.Context
import io.techery.mappery.Converter

class StringToIntConverter : Converter<String, Int> {
    override fun convert(context: Context, source: String) = source.toInt()
}