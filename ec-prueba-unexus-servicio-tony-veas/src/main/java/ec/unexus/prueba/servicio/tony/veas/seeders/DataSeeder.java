package ec.unexus.prueba.servicio.tony.veas.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;
import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;

/**
 * Esta clase es un seeder de datos, diseñada para llenar la base de datos con datos iniciales
 * para la aplicación. Se utiliza durante el inicio de la aplicación para poblar la base de datos
 * con datos de prueba si no hay datos ya presentes.
 *
 * Implementa la interfaz CommandLineRunner de Spring Boot, que indica que la lógica dentro del
 * método run se debe ejecutar después de que la aplicación se haya iniciado completamente.
 *
 * En este caso, el método run verifica si la tabla de clientes está vacía y, si es así, crea y
 * guarda nuevos clientes y direcciones en la base de datos.
 **/
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
			
			Cliente cliente1 = new Cliente();
			cliente1.setTipoIdentificacion("CÉDULA");
			cliente1.setNumeroIdentificacion("0987611111");
			cliente1.setNombres("Juana Lima");
			cliente1.setCorreo("juana.lima@email.com");
			cliente1.setNumeroCelular("0996791111");
			clienteRepo.save(cliente1);
			Direccion direccionMatriz = new Direccion();
			direccionMatriz.setProvincia("Provincia de Prueba Matriz Juana");
			direccionMatriz.setCiudad("Ciudad de Prueba Matriz Juana");
			direccionMatriz.setDireccion("Calle de Prueba Matriz Juana");
			direccionMatriz.setTipoDireccion(TipoDireccion.MATRIZ);
			direccionMatriz.setCliente(cliente1);
			Direccion direccionSucursal = new Direccion();
			direccionSucursal.setProvincia("Provincia de Prueba Sucursal Juana");
			direccionSucursal.setCiudad("Ciudad de Prueba Sucursal Juana");
			direccionSucursal.setDireccion("Calle de Prueba Sucursal Juana");
			direccionSucursal.setTipoDireccion(TipoDireccion.SUCURSAL);
			direccionSucursal.setCliente(cliente1);
			direccionRepo.save(direccionMatriz);
			direccionRepo.save(direccionSucursal);
			cliente1.setDireccionMatriz(direccionMatriz);
			clienteRepo.save(cliente1);

			Cliente cliente2 = new Cliente();
			cliente2.setTipoIdentificacion("CÉDULA");
			cliente2.setNumeroIdentificacion("0987622222");
			cliente2.setNombres("Milan Landázuri");
			cliente2.setCorreo("milan.landazuri@email.com");
			cliente2.setNumeroCelular("0996792222");
			clienteRepo.save(cliente2);
			Direccion direccionMatriz2 = new Direccion();
			direccionMatriz2.setProvincia("Provincia de Prueba Matriz Milan");
			direccionMatriz2.setCiudad("Ciudad de Prueba Matriz Milan");
			direccionMatriz2.setDireccion("Calle de Prueba Matriz Milan");
			direccionMatriz2.setTipoDireccion(TipoDireccion.MATRIZ);
			direccionMatriz2.setCliente(cliente2);
			direccionRepo.save(direccionMatriz2);
			cliente2.setDireccionMatriz(direccionMatriz2);
			clienteRepo.save(cliente2);
			
			Cliente cliente3 = new Cliente();
			cliente3.setTipoIdentificacion("CÉDULA");
			cliente3.setNumeroIdentificacion("0987633333");
			cliente3.setNombres("Lira Pinoargote");
			cliente3.setCorreo("Lira.pinoargote@email.com");
			cliente3.setNumeroCelular("0996793333");
			clienteRepo.save(cliente3);
			Direccion direccionMatriz3 = new Direccion();
			direccionMatriz3.setProvincia("Provincia de Prueba Matriz Lira Pinoargote");
			direccionMatriz3.setCiudad("Ciudad de Prueba Matriz Lira Pinoargote");
			direccionMatriz3.setDireccion("Calle de Prueba Matriz Lira Pinoargote");
			direccionMatriz3.setTipoDireccion(TipoDireccion.MATRIZ);
			direccionMatriz3.setCliente(cliente3);
			direccionRepo.save(direccionMatriz3);
			cliente3.setDireccionMatriz(direccionMatriz3);
			clienteRepo.save(cliente3);
			
			Cliente cliente4 = new Cliente();
			cliente4.setTipoIdentificacion("CÉDULA");
			cliente4.setNumeroIdentificacion("0587644444");
			cliente4.setNombres("Lira Perez");
			cliente4.setCorreo("lira.perez@email.com");
			cliente4.setNumeroCelular("0996794444");
			clienteRepo.save(cliente4);
			Direccion direccionMatriz4 = new Direccion();
			direccionMatriz4.setProvincia("Provincia de Prueba Matriz Lira Perez");
			direccionMatriz4.setCiudad("Ciudad de Prueba Matriz Lira Perez");
			direccionMatriz4.setDireccion("Calle de Prueba Matriz Lira Perez");
			direccionMatriz4.setTipoDireccion(TipoDireccion.MATRIZ);
			direccionMatriz4.setCliente(cliente4);
			direccionRepo.save(direccionMatriz4);
			cliente4.setDireccionMatriz(direccionMatriz4);
			clienteRepo.save(cliente4);
			
		}

	}
}
