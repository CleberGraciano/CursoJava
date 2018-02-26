package br.pro.delfino.drogaria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.ProdutoDao;
import br.pro.delfino.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable {
	
	private Produto produto;
	private Boolean exibePainelDados;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}

	@PostConstruct
	public void novo() {
		produto = new Produto();
		exibePainelDados = false;
	}
	
	public void buscar() {
		try {
			
			ProdutoDao produtoDao = new ProdutoDao();
			
			Produto resultado = produtoDao.Buscar(produto.getCodigo());
			
			if(resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Não existe Produto cadastrado para o código informado");
				
			}else {
				
				exibePainelDados = true;
				produto = resultado;
				
			}
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}

}
