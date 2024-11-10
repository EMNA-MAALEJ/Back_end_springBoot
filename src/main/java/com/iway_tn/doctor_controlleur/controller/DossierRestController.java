package com.iway_tn.doctor_controlleur.controller;

import com.iway_tn.doctor_controlleur.model.Dossier;
import com.iway_tn.doctor_controlleur.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dossiers")
@CrossOrigin(origins = "http://localhost:3000")
public class DossierRestController {

    @Autowired
    private DossierService dossierService;

    @GetMapping("/{id}")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        Optional<Dossier> Dossier = Optional.ofNullable(dossierService.getDossierById(id));
        return Dossier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

   @GetMapping("/pendingDossiers")
    public ResponseEntity<List<Dossier>> listPendingDossiers() {
        List<Dossier> dossiers = dossierService.getAllPendingDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Dossier>> listDossiers() {
        List<Dossier> dossiers = dossierService.getAllCompletedDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @PostMapping("/createDossier")
    public ResponseEntity<String> createDossier(@RequestBody Dossier dossier) {
        try {
            dossierService.addDossier(dossier);
            return ResponseEntity.status(HttpStatus.CREATED).body("Dossier ajouté avec succès!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'ajout du dossier.");
        }
    }

    @PutMapping("/{id_dossier}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable long id_dossier, @RequestBody Dossier dossierDetails) {
        Optional<Dossier> dossier = Optional.ofNullable(dossierService.getDossierById(id_dossier));
        if (dossier.isPresent()) {
            Dossier updatedDossier = dossier.get();
            updatedDossier.setStatus(dossierDetails.getStatus());
            updatedDossier.setCompletedDate(dossierDetails.getCompletedDate());
            return ResponseEntity.ok(dossierService.addDossier(updatedDossier));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_dossier}")
    public ResponseEntity<Void> deleteDossier(@PathVariable long id_dossier) {
        Optional<Dossier> dossier = Optional.ofNullable(dossierService.getDossierById(id_dossier));
        if (dossier.isPresent()) {
            dossierService.deleteDossierById(id_dossier);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
