package com.example.LabWorkMarch.service;

import com.example.LabWorkMarch.dao.EventDao;
import com.example.LabWorkMarch.dao.SubscriptionDao;
import com.example.LabWorkMarch.dto.SubscriptionDto;
import com.example.LabWorkMarch.entity.Event;
import com.example.LabWorkMarch.entity.Subscription;
import com.example.LabWorkMarch.exeption.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionDao subscriptionDao;
    private final EventDao eventDao;

    public SubscriptionService(SubscriptionDao subscriptionDao, EventDao eventDao) {
        this.subscriptionDao = subscriptionDao;
        this.eventDao = eventDao;
    }
    public List<SubscriptionDto> findByEmail(String email){
        var subscriptions = subscriptionDao.findSubscriptionByEmail(email);
        return subscriptions.stream().map(SubscriptionDto::from).collect(Collectors.toList());
    }
    public boolean cancelSubscription(Long eventID, String email){
        subscriptionDao.cancelSubscription(eventID, email);
        return true;
    }

    public ResponseEntity<?> subscribeToEvent(Long eventId, String email) {
        var eventDao1 = eventDao.findEventByID(eventId);
        if (eventDao.findEventByID(eventId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        LocalDate eventStartDate =eventDao1.get().getDate();
        if (eventStartDate.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("You can only subscribe to future events.");
        }

        Subscription subscription = new Subscription();
        subscription.setEventID(eventId);
        subscription.setEmail(email);
        subscription.setDateOfRegistration(LocalDate.now());
        subscriptionDao.add(subscription);


        return ResponseEntity.ok("You have registered for the event.");
    }

}
