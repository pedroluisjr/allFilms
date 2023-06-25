package br.com.allfilms.film.repository;

import br.com.allfilms.film.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.login = :login OR u.email = :email")
    Optional<User> findUser(String login, String email);

    @Query("SELECT u FROM User u WHERE u.activeUser = true")
    List<User> isActive();

    @Query("SELECT u FROM User u WHERE u.activeUser = false")
    List<User> isInactive();
}
