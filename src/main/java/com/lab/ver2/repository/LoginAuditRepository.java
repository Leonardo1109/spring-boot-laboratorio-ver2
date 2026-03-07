package com.lab.ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lab.ver2.model.LoginAudit;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {
}
