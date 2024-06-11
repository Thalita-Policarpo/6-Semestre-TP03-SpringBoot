package com.example.ensinotp03springboot.service;

import com.example.ensinotp03springboot.model.Aluno;
import com.example.ensinotp03springboot.model.Curso;
import com.example.ensinotp03springboot.repository.AlunoRepository;
import com.example.ensinotp03springboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "alunos", key = "#id")
    public Optional<Aluno> getById(int id) {
        return alunoRepository.findById(id);
    }

    @Cacheable("alunos")
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @CacheEvict(value = "alunos", allEntries = true)
    public void deleteById(int id) {
        alunoRepository.deleteById(id);
    }

    @CacheEvict(value = "alunos", allEntries = true)
    public Aluno update(int id, Aluno alunoUpdate) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoUpdate.getNome());
            aluno.setEmail(alunoUpdate.getEmail());
            aluno.setCursos(updateCursos(alunoUpdate.getCursos()));
            return alunoRepository.save(aluno);
        }).orElse(null);
    }

    @CacheEvict(value = "alunos", allEntries = true)
    public Aluno save(Aluno aluno) {
        aluno.setCursos(updateCursos(aluno.getCursos()));
        return alunoRepository.save(aluno);
    }

    private Set<Curso> updateCursos(Set<Curso> cursos) {
        Set<Curso> updatedCursos = new HashSet<>();
        for (Curso curso : cursos) {
            Curso foundCurso = cursoRepository.findById(curso.getId()).orElse(null);
            if (foundCurso != null) {
                updatedCursos.add(foundCurso);
            } else {
                throw new IllegalArgumentException("Curso ID " + curso.getId() + " não encontrado.");
            }
        }
        return updatedCursos;
    }
}