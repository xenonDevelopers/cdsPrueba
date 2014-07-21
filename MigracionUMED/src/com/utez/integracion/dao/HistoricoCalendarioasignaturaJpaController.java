/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.HistoricoCalendarioasignatura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistoricoCalendarioasignaturaJpaController implements Serializable {

    public HistoricoCalendarioasignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoricoCalendarioasignatura historicoCalendarioasignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioAsignatura idCalendarioasignatura = historicoCalendarioasignatura.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura = em.getReference(idCalendarioasignatura.getClass(), idCalendarioasignatura.getIdCalendarioasignatura());
                historicoCalendarioasignatura.setIdCalendarioasignatura(idCalendarioasignatura);
            }
            em.persist(historicoCalendarioasignatura);
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getHistoricoCalendarioasignaturaList().add(historicoCalendarioasignatura);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoricoCalendarioasignatura historicoCalendarioasignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricoCalendarioasignatura persistentHistoricoCalendarioasignatura = em.find(HistoricoCalendarioasignatura.class, historicoCalendarioasignatura.getIdHistoricocalendarioasignatura());
            CalendarioAsignatura idCalendarioasignaturaOld = persistentHistoricoCalendarioasignatura.getIdCalendarioasignatura();
            CalendarioAsignatura idCalendarioasignaturaNew = historicoCalendarioasignatura.getIdCalendarioasignatura();
            if (idCalendarioasignaturaNew != null) {
                idCalendarioasignaturaNew = em.getReference(idCalendarioasignaturaNew.getClass(), idCalendarioasignaturaNew.getIdCalendarioasignatura());
                historicoCalendarioasignatura.setIdCalendarioasignatura(idCalendarioasignaturaNew);
            }
            historicoCalendarioasignatura = em.merge(historicoCalendarioasignatura);
            if (idCalendarioasignaturaOld != null && !idCalendarioasignaturaOld.equals(idCalendarioasignaturaNew)) {
                idCalendarioasignaturaOld.getHistoricoCalendarioasignaturaList().remove(historicoCalendarioasignatura);
                idCalendarioasignaturaOld = em.merge(idCalendarioasignaturaOld);
            }
            if (idCalendarioasignaturaNew != null && !idCalendarioasignaturaNew.equals(idCalendarioasignaturaOld)) {
                idCalendarioasignaturaNew.getHistoricoCalendarioasignaturaList().add(historicoCalendarioasignatura);
                idCalendarioasignaturaNew = em.merge(idCalendarioasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = historicoCalendarioasignatura.getIdHistoricocalendarioasignatura();
                if (findHistoricoCalendarioasignatura(id) == null) {
                    throw new NonexistentEntityException("The historicoCalendarioasignatura with id " + id + " no longer exists.");
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
            HistoricoCalendarioasignatura historicoCalendarioasignatura;
            try {
                historicoCalendarioasignatura = em.getReference(HistoricoCalendarioasignatura.class, id);
                historicoCalendarioasignatura.getIdHistoricocalendarioasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoCalendarioasignatura with id " + id + " no longer exists.", enfe);
            }
            CalendarioAsignatura idCalendarioasignatura = historicoCalendarioasignatura.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getHistoricoCalendarioasignaturaList().remove(historicoCalendarioasignatura);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            em.remove(historicoCalendarioasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoricoCalendarioasignatura> findHistoricoCalendarioasignaturaEntities() {
        return findHistoricoCalendarioasignaturaEntities(true, -1, -1);
    }

    public List<HistoricoCalendarioasignatura> findHistoricoCalendarioasignaturaEntities(int maxResults, int firstResult) {
        return findHistoricoCalendarioasignaturaEntities(false, maxResults, firstResult);
    }

    private List<HistoricoCalendarioasignatura> findHistoricoCalendarioasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HistoricoCalendarioasignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoricoCalendarioasignatura findHistoricoCalendarioasignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoricoCalendarioasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoCalendarioasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HistoricoCalendarioasignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
