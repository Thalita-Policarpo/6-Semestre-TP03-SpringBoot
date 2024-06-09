package com.example.ensinotp03springboot.service;

import com.example.ensinotp03springboot.model.Aluno;
import com.example.ensinotp03springboot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private RedisTemplate<String, Aluno> redisTemplate;

    public Aluno save(Aluno aluno) {
        Aluno savedAluno = alunoRepository.save(aluno);
        redisTemplate.opsForValue().set("aluno:" + savedAluno.getId(), savedAluno);
        return savedAluno;
    }

    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> getById(int id) {
        String cacheKey = "aluno:" + id;
        Aluno aluno = redisTemplate.opsForValue().get(cacheKey);
        if (aluno == null) {
            Optional<Aluno> optionalAluno = alunoRepository.findById(id);
            optionalAluno.ifPresent(value -> redisTemplate.opsForValue().set(cacheKey, value));
            return optionalAluno;
        }
        return Optional.of(aluno);
    }

    public void deleteById(int id) {
        alunoRepository.deleteById(id);
        redisTemplate.delete("aluno:" + id);
    }

    public Aluno update(int id, Aluno alunoUpdate) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoUpdate.getNome());
            aluno.setEmail(alunoUpdate.getEmail());
            Aluno updatedAluno = alunoRepository.save(aluno);
            redisTemplate.opsForValue().set("aluno:" + updatedAluno.getId(), updatedAluno);
            return updatedAluno;
        }).orElse(null);
    }
}
