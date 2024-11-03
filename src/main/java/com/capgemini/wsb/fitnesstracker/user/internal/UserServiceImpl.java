package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new {@link User} entity and persists it in the database.
     * Throws an {@link IllegalArgumentException} if the user already has an ID, as updates are not permitted in this method.
     *
     * @param user The user entity to be created
     * @return The created {@link User} entity, including any generated fields like ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes a {@link User} entity identified by the given ID.
     *
     * @param userId The ID of the user to be deleted
     */
    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Retrieves all users who were born before the specified date.
     *
     * @param time The date to compare users' birth dates against
     * @return A {@link List} containing users born before the specified date, or an empty list if none matched
     */
    @Override
    public List<User> findAllUsersOlderThan(LocalDate time) { return userRepository.findOlder(time); }

    /**
     * Searches for users whose email contains the specified text, case-insensitive.
     *
     * @param email The partial email to search for
     * @return A {@link List} containing users with emails matching the specified text, or an empty list if none matched
     */
    @Override
    public List<User> getUsersByEmail(final String email) {
        return userRepository.findAllByEmail(email);
    }

    /**
     * Retrieves a user based on their ID.
     *
     * @param userId The ID of the user to be retrieved
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user based on their email.
     *
     * @param email The email of the user to be retrieved
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all users.
     *
     * @return A {@link List} containing all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get reference to User by ID, update all User data except ID.
     *
     * @param id ID of User to update
     * @param updateUserDto data of User to update (first and last name, birthdate and email)
     */
    @Override
    public void updateUserById(Long id, UpdateUserDto updateUserDto) {
        User userRef = userRepository.getReferenceById(id);
        userRef.UpdateUser(updateUserDto.firstName(),
                updateUserDto.lastName(),
                updateUserDto.birthdate(),
                updateUserDto.email());
    }

}