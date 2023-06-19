package ec.unexus.prueba.servicio.tony.veas.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;
import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;

@Component
public class DataSeeder implements CommandLineRunner {

	private final ClienteRepository clienteRepo;
	private final DireccionRepository direccionRepo;

	public DataSeeder(ClienteRepository clienteRepo, DireccionRepository direccionRepo) {
		this.clienteRepo = clienteRepo;
		this.direccionRepo = direccionRepo;
	}

	@Override
	public void run(String... args) throws Exception {

		if (clienteRepo.count() == 0) {
			Cliente cliente = new Cliente();
			cliente.setTipoIdentificacion("CED");
			cliente.setNumeroIdentificacion("0987611111");
			cliente.setNombres("Juana Lima");
			cliente.setCorreo("juana.lima@email.com");
			cliente.setNumeroCelular("0996790000");
			clienteRepo.save(cliente);
			Direccion direccionMatriz = new Direccion();
			direccionMatriz.setProvincia("Provincia de Prueba Matriz Juana");
			direccionMatriz.setCiudad("Ciudad de Prueba Matriz Juana");
			direccionMatriz.setDireccion("Calle de Prueba Matriz Juana");
			direccionMatriz.setTipoDireccion(TipoDireccion.MATRIZ);
			direccionMatriz.setCliente(cliente);
			Direccion direccionSucursal = new Direccion();
			direccionSucursal.setProvincia("Provincia de Prueba Sucursal Juana");
			direccionSucursal.setCiudad("Ciudad de Prueba Sucursal Juana");
			direccionSucursal.setDireccion("Calle de Prueba Sucursal Juana");
			direccionSucursal.setTipoDireccion(TipoDireccion.SUCURSAL);
			direccionSucursal.setCliente(cliente);
			direccionRepo.save(direccionMatriz);
			direccionRepo.save(direccionSucursal);
			cliente.setDireccionMatriz(direccionMatriz);
			clienteRepo.save(cliente);

			Cliente cliente2 = new Cliente();
			cliente2.setTipoIdentificacion("CED");
			cliente2.setNumeroIdentificacion("0987622222");
			cliente2.setNombres("Milan Landázuri");
			cliente2.setCorreo("milan.landazuri@email.com");
			cliente2.setNumeroCelular("0996791111");

			clienteRepo.save(cliente2);

			Direccion direccionMatriz2 = new Direccion();
			direccionMatriz2.setProvincia("Provincia de Prueba Matriz Milan");
			direccionMatriz2.setCiudad("Ciudad de Prueba Matriz Milan");
			direccionMatriz2.setDireccion("Calle de Prueba Matriz Milan");
			direccionMatriz2.setTipoDireccion(TipoDireccion.MATRIZ);
			direccionMatriz2.setCliente(cliente2);

			direccionRepo.save(direccionMatriz2);

			// Guarda la referencia de la dirección matriz en el cliente
			cliente2.setDireccionMatriz(direccionMatriz2);
			clienteRepo.save(cliente2);
		}

	}
}
