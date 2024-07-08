package next.school.cesar.spring_aula6.controllers;

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

import next.school.cesar.spring_aula6.entities.ReviewEntity;
import next.school.cesar.spring_aula6.repositories.ReviewRepository;


@RestController 
public class ReviewController {
	
	@Autowired
	private ReviewRepository repository;
	
	@PostMapping("/incluirReview") 
	public ReviewEntity incluir (@RequestBody ReviewEntity review) {
		System.out.println(review.getId());
		System.out.println(review.getComment());
		ReviewEntity reviewCompleto = repository.save(review);  
		return reviewCompleto;
	}
	
	@GetMapping("/buscarReview/{id}")
	public ReviewEntity buscar(@PathVariable UUID id) {
		Optional<ReviewEntity> retorno = repository.findById(id);
		boolean estaVazio = retorno.isEmpty();
		if (estaVazio) {
			return null;
		} else {
			ReviewEntity entidadeBuscada = retorno.get();
			return entidadeBuscada; 
		}
	}
	
	
	@PutMapping("/alterarReview") 
	public ReviewEntity alterar (@RequestBody ReviewEntity review) {
		System.out.println(review.getId());
		System.out.println(review.getComment());
		ReviewEntity reviewCompleto = repository.save(review);
		return reviewCompleto;  	                           
	}
	
	
	@DeleteMapping("/excluirReview/{id}")
	public void excluir (@PathVariable UUID id) {
		repository.deleteById(id);
	}

}
