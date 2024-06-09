package com.example.ensinotp03springboot.service;

import com.example.ensinotp03springboot.model.Aluno;
import com.example.ensinotp03springboot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Cacheable(value = "alunos", key = "#id")
    public Optional<Aluno> getById(int id) {
        return alunoRepository.findById(id);
    }

    @Cacheable("alunos")
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @CacheEvict(value = "alunos", key = "#id")
    public void deleteById(int id) {
        alunoRepository.deleteById(id);
    }

    @CacheEvict(value = "alunos", key = "#id")
    public Aluno update(int id, Aluno alunoUpdate) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoUpdate.getNome());
            aluno.setEmail(alunoUpdate.getEmail());
            return alunoRepository.save(aluno);
        }).orElse(null);
    }

    @CacheEvict(value = "alunos", allEntries = true)
    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
}
