package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptor.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // opcional (CMT)
//@TransactionManagement(TransactionManagementType.BEAN) // permite controlar a transação do JPA pelo código (BMT)
//@Interceptors({LogInterceptador.class})
public class AutorDao {
	
	@PersistenceContext
	private EntityManager em;
	
	/*@Inject
	UserTransaction tx;
	/**
	 * Metodo chamado de callBack
	 * Um Session Bean não é compartilhado entre threads
	 * Automaticamente thread safe
	 * Número máximo de instâncias é definido no arquivo standalone.xml do Jboss
	 * No item pools (max-pool-size)
	 */
	@PostConstruct
	void aposCriacao() {
		System.out.println("AutorDao foi criado");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED) //opcional (padrão)
	//@TransactionAttribute(TransactionAttributeType.MANDATORY) //
	public void salva(Autor autor) {
		
//		long currentTimeMillis = System.currentTimeMillis();
		
		System.out.println("Salvando o autor: " + autor.getNome());
		
		/*try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		em.persist(autor);
		
		System.out.println("Autor " + autor.getNome() + " foi salvo");
		
		//throw new RuntimeErrorException(null, "Serviço externo deu erro!");
		
//		System.out.println("Tempo gasto" + (System.currentTimeMillis() - currentTimeMillis));
	} 
	
	public List<Autor> todosAutores() {
		return em.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.em.find(Autor.class, autorId);
		return autor;
	}
	
}
