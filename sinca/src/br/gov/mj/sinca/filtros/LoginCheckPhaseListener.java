package br.gov.mj.sinca.filtros;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@SuppressWarnings("serial")
public class LoginCheckPhaseListener implements PhaseListener
{

	// CICLO DE VIDA DO JSF
	// 1. Restore view
	// 2. Apply request values; process events
	// 3. Process validations; process events
	// 4. Update model values; process events
	// 5. Invoke application; process events
	// 6. Render response

	public PhaseId getPhaseId()
	{
		return PhaseId.RESTORE_VIEW; // executar na primeira fase (início do
												// processamento)
	}

	public void afterPhase(PhaseEvent event)
	{
		boolean usuarioAutenticado = false;
//		LoginMB loginMB = (LoginMB) JSFUtil.getVariavelApplication("loginMB");
//
//		if (loginMB != null)
//			usuarioAutenticado = loginMB.isAutenticado();

		// ------------------------------------
		FacesContext contexto = event.getFacesContext();

		// Check to see if they are on the login page.
		boolean estaNaPaginaDeLogin = contexto.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true : false;
		if (!estaNaPaginaDeLogin)
			estaNaPaginaDeLogin = contexto.getViewRoot().getViewId().lastIndexOf("_expirado") > -1 ? true : false;

		if (!estaNaPaginaDeLogin && !usuarioAutenticado)
		{
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "_expirado.jsf");
		}

	}

	public void beforePhase(PhaseEvent event)
	{

	}

}