package com.iway_tn.doctor_controlleur.service;

import com.iway_tn.doctor_controlleur.model.Dossier;
import com.iway_tn.doctor_controlleur.model.Medecin;

import java.util.List;

public interface MedecinService {
    Medecin addMedecin(Medecin medecin);
    Medecin getMedecinById(Long id);
    List<Medecin> getAllMedecins();
    void deleteMedecinById(Long id);
    Medecin authenticateMedecin(String email, String password);
    Medecin updateMedecin(Medecin medecin);
    Medecin getMedecinByEmail(String email);
    boolean verifierMotDePasse(String motDePasse, String motDePasseHache);

    Medecin findById(Long medecinId);
}
