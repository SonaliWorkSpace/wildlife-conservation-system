
package com.wildlife.habitat.repository;

import com.wildlife.habitat.entity.ProtectedArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtectedAreaRepository
        extends JpaRepository<ProtectedArea, Long> {
}
