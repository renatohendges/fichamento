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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@SuppressWarnings("serial")
@Entity(name = "usuario")
@NamedQueries({ @NamedQuery(name = Usuario.PESQUISAR_POR_NOME,
	query = "SELECT u FROM usuario u WHERE u.nome = :nome"),
	@NamedQuery(name = Usuario.PESQUISAR_POR_EMAIL,
		query = "SELECT u FROM usuario u WHERE u.email = :email"),
	@NamedQuery(name = Usuario.PESQUISAR_POR_EMAIL_SENHA,
		query = "SELECT u FROM usuario u WHERE u.email = :email AND u.senha = :senha") })
@Data
public class Usuario implements Serializable {

	@Transient
	public static final String PESQUISAR_POR_NOME = "Usuario.pesquisarPorNome";
	public static final String PESQUISAR_POR_EMAIL = "Usuario.pesquisarPorEmail";
	public static final String PESQUISAR_POR_EMAIL_SENHA = "Usuario.pesquisarPorEmailSenha";
	public static final String PESQUISAR_POR_EMAIL_CODIGO = "Usuario.pesquisarPorEmailCodigo";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	@NotBlank(message = "Campo nome é obrigatório!")
	private String nome;
	@Column
	@NotBlank(message = "Campo senha é obrigatório!")
	private String senha;
	@Column
	@Email(message = "Precisa ser um email válido!")
	@NotBlank(message = "Campo email é obrigatório!")
	private String email;
	@Column
	private Boolean administrador = false;

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
