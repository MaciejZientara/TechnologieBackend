package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller responsible for handling HTTP requests related to training records.
 * This controller exposes endpoints to create, update, and retrieve training data.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Endpoint to retrieve all trainings.
     * This method fetches all training records from the database and maps them to DTOs.
     *
     * @return a list of {@link TrainingDto} objects representing all training records
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Endpoint to retrieve all trainings for a specific user by user ID.
     * This method fetches training records for a given user from the database and maps them to DTOs.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of {@link TrainingDto} objects representing all the user's training records
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findAllUserTrainings(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Endpoint to retrieve all trainings that were finished after a specific date.
     * The date is passed as a string in "yyyy-MM-dd" format.
     *
     * @param afterTime a date string representing the cutoff date to filter trainings by
     * @return a list of {@link TrainingDto} objects representing the trainings finished after the specified date
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getTrainingsAfterDate(@PathVariable String afterTime) {
        Date afterTimeDate = null;
        try {
            afterTimeDate = dateFormatter.parse(afterTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return trainingService.findAllTrainingsAfterDate(afterTimeDate)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Endpoint to retrieve all trainings of a specific activity type.
     * The activity type is passed as a query parameter.
     *
     * @param activityType the type of activity to filter trainings by (e.g., RUNNING, CYCLING)
     * @return a list of {@link TrainingDto} objects representing the trainings of the specified activity type
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        return trainingService.findAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Endpoint to create a new training record.
     * The training data is provided in the request body as a {@link TrainingUserIdDto}.
     *
     * @param newTrainingDto the DTO containing the data for the new training
     * @return a {@link TrainingDto} representing the created training record
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingUserIdDto newTrainingDto) {
        Training createdTraining = trainingService.createTraining(newTrainingDto);
        return trainingMapper.toDto(createdTraining);
    }

    /**
     * Endpoint to update an existing training record.
     * The training record to be updated is identified by the training ID in the URL path.
     * The new data is provided in the request body as a {@link TrainingUserIdDto}.
     *
     * @param trainingId the ID of the training to be updated
     * @param updateTrainingDto the DTO containing the updated training data
     * @return a {@link TrainingDto} representing the updated training record
     */
    @PutMapping("/{trainingId}")
    public TrainingDto updateTrainingById(@PathVariable Long trainingId, @RequestBody TrainingUserIdDto updateTrainingDto) {
        return trainingMapper.toDto(trainingService.updateTrainingById(trainingId, updateTrainingDto));
    }

}
