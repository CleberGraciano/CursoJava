package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;

public class EstadoDaoTest {
	@Test

	public void salvar() {

		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDao estadoDao = new EstadoDao();

		estadoDao.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDao estadoDao = new EstadoDao();
		List<Estado> resultado = estadoDao.listar();

		System.out.println("Total de Registros encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.Buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum Registro Encontrado");
		} else {
			System.out.println("Registro Encontrado");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

		}
	}

	@Test
	@Ignore
	public void excuir() {
		Long codigo = 1L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.Buscar(codigo);
		if (estado == null) {
			System.out.println("Registro Inexistente ");
		} else {
			estadoDao.excluir(estado);
			System.out.println("Registro excluido com sucesso");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

		}

	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 3L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.Buscar(codigo);

		if (estado == null) {
			System.out.println("Registro não existe");
		} else {
			System.out.println("Registro Antes ");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
			estado.setSigla("RS");
			estadoDao.editar(estado);
			System.out.println("Registro Alterado  ");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}

	}

}
