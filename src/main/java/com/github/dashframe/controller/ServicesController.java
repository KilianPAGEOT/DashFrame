package com.github.dashframe.controller;

import com.github.dashframe.rest.api.ServicesApi;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@CrossOrigin
public class ServicesController implements ServicesApi {

    private final NativeWebRequest request;

    @Autowired
    public ServicesController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
}
