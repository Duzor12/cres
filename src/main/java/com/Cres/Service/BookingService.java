package com.Cres.Service;

import com.Cres.Model.Booking;
import com.Cres.Repository.BookingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    
    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking makeBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    

}