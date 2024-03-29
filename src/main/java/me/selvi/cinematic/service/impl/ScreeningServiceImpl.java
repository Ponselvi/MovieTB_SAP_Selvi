package me.selvi.cinematic.service.impl;

import me.selvi.cinematic.exception.ScreeningNotFoundException;
import me.selvi.cinematic.model.Movie;
import me.selvi.cinematic.model.Screening;
import me.selvi.cinematic.model.Seat;
import me.selvi.cinematic.model.Theatre;
import me.selvi.cinematic.repository.ScreeningRepository;
import me.selvi.cinematic.service.MovieService;
import me.selvi.cinematic.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class ScreeningServiceImpl implements ScreeningService {
    private ScreeningRepository screeningRepository;

    private MovieService movieService;
    @Autowired
    public ScreeningServiceImpl(ScreeningRepository screeningRepository, MovieService movieService) {
        this.screeningRepository = screeningRepository;
        this.movieService = movieService;
    }

    @Override
    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public List<Screening> getAllScreenings(LocalDate startDate, LocalDate endDate) {
        return screeningRepository.findAll().stream().filter(screening -> screening.getDate().isAfter(startDate) && screening.getDate().isBefore(endDate)).collect(Collectors.toList());
    }

    @Override
    public Screening getScreeningById(Long screening_id) {
        return screeningRepository.findById(screening_id).orElseThrow(() -> new ScreeningNotFoundException("No Screening Found"));
    }

    @Override
    public Screening pushScreening(Screening newScreening) {
        return screeningRepository.save(newScreening);
    }

    @Override
    public Screening updateScreening(Screening updatedScreening, String movieTitle, LocalDate chosenDate,
                                     LocalTime localTime) {
        Movie movie = movieService.getMovieByTitle(movieTitle);
        Screening existingScreening = screeningRepository.findByMovieIdAndDateAndStartTime(movie.getId(), chosenDate, localTime);

        if(existingScreening == null) {
            throw new ScreeningNotFoundException("The screening is not found to update");
        }

        existingScreening.setAuditorium(updatedScreening.getAuditorium());
        existingScreening.setEndTime(updatedScreening.getEndTime());
        existingScreening.setPrice(updatedScreening.getPrice());
        existingScreening.setMovie(updatedScreening.getMovie());
        existingScreening.setStartTime(updatedScreening.getStartTime());
        existingScreening.setDate(updatedScreening.getDate());

        return screeningRepository.save(existingScreening);
    }

    @Override
    public void deleteScreening(String movieTitle, LocalDate chosenDate,
                                    LocalTime localTime) {
        Movie movie = movieService.getMovieByTitle(movieTitle);
        screeningRepository.deleteByMovieIdAndDateAndStartTime(movie.getId(), chosenDate, localTime);
    }

    @Override
    public List<Seat> getSeatsByScreeningId(Long screening_id) {
        Screening screening = getScreeningById(screening_id);
        if (screening.getIsFull()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(screening.getAuditorium().getSeats());
    }

    @Override
    public Map<Theatre, List<Screening>> browseTheatresByMovieAndDate(String movieTitle, LocalDate chosenDate, String cityName) {

        Movie movie = movieService.getMovieByTitle(movieTitle);
        List<Screening> screenings = screeningRepository.findByMovieIdAndDate(movie.getId(), chosenDate);

        // Group screenings by theatre
        Map<Theatre, List<Screening>> theatreScreeningsMap = new HashMap<>();
        for (Screening screening : screenings) {
            Theatre theatre = screening.getAuditorium().getTheatre();
            if(!theatre.getCityName().equalsIgnoreCase(cityName))
                continue;
            theatreScreeningsMap.computeIfAbsent(theatre, k -> new ArrayList<>()).add(screening);
        }

        return theatreScreeningsMap;
    }

}
