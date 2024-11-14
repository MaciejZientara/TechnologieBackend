package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TrainingMapper {
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

}


