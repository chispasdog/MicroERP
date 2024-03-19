package com.example.microERP.security;


import com.example.microERP.modelo.Role;
import com.example.microERP.repositorio.Repositioriousuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.microERP.modelo.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService { //implementa la interfaz user details service 

    private Repositioriousuario repositioriousuario; //inyeccion de dependencias

    public CustomUserDetailsService(Repositioriousuario userRepository) { //constructor. inyecta el repositorio usuario cuando cree una isntanfia de esta clase
        this.repositioriousuario = userRepository;
    }

    // @Override
    // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //     User user = repositioriousuario.findByEmail(email);

    //     if (user != null) {
    //         return new org.springframework.security.core.userdetails.User(user.getEmail(),
    //                 user.getPassword(),
    //                 mapRolesToAuthorities(user.getRoles()));
    //     }else{
    //         throw new UsernameNotFoundException("Invalid username or password.");
    //     }
    // }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //sobreescribe el metodo loadUserByUsername. sirve para buscar por correo electronico
        User user = repositioriousuario.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + email);
        }

        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles()); //convierte los roles en granted authority que se utiliza para representar los permisos del usuario
        authorities.forEach(auth -> System.out.println("Granted Authority: " + auth.getAuthority()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), //imprime cada autoridad condedida ((rol)) del usuario
                user.getPassword(),
                authorities);
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) { 
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

