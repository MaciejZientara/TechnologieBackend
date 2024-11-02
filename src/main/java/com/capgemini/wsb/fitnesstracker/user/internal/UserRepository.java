package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Searches for users whose email contains the specified text, case-insensitive.
     *
     * @param email The partial email to search for
     * @return A {@link List} containing users with emails matching the specified text, or an empty list if none matched
     */
    default List<User> findAllByEmail(String email) {
        return findAll().stream()
                        .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                        .toList();
    }

    /**
     * Retrieves all users who were born before the specified date.
     *
     * @param time The date to compare users' birth dates against
     * @return A {@link List} containing users born before the specified date, or an empty list if none matched
     */
    default List<User> findOlder(LocalDate time){
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .toList();
    }
}
