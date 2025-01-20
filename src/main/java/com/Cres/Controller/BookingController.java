package com.Cres.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

import com.Cres.Model.Booking;
import com.Cres.Service.BookingService;
import com.Cres.Model.Booking;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/makebooking")
    public ResponseEntity<Booking> makeBooking(@RequestBody Booking booking) {
        try {
            Booking savedBooking = bookingService.makeBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    

}
