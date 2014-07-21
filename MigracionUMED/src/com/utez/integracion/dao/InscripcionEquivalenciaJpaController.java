/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.InscripcionEquivalencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class InscripcionEquivalenciaJpaController implements Serializable {

    public InscripcionEquivalenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InscripcionEquivalencia inscripcionEquivalencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno matricula = inscripcionEquivalencia.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                inscripcionEquivalencia.setMatricula(matricula);
            }
            em.persist(inscripcionEquivalencia);
            if (matricula != null) {
                matricula.getInscripcionEquivalenciaList().add(inscripcionEquivalencia);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InscripcionEquivalencia inscripcionEquivalencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InscripcionEquivalencia persistentInscripcionEquivalencia = em.find(InscripcionEquivalencia.class, inscripcionEquivalencia.getIdInscripcionequivalencia());
            Alumno matriculaOld = persistentInscripcionEquivalencia.getMatricula();
            Alumno matriculaNew = inscripcionEquivalencia.getMatricula();
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                inscripcionEquivalencia.setMatricula(matriculaNew);
            }
            inscripcionEquivalencia = em.merge(inscripcionEquivalencia);
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getInscripcionEquivalenciaList().remove(inscripcionEquivalencia);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getInscripcionEquivalenciaList().add(inscripcionEquivalencia);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inscripcionEquivalencia.getIdInscripcionequivalencia();
                if (findInscripcionEquivalencia(id) == null) {
                    throw new NonexistentEntityException("The inscripcionEquivalencia with id " + id + " no longer exists.");
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
            InscripcionEquivalencia inscripcionEquivalencia;
            try {
                inscripcionEquivalencia = em.getReference(InscripcionEquivalencia.class, id);
                inscripcionEquivalencia.getIdInscripcionequivalencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inscripcionEquivalencia with id " + id + " no longer exists.", enfe);
            }
            Alumno matricula = inscripcionEquivalencia.getMatricula();
            if (matricula != null) {
                matricula.getInscripcionEquivalenciaList().remove(inscripcionEquivalencia);
                matricula = em.merge(matricula);
            }
            em.remove(inscripcionEquivalencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InscripcionEquivalencia> findInscripcionEquivalenciaEntities() {
        return findInscripcionEquivalenciaEntities(true, -1, -1);
    }

    public List<InscripcionEquivalencia> findInscripcionEquivalenciaEntities(int maxResults, int firstResult) {
        return findInscripcionEquivalenciaEntities(false, maxResults, firstResult);
    }

    private List<InscripcionEquivalencia> findInscripcionEquivalenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from InscripcionEquivalencia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public InscripcionEquivalencia findInscripcionEquivalencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InscripcionEquivalencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getInscripcionEquivalenciaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from InscripcionEquivalencia as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
