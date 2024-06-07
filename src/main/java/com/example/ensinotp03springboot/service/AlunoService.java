package com.example.ensinotp03springboot.service;

import com.example.ensinotp03springboot.model.Aluno;
import com.example.ensinotp03springboot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

   @Cacheable("alunos")
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> getAllSemcache(){
        return alunoRepository.findAll();
    }



}
