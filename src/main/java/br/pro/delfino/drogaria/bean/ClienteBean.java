package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.ClienteDao;
import br.pro.delfino.drogaria.dao.PessoaDao;
import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable  {
	
	private Cliente cliente;
	
	private List<Cliente> clientes;
	
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	
	public void novo() {
		
		try {
		
		
		cliente = new Cliente();
		
		PessoaDao pessoaDao = new PessoaDao();
		
		pessoas = pessoaDao.listar("nome");
		
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar u8m novo Cliente");
			erro.printStackTrace();
		}
		
		
		
	}
	
	@PostConstruct
	public void listar() {
		try {
		
		ClienteDao clienteDao = new ClienteDao();
		
		
		
		clientes = clienteDao.listar("dataCadastro");
		
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Listar os dados de Cliente");
			erro.printStackTrace();
		}
		
	}
	
	public void salvar() {
		
		try {
		
		ClienteDao clienteDao = new ClienteDao();
		
		clienteDao.merge(cliente);
		
		cliente = new Cliente();
		
		clientes = clienteDao.listar("dataCadastro");
		
		PessoaDao pessoaDao = new PessoaDao();
		pessoas = pessoaDao.listar("nome");
		
			Messages.addGlobalInfo("Cliente Salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao Salvar o Cliente");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento) {
		
		try {

			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");
			
			
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma nova cidade");

			erro.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento) {
		
		try {
			
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			
			ClienteDao clienteDao = new ClienteDao();
			clienteDao.excluir(cliente);
		
			
			clientes = clienteDao.listar("dataCadastro");
			
		
			
			Messages.addGlobalInfo("Cidade removida com sucesso");
			}catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar remover a Cidade");
				erro.printStackTrace();
			}
		
	}
	
	
	

}
