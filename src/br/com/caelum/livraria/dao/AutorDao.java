package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorDao {
	
	@PersistenceContext
	private EntityManager em;
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
	
	public void salva(Autor autor) {
		System.out.println("Salvando o autor: " + autor.getNome());
		
		/*try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		em.persist(autor);
		
		System.out.println("Autor " + autor.getNome() + " foi salvo");
	} 
	
	public List<Autor> todosAutores() {
		return em.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.em.find(Autor.class, autorId);
		return autor;
	}
	
}
