package com.example.notifications.adapter.out.persistence;

import com.example.notifications.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByTaskId(Long taskId);
}
