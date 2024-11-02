package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return A {@link List} of {@link UserDto} containing details of all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Retrieves basic information (ID, first name, last name) about all users.
     *
     * @return A {@link List} of {@link UserSimpleDto} containing basic user information
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllBasicInformationAboutUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    /**
     * Adds a new user based on provided user data and persists it in the database.
     *
     * @param userDto The {@link UserDto} containing details of the user to add
     * @return The created {@link UserDto} containing generated ID and stored data
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        // Demonstracja how to use @RequestBody
//        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
//        return null;
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve
     * @return A {@link UserDto} containing details of the user
     * @throws IllegalArgumentException if the user with specified ID is not found
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
    }

    /**
     * Searches for users by email, returning only ID and email for each matching user.
     *
     * @param email The email (or part of it) to search for
     * @return A {@link List} of {@link IdEmailDto} containing ID and email of matching users
     */
    @GetMapping("/email")
    public List<IdEmailDto> getUsersByEmail(@RequestParam String email) {
        return userService.getUsersByEmail(email)
                .stream()
                .map(userMapper::toIdEmailDto)
                .toList();
    }

    /**
     * Retrieves all users who were born before the specified date.
     *
     * @param time The date to compare users' birth dates against
     * @return A {@link List} of {@link UserDto} containing users born before the specified date
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findAllUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}