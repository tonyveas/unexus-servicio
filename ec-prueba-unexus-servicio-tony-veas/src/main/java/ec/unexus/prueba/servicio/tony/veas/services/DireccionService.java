package ec.unexus.prueba.servicio.tony.veas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDireccionesDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;
import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;

@Service
public class DireccionService {

	private DireccionRepository direccionRepository;
	private ClienteRepository clienteRepository;

	public DireccionService(DireccionRepository direccionRepository, ClienteRepository clienteRepository) {
		super();
		this.direccionRepository = direccionRepository;
		this.clienteRepository = clienteRepository;
	}

	public Direccion agregarDireccion(Integer idCliente, DireccionDTO direccionDTO) {
	    Cliente cliente = clienteRepository.findById(idCliente)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    "No se encontró un cliente con el ID proporcionado"));

	    // Revisa si el typeAddress es null o es SUCURSAL, entonces establece como SUCURSAL.
	    if (direccionDTO.getTypeAddress() == null
	            || TipoDireccion.SUCURSAL.name().equalsIgnoreCase(direccionDTO.getTypeAddress())) {
	        direccionDTO.setTypeAddress(TipoDireccion.SUCURSAL.name()); // Establece como SUCURSAL por defecto
	    } else {
	        // En caso contrario, establece como MATRIZ, pero primero verifica si ya existe una dirección MATRIZ
	        if (cliente.getDireccionMatriz() != null) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente ya tiene una dirección matriz.");
	        }
	        direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());
	    }

	    Direccion nuevaDireccion = createFromDTO(direccionDTO);
	    nuevaDireccion.setCliente(cliente);
	    Direccion direccionGuardada = save(nuevaDireccion);

	    // Si el typeAddress es MATRIZ, actualiza la dirección matriz en el cliente.
	    if (TipoDireccion.MATRIZ.name().equalsIgnoreCase(direccionDTO.getTypeAddress())) {
	        cliente.setDireccionMatriz(direccionGuardada);
	        clienteRepository.save(cliente);
	    }

	    return direccionGuardada;
	}


	public Direccion createFromDTO(DireccionDTO direccionDTO) {
		Direccion direccion = new Direccion();
		direccion.setProvincia(direccionDTO.getMainProvince());
		direccion.setCiudad(direccionDTO.getMainCity());
		direccion.setDireccion(direccionDTO.getMainAddress());

		if (direccionDTO.getTypeAddress() == null || direccionDTO.getTypeAddress().isEmpty()) {
			direccion.setTipoDireccion(TipoDireccion.MATRIZ);
		} else {
			direccion.setTipoDireccion(TipoDireccion.valueOf(direccionDTO.getTypeAddress().toUpperCase()));
		}

		return direccion;
	}

	public ClienteDireccionesDTO getDireccionesCliente(Integer idCliente) {
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No se encontró un cliente con el ID proporcionado"));
		ClienteDireccionesDTO clienteDireccionesDto = new ClienteDireccionesDTO();
		clienteDireccionesDto.setDireccionMatriz(cliente.getDireccionMatriz());

		List<Direccion> direccionesSucursales = cliente.getDireccionesSucursales().stream()
				.filter(direccion -> !direccion.getTipoDireccion().equals(TipoDireccion.MATRIZ))
				.collect(Collectors.toList());

		clienteDireccionesDto.setDireccionesSucursales(direccionesSucursales);
		return clienteDireccionesDto;
	}

	public Direccion save(Direccion direccion) {
		return direccionRepository.save(direccion);
	}

}
