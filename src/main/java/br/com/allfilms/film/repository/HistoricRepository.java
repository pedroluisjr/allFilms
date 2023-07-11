package br.com.allfilms.film.repository;

import br.com.allfilms.film.model.Historic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricRepository extends JpaRepository<Long, Historic> {
}
