package br.com.serasa.api.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serasa.api.cliente.entity.Clientes;
import br.com.serasa.api.cliente.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientesController {

	@Autowired
	ClientesRepository _clientesRepository;

	@PostMapping
	public ResponseEntity<Clientes> post(@RequestBody Clientes clientes) {
		return ResponseEntity.status(HttpStatus.CREATED).body(_clientesRepository.saveAndFlush(clientes));
	}

	@GetMapping
	public ResponseEntity<List<Clientes>> findAll() {
		return ResponseEntity.ok(_clientesRepository.findAll());
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<Clientes> getById(@PathVariable Long idCliente) {
		return _clientesRepository.findById(idCliente).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeCliente}")
	public ResponseEntity<List<Clientes>> getByTitulo(@PathVariable String nomeCliente) {
		return (ResponseEntity<List<Clientes>>) ResponseEntity.ok(_clientesRepository.findAllByNomeClienteContainingIgnoreCase(nomeCliente));

	}

}
