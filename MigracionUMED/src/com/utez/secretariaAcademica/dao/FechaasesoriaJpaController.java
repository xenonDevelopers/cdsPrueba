/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asesoriaasignatura;
import com.utez.secretariaAcademica.entity.Fechaasesoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechaasesoriaJpaController implements Serializable {

    public FechaasesoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechaasesoria fechaasesoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoriaasignatura idasesoriaasignatura = fechaasesoria.getIdasesoriaasignatura();
            if (idasesoriaasignatura != null) {
                idasesoriaasignatura = em.getReference(idasesoriaasignatura.getClass(), idasesoriaasignatura.getIdasesoriaasignatura());
                fechaasesoria.setIdasesoriaasignatura(idasesoriaasignatura);
            }
            em.persist(fechaasesoria);
            if (idasesoriaasignatura != null) {
                idasesoriaasignatura.getFechaasesoriaList().add(fechaasesoria);
                idasesoriaasignatura = em.merge(idasesoriaasignatura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechaasesoria fechaasesoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechaasesoria persistentFechaasesoria = em.find(Fechaasesoria.class, fechaasesoria.getIdfechaasesoria());
            Asesoriaasignatura idasesoriaasignaturaOld = persistentFechaasesoria.getIdasesoriaasignatura();
            Asesoriaasignatura idasesoriaasignaturaNew = fechaasesoria.getIdasesoriaasignatura();
            if (idasesoriaasignaturaNew != null) {
                idasesoriaasignaturaNew = em.getReference(idasesoriaasignaturaNew.getClass(), idasesoriaasignaturaNew.getIdasesoriaasignatura());
                fechaasesoria.setIdasesoriaasignatura(idasesoriaasignaturaNew);
            }
            fechaasesoria = em.merge(fechaasesoria);
            if (idasesoriaasignaturaOld != null && !idasesoriaasignaturaOld.equals(idasesoriaasignaturaNew)) {
                idasesoriaasignaturaOld.getFechaasesoriaList().remove(fechaasesoria);
                idasesoriaasignaturaOld = em.merge(idasesoriaasignaturaOld);
            }
            if (idasesoriaasignaturaNew != null && !idasesoriaasignaturaNew.equals(idasesoriaasignaturaOld)) {
                idasesoriaasignaturaNew.getFechaasesoriaList().add(fechaasesoria);
                idasesoriaasignaturaNew = em.merge(idasesoriaasignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechaasesoria.getIdfechaasesoria();
                if (findFechaasesoria(id) == null) {
                    throw new NonexistentEntityException("The fechaasesoria with id " + id + " no longer exists.");
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
            Fechaasesoria fechaasesoria;
            try {
                fechaasesoria = em.getReference(Fechaasesoria.class, id);
                fechaasesoria.getIdfechaasesoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaasesoria with id " + id + " no longer exists.", enfe);
            }
            Asesoriaasignatura idasesoriaasignatura = fechaasesoria.getIdasesoriaasignatura();
            if (idasesoriaasignatura != null) {
                idasesoriaasignatura.getFechaasesoriaList().remove(fechaasesoria);
                idasesoriaasignatura = em.merge(idasesoriaasignatura);
            }
            em.remove(fechaasesoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechaasesoria> findFechaasesoriaEntities() {
        return findFechaasesoriaEntities(true, -1, -1);
    }

    public List<Fechaasesoria> findFechaasesoriaEntities(int maxResults, int firstResult) {
        return findFechaasesoriaEntities(false, maxResults, firstResult);
    }

    private List<Fechaasesoria> findFechaasesoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechaasesoria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechaasesoria findFechaasesoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechaasesoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaasesoriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechaasesoria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
