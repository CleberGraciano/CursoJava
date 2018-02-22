package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;

public class FuncionarioDaoTest {

	@Test
	@Ignore
	public void salvar() throws ParseException {
		Long codigoPessoa = 2L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigoPessoa);

		Funcionario funcionario = new Funcionario();
		
		funcionario.setCarteiraTrabalho("123456");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("30/01/2018"));
		funcionario.setPessoa(pessoa);
		
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		funcionarioDao.salvar(funcionario);
		System.out.println("Funcionario Salvo com Sucesso");

	
	}
	
	@Test
	@Ignore
	public void listar() {
		
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		List<Funcionario> resultado = funcionarioDao.listar();
		
		System.out.println("Total de Funcionarios Cadastrados são:"+resultado.size());
				
		for(Funcionario funcionario : resultado) {
			
			System.out.println("Codigo Funcionario: "+funcionario.getCodigo()+
								" - Data Admissão: "+funcionario.getDataAdmissao()+
								" - Pessoa: "+funcionario.getPessoa().getNome()+
								"\n");
			
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		
		Long codigoFuncionario = 1L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.Buscar(codigoFuncionario);
		
		
		if(funcionario == null) {
			System.out.println("Funcionario não existe");
		}else {
			System.out.println("Codigo Funcionario: "+funcionario.getCodigo()+
					" - Data Admissão: "+funcionario.getDataAdmissao()+
					" - Pessoa: "+funcionario.getPessoa().getNome()+
					"\n");
		}
	}
	
	@Test
	@Ignore
	public void excluir() {
		
		Long codigoFuncionario = 1L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.Buscar(codigoFuncionario);
		
		
		if(funcionario == null) {
			System.out.println("Funcionario não encontrado ");
		}else {
		
		funcionarioDao.excluir(funcionario);
		
		System.out.println("Funcionario Excluido com sucesso");
	
		System.out.println("Codigo Funcionario: "+funcionario.getCodigo()+
				" - Data Admissão: "+funcionario.getDataAdmissao()+
				" - Pessoa: "+funcionario.getPessoa().getNome()+
				"\n");
		}
		
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		
		Long codigoFuncionario = 1L;
		
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.Buscar(codigoFuncionario);
		
		Long codigoPessoa = 1L;
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.Buscar(codigoPessoa);
		
		
		System.out.println("Funcionario a ser alterado");
		
		System.out.println("Codigo Funcionario: "+funcionario.getCodigo()+
				" - Data Admissão: "+funcionario.getDataAdmissao()+
				" - Pessoa: "+funcionario.getPessoa().getNome()+
				"\n");
		
		funcionario.setCarteiraTrabalho("1111");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("30/01/2018"));
		funcionario.setPessoa(pessoa);

		funcionarioDao.editar(funcionario);
		
		System.out.println("\n Funcionario Alterado");
		
		
		System.out.println("Codigo Funcionario: "+funcionario.getCodigo()+
				" - Data Admissão: "+funcionario.getDataAdmissao()+
				" - Pessoa: "+funcionario.getPessoa().getNome()+
				"\n");
		
		
	}
	

}

