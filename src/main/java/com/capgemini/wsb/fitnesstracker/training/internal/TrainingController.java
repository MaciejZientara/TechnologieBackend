package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findAllUserTrainings(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

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
    } // wyszukiwanie wszystkich treningów zakończonych (po konkretnej zdefiniowanej dacie)

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        return trainingService.findAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    } // wyszukiwanie wszystkich treningów dla konkretnej aktywności (np. wszystkie treningi biegowe)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingUserIdDto newTrainingDto) {
        Training createdTraining = trainingService.createTraining(newTrainingDto);
        return trainingMapper.toDto(createdTraining);
    } // utworzenie nowego treningu


    @PutMapping("/{trainingId}")
    public void updateTrainingById(@PathVariable Long trainingId, @RequestBody TrainingUserIdDto updateTrainingDto) {
        trainingService.updateTrainingById(trainingId, updateTrainingDto);
    } // aktualizacja treningu (dowolnie wybrane pole np. dystans)

}
