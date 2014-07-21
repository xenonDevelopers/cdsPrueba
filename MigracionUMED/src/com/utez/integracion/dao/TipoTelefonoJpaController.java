/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Telefono;
import com.utez.integracion.entity.TipoTelefono;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoTelefonoJpaController implements Serializable {

    public TipoTelefonoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoTelefono tipoTelefono) {
        if (tipoTelefono.getTelefonoList() == null) {
            tipoTelefono.setTelefonoList(new ArrayList<Telefono>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Telefono> attachedTelefonoList = new ArrayList<Telefono>();
            for (Telefono telefonoListTelefonoToAttach : tipoTelefono.getTelefonoList()) {
                telefonoListTelefonoToAttach = em.getReference(telefonoListTelefonoToAttach.getClass(), telefonoListTelefonoToAttach.getIdTelefono());
                attachedTelefonoList.add(telefonoListTelefonoToAttach);
            }
            tipoTelefono.setTelefonoList(attachedTelefonoList);
            em.persist(tipoTelefono);
            for (Telefono telefonoListTelefono : tipoTelefono.getTelefonoList()) {
                TipoTelefono oldIdTipotelefonoOfTelefonoListTelefono = telefonoListTelefono.getIdTipotelefono();
                telefonoListTelefono.setIdTipotelefono(tipoTelefono);
                telefonoListTelefono = em.merge(telefonoListTelefono);
                if (oldIdTipotelefonoOfTelefonoListTelefono != null) {
                    oldIdTipotelefonoOfTelefonoListTelefono.getTelefonoList().remove(telefonoListTelefono);
                    oldIdTipotelefonoOfTelefonoListTelefono = em.merge(oldIdTipotelefonoOfTelefonoListTelefono);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTelefono tipoTelefono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTelefono persistentTipoTelefono = em.find(TipoTelefono.class, tipoTelefono.getIdTipotelefono());
            List<Telefono> telefonoListOld = persistentTipoTelefono.getTelefonoList();
            List<Telefono> telefonoListNew = tipoTelefono.getTelefonoList();
            List<Telefono> attachedTelefonoListNew = new ArrayList<Telefono>();
            for (Telefono telefonoListNewTelefonoToAttach : telefonoListNew) {
                telefonoListNewTelefonoToAttach = em.getReference(telefonoListNewTelefonoToAttach.getClass(), telefonoListNewTelefonoToAttach.getIdTelefono());
                attachedTelefonoListNew.add(telefonoListNewTelefonoToAttach);
            }
            telefonoListNew = attachedTelefonoListNew;
            tipoTelefono.setTelefonoList(telefonoListNew);
            tipoTelefono = em.merge(tipoTelefono);
            for (Telefono telefonoListOldTelefono : telefonoListOld) {
                if (!telefonoListNew.contains(telefonoListOldTelefono)) {
                    telefonoListOldTelefono.setIdTipotelefono(null);
                    telefonoListOldTelefono = em.merge(telefonoListOldTelefono);
                }
            }
            for (Telefono telefonoListNewTelefono : telefonoListNew) {
                if (!telefonoListOld.contains(telefonoListNewTelefono)) {
                    TipoTelefono oldIdTipotelefonoOfTelefonoListNewTelefono = telefonoListNewTelefono.getIdTipotelefono();
                    telefonoListNewTelefono.setIdTipotelefono(tipoTelefono);
                    telefonoListNewTelefono = em.merge(telefonoListNewTelefono);
                    if (oldIdTipotelefonoOfTelefonoListNewTelefono != null && !oldIdTipotelefonoOfTelefonoListNewTelefono.equals(tipoTelefono)) {
                        oldIdTipotelefonoOfTelefonoListNewTelefono.getTelefonoList().remove(telefonoListNewTelefono);
                        oldIdTipotelefonoOfTelefonoListNewTelefono = em.merge(oldIdTipotelefonoOfTelefonoListNewTelefono);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoTelefono.getIdTipotelefono();
                if (findTipoTelefono(id) == null) {
                    throw new NonexistentEntityException("The tipoTelefono with id " + id + " no longer exists.");
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
            TipoTelefono tipoTelefono;
            try {
                tipoTelefono = em.getReference(TipoTelefono.class, id);
                tipoTelefono.getIdTipotelefono();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTelefono with id " + id + " no longer exists.", enfe);
            }
            List<Telefono> telefonoList = tipoTelefono.getTelefonoList();
            for (Telefono telefonoListTelefono : telefonoList) {
                telefonoListTelefono.setIdTipotelefono(null);
                telefonoListTelefono = em.merge(telefonoListTelefono);
            }
            em.remove(tipoTelefono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTelefono> findTipoTelefonoEntities() {
        return findTipoTelefonoEntities(true, -1, -1);
    }

    public List<TipoTelefono> findTipoTelefonoEntities(int maxResults, int firstResult) {
        return findTipoTelefonoEntities(false, maxResults, firstResult);
    }

    private List<TipoTelefono> findTipoTelefonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoTelefono as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoTelefono findTipoTelefono(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTelefono.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTelefonoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoTelefono as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
