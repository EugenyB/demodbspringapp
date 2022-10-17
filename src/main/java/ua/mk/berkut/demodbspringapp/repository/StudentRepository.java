package ua.mk.berkut.demodbspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mk.berkut.demodbspringapp.entities.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByName(String name);

}