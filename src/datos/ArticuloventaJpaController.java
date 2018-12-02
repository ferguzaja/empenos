/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

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
public class ArticuloventaJpaController implements Serializable {

    public ArticuloventaJpaController() {
         this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articuloventa articuloventa) {
        if (articuloventa.getVentaList() == null) {
            articuloventa.setVentaList(new ArrayList<Venta>());
        }
        if (articuloventa.getRemateList() == null) {
            articuloventa.setRemateList(new ArrayList<Remate>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prenda prendaIdprenda = articuloventa.getPrendaIdprenda();
            if (prendaIdprenda != null) {
                prendaIdprenda = em.getReference(prendaIdprenda.getClass(), prendaIdprenda.getIdprenda());
                articuloventa.setPrendaIdprenda(prendaIdprenda);
            }
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : articuloventa.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getIdventa());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            articuloventa.setVentaList(attachedVentaList);
            List<Remate> attachedRemateList = new ArrayList<Remate>();
            for (Remate remateListRemateToAttach : articuloventa.getRemateList()) {
                remateListRemateToAttach = em.getReference(remateListRemateToAttach.getClass(), remateListRemateToAttach.getIdremate());
                attachedRemateList.add(remateListRemateToAttach);
            }
            articuloventa.setRemateList(attachedRemateList);
            em.persist(articuloventa);
            if (prendaIdprenda != null) {
                prendaIdprenda.getArticuloventaList().add(articuloventa);
                prendaIdprenda = em.merge(prendaIdprenda);
            }
            for (Venta ventaListVenta : articuloventa.getVentaList()) {
                ventaListVenta.getArticuloventaList().add(articuloventa);
                ventaListVenta = em.merge(ventaListVenta);
            }
            for (Remate remateListRemate : articuloventa.getRemateList()) {
                remateListRemate.getArticuloventaList().add(articuloventa);
                remateListRemate = em.merge(remateListRemate);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articuloventa articuloventa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articuloventa persistentArticuloventa = em.find(Articuloventa.class, articuloventa.getIdarticuloventa());
            Prenda prendaIdprendaOld = persistentArticuloventa.getPrendaIdprenda();
            Prenda prendaIdprendaNew = articuloventa.getPrendaIdprenda();

            if (prendaIdprendaNew != null) {
                prendaIdprendaNew = em.getReference(prendaIdprendaNew.getClass(), prendaIdprendaNew.getIdprenda());
                articuloventa.setPrendaIdprenda(prendaIdprendaNew);
            }
            
            articuloventa = em.merge(articuloventa);
            if (prendaIdprendaOld != null && !prendaIdprendaOld.equals(prendaIdprendaNew)) {
                prendaIdprendaOld.getArticuloventaList().remove(articuloventa);
                prendaIdprendaOld = em.merge(prendaIdprendaOld);
            }
            if (prendaIdprendaNew != null && !prendaIdprendaNew.equals(prendaIdprendaOld)) {
                prendaIdprendaNew.getArticuloventaList().add(articuloventa);
                prendaIdprendaNew = em.merge(prendaIdprendaNew);
            }                        
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articuloventa.getIdarticuloventa();
                if (findArticuloventa(id) == null) {
                    throw new NonexistentEntityException("The articuloventa with id " + id + " no longer exists.");
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
            Articuloventa articuloventa;
            try {
                articuloventa = em.getReference(Articuloventa.class, id);
                articuloventa.getIdarticuloventa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articuloventa with id " + id + " no longer exists.", enfe);
            }
            Prenda prendaIdprenda = articuloventa.getPrendaIdprenda();
            if (prendaIdprenda != null) {
                prendaIdprenda.getArticuloventaList().remove(articuloventa);
                prendaIdprenda = em.merge(prendaIdprenda);
            }
            List<Venta> ventaList = articuloventa.getVentaList();
            for (Venta ventaListVenta : ventaList) {
                ventaListVenta.getArticuloventaList().remove(articuloventa);
                ventaListVenta = em.merge(ventaListVenta);
            }
            List<Remate> remateList = articuloventa.getRemateList();
            for (Remate remateListRemate : remateList) {
                remateListRemate.getArticuloventaList().remove(articuloventa);
                remateListRemate = em.merge(remateListRemate);
            }
            em.remove(articuloventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articuloventa> findArticuloventaEntities() {
        return findArticuloventaEntities(true, -1, -1);
    }

    public List<Articuloventa> findArticuloventaEntities(int maxResults, int firstResult) {
        return findArticuloventaEntities(false, maxResults, firstResult);
    }

    private List<Articuloventa> findArticuloventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articuloventa.class));
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

    public Articuloventa findArticuloventa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articuloventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articuloventa> rt = cq.from(Articuloventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
