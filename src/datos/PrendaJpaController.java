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
public class PrendaJpaController implements Serializable {

    public PrendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prenda prenda) {
        if (prenda.getArticuloventaList() == null) {
            prenda.setArticuloventaList(new ArrayList<Articuloventa>());
        }
        if (prenda.getFotoprendaList() == null) {
            prenda.setFotoprendaList(new ArrayList<Fotoprenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catalogo catalogoidCatalogo = prenda.getCatalogoidCatalogo();
            if (catalogoidCatalogo != null) {
                catalogoidCatalogo = em.getReference(catalogoidCatalogo.getClass(), catalogoidCatalogo.getIdcatalogo());
                prenda.setCatalogoidCatalogo(catalogoidCatalogo);
            }
            Empeno empenoIdempeno = prenda.getEmpenoIdempeno();
            if (empenoIdempeno != null) {
                empenoIdempeno = em.getReference(empenoIdempeno.getClass(), empenoIdempeno.getIdempeno());
                prenda.setEmpenoIdempeno(empenoIdempeno);
            }
            List<Articuloventa> attachedArticuloventaList = new ArrayList<Articuloventa>();
            for (Articuloventa articuloventaListArticuloventaToAttach : prenda.getArticuloventaList()) {
                articuloventaListArticuloventaToAttach = em.getReference(articuloventaListArticuloventaToAttach.getClass(), articuloventaListArticuloventaToAttach.getIdarticuloventa());
                attachedArticuloventaList.add(articuloventaListArticuloventaToAttach);
            }
            prenda.setArticuloventaList(attachedArticuloventaList);
            List<Fotoprenda> attachedFotoprendaList = new ArrayList<Fotoprenda>();
            for (Fotoprenda fotoprendaListFotoprendaToAttach : prenda.getFotoprendaList()) {
                fotoprendaListFotoprendaToAttach = em.getReference(fotoprendaListFotoprendaToAttach.getClass(), fotoprendaListFotoprendaToAttach.getIdfotoPrenda());
                attachedFotoprendaList.add(fotoprendaListFotoprendaToAttach);
            }
            prenda.setFotoprendaList(attachedFotoprendaList);
            em.persist(prenda);
            if (catalogoidCatalogo != null) {
                catalogoidCatalogo.getPrendaList().add(prenda);
                catalogoidCatalogo = em.merge(catalogoidCatalogo);
            }
            if (empenoIdempeno != null) {
                empenoIdempeno.getPrendaList().add(prenda);
                empenoIdempeno = em.merge(empenoIdempeno);
            }
            for (Articuloventa articuloventaListArticuloventa : prenda.getArticuloventaList()) {
                Prenda oldPrendaIdprendaOfArticuloventaListArticuloventa = articuloventaListArticuloventa.getPrendaIdprenda();
                articuloventaListArticuloventa.setPrendaIdprenda(prenda);
                articuloventaListArticuloventa = em.merge(articuloventaListArticuloventa);
                if (oldPrendaIdprendaOfArticuloventaListArticuloventa != null) {
                    oldPrendaIdprendaOfArticuloventaListArticuloventa.getArticuloventaList().remove(articuloventaListArticuloventa);
                    oldPrendaIdprendaOfArticuloventaListArticuloventa = em.merge(oldPrendaIdprendaOfArticuloventaListArticuloventa);
                }
            }
            for (Fotoprenda fotoprendaListFotoprenda : prenda.getFotoprendaList()) {
                Prenda oldPrendaIdprendaOfFotoprendaListFotoprenda = fotoprendaListFotoprenda.getPrendaIdprenda();
                fotoprendaListFotoprenda.setPrendaIdprenda(prenda);
                fotoprendaListFotoprenda = em.merge(fotoprendaListFotoprenda);
                if (oldPrendaIdprendaOfFotoprendaListFotoprenda != null) {
                    oldPrendaIdprendaOfFotoprendaListFotoprenda.getFotoprendaList().remove(fotoprendaListFotoprenda);
                    oldPrendaIdprendaOfFotoprendaListFotoprenda = em.merge(oldPrendaIdprendaOfFotoprendaListFotoprenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prenda prenda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prenda persistentPrenda = em.find(Prenda.class, prenda.getIdprenda());
            Catalogo catalogoidCatalogoOld = persistentPrenda.getCatalogoidCatalogo();
            Catalogo catalogoidCatalogoNew = prenda.getCatalogoidCatalogo();
            Empeno empenoIdempenoOld = persistentPrenda.getEmpenoIdempeno();
            Empeno empenoIdempenoNew = prenda.getEmpenoIdempeno();
            List<Articuloventa> articuloventaListOld = persistentPrenda.getArticuloventaList();
            List<Articuloventa> articuloventaListNew = prenda.getArticuloventaList();
            List<Fotoprenda> fotoprendaListOld = persistentPrenda.getFotoprendaList();
            List<Fotoprenda> fotoprendaListNew = prenda.getFotoprendaList();
            List<String> illegalOrphanMessages = null;
            for (Articuloventa articuloventaListOldArticuloventa : articuloventaListOld) {
                if (!articuloventaListNew.contains(articuloventaListOldArticuloventa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articuloventa " + articuloventaListOldArticuloventa + " since its prendaIdprenda field is not nullable.");
                }
            }
            for (Fotoprenda fotoprendaListOldFotoprenda : fotoprendaListOld) {
                if (!fotoprendaListNew.contains(fotoprendaListOldFotoprenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fotoprenda " + fotoprendaListOldFotoprenda + " since its prendaIdprenda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (catalogoidCatalogoNew != null) {
                catalogoidCatalogoNew = em.getReference(catalogoidCatalogoNew.getClass(), catalogoidCatalogoNew.getIdcatalogo());
                prenda.setCatalogoidCatalogo(catalogoidCatalogoNew);
            }
            if (empenoIdempenoNew != null) {
                empenoIdempenoNew = em.getReference(empenoIdempenoNew.getClass(), empenoIdempenoNew.getIdempeno());
                prenda.setEmpenoIdempeno(empenoIdempenoNew);
            }
            List<Articuloventa> attachedArticuloventaListNew = new ArrayList<Articuloventa>();
            for (Articuloventa articuloventaListNewArticuloventaToAttach : articuloventaListNew) {
                articuloventaListNewArticuloventaToAttach = em.getReference(articuloventaListNewArticuloventaToAttach.getClass(), articuloventaListNewArticuloventaToAttach.getIdarticuloventa());
                attachedArticuloventaListNew.add(articuloventaListNewArticuloventaToAttach);
            }
            articuloventaListNew = attachedArticuloventaListNew;
            prenda.setArticuloventaList(articuloventaListNew);
            List<Fotoprenda> attachedFotoprendaListNew = new ArrayList<Fotoprenda>();
            for (Fotoprenda fotoprendaListNewFotoprendaToAttach : fotoprendaListNew) {
                fotoprendaListNewFotoprendaToAttach = em.getReference(fotoprendaListNewFotoprendaToAttach.getClass(), fotoprendaListNewFotoprendaToAttach.getIdfotoPrenda());
                attachedFotoprendaListNew.add(fotoprendaListNewFotoprendaToAttach);
            }
            fotoprendaListNew = attachedFotoprendaListNew;
            prenda.setFotoprendaList(fotoprendaListNew);
            prenda = em.merge(prenda);
            if (catalogoidCatalogoOld != null && !catalogoidCatalogoOld.equals(catalogoidCatalogoNew)) {
                catalogoidCatalogoOld.getPrendaList().remove(prenda);
                catalogoidCatalogoOld = em.merge(catalogoidCatalogoOld);
            }
            if (catalogoidCatalogoNew != null && !catalogoidCatalogoNew.equals(catalogoidCatalogoOld)) {
                catalogoidCatalogoNew.getPrendaList().add(prenda);
                catalogoidCatalogoNew = em.merge(catalogoidCatalogoNew);
            }
            if (empenoIdempenoOld != null && !empenoIdempenoOld.equals(empenoIdempenoNew)) {
                empenoIdempenoOld.getPrendaList().remove(prenda);
                empenoIdempenoOld = em.merge(empenoIdempenoOld);
            }
            if (empenoIdempenoNew != null && !empenoIdempenoNew.equals(empenoIdempenoOld)) {
                empenoIdempenoNew.getPrendaList().add(prenda);
                empenoIdempenoNew = em.merge(empenoIdempenoNew);
            }
            for (Articuloventa articuloventaListNewArticuloventa : articuloventaListNew) {
                if (!articuloventaListOld.contains(articuloventaListNewArticuloventa)) {
                    Prenda oldPrendaIdprendaOfArticuloventaListNewArticuloventa = articuloventaListNewArticuloventa.getPrendaIdprenda();
                    articuloventaListNewArticuloventa.setPrendaIdprenda(prenda);
                    articuloventaListNewArticuloventa = em.merge(articuloventaListNewArticuloventa);
                    if (oldPrendaIdprendaOfArticuloventaListNewArticuloventa != null && !oldPrendaIdprendaOfArticuloventaListNewArticuloventa.equals(prenda)) {
                        oldPrendaIdprendaOfArticuloventaListNewArticuloventa.getArticuloventaList().remove(articuloventaListNewArticuloventa);
                        oldPrendaIdprendaOfArticuloventaListNewArticuloventa = em.merge(oldPrendaIdprendaOfArticuloventaListNewArticuloventa);
                    }
                }
            }
            for (Fotoprenda fotoprendaListNewFotoprenda : fotoprendaListNew) {
                if (!fotoprendaListOld.contains(fotoprendaListNewFotoprenda)) {
                    Prenda oldPrendaIdprendaOfFotoprendaListNewFotoprenda = fotoprendaListNewFotoprenda.getPrendaIdprenda();
                    fotoprendaListNewFotoprenda.setPrendaIdprenda(prenda);
                    fotoprendaListNewFotoprenda = em.merge(fotoprendaListNewFotoprenda);
                    if (oldPrendaIdprendaOfFotoprendaListNewFotoprenda != null && !oldPrendaIdprendaOfFotoprendaListNewFotoprenda.equals(prenda)) {
                        oldPrendaIdprendaOfFotoprendaListNewFotoprenda.getFotoprendaList().remove(fotoprendaListNewFotoprenda);
                        oldPrendaIdprendaOfFotoprendaListNewFotoprenda = em.merge(oldPrendaIdprendaOfFotoprendaListNewFotoprenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prenda.getIdprenda();
                if (findPrenda(id) == null) {
                    throw new NonexistentEntityException("The prenda with id " + id + " no longer exists.");
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
            Prenda prenda;
            try {
                prenda = em.getReference(Prenda.class, id);
                prenda.getIdprenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prenda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articuloventa> articuloventaListOrphanCheck = prenda.getArticuloventaList();
            for (Articuloventa articuloventaListOrphanCheckArticuloventa : articuloventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prenda (" + prenda + ") cannot be destroyed since the Articuloventa " + articuloventaListOrphanCheckArticuloventa + " in its articuloventaList field has a non-nullable prendaIdprenda field.");
            }
            List<Fotoprenda> fotoprendaListOrphanCheck = prenda.getFotoprendaList();
            for (Fotoprenda fotoprendaListOrphanCheckFotoprenda : fotoprendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prenda (" + prenda + ") cannot be destroyed since the Fotoprenda " + fotoprendaListOrphanCheckFotoprenda + " in its fotoprendaList field has a non-nullable prendaIdprenda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Catalogo catalogoidCatalogo = prenda.getCatalogoidCatalogo();
            if (catalogoidCatalogo != null) {
                catalogoidCatalogo.getPrendaList().remove(prenda);
                catalogoidCatalogo = em.merge(catalogoidCatalogo);
            }
            Empeno empenoIdempeno = prenda.getEmpenoIdempeno();
            if (empenoIdempeno != null) {
                empenoIdempeno.getPrendaList().remove(prenda);
                empenoIdempeno = em.merge(empenoIdempeno);
            }
            em.remove(prenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prenda> findPrendaEntities() {
        return findPrendaEntities(true, -1, -1);
    }

    public List<Prenda> findPrendaEntities(int maxResults, int firstResult) {
        return findPrendaEntities(false, maxResults, firstResult);
    }

    private List<Prenda> findPrendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prenda.class));
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

    public Prenda findPrenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prenda> rt = cq.from(Prenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
