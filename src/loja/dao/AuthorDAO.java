package loja.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import loja.models.Author;

@Dependent
public class AuthorDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Author> list(){
		return manager.createQuery("select a from Author a", Author.class).getResultList();
	}

}
