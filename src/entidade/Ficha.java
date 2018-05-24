package entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import lombok.Data;

@SuppressWarnings("serial")
@Entity(name = "ficha")
@NamedQueries({
	@NamedQuery(name = Ficha.PESQUISAR_TODAS,
		query = "SELECT f FROM ficha f")
})
@Data
public class Ficha implements Serializable {

	@Transient
	public static final String PESQUISAR_TODAS = "Ficha.pesquisarTodas";
	@Transient
	public static final String PESQUISAR_FICHA_POR_LIVRO = "Ficha.pesquisarFichaPorLivro";
	@Id
	@Column(name = "id_ficha")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;
	@Column(name = "texto")
	private String texto;
	@Column(name = "data_criacao")
	private Date dataCriacao;

	public Ficha() {
		super();
	}
}
