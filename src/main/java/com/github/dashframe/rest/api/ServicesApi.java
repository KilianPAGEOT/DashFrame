/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.github.dashframe.rest.api;

import com.github.dashframe.models.json.CreateService400Response;
import com.github.dashframe.models.json.CreateServiceRequest;
import com.github.dashframe.models.json.ListServices200Response;
import com.github.dashframe.models.json.ListServices401Response;
import com.github.dashframe.models.json.ListServices404Response;
import com.github.dashframe.models.json.ServiceInstance;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-06T17:27:32.778551651+01:00[Europe/Paris]"
)
@Validated
public interface ServicesApi {
    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /services : Create a new service instance
     *
     * @param createServiceRequest  (optional)
     * @return Expected response to a valid request (status code 200)
     *         or Returned if the requested was malformed (status code 400)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/services",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ServiceInstance> createService(
        @Valid @RequestBody(required = false) CreateServiceRequest createServiceRequest
    ) {
        getRequest()
            .ifPresent(request -> {
                for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString =
                            "{ \"id\" : 24, \"type\" : \"steam\", \"username\" : \"Xx_DarkGam3R_xX\", \"token\" : \"c3VwZXIgc2VjcmV0IHRva2VuCg==\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                }
            });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * DELETE /services/{serviceId} : Delete a specific service instance
     *
     * @param serviceId  (required)
     * @return All services of type or service was deleted successfully (status code 204)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/services/{serviceId}", produces = { "application/json" })
    default ResponseEntity<Void> deleteService(@Min(0) @PathVariable("serviceId") Integer serviceId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * DELETE /services : Removes all registered service instances of the user
     *
     * @param userId The ID of an user, assumed to be the current user if not specified (optional)
     * @return All services were deleted successfully (status code 204)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/services", produces = { "application/json" })
    default ResponseEntity<Void> deleteServices(
        @Min(0) @Valid @RequestParam(value = "userId", required = false) Integer userId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /services : Returns the list of available services and services instances registered by the user
     *
     * @param userId The ID of an user, assumed to be the current user if not specified (optional)
     * @return Expected response to a valid request (status code 200)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/services", produces = { "application/json" })
    default ResponseEntity<ListServices200Response> listServices(
        @Min(0) @Valid @RequestParam(value = "userId", required = false) Integer userId
    ) {
        getRequest()
            .ifPresent(request -> {
                for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString =
                            "{ \"instances\" : [ { \"id\" : 24, \"type\" : \"steam\", \"username\" : \"Xx_DarkGam3R_xX\", \"token\" : \"c3VwZXIgc2VjcmV0IHRva2VuCg==\" }, { \"id\" : 24, \"type\" : \"steam\", \"username\" : \"Xx_DarkGam3R_xX\", \"token\" : \"c3VwZXIgc2VjcmV0IHRva2VuCg==\" } ], \"available\" : [ { \"name\" : \"weather\", \"description\" : \"description\", \"iconUrl\" : \"https://openapi-generator.tech\", \"widgets\" : [ { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] }, { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] } ] }, { \"name\" : \"weather\", \"description\" : \"description\", \"iconUrl\" : \"https://openapi-generator.tech\", \"widgets\" : [ { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] }, { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] } ] } ] }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                }
            });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
