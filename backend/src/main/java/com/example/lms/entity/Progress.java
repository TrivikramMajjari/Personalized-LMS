package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Progress {

    @Id
    private Long id;
    private Double percentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
