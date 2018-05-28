package entidade;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.Data;
@SuppressWarnings("serial")
@Entity(name = "livro")
@NamedQueries({
		@NamedQuery(
				name = Livro.PESQUISAR_TODOS,
				query = "SELECT l FROM livro l")
})
@Data
public class Livro implements Serializable {
	@Transient
	public static final String PESQUISAR_TODOS = "Livro.pesquisarTodos";
	@Id
	@Column(name = "id_livro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "titulo")
	@NotEmpty(message = "o campo título é obrigatório!")
	private String titulo;
	@Column(name = "subtitulo")
	private String subTitulo;
	@Column(name = "autor")
	@NotEmpty(message = "O campo autor é obrigatório!")
	private String autor;
	@Column(name = "isbn")
	@NotNull(message = "O campo isbn é obrigatório!")
	private Integer isbn;
	@Column(name = "edicao")
	private Integer edicao;
	@Column(name = "cidade_publicacao")
	private String cidadePublicacao;
	@Column(name = "editora")
	private String editora;
	@Column(name = "ano_publicacao")
	private Integer anoPublicacao;
	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Fichamento> fichamentos;
	public Livro() {
		super();
	}
	public Livro(String titulo, String autor, Integer isbn) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
	}
	public Livro(String titulo, String subTitulo, String autor, Integer isbn, Integer edicao, String cidadePublicacao, String editora, Integer anoPublicacao) {
		super();
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.autor = autor;
		this.isbn = isbn;
		this.edicao = edicao;
		this.cidadePublicacao = cidadePublicacao;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
	}
}
