package com.github.dashframe.util;

import java.util.function.Consumer;
import reactor.core.publisher.Mono;

public final class MonoUtil {

    /**
     * Discard non-critical errors thrown by a {@link Mono}.
     * (When thrown errors are instances of {@link Exception})
     */
    public static <T> Mono<T> ignoreExceptions(Mono<T> mono, Consumer<Throwable> onError) {
        return mono
            .doOnError(onError)
            .onErrorResume(error -> {
                // discard error if it is an Exception to prevent other widget from breaking
                if (error instanceof Exception) {
                    return Mono.empty();
                } else {
                    return Mono.error(error);
                }
            });
    }
}
