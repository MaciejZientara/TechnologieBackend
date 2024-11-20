package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Mapper class for converting between {@link Training} entity and {@link TrainingDto} Data Transfer Object (DTO).
 * Helps in transforming the data from the persistence layer (entity) to the application layer (DTO)
 * and vice versa, ensuring the separation of concerns and facilitating data transfer.
 */
@Component
public class TrainingMapper {

    /**
     * Converts a {@link Training} entity into a {@link TrainingDto}.
     *
     * @param training The {@link Training} entity to be converted.
     * @return A {@link TrainingDto} representing the details of the training.
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a {@link TrainingDto} into a {@link Training} entity.
     *
     * @param trainingDto The {@link TrainingDto} to be converted into an entity.
     * @return A {@link Training} entity populated with the details from the {@link TrainingDto}.
     */
    Training toEntity(TrainingDto trainingDto){
        return new Training(trainingDto.user(),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }
}


