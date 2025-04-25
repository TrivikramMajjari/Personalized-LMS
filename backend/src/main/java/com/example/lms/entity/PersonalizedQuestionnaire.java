package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PersonalizedQuestionnaire {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // Define fields and methods for personalized questionnaire
}
