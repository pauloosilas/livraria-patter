package loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.transaction.Transactional;

import loja.dao.AuthorDAO;
import loja.daos.BookDAO;
import loja.infra.MessagesHelper;
import loja.models.Author;
import loja.models.Book;

@Model
public class AdminBooksBean {
	
	private Book product = new Book();
	
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	
	private List<Author> authors = new ArrayList<Author>();
	@Inject
	private BookDAO bookDAO;
	
	private List<Book> books = new ArrayList<Book>();
	@Inject
	private AuthorDAO authorDAO;
	
	//@Inject
	//private MessagesHelper messagesHelper;
	
	@PostConstruct
	public void loadObjects(){
		this.books = bookDAO.list();
		this.authors = authorDAO.list();
	}
	
	@Transactional
	public String save(){
		populateBookAuthor();
		bookDAO.save(product);
		clearObjects();
		
		//messagesHelper.addFlash(new FacesMessage("Livro Gravado com Sucesso!"));
		return "/livros/lista?faces-redirect=true";
	}
	
	private void populateBookAuthor(){
		selectedAuthorsIds.stream().map((id) -> {
			return new Author(id);
		}).forEach(product :: add);
	}
	
	public void clearObjects(){
		this.product = new Book();
		this.selectedAuthorsIds.clear();
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
