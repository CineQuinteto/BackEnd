package br.com.cinevision.service;

import br.com.cinevision.exception.DadosInvalidosException;
import br.com.cinevision.exception.RecursoNaoEncontradoException;
import br.com.cinevision.model.Filme;
import br.com.cinevision.model.Genero;
import br.com.cinevision.repository.FilmeRepository;
import br.com.cinevision.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final GeneroRepository generoRepository;

    public FilmeService(FilmeRepository filmeRepository, GeneroRepository generoRepository) {
        this.filmeRepository = filmeRepository;
        this.generoRepository = generoRepository;
    }

    public List<Filme> listar() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(Integer id) {
        Optional<Filme> resultado = filmeRepository.findById(id);

        if (resultado.isEmpty()) {
            throw new RecursoNaoEncontradoException("Filme com id " + id + " não encontrado.");
        }
        return resultado.get();
    }

    public Filme cadastrar(Filme novoFilme) {
        validar(novoFilme);

        novoFilme.setId(null);
        novoFilme.setGeneros(buscarGeneros(novoFilme.getGeneros()));

        return filmeRepository.save(novoFilme);
    }

    public Filme atualizar(Integer id, Filme dadosNovos) {
        Filme filmeExistente = buscarPorId(id);
        validar(dadosNovos);

        filmeExistente.setTitulo(dadosNovos.getTitulo());
        filmeExistente.setTituloOriginal(dadosNovos.getTituloOriginal());
        filmeExistente.setSinopse(dadosNovos.getSinopse());
        filmeExistente.setDuracaoMin(dadosNovos.getDuracaoMin());
        filmeExistente.setClassificacao(dadosNovos.getClassificacao());
        filmeExistente.setDataEstreia(dadosNovos.getDataEstreia());
        filmeExistente.setUrlPoster(dadosNovos.getUrlPoster());
        filmeExistente.setUrlTrailer(dadosNovos.getUrlTrailer());
        if (dadosNovos.getAtivo() != null) {
            filmeExistente.setAtivo(dadosNovos.getAtivo());
        }
        filmeExistente.setGeneros(buscarGeneros(dadosNovos.getGeneros()));

        return filmeRepository.save(filmeExistente);
    }

    public void excluir(Integer id) {
        Filme filme = buscarPorId(id);
        filmeRepository.delete(filme);
    }

    private void validar(Filme filme) {
        if (filme.getTitulo() == null || filme.getTitulo().isBlank()) {
            throw new DadosInvalidosException("O título do filme é obrigatório.");
        }
        if (filme.getTitulo().length() > 150) {
            throw new DadosInvalidosException("O título deve ter no máximo 150 caracteres.");
        }
        if (filme.getDuracaoMin() == null || filme.getDuracaoMin() <= 0) {
            throw new DadosInvalidosException("A duração em minutos é obrigatória e deve ser maior que zero.");
        }
        if (filme.getClassificacao() == null || !classificacaoValida(filme.getClassificacao())) {
            throw new DadosInvalidosException("Classificação inválida. Use: L, 10, 12, 14, 16 ou 18.");
        }
    }

    private boolean classificacaoValida(String classificacao) {
        return classificacao.equals("L")
                || classificacao.equals("10")
                || classificacao.equals("12")
                || classificacao.equals("14")
                || classificacao.equals("16")
                || classificacao.equals("18");
    }

    private List<Genero> buscarGeneros(List<Genero> generosRecebidos) {
        List<Genero> generosEncontrados = new ArrayList<>();

        if (generosRecebidos == null) {
            return generosEncontrados;
        }

        for (Genero genero : generosRecebidos) {
            if (genero.getId() == null) {
                throw new DadosInvalidosException("Cada gênero precisa ter um id. Exemplo: \"generos\": [{\"id\": 1}]");
            }

            Optional<Genero> resultado = generoRepository.findById(genero.getId());
            if (resultado.isEmpty()) {
                throw new RecursoNaoEncontradoException("Gênero com id " + genero.getId() + " não encontrado.");
            }
            generosEncontrados.add(resultado.get());
        }
        return generosEncontrados;
    }
}
