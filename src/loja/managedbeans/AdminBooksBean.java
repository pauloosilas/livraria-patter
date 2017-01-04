package loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import loja.daos.BookDAO;
import loja.models.Author;
import loja.models.Book;

@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	
	private List<Author> authors = new ArrayList<Author>();
	@Inject
	private BookDAO bookDAO = new BookDAO();
	
	@Inject
	private AuthorDAO authorDAO;
	
	
	@PostConstruct
	public void loadObjects(){
		this.authors = authorDAO.getList();
	}
	
	@Transactional
	public void save(){
		populateBookAuthor();
		bookDAO.save(product);
		
	}
	
	private void populateBookAuthor(){
		selectedAuthorsIds.stream().map((id) -> {
			return new Author(id);
		}).forEach(product :: add);
	}
	
	public Book getProduct(){
		return product;
	}
	
	public List<Author> getAuthors(){
		return authors;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}
	
	
}
