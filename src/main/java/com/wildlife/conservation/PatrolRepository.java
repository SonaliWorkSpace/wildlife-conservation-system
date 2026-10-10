
package com.wildlife.conservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrolRepository extends JpaRepository<Patrol, Long> {
	  long countByStatus(String status);
}
