package com.egregore.bot.persistence.repository;

import com.egregore.bot.persistence.entity.UserReputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReputationRepository extends JpaRepository<UserReputation, Long> {
    // ¡Listo! No necesitas escribir SQL. Spring lo hace por ti.
}