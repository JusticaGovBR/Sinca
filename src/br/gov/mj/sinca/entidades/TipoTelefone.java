package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_telefone database table.
 * 
 */
@Entity
@Table(name="tipo_telefone")
@NamedQuery(name="TipoTelefone.findAll", query="SELECT t FROM TipoTelefone t")
public class TipoTelefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO")
	private Integer codTipo;

	private String descricao;

	//bi-directional many-to-one association to TelefonePessoa
	@OneToMany(mappedBy="tipoTelefone")
	private List<TelefonePessoa> telefonePessoas;

	public TipoTelefone() {
	}

	public TipoTelefone(Integer codTipo) {
	    this.codTipo = codTipo;
	}

	public Integer getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<TelefonePessoa> getTelefonePessoas() {
		return this.telefonePessoas;
	}

	public void setTelefonePessoas(List<TelefonePessoa> telefonePessoas) {
		this.telefonePessoas = telefonePessoas;
	}

	public TelefonePessoa addTelefonePessoa(TelefonePessoa telefonePessoa) {
		getTelefonePessoas().add(telefonePessoa);
		telefonePessoa.setTipoTelefone(this);

		return telefonePessoa;
	}

	public TelefonePessoa removeTelefonePessoa(TelefonePessoa telefonePessoa) {
		getTelefonePessoas().remove(telefonePessoa);
		telefonePessoa.setTipoTelefone(null);

		return telefonePessoa;
	}

}