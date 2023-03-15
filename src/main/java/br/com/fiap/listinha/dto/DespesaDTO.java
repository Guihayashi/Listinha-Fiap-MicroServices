package br.com.fiap.listinha.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
@Document(collection = "Despesas")
public class DespesaDTO {

	@Id
	private String id;
	private String name;
	private String categoria;
	private String status;
	private BigDecimal valor;
	private Date dataVencimento;
	private String descricao;
	@CreatedDate
	private Date createdDate;

	@LastModifiedDate
	private Date lastModifiedDate;

	public String getId() {return id;}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public DespesaDTO criarId() {
		setId(new ObjectId().toString());
		return this;
	}

	public DespesaDTO() {}

	public DespesaDTO(String name, String categoria, String status, BigDecimal valor, Date dataVencimento, String descricao) {
		this.name = name;
		this.categoria = categoria;
		this.status = status;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.descricao = descricao;
	}

}
