//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.ashimjk.swaggerui.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@io.swagger.annotations.Api(value = "Beneficiary")
public interface Api {

    @ApiOperation(value = "", nickname = "cancel", response = Resource.class, tags = {"beneficiary"})
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Resource.class)})
    @RequestMapping(
            value = {"/api/v2/beneficiaries/{reference}/cancel"},
            produces = {"application/json"},
            method = {RequestMethod.PUT}
    )
    default ResponseEntity<Resource> cancel(@ApiParam(required = true) @PathVariable("reference") String reference) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "complete task on beneficiary", nickname = "completeTask", response = Resource.class, tags = {"beneficiary"})
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Resource.class)})
    @RequestMapping(
            value = {"/api/v2/beneficiaries/{reference}/tasks/{taskId}/complete/{action}"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    default ResponseEntity<Resource> completeTask(@ApiParam(value = "reference", required = true) @PathVariable("reference") String reference, @ApiParam(value = "taskId", required = true) @PathVariable("taskId") String taskId, @ApiParam(value = "action", required = true) @PathVariable("action") String action, @ApiParam(value = "variables", required = true) @RequestBody Map<String, Object> requestBody) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "create beneficiary", nickname = "createBeneficiary", notes = "create beneficiary", response = Resource.class, tags = {"beneficiary"})
    @ApiResponses({@ApiResponse(code = 201, message = "create beneficiary", response = Resource.class)})
    @RequestMapping(
            value = {"/api/v2/beneficiaries"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    default ResponseEntity<Resource> createBeneficiary(@ApiParam() @RequestBody(required = false) Request request) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
