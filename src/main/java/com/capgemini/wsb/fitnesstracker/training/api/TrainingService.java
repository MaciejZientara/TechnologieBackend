package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingUserIdDto;

/**
 * Service interface for managing training sessions.
 * Provides methods for creating and updating training records.
 */
public interface TrainingService {

    /**
     * Creates a new training session.
     *
     * @param newTrainingDto the data transfer object containing details of the training to be created
     * @return the created {@link Training} entity
     */
    Training createTraining(TrainingUserIdDto newTrainingDto);

    /**
     * Updates an existing training session by its ID.
     *
     * @param trainingId         the ID of the training session to update
     * @param updateTrainingDto  the data transfer object containing updated details of the training
     * @return the updated {@link Training} entity
     */
    Training updateTrainingById(Long trainingId, TrainingUserIdDto updateTrainingDto);
}
