package entidade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;
@SuppressWarnings("serial")
@Entity(name = "usuario")
@NamedQueries({
		@NamedQuery(
				name = Usuario.PESQUISAR_POR_EMAIL,
				query = "SELECT u FROM usuario u WHERE u.email = :email"),
		@NamedQuery(
				name = Usuario.PESQUISAR_POR_EMAIL_SENHA,
				query = "SELECT u FROM usuario u WHERE u.email = :email AND u.senha = :senha")
})
@Data
public class Usuario implements Serializable {
	@Transient
	public static final String PESQUISAR_POR_EMAIL = "Usuario.pesquisarPorEmail";
	@Transient
	public static final String PESQUISAR_POR_EMAIL_SENHA = "Usuario.pesquisarPorEmailSenha";
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome")
	@NotBlank(message = "Campo nome é obrigatório!")
	private String nome;
	@Column(name = "senha")
	@NotBlank(message = "Campo senha é obrigatório!")
	private String senha;
	@Column(name = "email")
	@Email(message = "Precisa ser um email válido!")
	@NotBlank(message = "Campo email é obrigatório!")
	private String email;
	@Column(name = "administrador")
	private Boolean administrador = false;
	@Column(name = "data_cadastro")
	private Date dataCadastro = new Date();
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Fichamento> fichamentos;
	public Usuario() {
	}
	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	public Usuario(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
}
