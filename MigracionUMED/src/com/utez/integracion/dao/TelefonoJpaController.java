/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoTelefono;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.Telefono;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TelefonoJpaController implements Serializable {

    public TelefonoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Telefono telefono) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTelefono idTipotelefono = telefono.getIdTipotelefono();
            if (idTipotelefono != null) {
                idTipotelefono = em.getReference(idTipotelefono.getClass(), idTipotelefono.getIdTipotelefono());
                telefono.setIdTipotelefono(idTipotelefono);
            }
            Persona curp = telefono.getCurp();
            if (curp != null) {
                curp = em.getReference(curp.getClass(), curp.getCurp());
                telefono.setCurp(curp);
            }
            em.persist(telefono);
            if (idTipotelefono != null) {
                idTipotelefono.getTelefonoList().add(telefono);
                idTipotelefono = em.merge(idTipotelefono);
            }
            if (curp != null) {
                curp.getTelefonoList().add(telefono);
                curp = em.merge(curp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telefono telefono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telefono persistentTelefono = em.find(Telefono.class, telefono.getIdTelefono());
            TipoTelefono idTipotelefonoOld = persistentTelefono.getIdTipotelefono();
            TipoTelefono idTipotelefonoNew = telefono.getIdTipotelefono();
            Persona curpOld = persistentTelefono.getCurp();
            Persona curpNew = telefono.getCurp();
            if (idTipotelefonoNew != null) {
                idTipotelefonoNew = em.getReference(idTipotelefonoNew.getClass(), idTipotelefonoNew.getIdTipotelefono());
                telefono.setIdTipotelefono(idTipotelefonoNew);
            }
            if (curpNew != null) {
                curpNew = em.getReference(curpNew.getClass(), curpNew.getCurp());
                telefono.setCurp(curpNew);
            }
            telefono = em.merge(telefono);
            if (idTipotelefonoOld != null && !idTipotelefonoOld.equals(idTipotelefonoNew)) {
                idTipotelefonoOld.getTelefonoList().remove(telefono);
                idTipotelefonoOld = em.merge(idTipotelefonoOld);
            }
            if (idTipotelefonoNew != null && !idTipotelefonoNew.equals(idTipotelefonoOld)) {
                idTipotelefonoNew.getTelefonoList().add(telefono);
                idTipotelefonoNew = em.merge(idTipotelefonoNew);
            }
            if (curpOld != null && !curpOld.equals(curpNew)) {
                curpOld.getTelefonoList().remove(telefono);
                curpOld = em.merge(curpOld);
            }
            if (curpNew != null && !curpNew.equals(curpOld)) {
                curpNew.getTelefonoList().add(telefono);
                curpNew = em.merge(curpNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = telefono.getIdTelefono();
                if (findTelefono(id) == null) {
                    throw new NonexistentEntityException("The telefono with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telefono telefono;
            try {
                telefono = em.getReference(Telefono.class, id);
                telefono.getIdTelefono();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telefono with id " + id + " no longer exists.", enfe);
            }
            TipoTelefono idTipotelefono = telefono.getIdTipotelefono();
            if (idTipotelefono != null) {
                idTipotelefono.getTelefonoList().remove(telefono);
                idTipotelefono = em.merge(idTipotelefono);
            }
            Persona curp = telefono.getCurp();
            if (curp != null) {
                curp.getTelefonoList().remove(telefono);
                curp = em.merge(curp);
            }
            em.remove(telefono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telefono> findTelefonoEntities() {
        return findTelefonoEntities(true, -1, -1);
    }

    public List<Telefono> findTelefonoEntities(int maxResults, int firstResult) {
        return findTelefonoEntities(false, maxResults, firstResult);
    }

    private List<Telefono> findTelefonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Telefono as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Telefono findTelefono(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telefono.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefonoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Telefono as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
