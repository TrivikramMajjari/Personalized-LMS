package com.example.lms.repository;

import com.example.lms.entity.PersonalizedQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalizedQuestionnaireRepository extends JpaRepository<PersonalizedQuestionnaire, Long> {
}
