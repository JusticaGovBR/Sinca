package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_endereco database table.
 * 
 */
@Entity
@Table(name="tipo_endereco")
@NamedQuery(name="TipoEndereco.findAll", query="SELECT t FROM TipoEndereco t")
public class TipoEndereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_ENDERECO", unique=true, nullable=false)
	private Integer idTipoEndereco;

	@Column(nullable=false, length=200)
	private String descricao;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="tipoEndereco")
	private List<Endereco> enderecos;

	public TipoEndereco() {
	}

	public Integer getIdTipoEndereco() {
		return this.idTipoEndereco;
	}

	public void setIdTipoEndereco(Integer idTipoEndereco) {
		this.idTipoEndereco = idTipoEndereco;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setTipoEndereco(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setTipoEndereco(null);

		return endereco;
	}

}