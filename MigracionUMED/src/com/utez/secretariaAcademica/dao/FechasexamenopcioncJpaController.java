/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Fechasexamenopcionc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Mesopcionc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechasexamenopcioncJpaController implements Serializable {

    public FechasexamenopcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechasexamenopcionc fechasexamenopcionc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesopcionc idmesopcionc = fechasexamenopcionc.getIdmesopcionc();
            if (idmesopcionc != null) {
                idmesopcionc = em.getReference(idmesopcionc.getClass(), idmesopcionc.getIdmesopcionc());
                fechasexamenopcionc.setIdmesopcionc(idmesopcionc);
            }
            em.persist(fechasexamenopcionc);
            if (idmesopcionc != null) {
                idmesopcionc.getFechasexamenopcioncList().add(fechasexamenopcionc);
                idmesopcionc = em.merge(idmesopcionc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechasexamenopcionc fechasexamenopcionc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechasexamenopcionc persistentFechasexamenopcionc = em.find(Fechasexamenopcionc.class, fechasexamenopcionc.getIdfechasexamenopcionc());
            Mesopcionc idmesopcioncOld = persistentFechasexamenopcionc.getIdmesopcionc();
            Mesopcionc idmesopcioncNew = fechasexamenopcionc.getIdmesopcionc();
            if (idmesopcioncNew != null) {
                idmesopcioncNew = em.getReference(idmesopcioncNew.getClass(), idmesopcioncNew.getIdmesopcionc());
                fechasexamenopcionc.setIdmesopcionc(idmesopcioncNew);
            }
            fechasexamenopcionc = em.merge(fechasexamenopcionc);
            if (idmesopcioncOld != null && !idmesopcioncOld.equals(idmesopcioncNew)) {
                idmesopcioncOld.getFechasexamenopcioncList().remove(fechasexamenopcionc);
                idmesopcioncOld = em.merge(idmesopcioncOld);
            }
            if (idmesopcioncNew != null && !idmesopcioncNew.equals(idmesopcioncOld)) {
                idmesopcioncNew.getFechasexamenopcioncList().add(fechasexamenopcionc);
                idmesopcioncNew = em.merge(idmesopcioncNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechasexamenopcionc.getIdfechasexamenopcionc();
                if (findFechasexamenopcionc(id) == null) {
                    throw new NonexistentEntityException("The fechasexamenopcionc with id " + id + " no longer exists.");
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
            Fechasexamenopcionc fechasexamenopcionc;
            try {
                fechasexamenopcionc = em.getReference(Fechasexamenopcionc.class, id);
                fechasexamenopcionc.getIdfechasexamenopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechasexamenopcionc with id " + id + " no longer exists.", enfe);
            }
            Mesopcionc idmesopcionc = fechasexamenopcionc.getIdmesopcionc();
            if (idmesopcionc != null) {
                idmesopcionc.getFechasexamenopcioncList().remove(fechasexamenopcionc);
                idmesopcionc = em.merge(idmesopcionc);
            }
            em.remove(fechasexamenopcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechasexamenopcionc> findFechasexamenopcioncEntities() {
        return findFechasexamenopcioncEntities(true, -1, -1);
    }

    public List<Fechasexamenopcionc> findFechasexamenopcioncEntities(int maxResults, int firstResult) {
        return findFechasexamenopcioncEntities(false, maxResults, firstResult);
    }

    private List<Fechasexamenopcionc> findFechasexamenopcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechasexamenopcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechasexamenopcionc findFechasexamenopcionc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechasexamenopcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechasexamenopcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechasexamenopcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
