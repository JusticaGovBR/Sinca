package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

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
       

}