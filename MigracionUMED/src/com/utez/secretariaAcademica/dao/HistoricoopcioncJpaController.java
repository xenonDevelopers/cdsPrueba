/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Historicoopcionc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Programacionopcionc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistoricoopcioncJpaController implements Serializable {

    public HistoricoopcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicoopcionc historicoopcionc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionopcionc idprogramacionopcionc = historicoopcionc.getIdprogramacionopcionc();
            if (idprogramacionopcionc != null) {
                idprogramacionopcionc = em.getReference(idprogramacionopcionc.getClass(), idprogramacionopcionc.getIdprogramacionopcionc());
                historicoopcionc.setIdprogramacionopcionc(idprogramacionopcionc);
            }
            em.persist(historicoopcionc);
            if (idprogramacionopcionc != null) {
                idprogramacionopcionc.getHistoricoopcioncList().add(historicoopcionc);
                idprogramacionopcionc = em.merge(idprogramacionopcionc);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historicoopcionc historicoopcionc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historicoopcionc persistentHistoricoopcionc = em.find(Historicoopcionc.class, historicoopcionc.getIdhistoricoopcionc());
            Programacionopcionc idprogramacionopcioncOld = persistentHistoricoopcionc.getIdprogramacionopcionc();
            Programacionopcionc idprogramacionopcioncNew = historicoopcionc.getIdprogramacionopcionc();
            if (idprogramacionopcioncNew != null) {
                idprogramacionopcioncNew = em.getReference(idprogramacionopcioncNew.getClass(), idprogramacionopcioncNew.getIdprogramacionopcionc());
                historicoopcionc.setIdprogramacionopcionc(idprogramacionopcioncNew);
            }
            historicoopcionc = em.merge(historicoopcionc);
            if (idprogramacionopcioncOld != null && !idprogramacionopcioncOld.equals(idprogramacionopcioncNew)) {
                idprogramacionopcioncOld.getHistoricoopcioncList().remove(historicoopcionc);
                idprogramacionopcioncOld = em.merge(idprogramacionopcioncOld);
            }
            if (idprogramacionopcioncNew != null && !idprogramacionopcioncNew.equals(idprogramacionopcioncOld)) {
                idprogramacionopcioncNew.getHistoricoopcioncList().add(historicoopcionc);
                idprogramacionopcioncNew = em.merge(idprogramacionopcioncNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicoopcionc.getIdhistoricoopcionc();
                if (findHistoricoopcionc(id) == null) {
                    throw new NonexistentEntityException("The historicoopcionc with id " + id + " no longer exists.");
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
            Historicoopcionc historicoopcionc;
            try {
                historicoopcionc = em.getReference(Historicoopcionc.class, id);
                historicoopcionc.getIdhistoricoopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoopcionc with id " + id + " no longer exists.", enfe);
            }
            Programacionopcionc idprogramacionopcionc = historicoopcionc.getIdprogramacionopcionc();
            if (idprogramacionopcionc != null) {
                idprogramacionopcionc.getHistoricoopcioncList().remove(historicoopcionc);
                idprogramacionopcionc = em.merge(idprogramacionopcionc);
            }
            em.remove(historicoopcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historicoopcionc> findHistoricoopcioncEntities() {
        return findHistoricoopcioncEntities(true, -1, -1);
    }

    public List<Historicoopcionc> findHistoricoopcioncEntities(int maxResults, int firstResult) {
        return findHistoricoopcioncEntities(false, maxResults, firstResult);
    }

    private List<Historicoopcionc> findHistoricoopcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Historicoopcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Historicoopcionc findHistoricoopcionc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicoopcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoopcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Historicoopcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
