package com.example.LabWorkMarch.util;

import com.example.LabWorkMarch.dao.EventDao;
import com.example.LabWorkMarch.dao.SubscriptionDao;
import com.example.LabWorkMarch.entity.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class InitDataBase {
    private static final Random r = new Random();
    @Bean
    CommandLineRunner init(EventDao eventDao, SubscriptionDao subscriptionDao){
        return args -> {
            eventDao.createTable();
            subscriptionDao.createTable();

            eventDao.deleteAll();
            subscriptionDao.deleteAll();
        };
    }
}
