package com.example.notifications.application;

import com.example.notifications.adapter.out.persistence.NotificationJpaRepository;
import com.example.notifications.domain.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationJpaRepository repository;

    public NotificationService(NotificationJpaRepository repository) {
        this.repository = repository;
    }

    public Notification createNotification(Long taskId, String message) {
        Notification n = new Notification();
        n.setTaskId(taskId);
        n.setMessage(message);
        return repository.save(n);
    }

    public List<Notification> getByTaskId(Long taskId) {
        return repository.findByTaskId(taskId);
    }
}
