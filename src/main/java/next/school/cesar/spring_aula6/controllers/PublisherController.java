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

import next.school.cesar.spring_aula6.entities.PublisherEntity;
import next.school.cesar.spring_aula6.repositories.PublisherRepository;

@RestController 
public class PublisherController {
	
	@Autowired  //joga a responsabilidade do dado para o banco de dados, essa anotações habilita diversas fuções, como o save 
	private PublisherRepository repository; 
	
	//incluir
	@PostMapping("/incluirPublisher") 
	public PublisherEntity incluir (@RequestBody PublisherEntity publisher) {
		System.out.println(publisher.getId());
		System.out.println(publisher.getTitle());
		//para incluir, não passamos o ID, pois ele é gerado automaticamente
		PublisherEntity publisherCompleto = repository.save(publisher);//salvando no banco de dados si    
		return publisherCompleto;  //aqui temos o objeto Publisher completo, com o ID gerado automaticamete. 
		                           //Estamos colocando dentro de uma variável para saber qual o ID que foi gerado	
	}
	
	//buscar
	@GetMapping("/buscarPublisher/{id}")
	public PublisherEntity buscar(@PathVariable UUID id) {
		Optional<PublisherEntity> retorno = repository.findById(id);
		//optional, neste caso, servirá para consulta não retornar nulo
		boolean estaVazio = retorno.isEmpty();
		if (estaVazio) {
			return null;
		} else {
			PublisherEntity entidadeBuscada = retorno.get();
			return entidadeBuscada; //retona o id, se achar
		}
	}
	
	//put para alterar 
	@PutMapping("/alterarPublisher") 
	public PublisherEntity alterar (@RequestBody PublisherEntity publisher) {
		System.out.println(publisher.getId());
		System.out.println(publisher.getTitle());
		//no alterar passamos o ID, para fazer o update
		PublisherEntity publisherCompleto = repository.save(publisher);
		return publisherCompleto;  
		                           
	}
	
	//delete pela url
	@DeleteMapping("/excluirPublisher/{id}")
	public void excluir (@PathVariable UUID id) {
		repository.deleteById(id);
	}
	
	//listAll
	@GetMapping("/listPublishers")
	public List<PublisherEntity> getById() {
		List<PublisherEntity> publishers;
		publishers = repository.findAll();
		
		return publishers;
	}

}
