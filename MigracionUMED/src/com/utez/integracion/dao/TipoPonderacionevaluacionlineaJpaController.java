/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.TipoPonderacionevaluacionlinea;
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
public class TipoPonderacionevaluacionlineaJpaController implements Serializable {

    public TipoPonderacionevaluacionlineaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPonderacionevaluacionlinea tipoPonderacionevaluacionlinea) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoPonderacionevaluacionlinea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPonderacionevaluacionlinea tipoPonderacionevaluacionlinea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoPonderacionevaluacionlinea = em.merge(tipoPonderacionevaluacionlinea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoPonderacionevaluacionlinea.getIdTipoponderacionevaluacionlinea();
                if (findTipoPonderacionevaluacionlinea(id) == null) {
                    throw new NonexistentEntityException("The tipoPonderacionevaluacionlinea with id " + id + " no longer exists.");
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
            TipoPonderacionevaluacionlinea tipoPonderacionevaluacionlinea;
            try {
                tipoPonderacionevaluacionlinea = em.getReference(TipoPonderacionevaluacionlinea.class, id);
                tipoPonderacionevaluacionlinea.getIdTipoponderacionevaluacionlinea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPonderacionevaluacionlinea with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoPonderacionevaluacionlinea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPonderacionevaluacionlinea> findTipoPonderacionevaluacionlineaEntities() {
        return findTipoPonderacionevaluacionlineaEntities(true, -1, -1);
    }

    public List<TipoPonderacionevaluacionlinea> findTipoPonderacionevaluacionlineaEntities(int maxResults, int firstResult) {
        return findTipoPonderacionevaluacionlineaEntities(false, maxResults, firstResult);
    }

    private List<TipoPonderacionevaluacionlinea> findTipoPonderacionevaluacionlineaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoPonderacionevaluacionlinea as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoPonderacionevaluacionlinea findTipoPonderacionevaluacionlinea(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPonderacionevaluacionlinea.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPonderacionevaluacionlineaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoPonderacionevaluacionlinea as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
