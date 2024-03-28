package me.selvi.cinematic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theatre_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    //@JsonBackReference
    @JsonIgnoreProperties("theatre")
    private Set<Auditorium> auditoriums;
}
