package com.javaspring.artistas_canciones.repository;

import com.javaspring.artistas_canciones.model.Artista;
import com.javaspring.artistas_canciones.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNombreContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.canciones m WHERE a.nombre ILIKE %:nombre%")
    List<Cancion> buscaCancionesPorArtista(String nombre);

}
