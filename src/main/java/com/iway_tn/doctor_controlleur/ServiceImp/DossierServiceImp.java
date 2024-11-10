package com.iway_tn.doctor_controlleur.ServiceImp;

import com.iway_tn.doctor_controlleur.model.Dossier;
import com.iway_tn.doctor_controlleur.repository.DossierRepository;
import com.iway_tn.doctor_controlleur.service.DossierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DossierServiceImp implements DossierService {

    @Autowired
    private DossierRepository repositoryDossier;

    @Override
    public Dossier getDossierById(long id_dossier) {
        return null;
    }

    @Override
    public Dossier updateDossier(Dossier dossier) {
        return null;
    }

    @Override
    public void deleteDossierById(long id_dossier) {

    }

    @Override
    public Dossier getDossierByPatientName(String patientName) {
        return repositoryDossier.findByPatientName(patientName);
    }


    @Override
    public Dossier addDossier(Dossier dossier) {
        return repositoryDossier.save(dossier);
    }

    @Override
    public List<Dossier> getAllCompletedDossiers() {
        return repositoryDossier.findByCompletedStatus();
    }
    @Override
    public List<Dossier> getAllPendingDossiers() {
        return repositoryDossier.findByPendingStatus();
    }

    @Override
    public List<Dossier> getAllDossiers() {
        return repositoryDossier.findAll();
    }
}
