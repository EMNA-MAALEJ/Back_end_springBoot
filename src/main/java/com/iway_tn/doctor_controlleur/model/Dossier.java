package com.iway_tn.doctor_controlleur.model;

import com.iway_tn.doctor_controlleur.model.Enum.Status;
import jakarta.persistence.*;

@Entity
@Table(name="dossier")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    @Enumerated(EnumType.STRING)
    private Status status;

    private String createdDate;
    private String completedDate;

    public Dossier(Long id, String patientName, Status status, String createdDate, String completedDate) {
        super();
        this.id = id;
        this.patientName = patientName  ;
        this.status = status;
        this.createdDate = createdDate;
        this.completedDate = completedDate;
    }

    public Dossier() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }
}

