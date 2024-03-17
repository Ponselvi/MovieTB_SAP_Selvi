package me.selvi.cinematic.repository;

import me.selvi.cinematic.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
