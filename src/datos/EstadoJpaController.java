/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import datos.exceptions.IllegalOrphanException;
import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jahir
 */
public class EstadoJpaController implements Serializable {

    public EstadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) {
        if (estado.getCiudadList() == null) {
            estado.setCiudadList(new ArrayList<Ciudad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais paisIdpais = estado.getPaisIdpais();
            if (paisIdpais != null) {
                paisIdpais = em.getReference(paisIdpais.getClass(), paisIdpais.getIdpais());
                estado.setPaisIdpais(paisIdpais);
            }
            List<Ciudad> attachedCiudadList = new ArrayList<Ciudad>();
            for (Ciudad ciudadListCiudadToAttach : estado.getCiudadList()) {
                ciudadListCiudadToAttach = em.getReference(ciudadListCiudadToAttach.getClass(), ciudadListCiudadToAttach.getIdciudad());
                attachedCiudadList.add(ciudadListCiudadToAttach);
            }
            estado.setCiudadList(attachedCiudadList);
            em.persist(estado);
            if (paisIdpais != null) {
                paisIdpais.getEstadoList().add(estado);
                paisIdpais = em.merge(paisIdpais);
            }
            for (Ciudad ciudadListCiudad : estado.getCiudadList()) {
                Estado oldEstadoIdestadoOfCiudadListCiudad = ciudadListCiudad.getEstadoIdestado();
                ciudadListCiudad.setEstadoIdestado(estado);
                ciudadListCiudad = em.merge(ciudadListCiudad);
                if (oldEstadoIdestadoOfCiudadListCiudad != null) {
                    oldEstadoIdestadoOfCiudadListCiudad.getCiudadList().remove(ciudadListCiudad);
                    oldEstadoIdestadoOfCiudadListCiudad = em.merge(oldEstadoIdestadoOfCiudadListCiudad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado persistentEstado = em.find(Estado.class, estado.getIdestado());
            Pais paisIdpaisOld = persistentEstado.getPaisIdpais();
            Pais paisIdpaisNew = estado.getPaisIdpais();
            List<Ciudad> ciudadListOld = persistentEstado.getCiudadList();
            List<Ciudad> ciudadListNew = estado.getCiudadList();
            List<String> illegalOrphanMessages = null;
            for (Ciudad ciudadListOldCiudad : ciudadListOld) {
                if (!ciudadListNew.contains(ciudadListOldCiudad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ciudad " + ciudadListOldCiudad + " since its estadoIdestado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paisIdpaisNew != null) {
                paisIdpaisNew = em.getReference(paisIdpaisNew.getClass(), paisIdpaisNew.getIdpais());
                estado.setPaisIdpais(paisIdpaisNew);
            }
            List<Ciudad> attachedCiudadListNew = new ArrayList<Ciudad>();
            for (Ciudad ciudadListNewCiudadToAttach : ciudadListNew) {
                ciudadListNewCiudadToAttach = em.getReference(ciudadListNewCiudadToAttach.getClass(), ciudadListNewCiudadToAttach.getIdciudad());
                attachedCiudadListNew.add(ciudadListNewCiudadToAttach);
            }
            ciudadListNew = attachedCiudadListNew;
            estado.setCiudadList(ciudadListNew);
            estado = em.merge(estado);
            if (paisIdpaisOld != null && !paisIdpaisOld.equals(paisIdpaisNew)) {
                paisIdpaisOld.getEstadoList().remove(estado);
                paisIdpaisOld = em.merge(paisIdpaisOld);
            }
            if (paisIdpaisNew != null && !paisIdpaisNew.equals(paisIdpaisOld)) {
                paisIdpaisNew.getEstadoList().add(estado);
                paisIdpaisNew = em.merge(paisIdpaisNew);
            }
            for (Ciudad ciudadListNewCiudad : ciudadListNew) {
                if (!ciudadListOld.contains(ciudadListNewCiudad)) {
                    Estado oldEstadoIdestadoOfCiudadListNewCiudad = ciudadListNewCiudad.getEstadoIdestado();
                    ciudadListNewCiudad.setEstadoIdestado(estado);
                    ciudadListNewCiudad = em.merge(ciudadListNewCiudad);
                    if (oldEstadoIdestadoOfCiudadListNewCiudad != null && !oldEstadoIdestadoOfCiudadListNewCiudad.equals(estado)) {
                        oldEstadoIdestadoOfCiudadListNewCiudad.getCiudadList().remove(ciudadListNewCiudad);
                        oldEstadoIdestadoOfCiudadListNewCiudad = em.merge(oldEstadoIdestadoOfCiudadListNewCiudad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estado.getIdestado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ciudad> ciudadListOrphanCheck = estado.getCiudadList();
            for (Ciudad ciudadListOrphanCheckCiudad : ciudadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Ciudad " + ciudadListOrphanCheckCiudad + " in its ciudadList field has a non-nullable estadoIdestado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pais paisIdpais = estado.getPaisIdpais();
            if (paisIdpais != null) {
                paisIdpais.getEstadoList().remove(estado);
                paisIdpais = em.merge(paisIdpais);
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estado findEstado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
