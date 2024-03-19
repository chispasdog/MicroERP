package com.example.microERP.servicio;

import com.example.microERP.modelo.User;
import com.example.microERP.repositorio.Repositioriousuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUser {

    @Autowired
    private Repositioriousuario repositorioUser;

    public User guardarUsuario(User user) {
        user.setActive(true);
        user.setName("prueba");
        return repositorioUser.save(user);
    }

    public Optional<User> obtenerUserPorId(Long id) {
        return repositorioUser.findById(id);
    }

    public List<User> listarTodosLosUsers() {
        return repositorioUser.findAll();
    }

    public User buscarUserPorEmail(String email) {
        return repositorioUser.findByEmail(email);
    }

    public void eliminarUser(Long id) {
        repositorioUser.deleteById(id);
    }


}
