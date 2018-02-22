package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cidade;
import br.pro.delfino.drogaria.domain.Estado;

public class CidadeDaoTest {

	@Test
	@Ignore
	public void salvar() {
		Long codigoEstado = 1L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.Buscar(codigoEstado);

		if (estado == null) {
			System.out.println("Estado não cadastrado");

		} else {

			Cidade cidade = new Cidade();
			cidade.setNome("Piraju");
			cidade.setEstado(estado);

			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.salvar(cidade);
		}

	}

	@Test
	@Ignore
	public void listar() {

		CidadeDao cidadeDao = new CidadeDao();
		List<Cidade> resultado = cidadeDao.listar();

		System.out.println("O total de Registros e: " + resultado.size());
		for (Cidade cidade : resultado) {
			System.out.println("Codigo: " + cidade.getCodigo() + " Cidade: " + cidade.getNome() + " - Estado: "
					+ cidade.getEstado().getNome() + "/" + cidade.getEstado().getSigla());
			System.out.println("");
		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.Buscar(codigo);

		if (cidade == null) {
			System.out.println("Cidade Não Cadastrada");
		} else {
			System.out.println("Codigo: " + cidade.getCodigo() + " Cidade: " + cidade.getNome() + " - Estado: "
					+ cidade.getEstado().getNome() + "/" + cidade.getEstado().getSigla());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.Buscar(codigo);

		if (cidade == null) {
			System.out.println("Registro não encontrado!");
		} else {
			cidadeDao.excluir(cidade);
			System.out.println("Registro Excluido com sucesso!");
			System.out.println("Codigo: " + cidade.getCodigo() + " Cidade: " + cidade.getNome() + " - Estado: "
					+ cidade.getEstado().getNome() + "/" + cidade.getEstado().getSigla());

		}

	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 1L;
		Long codigoEstado = 6L;
		
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.Buscar(codigoEstado);
		
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.Buscar(codigoCidade);
		
		if((estado == null) || (cidade == null)) {
			System.out.println("Campo cidade ou Estado não existe");
		}else {
		
		System.out.println("Cidade  a ser Editada");
		System.out.println("Codigo: " + cidade.getCodigo() + " Cidade: " + cidade.getNome() + " - Estado: "
				+ cidade.getEstado().getNome() + "/" + cidade.getEstado().getSigla());
		
		cidade.setNome("Piraju");
		cidade.setEstado(estado);
		cidadeDao.editar(cidade);
		
		System.out.println("Cidade  Editada");
		System.out.println("Codigo: " + cidade.getCodigo() + " Cidade: " + cidade.getNome() + " - Estado: "
				+ cidade.getEstado().getNome() + "/" + cidade.getEstado().getSigla());
		}
	}
	
	@Test
	public void buscarPorEstado() {
		
		Long estadoCodigo = 1L;

		CidadeDao cidadeDao = new CidadeDao();
		List<Cidade> resultado = cidadeDao.buscarPorEstado(estadoCodigo);

		System.out.println("O total de Registros e: " + resultado.size());
		for (Cidade cidade : resultado) {
			System.out.println("Codigo: " + cidade.getCodigo() + " Cidade: " + cidade.getNome() + " - Estado: "
					+ cidade.getEstado().getNome() + "/" + cidade.getEstado().getSigla());
			System.out.println("");
		}

	}

}
