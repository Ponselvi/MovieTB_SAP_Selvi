package me.selvi.cinematic.repository;

import me.selvi.cinematic.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {}
