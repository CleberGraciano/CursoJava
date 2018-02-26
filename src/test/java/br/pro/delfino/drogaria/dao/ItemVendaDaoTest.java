package br.pro.delfino.drogaria.dao;


import java.math.BigDecimal;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.pro.delfino.drogaria.domain.ItemVenda;
import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.domain.Venda;

public class ItemVendaDaoTest {

	@Test
	@Ignore
	public void salvar() {
		Long codigoProduto = 1L;
		Long codigoVenda = 1L;
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.Buscar(codigoProduto);
		
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.Buscar(codigoVenda);
		
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setQuantidade(new Short("5"));
		itemVenda.setValorParcial(new BigDecimal("5.25"));
		itemVenda.setProduto(produto);
		itemVenda.setVenda(venda);
		
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		itemVendaDao.salvar(itemVenda);
		
		
	}
	@Test
	@Ignore
	public void listar() {
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		List<ItemVenda> resultado = itemVendaDao.listar();
		
		System.out.println("O total de Itens de Vendaé: "+resultado.size());
		
		for(ItemVenda itemVenda : resultado) {
			
			System.out.println("Codigo: "+itemVenda.getCodigo()+" - Quantidade: "+itemVenda.getQuantidade()+" - Valor Parcial: "+itemVenda.getValorParcial()+" - Produto: "+itemVenda.getProduto().getDescricao()+" - Venda: "+itemVenda.getVenda().getCodigo());
			
		}
	}
	@Test
	@Ignore
	public void buscar ( ) {
		Long codigo = 1L;
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		ItemVenda itemVenda = itemVendaDao.Buscar(codigo);
		

		if (itemVenda == null) {
			System.out.println("ItemVenda Não Cadastrada");
		} else {
			System.out.println("Codigo: "+itemVenda.getCodigo()+
					" - Quantidade: "+itemVenda.getQuantidade()+
					" - Valor Parcial: "+itemVenda.getValorParcial()+
					" - Produto: "+itemVenda.getProduto().getDescricao()+
					" - Venda: "+itemVenda.getVenda().getCodigo());
		}
	}
	@Test
	@Ignore
	public void excluir() {
		
		Long codigo = 1L;
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		ItemVenda itemVenda = itemVendaDao.Buscar(codigo);

		if (itemVenda == null) {
			System.out.println("Item Venda não encontrada!");
		} else {
			itemVendaDao.excluir(itemVenda);
			System.out.println("Item Venda Excluida com sucesso!");

			System.out.println("Codigo: "+itemVenda.getCodigo()+
					" - Quantidade: "+itemVenda.getQuantidade()+
					" - Valor Parcial: "+itemVenda.getValorParcial()+
					" - Produto: "+itemVenda.getProduto().getDescricao()+
					" - Venda: "+itemVenda.getVenda().getCodigo());

		}
		
	}
	
	@Test
	public void editar() {
		
		
		Long codigo = 1L;
		Long codigoProduto = 1L;
		Long codigoVenda = 1L;
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produto = produtoDao.Buscar(codigoProduto);
		
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.Buscar(codigoVenda);
	
		ItemVendaDao itemVendaDao = new ItemVendaDao();
		ItemVenda itemVenda = itemVendaDao.Buscar(codigo);

		
		

		System.out.println("Item Venda a ser alterado");

		System.out.println("Codigo: "+itemVenda.getCodigo()+
				" - Quantidade: "+itemVenda.getQuantidade()+
				" - Valor Parcial: "+itemVenda.getValorParcial()+
				" - Produto: "+itemVenda.getProduto().getDescricao()+
				" - Venda: "+itemVenda.getVenda().getCodigo());
		

		itemVenda.setQuantidade(new Short("5"));
		itemVenda.setValorParcial(new BigDecimal("5.25"));
		itemVenda.setProduto(produto);
		itemVenda.setVenda(venda);

		itemVendaDao.editar(itemVenda);

		System.out.println("\n Item Venda  Alterado com sucesso");

		System.out.println("Codigo: "+itemVenda.getCodigo()+
				" - Quantidade: "+itemVenda.getQuantidade()+
				" - Valor Parcial: "+itemVenda.getValorParcial()+
				" - Produto: "+itemVenda.getProduto().getDescricao()+
				" - Venda: "+itemVenda.getVenda().getCodigo());

	}
	
}
