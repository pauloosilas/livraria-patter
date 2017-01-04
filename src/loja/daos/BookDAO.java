package loja.daos;

import java.util.List;

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
	
	public List<Book> list(){
		return manager.createQuery("select b from Book b", Book.class)
				.getResultList();
				
	}
}
