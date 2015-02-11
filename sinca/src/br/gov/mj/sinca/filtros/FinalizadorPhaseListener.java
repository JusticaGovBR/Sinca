package br.gov.mj.sinca.filtros;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.gov.mj.sinca.dao.JPAUtil;

public class FinalizadorPhaseListener implements PhaseListener
{

	// CICLO DE VIDA DO JSF
	// 1. Restore view
	// 2. Apply request values; process events
	// 3. Process validations; process events
	// 4. Update model values; process events
	// 5. Invoke application; process events
	// 6. Render response

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PhaseId getPhaseId()
	{
		return PhaseId.RENDER_RESPONSE; // executar na última fase (final do
													// processamento)
	}

	
	public void afterPhase(PhaseEvent event)
	{
		// liberar o objeto armazenado no Cache da JPAUtil
	    System.out.println("Limpando cache...");
		JPAUtil.limparCacheEntityManager();
	}

	
	public void beforePhase(PhaseEvent event)
	{

	}

}