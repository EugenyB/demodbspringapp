package ua.mk.berkut.demodbspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mk.berkut.demodbspringapp.entities.Gruppa;

public interface GruppaRepository extends JpaRepository<Gruppa, Integer> {
}