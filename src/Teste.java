import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.ReparacaoMoralAnalise;
import br.gov.mj.sinca.entidades.ReparacaoMoralJulgamento;

public class Teste {
    public static void main2(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sincaEM");
	EntityManager em = factory.createEntityManager();
	em.getTransaction().begin();
	PessoaFisica pessoa = em.find(PessoaFisica.class,89l);
	System.out.println(pessoa.getNomePessoa());
	em.getTransaction().commit();
    }
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
	ReparacaoMoralAnalise moralAnalise = new ReparacaoMoralAnalise();
	moralAnalise.setDeclaracaoRetificacao("TESTE MORAL ANALISE");
	ReparacaoMoralJulgamento moralJulgamento = new ReparacaoMoralJulgamento();
	
	for(Method methodAnalise : moralAnalise.getClass().getMethods()){
	    for(Method methodJulgamento : moralJulgamento.getClass().getMethods()){
		if(methodAnalise.getName().contains("get") && methodAnalise.getName().equals(methodJulgamento.getName())){
		    methodJulgamento.invoke(methodAnalise.getDefaultValue());
		}
	    }
	}
	
    }
    
}
