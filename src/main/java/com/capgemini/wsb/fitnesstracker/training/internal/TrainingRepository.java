package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for {@link Training} entity, providing methods to access and query training records.
 * This interface extends {@link JpaRepository} to provide standard CRUD operations.
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all trainings for a specific user identified by the provided user ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of {@link Training} objects associated with the given user
     */
    default List<Training> findTrainingsByUserId(final long userId){
        return findAll().stream()
                .filter(training -> training.getUser().getId() == userId)
                .toList();
//        warning that getUser() may return Null - is it really the case?
    }

    /**
     * Finds all trainings that match a specific activity type.
     *
     * @param type the {@link ActivityType} representing the desired activity type
     * @return a list of {@link Training} objects of the specified activity type
     */
    default List<Training> findTrainingsByActivityType(ActivityType type) {
        return findAll().stream()
                .filter(training -> training.getActivityType() == type)
                .toList();
    }

    /**
     * Finds all trainings that ended after the specified date.
     *
     * @param afterTime the {@link Date} after which the trainings should have ended
     * @return a list of {@link Training} objects that ended after the specified date
     */
    default List<Training> findTrainingsAfterDate(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime))
                .toList();
    }
}
