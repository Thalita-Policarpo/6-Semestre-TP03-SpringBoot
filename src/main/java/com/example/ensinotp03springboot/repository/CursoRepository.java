package com.example.ensinotp03springboot.repository;

import com.example.ensinotp03springboot.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
