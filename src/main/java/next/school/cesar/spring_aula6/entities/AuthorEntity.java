package next.school.cesar.spring_aula6.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok   -- cria os getters e setters
@Builder //lombomk  -- implementa o padrão builder
@NoArgsConstructor  //lombok  -- cria um construtor vazio 
@AllArgsConstructor  //lombok -- cria um construtor com todos os atributos 

@Entity(name = "autor")
public class AuthorEntity implements Serializable {
	
	//criptografa o ID
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;// dfsr-23fb-23gd-32fa = 2
	// PUT: localhost:8080/users/dfsr-23fb-23gd-32fa
	
	//      não aceita nulo 
	@Column(name= "nome",nullable = false)
	private String nome;
		
	@ManyToMany
	(mappedBy = "authors")
	private Set <BookEntity> authors = new HashSet<BookEntity>();
	
	//@Embedded
	//private Endereco endereco;
}
