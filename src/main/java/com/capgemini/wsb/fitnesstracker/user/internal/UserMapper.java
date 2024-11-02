package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user The user entity to be converted
     * @return A {@link UserDto} containing user details
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Converts a {@link UserDto} to a {@link User} entity.
     *
     * @param userDto The DTO containing user data
     * @return A {@link User} entity created from the provided DTO
     */
    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    /**
     * Converts a {@link User} entity to a simplified {@link UserSimpleDto}, containing only ID, first name, and last name.
     *
     * @param user The user entity to be converted
     * @return A {@link UserSimpleDto} with the user's ID, first name, and last name
     */
    UserSimpleDto toSimpleDto(User user){
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts a {@link User} entity to an {@link IdEmailDto}, containing only the user's ID and email.
     *
     * @param user The user entity to be converted
     * @return An {@link IdEmailDto} with the user's ID and email
     */
    IdEmailDto toIdEmailDto(User user){
        return new IdEmailDto(
                user.getId(),
                user.getEmail());
    }

}
