
package com.wildlife.conservation.repository;

import com.wildlife.conservation.entity.ConservationProgramme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConservationProgrammeRepository
        extends JpaRepository<ConservationProgramme, Long> {
}
