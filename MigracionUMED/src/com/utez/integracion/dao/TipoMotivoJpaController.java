/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.TipoMotivo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoMotivoJpaController implements Serializable {

    public TipoMotivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMotivo tipoMotivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoMotivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMotivo tipoMotivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoMotivo = em.merge(tipoMotivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoMotivo.getIdTipomotivo();
                if (findTipoMotivo(id) == null) {
                    throw new NonexistentEntityException("The tipoMotivo with id " + id + " no longer exists.");
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
            TipoMotivo tipoMotivo;
            try {
                tipoMotivo = em.getReference(TipoMotivo.class, id);
                tipoMotivo.getIdTipomotivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMotivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoMotivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMotivo> findTipoMotivoEntities() {
        return findTipoMotivoEntities(true, -1, -1);
    }

    public List<TipoMotivo> findTipoMotivoEntities(int maxResults, int firstResult) {
        return findTipoMotivoEntities(false, maxResults, firstResult);
    }

    private List<TipoMotivo> findTipoMotivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoMotivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoMotivo findTipoMotivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMotivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMotivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoMotivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
