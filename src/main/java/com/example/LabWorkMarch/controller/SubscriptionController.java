package com.example.LabWorkMarch.controller;

import com.example.LabWorkMarch.dto.SubscriptionDto;
import com.example.LabWorkMarch.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping("/{email}")
    public List<SubscriptionDto> findByEmail(@RequestParam String email){
        return service.findByEmail(email);
    }

    @PostMapping("/subscribe/{eventID}")
    public ResponseEntity<Void> subscribe(@PathVariable("eventID") Long eventID, @RequestParam("email") String email){
        ResponseEntity response = service.subscribeToEvent(eventID,email);
        if (response.getStatusCode().is2xxSuccessful())
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable("id") Long eventID, @RequestParam("email") String email){
      if(service.cancelSubscription(eventID,email))
          return ResponseEntity.noContent().build();

      return ResponseEntity.notFound().build();
    }
}
