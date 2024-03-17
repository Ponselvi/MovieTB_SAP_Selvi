package me.selvi.cinematic.service;

import me.selvi.cinematic.model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getAllSeats();

    Seat getSeatById(Long seat_id);

    Seat pushSeat(Seat newSeat);

    Seat updateSeat(Seat updatedSeat, Long seat_id);

    void deleteSeatById(Long seat_id);
}
