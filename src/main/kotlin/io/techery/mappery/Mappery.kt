package io.techery.mappery

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class Mappery private constructor(private val converters: ConcurrentMap<Pair<Class<*>, Class<*>>, Converter<*, *>>) : MapperyContext {

    private val context = MapperyContextWrapper(this)

    override fun <T> convert(source: Any, clazzTo: Class<T>): T {
        val converter = converter(source.javaClass, clazzTo) ?:
                throw NullPointerException("Converter to convert ${source.javaClass} to $clazzTo is not found")
        return converter.convert(context, source)
    }


    override fun <T> convert(source: Iterable<*>, clazzTo: Class<T>): List<T> {
        return source.map { convert(it!!, clazzTo) }
    }

    private fun <S, T> converter(sourceClazz: Class<S>, targetClazz: Class<T>): Converter<S, T>? {
        return converters[Pair(sourceClazz, targetClazz)] as? Converter<S, T>
    }

    class Builder : MapperyBuilder {
        private val converters: ConcurrentMap<Pair<Class<*>, Class<*>>, Converter<*, *>> = ConcurrentHashMap()

        override fun <T> map(clazz: Class<T>): ClassBuilder<T> {
            return ClassBuilder(this, clazz)
        }

        private fun <S, T> addConverter(sourceClass: Class<S>, targetClass: Class<T>, converter: Converter<S, T>) {
            var sclass: Class<*> = sourceClass
            if (sclass.isPrimitive && PRIMITIVES.containsKey(sclass)) {
                sclass = PRIMITIVES[sclass] ?:
                        throw IllegalArgumentException("Couldn't find any wrapper fro primitive class $sourceClass")
            }
            val key = Pair(sclass, targetClass)
            if (converters.contains(key)
                    && converters[key] != converter) {
                throw IllegalArgumentException("Converter to convert $sourceClass to $targetClass is already registered")
            }
            converters[key] = converter
        }

        override fun build(): Mappery = Mappery(converters)

        class ClassBuilder<M> internal constructor(private val builder: Builder,
                                                   private val clazz: Class<M>) : MapperyBuilder {
            fun <F> from(clazzFrom: Class<F>, converter: Converter<F, M>): ClassBuilder<M> {
                builder.addConverter(clazzFrom, clazz, converter)
                return this
            }

            fun <T> to(clazzTo: Class<T>, converter: Converter<M, T>): ClassBuilder<M> {
                builder.addConverter(clazz, clazzTo, converter)
                return this
            }

            override fun <T> map(clazz: Class<T>): ClassBuilder<T> = builder.map(clazz)

            override fun build(): Mappery = builder.build()
        }

        private companion object {
            private val PRIMITIVES: MutableMap<Class<*>, Class<*>> = HashMap(16)

            init {
                with(PRIMITIVES) {
                    put(Boolean::class.javaPrimitiveType!!, Boolean::class.javaObjectType)
                    put(Byte::class.javaPrimitiveType!!, Byte::class.javaObjectType)
                    put(Short::class.javaPrimitiveType!!, Short::class.javaObjectType)
                    put(Char::class.javaPrimitiveType!!, Char::class.javaObjectType)
                    put(Int::class.javaPrimitiveType!!, Int::class.javaObjectType)
                    put(Long::class.javaPrimitiveType!!, Long::class.javaObjectType)
                    put(Float::class.javaPrimitiveType!!, Float::class.javaObjectType)
                    put(Double::class.javaPrimitiveType!!, Double::class.javaObjectType)
                    put(Void::class.java, Void::class.javaObjectType)
                }
            }
        }
    }


    internal interface MapperyBuilder {
        fun <T> map(clazz: Class<T>): Builder.ClassBuilder<T>
        fun build(): Mappery
    }

    internal class MapperyContextWrapper(private val context: MapperyContext) : MapperyContext {
        override fun <T> convert(source: Any, clazzTo: Class<T>): T {
            return context.convert(source, clazzTo)
        }

        override fun <T> convert(source: Iterable<*>, clazzTo: Class<T>): List<T> {
            return context.convert(source, clazzTo)
        }
    }
}
