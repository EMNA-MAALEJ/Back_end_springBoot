package com.iway_tn.doctor_controlleur.ServiceImp;

import com.iway_tn.doctor_controlleur.model.Dossier;
import com.iway_tn.doctor_controlleur.model.Medecin;
import com.iway_tn.doctor_controlleur.repository.MedecinRepository;
import com.iway_tn.doctor_controlleur.repository.DossierRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.iway_tn.doctor_controlleur.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedecinServiceImp implements MedecinService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private DossierRepository dossierRepository;
    @Override
    public Medecin addMedecin(Medecin medecin) {
        String hashedPassword = passwordEncoder.encode(medecin.getPassword());
        medecin.setPassword(hashedPassword);
        return medecinRepository.save(medecin);
    }
    @Override
    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public void deleteMedecinById(Long id) {
        medecinRepository.deleteById(id);
    }

    @Override
    public Medecin authenticateMedecin(String email, String password) {
        Medecin medecin = medecinRepository.findByEmail(email).orElse(null);
        if (medecin != null && passwordEncoder.matches(password, medecin.getPassword())) {
            return medecin;
        } else {
            return null;
        }
    }
    @Override
    public Medecin updateMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }
    @Override
    public Medecin getMedecinByEmail(String email) {
        return medecinRepository.findByEmail(email).orElse(null);
    }
    public boolean verifierMotDePasse(String motDePasse, String motDePasseHache) {
        return passwordEncoder.matches(motDePasse, motDePasseHache);
    }

    @Override
    public Medecin findById(Long medecinId) {
        return null;
    }

}



