package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Pessoa;


public class PessoaDaoTest {
	@Test

	public void salvar() {
		Long CodigoCidade = 2L;
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.Buscar(CodigoCidade);
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Marcia");
		pessoa.setCpf("418.848.738-05");
		pessoa.setRg("48482773-X");
		pessoa.setRua("Rua Professor Jose augusto de Oliveira");
		pessoa.setNumero(new Short("426"));
		pessoa.setBairro("Vila Brasil");
		pessoa.setCep("19915280");
		pessoa.setComplemento("Proximo");
		pessoa.setTelefone("1196482-6534");
		pessoa.setCelular("1499810-9215");
		pessoa.setEmail("marcia_batista2010@hotmail.com");
		pessoa.setCidade(cidade);
		
		PessoaDao pessoaDao = new PessoaDao();
		
		pessoaDao.salvar(pessoa);
		
		
		System.out.println("Produto Salvo com Sucesso");
		
		
		
		
	}
	
	@Test
	@Ignore
	public void listar() {
		
		PessoaDao pessoaDao = new PessoaDao();
		
		List<Pessoa> resultado = pessoaDao.listar();
		
		System.out.println("Total de Pessoas Cadastradas"+resultado.size());
		
		for(Pessoa pessoa : resultado) {
			System.out.println("Nome Pessoa: "+pessoa.getNome()+
					           " - Cpf: "+pessoa.getCpf()+
					           " - Rg: "+pessoa.getRg()+
					           " - Rua: "+pessoa.getRua()+
					           " - Numero: "+pessoa.getNumero()+
					           " - Bairro: "+pessoa.getBairro()+
					           " - Cep: "+pessoa.getCep()+
					           " - Complemento: "+pessoa.getComplemento()+
					           " - Telefone: "+pessoa.getTelefone()+
					           " - Celular: "+pessoa.getCelular()+
					           " - Email: "+pessoa.getEmail()+
					           " - Cidade: "+pessoa.getCidade().getNome());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		
		Long codigo = 2L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigo);
		
		if(pessoa == null) {
			System.out.println("Pessoa não Cadastrada");
		}else {
			System.out.println("Nome Pessoa: "+pessoa.getNome()+
			           " - Cpf: "+pessoa.getCpf()+
			           " - Rg: "+pessoa.getRg()+
			           " - Rua: "+pessoa.getRua()+
			           " - Numero: "+pessoa.getNumero()+
			           " - Bairro: "+pessoa.getBairro()+
			           " - Cep: "+pessoa.getCep()+
			           " - Complemento: "+pessoa.getComplemento()+
			           " - Telefone: "+pessoa.getTelefone()+
			           " - Celular: "+pessoa.getCelular()+
			           " - Email: "+pessoa.getEmail()+
			           " - Cidade: "+pessoa.getCidade().getNome());
		}
		
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigo);
		
		if(pessoa == null) {
			System.out.println("Pessoa não cadastrada");
		}else {
			pessoaDao.excluir(pessoa);
			System.out.println("Pessoa Excluida com sucesso");System.out.println("Nome Pessoa: "+pessoa.getNome()+
			           " - Cpf: "+pessoa.getCpf()+
			           " - Rg: "+pessoa.getRg()+
			           " - Rua: "+pessoa.getRua()+
			           " - Numero: "+pessoa.getNumero()+
			           " - Bairro: "+pessoa.getBairro()+
			           " - Cep: "+pessoa.getCep()+
			           " - Complemento: "+pessoa.getComplemento()+
			           " - Telefone: "+pessoa.getTelefone()+
			           " - Celular: "+pessoa.getCelular()+
			           " - Email: "+pessoa.getEmail()+
			           " - Cidade: "+pessoa.getCidade().getNome());
			
		}
		
	}
	
	@Test
	@Ignore
	public void editar() {
		
		Long codigoPessoa = 2L;
		Long codigoCidade = 1L;
		
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigoPessoa);
		
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.Buscar(codigoCidade);
		
		System.out.println("Produto a ser Editado");
		System.out.println("Pessoa Excluida com sucesso");System.out.println("Nome Pessoa: "+pessoa.getNome()+
		           " - Cpf: "+pessoa.getCpf()+
		           " - Rg: "+pessoa.getRg()+
		           " - Rua: "+pessoa.getRua()+
		           " - Numero: "+pessoa.getNumero()+
		           " - Bairro: "+pessoa.getBairro()+
		           " - Cep: "+pessoa.getCep()+
		           " - Complemento: "+pessoa.getComplemento()+
		           " - Telefone: "+pessoa.getTelefone()+
		           " - Celular: "+pessoa.getCelular()+
		           " - Email: "+pessoa.getEmail()+
		           " - Cidade: "+pessoa.getCidade().getNome());
		
		pessoa.setNome("Cleber Batista Graciano");
		pessoa.setCpf("418.848.738-04");
		pessoa.setRg("48482773-X");
		pessoa.setRua("Rua Professor Jose Augusto de Oliveira");
		pessoa.setNumero(new Short("526"));
		pessoa.setBairro("Vila Brasil");
		pessoa.setCep("19915280");
		pessoa.setComplemento("Vila");
		pessoa.setTelefone("1196482-6534");
		pessoa.setCelular("1499810-9215");
		pessoa.setEmail("cleber_batista2010@hotmail.com");
		pessoa.setCidade(cidade);
		
					pessoaDao.editar(pessoa);
					
					System.out.println("Produto Alterado");
					System.out.println("Pessoa Excluida com sucesso");System.out.println("Nome Pessoa: "+pessoa.getNome()+
					           " - Cpf: "+pessoa.getCpf()+
					           " - Rg: "+pessoa.getRg()+
					           " - Rua: "+pessoa.getRua()+
					           " - Numero: "+pessoa.getNumero()+
					           " - Bairro: "+pessoa.getBairro()+
					           " - Cep: "+pessoa.getCep()+
					           " - Complemento: "+pessoa.getComplemento()+
					           " - Telefone: "+pessoa.getTelefone()+
					           " - Celular: "+pessoa.getCelular()+
					           " - Email: "+pessoa.getEmail()+
					           " - Cidade: "+pessoa.getCidade().getNome());
					
		
		
	}
	

}
