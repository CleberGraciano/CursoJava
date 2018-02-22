package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Venda;

public class VendaDaoTest {

	@Test
	@Ignore
	public void salvar() throws ParseException {

		Long codigoCliente = 1L;
		Long codigoFuncionario = 1L;

		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.Buscar(codigoCliente);

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.Buscar(codigoFuncionario);

		Venda venda = new Venda();

		venda.setHorario(new SimpleDateFormat("HH:mm:ss").parse("15:30:02"));
		venda.setValorTotal(new BigDecimal("225.50"));
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);

		VendaDao vendaDao = new VendaDao();
		vendaDao.salvar(venda);

	}

	@Test
	@Ignore
	public void listar() {

		VendaDao vendaDao = new VendaDao();
		List<Venda> resultado = vendaDao.listar();

		System.out.println("O total de Vendas  é: " + resultado.size());

		for (Venda venda : resultado) {
			System.out.println("Codigo: " + venda.getCodigo() + " - Horario: " + venda.getHorario() + " - Valor Total: "
					+ venda.getValorTotal() + " - Cliente: " + venda.getCliente().getPessoa().getNome()
					+ " - Funcionario: " + venda.getFuncionario().getPessoa().getNome());

		}

	}

	@Test
	@Ignore
	public void buscar() {

		Long codigo = 10L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.Buscar(codigo);

		if (venda == null) {
			System.out.println("Venda Não Cadastrada");
		} else {
			System.out.println("Codigo: " + venda.getCodigo() + " - Horario: " + venda.getHorario() + " - Valor Total: "
					+ venda.getValorTotal() + " - Cliente: " + venda.getCliente().getPessoa().getNome()
					+ " - Funcionario: " + venda.getFuncionario().getPessoa().getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {

		Long codigo = 10L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.Buscar(codigo);

		if (venda == null) {
			System.out.println("Venda não encontrada!");
		} else {
			vendaDao.excluir(venda);
			System.out.println("Venda Excluida com sucesso!");

			System.out.println("Codigo: " + venda.getCodigo() + " - Horario: " + venda.getHorario() + " - Valor Total: "
					+ venda.getValorTotal() + " - Cliente: " + venda.getCliente().getPessoa().getNome()
					+ " - Funcionario: " + venda.getFuncionario().getPessoa().getNome());

		}

	}

	@Test
	@Ignore
	public void editar() throws ParseException {
		Long codigoVenda = 1L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.Buscar(codigoVenda);

		Long codigoCliente = 1L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.Buscar(codigoCliente);

		Long codigoFuncionario = 1L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.Buscar(codigoFuncionario);

		System.out.println("Venda a ser alterado");

		System.out.println("Codigo: " + venda.getCodigo() + " - Horario: " + venda.getHorario() + " - Valor Total: "
				+ venda.getValorTotal() + " - Cliente: " + venda.getCliente().getPessoa().getNome() + " - Funcionario: "
				+ venda.getFuncionario().getPessoa().getNome());

		venda.setHorario(new SimpleDateFormat("HH:mm:ss").parse("15:30:02"));
		venda.setValorTotal(new BigDecimal("225.50"));
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);

		vendaDao.editar(venda);

		System.out.println("\n Venda  Alterado");

		System.out.println("Codigo: " + venda.getCodigo() + " - Horario: " + venda.getHorario() + " - Valor Total: "
				+ venda.getValorTotal() + " - Cliente: " + venda.getCliente().getPessoa().getNome() + " - Funcionario: "
				+ venda.getFuncionario().getPessoa().getNome());

	}

}
