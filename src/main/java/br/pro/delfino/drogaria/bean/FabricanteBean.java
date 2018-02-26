package br.pro.delfino.drogaria.bean;

import java.io.Serializable;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.pro.delfino.drogaria.dao.FabricanteDao;
import br.pro.delfino.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	private Fabricante fabricante;

	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public void novo() {

		try {

			fabricante = new Fabricante();

			listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao criar um novo Fabricante");
		}
	}

	public void salvar() {

		try {

			// FabricanteDao fabricanteDao = new FabricanteDao();
			// fabricanteDao.merge(fabricante);

			// Fabricante new Fabricante();
			// fabricantes = fabricanteDao.listar("descricao");

			Client cliente = ClientBuilder.newClient();

			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");

			Gson gson = new Gson();

			String json = gson.toJson(fabricante);

			// Criar uma entity a partir da String json
			caminho.request().post(Entity.json(json));

			fabricante = new Fabricante();

			// Busca no meu desvico todas as descrições
			json = caminho.request().get(String.class);

			// transforma o json em vetor de fabricante
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);

			// converte o vetor para array list
			fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante Salvo com Sucesso");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Erro ao salvar Fabricante");
			erro.printStackTrace();

		}
	}

	@PostConstruct
	public void listar() {

		try {

			// FabricanteDao fabricanteDao = new FabricanteDao();
			// fabricantes = fabricanteDao.listar("descricao");

			// Criando uma chamada ao serviço criado
			Client cliente = ClientBuilder.newClient();

			// pega o caminho o path do serviço
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/fabricante");

			// especifica o tipo do serviço caso Get para listar
			String json = caminho.request().get(String.class);

			// cria um json
			Gson gson = new Gson();

			// transforma o json em vetor
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);

			// converte o vetor para array list
			fabricantes = Arrays.asList(vetor);

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao exibir os Fabricantes");
			erro.printStackTrace();

		}
	}

	public void excluir(ActionEvent evento) {

		try {

			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricanteDao.excluir(fabricante);

			listar();

			Messages.addGlobalInfo("Fabricante Excluido com Sucesso");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Erro ao salvar Fabricante");
			erro.printStackTrace();

		}

	}

	public void editar(ActionEvent evento) {

		try {

			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Fabricante");
			erro.printStackTrace();
		}

	}

}
