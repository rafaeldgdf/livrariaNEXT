package next.school.cesar.spring_aula6.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok   -- cria os getters e setters
@Builder //lombomk  -- implementa o padrão builder
@NoArgsConstructor  //lombok  -- cria um construtor vazio 
@AllArgsConstructor  //lombok -- cria um construtor com todos os atributos 

@Entity(name="livro") 
public class BookEntity implements Serializable {
	
	//criptografa o ID
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;// dfsr-23fb-23gd-32fa = 2
	// PUT: localhost:8080/users/dfsr-23fb-23gd-32fa
	
	//                         titulo unico /  não aceita nulo 
	@Column(name = "titulo", unique = true, nullable = false)
	private String title;
	
	//muitos para um 
	@ManyToOne //anotação para criar a relação de book com entity 
	@JoinColumn(name="publisher_entity_id")
	private PublisherEntity publisher;
	
	//relação 1 pra 1 
	@OneToOne (mappedBy = "book", cascade = CascadeType.ALL) //adicionando efeito cascata em review, para caso haja um delete no livro, delete as review
	private ReviewEntity review;
	
	@ManyToMany  //relação muitos pra muitos 
	@JoinTable ( //criando tabela associativa   //o joinTable é opcional, só para nomear as colunas corretamente
			name = "livro_autor",
			joinColumns = @JoinColumn (name = "livro_id"), 
			inverseJoinColumns = @JoinColumn (name = "autor_id")  //MapsId pra trocar o nome da coluna/relação na tabela associativa 
			) //para criar uma classe que se refere a uma tabela associativa, criasse uma classe tradicional e coloca as duas relaões das tabelas, com suas chaves, e o relacionamento @ManyToOne
	private Set <AuthorEntity> authors = new HashSet<AuthorEntity>();
}
