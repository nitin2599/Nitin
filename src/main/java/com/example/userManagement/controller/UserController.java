package com.example.userManagement.controller;

import com.example.userManagement.kafka.EventChannelHandler;
import com.example.userManagement.model.*;
import com.example.userManagement.model.index.UserIndexModel;
import com.example.userManagement.service.UserService;
import com.example.userManagement.service.UserServiceES;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


import static com.example.userManagement.utils.Constants.USER_ID;

@Validated
@Api(value = "user", description = "the user API")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventChannelHandler channelHandler;

    @Autowired
    private UserServiceES userServiceES;


    /**
     * POST /user : Create user
     * We can create user by adding user info!
     *
     * @param user user Request
     * @return This is a user info!. (status code 200)
     * or Error Occurred (status code 400)
     * or Error Occurred (status code 404)
     */
    @ApiOperation(value = "Create user", nickname = "createUser", notes = "We can create user by adding user info!", response = User.class, tags = {"user operations",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is a user info!.", response = User.class),
            @ApiResponse(code = 400, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 404, message = "Error Occurred", response = GetErrorResponse.class)})
    @PostMapping(
            value = "/user",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<User> createUser(@ApiParam(value = "user Request") @Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        channelHandler.publishUserEvent(Event.CREATE, user);
        return ResponseEntity.ok(createdUser);
    }


    /**
     * DELETE /user/{userId} : Delete user
     *
     * @param userId userId that needs to be deleted (required)
     *               or Error Occurred (status code 400)
     *               or Error Occurred (status code 404)
     */
    @ApiOperation(value = "Delete user", nickname = "deleteUser", notes = "This can only be done by the logged in user.", response = UUID.class, tags = {"user operations",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is a user info!.", response = UUID.class),
            @ApiResponse(code = 400, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 404, message = "Error Occurred", response = GetErrorResponse.class)})
    @DeleteMapping(
            value = "/user/{" + USER_ID + "}",
            produces = {"application/json"}
    )
    public ResponseEntity<Boolean> deleteUser(@ApiParam(value = USER_ID + " that needs to be deleted", required = true)
                                              @PathVariable(USER_ID) UUID userId) {
        userService.deleteUserById(userId);
        channelHandler.publishUserEventWithUserId(Event.DELETE, userId);
        return ResponseEntity.ok(true);
    }


    /**
     * GET /user : Get all User data  with some filters.
     * Get list of all Users data
     *
     * @param query Query criteria. Refer following links for more details:&lt;br/&gt; - [match](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query.html)&lt;br/&gt; - [multi_match](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-multi-match-query.html)&lt;br/&gt; - [term](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-term-query.html)&lt;br/&gt; - [terms](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-terms-query.html)&lt;br/&gt; - [range](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-range-query.html)&lt;br/&gt; - [regexp](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-regexp-query.html) (optional)
     * @return This is a user info!. (status code 200)
     * or Error Occurred (status code 400)
     * or Error Occurred (status code 401)
     * or Error Occurred (status code 404)
     */
    @ApiOperation(value = "Get all User data  with some filters.", nickname = "getAllUsers", notes = "Get list of all Users data ", response = UserResponse.class, tags = {"Filter user data",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is a user info!.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 401, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 404, message = "Error Occurred", response = GetErrorResponse.class)})
    @GetMapping(value = "/user")
    public List<UserIndexModel> getAllUsers(@ApiParam(value = "Query criteria. Refer following links for more details:<br/> - [match](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-match-query.html)<br/> - [multi_match](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-multi-match-query.html)<br/> - [term](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-term-query.html)<br/> - [terms](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-terms-query.html)<br/> - [range](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-range-query.html)<br/> - [regexp](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-regexp-query.html)")
                                            @Valid Query query) {
        return userServiceES.findUserByQuery(query);
    }


    /**
     * GET /user/{userId} : Get user by user id
     *
     * @param userId (required)
     * @return This is a user info!. (status code 200)
     * or Error Occurred (status code 400)
     * or Error Occurred (status code 404)
     */
    @ApiOperation(value = "Get user by user id", nickname = "getUserById", notes = "", response = User.class, tags = {"user operations",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is a user info!.", response = User.class),
            @ApiResponse(code = 400, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 404, message = "Error Occurred", response = GetErrorResponse.class)})
    @GetMapping(
            value = "/user/{" + USER_ID + "}",
            produces = {"application/json"}
    )
    public User getUserById(@ApiParam(value = "user id which user need to get", required = true) @PathVariable(USER_ID) UUID userId) {
        User userById = userService.getUserById(userId);
        channelHandler.publishUserEvent(Event.GET, userById);
        return userById;
    }

    /**
     * PUT /user/{userId} : Updated user
     * This can only be done by the logged in user.
     *
     * @param userId (required)
     * @param user   user Request (optional)
     * @return This is a user info!. (status code 200)
     * or Error Occurred (status code 400)
     * or Error Occurred (status code 404)
     */
    @ApiOperation(value = "Updated user", nickname = "updateUser", notes = "This can only be done by the logged in user.", response = User.class, tags = {"user operations",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "This is a user info!.", response = User.class),
            @ApiResponse(code = 400, message = "Error Occurred", response = GetErrorResponse.class),
            @ApiResponse(code = 404, message = "Error Occurred", response = GetErrorResponse.class)})
    @PutMapping(
            value = "/user/{" + USER_ID + "}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<User> updateUser(@ApiParam(value = "User id to update a user", required = true)
                                           @PathVariable(USER_ID) UUID userId, @ApiParam(value = "user Request")
                                           @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        channelHandler.publishUserEvent(Event.UPDATE, user);
        return ResponseEntity.ok(updatedUser);
    }
}
