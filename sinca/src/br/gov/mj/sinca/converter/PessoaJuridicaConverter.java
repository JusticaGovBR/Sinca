package br.gov.mj.sinca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.mj.sinca.dao.PessoaJuridicaDAO;
import br.gov.mj.sinca.entidades.PessoaJuridica;
import br.gov.mj.sinca.util.StringUtil;

@FacesConverter(value = "pessoaJuridicaConverter", forClass = PessoaJuridica.class)
public class PessoaJuridicaConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	PessoaJuridica pessoa = new PessoaJuridica();
	try {
	    if (StringUtil.ehInteiro(value)) {
		pessoa = new PessoaJuridicaDAO().lerPorId(Long.parseLong(value));
	    } else {
		pessoa.setNomePessoa(StringUtil.lowerCaseFirstLetter(value));
	    }
	} catch (Exception e) {
	}
	return pessoa;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
	PessoaJuridica c = (PessoaJuridica) value;
	if (c == null || c.getIdPessoaJuridica()== null) {
	    return "";
	}
	return String.valueOf(c.getIdPessoaJuridica());
    }

}