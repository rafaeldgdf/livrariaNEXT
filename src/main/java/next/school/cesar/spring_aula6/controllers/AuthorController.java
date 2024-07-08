package next.school.cesar.spring_aula6.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import next.school.cesar.spring_aula6.entities.AuthorEntity;
import next.school.cesar.spring_aula6.entities.PublisherEntity;
import next.school.cesar.spring_aula6.repositories.AuthorRepository;


@RestController 
public class AuthorController {
	
	@Autowired
	private AuthorRepository repository;
	
	@PostMapping("/incluirAuthor") 
	public AuthorEntity incluir (@RequestBody AuthorEntity author) {
		System.out.println(author.getId());
		System.out.println(author.getNome());
		AuthorEntity authorCompleto = repository.save(author);  
		return authorCompleto;
	}
	
	@GetMapping("/buscarAuthor/{id}")
	public AuthorEntity buscar(@PathVariable UUID id) {
		Optional<AuthorEntity> retorno = repository.findById(id);
		boolean estaVazio = retorno.isEmpty();
		if (estaVazio) {
			return null;
		} else {
			AuthorEntity entidadeBuscada = retorno.get();
			return entidadeBuscada; 
		}
	}
	
	
	@PutMapping("/alterarAuthor") 
	public AuthorEntity alterar (@RequestBody AuthorEntity author) {
		System.out.println(author.getId());
		System.out.println(author.getNome());
		AuthorEntity authorCompleto = repository.save(author);
		return authorCompleto;  	                           
	}
	
	
	@DeleteMapping("/excluirAuthor/{id}")
	public void excluir (@PathVariable UUID id) {
		repository.deleteById(id);
	}
	
	//listAll
	@GetMapping("/listAuthors")
	public List<AuthorEntity> getById() {
		List<AuthorEntity> authors;
		authors = repository.findAll();
		
		return authors;
	}

}
