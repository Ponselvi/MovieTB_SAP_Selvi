package me.selvi.cinematic.repository;

import me.selvi.cinematic.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    List<Screening> findByMovieIdAndDate(Long movieId, LocalDate date);

}
