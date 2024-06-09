package com.example.ensinotp03springboot.service;

import com.example.ensinotp03springboot.model.Curso;
import com.example.ensinotp03springboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RedisTemplate<String, Curso> redisTemplate;

    public Curso save(Curso curso) {
        Curso savedCurso = cursoRepository.save(curso);
        redisTemplate.opsForValue().set("curso:" + savedCurso.getId(), savedCurso);
        return savedCurso;
    }

    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getById(int id) {
        String cacheKey = "curso:" + id;
        Curso curso = redisTemplate.opsForValue().get(cacheKey);
        if (curso == null) {
            Optional<Curso> optionalCurso = cursoRepository.findById(id);
            optionalCurso.ifPresent(value -> redisTemplate.opsForValue().set(cacheKey, value));
            return optionalCurso;
        }
        return Optional.of(curso);
    }

    public void deleteById(int id) {
        cursoRepository.deleteById(id);
        redisTemplate.delete("curso:" + id);
    }

    public Curso update(int id, Curso cursoUpdate) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoUpdate.getNome());
            Curso updatedCurso = cursoRepository.save(curso);
            redisTemplate.opsForValue().set("curso:" + updatedCurso.getId(), updatedCurso);
            return updatedCurso;
        }).orElse(null);
    }
}
