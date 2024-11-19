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

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }


    @Override
    public List<Training> findAllUserTrainings(final Long userId) {
        return trainingRepository.findTrainingsByUserId(userId);
    }

    @Override
    public List<Training> findAllTrainingsAfterDate(Date afterTime) {
        return trainingRepository.findTrainingsAfterDate(afterTime);
    }

    @Override
    public List<Training> findAllTrainingsByActivityType(ActivityType type) {
        return trainingRepository.findTrainingsByActivityType(type);
    }

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

    @Override
    public void updateTrainingById(Long trainingId, TrainingUserIdDto updateTrainingDto) {
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
    }

}
