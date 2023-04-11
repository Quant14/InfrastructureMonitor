package org.elsys.infrastructuremonitorserver.repository;

import org.elsys.infrastructuremonitorserver.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitorRepository extends JpaRepository<Machine, Long> {
}
