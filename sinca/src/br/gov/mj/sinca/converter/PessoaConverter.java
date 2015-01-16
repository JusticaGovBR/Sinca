package br.gov.mj.sinca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.entidades.Pessoa;
import br.gov.mj.sinca.util.StringUtil;

@FacesConverter(value="pessoaConverter",forClass=Pessoa.class)
public class PessoaConverter implements Converter {
    
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	Pessoa pessoa = new Pessoa();
    	try {
    		if(StringUtil.ehInteiro(value)){
    		  pessoa = new PessoaDAO().lerPorId( Long.parseLong(value) );
    		}else{
    		        pessoa.setNomePessoa(StringUtil.lowerCaseFirstLetter(value));
    		}
		} catch (Exception e) {
		}
        return pessoa;
    }

    
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	Pessoa c = (Pessoa) value;
    	if(c==null || c.getIdPessoa() == null){
    		return "";
    	}
        return String.valueOf( c.getIdPessoa() );
    }

}