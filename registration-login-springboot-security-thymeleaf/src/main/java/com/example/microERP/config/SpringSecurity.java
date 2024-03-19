package com.example.microERP.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity

public class SpringSecurity  { //extends WebSecurityConfigurerAdapter pero no esta luego revisatr

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){ //encriptamos la contraseña
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/clientes", "/inventario", "/register/**").permitAll() // Asegúrate de incluir "/register/**" aquí
                        .requestMatchers("/register/**", "/index", "/factura/**", "/users", "/detallesCliente", "cliente/**").authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/users", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());

        return http.build();
    }










/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //plan de seguridad. filtra como acceder a cada url
        http.csrf().disable() //desactiva la protección csrf.
                .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
                 http.authorizeHttpRequests((authorize) -> //comienza la configuracion de las reglas para las solicitudes http
                         authorize.requestMatchers("/register/**").authenticated() // especifica que para acceder a las rutas dadas el usuario tiene que estar autenticado.
                                 .requestMatchers("/index").authenticated()
                                 .requestMatchers("/factura/**").authenticated()
                                 .requestMatchers("/users").authenticated()
                                 .requestMatchers("/login").permitAll()
                                 .requestMatchers("/clientes").permitAll()
                                 .requestMatchers("/detallesCliente").authenticated()
                                 .requestMatchers("/inventario").permitAll()

                 ).formLogin( // configura el formilario de login
                         form -> form
                                 .loginPage("/login") // indica la ruta al formulario de inicio de sesion
                                 .loginProcessingUrl("/login") //especifica la url para procesar los datos del formulario
                                 .defaultSuccessUrl("/users") // define la url a la que se redirige al usuario despues de un login correcto
                                 .permitAll() // indica que todos los usuarios pueden acceder al formulario de inicio de sesion
                 ).logout( //configuramos el cierre de sesion
                         logout -> logout
                                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //especifica la url para activar el cierre de sesion
                                 .permitAll() //indica que todos los usuarios pueden cerrar la sesion
                 );
        return http.build(); //finaliza la configuracion y construye el security filter chain con las configuraciones definida arriba
    }*/


    @Autowired //anotacion que dice a Spring que inyecte automaticamente el el objeto cuando se cree la instancia de esta clase.
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // define un metodo que spring security usa para autenticar usuarios
        auth
                .userDetailsService(userDetailsService) //confighura el servicio que spring security usa para autenticar usuarios
                .passwordEncoder(passwordEncoder()); //configura el encriptador de passwords
    }
}
