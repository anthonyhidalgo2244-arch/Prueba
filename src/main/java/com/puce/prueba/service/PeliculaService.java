package com.puce.prueba.service;

import com.puce.prueba.model.Pelicula;
import com.puce.prueba.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    // Obtiene todas las peliculas guardadas
    public List<Pelicula> traerTodos() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> traerUno(int id) {
        return peliculaRepository.findById(id);
    }

    // Guarda una pelicula
    public Pelicula guardar(Pelicula pelicula) {
        pelicula.setId(0);
        return peliculaRepository.save(pelicula);
    }

    //editar una pelicula
    public Optional<Pelicula> editar(int id, Pelicula datos) {
        Optional<Pelicula> resultado = peliculaRepository.findById(id);

        if (resultado.isEmpty()) {
            return Optional.empty();
        }

        Pelicula pelicula = resultado.get();
        pelicula.setTitulo(datos.getTitulo());
        pelicula.setDirector(datos.getDirector());
        pelicula.setGenero(datos.getGenero());
        pelicula.setDuracionMinutos(datos.getDuracionMinutos());
        pelicula.setAnioEstreno(datos.getAnioEstreno());

        return Optional.of(peliculaRepository.save(pelicula));
    }

    // Elimina la pelicula
    public boolean eliminar(int id) {
        if (!peliculaRepository.existsById(id)) {
            return false;
        }

        peliculaRepository.deleteById(id);
        return true;
    }

    // Filtra las peliculas
    public List<Pelicula> estrenos(int desde, int hasta) {
        List<Pelicula> estrenos = new ArrayList<>();

        for (Pelicula pelicula : peliculaRepository.findAll()) {
            Integer anio = pelicula.getAnioEstreno();
            if (anio != null && anio >= desde && anio <= hasta) {
                estrenos.add(pelicula);
            }
        }

        return estrenos;
    }
}

