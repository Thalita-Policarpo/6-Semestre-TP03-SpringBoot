package com.example.ensinotp03springboot.controller;

import com.example.ensinotp03springboot.model.Curso;
import com.example.ensinotp03springboot.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @GetMapping
    public List<Curso> getAll() {
        return cursoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> getCursoById(@PathVariable int id) {
        return cursoService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        cursoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Curso updateById(@PathVariable int id, @RequestBody Curso curso) {
        return cursoService.update(id, curso);
    }
}