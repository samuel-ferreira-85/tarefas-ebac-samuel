package com.samuel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.samuel.dao.ClienteDaoMock;
import com.samuel.dao.IClienteDAO;
import com.samuel.domain.Cliente;
import com.samuel.exceptions.DAOException;
import com.samuel.exceptions.TipoChaveNaoEncontradaException;
import com.samuel.services.ClienteService;
import com.samuel.services.IClienteService;

public class ClienteServiceTest {
	
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Samuel");
		cliente.setEmail("samuel@ebac.com");
		cliente.setCidade("Beberibe");
		cliente.setEndereco("Serra do Félix");
		cliente.setEstado("Ceará");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
	}
	
	@Test
	public void pesquisarCliente() throws DAOException {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() throws DAOException {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		cliente.setNome("Samuel Ferreira");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Samuel Ferreira", cliente.getNome());
	}
}
