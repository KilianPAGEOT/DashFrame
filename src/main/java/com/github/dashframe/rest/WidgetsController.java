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
    )public ResponseEntity<WidgetInstance> createWidget(
            @Valid @RequestBody(required = false) CreateWidgetRequest createWidgetRequest)  {
        Optional<Service> service = serviceDAO.findById(createWidgetRequest.getServiceId());
        if(service.isPresent()){
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
            return ResponseEntity.ok(new WidgetInstance().id(widget.getId())
                    .serviceId(widget.getService().getId())
                    .columnPos(widget.getColumn_pos()));
        } return ResponseEntity.badRequest().body(null);
    }
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/widgets",
            produces = { "application/json" },
            consumes = { "application/*" }
    )public ResponseEntity<ArrayList<WidgetInstance>> getWidgets(
            @Valid @RequestParam(required = false)  int userId)  {
        User user = userDao.findById(userId);
        ArrayList<Service> services = serviceDAO.findByUser(user);
        if(services.size()!=0){
            ArrayList<WidgetInstance> widgetInstances = new ArrayList<>();
            ArrayList<Widget> widgets = new ArrayList<>();
            for (Service service:services)
            {
                Widget widget= widgetDAO.findByService(service);
                if(widget!=null){
                    widgets.add(widget);
                }
            }
            if(widgets.size()!=0){
                for(Widget widget:widgets)
                {
                    /*WidgetConfiguration widgetConfiguration = new WidgetConfiguration();
                    widgetConfiguration.setType(widget.getType());
                    widgetConfiguration.setName(widget.getName());
                    widgetConfiguration.setRefreshRate(widget.getRefreshRate());*/
                    widgetInstances.add(new WidgetInstance().id(widget.getId())
                            .serviceId(widget.getService().getId())
                            .position(widget.getPosition())
                            .columnPos(widget.getColumn_pos())
                            /*.config(widgetConfiguration)*/);
                    System.out.println(widgetInstances);
                }
                return ResponseEntity.ok(widgetInstances);
            }
            return ResponseEntity.badRequest().body(null);

        } return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/widgets",
            produces = { "application/json" },
            consumes = { "application/*" }
    )public ResponseEntity<ArrayList<WidgetInstance>> DeleteWidgets(
            @Valid @RequestParam(required = false)  int userId)  {
        User user = userDao.findById(userId);
        ArrayList<Service> services = serviceDAO.findByUser(user);
        if(services.size()!=0){
            ArrayList<Widget> widgets = new ArrayList<>();
            for (Service service:services)
            {
                Widget widget= widgetDAO.findByService(service);
                if(widget!=null){
                    widgets.add(widget);
                }
            }
            if(widgets.size()!=0){
                for(Widget widget:widgets)
                {
                    widgetDAO.delete(widget);
                }
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body(null);

        } return ResponseEntity.badRequest().body(null);
    }
}
