package io.techery.mappery.test.converter;

import org.jetbrains.annotations.NotNull;

import io.techery.mappery.Converter;
import io.techery.mappery.MapperyContext;

public class ABConverter implements Converter<ABConverter.A, String> {
    @Override
    public String convert(@NotNull MapperyContext context, A source) {
        return source.toString();
    }

    public static class A {
    }

    public static class B extends A {
    }
}
