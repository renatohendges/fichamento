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

import  lombok.Data;

@SuppressWarnings("serial")
@Entity(name = "usuario")
// @NamedQueries({name = Usuario.PESQUISAR_POR_NOME_SENHA, query = "SELECT u
// FROM usuario u WHERE u.nome = :nome AND u.senha = :senha"})
@NamedQueries({ @NamedQuery(name = Usuario.PESQUISAR_POR_NOME_SENHA, query = "SELECT u FROM usuario u WHERE u.nome = :nome AND u.senha = :senha"), @NamedQuery(name = Usuario.PESQUISAR_POR_EMAIL_CODIGO, query = "select u from usuario u where u.email like :email and u.codigo like :codigo") })
@Data
public class Usuario implements Serializable {
	
	@Transient
	public static final String PESQUISAR_POR_NOME_SENHA = "Usuario.pesquisarPorNomeSenha";
	public static final String PESQUISAR_POR_EMAIL_CODIGO = "Usuario.pesquisarPorEmailCodigo";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String nome;
	@Column
	private String senha;
	@Column
	private String email;
	@Column
	private Boolean ativo = false;
	@Column
	private Boolean administrador = false;
	@Column
	private String codigo;

	public Usuario() {
	}

	public Usuario(String nome, String senha, String email) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	public Usuario(String nome, String senha, String email, String codigo) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.codigo = codigo;
	}

}
