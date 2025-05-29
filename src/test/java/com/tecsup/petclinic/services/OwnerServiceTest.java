package com.tecsup.petclinic.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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

		Integer ID = 1;
		String NAME = "Betty";
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
			System.out.println("Usuario encontrado: " + owner);

		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + owner);

		assertEquals(NAME, owner.getFirstName());

	}
	@Test
	public void testCreateOwner() {

		String FIRST_NAME = "jurgen";
		String LAST_NAME = "calle";
		String ADDRESS = "Av.las flores";
		String CITY = "Cuzco";
		String TELEPHONE = "987600243";

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
	public void testFindOwnerByName() {

		String NAME = "Betty";
		Owner owner = null;

	}

}
