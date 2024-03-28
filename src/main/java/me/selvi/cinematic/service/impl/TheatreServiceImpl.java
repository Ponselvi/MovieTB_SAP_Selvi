package me.selvi.cinematic.service.impl;

import me.selvi.cinematic.model.Theatre;
import me.selvi.cinematic.repository.TheatreRepository;
import me.selvi.cinematic.service.TheatreService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TheatreServiceImpl implements TheatreService {
    private TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }


    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long theatre_id) {
        return null;
    }

    @Override
    public Theatre pushTheatre(Theatre newTheatre) {
        return theatreRepository.save(newTheatre);
    }

    @Override
    public Theatre updateTheatre(Theatre updatedTheatre, Long theatre_id) {
        return null;
    }

    @Override
    public void deleteTheatreById(Long theatre_id) {
        theatreRepository.deleteById(theatre_id);
    }
}
