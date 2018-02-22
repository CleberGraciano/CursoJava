package br.pro.delfino.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.pro.delfino.drogaria.dao.FabricanteDao;
import br.pro.delfino.drogaria.dao.ProdutoDao;
import br.pro.delfino.drogaria.domain.Fabricante;
import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private Produto produto;

	private List<Fabricante> fabricantes;

	private List<Produto> produtos;

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void novo() {

		try {

			produto = new Produto();
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo Produto");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {

		try {

			ProdutoDao produtoDao = new ProdutoDao();
			produtos = produtoDao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Produtos");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {

			if (produto.getCaminho() == null) {

				Messages.addGlobalError("A campo foto é obrigatório");
				return;
			}

			ProdutoDao produtoDao = new ProdutoDao();
			Produto produtoRetorno = produtoDao.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("C:/Users/Usuario/Desktop/Sistema Drograria Curso/Uploads/Produtos/"
					+ produtoRetorno.getCodigo() + ".jpg");

			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			produto = new Produto();

			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();

			produtos = produtoDao.listar();

			Messages.addGlobalInfo("Produto Salvo com sucesso");

		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar Salvar o Produto");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDao produtoDao = new ProdutoDao();
			produtoDao.excluir(produto);

			Path arquivo = Paths.get("C:/Users/Usuario/Desktop/Sistema Drograria Curso/Uploads/Produtos/"
					+ produto.getCodigo() + ".jpg");

			Files.deleteIfExists(arquivo);

			produtos = produtoDao.listar();

			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (RuntimeException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o Produto");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {

			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			produto.setCaminho("C:/Users/Usuario/Desktop/Sistema Drograria Curso/Uploads/Produtos/"
					+ produto.getCodigo() + ".jpg");

			FabricanteDao fabricanteDao = new FabricanteDao();

			fabricantes = fabricanteDao.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma Produto");

			erro.printStackTrace();
		}

	}

	public void upload(FileUploadEvent evento) {
		try {

			UploadedFile arquivoUpload = evento.getFile();

			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());

			Messages.addGlobalInfo("Upload realizado com sucesso");

		} catch (IOException erro) {
			Messages.addGlobalError("Erro ao fazer upload do arquivo");
			erro.printStackTrace();
		}

	}

	public void imprimir() {

		// Relatorio relatorio = new Relatorio();
		// relatorio.getRelatorio(produtos);

		DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");

		Map<String, Object> filtros = tabela.getFilters();

		String proDescricao = (String) filtros.get("descricao");
		String fabDescricao = (String) filtros.get("fabricante.descricao");

		String caminho = Faces.getRealPath("/reports/");

		Map<String, Object> parametros = new HashMap<>();

		if (proDescricao == null) {

			parametros.put("PRODUTO_DESCRICAO", "%%");

		} else {

			parametros.put("PRODUTO_DESCRICAO", "%" + proDescricao + "%");
		}

		if (fabDescricao == null) {

			parametros.put("FABRICANTE_DESCRICAO", "%%");

		} else {

			parametros.put("FABRICANTE_DESCRICAO", "%" + fabDescricao + "%");
		}

		Connection conexao = HibernateUtil.getConexao();

		try {

			JasperPrint relatorio = JasperFillManager.fillReport(caminho + "produtos.jasper", parametros, conexao);
			byte[] bytes = JasperExportManager.exportReportToPdf(relatorio);

			if (bytes != null) {

				FacesContext.getCurrentInstance().renderResponse();

				HttpServletResponse response = getResponse();
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "inline;filename=produtos.pdf");
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				response.flushBuffer();
				responseComplete();

			}

			// JasperExportManager.exportReportToPdfFile(relatorio,caminho+"produtos.pdf");

			// JasperPrintManager.printReport(relatorio, true);

		} catch (JRException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");

			erro.printStackTrace();
		} finally {
			try {
				if (conexao.isClosed()) {
					System.out.println("fechada");

				} else {

					System.out.println("aberta");
					conexao.close();

					if (conexao.isClosed()) {
						System.out.println("fechada");

					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	public static HttpServletResponse getResponse() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse resp = (HttpServletResponse) externalContext.getResponse();
		return resp;
	}

	/**
	 * Método para gerar de relatórios
	 * 
	 * @return
	 */
	public static void responseComplete() {
		FacesContext.getCurrentInstance().renderResponse();
		FacesContext.getCurrentInstance().responseComplete();

	}
}
