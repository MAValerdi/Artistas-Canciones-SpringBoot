package com.javaspring.artistas_canciones.model;

import jakarta.persistence.*;

@Entity
@Table(name = "canciones")

public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    //relacion de un artista con muchas canciones
    @ManyToOne
    private Artista artista;

    public Cancion(){}
    public Cancion(String nombreCancion){
        this.titulo = nombreCancion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return
                " Título= '" + titulo + '\'' +
                ", Artista= " + artista.getNombre();
    }
}
