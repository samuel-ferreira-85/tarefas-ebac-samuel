package com.samuel.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.samuel.exception.DaoException;
import com.samuel.exception.NegocioException;
import com.samuel.model.Cliente;
import com.samuel.service.IClienteService;
import com.samuel.utility.Message;

@Named(value = "clienteMB")
@ViewScoped
public class ClienteController implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cliente cliente;
	
	@Inject
	private IClienteService service;
	
	private Collection<Cliente> clientes;
	
	@PostConstruct
	public void init() throws NegocioException, DaoException {
		clientes = service.buscarTodos();
	}
	
	public void adicionar() throws DaoException {
		try {
			service.cadastrar(cliente);
			cliente = new Cliente();
			init();
			Message.info("Salvo com sucesso");
			
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}
	
	public void excluir() throws DaoException {
		try {
			service.excluir(cliente);
			init();
			Message.info(cliente.getNome() + " foi removido com sucesso.");
			
		} catch (NegocioException e) {
			Message.erro(e.getMessage());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

}
