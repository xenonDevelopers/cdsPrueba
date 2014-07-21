/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.SeguimientoAlumno;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SeguimientoAlumnoJpaController implements Serializable {

    public SeguimientoAlumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeguimientoAlumno seguimientoAlumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idResponsable = seguimientoAlumno.getIdResponsable();
            if (idResponsable != null) {
                idResponsable = em.getReference(idResponsable.getClass(), idResponsable.getIdRecursohumano());
                seguimientoAlumno.setIdResponsable(idResponsable);
            }
            Alumno matricula = seguimientoAlumno.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                seguimientoAlumno.setMatricula(matricula);
            }
            em.persist(seguimientoAlumno);
            if (idResponsable != null) {
                idResponsable.getSeguimientoAlumnoList().add(seguimientoAlumno);
                idResponsable = em.merge(idResponsable);
            }
            if (matricula != null) {
                matricula.getSeguimientoAlumnoList().add(seguimientoAlumno);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeguimientoAlumno seguimientoAlumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SeguimientoAlumno persistentSeguimientoAlumno = em.find(SeguimientoAlumno.class, seguimientoAlumno.getIdSeguimiento());
            RecursoHumano idResponsableOld = persistentSeguimientoAlumno.getIdResponsable();
            RecursoHumano idResponsableNew = seguimientoAlumno.getIdResponsable();
            Alumno matriculaOld = persistentSeguimientoAlumno.getMatricula();
            Alumno matriculaNew = seguimientoAlumno.getMatricula();
            if (idResponsableNew != null) {
                idResponsableNew = em.getReference(idResponsableNew.getClass(), idResponsableNew.getIdRecursohumano());
                seguimientoAlumno.setIdResponsable(idResponsableNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                seguimientoAlumno.setMatricula(matriculaNew);
            }
            seguimientoAlumno = em.merge(seguimientoAlumno);
            if (idResponsableOld != null && !idResponsableOld.equals(idResponsableNew)) {
                idResponsableOld.getSeguimientoAlumnoList().remove(seguimientoAlumno);
                idResponsableOld = em.merge(idResponsableOld);
            }
            if (idResponsableNew != null && !idResponsableNew.equals(idResponsableOld)) {
                idResponsableNew.getSeguimientoAlumnoList().add(seguimientoAlumno);
                idResponsableNew = em.merge(idResponsableNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getSeguimientoAlumnoList().remove(seguimientoAlumno);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getSeguimientoAlumnoList().add(seguimientoAlumno);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = seguimientoAlumno.getIdSeguimiento();
                if (findSeguimientoAlumno(id) == null) {
                    throw new NonexistentEntityException("The seguimientoAlumno with id " + id + " no longer exists.");
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
            SeguimientoAlumno seguimientoAlumno;
            try {
                seguimientoAlumno = em.getReference(SeguimientoAlumno.class, id);
                seguimientoAlumno.getIdSeguimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguimientoAlumno with id " + id + " no longer exists.", enfe);
            }
            RecursoHumano idResponsable = seguimientoAlumno.getIdResponsable();
            if (idResponsable != null) {
                idResponsable.getSeguimientoAlumnoList().remove(seguimientoAlumno);
                idResponsable = em.merge(idResponsable);
            }
            Alumno matricula = seguimientoAlumno.getMatricula();
            if (matricula != null) {
                matricula.getSeguimientoAlumnoList().remove(seguimientoAlumno);
                matricula = em.merge(matricula);
            }
            em.remove(seguimientoAlumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeguimientoAlumno> findSeguimientoAlumnoEntities() {
        return findSeguimientoAlumnoEntities(true, -1, -1);
    }

    public List<SeguimientoAlumno> findSeguimientoAlumnoEntities(int maxResults, int firstResult) {
        return findSeguimientoAlumnoEntities(false, maxResults, firstResult);
    }

    private List<SeguimientoAlumno> findSeguimientoAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SeguimientoAlumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SeguimientoAlumno findSeguimientoAlumno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeguimientoAlumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguimientoAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SeguimientoAlumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
