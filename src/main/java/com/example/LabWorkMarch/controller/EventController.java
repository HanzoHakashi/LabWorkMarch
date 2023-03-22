package com.example.LabWorkMarch.controller;

import com.example.LabWorkMarch.dto.EventDto;
import com.example.LabWorkMarch.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> findAll(){
        return eventService.findAll();
    }
}
