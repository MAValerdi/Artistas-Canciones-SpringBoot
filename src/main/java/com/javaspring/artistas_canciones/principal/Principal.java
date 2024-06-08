package com.javaspring.artistas_canciones.principal;

import com.javaspring.artistas_canciones.repository.ArtistaRepository;
import com.javaspring.artistas_canciones.model.Cancion;
import com.javaspring.artistas_canciones.model.Artista;
import com.javaspring.artistas_canciones.model.TipoArtista;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner teclado = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void showMenu() {
        var opcion = -1;

        while (opcion!= 0) {
            var menu = """
                    
                    *** Menú Principal - Artistas y sus Canciones  ***                    
                                        
                    1- Registrar Artistas
                    2- Registrar Canciones
                    3- Mostrar Canciones
                    4- Buscar Canciones por Artista
                    5- Buscar Informacion del artista
                                    
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    registrarArtista();
                    break;
                case 2:
                    registrarCanciones();
                    break;
                case 3:
                    listarCanciones();
                    break;
                case 4:
                    buscarCancionesPorArtista();
                    break;
                case 5:
                    //buscarDatosDelArtista();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // Datos del ChapGPT quedan inhabilitados
//    private void buscarDatosDelArtista() {
//        System.out.println("¿Desea buscar datos sobre qual artista? ");
//        var nombre = teclado.nextLine();
//        var respueta = ConsultaChatGPT.obtenerInformacion(nombre);
//        System.out.println(respueta.trim());
//    }

    private void buscarCancionesPorArtista() {
        System.out.println("Proporcioname el nombre del Artista que deseas buscar sus Canciones, Por Favor ");
        var nombre = teclado.nextLine();
        List<Cancion> canciones = repositorio.buscaCancionesPorArtista(nombre);
        canciones.forEach(System.out::println);
    }

    private void listarCanciones() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getCanciones().forEach(System.out::println));
    }

    private void registrarCanciones() {
        System.out.println("¿Desea Ingresar una canción?  Indícame el nombre del Artista? ");
        var nombre = teclado.nextLine();
        Optional<Artista> artista = repositorio.findByNombreContainingIgnoreCase(nombre);
        if (artista.isPresent()) {
            System.out.println("Ingresa por favor el título de la canción: ");
            var nombreCancion = teclado.nextLine();
            Cancion cancion = new Cancion(nombreCancion);
            cancion.setArtista(artista.get());
            artista.get().getCanciones().add(cancion);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista no encontrado!!! ");
        }
    }

    private void registrarArtista() {
        var registrarNuevo = "S";

        while (registrarNuevo.equalsIgnoreCase("s")) {
            System.out.println("Proporciona el nombre del artista, Por Favor: ");
            var nombre = teclado.nextLine();
            System.out.println("Indica el tipo de este Artista: (solo, duo o banda) ");
            var tipo = teclado.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nombre, tipoArtista);
            repositorio.save(artista);
            System.out.println("¿Deseas AGREGAR un Artista más? (S/N)");
            registrarNuevo = teclado.nextLine();
        }
    }
}
