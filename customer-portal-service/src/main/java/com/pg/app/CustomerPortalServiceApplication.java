package com.pg.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pg.app.entity.Room;
import com.pg.app.repository.RoomRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CustomerPortalServiceApplication 
{
    public static void main(String[] args)
    {
        SpringApplication.run(CustomerPortalServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(RoomRepository roomRepository) {
        return args -> {
            if (roomRepository.count() == 0) {
                roomRepository.save(Room.builder()
                        .roomNo("101")
                        .type("Single")
                        .price(5000)
                        .booked(true)
                        .build());

                roomRepository.save(Room.builder()
                        .roomNo("102")
                        .type("Double")
                        .price(8000)
                        .booked(false)
                        .build());

                roomRepository.save(Room.builder()
                        .roomNo("103")
                        .type("Deluxe")
                        .price(12000)
                        .booked(false)
                        .build());

                roomRepository.save(Room.builder()
                        .roomNo("104")
                        .type("Single")
                        .price(5000)
                        .booked(false)
                        .build());

//                System.out.println("Preloaded sample room data.");
                log.info("Preloaded sample room data.");
            }
        };
    }
}