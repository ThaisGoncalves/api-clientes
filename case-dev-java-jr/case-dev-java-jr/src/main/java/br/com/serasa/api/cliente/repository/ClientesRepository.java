package br.com.serasa.api.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.api.cliente.entity.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
	
	List<Clientes> findAllByNomeClienteContainingIgnoreCase(String nomeCliente);
	
}
