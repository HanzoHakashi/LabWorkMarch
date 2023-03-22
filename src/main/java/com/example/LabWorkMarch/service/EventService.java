package com.example.LabWorkMarch.service;

import com.example.LabWorkMarch.dao.EventDao;
import com.example.LabWorkMarch.dto.EventDto;
import com.example.LabWorkMarch.entity.Event;
import com.example.LabWorkMarch.exeption.ResourceNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventDao eventDao;

    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    private EventDto findByID(Long eventID){
        var event = eventDao.findEventByID(eventID)
                .orElseThrow(()-> new ResourceNotFoundException("Can't find  Event with ID "+eventID));
        return EventDto.from(event);
    }
    public List<EventDto> findAll(){
        var eventsList = eventDao.findAll();
        return eventsList.stream().map(EventDto::from).collect(Collectors.toList());
    }


}
