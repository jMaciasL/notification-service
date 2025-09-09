package com.example.notifications.adapter.in.web;

import com.example.notifications.application.NotificationService;
import com.example.notifications.domain.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public Notification create(@RequestParam Long taskId,
                               @RequestParam String message) {
        return service.createNotification(taskId, message);
    }

    @GetMapping("/{taskId}")
    public List<Notification> getByTask(@PathVariable Long taskId) {
        return service.getByTaskId(taskId);
    }
}
