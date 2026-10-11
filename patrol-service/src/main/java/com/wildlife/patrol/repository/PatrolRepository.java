package com.wildlife.patrol.repository;

import com.wildlife.patrol.entity.Patrol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrolRepository extends JpaRepository<Patrol, Long> {
}