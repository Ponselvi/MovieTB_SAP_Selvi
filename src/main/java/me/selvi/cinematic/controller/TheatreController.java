package me.selvi.cinematic.controller;

import me.selvi.cinematic.model.Movie;
import me.selvi.cinematic.model.Theatre;
import me.selvi.cinematic.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheatreController {
    private TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @PostMapping(value = "/theatres", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Theatre addTheater(@RequestBody Theatre theatre) {
        return theatreService.pushTheatre(theatre);
    }

    @PutMapping(value = "/theatres")
    public Theatre updateTheater(@RequestBody Theatre theatre, long theaterId) {
        return theatreService.updateTheatre(theatre, theaterId);
    }
}
