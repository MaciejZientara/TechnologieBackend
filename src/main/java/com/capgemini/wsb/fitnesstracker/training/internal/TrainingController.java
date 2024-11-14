package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

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
    public List<TrainingDto> getTrainingsAfterDate(@PathVariable LocalDate afterTime) {
        return trainingService.findAllTrainingsAfterDate(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    } // wyszukiwanie wszystkich treningów zakończonych (po konkretnej zdefiniowanej dacie)

    @GetMapping("/finished/activityType")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType type) {
        return trainingService.findAllTrainingsByActivityType(type)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    } // wyszukiwanie wszystkich treningów dla konkretnej aktywności (np. wszystkie treningi biegowe)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingDto trainingDto) throws InterruptedException {
        Training training = trainingMapper.toEntity(trainingDto);
        Training createdTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(createdTraining);
    } // utworzenie nowego treningu


    @PutMapping("/{trainingId}")
    public void updateTrainingById(@PathVariable Long trainingId, @RequestBody TrainingUpdateDto updateTrainingDto) {
        trainingService.updateTrainingById(trainingId, updateTrainingDto);
    } // aktualizacja treningu (dowolnie wybrane pole np. dystans)

}
