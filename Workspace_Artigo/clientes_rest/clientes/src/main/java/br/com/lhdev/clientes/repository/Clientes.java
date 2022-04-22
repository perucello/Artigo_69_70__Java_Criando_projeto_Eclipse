package br.com.lhdev.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lhdev.clientes.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {


}