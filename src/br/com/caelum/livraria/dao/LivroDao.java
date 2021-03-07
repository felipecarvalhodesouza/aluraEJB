package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDao {

//	private Banco banco = new Banco();
	
	@PersistenceContext
	private EntityManager em;
	
	public void salva(Livro livro) {
//		banco.save(livro);
		em.persist(livro);
	}
	
	public List<Livro> todosLivros() {
//		return banco.listaLivros();		
		return em.createQuery("select l from Livro l", Livro.class).getResultList();
	}
	
	public List<Livro> getLivrosPeloNome(String nome) {
		
		TypedQuery<Livro> query = this.em.createQuery("select l from Livro l where l.titulo like :pTitulo", Livro.class);
		query.setParameter("pTitulo", "%" + nome + "%");

		return query.getResultList();
	}
	
}
