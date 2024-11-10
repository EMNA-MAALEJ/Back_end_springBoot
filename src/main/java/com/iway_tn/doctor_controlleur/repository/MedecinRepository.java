package com.iway_tn.doctor_controlleur.repository;

import com.iway_tn.doctor_controlleur.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByEmailAndPassword(String email, String password);
    @Query("select m from Medecin m where m.email = ?1")
    Optional<Medecin> findByEmail(String email);

}
