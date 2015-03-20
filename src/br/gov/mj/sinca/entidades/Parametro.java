package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Parametro")
@NamedQuery(name = "Parametro.findAll", query = "SELECT t FROM Parametro t")
public class Parametro implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5803884098098130012L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PARAMETRO")
    private Long idParametro;
    
    @Column(name = "NOME_PARAMETRO")
    private String nomeParametro;
    
    @Column(name = "VALOR_PARAMETRO")
    private String valorParametro;

    public Long getIdParametro() {
        return idParametro;
    }

    public String getNomeParametro() {
        return nomeParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
    
    

}
