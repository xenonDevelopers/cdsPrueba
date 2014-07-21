/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Fechaasesoriabloquelinea;
import com.utez.secretariaAcademica.entity.Fechasexamenbloque;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechasexamenbloqueJpaController implements Serializable {

    public FechasexamenbloqueJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechasexamenbloque fechasexamenbloque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechaasesoriabloquelinea idfechaaseseoriabloque = fechasexamenbloque.getIdfechaaseseoriabloque();
            if (idfechaaseseoriabloque != null) {
                idfechaaseseoriabloque = em.getReference(idfechaaseseoriabloque.getClass(), idfechaaseseoriabloque.getIdfechaaseseoriabloque());
                fechasexamenbloque.setIdfechaaseseoriabloque(idfechaaseseoriabloque);
            }
            em.persist(fechasexamenbloque);
            if (idfechaaseseoriabloque != null) {
                idfechaaseseoriabloque.getFechasexamenbloqueList().add(fechasexamenbloque);
                idfechaaseseoriabloque = em.merge(idfechaaseseoriabloque);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechasexamenbloque fechasexamenbloque) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechasexamenbloque persistentFechasexamenbloque = em.find(Fechasexamenbloque.class, fechasexamenbloque.getIdfechasexamenbloque());
            Fechaasesoriabloquelinea idfechaaseseoriabloqueOld = persistentFechasexamenbloque.getIdfechaaseseoriabloque();
            Fechaasesoriabloquelinea idfechaaseseoriabloqueNew = fechasexamenbloque.getIdfechaaseseoriabloque();
            if (idfechaaseseoriabloqueNew != null) {
                idfechaaseseoriabloqueNew = em.getReference(idfechaaseseoriabloqueNew.getClass(), idfechaaseseoriabloqueNew.getIdfechaaseseoriabloque());
                fechasexamenbloque.setIdfechaaseseoriabloque(idfechaaseseoriabloqueNew);
            }
            fechasexamenbloque = em.merge(fechasexamenbloque);
            if (idfechaaseseoriabloqueOld != null && !idfechaaseseoriabloqueOld.equals(idfechaaseseoriabloqueNew)) {
                idfechaaseseoriabloqueOld.getFechasexamenbloqueList().remove(fechasexamenbloque);
                idfechaaseseoriabloqueOld = em.merge(idfechaaseseoriabloqueOld);
            }
            if (idfechaaseseoriabloqueNew != null && !idfechaaseseoriabloqueNew.equals(idfechaaseseoriabloqueOld)) {
                idfechaaseseoriabloqueNew.getFechasexamenbloqueList().add(fechasexamenbloque);
                idfechaaseseoriabloqueNew = em.merge(idfechaaseseoriabloqueNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechasexamenbloque.getIdfechasexamenbloque();
                if (findFechasexamenbloque(id) == null) {
                    throw new NonexistentEntityException("The fechasexamenbloque with id " + id + " no longer exists.");
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
            Fechasexamenbloque fechasexamenbloque;
            try {
                fechasexamenbloque = em.getReference(Fechasexamenbloque.class, id);
                fechasexamenbloque.getIdfechasexamenbloque();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechasexamenbloque with id " + id + " no longer exists.", enfe);
            }
            Fechaasesoriabloquelinea idfechaaseseoriabloque = fechasexamenbloque.getIdfechaaseseoriabloque();
            if (idfechaaseseoriabloque != null) {
                idfechaaseseoriabloque.getFechasexamenbloqueList().remove(fechasexamenbloque);
                idfechaaseseoriabloque = em.merge(idfechaaseseoriabloque);
            }
            em.remove(fechasexamenbloque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechasexamenbloque> findFechasexamenbloqueEntities() {
        return findFechasexamenbloqueEntities(true, -1, -1);
    }

    public List<Fechasexamenbloque> findFechasexamenbloqueEntities(int maxResults, int firstResult) {
        return findFechasexamenbloqueEntities(false, maxResults, firstResult);
    }

    private List<Fechasexamenbloque> findFechasexamenbloqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechasexamenbloque as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechasexamenbloque findFechasexamenbloque(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechasexamenbloque.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechasexamenbloqueCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechasexamenbloque as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
