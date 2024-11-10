package com.iway_tn.doctor_controlleur.controller;

import com.iway_tn.doctor_controlleur.model.Medecin;
import com.iway_tn.doctor_controlleur.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @PostMapping("/add")
    public ResponseEntity createMedecin(MultiValueMap<String, String> formData) {
        Medecin medecin = new Medecin();

        medecin.setName(formData.getFirst("name"));
        medecin.setSurname(formData.getFirst("surName"));
        medecin.setEmail(formData.getFirst("email"));
        medecin.setPassword(formData.getFirst("password"));
        medecin.setSpeciality(formData.getFirst("speciality"));

        return ResponseEntity.status(HttpStatus.CREATED).body(medecinService.addMedecin(medecin));
    }

        @GetMapping("/all")
    public String showAllMedecins(Model model) {
        model.addAttribute("medecins", medecinService.getAllMedecins());
        return "medecinsList";
    }

    @PostMapping("/delete")
    public String deleteMedecin(@RequestParam("id_medecin") long id_medecin, RedirectAttributes redirectAttributes) {
        Optional<Medecin> medecin = Optional.ofNullable(medecinService.getMedecinById(id_medecin));
        if (medecin.isPresent()) {
            medecinService.deleteMedecinById(id_medecin);
            redirectAttributes.addFlashAttribute("message", "Medecin deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Medecin not found!");
        }
        return "redirect:/medecins";
    }
}
