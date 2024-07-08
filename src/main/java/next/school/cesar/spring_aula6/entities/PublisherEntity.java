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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok   -- cria os getters e setters
@Builder //lombomk  -- implementa o padrão builder
@NoArgsConstructor  //lombok  -- cria um construtor vazio 
@AllArgsConstructor  //lombok -- cria um construtor com todos os atributos 

@Entity(name = "editora")
public class PublisherEntity implements Serializable {
	
	//criptografa o ID
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;// dfsr-23fb-23gd-32fa = 2
	// PUT: localhost:8080/users/dfsr-23fb-23gd-32fa
	
	//      titulo unico /  não aceita nulo 
	@Column(name = "titulo", unique = true, nullable = false)
	private String title;
	
//relacionamento 1 para muitos
	@OneToMany (mappedBy = "publisher") 
	private Set <BookEntity> books = new HashSet<BookEntity>(); //tudo isso é uma lista
	//List = Set = tudo na lista de livros (BookEntity)
	//em relações o @OneTomMany é necessário passar uma lista 
	
	@Embedded
	private Endereco endereco;

}
