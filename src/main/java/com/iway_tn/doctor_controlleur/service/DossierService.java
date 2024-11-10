package com.iway_tn.doctor_controlleur.service;

import com.iway_tn.doctor_controlleur.model.Dossier;

import java.util.List;

public interface DossierService {
    Dossier getDossierById (long id_dossier);

    Dossier updateDossier(Dossier dossier);

    void deleteDossierById(long id_dossier);
    Dossier addDossier(Dossier dossier);

    List<Dossier> getAllCompletedDossiers();
    List<Dossier> getAllPendingDossiers();
    List<Dossier> getAllDossiers();
    Dossier getDossierByPatientName(String patientName);

}
