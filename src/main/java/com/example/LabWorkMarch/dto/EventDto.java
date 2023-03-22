package com.example.LabWorkMarch.dto;

import com.example.LabWorkMarch.entity.Event;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EventDto {

    public static EventDto from(Event event){
        return builder()
                .id(event.getId())
                .date(event.getDate())
                .name(event.getName())
                .description(event.getDescription())
                .build();
    }
    private Long id;
    private LocalDate date;
    private String name;
    private String description;
    public Date getDate() {
        return Date.valueOf(date);
    }
}
