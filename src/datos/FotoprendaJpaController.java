/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jahir
 */
public class FotoprendaJpaController implements Serializable {

    public FotoprendaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fotoprenda fotoprenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prenda prendaIdprenda = fotoprenda.getPrendaIdprenda();
            if (prendaIdprenda != null) {
                prendaIdprenda = em.getReference(prendaIdprenda.getClass(), prendaIdprenda.getIdprenda());
                fotoprenda.setPrendaIdprenda(prendaIdprenda);
            }
            em.persist(fotoprenda);
            if (prendaIdprenda != null) {
                prendaIdprenda.getFotoprendaList().add(fotoprenda);
                prendaIdprenda = em.merge(prendaIdprenda);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fotoprenda fotoprenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotoprenda persistentFotoprenda = em.find(Fotoprenda.class, fotoprenda.getIdfotoPrenda());
            Prenda prendaIdprendaOld = persistentFotoprenda.getPrendaIdprenda();
            Prenda prendaIdprendaNew = fotoprenda.getPrendaIdprenda();
            if (prendaIdprendaNew != null) {
                prendaIdprendaNew = em.getReference(prendaIdprendaNew.getClass(), prendaIdprendaNew.getIdprenda());
                fotoprenda.setPrendaIdprenda(prendaIdprendaNew);
            }
            fotoprenda = em.merge(fotoprenda);
            if (prendaIdprendaOld != null && !prendaIdprendaOld.equals(prendaIdprendaNew)) {
                prendaIdprendaOld.getFotoprendaList().remove(fotoprenda);
                prendaIdprendaOld = em.merge(prendaIdprendaOld);
            }
            if (prendaIdprendaNew != null && !prendaIdprendaNew.equals(prendaIdprendaOld)) {
                prendaIdprendaNew.getFotoprendaList().add(fotoprenda);
                prendaIdprendaNew = em.merge(prendaIdprendaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fotoprenda.getIdfotoPrenda();
                if (findFotoprenda(id) == null) {
                    throw new NonexistentEntityException("The fotoprenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotoprenda fotoprenda;
            try {
                fotoprenda = em.getReference(Fotoprenda.class, id);
                fotoprenda.getIdfotoPrenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotoprenda with id " + id + " no longer exists.", enfe);
            }
            Prenda prendaIdprenda = fotoprenda.getPrendaIdprenda();
            if (prendaIdprenda != null) {
                prendaIdprenda.getFotoprendaList().remove(fotoprenda);
                prendaIdprenda = em.merge(prendaIdprenda);
            }
            em.remove(fotoprenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fotoprenda> findFotoprendaEntities() {
        return findFotoprendaEntities(true, -1, -1);
    }

    public List<Fotoprenda> findFotoprendaEntities(int maxResults, int firstResult) {
        return findFotoprendaEntities(false, maxResults, firstResult);
    }

    private List<Fotoprenda> findFotoprendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fotoprenda.class));
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

    public Fotoprenda findFotoprenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fotoprenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotoprendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fotoprenda> rt = cq.from(Fotoprenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
