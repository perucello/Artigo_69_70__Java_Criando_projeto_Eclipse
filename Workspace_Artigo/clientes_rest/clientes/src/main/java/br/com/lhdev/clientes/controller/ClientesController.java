package br.com.lhdev.clientes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lhdev.clientes.model.Cliente;
import br.com.lhdev.clientes.repository.Clientes;



@RestController
@RequestMapping("clientes")
public class ClientesController {
	
	@Autowired
	private Clientes clientes;
	
	
	// LISTAR 1
	@GetMapping
	public List<Cliente> listar(){
		
		System.out.println("Quantidade de Registros : "+clientes.count());
		
		return (clientes.findAll());
	}
	
	
	// ADICIONAR 2
	@PostMapping("/add")
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		
		return clientes.save(cliente);
	}
	
	// BUSCAR 3
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		
		Cliente cliente = clientes.findOne(id);
		if (clientes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	
	
	// ATUALIZAR 4
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
		Cliente target = clientes.findOne(id);
		
		if (target == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cliente, target, "id");
		
		target = clientes.save(cliente);
		
		return ResponseEntity.ok(target);
	}
	
	
	// DELETAR 5
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		Cliente cliente = clientes.findOne(id);
		
		if(cliente != null) {
			clientes.delete(cliente);
		}
		return ResponseEntity.noContent().build();
	}
	
}
