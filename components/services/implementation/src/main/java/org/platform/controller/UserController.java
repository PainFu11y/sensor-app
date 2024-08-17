package org.platform.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.platform.constants.RoutConstants;
import org.platform.exceptions.ExceptionResponse;
import org.platform.model.User;
import org.platform.model.UserPassword;
import org.platform.service.UserService;
import org.platform.springJpa.TempUserSpringJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(RoutConstants.BASE_URL +  RoutConstants.VERSION + RoutConstants.USERS )
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TempUserSpringJpa tempUserSpringJpa;


    @Operation(summary = "Get user with given ID.") //
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),

    })
    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable UUID id) {
        log.info("Received request to get user with id.");
        User user = userService.getUserById(id);
        log.info("User found");
        return user;
    }

    @Operation(summary = "Create user with specified parameters.") //
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TempUser created.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "User already exists.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while creating user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),

    })
//    @PostMapping("/create-user")
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody User create(@RequestBody @Valid User user) {
//        log.info("Received request to create user.", user);
//        User newUser = userService.createUser(user);
//        log.info("User created Successfully.");
//
//        return newUser;
//    }
    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody @Valid User user) {
        log.info("Received request to create user.", user);
        String tempUserId = tempUserSpringJpa.createTempUser(user);
        return tempUserId;
    }
    @Operation(summary = "Create the first password for the user.") //
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "User already exists.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while creating user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),

    })
    @PostMapping("/{tempUserId}/password")
    @ResponseStatus(HttpStatus.CREATED)
    public User setPassword(@PathVariable String tempUserId,
                                            @RequestParam @Valid String password) {
        User createdUser = tempUserSpringJpa.completeUserCreation(tempUserId, password);
        log.info("User created Successfully.");
        return createdUser;
    }


    @Operation(summary = "Update password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Password updated.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to sent endpoint.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while updating password.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),

    })
    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable UUID userId,
                                               @RequestBody @Valid UserPassword password) {
        userService.updatePassword(userId, password);

    }


    @Operation(summary = "Get list of users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),

    })
    @GetMapping()
    public @ResponseBody List<User> getUsers() {
        log.info("Received request to get list of users.");
        List<User> users = userService.getUsers();
        log.info("Users found");
        return users;
    }


    @Operation(summary = "Get list of users by given email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while getting user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),

    })
    @GetMapping(params = "email")
    public @ResponseBody List<User> getUsersByEmail(@RequestParam (required = false) String email) {
        log.info("Received request to get list of users by email.");
        List<User> userByEmail = userService.getUserByEmail(email);
        log.info("Users found by email");
        return userByEmail;
    }

    @Operation(summary = "Delete user by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while deleting user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            })
    })
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable UUID userId) {
        log.info("Received request to delete user.");
        userService.deleteUser(userId);
        log.info("User deleted Successfully.");
    }


    @Operation(summary = "Update user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while updating user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            })
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable UUID id, @RequestBody @Valid User user) {
        log.info("Received request to update user.");
        User updatedUser = userService.updateUser(id,user);
        log.info("User updated Successfully.");
        return updatedUser;
    }


}
