package ec.unexus.prueba.servicio.tony.veas.controllers;

import java.util.ArrayList;
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

import ec.unexus.prueba.servicio.tony.veas.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.services.ClienteService;
import ec.unexus.prueba.servicio.tony.veas.services.DireccionService;

@RestController
public class ClienteController {

	private ClienteService clienteService;
	private DireccionService direccionService;

	public ClienteController(ClienteService clienteService, DireccionService direccionService) {
		super();
		this.clienteService = clienteService;
		this.direccionService = direccionService;
	}

	@GetMapping(path = "/getClientes")
	public List<Cliente> getAllClientes() {
		return clienteService.getClientes();
	}

	@GetMapping(path = "/buscarClientes")
	public List<ClienteDTO> buscarClientes(@RequestParam(required = false) String search) {
		return clienteService.buscarClientes(search);
	}

	@PostMapping(path = "/crearCliente")
	public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteDTO clienteDTO) {
		Cliente existente = clienteService.findByNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		if (existente != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Ya existe un cliente con el número de identificación proporcionado");
		}

		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setTipoIdentificacion(clienteDTO.getIdentificationType());
		nuevoCliente.setNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		nuevoCliente.setNombres(clienteDTO.getNames());
		nuevoCliente.setCorreo(clienteDTO.getEmail());
		nuevoCliente.setNumeroCelular(clienteDTO.getCellphone());

		if (clienteDTO.getMainProvince() != null && !clienteDTO.getMainProvince().isEmpty()
				&& clienteDTO.getMainCity() != null && !clienteDTO.getMainCity().isEmpty()
				&& clienteDTO.getMainAddress() != null && !clienteDTO.getMainAddress().isEmpty()) {

			Direccion direccion = new Direccion();
			direccion.setProvincia(clienteDTO.getMainProvince());
			direccion.setCiudad(clienteDTO.getMainCity());
			direccion.setDireccion(clienteDTO.getMainAddress());

			// Set TipoDireccion a MATRIZ si no se especificó otra
			if (clienteDTO.getTypeAddress() == null) {
				direccion.setTipoDireccion(TipoDireccion.MATRIZ);
				nuevoCliente.setDireccionMatriz(direccion);
			} else {
				
				try {
					TipoDireccion tipoDireccion = TipoDireccion.valueOf(clienteDTO.getTypeAddress().toUpperCase());

	                if (tipoDireccion != TipoDireccion.MATRIZ && tipoDireccion != TipoDireccion.SUCURSAL) {
	                    throw new IllegalArgumentException("TipoDireccion no es válido. Debe ser 'MATRIZ' o 'SUCURSAL'");
	                }

	                direccion.setTipoDireccion(tipoDireccion);

	                if (nuevoCliente.getDireccionesSucursales() == null) {
	                    nuevoCliente.setDireccionesSucursales(new ArrayList<>());
	                }
	                
	                if (tipoDireccion == TipoDireccion.MATRIZ) {
	                    nuevoCliente.setDireccionMatriz(direccion);
	                } else {
	                    nuevoCliente.getDireccionesSucursales().add(direccion); 
	                }
		        } catch (IllegalArgumentException e) {
		            throw new ResponseStatusException(
		                    HttpStatus.BAD_REQUEST, "TipoDireccion no es válido. Debe ser 'MATRIZ' o 'SUCURSAL'", e);
		        }
			}

			direccion.setCliente(nuevoCliente);
		}

		nuevoCliente = clienteService.save(nuevoCliente); // Save the cliente and the address

		return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
	}

	@PutMapping(path = "/editarCliente")
	public ResponseEntity<ClienteDTO> editarCliente(@RequestBody ClienteDTO clienteDTO) {
		Cliente clienteExistente = clienteService.findByNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		if (clienteExistente != null && !clienteExistente.getId().equals(clienteDTO.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Ya existe otro cliente con el número de identificación proporcionado");
		}
		return new ResponseEntity<>(clienteService.update(clienteDTO), HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminarCliente/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
		clienteService.eliminarCliente(id);
		return ResponseEntity.ok().build();
	}

}
