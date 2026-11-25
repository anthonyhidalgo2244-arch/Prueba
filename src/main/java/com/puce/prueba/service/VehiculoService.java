package com.puce.prueba.service;

import com.puce.prueba.model.Vehiculo;
import com.puce.prueba.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> traerTodos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        vehiculo.setId(0);
        return vehiculoRepository.save(vehiculo);
    }

}

