package com.github.dashframe.rest;

import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.*;
import com.github.dashframe.rest.api.WidgetsApi;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin
public class WidgetsController implements WidgetsApi {

    private final NativeWebRequest request;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    public WidgetsController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/widgets",
            produces = { "application/json" },
            consumes = { "application/json" }
    )public ResponseEntity<WidgetInstance> createWidget(
            @Valid @RequestBody(required = false) CreateWidgetRequest createWidgetRequest
    ) throws NoSuchFieldException {
        EpicGamesFreeGamesWidgetConfiguration epicegame;
        System.out.println(createWidgetRequest.getConfig());
        System.out.println(createWidgetRequest.getServiceId());
        Optional<Service> service = serviceDAO.findById(createWidgetRequest.getServiceId());
        if(service.isPresent()){
            System.out.println("ok");
            Widget widget = widgetDAO.save(
                    new Widget(
                            service.get(),
                            createWidgetRequest.getConfig().getName(),
                            createWidgetRequest.getPosition(),
                            createWidgetRequest.getColumnPos(),
                            createWidgetRequest.getConfig().getType(),
                            createWidgetRequest.getConfig().getRefreshRate()
                    )
            );
            System.out.println(widget);
            return ResponseEntity.ok(new WidgetInstance().id(widget.getId()).serviceId(widget.getService().getId()).columnPos(widget.getColumn_pos()));

        } return ResponseEntity.badRequest().body(null);
    }
}
