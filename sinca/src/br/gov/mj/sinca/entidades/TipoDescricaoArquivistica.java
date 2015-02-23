package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_descricao_arquivistica database table.
 * 
 */
@Entity
@Table(name="tipo_descricao_arquivistica")
@NamedQuery(name="TipoDescricaoArquivistica.findAll", query="SELECT t FROM TipoDescricaoArquivistica t")
public class TipoDescricaoArquivistica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DESCRICAO_ARQUIVISTICA")
	private int idDescricaoArquivistica;

	private String decricao;

	//bi-directional many-to-one association to ArquivoProcesso
	@OneToMany(mappedBy="tipoDescricaoArquivistica")
	private List<ArquivoProcesso> arquivoProcessos;

	public TipoDescricaoArquivistica() {
	}

	public int getIdDescricaoArquivistica() {
		return this.idDescricaoArquivistica;
	}

	public void setIdDescricaoArquivistica(int idDescricaoArquivistica) {
		this.idDescricaoArquivistica = idDescricaoArquivistica;
	}

	public String getDecricao() {
		return this.decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

	public List<ArquivoProcesso> getArquivoProcessos() {
		return this.arquivoProcessos;
	}

	public void setArquivoProcessos(List<ArquivoProcesso> arquivoProcessos) {
		this.arquivoProcessos = arquivoProcessos;
	}

	public ArquivoProcesso addArquivoProcesso(ArquivoProcesso arquivoProcesso) {
		getArquivoProcessos().add(arquivoProcesso);
		arquivoProcesso.setTipoDescricaoArquivistica(this);

		return arquivoProcesso;
	}

	public ArquivoProcesso removeArquivoProcesso(ArquivoProcesso arquivoProcesso) {
		getArquivoProcessos().remove(arquivoProcesso);
		arquivoProcesso.setTipoDescricaoArquivistica(null);

		return arquivoProcesso;
	}

}