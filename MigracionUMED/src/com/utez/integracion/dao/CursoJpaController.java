/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Curso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Curso curso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = curso.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                curso.setIdRecursohumano(idRecursohumano);
            }
            em.persist(curso);
            if (idRecursohumano != null) {
                idRecursohumano.getCursoList().add(curso);
                idRecursohumano = em.merge(idRecursohumano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getIdCurso());
            RecursoHumano idRecursohumanoOld = persistentCurso.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = curso.getIdRecursohumano();
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                curso.setIdRecursohumano(idRecursohumanoNew);
            }
            curso = em.merge(curso);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getCursoList().remove(curso);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getCursoList().add(curso);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = curso.getIdCurso();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getIdCurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idRecursohumano = curso.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getCursoList().remove(curso);
                idRecursohumano = em.merge(idRecursohumano);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Curso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Curso findCurso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Curso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
