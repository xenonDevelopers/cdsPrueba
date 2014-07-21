/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.GrupoEvento;
import com.utez.integracion.entity.GrupoEventoPK;
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
public class GrupoEventoJpaController implements Serializable {

    public GrupoEventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoEvento grupoEvento) throws PreexistingEntityException, Exception {
        if (grupoEvento.getGrupoEventoPK() == null) {
            grupoEvento.setGrupoEventoPK(new GrupoEventoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(grupoEvento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrupoEvento(grupoEvento.getGrupoEventoPK()) != null) {
                throw new PreexistingEntityException("GrupoEvento " + grupoEvento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoEvento grupoEvento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            grupoEvento = em.merge(grupoEvento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                GrupoEventoPK id = grupoEvento.getGrupoEventoPK();
                if (findGrupoEvento(id) == null) {
                    throw new NonexistentEntityException("The grupoEvento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(GrupoEventoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoEvento grupoEvento;
            try {
                grupoEvento = em.getReference(GrupoEvento.class, id);
                grupoEvento.getGrupoEventoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoEvento with id " + id + " no longer exists.", enfe);
            }
            em.remove(grupoEvento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GrupoEvento> findGrupoEventoEntities() {
        return findGrupoEventoEntities(true, -1, -1);
    }

    public List<GrupoEvento> findGrupoEventoEntities(int maxResults, int firstResult) {
        return findGrupoEventoEntities(false, maxResults, firstResult);
    }

    private List<GrupoEvento> findGrupoEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from GrupoEvento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public GrupoEvento findGrupoEvento(GrupoEventoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoEvento.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoEventoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from GrupoEvento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
