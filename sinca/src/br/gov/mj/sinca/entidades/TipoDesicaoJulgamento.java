package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_desicao_julgamento database table.
 * 
 */
@Entity
@Table(name="tipo_desicao_julgamento")
@NamedQuery(name="TipoDesicaoJulgamento.findAll", query="SELECT t FROM TipoDesicaoJulgamento t")
public class TipoDesicaoJulgamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO_DESICAO")
	private int codTipoDesicao;

	private String decricao;

	//bi-directional many-to-one association to JulgamentoProcesso
	@OneToMany(mappedBy="tipoDesicaoJulgamento")
	private List<JulgamentoProcesso> julgamentoProcessos;

	public TipoDesicaoJulgamento() {
	}

	public int getCodTipoDesicao() {
		return this.codTipoDesicao;
	}

	public void setCodTipoDesicao(int codTipoDesicao) {
		this.codTipoDesicao = codTipoDesicao;
	}

	public String getDecricao() {
		return this.decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

	public List<JulgamentoProcesso> getJulgamentoProcessos() {
		return this.julgamentoProcessos;
	}

	public void setJulgamentoProcessos(List<JulgamentoProcesso> julgamentoProcessos) {
		this.julgamentoProcessos = julgamentoProcessos;
	}

	public JulgamentoProcesso addJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
		getJulgamentoProcessos().add(julgamentoProcesso);
		julgamentoProcesso.setTipoDesicaoJulgamento(this);

		return julgamentoProcesso;
	}

	public JulgamentoProcesso removeJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
		getJulgamentoProcessos().remove(julgamentoProcesso);
		julgamentoProcesso.setTipoDesicaoJulgamento(null);

		return julgamentoProcesso;
	}

}