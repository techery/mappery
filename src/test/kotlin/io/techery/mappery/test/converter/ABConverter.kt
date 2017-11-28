package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class ABConverter : Converter<ABConverter.A, String> {

    override fun convert(context: MapperyContext, source: A): String {
        return source.toString()
    }

    open class A

    class B : A()
}
