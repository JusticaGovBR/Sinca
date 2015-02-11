package br.gov.mj.sinca.mb.julgamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.TipoAnaliseJulgamentoDAO;
import br.gov.mj.sinca.entidades.TipoAnaliseJulgamento;

/**
 * Maneger Beam que realiza as funcionalidades de manter Julgamento do processo
 * de anistia.
 * 
 * @author Sebastiao.Costa Contrato nº:XXXXX
 */
@ManagedBean(name = "manterJulgamentoMB")
@ViewScoped
public class ManterJulgamentoMB implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1007696592419574224L;

    private List<TipoAnaliseJulgamento> listarTipoAnaliseJulgamento = new ArrayList<TipoAnaliseJulgamento>();

    private Integer codTipoAnaliseJug;

    private Logger logger;


    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada :" + this.getClass().getName() + " Init <>  PosConstruct");
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	listarTipoAnaliseJulgamento = new TipoAnaliseJulgamentoDAO().lerTodos();

    }    
    
    public List<TipoAnaliseJulgamento> getListarTipoAnaliseJulgamento() {
	return listarTipoAnaliseJulgamento;
    }

    public void setListarTipoAnaliseJulgamento(List<TipoAnaliseJulgamento> listarTipoAnaliseJulgamento) {
	this.listarTipoAnaliseJulgamento = listarTipoAnaliseJulgamento;
    }

    public Integer getCodTipoAnaliseJug() {
        return codTipoAnaliseJug;
    }

    public void setCodTipoAnaliseJug(Integer codTipoAnaliseJug) {
        this.codTipoAnaliseJug = codTipoAnaliseJug;
    }

}
