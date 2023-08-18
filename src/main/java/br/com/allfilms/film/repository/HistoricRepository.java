package br.com.allfilms.film.repository;

import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    List<Historic> findByUser (User user);

}
