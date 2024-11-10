package com.iway_tn.doctor_controlleur.controller;

import com.iway_tn.doctor_controlleur.model.Dossier;
import com.iway_tn.doctor_controlleur.model.Medecin;
import com.iway_tn.doctor_controlleur.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@CrossOrigin(origins = "http://localhost:3000")
public class MedecinRestController {

    @Autowired
    private MedecinService medecinService;
    @PostMapping
    public ResponseEntity<Medecin> createMedecin(@RequestParam("name") String name,
                                                 @RequestParam("surName") String surName,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("speciality") String speciality) {
        Medecin medecin = new Medecin();
        medecin.setName(name);
        medecin.setSurname(surName);
        medecin.setEmail(email);
        medecin.setPassword(password);
        medecin.setSpeciality(speciality);

        medecinService.addMedecin(medecin);
        return ResponseEntity.status(HttpStatus.CREATED).body(medecin);
    }

    @GetMapping
    public ResponseEntity<Iterable<Medecin>> getAllMedecins() {
        return ResponseEntity.ok(medecinService.getAllMedecins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Optional<Medecin> medecin = Optional.ofNullable(medecinService.getMedecinById(id));
        return medecin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        Optional<Medecin> medecin = Optional.ofNullable(medecinService.getMedecinById(id));
        if (medecin.isPresent()) {
            medecinService.deleteMedecinById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Medecin> authenticateMedecin(@RequestBody Medecin medecin) {
        Medecin authenticatedMedecin = medecinService.authenticateMedecin(medecin.getEmail(), medecin.getPassword());
        if (authenticatedMedecin != null) {
            return ResponseEntity.ok(authenticatedMedecin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/profile/{id}")
    @ResponseBody
    public ResponseEntity<Medecin> getProfile(@PathVariable Long id) {
        Optional<Medecin> medecin = Optional.ofNullable(medecinService.getMedecinById(id));
        if (medecin.isPresent()) {
            return ResponseEntity.ok(medecin.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Medecin> getMedecinByEmail(@PathVariable String email) {
        Medecin medecin = medecinService.getMedecinByEmail(email);
        if (medecin != null) {
            return ResponseEntity.ok(medecin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Medecin> updateProfile(@RequestBody Medecin updatedMedecin) {
        Medecin existingMedecin = medecinService.getMedecinByEmail(updatedMedecin.getEmail());

        if (existingMedecin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        existingMedecin.setName(updatedMedecin.getName());
        existingMedecin.setSurname(updatedMedecin.getSurname());
        existingMedecin.setPassword(updatedMedecin.getPassword());
        existingMedecin.setSpeciality(updatedMedecin.getSpeciality());

        Medecin savedMedecin = medecinService.updateMedecin(existingMedecin);
        return ResponseEntity.ok(savedMedecin);
    }


}

