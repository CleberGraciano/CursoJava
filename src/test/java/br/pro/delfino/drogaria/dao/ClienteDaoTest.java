package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Pessoa;

public class ClienteDaoTest {
	@Test

	public void salvar() throws ParseException {
		Long codigoPessoa = 1L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigoPessoa);

		Cliente cliente = new Cliente();

		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2018"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);

		ClienteDao clienteDao = new ClienteDao();

		clienteDao.salvar(cliente);
		System.out.println("Cliente Salvo com Sucesso");
	}
	
	@Test
	@Ignore
	public void listar() {
		
		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> resultado = clienteDao.listar();
		
		System.out.println("Total de Cliente Cadastrados são:");
		
		for(Cliente cliente : resultado) {
			
			System.out.println("Codigo Cliente: "+cliente.getCodigo()+
								" - Data Cadastro: "+cliente.getDataCadastro()+
								" - Status: "+cliente.getLiberado()+
								" - Pessoa: "+cliente.getPessoa().getNome()+
								"\n");
			
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo=1L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.Buscar(codigo);
		
		if(cliente == null) {
			System.out.println("Cliente não existe");
		}else {
			System.out.println("Codigo Cliente: "+cliente.getCodigo()+
					" - Data Cadastro: "+cliente.getDataCadastro()+
					" - Status: "+cliente.getLiberado()+
					" - Pessoa: "+cliente.getPessoa().getNome()+
					"\n");
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.Buscar(codigo);
		
		if(cliente == null) {
			System.out.println("Cliente não encontrado ");
		}else {
		
		clienteDao.excluir(cliente);
		System.out.println("Cliente Excluido com sucesso");
		System.out.println("Codigo Cliente: "+cliente.getCodigo()+
				" - Data Cadastro: "+cliente.getDataCadastro()+
				" - Status: "+cliente.getLiberado()+
				" - Pessoa: "+cliente.getPessoa().getNome()+
				"\n");
		}
		
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		
		Long codigo = 3L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.Buscar(codigo);
		
		Long codigoPessoa = 1L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigoPessoa);
		
		System.out.println("Cliente a ser alterado");
		
		System.out.println("Codigo Cliente: "+cliente.getCodigo()+
				" - Data Cadastro: "+cliente.getDataCadastro()+
				" - Status: "+cliente.getLiberado()+
				" - Pessoa: "+cliente.getPessoa().getNome()+
				"\n");

	
		
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2012"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);

		clienteDao.editar(cliente);
		
		System.out.println("\n Cliente Alterado");
		
		
		System.out.println("Codigo Cliente: "+cliente.getCodigo()+
				" - Data Cadastro: "+cliente.getDataCadastro()+
				" - Status: "+cliente.getLiberado()+
				" - Pessoa: "+cliente.getPessoa().getNome()+
				"\n");
		
		
	}

}
