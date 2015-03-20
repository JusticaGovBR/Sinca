package br.gov.mj.sinca.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.mj.sinca.dao.UsuarioDAO;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.util.StringUtil;

@FacesConverter(value = "usuarioConverter", forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	Usuario pessoa = new Usuario();
	try {
	    if (StringUtil.ehInteiro(value)) {
		pessoa = new UsuarioDAO().lerPorId(Long.parseLong(value));
	    } else {
		pessoa.setDescUsuario(StringUtil.lowerCaseFirstLetter(value));
	    }
	} catch (Exception e) {
	}
	return pessoa;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
	Usuario c = (Usuario) value;
	if (c == null || c.getIdUsuario() == null) {
	    return "";
	}
	return String.valueOf(c.getIdUsuario());
    }

}