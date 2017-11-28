package io.techery.mappery.test

import io.techery.mappery.Mappery
import io.techery.mappery.test.converter.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertNotNull
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class MapperySpec : Spek({

    describe("Mappery Testing") {

        it("should map source class for converting") {
            val mappery = Mappery.Builder()
                    .map(String::class.java)
                    .from(Int::class.java, IntToStringConverter())
                    .from(Double::class.java, DoubleToStringConverter())
                    .from(ABConverter.A::class.java, ABConverter())
                    .build()
            var str = mappery.convert(Int.MAX_VALUE, String::class.java)
            assertNotNull(str)
            str = mappery.convert(ABConverter.B(), String::class.java)
            assertNotNull(str)
            str = mappery.convert(Double.MAX_VALUE, String::class.java)
            assertNotNull(str)
        }

        it("should map target class for converting") {
            val mappery = Mappery.Builder()
                    .map(String::class.java)
                    .to(Int::class.java, StringToIntConverter())
                    .to(Double::class.java, StringToDoubleConverter())
                    .build()
            val string = "1"
            val intValue = mappery.convert(string, Int::class.java)
            assertNotNull(intValue)
            val doubleValue = mappery.convert(string, Double::class.java)
            assertNotNull(doubleValue)
        }

        it("should map target class to inherited classes") {
            val mappery = Mappery.Builder()
                    .map(ModelA::class.java)
                    .to(ModelB::class.java, ModelAToModelBConverter())
                    .to(ModelC::class.java, ModelAToModelCConverter())
                    .build()
            val a = ModelA()
            val intValue = mappery.convert(a, ModelB::class.java)
            assertNotNull(intValue)
            val doubleValue = mappery.convert(a, ModelC::class.java)
            assertNotNull(doubleValue)
        }
    }
})
