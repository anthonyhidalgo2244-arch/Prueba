package com.puce.prueba.controller;

import com.puce.prueba.model.Pelicula;
import com.puce.prueba.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    // Devuelve lista completa
    @GetMapping
    public List<Pelicula> verTodos() {
        return peliculaService.traerTodos();
    }

    // Busca una pelicula por id
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> traerUno(@PathVariable int id) {
        return peliculaService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registra una pelicula
    @PostMapping
    public ResponseEntity<Pelicula> guardar(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaService.guardar(pelicula);
        return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
    }

    // Actualiza los datos
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> editar(@PathVariable int id, @RequestBody Pelicula pelicula) {
        return peliculaService.editar(id, pelicula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Elimina una pelicula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (peliculaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //pelicula por anio
    @GetMapping("/estrenos")
    public List<Pelicula> estrenos(@RequestParam int desde, @RequestParam int hasta) {
        return peliculaService.estrenos(desde, hasta);
    }
}

