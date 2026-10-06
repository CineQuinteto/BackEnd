package br.com.cinevision.controller;

import br.com.cinevision.model.Filme;
import br.com.cinevision.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {
    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<Filme> listar() {
        return filmeService.listar();
    }

    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable Integer id) {
        return filmeService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme) {
        Filme filmeSalvo = filmeService.cadastrar(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeSalvo);
    }

    @PutMapping("/{id}")
    public Filme atualizar(@PathVariable Integer id, @RequestBody Filme filme) {
        return filmeService.atualizar(id, filme);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        filmeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
