package com.finanzapp.categorias.controller;

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

import com.finanzapp.categorias.model.Categoria;
import com.finanzapp.categorias.service.CategoriaService;
@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService Categoriaservice;

    // GET todos → 200
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return new ResponseEntity<>(Categoriaservice.listarTodos(), HttpStatus.OK);
    }

    // POST crear → 201
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(Categoriaservice.guardar(categoria), HttpStatus.CREATED);
    }

    // GET por id → 200 o 404
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = Categoriaservice.buscarPorId(id);
        if (categoria.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categoria.get(), HttpStatus.OK);
    }

    // PUT actualizar → 200 o 404
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> existente = Categoriaservice.buscarPorId(id);
        if (existente.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        categoria.setId(id);
        return new ResponseEntity<>(Categoriaservice.guardar(categoria), HttpStatus.OK);
    }

    // DELETE → 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Categoriaservice.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
