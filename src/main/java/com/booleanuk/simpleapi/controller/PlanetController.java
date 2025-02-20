package com.booleanuk.simpleapi.controller;

import com.booleanuk.simpleapi.model.Planet;
import com.booleanuk.simpleapi.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("planets")
public class PlanetController {
    @Autowired
    private PlanetRepository planetRepository;

    @PostMapping
    public ResponseEntity<Planet> createPlanet (@RequestBody Planet planet) {
        return new ResponseEntity<Planet>(this.planetRepository.save(planet), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Planet> getAllPlanet() {
        return planetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanet(@PathVariable int id) {
        return new ResponseEntity<>(this.planetRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No planet with that id were found")), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Planet> updatePlanet(@PathVariable int id, @RequestBody Planet planet) {
        Planet updatePlanet = this.planetRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find planet with given id"));

        updatePlanet.setName(planet.getName());
        updatePlanet.setDestroydByTheDeathStar(planet.isDestroydByTheDeathStar());
        updatePlanet.setPeople(planet.getPeople());

        return new ResponseEntity<Planet>(this.planetRepository.save(updatePlanet), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Planet> deletePlanet(@PathVariable int id) {
        Planet deletePlanet = this.planetRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find planet with given id"));
        this.planetRepository.delete(deletePlanet);
        return ResponseEntity.ok(deletePlanet);
    }
}
