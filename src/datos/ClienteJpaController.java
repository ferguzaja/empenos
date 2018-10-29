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
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
         this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getVentaList() == null) {
            cliente.setVentaList(new ArrayList<Venta>());
        }
        if (cliente.getFotoclienteList() == null) {
            cliente.setFotoclienteList(new ArrayList<Fotocliente>());
        }
        if (cliente.getRemateList() == null) {
            cliente.setRemateList(new ArrayList<Remate>());
        }
        if (cliente.getEmpenoList() == null) {
            cliente.setEmpenoList(new ArrayList<Empeno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudad ciudadIdciudad = cliente.getCiudadIdciudad();
            if (ciudadIdciudad != null) {
                ciudadIdciudad = em.getReference(ciudadIdciudad.getClass(), ciudadIdciudad.getIdciudad());
                cliente.setCiudadIdciudad(ciudadIdciudad);
            }
            Ocupacion ocupacionIdocupacion = cliente.getOcupacionIdocupacion();
            if (ocupacionIdocupacion != null) {
                ocupacionIdocupacion = em.getReference(ocupacionIdocupacion.getClass(), ocupacionIdocupacion.getIdocupacion());
                cliente.setOcupacionIdocupacion(ocupacionIdocupacion);
            }
            Tipoidentificacion tipoidentificacionIdtipoidentificacion = cliente.getTipoidentificacionIdtipoidentificacion();
            if (tipoidentificacionIdtipoidentificacion != null) {
                tipoidentificacionIdtipoidentificacion = em.getReference(tipoidentificacionIdtipoidentificacion.getClass(), tipoidentificacionIdtipoidentificacion.getIdtipoidentificacion());
                cliente.setTipoidentificacionIdtipoidentificacion(tipoidentificacionIdtipoidentificacion);
            }
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : cliente.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getIdventa());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            cliente.setVentaList(attachedVentaList);
            List<Fotocliente> attachedFotoclienteList = new ArrayList<Fotocliente>();
            for (Fotocliente fotoclienteListFotoclienteToAttach : cliente.getFotoclienteList()) {
                fotoclienteListFotoclienteToAttach = em.getReference(fotoclienteListFotoclienteToAttach.getClass(), fotoclienteListFotoclienteToAttach.getIdfotoCliente());
                attachedFotoclienteList.add(fotoclienteListFotoclienteToAttach);
            }
            cliente.setFotoclienteList(attachedFotoclienteList);
            List<Remate> attachedRemateList = new ArrayList<Remate>();
            for (Remate remateListRemateToAttach : cliente.getRemateList()) {
                remateListRemateToAttach = em.getReference(remateListRemateToAttach.getClass(), remateListRemateToAttach.getIdremate());
                attachedRemateList.add(remateListRemateToAttach);
            }
            cliente.setRemateList(attachedRemateList);
            List<Empeno> attachedEmpenoList = new ArrayList<Empeno>();
            for (Empeno empenoListEmpenoToAttach : cliente.getEmpenoList()) {
                empenoListEmpenoToAttach = em.getReference(empenoListEmpenoToAttach.getClass(), empenoListEmpenoToAttach.getIdempeno());
                attachedEmpenoList.add(empenoListEmpenoToAttach);
            }
            cliente.setEmpenoList(attachedEmpenoList);
            em.persist(cliente);
            if (ciudadIdciudad != null) {
                ciudadIdciudad.getClienteList().add(cliente);
                ciudadIdciudad = em.merge(ciudadIdciudad);
            }
            if (ocupacionIdocupacion != null) {
                ocupacionIdocupacion.getClienteList().add(cliente);
                ocupacionIdocupacion = em.merge(ocupacionIdocupacion);
            }
            if (tipoidentificacionIdtipoidentificacion != null) {
                tipoidentificacionIdtipoidentificacion.getClienteList().add(cliente);
                tipoidentificacionIdtipoidentificacion = em.merge(tipoidentificacionIdtipoidentificacion);
            }
            for (Venta ventaListVenta : cliente.getVentaList()) {
                Cliente oldClienteIdclienteOfVentaListVenta = ventaListVenta.getClienteIdcliente();
                ventaListVenta.setClienteIdcliente(cliente);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldClienteIdclienteOfVentaListVenta != null) {
                    oldClienteIdclienteOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldClienteIdclienteOfVentaListVenta = em.merge(oldClienteIdclienteOfVentaListVenta);
                }
            }
            for (Fotocliente fotoclienteListFotocliente : cliente.getFotoclienteList()) {
                Cliente oldClienteIdclienteOfFotoclienteListFotocliente = fotoclienteListFotocliente.getClienteIdcliente();
                fotoclienteListFotocliente.setClienteIdcliente(cliente);
                fotoclienteListFotocliente = em.merge(fotoclienteListFotocliente);
                if (oldClienteIdclienteOfFotoclienteListFotocliente != null) {
                    oldClienteIdclienteOfFotoclienteListFotocliente.getFotoclienteList().remove(fotoclienteListFotocliente);
                    oldClienteIdclienteOfFotoclienteListFotocliente = em.merge(oldClienteIdclienteOfFotoclienteListFotocliente);
                }
            }
            for (Remate remateListRemate : cliente.getRemateList()) {
                Cliente oldClienteIdclienteOfRemateListRemate = remateListRemate.getClienteIdcliente();
                remateListRemate.setClienteIdcliente(cliente);
                remateListRemate = em.merge(remateListRemate);
                if (oldClienteIdclienteOfRemateListRemate != null) {
                    oldClienteIdclienteOfRemateListRemate.getRemateList().remove(remateListRemate);
                    oldClienteIdclienteOfRemateListRemate = em.merge(oldClienteIdclienteOfRemateListRemate);
                }
            }
            for (Empeno empenoListEmpeno : cliente.getEmpenoList()) {
                Cliente oldClienteIdclienteOfEmpenoListEmpeno = empenoListEmpeno.getClienteIdcliente();
                empenoListEmpeno.setClienteIdcliente(cliente);
                empenoListEmpeno = em.merge(empenoListEmpeno);
                if (oldClienteIdclienteOfEmpenoListEmpeno != null) {
                    oldClienteIdclienteOfEmpenoListEmpeno.getEmpenoList().remove(empenoListEmpeno);
                    oldClienteIdclienteOfEmpenoListEmpeno = em.merge(oldClienteIdclienteOfEmpenoListEmpeno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            Ciudad ciudadIdciudadOld = persistentCliente.getCiudadIdciudad();
            Ciudad ciudadIdciudadNew = cliente.getCiudadIdciudad();
            Ocupacion ocupacionIdocupacionOld = persistentCliente.getOcupacionIdocupacion();
            Ocupacion ocupacionIdocupacionNew = cliente.getOcupacionIdocupacion();
            Tipoidentificacion tipoidentificacionIdtipoidentificacionOld = persistentCliente.getTipoidentificacionIdtipoidentificacion();
            Tipoidentificacion tipoidentificacionIdtipoidentificacionNew = cliente.getTipoidentificacionIdtipoidentificacion();
            
            /*List<Venta> ventaListOld = persistentCliente.getVentaList();
            List<Venta> ventaListNew = cliente.getVentaList();
            List<Fotocliente> fotoclienteListOld = persistentCliente.getFotoclienteList();
            List<Fotocliente> fotoclienteListNew = cliente.getFotoclienteList();
            List<Remate> remateListOld = persistentCliente.getRemateList();
            List<Remate> remateListNew = cliente.getRemateList();
            List<Empeno> empenoListOld = persistentCliente.getEmpenoList();
            List<Empeno> empenoListNew = cliente.getEmpenoList();
            List<String> illegalOrphanMessages = null;
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venta " + ventaListOldVenta + " since its clienteIdcliente field is not nullable.");
                }
            }
            for (Fotocliente fotoclienteListOldFotocliente : fotoclienteListOld) {
                if (!fotoclienteListNew.contains(fotoclienteListOldFotocliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fotocliente " + fotoclienteListOldFotocliente + " since its clienteIdcliente field is not nullable.");
                }
            }
            for (Remate remateListOldRemate : remateListOld) {
                if (!remateListNew.contains(remateListOldRemate)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Remate " + remateListOldRemate + " since its clienteIdcliente field is not nullable.");
                }
            }
            for (Empeno empenoListOldEmpeno : empenoListOld) {
                if (!empenoListNew.contains(empenoListOldEmpeno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empeno " + empenoListOldEmpeno + " since its clienteIdcliente field is not nullable.");
                }
            }*/
            /*if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }*/
            if (ciudadIdciudadNew != null) {
                ciudadIdciudadNew = em.getReference(ciudadIdciudadNew.getClass(), ciudadIdciudadNew.getIdciudad());
                cliente.setCiudadIdciudad(ciudadIdciudadNew);
            }
            if (ocupacionIdocupacionNew != null) {
                ocupacionIdocupacionNew = em.getReference(ocupacionIdocupacionNew.getClass(), ocupacionIdocupacionNew.getIdocupacion());
                cliente.setOcupacionIdocupacion(ocupacionIdocupacionNew);
            }
            if (tipoidentificacionIdtipoidentificacionNew != null) {
                tipoidentificacionIdtipoidentificacionNew = em.getReference(tipoidentificacionIdtipoidentificacionNew.getClass(), tipoidentificacionIdtipoidentificacionNew.getIdtipoidentificacion());
                cliente.setTipoidentificacionIdtipoidentificacion(tipoidentificacionIdtipoidentificacionNew);
            }
            /*List<Venta> attachedVentaListNew = new ArrayList<Venta>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getIdventa());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            cliente.setVentaList(ventaListNew);
            List<Fotocliente> attachedFotoclienteListNew = new ArrayList<Fotocliente>();
            for (Fotocliente fotoclienteListNewFotoclienteToAttach : fotoclienteListNew) {
                fotoclienteListNewFotoclienteToAttach = em.getReference(fotoclienteListNewFotoclienteToAttach.getClass(), fotoclienteListNewFotoclienteToAttach.getIdfotoCliente());
                attachedFotoclienteListNew.add(fotoclienteListNewFotoclienteToAttach);
            }
            fotoclienteListNew = attachedFotoclienteListNew;
            cliente.setFotoclienteList(fotoclienteListNew);
            List<Remate> attachedRemateListNew = new ArrayList<Remate>();
            for (Remate remateListNewRemateToAttach : remateListNew) {
                remateListNewRemateToAttach = em.getReference(remateListNewRemateToAttach.getClass(), remateListNewRemateToAttach.getIdremate());
                attachedRemateListNew.add(remateListNewRemateToAttach);
            }
            remateListNew = attachedRemateListNew;
            cliente.setRemateList(remateListNew);
            List<Empeno> attachedEmpenoListNew = new ArrayList<Empeno>();
            for (Empeno empenoListNewEmpenoToAttach : empenoListNew) {
                empenoListNewEmpenoToAttach = em.getReference(empenoListNewEmpenoToAttach.getClass(), empenoListNewEmpenoToAttach.getIdempeno());
                attachedEmpenoListNew.add(empenoListNewEmpenoToAttach);
            }
            empenoListNew = attachedEmpenoListNew;
            cliente.setEmpenoList(empenoListNew);
            cliente = em.merge(cliente);*/
            if (ciudadIdciudadOld != null && !ciudadIdciudadOld.equals(ciudadIdciudadNew)) {
                ciudadIdciudadOld.getClienteList().remove(cliente);
                ciudadIdciudadOld = em.merge(ciudadIdciudadOld);
            }
            if (ciudadIdciudadNew != null && !ciudadIdciudadNew.equals(ciudadIdciudadOld)) {
                ciudadIdciudadNew.getClienteList().add(cliente);
                ciudadIdciudadNew = em.merge(ciudadIdciudadNew);
            }
            if (ocupacionIdocupacionOld != null && !ocupacionIdocupacionOld.equals(ocupacionIdocupacionNew)) {
                ocupacionIdocupacionOld.getClienteList().remove(cliente);
                ocupacionIdocupacionOld = em.merge(ocupacionIdocupacionOld);
            }
            if (ocupacionIdocupacionNew != null && !ocupacionIdocupacionNew.equals(ocupacionIdocupacionOld)) {
                ocupacionIdocupacionNew.getClienteList().add(cliente);
                ocupacionIdocupacionNew = em.merge(ocupacionIdocupacionNew);
            }
            if (tipoidentificacionIdtipoidentificacionOld != null && !tipoidentificacionIdtipoidentificacionOld.equals(tipoidentificacionIdtipoidentificacionNew)) {
                tipoidentificacionIdtipoidentificacionOld.getClienteList().remove(cliente);
                tipoidentificacionIdtipoidentificacionOld = em.merge(tipoidentificacionIdtipoidentificacionOld);
            }
            if (tipoidentificacionIdtipoidentificacionNew != null && !tipoidentificacionIdtipoidentificacionNew.equals(tipoidentificacionIdtipoidentificacionOld)) {
                tipoidentificacionIdtipoidentificacionNew.getClienteList().add(cliente);
                tipoidentificacionIdtipoidentificacionNew = em.merge(tipoidentificacionIdtipoidentificacionNew);
            }
            /*for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Cliente oldClienteIdclienteOfVentaListNewVenta = ventaListNewVenta.getClienteIdcliente();
                    ventaListNewVenta.setClienteIdcliente(cliente);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldClienteIdclienteOfVentaListNewVenta != null && !oldClienteIdclienteOfVentaListNewVenta.equals(cliente)) {
                        oldClienteIdclienteOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldClienteIdclienteOfVentaListNewVenta = em.merge(oldClienteIdclienteOfVentaListNewVenta);
                    }
                }
            }*/
            /*for (Fotocliente fotoclienteListNewFotocliente : fotoclienteListNew) {
                if (!fotoclienteListOld.contains(fotoclienteListNewFotocliente)) {
                    Cliente oldClienteIdclienteOfFotoclienteListNewFotocliente = fotoclienteListNewFotocliente.getClienteIdcliente();
                    fotoclienteListNewFotocliente.setClienteIdcliente(cliente);
                    fotoclienteListNewFotocliente = em.merge(fotoclienteListNewFotocliente);
                    if (oldClienteIdclienteOfFotoclienteListNewFotocliente != null && !oldClienteIdclienteOfFotoclienteListNewFotocliente.equals(cliente)) {
                        oldClienteIdclienteOfFotoclienteListNewFotocliente.getFotoclienteList().remove(fotoclienteListNewFotocliente);
                        oldClienteIdclienteOfFotoclienteListNewFotocliente = em.merge(oldClienteIdclienteOfFotoclienteListNewFotocliente);
                    }
                }
            }/*
            /*for (Remate remateListNewRemate : remateListNew) {
                if (!remateListOld.contains(remateListNewRemate)) {
                    Cliente oldClienteIdclienteOfRemateListNewRemate = remateListNewRemate.getClienteIdcliente();
                    remateListNewRemate.setClienteIdcliente(cliente);
                    remateListNewRemate = em.merge(remateListNewRemate);
                    if (oldClienteIdclienteOfRemateListNewRemate != null && !oldClienteIdclienteOfRemateListNewRemate.equals(cliente)) {
                        oldClienteIdclienteOfRemateListNewRemate.getRemateList().remove(remateListNewRemate);
                        oldClienteIdclienteOfRemateListNewRemate = em.merge(oldClienteIdclienteOfRemateListNewRemate);
                    }
                }
            }*/
            /*for (Empeno empenoListNewEmpeno : empenoListNew) {
                if (!empenoListOld.contains(empenoListNewEmpeno)) {
                    Cliente oldClienteIdclienteOfEmpenoListNewEmpeno = empenoListNewEmpeno.getClienteIdcliente();
                    empenoListNewEmpeno.setClienteIdcliente(cliente);
                    empenoListNewEmpeno = em.merge(empenoListNewEmpeno);
                    if (oldClienteIdclienteOfEmpenoListNewEmpeno != null && !oldClienteIdclienteOfEmpenoListNewEmpeno.equals(cliente)) {
                        oldClienteIdclienteOfEmpenoListNewEmpeno.getEmpenoList().remove(empenoListNewEmpeno);
                        oldClienteIdclienteOfEmpenoListNewEmpeno = em.merge(oldClienteIdclienteOfEmpenoListNewEmpeno);
                    }
                }
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Venta> ventaListOrphanCheck = cliente.getVentaList();
            for (Venta ventaListOrphanCheckVenta : ventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Venta " + ventaListOrphanCheckVenta + " in its ventaList field has a non-nullable clienteIdcliente field.");
            }
            List<Fotocliente> fotoclienteListOrphanCheck = cliente.getFotoclienteList();
            for (Fotocliente fotoclienteListOrphanCheckFotocliente : fotoclienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Fotocliente " + fotoclienteListOrphanCheckFotocliente + " in its fotoclienteList field has a non-nullable clienteIdcliente field.");
            }
            List<Remate> remateListOrphanCheck = cliente.getRemateList();
            for (Remate remateListOrphanCheckRemate : remateListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Remate " + remateListOrphanCheckRemate + " in its remateList field has a non-nullable clienteIdcliente field.");
            }
            List<Empeno> empenoListOrphanCheck = cliente.getEmpenoList();
            for (Empeno empenoListOrphanCheckEmpeno : empenoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Empeno " + empenoListOrphanCheckEmpeno + " in its empenoList field has a non-nullable clienteIdcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ciudad ciudadIdciudad = cliente.getCiudadIdciudad();
            if (ciudadIdciudad != null) {
                ciudadIdciudad.getClienteList().remove(cliente);
                ciudadIdciudad = em.merge(ciudadIdciudad);
            }
            Ocupacion ocupacionIdocupacion = cliente.getOcupacionIdocupacion();
            if (ocupacionIdocupacion != null) {
                ocupacionIdocupacion.getClienteList().remove(cliente);
                ocupacionIdocupacion = em.merge(ocupacionIdocupacion);
            }
            Tipoidentificacion tipoidentificacionIdtipoidentificacion = cliente.getTipoidentificacionIdtipoidentificacion();
            if (tipoidentificacionIdtipoidentificacion != null) {
                tipoidentificacionIdtipoidentificacion.getClienteList().remove(cliente);
                tipoidentificacionIdtipoidentificacion = em.merge(tipoidentificacionIdtipoidentificacion);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
