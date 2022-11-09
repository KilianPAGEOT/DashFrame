package com.github.dashframe.rest;

import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.json.CreateServiceRequest;
import com.github.dashframe.models.json.CreateUserRequest;
import com.github.dashframe.models.json.ServiceInstance;
import com.github.dashframe.models.json.UserInstance;
import com.github.dashframe.rest.api.ServicesApi;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@CrossOrigin
public class ServicesController implements ServicesApi {

    private final NativeWebRequest request;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private UserDAO userDao;

    @Autowired
    public ServicesController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/services",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public ResponseEntity<ServiceInstance> createService(
        @Valid @RequestBody(required = false) CreateServiceRequest createServiceRequest
    ) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userDao.findByUsername(user) != null) {
            Service service = serviceDAO.save(
                new Service(
                    userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
                    createServiceRequest.getType(),
                    createServiceRequest.getToken(),
                    createServiceRequest.getUsername()
                )
            );
            return ResponseEntity.ok(
                new ServiceInstance()
                    .id(service.getId())
                    .type(service.getType())
                    .username(service.getUser().getUsername())
                    .token(service.getToken())
                    .createdAt(service.getCreatedAt())
            );
        } else return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/services",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<ArrayList<ServiceInstance>> getServices(@Valid @RequestParam(required = false) int userId) {
        User user = userDao.findById(userId);
        ArrayList<Service> services = serviceDAO.findByUser(user);
        System.out.println(services);
        if (services.size() != 0) {
            ArrayList<ServiceInstance> serviceInstance = new ArrayList<>();
            for (Service service : services) {
                serviceInstance.add(
                    new ServiceInstance()
                        .id(service.getId())
                        .type(service.getType())
                        .username(service.getUser().getUsername())
                        .token(service.getToken())
                );
            }
            return ResponseEntity.ok(serviceInstance);
        } else return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/services",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<? extends Object> DeleteServices(@Valid @RequestParam(required = false) int userId) {
        User user = userDao.findById(userId);
        ArrayList<Service> services = serviceDAO.findByUser(user);
        System.out.println(services);
        if (services.size() != 0) {
            ArrayList<ServiceInstance> serviceInstance = new ArrayList<>();
            for (Service service : services) {
                serviceDAO.delete(service);
            }
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/services/:{serviceId}",
        produces = { "application/json" },
        consumes = { "application/*" }
    )
    public ResponseEntity<? extends Object> DeleteService(@Valid @PathVariable(required = false) int serviceId) {
        Service services = serviceDAO.findById(serviceId);
        if (services != null) {
            serviceDAO.delete(services);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.badRequest().body(null);
    }
}
