package ru.kurlyanchik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kurlyanchik.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long > {

    Optional<User> findByUsername(String username);

}
