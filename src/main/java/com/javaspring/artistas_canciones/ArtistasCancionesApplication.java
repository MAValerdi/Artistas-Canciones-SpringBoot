package com.javaspring.artistas_canciones;

import com.javaspring.artistas_canciones.principal.Principal;
import com.javaspring.artistas_canciones.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistasCancionesApplication implements CommandLineRunner {

    @Autowired
    private ArtistaRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(ArtistasCancionesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio);
        principal.showMenu();
    }

}
