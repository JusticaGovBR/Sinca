import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.gov.mj.sinca.entidades.PessoaFisica;

public class Teste {
    public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sincaEM");
	EntityManager em = factory.createEntityManager();
	em.getTransaction().begin();
	PessoaFisica pessoa = em.find(PessoaFisica.class,89l);
	System.out.println(pessoa.getNomePessoa());
	em.getTransaction().commit();
    }
}
