package com.yefan.study.spring.reactive.utils;

import com.yefan.study.spring.reactive.exception.NotFoundResourceException;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Supplier;

public class MonoUtils {
    public static <T> Mono<T> fromOptional(Optional<T> option) {
        return option.map(Mono::just).orElseGet(Mono::empty);
    }

    public static <T> Mono<T> fromOptional(Optional<T> option, Supplier<? extends Exception> errorSupplier) {
        return option.map(Mono::just).orElseGet(() -> Mono.error(errorSupplier.get()));
    }

    public static <T> Mono<T> fromOptionalWithNotFoundException(Optional<T> option, String resourceName) {
        return option.
                map(Mono::just).
                orElseGet(() -> Mono.error(new NotFoundResourceException(resourceName)));
    }
}
