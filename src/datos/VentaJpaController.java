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

/**
 *
 * @author Jahir
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        if (venta.getArticuloventaList() == null) {
            venta.setArticuloventaList(new ArrayList<Articuloventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoidEmpleado = venta.getEmpleadoidEmpleado();
            if (empleadoidEmpleado != null) {
                empleadoidEmpleado = em.getReference(empleadoidEmpleado.getClass(), empleadoidEmpleado.getIdempleado());
                venta.setEmpleadoidEmpleado(empleadoidEmpleado);
            }
            Cliente clienteIdcliente = venta.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                venta.setClienteIdcliente(clienteIdcliente);
            }
            List<Articuloventa> attachedArticuloventaList = new ArrayList<Articuloventa>();
            for (Articuloventa articuloventaListArticuloventaToAttach : venta.getArticuloventaList()) {
                articuloventaListArticuloventaToAttach = em.getReference(articuloventaListArticuloventaToAttach.getClass(), articuloventaListArticuloventaToAttach.getIdarticuloventa());
                attachedArticuloventaList.add(articuloventaListArticuloventaToAttach);
            }
            venta.setArticuloventaList(attachedArticuloventaList);
            em.persist(venta);
            if (empleadoidEmpleado != null) {
                empleadoidEmpleado.getVentaList().add(venta);
                empleadoidEmpleado = em.merge(empleadoidEmpleado);
            }
            if (clienteIdcliente != null) {
                clienteIdcliente.getVentaList().add(venta);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            for (Articuloventa articuloventaListArticuloventa : venta.getArticuloventaList()) {
                articuloventaListArticuloventa.getVentaList().add(venta);
                articuloventaListArticuloventa = em.merge(articuloventaListArticuloventa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getIdventa());
            Empleado empleadoidEmpleadoOld = persistentVenta.getEmpleadoidEmpleado();
            Empleado empleadoidEmpleadoNew = venta.getEmpleadoidEmpleado();
            Cliente clienteIdclienteOld = persistentVenta.getClienteIdcliente();
            Cliente clienteIdclienteNew = venta.getClienteIdcliente();
            List<Articuloventa> articuloventaListOld = persistentVenta.getArticuloventaList();
            List<Articuloventa> articuloventaListNew = venta.getArticuloventaList();
            if (empleadoidEmpleadoNew != null) {
                empleadoidEmpleadoNew = em.getReference(empleadoidEmpleadoNew.getClass(), empleadoidEmpleadoNew.getIdempleado());
                venta.setEmpleadoidEmpleado(empleadoidEmpleadoNew);
            }
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                venta.setClienteIdcliente(clienteIdclienteNew);
            }
            List<Articuloventa> attachedArticuloventaListNew = new ArrayList<Articuloventa>();
            for (Articuloventa articuloventaListNewArticuloventaToAttach : articuloventaListNew) {
                articuloventaListNewArticuloventaToAttach = em.getReference(articuloventaListNewArticuloventaToAttach.getClass(), articuloventaListNewArticuloventaToAttach.getIdarticuloventa());
                attachedArticuloventaListNew.add(articuloventaListNewArticuloventaToAttach);
            }
            articuloventaListNew = attachedArticuloventaListNew;
            venta.setArticuloventaList(articuloventaListNew);
            venta = em.merge(venta);
            if (empleadoidEmpleadoOld != null && !empleadoidEmpleadoOld.equals(empleadoidEmpleadoNew)) {
                empleadoidEmpleadoOld.getVentaList().remove(venta);
                empleadoidEmpleadoOld = em.merge(empleadoidEmpleadoOld);
            }
            if (empleadoidEmpleadoNew != null && !empleadoidEmpleadoNew.equals(empleadoidEmpleadoOld)) {
                empleadoidEmpleadoNew.getVentaList().add(venta);
                empleadoidEmpleadoNew = em.merge(empleadoidEmpleadoNew);
            }
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getVentaList().remove(venta);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getVentaList().add(venta);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            for (Articuloventa articuloventaListOldArticuloventa : articuloventaListOld) {
                if (!articuloventaListNew.contains(articuloventaListOldArticuloventa)) {
                    articuloventaListOldArticuloventa.getVentaList().remove(venta);
                    articuloventaListOldArticuloventa = em.merge(articuloventaListOldArticuloventa);
                }
            }
            for (Articuloventa articuloventaListNewArticuloventa : articuloventaListNew) {
                if (!articuloventaListOld.contains(articuloventaListNewArticuloventa)) {
                    articuloventaListNewArticuloventa.getVentaList().add(venta);
                    articuloventaListNewArticuloventa = em.merge(articuloventaListNewArticuloventa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getIdventa();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getIdventa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoidEmpleado = venta.getEmpleadoidEmpleado();
            if (empleadoidEmpleado != null) {
                empleadoidEmpleado.getVentaList().remove(venta);
                empleadoidEmpleado = em.merge(empleadoidEmpleado);
            }
            Cliente clienteIdcliente = venta.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getVentaList().remove(venta);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            List<Articuloventa> articuloventaList = venta.getArticuloventaList();
            for (Articuloventa articuloventaListArticuloventa : articuloventaList) {
                articuloventaListArticuloventa.getVentaList().remove(venta);
                articuloventaListArticuloventa = em.merge(articuloventaListArticuloventa);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
