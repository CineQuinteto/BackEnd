package br.com.cinevision.repository;

import br.com.cinevision.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}
