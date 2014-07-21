/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaExamen;
import com.utez.integracion.entity.HistoricoFechaexamen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistoricoFechaexamenJpaController implements Serializable {

    public HistoricoFechaexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoricoFechaexamen historicoFechaexamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamen idFechaexamen = historicoFechaexamen.getIdFechaexamen();
            if (idFechaexamen != null) {
                idFechaexamen = em.getReference(idFechaexamen.getClass(), idFechaexamen.getIdFechaexamen());
                historicoFechaexamen.setIdFechaexamen(idFechaexamen);
            }
            em.persist(historicoFechaexamen);
            if (idFechaexamen != null) {
                idFechaexamen.getHistoricoFechaexamenList().add(historicoFechaexamen);
                idFechaexamen = em.merge(idFechaexamen);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoricoFechaexamen historicoFechaexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricoFechaexamen persistentHistoricoFechaexamen = em.find(HistoricoFechaexamen.class, historicoFechaexamen.getIdHistoricoFechaexamen());
            FechaExamen idFechaexamenOld = persistentHistoricoFechaexamen.getIdFechaexamen();
            FechaExamen idFechaexamenNew = historicoFechaexamen.getIdFechaexamen();
            if (idFechaexamenNew != null) {
                idFechaexamenNew = em.getReference(idFechaexamenNew.getClass(), idFechaexamenNew.getIdFechaexamen());
                historicoFechaexamen.setIdFechaexamen(idFechaexamenNew);
            }
            historicoFechaexamen = em.merge(historicoFechaexamen);
            if (idFechaexamenOld != null && !idFechaexamenOld.equals(idFechaexamenNew)) {
                idFechaexamenOld.getHistoricoFechaexamenList().remove(historicoFechaexamen);
                idFechaexamenOld = em.merge(idFechaexamenOld);
            }
            if (idFechaexamenNew != null && !idFechaexamenNew.equals(idFechaexamenOld)) {
                idFechaexamenNew.getHistoricoFechaexamenList().add(historicoFechaexamen);
                idFechaexamenNew = em.merge(idFechaexamenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = historicoFechaexamen.getIdHistoricoFechaexamen();
                if (findHistoricoFechaexamen(id) == null) {
                    throw new NonexistentEntityException("The historicoFechaexamen with id " + id + " no longer exists.");
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
            HistoricoFechaexamen historicoFechaexamen;
            try {
                historicoFechaexamen = em.getReference(HistoricoFechaexamen.class, id);
                historicoFechaexamen.getIdHistoricoFechaexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoFechaexamen with id " + id + " no longer exists.", enfe);
            }
            FechaExamen idFechaexamen = historicoFechaexamen.getIdFechaexamen();
            if (idFechaexamen != null) {
                idFechaexamen.getHistoricoFechaexamenList().remove(historicoFechaexamen);
                idFechaexamen = em.merge(idFechaexamen);
            }
            em.remove(historicoFechaexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoricoFechaexamen> findHistoricoFechaexamenEntities() {
        return findHistoricoFechaexamenEntities(true, -1, -1);
    }

    public List<HistoricoFechaexamen> findHistoricoFechaexamenEntities(int maxResults, int firstResult) {
        return findHistoricoFechaexamenEntities(false, maxResults, firstResult);
    }

    private List<HistoricoFechaexamen> findHistoricoFechaexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HistoricoFechaexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoricoFechaexamen findHistoricoFechaexamen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoricoFechaexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoFechaexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HistoricoFechaexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
