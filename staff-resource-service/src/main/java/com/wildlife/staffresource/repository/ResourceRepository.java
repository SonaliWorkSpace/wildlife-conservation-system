
package com.wildlife.staffresource.repository;

import com.wildlife.staffresource.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
