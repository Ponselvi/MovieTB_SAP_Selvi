package me.selvi.cinematic.service.impl;

import me.selvi.cinematic.model.Booking;
import me.selvi.cinematic.model.User;
import me.selvi.cinematic.repository.BookingRepository;
import me.selvi.cinematic.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    @Autowired
    private UserBookingService userBookingService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long booking_id) {
        return null;
    }

    @Override
    public Booking pushBooking(Booking newBooking) {
        User user = newBooking.getUser();
        return userBookingService.saveUserAndBooking(user, newBooking);
       // return bookingRepository.save(newBooking);
    }

    @Override
    public Booking updateBooking(Booking updatedBooking, Long booking_id) {
        return null;
    }

    @Override
    public void deleteBookingById(Long booking_id) {

    }
}

