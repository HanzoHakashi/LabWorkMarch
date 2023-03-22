package com.example.LabWorkMarch.dto;

import com.example.LabWorkMarch.entity.Subscription;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubscriptionDto {

    public static SubscriptionDto from(Subscription subscription){
        return builder()
                .id(subscription.getId())
                .eventID(subscription.getEventID())
                .email(subscription.getEmail())
                .dateOfRegistration(subscription.getDateOfRegistration())
                .build();
    }

    private Long id;
    private Long eventID;
    private String email;
    private LocalDate dateOfRegistration;
    public Date getDate() {
        return Date.valueOf(dateOfRegistration);
    }
}
