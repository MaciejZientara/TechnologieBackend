package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions available in the system.
     *
     * @return a {@link List} of all training sessions
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves all training sessions associated with a specific user.
     *
     * @param userId the ID of the user whose training sessions are to be retrieved
     * @return a {@link List} of training sessions for the specified user
     */
    List<Training> findAllUserTrainings(final Long userId);

    /**
     * Retrieves all training sessions that were completed after a specific date and time.
     *
     * @param afterTime the {@link Date} after which the training sessions should be retrieved
     * @return a {@link List} of training sessions completed after the specified time
     */
    List<Training> findAllTrainingsAfterDate(Date afterTime);

    /**
     * Retrieves all training sessions of a specific activity type.
     *
     * @param type the {@link ActivityType} of the training sessions to retrieve
     * @return a {@link List} of training sessions matching the specified activity type
     */
    List<Training> findAllTrainingsByActivityType(ActivityType type);
}
