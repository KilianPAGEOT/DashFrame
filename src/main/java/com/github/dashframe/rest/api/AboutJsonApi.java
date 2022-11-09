/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.github.dashframe.rest.api;

import com.github.dashframe.models.json.GetAboutJson200Response;
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
    date = "2022-11-09T11:51:12.913786908+01:00[Europe/Paris]"
)
@Validated
public interface AboutJsonApi {
    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /about.json : Information about the supported services
     *
     * @return Expected response to a valid request (status code 200)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/about.json", produces = { "application/json" })
    default ResponseEntity<GetAboutJson200Response> getAboutJson() {
        getRequest()
            .ifPresent(request -> {
                for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString =
                            "{ \"server\" : { \"services\" : [ { \"name\" : \"weather\", \"description\" : \"description\", \"iconUrl\" : \"https://openapi-generator.tech\", \"widgets\" : [ { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] }, { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] } ] }, { \"name\" : \"weather\", \"description\" : \"description\", \"iconUrl\" : \"https://openapi-generator.tech\", \"widgets\" : [ { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] }, { \"name\" : \"city_temperature\", \"description\" : \"Display temperature for a city\", \"params\" : [ { \"name\" : \"city\", \"type\" : \"string\" }, { \"name\" : \"city\", \"type\" : \"string\" } ] } ] } ], \"current_time\" : 1531680780 }, \"client\" : { \"name\" : \"city\", \"type\" : \"string\" } }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                }
            });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
