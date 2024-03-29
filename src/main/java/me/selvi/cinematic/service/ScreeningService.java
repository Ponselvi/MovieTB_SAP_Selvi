package me.selvi.cinematic.service;

import me.selvi.cinematic.model.Screening;
import me.selvi.cinematic.model.Seat;
import me.selvi.cinematic.model.Theatre;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScreeningService {
    List<Screening> getAllScreenings();

    Screening getScreeningById(Long screening_id);

    Screening pushScreening(Screening newScreening);

    Screening updateScreening(Screening updatedScreening, Long screening_id);

    List<Seat> getSeatsByScreeningId(Long screening_id);

    List<Screening> getAllScreenings(LocalDate startDate, LocalDate endDate);

    Map<Theatre, List<Screening>> browseTheatresByMovieAndDate(String movieTitle, LocalDate chosenDate, String cityName);
}
