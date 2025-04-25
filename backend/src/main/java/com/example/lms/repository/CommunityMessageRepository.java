package com.example.lms.repository;

import com.example.lms.entity.CommunityMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityMessageRepository extends JpaRepository<CommunityMessage, Long> {
}
