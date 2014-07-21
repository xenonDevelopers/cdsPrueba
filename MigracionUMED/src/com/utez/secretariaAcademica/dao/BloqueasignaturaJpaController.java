/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Bloqueasignatura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class BloqueasignaturaJpaController implements Serializable {

    public BloqueasignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bloqueasignatura bloqueasignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura idasignatura = bloqueasignatura.getIdasignatura();
            if (idasignatura != null) {
                idasignatura = em.getReference(idasignatura.getClass(), idasignatura.getIdasignatura());
                bloqueasignatura.setIdasignatura(idasignatura);
            }
            em.persist(bloqueasignatura);
            if (idasignatura != null) {
                idasignatura.getBloqueasignaturaList().add(bloqueasignatura);
                idasignatura = em.merge(idasignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bloqueasignatura bloqueasignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bloqueasignatura persistentBloqueasignatura = em.find(Bloqueasignatura.class, bloqueasignatura.getIdbloqueasignatura());
            Asignatura idasignaturaOld = persistentBloqueasignatura.getIdasignatura();
            Asignatura idasignaturaNew = bloqueasignatura.getIdasignatura();
            if (idasignaturaNew != null) {
                idasignaturaNew = em.getReference(idasignaturaNew.getClass(), idasignaturaNew.getIdasignatura());
                bloqueasignatura.setIdasignatura(idasignaturaNew);
            }
            bloqueasignatura = em.merge(bloqueasignatura);
            if (idasignaturaOld != null && !idasignaturaOld.equals(idasignaturaNew)) {
                idasignaturaOld.getBloqueasignaturaList().remove(bloqueasignatura);
                idasignaturaOld = em.merge(idasignaturaOld);
            }
            if (idasignaturaNew != null && !idasignaturaNew.equals(idasignaturaOld)) {
                idasignaturaNew.getBloqueasignaturaList().add(bloqueasignatura);
                idasignaturaNew = em.merge(idasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bloqueasignatura.getIdbloqueasignatura();
                if (findBloqueasignatura(id) == null) {
                    throw new NonexistentEntityException("The bloqueasignatura with id " + id + " no longer exists.");
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
            Bloqueasignatura bloqueasignatura;
            try {
                bloqueasignatura = em.getReference(Bloqueasignatura.class, id);
                bloqueasignatura.getIdbloqueasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bloqueasignatura with id " + id + " no longer exists.", enfe);
            }
            Asignatura idasignatura = bloqueasignatura.getIdasignatura();
            if (idasignatura != null) {
                idasignatura.getBloqueasignaturaList().remove(bloqueasignatura);
                idasignatura = em.merge(idasignatura);
            }
            em.remove(bloqueasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bloqueasignatura> findBloqueasignaturaEntities() {
        return findBloqueasignaturaEntities(true, -1, -1);
    }

    public List<Bloqueasignatura> findBloqueasignaturaEntities(int maxResults, int firstResult) {
        return findBloqueasignaturaEntities(false, maxResults, firstResult);
    }

    private List<Bloqueasignatura> findBloqueasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Bloqueasignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bloqueasignatura findBloqueasignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bloqueasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getBloqueasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Bloqueasignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
