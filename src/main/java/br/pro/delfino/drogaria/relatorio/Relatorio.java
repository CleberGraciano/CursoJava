package br.pro.delfino.drogaria.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.pro.delfino.drogaria.domain.Produto;
import br.pro.delfino.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Relatorio {
	
	private HttpServletResponse response;
	private FacesContext context;
	private ByteArrayOutputStream baos;
	private InputStream stream;
	
	public Relatorio() {
		this.context = FacesContext.getCurrentInstance();
		this.response = (HttpServletResponse) context.getExternalContext().getResponse();
		
	}
	
	public Connection getConexao() {
		
		
		return HibernateUtil.getConexao();
		
		
		
	}
	
	public void getRelatorio (List <Produto> produto) {
		
		DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
		
		Map<String, Object> filtros = tabela.getFilters();
		
		String proDescricao = (String) filtros.get("descricao");
		String fabDescricao = (String) filtros.get("fabricante.descricao");
		
		// String caminho = Faces.getRealPath("/reports/");
		
		//stream = this.getClass().getResourceAsStream("/reports/produtos.jasper");
		
		
		
				
		Map<String, Object> parametros = new HashMap<>();
		
		if(proDescricao == null) {
			
			parametros.put("PRODUTO_DESCRICAO", "%%");
			
		} else {
			
			parametros.put("PRODUTO_DESCRICAO","%" + proDescricao + "%");
		}
		
		if(fabDescricao == null) {
			
			parametros.put("FABRICANTE_DESCRICAO", "%%");
			
		} else {
			
			parametros.put("FABRICANTE_DESCRICAO", "%" + fabDescricao + "%" );
		}
		
		
		
		baos = new ByteArrayOutputStream();
		
		try {
		
		JasperReport report = (JasperReport) JRLoader.loadObject(stream);
		 
		JasperPrint print = JasperFillManager.fillReport(report, parametros, getConexao());
		
		JasperExportManager.exportReportToPdfStream(print, baos);
		
		response.reset();
		
		response.setContentType("application/pdf");
		
		response.setContentLength(baos.size());
		
		response.setHeader("Content-disposition", "inline; filename=produtos.pdf");
		
		response.getOutputStream().write(baos.toByteArray());
		
		response.getOutputStream().flush();
		
		response.getOutputStream().close();
		
		context.responseComplete();
		 
		
		
		 
		 
		 
		 //JasperPrintManager.printReport(relatorio, true);
		// JasperExportManager.exportReportToPdfFile(relatorio ,caminho+"produtos.pdf");
		//JasperPrintManager.printReport(relatorio, true);
		// JasperExportManager.exportReportToPdfFile(relatorio,caminho+"produtos.pdf");
		//JasperFillManager.fillReportToFile(caminho+"produtos.jasper", parametros, conexao); 
		//JasperExportManager.exportReportToPdfFile(caminho+"produtos.jrxml");
		//net.sf.jasperreports.view.JasperViewer.viewReport(caminho+"produtos.pdf", false); 
		
		 
		
		
		}catch (JRException | IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
				
			erro.printStackTrace();
		}
		
	}
	
	

}
