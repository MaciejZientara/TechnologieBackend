package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UpdateUserDto;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new {@link User} entity and persists it in the database.
     *
     * @param user The user entity to be created
     * @return The created {@link User} entity, including any generated fields like ID
     */
    User createUser(User user);

    /**
     * Deletes a {@link User} entity identified by the given ID.
     *
     * @param userId The ID of the user to be deleted
     */
    void deleteUser(final Long userId);

    /**
     * Get reference to User by ID, update all User data except ID.
     *
     * @param id ID of User to update
     * @param updateUserDto data of User to update (first and last name, birthdate and email)
     */
    void updateUserById(Long id, UpdateUserDto updateUserDto);
}
