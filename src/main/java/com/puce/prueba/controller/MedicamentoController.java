package com.puce.prueba.controller;

import com.puce.prueba.model.Medicamento;
import com.puce.prueba.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public List<Medicamento> verTodos() {
        return medicamentoService.traerTodos();
    }

    @PostMapping
    public ResponseEntity<Medicamento> guardar(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoService.guardar(medicamento);
        return new ResponseEntity<>(nuevoMedicamento, HttpStatus.CREATED);
    }

}

