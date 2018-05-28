package entidade;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import lombok.Data;
@SuppressWarnings("serial")
@Entity(name = "ficha")
@NamedQueries({
		@NamedQuery(
				name = Ficha.PESQUISAR_TODAS,
				query = "SELECT f FROM ficha f")
})
@Data
public class Ficha implements Serializable {
	@Transient
	public static final String PESQUISAR_TODAS = "Ficha.pesquisarTodas";
	@Id
	@Column(name = "id_ficha")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "texto", columnDefinition = "text")
	private String texto;
	@Column(name = "data_cadastro")
	private Date dataCadatro = new Date();
	@ManyToOne
	@JoinColumn(name = "id_fichamento", foreignKey = @ForeignKey(name = "ficha_fichamento"))
	private Fichamento fichamento;
}
