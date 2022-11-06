package com.github.dashframe.rest;

import com.github.dashframe.rest.api.WidgetsApi;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@CrossOrigin
public class WidgetsController implements WidgetsApi {

    private final NativeWebRequest request;

    @Autowired
    public WidgetsController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
}
