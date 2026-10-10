
package com.wildlife.conservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RescueRepository extends JpaRepository<Rescue, Long> {

    long countByStatus(String status);

    long countByPriority(String priority);
}
