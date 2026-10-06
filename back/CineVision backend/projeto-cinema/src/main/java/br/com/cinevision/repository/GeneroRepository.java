package br.com.cinevision.repository;

import br.com.cinevision.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}
