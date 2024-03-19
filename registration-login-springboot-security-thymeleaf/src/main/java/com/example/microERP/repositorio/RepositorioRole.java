package com.example.microERP.repositorio;



import com.example.microERP.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioRole extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    List<Role> findByUsersEmail(String email);
    List<Role> findByUsersActive(boolean active);
}
