/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.RespuestaAbierta;
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
public class RespuestaAbiertaJpaController implements Serializable {

    public RespuestaAbiertaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaAbierta respuestaAbierta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(respuestaAbierta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaAbierta respuestaAbierta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            respuestaAbierta = em.merge(respuestaAbierta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaAbierta.getIdRespuestaabierta();
                if (findRespuestaAbierta(id) == null) {
                    throw new NonexistentEntityException("The respuestaAbierta with id " + id + " no longer exists.");
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
            RespuestaAbierta respuestaAbierta;
            try {
                respuestaAbierta = em.getReference(RespuestaAbierta.class, id);
                respuestaAbierta.getIdRespuestaabierta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaAbierta with id " + id + " no longer exists.", enfe);
            }
            em.remove(respuestaAbierta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaAbierta> findRespuestaAbiertaEntities() {
        return findRespuestaAbiertaEntities(true, -1, -1);
    }

    public List<RespuestaAbierta> findRespuestaAbiertaEntities(int maxResults, int firstResult) {
        return findRespuestaAbiertaEntities(false, maxResults, firstResult);
    }

    private List<RespuestaAbierta> findRespuestaAbiertaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaAbierta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaAbierta findRespuestaAbierta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaAbierta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaAbiertaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaAbierta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
