package faby.faby_BE.repository;

import faby.faby_BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Puoi aggiungere metodi personalizzati, ad esempio:
    User findByEmail(String email);
}
