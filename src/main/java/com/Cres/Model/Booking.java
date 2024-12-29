package com.Cres.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Document(collection = "booking")
@Getter
@Setter
public class Booking {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime createdAt;


    public Booking(String name, String email, String phone, LocalDate date, LocalTime time) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.createdAt = LocalDateTime.now();
    }
}

