package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.Viaggio2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Viaggio2Repository extends JpaRepository<Viaggio2, UUID> {
}