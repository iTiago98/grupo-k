package negocio;

import entidades.Usuario;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class NegocioUsuario {
    @PersistenceContext(unitName = "AcoesEE-entidadesPU")
    private EntityManager em;

    public void addUsuario(Usuario u) /*throws DatosDuplicadosException*/ {
        //Usuario user = this.em.find(Usuario.class, u.getId());
        //if(user != null) throw new DatosDuplicadosException("Ya existe un usuario con el mismo ID");
        
        em.persist(u);
    }
    
    public void removeUsuario(Usuario u) {
        em.remove(em.merge(u));
    }
    
    public void modifyUsuario(Usuario u) {
        em.merge(u);
    }
    
    public List<Usuario> getUsuarios() {
        return em.createNamedQuery("getUsuarios").getResultList();
    }
}
