package org.elsys.infrastructuremonitorserver.repository;

import org.elsys.infrastructuremonitorserver.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Machine, Long> {
}
