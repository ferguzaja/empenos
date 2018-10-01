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

/**
 *
 * @author Jahir
 */
public class CotitularJpaController implements Serializable {

    public CotitularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cotitular cotitular) {
        if (cotitular.getEmpenoList() == null) {
            cotitular.setEmpenoList(new ArrayList<Empeno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empeno> attachedEmpenoList = new ArrayList<Empeno>();
            for (Empeno empenoListEmpenoToAttach : cotitular.getEmpenoList()) {
                empenoListEmpenoToAttach = em.getReference(empenoListEmpenoToAttach.getClass(), empenoListEmpenoToAttach.getIdempeno());
                attachedEmpenoList.add(empenoListEmpenoToAttach);
            }
            cotitular.setEmpenoList(attachedEmpenoList);
            em.persist(cotitular);
            for (Empeno empenoListEmpeno : cotitular.getEmpenoList()) {
                Cotitular oldCotitularidCotitularOfEmpenoListEmpeno = empenoListEmpeno.getCotitularidCotitular();
                empenoListEmpeno.setCotitularidCotitular(cotitular);
                empenoListEmpeno = em.merge(empenoListEmpeno);
                if (oldCotitularidCotitularOfEmpenoListEmpeno != null) {
                    oldCotitularidCotitularOfEmpenoListEmpeno.getEmpenoList().remove(empenoListEmpeno);
                    oldCotitularidCotitularOfEmpenoListEmpeno = em.merge(oldCotitularidCotitularOfEmpenoListEmpeno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotitular cotitular) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotitular persistentCotitular = em.find(Cotitular.class, cotitular.getIdcotitular());
            List<Empeno> empenoListOld = persistentCotitular.getEmpenoList();
            List<Empeno> empenoListNew = cotitular.getEmpenoList();
            List<String> illegalOrphanMessages = null;
            for (Empeno empenoListOldEmpeno : empenoListOld) {
                if (!empenoListNew.contains(empenoListOldEmpeno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empeno " + empenoListOldEmpeno + " since its cotitularidCotitular field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empeno> attachedEmpenoListNew = new ArrayList<Empeno>();
            for (Empeno empenoListNewEmpenoToAttach : empenoListNew) {
                empenoListNewEmpenoToAttach = em.getReference(empenoListNewEmpenoToAttach.getClass(), empenoListNewEmpenoToAttach.getIdempeno());
                attachedEmpenoListNew.add(empenoListNewEmpenoToAttach);
            }
            empenoListNew = attachedEmpenoListNew;
            cotitular.setEmpenoList(empenoListNew);
            cotitular = em.merge(cotitular);
            for (Empeno empenoListNewEmpeno : empenoListNew) {
                if (!empenoListOld.contains(empenoListNewEmpeno)) {
                    Cotitular oldCotitularidCotitularOfEmpenoListNewEmpeno = empenoListNewEmpeno.getCotitularidCotitular();
                    empenoListNewEmpeno.setCotitularidCotitular(cotitular);
                    empenoListNewEmpeno = em.merge(empenoListNewEmpeno);
                    if (oldCotitularidCotitularOfEmpenoListNewEmpeno != null && !oldCotitularidCotitularOfEmpenoListNewEmpeno.equals(cotitular)) {
                        oldCotitularidCotitularOfEmpenoListNewEmpeno.getEmpenoList().remove(empenoListNewEmpeno);
                        oldCotitularidCotitularOfEmpenoListNewEmpeno = em.merge(oldCotitularidCotitularOfEmpenoListNewEmpeno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cotitular.getIdcotitular();
                if (findCotitular(id) == null) {
                    throw new NonexistentEntityException("The cotitular with id " + id + " no longer exists.");
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
            Cotitular cotitular;
            try {
                cotitular = em.getReference(Cotitular.class, id);
                cotitular.getIdcotitular();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotitular with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empeno> empenoListOrphanCheck = cotitular.getEmpenoList();
            for (Empeno empenoListOrphanCheckEmpeno : empenoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cotitular (" + cotitular + ") cannot be destroyed since the Empeno " + empenoListOrphanCheckEmpeno + " in its empenoList field has a non-nullable cotitularidCotitular field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cotitular);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cotitular> findCotitularEntities() {
        return findCotitularEntities(true, -1, -1);
    }

    public List<Cotitular> findCotitularEntities(int maxResults, int firstResult) {
        return findCotitularEntities(false, maxResults, firstResult);
    }

    private List<Cotitular> findCotitularEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotitular.class));
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

    public Cotitular findCotitular(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotitular.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotitularCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotitular> rt = cq.from(Cotitular.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
