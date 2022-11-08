/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.github.dashframe.rest.api;

import com.github.dashframe.models.json.CreateToken400Response;
import com.github.dashframe.models.json.CreateToken401Response;
import com.github.dashframe.models.json.CreateUser404Response;
import com.github.dashframe.models.json.CreateWidgetRequest;
import com.github.dashframe.models.json.UpdateWidgetRequest;
import com.github.dashframe.models.json.WidgetInstance;
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
    date = "2022-11-08T15:27:52.118173200+01:00[Europe/Paris]"
)
@Validated
public interface WidgetsApi {
    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /widgets : Create a new widget instance in the given user&#39;s board
     *
     * @param createWidgetRequest  (optional)
     * @return Widget was created successfully (status code 201)
     *         or Returned if the requested was malformed (status code 400)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/widgets",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<WidgetInstance> createWidget(
        @Valid @RequestBody(required = false) CreateWidgetRequest createWidgetRequest
    ) {
        getRequest()
            .ifPresent(request -> {
                for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString =
                            "{ \"id\" : 42, \"service_id\" : 1, \"order\" : 3, \"column_pos\" : 1, \"config\" : { \"type\" : \"steam/friends_list\", \"parameters\" : { \"showOffline\" : 1 } }, \"createdAt\" : \"2022-11-07 15:17:56.69100\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                }
            });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * DELETE /widgets/{widgetId} : Delete a widget
     *
     * @param widgetId The ID of a widget (required)
     * @return The widget was deleted successfully (status code 204)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/widgets/{widgetId}", produces = { "application/json" })
    default ResponseEntity<Void> deleteWidget(@Min(0) @PathVariable("widgetId") Integer widgetId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * DELETE /widgets : Clear all the widgets in the user&#39;s board
     *
     * @param userId The ID of an user, assumed to be the current user if not specified (optional)
     * @return Board was cleared successfully (status code 204)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/widgets", produces = { "application/json" })
    default ResponseEntity<Void> deleteWidgets(
        @Min(0) @Valid @RequestParam(value = "userId", required = false) Integer userId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /widgets/{widgetId} : Get information about a specific widget
     *
     * @param widgetId The ID of a widget (required)
     * @return Expected response to a valid request (status code 200)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/widgets/{widgetId}", produces = { "application/json" })
    default ResponseEntity<WidgetInstance> getWidget(@Min(0) @PathVariable("widgetId") Integer widgetId) {
        getRequest()
            .ifPresent(request -> {
                for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString =
                            "{ \"id\" : 42, \"service_id\" : 1, \"order\" : 3, \"column_pos\" : 1, \"config\" : { \"type\" : \"steam/friends_list\", \"parameters\" : { \"showOffline\" : 1 } }, \"createdAt\" : \"2022-11-07 15:17:56.69100\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                }
            });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /widgets : Returns the layout of the requested user&#39;s board
     *
     * @param userId The ID of an user, assumed to be the current user if not specified (optional)
     * @return Expected response to a valid request (status code 200)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/widgets", produces = { "application/json" })
    default ResponseEntity<List<WidgetInstance>> listWidgets(
        @Min(0) @Valid @RequestParam(value = "userId", required = false) Integer userId
    ) {
        getRequest()
            .ifPresent(request -> {
                for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString =
                            "{ \"id\" : 42, \"service_id\" : 1, \"order\" : 3, \"column_pos\" : 1, \"config\" : { \"type\" : \"steam/friends_list\", \"parameters\" : { \"showOffline\" : 1 } }, \"createdAt\" : \"2022-11-07 15:17:56.69100\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                }
            });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * PATCH /widgets/{widgetId} : Update an existing widget&#39;s configuration
     *
     * @param widgetId The ID of a widget (required)
     * @param updateWidgetRequest  (optional)
     * @return The widget was updated successfully (status code 204)
     *         or Returned if the requested was malformed (status code 400)
     *         or The user is not logged in (status code 401)
     *         or Returned if the requested resource doesn&#39;t exist, or the user does not have access (status code 404)
     */
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/widgets/{widgetId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> updateWidget(
        @Min(0) @PathVariable("widgetId") Integer widgetId,
        @Valid @RequestBody(required = false) UpdateWidgetRequest updateWidgetRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
