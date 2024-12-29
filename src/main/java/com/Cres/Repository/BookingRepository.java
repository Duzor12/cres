package com.Cres.Repository;

import com.Cres.Model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    // Find all bookings for a specific date
    List<Booking> findByDate(LocalDate date);
    
    // Find bookings by email
    List<Booking> findByEmail(String email);
    
    // Find bookings by name containing the search term (case-insensitive)
    List<Booking> findByNameContainingIgnoreCase(String name);
    
    // Find bookings created after a certain datetime
    List<Booking> findByCreatedAtAfter(LocalDateTime dateTime);


    
}
