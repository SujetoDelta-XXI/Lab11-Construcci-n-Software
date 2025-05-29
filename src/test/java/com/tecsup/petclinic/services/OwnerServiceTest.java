package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

import lombok.extern.slf4j.Slf4j;
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

		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("" + owner);

		assertEquals(NAME, owner.getFirstName());

	}

	@Test
	public void testFindOwnerByName() {

		String NAME = "Betty";
		Owner owner = null;

	}

}
