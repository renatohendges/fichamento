package entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@SuppressWarnings("serial")
@Entity(name = "livro")
@NamedQueries({
	@NamedQuery(name = Livro.PESQUISAR_POR_TITULO,
		query = "SELECT l FROM livro l WHERE l.titulo LIKE '%:titulo%'"),
	@NamedQuery(name = Livro.PESQUISAR_POR_ISBN,
		query = "SELECT l FROM livro l WHERE l.isbn LIKE '%:isbn%'"),
	@NamedQuery(name = Livro.PESQUISAR_POR_AUTOR,
		query = "SELECT l FROM livro l WHERE l.autor LIKE '%:autor%'"),
	@NamedQuery(name = Livro.PESQUISAR_TODOS,
		query = "SELECT l FROM livro l"),
	@NamedQuery(name = Livro.QUANTIDADE_FICHAS_POR_LIVRO,
		query = "SELECT COUNT(f) FROM ficha f WHERE f.id_livro =:idLivro")
})
@Data
public class Livro implements Serializable {

	@Transient
	public static final String PESQUISAR_POR_TITULO = "Livro.pesquisarPorTitulo";
	@Transient
	public static final String PESQUISAR_POR_ISBN = "Livro.pesquisarPorIsbn";
	@Transient
	public static final String PESQUISAR_POR_AUTOR = "Livro.pesquisarPorAutor";
	@Transient
	public static final String PESQUISAR_TODOS = "Livro.pesquisarTodos";
	@Transient
	public static final String QUANTIDADE_FICHAS_POR_LIVRO = "Livro.quantidadeFichasPorLivro";
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
