package com.example.notifications.adapter.in.web;

import com.example.notifications.domain.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NotificationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/notifications";
    }

    @Test
    void testCreateAndGetNotifications() {
        // Crear notificación
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<Notification> response = restTemplate.postForEntity(
                baseUrl() + "?taskId={taskId}&message={msg}",
                request,
                Notification.class,
                1L, "Tarea creada"
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Notification created = response.getBody();
        assertNotNull(created);
        assertEquals(1L, created.getTaskId());
        assertEquals("Tarea creada", created.getMessage());

        // Obtener notificaciones por taskId
        ResponseEntity<Notification[]> getResponse = restTemplate.getForEntity(
                baseUrl() + "/{taskId}",
                Notification[].class,
                1L
        );

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Notification[] notifications = getResponse.getBody();
        assertNotNull(notifications);
        assertTrue(notifications.length >= 1);
    }
}
