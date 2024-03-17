package me.selvi.cinematic.controller;

import me.selvi.cinematic.model.Auditorium;
import me.selvi.cinematic.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditoriumController {
    private AuditoriumService auditoriumService;


    @Autowired
    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @GetMapping("/auditoriums")
    public List<Auditorium> getAllTheatres() {
        return auditoriumService.getAllAuditoriums();
    }
}
