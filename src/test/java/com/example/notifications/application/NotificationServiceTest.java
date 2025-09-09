package com.example.notifications.application;

import com.example.notifications.adapter.out.persistence.NotificationJpaRepository;
import com.example.notifications.domain.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private NotificationJpaRepository repository;
    private NotificationService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(NotificationJpaRepository.class);
        service = new NotificationService(repository);
    }

    @Test
    void testCreateNotification() {
        Notification n = new Notification();
        n.setTaskId(1L);
        n.setMessage("Test message");

        when(repository.save(any(Notification.class))).thenReturn(n);

        Notification created = service.createNotification(1L, "Test message");

        assertNotNull(created);
        assertEquals(1L, created.getTaskId());
        assertEquals("Test message", created.getMessage());

        verify(repository, times(1)).save(any(Notification.class));
    }

    @Test
    void testGetByTaskId() {
        Notification n1 = new Notification();
        n1.setTaskId(1L);
        n1.setMessage("Msg1");

        Notification n2 = new Notification();
        n2.setTaskId(1L);
        n2.setMessage("Msg2");

        when(repository.findByTaskId(1L)).thenReturn(List.of(n1, n2));

        List<Notification> list = service.getByTaskId(1L);

        assertEquals(2, list.size());
        assertEquals("Msg1", list.get(0).getMessage());
        assertEquals("Msg2", list.get(1).getMessage());
    }
}
