package br.gov.mj.sinca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.util.StringUtil;

@FacesConverter(value="pessoaConverter",forClass=PessoaFisica.class)
public class PessoaConverter implements Converter {
    
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	PessoaFisica pessoa = new PessoaFisica();
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
    	PessoaFisica c = (PessoaFisica) value;
    	if(c==null || c.getIdPessoa() == null){
    		return "";
    	}
        return String.valueOf( c.getIdPessoa() );
    }

}