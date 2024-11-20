package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing {@link Training} entities.
 * Implements both {@link TrainingService} and {@link TrainingProvider} interfaces,
 * providing methods for creating, updating, and retrieving training records.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    /**
     * Retrieves a training by its ID.
     *
     * @param trainingId the ID of the training to be retrieved
     * @return an {@link Optional} containing the training if found
     * @throws UnsupportedOperationException if called, as the method is not yet implemented
     */
    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    /**
     * Retrieves all trainings stored in the database.
     *
     * @return a list of all {@link Training} objects
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all trainings for a specific user, identified by their user ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of {@link Training} objects associated with the given user
     */
    @Override
    public List<Training> findAllUserTrainings(final Long userId) {
        return trainingRepository.findTrainingsByUserId(userId);
    }

    /**
     * Retrieves all trainings that ended after a specified date.
     *
     * @param afterTime the {@link Date} after which the trainings should have ended
     * @return a list of {@link Training} objects that ended after the specified date
     */
    @Override
    public List<Training> findAllTrainingsAfterDate(Date afterTime) {
        return trainingRepository.findTrainingsAfterDate(afterTime);
    }

    /**
     * Retrieves all trainings of a specific activity type.
     *
     * @param type the {@link ActivityType} representing the desired activity type
     * @return a list of {@link Training} objects of the specified activity type
     */
    @Override
    public List<Training> findAllTrainingsByActivityType(ActivityType type) {
        return trainingRepository.findTrainingsByActivityType(type);
    }

    /**
     * Creates a new training record based on the provided {@link TrainingUserIdDto}.
     * The method first retrieves the user using their ID from the DTO. If the user does not exist,
     * an exception is thrown.
     * The method then creates and saves a new {@link Training} entity in the repository.
     *
     * @param newTrainingDto the DTO containing details of the new training
     * @return the newly created {@link Training} entity
     * @throws IllegalArgumentException if the user cannot be found with the provided ID
     */
    @Override
    public Training createTraining(TrainingUserIdDto newTrainingDto) {
        User usr = userProvider.getUserById(newTrainingDto.userId()).orElseThrow(() -> new IllegalArgumentException("User not found!"));
        Training newTraining = new Training(
                usr,
                newTrainingDto.startTime(),
                newTrainingDto.endTime(),
                newTrainingDto.activityType(),
                newTrainingDto.distance(),
                newTrainingDto.averageSpeed());
        return trainingRepository.save(newTraining);
    }

    /**
     * Updates an existing training record based on the provided training ID and new details from the DTO.
     * The method retrieves the {@link Training} by its ID. If the training is not found,
     * it will throw an exception.
     * Then, it updates the existing training entity with the new data and saves it back to the repository.
     */
    @Override
    public Training updateTrainingById(Long trainingId, TrainingUserIdDto updateTrainingDto) {
        User usr = userProvider.getUserById(updateTrainingDto.userId()).orElseThrow(() -> new IllegalArgumentException("User not found!"));
        Training trainingRef = trainingRepository.getReferenceById(trainingId);
        trainingRef.updateTraining(
                usr,
                updateTrainingDto.startTime(),
                updateTrainingDto.endTime(),
                updateTrainingDto.activityType(),
                updateTrainingDto.distance(),
                updateTrainingDto.averageSpeed());
        trainingRepository.save(trainingRef);
        return trainingRef;
    }

}
