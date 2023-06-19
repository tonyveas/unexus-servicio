package ec.unexus.prueba.servicio.tony.veas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.services.ClienteService;
import jakarta.validation.Valid;

/**
 * Controlador para la gestión de clientes.
 * Proporciona endpoints para buscar, crear, editar y eliminar clientes.
 */
@RestController
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	/* Endpoint para buscar clientes */
	@GetMapping(path = "/clientes")
	public ResponseEntity<List<ClienteDTO>> buscarClientes(@RequestParam(required = false) String search) {
	    List<ClienteDTO> clientes = clienteService.buscarClientes(search);
	    return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	/* Endpoint para crear un cliente */
	@PostMapping(path = "/clientes")
	public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		try {
			Cliente nuevoCliente = clienteService.createCliente(clienteDTO);
			return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	/* Endpoint para editar un cliente existente */
	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<ClienteDTO> editarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
	    try {
	        return new ResponseEntity<>(clienteService.update(id, clienteDTO), HttpStatus.OK);
	    } catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	/* Endpoint para eliminar un cliente */
	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
		clienteService.eliminarCliente(id);
		return ResponseEntity.noContent().build();
	}
	
}
