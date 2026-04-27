package com.finanzapp.transacciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finanzapp.transacciones.model.Transaccion;
import com.finanzapp.transacciones.repository.TransaccionRepository;
@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> listarTodos() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> buscarPorId(Long id) {
        return transaccionRepository.findById(id);
    }

    public Transaccion guardar(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public void eliminar(Long id) {
        transaccionRepository.deleteById(id);
    }
}
