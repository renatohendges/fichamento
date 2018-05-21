package entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@SuppressWarnings("serial")
@Entity(name = "livro")
@NamedQueries({
	@NamedQuery(name = Livro.PESQUISAR_POR_TITULO,
		query = "SELECT l FROM livro l WHERE l.titulo LIKE %:titulo%"),
	@NamedQuery(name = Livro.PESQUISAR_POR_ISBN,
		query = "SELECT l FROM livro l WHERE l.isbn LIKE %:isbn%"),
	@NamedQuery(name = Livro.PESQUISAR_POR_AUTOR,
		query = "SELECT l FROM livro l WHERE l.autor LIKE %:autor%")
})
@Data
public class Livro implements Serializable {

	@Transient
	public static final String PESQUISAR_POR_TITULO = "Livro.pesquisarPorTitulo";
	public static final String PESQUISAR_POR_ISBN = "Livro.pesquisarPorIsbn";
	public static final String PESQUISAR_POR_AUTOR = "Livro.pesquisarPorAutor";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	@NotBlank(message = "o campo título é obrigatório!")
	private String titulo;
	@Column
	private String subTitulo;
	@Column
	@NotBlank(message = "O campo autor é obrigatório!")
	private String autor;
	@Column
	@NotBlank(message = "O campo isbn é obrigatório!")
	private Integer isbn;
	@Column
	private Integer edicao;
	@Column
	private String cidadePublicacao;
	@Column
	private String editora;
	@Column
	private Date anoPublicacao;

	public Livro() {
		super();
	}

	public Livro(String titulo, String autor, Integer isbn) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
	}

	public Livro(String titulo, String subTitulo, String autor, Integer isbn, Integer edicao, String cidadePublicacao, String editora, Date anoPublicacao) {
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
