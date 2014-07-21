/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.RespuestaAbiertadocente;
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
public class RespuestaAbiertadocenteJpaController implements Serializable {

    public RespuestaAbiertadocenteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RespuestaAbiertadocente respuestaAbiertadocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(respuestaAbiertadocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RespuestaAbiertadocente respuestaAbiertadocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            respuestaAbiertadocente = em.merge(respuestaAbiertadocente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = respuestaAbiertadocente.getIdRespuestaabiertadocente();
                if (findRespuestaAbiertadocente(id) == null) {
                    throw new NonexistentEntityException("The respuestaAbiertadocente with id " + id + " no longer exists.");
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
            RespuestaAbiertadocente respuestaAbiertadocente;
            try {
                respuestaAbiertadocente = em.getReference(RespuestaAbiertadocente.class, id);
                respuestaAbiertadocente.getIdRespuestaabiertadocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestaAbiertadocente with id " + id + " no longer exists.", enfe);
            }
            em.remove(respuestaAbiertadocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RespuestaAbiertadocente> findRespuestaAbiertadocenteEntities() {
        return findRespuestaAbiertadocenteEntities(true, -1, -1);
    }

    public List<RespuestaAbiertadocente> findRespuestaAbiertadocenteEntities(int maxResults, int firstResult) {
        return findRespuestaAbiertadocenteEntities(false, maxResults, firstResult);
    }

    private List<RespuestaAbiertadocente> findRespuestaAbiertadocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RespuestaAbiertadocente as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RespuestaAbiertadocente findRespuestaAbiertadocente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RespuestaAbiertadocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestaAbiertadocenteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RespuestaAbiertadocente as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
