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
@Entity(
	name = "ficha")
@NamedQueries({
	@NamedQuery(
		name = Ficha.PESQUISAR_TODAS,
		query = "SELECT f FROM ficha f"),
	@NamedQuery(
		name = Ficha.PESQUISAR_POR_USUARIO,
		query = "SELECT f FROM ficha f WHERE f.usuario=:usuario"),
	@NamedQuery(
		name = Ficha.PESQUISAR_POR_LIVRO,
		query = "SELECT f FROM ficha f WHERE f.livro=:livo")
})
@Data
public class Ficha implements Serializable {

	@Transient
	public static final String PESQUISAR_TODAS = "Ficha.pesquisarTodas";
	@Transient
	public static final String PESQUISAR_POR_USUARIO = "Ficha.pesquisarPorUsuario";
	@Transient
	public static final String PESQUISAR_POR_LIVRO = "Ficha.pesquisarPorLivro";
	@Id
	@Column(
		name = "id_ficha")
	@GeneratedValue(
		strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(
		name = "id_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(
		name = "id_livro")
	private Livro livro;
	@Column(
		name = "texto")
	private String texto;
	@Column(
		name = "data_criacao")
	private Date dataCriacao;
	@Column(
		name = "compartilhada")
	private Boolean compartilhada = false;

	public Ficha() {
		super();
	}

	public Ficha(Usuario usuario, Livro livro, String texto, Date dataCriacao, Boolean compartilhada) {
		super();
		this.usuario = usuario;
		this.livro = livro;
		this.texto = texto;
		this.dataCriacao = dataCriacao;
		this.compartilhada = compartilhada;
	}
}
