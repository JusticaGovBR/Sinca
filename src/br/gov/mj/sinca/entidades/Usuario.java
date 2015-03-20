package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable {
    
	private static final long serialVersionUID = 188787987L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	@Column(name="DESC_USUARIO")
	private String descUsuario;

	@Column(name="SGL_USUARIO")
	private String sglUsuario;

	@Column(name="SENHA")
	private String senha;

	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ULTIMO_ACESSO")
	private Date dataUltimoAcesso;


	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescUsuario() {
		return this.descUsuario;
	}

	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}

	public String getSglUsuario() {
		return this.sglUsuario;
	}

	public void setSglUsuario(String sglUsuario) {
		this.sglUsuario = sglUsuario;
	}

	public boolean senhaCorreta(String senhaDigitada)
	{
		if (this.senha.equals(senhaDigitada)){
			return true;
		}
		else{
			return false;
		}
	}

	public String getSenha() {
	    return senha;
	}

	public void setSenha(String senha) {
	    this.senha = senha;
	}

	public Date getDataCadastro() {
	    return dataCadastro;
	}

	public Date getDataUltimoAcesso() {
	    return dataUltimoAcesso;
	}

	public void setDataCadastro(Date dataCadastro) {
	    this.dataCadastro = dataCadastro;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
	    this.dataUltimoAcesso = dataUltimoAcesso;
	}
       

}