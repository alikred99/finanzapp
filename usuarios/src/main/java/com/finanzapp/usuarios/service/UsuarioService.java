package com.finanzapp.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzapp.usuarios.model.Usuario;
import com.finanzapp.usuarios.repository.UsuarioRepository;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List <Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Optional <Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario){
    usuario.setId(id);
    return usuarioRepository.save(usuario);
}
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
