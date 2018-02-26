package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain {
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	@Column(nullable =  false, precision = 7, scale = 2)
	private BigDecimal valorTotal;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
	
	
	
	
	

}
