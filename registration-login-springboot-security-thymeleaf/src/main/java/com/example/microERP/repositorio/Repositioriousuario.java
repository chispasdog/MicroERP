package com.example.microERP.repositorio;



import com.example.microERP.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositioriousuario extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);



}
