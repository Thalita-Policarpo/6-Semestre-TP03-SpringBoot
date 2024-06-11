package com.example.ensinotp03springboot.service;

import com.example.ensinotp03springboot.model.Curso;
import com.example.ensinotp03springboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "cursos", key = "#id")
    public Optional<Curso> getById(int id) {
        return cursoRepository.findById(id);
    }

    @Cacheable("cursos")
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    @CacheEvict(value = "cursos", allEntries = true)
    public void deleteById(int id) {
        cursoRepository.deleteById(id);
    }

    @CacheEvict(value = "cursos", allEntries = true)
    public Curso update(int id, Curso cursoUpdate) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoUpdate.getNome());
            return cursoRepository.save(curso);
        }).orElse(null);
    }

    @CacheEvict(value = "cursos", allEntries = true)
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }
}