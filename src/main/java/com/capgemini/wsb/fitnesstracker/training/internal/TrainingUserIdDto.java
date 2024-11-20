package com.capgemini.wsb.fitnesstracker.training.internal;

import java.util.Date;

/**
 * A Data Transfer Object (DTO) for representing training details along with the user ID.
 * Used for transferring training data between layers or systems.
 * It encapsulates the necessary information for creating or updating a training record.
 */
public record TrainingUserIdDto(Long userId,
                                Date startTime,
                                Date endTime,
                                ActivityType activityType,
                                double distance,
                                double averageSpeed) {
}
