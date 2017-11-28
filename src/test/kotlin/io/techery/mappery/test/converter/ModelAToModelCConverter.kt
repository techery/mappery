package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class ModelAToModelCConverter : Converter<ModelA, ModelC> {

    override fun convert(context: MapperyContext, source: ModelA): ModelC = ModelC()
}
