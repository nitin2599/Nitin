package com.example.userManagement.controller;

import com.example.userManagement.model.GetErrorResponse;
import com.example.userManagement.model.UserResponse;
import com.example.userManagement.model.index.UserIndexModel;
import com.example.userManagement.service.UserServiceES;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

import static  com.example.userManagement.utils.Constants.*;


@Validated
@Api(value = "userHistory", description = "the userHistory API")
@RestController
class UserHistoryApi {


    @Autowired
    private UserServiceES userServiceES;

    /**
     * GET /userHistory/{userId}/change-log : Get the User change log
     *
     * @param userId       userId that needs to be deleted (required)
     * @param from         Start time of the change log in unix epoch format. (required)
     * @param limit        Number of items to return. (optional, default to 50)
     * @param offset       Number of items to skip from start. (optional, default to 0)
     * @return This is a user info!. (status code 200)
     * or Error Occurred (status code 400)
     * or Error Occurred (status code 401)
     * or Error Occurred (status code 404)
     */
    @ApiOperation(value = "Get the User change log", nickname = "getUserChangeLog", notes = "", response = UserResponse.class, tags = {"Change Log",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is a user info!.", response = List.class),
            @ApiResponse(code = 400, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 401, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 404, message = "Error Occurred", response = GetErrorResponse.class)})
    @GetMapping(value = "/userHistory/{userId}/change-log")
    public List<UserIndexModel> getUserChangeLog(
            @ApiParam(value = "userId that needs to be deleted", required = true) @PathVariable("userId") UUID userId,
            @NotNull @ApiParam(value = "Start time of the change log in unix epoch format.", required = true)
            @Valid @RequestParam(value = "from", required = true) long from, @Min(1) @Max(31)
            @ApiParam(value = "Number of days for which change log is to be returned.", defaultValue = TO_DATE_DEFAULT)
            @Valid @RequestParam(value = "toDate", required = false, defaultValue = TO_DATE_DEFAULT) long toDate, @Min(1)
            @ApiParam(value = "Number of items to return.", defaultValue = LIMIT)
            @Valid @RequestParam(value = "limit", required = false, defaultValue = LIMIT) Integer limit, @Min(0)
            @ApiParam(value = "Number of items to skip from start.", defaultValue = OFF_SET_DEFAULT_VALUE)
            @Valid @RequestParam(value = "offset", required = false, defaultValue = OFF_SET_DEFAULT_VALUE) Integer offset) {
       return userServiceES.userHistory(userId, from, toDate, limit, offset);
    }

}

