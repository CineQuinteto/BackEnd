package br.com.cinevision.controller;

import br.com.cinevision.exception.DadosInvalidosException;
import br.com.cinevision.model.Genero;
import br.com.cinevision.repository.GeneroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {
    private final GeneroRepository generoRepository;

    public GeneroController(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @GetMapping
    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Genero> cadastrar(@RequestBody Genero genero) {
        if (genero.getNome() == null || genero.getNome().isBlank()) {
            throw new DadosInvalidosException("O nome do gênero é obrigatório.");
        }
        genero.setId(null);
        Genero generoSalvo = generoRepository.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoSalvo);
    }
}
