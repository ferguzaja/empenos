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
public class CiudadJpaController implements Serializable {

    public CiudadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EmpenoFacilPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudad ciudad) {
        if (ciudad.getClienteList() == null) {
            ciudad.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estadoIdestado = ciudad.getEstadoIdestado();
            if (estadoIdestado != null) {
                estadoIdestado = em.getReference(estadoIdestado.getClass(), estadoIdestado.getIdestado());
                ciudad.setEstadoIdestado(estadoIdestado);
            }
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : ciudad.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            ciudad.setClienteList(attachedClienteList);
            em.persist(ciudad);
            if (estadoIdestado != null) {
                estadoIdestado.getCiudadList().add(ciudad);
                estadoIdestado = em.merge(estadoIdestado);
            }
            for (Cliente clienteListCliente : ciudad.getClienteList()) {
                Ciudad oldCiudadIdciudadOfClienteListCliente = clienteListCliente.getCiudadIdciudad();
                clienteListCliente.setCiudadIdciudad(ciudad);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldCiudadIdciudadOfClienteListCliente != null) {
                    oldCiudadIdciudadOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldCiudadIdciudadOfClienteListCliente = em.merge(oldCiudadIdciudadOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudad ciudad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudad persistentCiudad = em.find(Ciudad.class, ciudad.getIdciudad());
            Estado estadoIdestadoOld = persistentCiudad.getEstadoIdestado();
            Estado estadoIdestadoNew = ciudad.getEstadoIdestado();
            List<Cliente> clienteListOld = persistentCiudad.getClienteList();
            List<Cliente> clienteListNew = ciudad.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its ciudadIdciudad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoIdestadoNew != null) {
                estadoIdestadoNew = em.getReference(estadoIdestadoNew.getClass(), estadoIdestadoNew.getIdestado());
                ciudad.setEstadoIdestado(estadoIdestadoNew);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            ciudad.setClienteList(clienteListNew);
            ciudad = em.merge(ciudad);
            if (estadoIdestadoOld != null && !estadoIdestadoOld.equals(estadoIdestadoNew)) {
                estadoIdestadoOld.getCiudadList().remove(ciudad);
                estadoIdestadoOld = em.merge(estadoIdestadoOld);
            }
            if (estadoIdestadoNew != null && !estadoIdestadoNew.equals(estadoIdestadoOld)) {
                estadoIdestadoNew.getCiudadList().add(ciudad);
                estadoIdestadoNew = em.merge(estadoIdestadoNew);
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Ciudad oldCiudadIdciudadOfClienteListNewCliente = clienteListNewCliente.getCiudadIdciudad();
                    clienteListNewCliente.setCiudadIdciudad(ciudad);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldCiudadIdciudadOfClienteListNewCliente != null && !oldCiudadIdciudadOfClienteListNewCliente.equals(ciudad)) {
                        oldCiudadIdciudadOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldCiudadIdciudadOfClienteListNewCliente = em.merge(oldCiudadIdciudadOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ciudad.getIdciudad();
                if (findCiudad(id) == null) {
                    throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.");
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
            Ciudad ciudad;
            try {
                ciudad = em.getReference(Ciudad.class, id);
                ciudad.getIdciudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = ciudad.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudad (" + ciudad + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable ciudadIdciudad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estado estadoIdestado = ciudad.getEstadoIdestado();
            if (estadoIdestado != null) {
                estadoIdestado.getCiudadList().remove(ciudad);
                estadoIdestado = em.merge(estadoIdestado);
            }
            em.remove(ciudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudad> findCiudadEntities() {
        return findCiudadEntities(true, -1, -1);
    }

    public List<Ciudad> findCiudadEntities(int maxResults, int firstResult) {
        return findCiudadEntities(false, maxResults, firstResult);
    }

    private List<Ciudad> findCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudad.class));
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

    public Ciudad findCiudad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudad> rt = cq.from(Ciudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
