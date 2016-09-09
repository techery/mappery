package io.techery.mappery

import java.util.*

class Mappery private constructor(private val converters: List<ConverterHolder<*, *>>) : MapperyContext {

    private val context = MapperyContextWrapper(this)

    override fun <T> convert(source: Any, clazzTo: Class<T>): T {
        val holder = converters.find(source.javaClass, clazzTo) ?:
                throw NullPointerException("Converter to convert ${source.javaClass} to $clazzTo is not found")
        val converter: Converter<Any, T> = holder.converter as Converter<Any, T>
        return converter.convert(context, holder.sourceClass.cast(source))
    }


    override fun <T> convert(source: Iterable<*>, clazzTo: Class<T>): List<T> {
        return source.map { convert(it!!, clazzTo) }
    }

    class Builder : MapperyBuilder {
        private val converters: MutableList<ConverterHolder<*, *>> = ArrayList()

        override fun <T> map(clazz: Class<T>): ClassBuilder<T> {
            return ClassBuilder(this, clazz)
        }

        private fun <S, T> addConverter(sourceClass: Class<S>, targetClass: Class<T>, converter: Converter<S, T>) {
            var sclass: Class<*> = sourceClass
            if (sclass.isPrimitive && PRIMITIVES.containsKey(sclass)) {
                sclass = PRIMITIVES[sclass] ?:
                        throw IllegalArgumentException("Couldn't find any wrapper fro primitive class $sourceClass")
            }
            if (converters.find(sclass, targetClass) != null) {
                throw IllegalArgumentException("Converter to convert $sourceClass to $targetClass is already registered")
            }
            converters.add(ConverterHolder(sclass, targetClass, converter))
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

    internal data class ConverterHolder<S, T>(val sourceClass: Class<S>, val targetClass: Class<T>, val converter: Converter<*, *>)

    private companion object {
        fun <S, T> List<ConverterHolder<*, *>>.find(sourceClazz: Class<S>, targetClazz: Class<T>): ConverterHolder<S, T>? {
            val holder = find {
                it.sourceClass.isAssignableFrom(sourceClazz)
                        && it.targetClass.isAssignableFrom(targetClazz)
            }
            return holder as ConverterHolder<S, T>?
        }
    }
}
