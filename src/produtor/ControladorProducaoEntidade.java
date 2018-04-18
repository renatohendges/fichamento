package produtor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.inject.Named;

@ApplicationScoped
public class ControladorProducaoEntidade {

	@PersistenceContext(name = "fichaPU")
	private EntityManager fichaEM;

	@Named
	@Produces
	@RequestScoped
	public EntityManager getFichaEM() {
		return fichaEM;
	}
}