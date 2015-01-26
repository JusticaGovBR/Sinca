package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the doenca database table.
 * 
 */
@Entity
@NamedQuery(name="Doenca.findAll", query="SELECT d FROM Doenca d")
public class Doenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DOENCA")
	private Long idDoenca;

	@Column(name="COD_CID")
	private String codCid;

	@Column(name="DESC_DOENCA")
	private String descDoenca;

	//bi-directional many-to-one association to DoencaPessoa
	@OneToMany(mappedBy="doenca")
	private List<DoencaPessoa> doencaPessoas;

	public Doenca() {
	}

	public Long getIdDoenca() {
		return this.idDoenca;
	}

	public void setIdDoenca(Long idDoenca) {
		this.idDoenca = idDoenca;
	}

	public String getCodCid() {
		return this.codCid;
	}

	public void setCodCid(String codCid) {
		this.codCid = codCid;
	}

	public String getDescDoenca() {
		return this.descDoenca;
	}

	public void setDescDoenca(String descDoenca) {
		this.descDoenca = descDoenca;
	}

	public List<DoencaPessoa> getDoencaPessoas() {
		return this.doencaPessoas;
	}

	public void setDoencaPessoas(List<DoencaPessoa> doencaPessoas) {
		this.doencaPessoas = doencaPessoas;
	}

	public DoencaPessoa addDoencaPessoa(DoencaPessoa doencaPessoa) {
		getDoencaPessoas().add(doencaPessoa);
		doencaPessoa.setDoenca(this);

		return doencaPessoa;
	}

	public DoencaPessoa removeDoencaPessoa(DoencaPessoa doencaPessoa) {
		getDoencaPessoas().remove(doencaPessoa);
		doencaPessoa.setDoenca(null);

		return doencaPessoa;
	}

}