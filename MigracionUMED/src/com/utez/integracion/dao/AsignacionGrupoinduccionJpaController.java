/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionGrupoinduccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionGrupoinduccionJpaController implements Serializable {

    public AsignacionGrupoinduccionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionGrupoinduccion asignacionGrupoinduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoInduccion idGrupoinduccion = asignacionGrupoinduccion.getIdGrupoinduccion();
            if (idGrupoinduccion != null) {
                idGrupoinduccion = em.getReference(idGrupoinduccion.getClass(), idGrupoinduccion.getIdGrupoinduccion());
                asignacionGrupoinduccion.setIdGrupoinduccion(idGrupoinduccion);
            }
            Alumno matricula = asignacionGrupoinduccion.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                asignacionGrupoinduccion.setMatricula(matricula);
            }
            em.persist(asignacionGrupoinduccion);
            if (idGrupoinduccion != null) {
                idGrupoinduccion.getAsignacionGrupoinduccionList().add(asignacionGrupoinduccion);
                idGrupoinduccion = em.merge(idGrupoinduccion);
            }
            if (matricula != null) {
                matricula.getAsignacionGrupoinduccionList().add(asignacionGrupoinduccion);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionGrupoinduccion asignacionGrupoinduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionGrupoinduccion persistentAsignacionGrupoinduccion = em.find(AsignacionGrupoinduccion.class, asignacionGrupoinduccion.getIdAsignacionGrupoinduccion());
            GrupoInduccion idGrupoinduccionOld = persistentAsignacionGrupoinduccion.getIdGrupoinduccion();
            GrupoInduccion idGrupoinduccionNew = asignacionGrupoinduccion.getIdGrupoinduccion();
            Alumno matriculaOld = persistentAsignacionGrupoinduccion.getMatricula();
            Alumno matriculaNew = asignacionGrupoinduccion.getMatricula();
            if (idGrupoinduccionNew != null) {
                idGrupoinduccionNew = em.getReference(idGrupoinduccionNew.getClass(), idGrupoinduccionNew.getIdGrupoinduccion());
                asignacionGrupoinduccion.setIdGrupoinduccion(idGrupoinduccionNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                asignacionGrupoinduccion.setMatricula(matriculaNew);
            }
            asignacionGrupoinduccion = em.merge(asignacionGrupoinduccion);
            if (idGrupoinduccionOld != null && !idGrupoinduccionOld.equals(idGrupoinduccionNew)) {
                idGrupoinduccionOld.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccion);
                idGrupoinduccionOld = em.merge(idGrupoinduccionOld);
            }
            if (idGrupoinduccionNew != null && !idGrupoinduccionNew.equals(idGrupoinduccionOld)) {
                idGrupoinduccionNew.getAsignacionGrupoinduccionList().add(asignacionGrupoinduccion);
                idGrupoinduccionNew = em.merge(idGrupoinduccionNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccion);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAsignacionGrupoinduccionList().add(asignacionGrupoinduccion);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionGrupoinduccion.getIdAsignacionGrupoinduccion();
                if (findAsignacionGrupoinduccion(id) == null) {
                    throw new NonexistentEntityException("The asignacionGrupoinduccion with id " + id + " no longer exists.");
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
            AsignacionGrupoinduccion asignacionGrupoinduccion;
            try {
                asignacionGrupoinduccion = em.getReference(AsignacionGrupoinduccion.class, id);
                asignacionGrupoinduccion.getIdAsignacionGrupoinduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionGrupoinduccion with id " + id + " no longer exists.", enfe);
            }
            GrupoInduccion idGrupoinduccion = asignacionGrupoinduccion.getIdGrupoinduccion();
            if (idGrupoinduccion != null) {
                idGrupoinduccion.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccion);
                idGrupoinduccion = em.merge(idGrupoinduccion);
            }
            Alumno matricula = asignacionGrupoinduccion.getMatricula();
            if (matricula != null) {
                matricula.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccion);
                matricula = em.merge(matricula);
            }
            em.remove(asignacionGrupoinduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionGrupoinduccion> findAsignacionGrupoinduccionEntities() {
        return findAsignacionGrupoinduccionEntities(true, -1, -1);
    }

    public List<AsignacionGrupoinduccion> findAsignacionGrupoinduccionEntities(int maxResults, int firstResult) {
        return findAsignacionGrupoinduccionEntities(false, maxResults, firstResult);
    }

    private List<AsignacionGrupoinduccion> findAsignacionGrupoinduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionGrupoinduccion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionGrupoinduccion findAsignacionGrupoinduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionGrupoinduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionGrupoinduccionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionGrupoinduccion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
