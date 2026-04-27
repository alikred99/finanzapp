package com.finanzapp.transacciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finanzapp.transacciones.model.Transaccion;
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

    
}