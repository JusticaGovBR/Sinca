package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_documento database table.
 * 
 */
@Entity
@Table(name="tipo_documento")
@NamedQuery(name="TipoDocumento.findAll", query="SELECT t FROM TipoDocumento t")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_DOCUMENTO")
	private Integer idTipoDocumento;

	private String descricao;

	//bi-directional many-to-one association to DocumentoPessoa
	@OneToMany(mappedBy="tipoDocumento")
	private List<DocumentoPessoa> documentoPessoas;

	public TipoDocumento() {
	}

	public Integer getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<DocumentoPessoa> getDocumentoPessoas() {
		return this.documentoPessoas;
	}

	public void setDocumentoPessoas(List<DocumentoPessoa> documentoPessoas) {
		this.documentoPessoas = documentoPessoas;
	}

	public DocumentoPessoa addDocumentoPessoa(DocumentoPessoa documentoPessoa) {
		getDocumentoPessoas().add(documentoPessoa);
		documentoPessoa.setTipoDocumento(this);

		return documentoPessoa;
	}

	public DocumentoPessoa removeDocumentoPessoa(DocumentoPessoa documentoPessoa) {
		getDocumentoPessoas().remove(documentoPessoa);
		documentoPessoa.setTipoDocumento(null);

		return documentoPessoa;
	}

}