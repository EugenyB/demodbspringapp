package ua.mk.berkut.demodbspringapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mk.berkut.demodbspringapp.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}