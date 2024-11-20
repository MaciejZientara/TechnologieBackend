package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;

/**
 * Data Transfer Object (DTO) representing the details of a training session.
 * It includes the user, start and end times, activity type, distance covered, and average speed.
 */
public record TrainingDto(User user,
                          Date startTime,
                          Date endTime,
                          ActivityType activityType,
                          double distance,
                          double averageSpeed) {
}
