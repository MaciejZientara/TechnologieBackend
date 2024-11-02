package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return A {@link List} containing all users
     */
    List<User> findAllUsers();

    /**
     * Retrieves all users who have the specified email.
     *
     * @param email The email to search for users
     * @return A {@link List} containing users with the specified email, or an empty list if none found
     */
    List<User> getUsersByEmail(final String email);

    /**
     * Retrieves all users who were born before the specified date.
     *
     * @param time The date to compare users' birth dates against
     * @return A {@link List} containing users born before the specified date, or an empty list if none found
     */
    List<User> findAllUsersOlderThan(LocalDate time);
}