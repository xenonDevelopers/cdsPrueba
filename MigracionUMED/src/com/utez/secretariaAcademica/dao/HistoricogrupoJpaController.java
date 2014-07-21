/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Fechasexamen;
import com.utez.secretariaAcademica.entity.Historicogrupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistoricogrupoJpaController implements Serializable {

    public HistoricogrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicogrupo historicogrupo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechasexamen idfechaexamen = historicogrupo.getIdfechaexamen();
            if (idfechaexamen != null) {
                idfechaexamen = em.getReference(idfechaexamen.getClass(), idfechaexamen.getIdfechaexamen());
                historicogrupo.setIdfechaexamen(idfechaexamen);
            }
            em.persist(historicogrupo);
            if (idfechaexamen != null) {
                idfechaexamen.getHistoricogrupoList().add(historicogrupo);
                idfechaexamen = em.merge(idfechaexamen);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historicogrupo historicogrupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historicogrupo persistentHistoricogrupo = em.find(Historicogrupo.class, historicogrupo.getIdhistoricogrupo());
            Fechasexamen idfechaexamenOld = persistentHistoricogrupo.getIdfechaexamen();
            Fechasexamen idfechaexamenNew = historicogrupo.getIdfechaexamen();
            if (idfechaexamenNew != null) {
                idfechaexamenNew = em.getReference(idfechaexamenNew.getClass(), idfechaexamenNew.getIdfechaexamen());
                historicogrupo.setIdfechaexamen(idfechaexamenNew);
            }
            historicogrupo = em.merge(historicogrupo);
            if (idfechaexamenOld != null && !idfechaexamenOld.equals(idfechaexamenNew)) {
                idfechaexamenOld.getHistoricogrupoList().remove(historicogrupo);
                idfechaexamenOld = em.merge(idfechaexamenOld);
            }
            if (idfechaexamenNew != null && !idfechaexamenNew.equals(idfechaexamenOld)) {
                idfechaexamenNew.getHistoricogrupoList().add(historicogrupo);
                idfechaexamenNew = em.merge(idfechaexamenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicogrupo.getIdhistoricogrupo();
                if (findHistoricogrupo(id) == null) {
                    throw new NonexistentEntityException("The historicogrupo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historicogrupo historicogrupo;
            try {
                historicogrupo = em.getReference(Historicogrupo.class, id);
                historicogrupo.getIdhistoricogrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicogrupo with id " + id + " no longer exists.", enfe);
            }
            Fechasexamen idfechaexamen = historicogrupo.getIdfechaexamen();
            if (idfechaexamen != null) {
                idfechaexamen.getHistoricogrupoList().remove(historicogrupo);
                idfechaexamen = em.merge(idfechaexamen);
            }
            em.remove(historicogrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historicogrupo> findHistoricogrupoEntities() {
        return findHistoricogrupoEntities(true, -1, -1);
    }

    public List<Historicogrupo> findHistoricogrupoEntities(int maxResults, int firstResult) {
        return findHistoricogrupoEntities(false, maxResults, firstResult);
    }

    private List<Historicogrupo> findHistoricogrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Historicogrupo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Historicogrupo findHistoricogrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicogrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricogrupoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Historicogrupo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
