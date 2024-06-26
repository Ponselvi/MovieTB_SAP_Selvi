package me.selvi.cinematic.controller;

import me.selvi.cinematic.exception.HousefullException;
import me.selvi.cinematic.model.Screening;
import me.selvi.cinematic.model.Seat;
import me.selvi.cinematic.model.Theatre;
import me.selvi.cinematic.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScreeningController {
    private ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/screenings")
    public ResponseEntity<?> getAllScreenings(@RequestParam(required = false) Map<String, String> dates) {
        List<Screening> screenings = null;
        if(!dates.isEmpty()) {
            LocalDate startDate = LocalDate.parse(dates.get("startDate"));
            LocalDate endDate = LocalDate.parse(dates.get("endDate"));
            screenings = screeningService.getAllScreenings(startDate, endDate);
        } else {
            screenings = screeningService.getAllScreenings();
        }
        return ResponseEntity.status(HttpStatus.OK).body(screenings);
    }

    @GetMapping("/screenings/{screening_id}")
    public ResponseEntity<?> getScreenById(@PathVariable Long screening_id) {
        Screening screening = screeningService.getScreeningById(screening_id);
        return ResponseEntity.status(HttpStatus.OK).body(screening);
    }

    @GetMapping("/screenings/{screening_id}/seats")
    public ResponseEntity<?> getSeatsByScreeningId(@PathVariable Long screening_id) throws HousefullException {
        List<Seat> seats = screeningService.getSeatsByScreeningId(screening_id);
        if(seats.size() == 0) {
           throw new HousefullException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(seats);
    }

    @PostMapping(value = "/screenings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Screening addScreening(@RequestBody Screening screening) {
        return screeningService.pushScreening(screening);
    }

    @PutMapping(value = "/screenings/{movieTitle}/{chosenDate}/{localTime}")
    public Screening updateScreening(@RequestBody Screening screening, @PathVariable String movieTitle,
                                     @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate chosenDate,
                                     @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime localTime) {
        return screeningService.updateScreening(screening, movieTitle, chosenDate, localTime);
    }

    @DeleteMapping("/screenings/{movieTitle}/{chosenDate}/{localTime}")
    @Transactional
    public ResponseEntity<?> deleteScreeningById(@PathVariable String movieTitle,
                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate chosenDate,
                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime localTime) {
        screeningService.deleteScreening(movieTitle, chosenDate,
                localTime);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/screenings/{movieTitle}/{chosenDate}/{cityName}")
    public ResponseEntity<?> browseTheatresByMovieAndDate(@PathVariable String movieTitle,
                                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate chosenDate,
                                                          @PathVariable String cityName) {
        Map<Theatre, List<Screening>> theatreScreeningsMap = screeningService.browseTheatresByMovieAndDate(movieTitle, chosenDate, cityName);
        Map<String, List<LocalTime>> theaterLocalTime = new HashMap<>();
        
        // Print theatre names and their corresponding show timings
        for (Map.Entry<Theatre, List<Screening>> entry : theatreScreeningsMap.entrySet()) {
            Theatre theatre = entry.getKey();
            List<Screening> screenings = entry.getValue();

            System.out.println("Theatre: " + theatre.getName());
            for (Screening screening : screenings) {
                System.out.println("  Show Timing: " + screening.getStartTime());
                theaterLocalTime.computeIfAbsent(theatre.getName(), k -> new ArrayList<>()).add(screening.getStartTime());
            }
        }
       
        return ResponseEntity.status(HttpStatus.OK).body(theaterLocalTime);
    }
}
