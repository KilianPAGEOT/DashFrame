package com.github.dashframe.rest;

import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.*;
import com.github.dashframe.rest.api.WidgetsApi;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@CrossOrigin
public class WidgetsController implements WidgetsApi {

    private final NativeWebRequest request;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    private UserDAO userDao;

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
    )
    public ResponseEntity<WidgetInstance> createWidget(
        @Valid @RequestBody(required = false) CreateWidgetRequest createWidgetRequest
    ) {
        Optional<Service> service = serviceDAO.findById(createWidgetRequest.getServiceId());
        if (service.isPresent()) {
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
            return ResponseEntity.ok(
                new WidgetInstance()
                    .id(widget.getId())
                    .serviceId(widget.getService().getId())
                    .columnPos(widget.getColumn_pos())
            );
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/widgets",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<ArrayList<WidgetInstance>> getWidgets(@Valid @RequestParam(required = false) int userId) {
        User user = userDao.findById(userId);
        ArrayList<Service> services = serviceDAO.findByUser(user);
        if (services.size() != 0) {
            ArrayList<WidgetInstance> widgetInstances = new ArrayList<>();
            ArrayList<Widget> widgets = new ArrayList<>();
            for (Service service : services) {
                Widget widget = widgetDAO.findByService(service);
                if (widget != null) {
                    widgets.add(widget);
                }
            }
            if (widgets.size() != 0) {
                for (Widget widget : widgets) {
                    /*WidgetConfiguration widgetConfiguration = new WidgetConfiguration();
                    widgetConfiguration.setType(widget.getType());
                    widgetConfiguration.setName(widget.getName());
                    widgetConfiguration.setRefreshRate(widget.getRefreshRate());*/
                    widgetInstances.add(
                        new WidgetInstance()
                            .id(widget.getId())
                            .serviceId(widget.getService().getId())
                            .position(widget.getPosition())
                            .columnPos(widget.getColumn_pos())
                        /*.config(widgetConfiguration)*/
                    );
                    System.out.println(widgetInstances);
                }
                return ResponseEntity.ok(widgetInstances);
            }
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/widgets",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<ArrayList<WidgetInstance>> DeleteWidgets(@Valid @RequestParam(required = false) int userId) {
        User user = userDao.findById(userId);
        ArrayList<Service> services = serviceDAO.findByUser(user);
        if (services.size() != 0) {
            ArrayList<Widget> widgets = new ArrayList<>();
            for (Service service : services) {
                Widget widget = widgetDAO.findByService(service);
                if (widget != null) {
                    widgets.add(widget);
                }
            }
            if (widgets.size() != 0) {
                for (Widget widget : widgets) {
                    widgetDAO.delete(widget);
                }
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/widgets/:{widgetId}",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<WidgetInstance> GetWidget(@Valid @PathVariable(required = false) int widgetId) {
        Widget widget = widgetDAO.findById(widgetId);
        if (widget != null) {
            return ResponseEntity.ok(
                new WidgetInstance()
                    .id(widget.getId())
                    .serviceId(widget.getService().getId())
                    .name(widget.getName())
                    .position(widget.getPosition())
                    .columnPos(widget.getColumn_pos())
            );
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/widgets/:{widgetId}",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<WidgetInstance> PutWidget(
        @Valid @PathVariable(required = false) int widgetId,
        @Valid @RequestBody(required = false) UpdateWidgetRequest UpdateWidgetRequest
    ) {
        Widget widget = widgetDAO.findById(widgetId);
        System.out.println(widget);
        if (widget != null) {
            System.out.println(UpdateWidgetRequest.getConfig());
            widget.setType(UpdateWidgetRequest.getConfig().getType());
            widget.setName(UpdateWidgetRequest.getConfig().getName());
            widget.setRefreshRate(UpdateWidgetRequest.getConfig().getRefreshRate());
            widgetDAO.save(widget);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/widgets/:{widgetId}",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<WidgetInstance> DeleteWidget(@Valid @PathVariable(required = false) int widgetId) {
        Widget widget = widgetDAO.findById(widgetId);
        if (widget != null) {
            widgetDAO.delete(widget);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(null);
    }
}
