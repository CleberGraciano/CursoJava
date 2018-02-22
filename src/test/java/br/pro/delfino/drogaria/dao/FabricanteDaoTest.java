package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Fabricante;

public class FabricanteDaoTest {

	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();

		fabricante.setDescricao("Distribuidor de Comprimidos para dor de Cabeça");

		FabricanteDao fabricanteDao = new FabricanteDao();

		fabricanteDao.salvar(fabricante);

	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDao fabricanteDao = new FabricanteDao();
		List<Fabricante> resultado = fabricanteDao.listar();

		System.out.println("O total de Registros e: " + resultado.size());
		for (Fabricante fabricante : resultado) {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(codigo);

		if (fabricante == null) {
			System.out.println("Fabricante não encontrado!");
		} else {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(codigo);

		if (fabricante == null) {
			System.out.println("Registro não encontrado!");
		} else {
			fabricanteDao.excluir(fabricante);
			System.out.println("Registro Excluido com sucesso!");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());

		}

	}
	@Test
	@Ignore
	public void editar() {
		Long codigo = 3L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(codigo);

		if (fabricante == null) {
			System.out.println("Registro não encontrado");
		}else {
		
		System.out.println("Registro Anterior");
		System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		
		fabricante.setDescricao("Comprimidos para Diabetes");
		fabricanteDao.editar(fabricante);
		
		System.out.println("Registro Alterado");
		System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}
	
	@Test
	public void merge() {
		
		/* Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante A");
		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.merge(fabricante);*/
		
		
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(2L);
		fabricante.setDescricao("Fabricante B");
		fabricanteDao.merge(fabricante);

	}

}
