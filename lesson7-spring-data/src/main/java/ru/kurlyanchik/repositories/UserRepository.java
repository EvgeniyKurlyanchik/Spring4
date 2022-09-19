package ru.kurlyanchik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kurlyanchik.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long >, QuerydslPredicateExecutor<User> {
    @Query(""" 
            select u 
            from User u 
            join fetch u.roles
            where u.username = :username
            """)
    Optional<User> findByUsername(String username);

}
