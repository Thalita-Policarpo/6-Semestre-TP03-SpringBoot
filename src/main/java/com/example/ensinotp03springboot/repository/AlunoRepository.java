package com.example.ensinotp03springboot.repository;

import com.example.ensinotp03springboot.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
