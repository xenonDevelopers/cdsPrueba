/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Fotografia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FotografiaJpaController implements Serializable {

    public FotografiaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fotografia fotografia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona curp = fotografia.getCurp();
            if (curp != null) {
                curp = em.getReference(curp.getClass(), curp.getCurp());
                fotografia.setCurp(curp);
            }
            em.persist(fotografia);
            if (curp != null) {
                curp.getFotografiaList().add(fotografia);
                curp = em.merge(curp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fotografia fotografia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fotografia persistentFotografia = em.find(Fotografia.class, fotografia.getIdFotografia());
            Persona curpOld = persistentFotografia.getCurp();
            Persona curpNew = fotografia.getCurp();
            if (curpNew != null) {
                curpNew = em.getReference(curpNew.getClass(), curpNew.getCurp());
                fotografia.setCurp(curpNew);
            }
            fotografia = em.merge(fotografia);
            if (curpOld != null && !curpOld.equals(curpNew)) {
                curpOld.getFotografiaList().remove(fotografia);
                curpOld = em.merge(curpOld);
            }
            if (curpNew != null && !curpNew.equals(curpOld)) {
                curpNew.getFotografiaList().add(fotografia);
                curpNew = em.merge(curpNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fotografia.getIdFotografia();
                if (findFotografia(id) == null) {
                    throw new NonexistentEntityException("The fotografia with id " + id + " no longer exists.");
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
            Fotografia fotografia;
            try {
                fotografia = em.getReference(Fotografia.class, id);
                fotografia.getIdFotografia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotografia with id " + id + " no longer exists.", enfe);
            }
            Persona curp = fotografia.getCurp();
            if (curp != null) {
                curp.getFotografiaList().remove(fotografia);
                curp = em.merge(curp);
            }
            em.remove(fotografia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fotografia> findFotografiaEntities() {
        return findFotografiaEntities(true, -1, -1);
    }

    public List<Fotografia> findFotografiaEntities(int maxResults, int firstResult) {
        return findFotografiaEntities(false, maxResults, firstResult);
    }

    private List<Fotografia> findFotografiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fotografia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fotografia findFotografia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fotografia.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotografiaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fotografia as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
