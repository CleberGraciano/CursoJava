package br.pro.delfino.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;

public class ProdutoDaoTest {

	@Test
	
	public void salvar() {
		Long codigoFabricante = 1L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(codigoFabricante);

		Produto produto = new Produto();
		produto.setDescricao("Cataflan 50mg com 20 comprimidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));

		ProdutoDao produtoDao = new ProdutoDao();
		produtoDao.salvar(produto);
		System.out.println("Produto Salvo com sucesso");

	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> resultado = produtoDao.listar();

		System.out.println("O total de Produtos Cadastrados é: "+resultado.size());

		for (Produto produto : resultado) {
			System.out.println("Codigo Produto: " + produto.getCodigo() + " - Descrição: " + produto.getDescricao()
					+ " - Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco()
					+ " - Quantidade: " + produto.getQuantidade());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.Buscar(codigo);

		if (produto == null) {
			System.out.println("Produto não existe ");
		} else {
			System.out.println("Codigo Produto: " + produto.getCodigo() + " - Descrição: " + produto.getDescricao()
					+ " - Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco()
					+ " - Quantidade: " + produto.getQuantidade());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.Buscar(codigo);

		if (produto == null) {
			System.out.println("Produto não encontado");
		} else {

			produtoDao.excluir(produto);
			System.out.println("Registro excluido com sucesso");
			System.out.println("Codigo Produto: " + produto.getCodigo() + " - Descrição: " + produto.getDescricao()
					+ " - Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco()
					+ " - Quantidade: " + produto.getQuantidade());

		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoFabricante = 3L;
		Long codigoProduto = 2L;

		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.Buscar(codigoFabricante);

		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.Buscar(codigoProduto);

		System.out.println("Produto a ser Editado");
		System.out.println("Codigo Produto: " + produto.getCodigo() + " - Descrição: " + produto.getDescricao()
				+ " - Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco()
				+ " - Quantidade: " + produto.getQuantidade());

		produto.setDescricao("Dipirona");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("2.50"));
		produto.setQuantidade(new Short("2"));

		produtoDao.editar(produto);
		System.out.println("Produto Editado com sucesso");
		System.out.println("");

		System.out.println("Codigo Produto: " + produto.getCodigo() + " - Descrição: " + produto.getDescricao()
				+ " - Fabricante: " + produto.getFabricante().getDescricao() + " - Preço: " + produto.getPreco()
				+ " - Quantidade: " + produto.getQuantidade());

	}

}
