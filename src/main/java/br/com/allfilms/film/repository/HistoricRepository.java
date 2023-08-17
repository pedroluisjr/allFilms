package br.com.allfilms.film.repository;

import br.com.allfilms.film.model.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {
}
