package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Component responsible for sending email summaries of user training data.
 * Emails are sent monthly to all users with registered email addresses.
 */
@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class EmailSender {
    @Autowired
    private JavaMailSender emailSender;

    private final TrainingProvider trainingProvider;
    private final UserProvider userProvider;

    /**
     * Sends a simple email message to the specified recipient.
     *
     * @param to   recipient's email address
     * @param text content of the email
     */
    private void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("training@FitnessTracker.com");
        message.setTo(to);
        message.setSubject("TrainingSummary");
        message.setText(text);
        emailSender.send(message);
    }

    /**
     * Scheduled task that sends monthly training summaries to all users.
     * Each user receives an email with the total number of their trainings.
     */
    @Scheduled(cron = "@monthly")
    public void emailSender() {
        List<User> allUsers = userProvider.findAllUsers();

        for (User u : allUsers) {
            if (u.getEmail().isEmpty()) continue;
            List<Training> userTrainings = trainingProvider.findAllUserTrainings(u.getId());

            String text = "Trainings in total: " + userTrainings.size();
            sendEmail(u.getEmail(), text);
        }
    }

}
