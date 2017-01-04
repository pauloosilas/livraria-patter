package loja.daos;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import loja.models.Book;

@Dependent
public class BookDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void save(Book product){
		manager.persist(product);
			
	}
}
