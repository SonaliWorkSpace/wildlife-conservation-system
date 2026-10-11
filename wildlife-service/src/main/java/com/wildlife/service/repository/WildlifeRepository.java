package com.wildlife.service.repository;

import com.wildlife.service.entity.Wildlife;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WildlifeRepository extends JpaRepository<Wildlife, Long> {
}