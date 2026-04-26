package com.finanzapp.usuarios.controller;

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

import com.finanzapp.usuarios.model.Usuario;
import com.finanzapp.usuarios.service.UsuarioService;
@RequestMapping("api/v1/usuarios")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return new ResponseEntity<>(usuarioService.listarTodos(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable Long id){
        Optional<Usuario> existente = usuarioService.buscarPorId(id);
        if (existente.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(existente.get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> existente = usuarioService.buscarPorId(id);
        if (existente.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        usuario.setId(id);
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
