package next.school.cesar.spring_aula6.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import next.school.cesar.spring_aula6.entities.AuthorEntity;
import next.school.cesar.spring_aula6.entities.BookEntity;
import next.school.cesar.spring_aula6.entities.PublisherEntity;
import next.school.cesar.spring_aula6.repositories.AuthorRepository;
import next.school.cesar.spring_aula6.repositories.BookRepository;
import next.school.cesar.spring_aula6.repositories.PublisherRepository;

@RestController 
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	
    @PostMapping("/incluirBook")
    public BookEntity incluir(@RequestBody BookEntity book) {
        // Extrair o UUID do Publisher
        UUID publisherId = book.getPublisher().getId();
        // Buscar a entidade Publisher usando o UUID
        Optional<PublisherEntity> optionalPublisher = publisherRepository.findById(publisherId);
        if (optionalPublisher.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editora não encontrada");
        }
        PublisherEntity publisher = optionalPublisher.get();
        // Definir o Publisher encontrado no Book
        book.setPublisher(publisher); 
        
        // Buscar author
        Set<UUID> authorIds = book.getAuthors().stream().map(AuthorEntity::getId).collect(Collectors.toSet());
        Set<AuthorEntity> authors = authorIds.stream()
            .map(id -> authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado: " + id)))
            .collect(Collectors.toSet());
        
        book.setAuthors(authors);
           
        // Salvar a entidade Book no banco de dados
        BookEntity bookCompleto = bookRepository.save(book);
        return bookCompleto;
    }
	
	@GetMapping("/buscarBook/{id}")
	public BookEntity buscar(@PathVariable UUID id) {
		Optional<BookEntity> retorno = bookRepository.findById(id);
		boolean estaVazio = retorno.isEmpty();
		if (estaVazio) {
			return null;
		} else {
			BookEntity entidadeBuscada = retorno.get();
			return entidadeBuscada; 
		}
	}
	
	
	@PutMapping("/alterarBook") 
	public BookEntity alterar (@RequestBody BookEntity book) {
		System.out.println(book.getId());
		System.out.println(book.getTitle());
		BookEntity bookCompleto = bookRepository.save(book);
		return bookCompleto;  	                           
	}
	
	
	@DeleteMapping("/excluirBook/{id}")
	public void excluir (@PathVariable UUID id) {
		bookRepository.deleteById(id);
	}
	
	//listAll
	@GetMapping("/listBooks")
	public List<BookEntity> getById() {
		List<BookEntity> books;
		books = bookRepository.findAll();
		
		return books;
	}

}
