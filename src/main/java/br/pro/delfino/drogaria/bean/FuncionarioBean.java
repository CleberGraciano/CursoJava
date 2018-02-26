package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.FuncionarioDao;
import br.pro.delfino.drogaria.dao.PessoaDao;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {
	
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	
	public void novo () {
		try {
		
		funcionario = new Funcionario();
		
		PessoaDao pessoaDao = new PessoaDao();
		pessoas = pessoaDao.listar("nome");
		
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar um novo Funcionario");
			erro.printStackTrace();
		}
		
		
		
	}
	
	@PostConstruct
	public void listar() {
		
		try {
			
		
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		
		funcionarios = funcionarioDao.listar("dataAdmissao");
		
		}catch (RuntimeException erro) {
			
			Messages.addGlobalError("Erro ao Listar os Funcionarios");
			erro.printStackTrace();
		}
		
		
	}
	
	public void salvar() {
		
		try {
			
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			
			funcionarioDao.merge(funcionario);
			
			funcionario = new Funcionario();
			
			funcionarios = funcionarioDao.listar("dataAdmissao");
			
			PessoaDao pessoaDao = new PessoaDao();
			
			pessoas = pessoaDao.listar("nome");
			
			
			Messages.addGlobalInfo("Funcionario Salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar Salvar o Funcionario");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento) {
		try {

			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");
			
			
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Funcionario ");

			erro.printStackTrace();
		}
		
		
		
	}
	
	public void excluir(ActionEvent evento) {
		
try {
			
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.excluir(funcionario);
			
			
		
			
			funcionarios = funcionarioDao.listar("dataAdmissao");
			
		
			
			Messages.addGlobalInfo("Funcionario removido com sucesso");
			}catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar remover o Funcionario");
				erro.printStackTrace();
			}
		
	}
	

}
