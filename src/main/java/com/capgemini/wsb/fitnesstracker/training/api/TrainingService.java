package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingUserIdDto;

public interface TrainingService {

    Training createTraining(TrainingUserIdDto newTrainingDto);

    Training updateTrainingById(Long trainingId, TrainingUserIdDto updateTrainingDto);
}
