import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.gov.mj.sinca.entidades.Pessoa;

public class Teste {
    public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sincaEM");
	EntityManager em = factory.createEntityManager();
	em.getTransaction().begin();
	Pessoa pessoa = em.find(Pessoa.class,89l);
	System.out.println(pessoa.getNomePessoa());
	em.getTransaction().commit();
    }
}
