package com.dcurioustech.educhat.repository;

import com.dcurioustech.educhat.model.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, String> {
}