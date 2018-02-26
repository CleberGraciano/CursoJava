package br.pro.delfino.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.pro.delfino.drogaria.dao.FabricanteDao;
import br.pro.delfino.drogaria.domain.Fabricante;


//http://localhost:8080/Drogaria/rest/fabricante
@Path("fabricante")
public class FabricanteService {
	
	@GET
	public String listar() {
		
		FabricanteDao fabricanteDao = new FabricanteDao();
		List<Fabricante> fabricantes = fabricanteDao.listar("descricao");
		
		Gson gson = new Gson();
		
		String json =gson.toJson(fabricantes);
		
		return json;
		
		
	}
	
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(codigo);
		Gson gson = new Gson();
		String json = gson.toJson(fabricante);
		return json;
		
		
	}
	
	// http://localhost:8080/Drogaria/rest/fabricante

		@POST
		public String salvar(String json) {

			Gson gson = new Gson();
			Fabricante fabricante = gson.fromJson(json, Fabricante.class);
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricanteDao.merge(fabricante);
			String jsonSaida = gson.toJson(fabricante);

			return jsonSaida;

		}
		
		// http://localhost:8080/Drogaria/rest/fabricante
		
		@PUT
		public String editar(String json) {

			Gson gson = new Gson();
			Fabricante fabricante = gson.fromJson(json, Fabricante.class);
			
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricanteDao.editar(fabricante);
			
			String jsonSaida = gson.toJson(fabricante);
			return jsonSaida;

		}
		
		@DELETE
		public String excluir(String json) {
			
			Gson gson = new Gson();
			Fabricante fabricante = gson.fromJson(json, Fabricante.class);
			
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricante = fabricanteDao.Buscar(fabricante.getCodigo());
			fabricanteDao.excluir(fabricante);
			
			String jsonSaida = gson.toJson(fabricante);
			return jsonSaida;
		}

}
