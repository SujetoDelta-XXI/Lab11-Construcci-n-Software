package com.tecsup.petclinic.services;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testFindOwnerById() {

		Integer ID = 2;
		String NAME = "Betty";
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);

		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + owner);

		assertEquals(NAME, owner.getFirstName());

	}
	@Test
	public void testCreateOwner() {

		String FIRST_NAME = "josuesito";
		String LAST_NAME = "regliati";
		String ADDRESS = "Av.los proceres";
		String CITY = "Lurin";
		String TELEPHONE = "987600063";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);

		Owner ownerCreated = ownerService.create(owner);

		log.info("Owner created: " + ownerCreated);

		assertNotNull(ownerCreated.getId(), "El ID no debe ser null");
		assertEquals(FIRST_NAME, ownerCreated.getFirstName());
		assertEquals(LAST_NAME, ownerCreated.getLastName());
		assertEquals(ADDRESS, ownerCreated.getAddress());
		assertEquals(CITY, ownerCreated.getCity());
		assertEquals(TELEPHONE, ownerCreated.getTelephone());

		try {
			ownerService.delete(ownerCreated.getId());
		} catch (OwnerNotFoundException e) {
			fail("No se pudo eliminar el owner de prueba: " + e.getMessage());
		}
	}

	@Test
	public void testUpdateOwner() {

		//Crear un Owner temporal
		Owner owner = new Owner("Carlos", "Ramirez", "Av. Siempre Viva", "Trujillo", "900000000");
		Owner createdOwner = ownerService.create(owner);

		createdOwner.setFirstName("Carlos A."); //Actualizar datos
		createdOwner.setCity("Lima");

		Owner updatedOwner = ownerService.update(createdOwner); //ejecutar actualización

		assertEquals("Carlos A.", updatedOwner.getFirstName()); //verificar cambios
		assertEquals("Lima", updatedOwner.getCity());
		log.info("UPDATED OWNER: {}", updatedOwner);

		try {
			ownerService.delete(updatedOwner.getId());  //eliminar para limpiar
		} catch (OwnerNotFoundException e) {
			fail("No se pudo eliminar el owner de prueba: " + e.getMessage());
		}
	}

	@Test
	public void testDeleteOwner() {

		//crear un Owner temporal
		Owner owner = new Owner("Temporal", "Eliminar", "Av. Central", "Arequipa", "999999999");
		Owner createdOwner = ownerService.create(owner);

		Integer id = createdOwner.getId();
		assertNotNull(id);
		log.info("OWNER A ELIMINAR: {}", createdOwner);

		//eliminar
		try {
			ownerService.delete(id);        //eliminar
		} catch (OwnerNotFoundException e) {
			fail("Error eliminando el Owner: " + e.getMessage());
		}

		//verificar que ya no existe
		assertThrows(OwnerNotFoundException.class, () -> { //verificar
			ownerService.findById(id);
		});
	}

	@Test
	public void testFindOwnerByName() {

		String NAME = "Betty";
		Owner owner = null;

	}
}

