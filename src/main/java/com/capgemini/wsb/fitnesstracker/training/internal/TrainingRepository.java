package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findTrainingsByUserId(final long userId){
        return findAll().stream()
                .filter(training -> training.getUser().getId() == userId)
                .toList();
//        warning that getUser() may return Null - is it really the case?
    }

    default List<Training> findTrainingsByActivityType(ActivityType type) {
        return findAll().stream()
                .filter(training -> training.getActivityType() == type)
                .toList();
    }

    default List<Training> findTrainingsAfterDate(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime))
                .toList();
    }

}
