package ec.unexus.prueba.servicio.tony.veas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;
import jakarta.transaction.Transactional;

/**
 * La clase ClienteService es un servicio de Spring Boot que se encarga de la lógica para los clientes.
 * El servicio interactúa con el repositorio de clientes para realizar operaciones CRUD (crear, leer, actualizar, borrar)
 * en la base de datos.
 *
 * Las operaciones incluyen buscar clientes por un término de búsqueda, convertir una entidad Cliente a un DTO, encontrar 
 * un cliente por su número de identificación, crear un nuevo cliente a partir de un DTO, verificar si un tipo de dirección 
 * es válido, guardar un cliente en la base de datos, actualizar los datos de un cliente existente con datos 
 * de un DTO, encontrar un cliente por su ID y eliminar un cliente de la base de datos por su ID.
 **/
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	public List<ClienteDTO> buscarClientes(String search) {
		List<Cliente> clientes = clienteRepository.findByNumeroIdentificacionContainingOrNombresContaining(search,
				search);
		return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ClienteDTO convertToDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setIdentificationType(cliente.getTipoIdentificacion());
		clienteDTO.setIdentificationNumber(cliente.getNumeroIdentificacion());
		clienteDTO.setNames(cliente.getNombres());
		clienteDTO.setEmail(cliente.getCorreo());
		clienteDTO.setCellphone(cliente.getNumeroCelular());
		Direccion direccionMatriz = cliente.getDireccionMatriz();
		clienteDTO.setMainProvince(direccionMatriz.getProvincia());
		clienteDTO.setMainCity(direccionMatriz.getCiudad());
		clienteDTO.setMainAddress(direccionMatriz.getDireccion());
		clienteDTO.setTypeAddress(direccionMatriz.getTipoDireccion().toString());
		return clienteDTO;
	}

	public Cliente findByNumeroIdentificacion(String numeroIdentificacion) {
		return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);
	}

	@Transactional
	public Cliente createCliente(ClienteDTO clienteDTO) {
		Cliente existente = findByNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		if (existente != null) {
			throw new IllegalArgumentException("Ya existe un cliente con el número de identificación proporcionado");
		}

		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setTipoIdentificacion(clienteDTO.getIdentificationType());
		nuevoCliente.setNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		nuevoCliente.setNombres(clienteDTO.getNames());
		nuevoCliente.setCorreo(clienteDTO.getEmail());
		nuevoCliente.setNumeroCelular(clienteDTO.getCellphone());

		if (clienteDTO.getMainProvince() != null & clienteDTO.getMainCity() != null
				& clienteDTO.getMainAddress() != null & !clienteDTO.getMainProvince().isEmpty()
				& !clienteDTO.getMainCity().isEmpty() & !clienteDTO.getMainAddress().isEmpty()) {

			Direccion direccion = new Direccion();
			direccion.setProvincia(clienteDTO.getMainProvince());
			direccion.setCiudad(clienteDTO.getMainCity());
			direccion.setDireccion(clienteDTO.getMainAddress());

			if (clienteDTO.getTypeAddress() == null) {
				direccion.setTipoDireccion(TipoDireccion.MATRIZ);
				nuevoCliente.setDireccionMatriz(direccion);
			} else {
				if (!isValidTipoDireccion(clienteDTO.getTypeAddress().toUpperCase())) {
					throw new IllegalArgumentException("TipoDireccion no es válido. Debe ser 'MATRIZ' o 'SUCURSAL'");
				}

				TipoDireccion tipoDireccion = TipoDireccion.valueOf(clienteDTO.getTypeAddress().toUpperCase());

				direccion.setTipoDireccion(tipoDireccion);

				if (tipoDireccion == TipoDireccion.MATRIZ) {
					nuevoCliente.setDireccionMatriz(direccion);
				} else {
					nuevoCliente.getDireccionesSucursales().add(direccion);
				}
			}

			direccion.setCliente(nuevoCliente);
		}

		return save(nuevoCliente);
	}

	private boolean isValidTipoDireccion(String tipoDireccion) {
		for (TipoDireccion td : TipoDireccion.values()) {
			if (td.name().equals(tipoDireccion)) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public ClienteDTO update(Integer id, ClienteDTO clienteDTO) {
		Cliente clienteExistente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No se encontró un cliente con el ID proporcionado"));

		Cliente clienteConNuevoNumero = clienteRepository
				.findByNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		if (clienteConNuevoNumero != null & !clienteConNuevoNumero.getId().equals(id)) {
			throw new IllegalArgumentException("Ya existe un cliente con el número de identificación proporcionado");
		}

		clienteExistente.setTipoIdentificacion(clienteDTO.getIdentificationType());
		clienteExistente.setNumeroIdentificacion(clienteDTO.getIdentificationNumber());
		clienteExistente.setNombres(clienteDTO.getNames());
		clienteExistente.setCorreo(clienteDTO.getEmail());
		clienteExistente.setNumeroCelular(clienteDTO.getCellphone());

		Cliente clienteActualizado = clienteRepository.save(clienteExistente);

		return convertToDTO(clienteActualizado);
	}

	public Cliente findById(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
	}

	@Transactional
	public void eliminarCliente(Integer id) {
		clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"No se encontró un cliente con el ID proporcionado"));
		clienteRepository.deleteById(id);
	}

}
