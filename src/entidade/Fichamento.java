package entidade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Data;
@SuppressWarnings("serial")
@Entity(name = "fichamento")
@NamedQueries({
		@NamedQuery(
				name = Fichamento.PESQUISAR_TODAS,
				query = "SELECT f FROM fichamento f")
})
@Data
public class Fichamento implements Serializable {
	@Transient
	public static final String PESQUISAR_TODAS = "Fichamento.pesquisarTodas";
	@Id
	@Column(name = "id_fichamento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fichamento_usuario"))
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "id_livro", foreignKey = @ForeignKey(name = "fichamento_livro"))
	private Livro livro;
	@Column(name = "data_cadastro")
	private Date dataCadastro = new Date();
	@Column(name = "compartilhada")
	private Boolean compartilhada = false;
	@OneToMany(mappedBy = "fichamento", cascade = CascadeType.ALL)
	private List<Ficha> fichas;
	public Fichamento() {
		super();
	}
	public Fichamento(Usuario usuario, Livro livro, Boolean compartilhada) {
		super();
		this.usuario = usuario;
		this.livro = livro;
		this.compartilhada = compartilhada;
	}
}
