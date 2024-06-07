package com.example.ensinotp03springboot.controller;

import com.example.ensinotp03springboot.model.Aluno;
import com.example.ensinotp03springboot.service.AlunoService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @GetMapping("/sem-cache")
    public String getAllAlunosSemCache() {
        long start = System.currentTimeMillis();
        List<Aluno> alunos = alunoService.getAllSemcache();
        long end = System.currentTimeMillis();
        long duration = end - start;

        return "duracção com cache: " + duration+ "ms";
    }
}
