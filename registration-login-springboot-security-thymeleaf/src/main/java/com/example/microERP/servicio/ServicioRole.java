package com.example.microERP.servicio;

import com.example.microERP.modelo.Role;
import com.example.microERP.repositorio.RepositorioRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioRole {

    @Autowired
    private RepositorioRole repositorioRole;

    public Role guardarRole(Role role) {
        return repositorioRole.save(role);
    }

    public Optional<Role> obtenerRolePorId(Long id) {
        return repositorioRole.findById(id);
    }

    public List<Role> listarTodosLosRoles() {
        return repositorioRole.findAll();
    }

    public Optional<Role> buscarRolePorNombre(String nombre) {
        return repositorioRole.findByName(nombre);
    }



    public List<Role> buscarRolesPorEmailUsuario(String email) {
        return repositorioRole.findByUsersEmail(email);
    }

    public List<Role> buscarRolesPorEstadoActivoUsuario(boolean activo) {
        return repositorioRole.findByUsersActive(activo);
    }

    public void eliminarRole(Long id) {
        repositorioRole.deleteById(id);
    }


}
