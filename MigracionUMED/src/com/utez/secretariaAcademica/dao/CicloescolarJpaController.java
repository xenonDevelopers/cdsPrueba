/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import com.utez.secretariaAcademica.entity.Cicloescolar;
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
public class CicloescolarJpaController implements Serializable {

    public CicloescolarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cicloescolar cicloescolar) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cicloescolar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCicloescolar(cicloescolar.getCiclo()) != null) {
                throw new PreexistingEntityException("Cicloescolar " + cicloescolar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cicloescolar cicloescolar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cicloescolar = em.merge(cicloescolar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cicloescolar.getCiclo();
                if (findCicloescolar(id) == null) {
                    throw new NonexistentEntityException("The cicloescolar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cicloescolar cicloescolar;
            try {
                cicloescolar = em.getReference(Cicloescolar.class, id);
                cicloescolar.getCiclo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cicloescolar with id " + id + " no longer exists.", enfe);
            }
            em.remove(cicloescolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cicloescolar> findCicloescolarEntities() {
        return findCicloescolarEntities(true, -1, -1);
    }

    public List<Cicloescolar> findCicloescolarEntities(int maxResults, int firstResult) {
        return findCicloescolarEntities(false, maxResults, firstResult);
    }

    private List<Cicloescolar> findCicloescolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cicloescolar as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cicloescolar findCicloescolar(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cicloescolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCicloescolarCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cicloescolar as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
