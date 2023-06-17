package ec.unexus.prueba.servicio.tony.veas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDireccionesDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;

@Service
public class DireccionService {

	private DireccionRepository direccionRepository;
    private ClienteRepository clienteRepository; // Nueva referencia al repositorio de clientes

	public DireccionService(DireccionRepository direccionRepository, ClienteRepository clienteRepository) {
		super();
		this.direccionRepository = direccionRepository;
		this.clienteRepository = clienteRepository;
	}
	
	public Direccion createFromDTO(DireccionDTO direccionDTO) {
	    Direccion direccion = new Direccion();
	    direccion.setProvincia(direccionDTO.getMainProvince());
	    direccion.setCiudad(direccionDTO.getMainCity());
	    direccion.setDireccion(direccionDTO.getMainAddress());

	    // Set TipoDireccion a MATRIZ si no se especificó otra
	    if (direccionDTO.getTypeAddress() == null || direccionDTO.getTypeAddress().isEmpty()) {
	        direccion.setTipoDireccion(TipoDireccion.MATRIZ);
	    } else {
	        direccion.setTipoDireccion(TipoDireccion.valueOf(direccionDTO.getTypeAddress().toUpperCase()));
	    }

	    return direccion;
	}
	
	public ClienteDireccionesDTO getDireccionesCliente(Integer idCliente) {
	    Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> 
	        new ResponseStatusException(
	                HttpStatus.NOT_FOUND, "No se encontró un cliente con el ID proporcionado"
	        )
	    );
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
