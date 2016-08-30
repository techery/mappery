package io.techery.mappery.test

import io.techery.mappery.Mappery
import io.techery.mappery.test.converter.DoubleToStringConverter
import io.techery.mappery.test.converter.IntToStringConverter
import io.techery.mappery.test.converter.StringToDoubleConverter
import io.techery.mappery.test.converter.StringToIntConverter
import org.jetbrains.spek.api.Spek
import kotlin.test.assertNotNull

class MapperySpec : Spek({

    describe("Mappery Testing") {

        it("should map source class for converting") {
            val mappery = Mappery.Builder()
                    .map(String::class.java)
                    .from(Int::class.java, IntToStringConverter())
                    .from(Double::class.java, DoubleToStringConverter())
                    .build()
            var str = mappery.convert(Int.MAX_VALUE, String::class.java)
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
    }
})
