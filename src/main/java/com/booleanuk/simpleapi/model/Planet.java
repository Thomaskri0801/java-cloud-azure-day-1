package com.booleanuk.simpleapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private boolean destroydByTheDeathStar;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Person> people;

    public Planet(int id) {
        this.id = id;
    }
}
