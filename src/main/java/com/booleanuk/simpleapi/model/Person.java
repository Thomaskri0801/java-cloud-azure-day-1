package com.booleanuk.simpleapi.model;

import com.booleanuk.simpleapi.repository.PersonRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column
    private String description;

    @Column
    private boolean alive;

    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

    public Person(int id) {
        this.id = id;
    }
}
