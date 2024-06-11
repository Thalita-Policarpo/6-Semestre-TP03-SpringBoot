package com.example.ensinotp03springboot.controller;

import com.example.ensinotp03springboot.model.Aluno;
import com.example.ensinotp03springboot.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
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
    public List<Aluno> getAll() {
        return alunoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> getAlunoById(@PathVariable int id) {
        return alunoService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        alunoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Aluno updateById(@PathVariable int id, @RequestBody Aluno aluno) {
        return alunoService.update(id, aluno);
    }
}