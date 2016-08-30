package io.techery.mappery.test.converter

import io.techery.mappery.Context
import io.techery.mappery.Converter

class IntToStringConverter : Converter<Int, String> {
    override fun convert(context: Context, source: Int) = source.toString()
}