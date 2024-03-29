package me.selvi.cinematic.service;

import me.selvi.cinematic.model.Screening;
import me.selvi.cinematic.model.Seat;
import me.selvi.cinematic.model.Theatre;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ScreeningService {
    List<Screening> getAllScreenings();

    Screening getScreeningById(Long screening_id);

    Screening pushScreening(Screening newScreening);

    Screening updateScreening(Screening updatedScreening, String movieTitle, LocalDate chosenDate, LocalTime LocalTime);

    List<Seat> getSeatsByScreeningId(Long screening_id);

    List<Screening> getAllScreenings(LocalDate startDate, LocalDate endDate);

    void deleteScreening(String movieTitle, LocalDate chosenDate,
                             LocalTime localTime);

    Map<Theatre, List<Screening>> browseTheatresByMovieAndDate(String movieTitle, LocalDate chosenDate, String cityName);
}
