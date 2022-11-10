package com.github.dashframe.service.service;

import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Not an actual RSS service
 */
@RestController
public class FakeExternalService {

    @GetMapping("/data")
    public Data getData() {
        return new Data("Title", "lorem ipsum");
    }

    @PostMapping("/check")
    public void checkStuff() {}

    public record Data(@NotNull String title, @NotNull String content) {}
}
