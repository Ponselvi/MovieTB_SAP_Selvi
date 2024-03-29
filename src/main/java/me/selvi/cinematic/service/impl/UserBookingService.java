package me.selvi.cinematic.service.impl;

import me.selvi.cinematic.model.Booking;
import me.selvi.cinematic.model.User;
import me.selvi.cinematic.repository.BookingRepository;
import me.selvi.cinematic.service.BookingService;
import me.selvi.cinematic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private BookingService bookingService;

    public Booking saveUserAndBooking(User user, Booking booking) {

        User existingUser = userService.getUserByName(user.getUserName());
        if(existingUser != null) {
            booking.setUser(existingUser);
        } else {
            existingUser = userService.pushUser(user);
        }
        return bookingRepository.save(booking);
    }
}
