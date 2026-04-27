package com.finanzapp.transacciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finanzapp.transacciones.model.Transaccion;
import com.finanzapp.transacciones.service.TransaccionService;
@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    // GET todos → 200
    @GetMapping
    public ResponseEntity<List<Transaccion>> listar() {
        return new ResponseEntity<>(transaccionService.listarTodos(), HttpStatus.OK);
    }

    // POST crear → 201
    @PostMapping
    public ResponseEntity<Transaccion> crear(@RequestBody Transaccion transaccion) {
        return new ResponseEntity<>(transaccionService.guardar(transaccion), HttpStatus.CREATED);
    }

    // GET por id → 200 o 404
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> buscarPorId(@PathVariable Long id) {
        Optional<Transaccion> transaccion = transaccionService.buscarPorId(id);
        if (transaccion.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transaccion.get(), HttpStatus.OK);
    }

    // PUT actualizar → 200 o 404
    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizar(@PathVariable Long id, @RequestBody Transaccion transaccion) {
        Optional<Transaccion> existente = transaccionService.buscarPorId(id);
        if (existente.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        transaccion.setId(id);
        return new ResponseEntity<>(transaccionService.guardar(transaccion), HttpStatus.OK);
    }

    // DELETE → 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        transaccionService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
