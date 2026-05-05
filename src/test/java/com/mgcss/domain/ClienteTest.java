package com.mgcss.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteTest {
	
	@Test
	void debePermitirLeerYModificarCliente() {
	    Cliente cliente = new Cliente(1L, "Jose", "jose@email.com", EstadoCliente.STANDARD);

	    cliente.setId(2L);
	    cliente.setNombre("Ana");
	    cliente.setEmail("ana@email.com");
	    cliente.setEstado(EstadoCliente.PREMIUM);

	    assertEquals(2L, cliente.getId());
	    assertEquals("Ana", cliente.getNombre());
	    assertEquals("ana@email.com", cliente.getEmail());
	    assertEquals(EstadoCliente.PREMIUM, cliente.getEstado());
	}

}
