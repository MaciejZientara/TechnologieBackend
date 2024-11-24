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

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class EmailSender {
    @Autowired
    private JavaMailSender emailSender;

    private final TrainingProvider trainingProvider;
    private final UserProvider userProvider;

    private void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("training@FitnessTracker.com");
        message.setTo(to);
        message.setSubject("TrainingSummary");
        message.setText(text);
        emailSender.send(message);
    }

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
