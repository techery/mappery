package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class ModelAToModelBConverter : Converter<ModelA, ModelB> {

    override fun convert(context: MapperyContext, source: ModelA): ModelB = ModelB()
}
