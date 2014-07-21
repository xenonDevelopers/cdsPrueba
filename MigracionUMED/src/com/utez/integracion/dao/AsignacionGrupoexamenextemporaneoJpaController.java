/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionGrupoexamenextemporaneo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.GrupoExamenextemporaneo;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionGrupoexamenextemporaneoJpaController implements Serializable {

    public AsignacionGrupoexamenextemporaneoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoExamenextemporaneo idGrupoexamenextemporaneo = asignacionGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo();
            if (idGrupoexamenextemporaneo != null) {
                idGrupoexamenextemporaneo = em.getReference(idGrupoexamenextemporaneo.getClass(), idGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo());
                asignacionGrupoexamenextemporaneo.setIdGrupoexamenextemporaneo(idGrupoexamenextemporaneo);
            }
            Alumno matricula = asignacionGrupoexamenextemporaneo.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                asignacionGrupoexamenextemporaneo.setMatricula(matricula);
            }
            em.persist(asignacionGrupoexamenextemporaneo);
            if (idGrupoexamenextemporaneo != null) {
                idGrupoexamenextemporaneo.getAsignacionGrupoexamenextemporaneoList().add(asignacionGrupoexamenextemporaneo);
                idGrupoexamenextemporaneo = em.merge(idGrupoexamenextemporaneo);
            }
            if (matricula != null) {
                matricula.getAsignacionGrupoexamenextemporaneoList().add(asignacionGrupoexamenextemporaneo);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionGrupoexamenextemporaneo persistentAsignacionGrupoexamenextemporaneo = em.find(AsignacionGrupoexamenextemporaneo.class, asignacionGrupoexamenextemporaneo.getIdAsignaciongrupoexamenextemporaneo());
            GrupoExamenextemporaneo idGrupoexamenextemporaneoOld = persistentAsignacionGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo();
            GrupoExamenextemporaneo idGrupoexamenextemporaneoNew = asignacionGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo();
            Alumno matriculaOld = persistentAsignacionGrupoexamenextemporaneo.getMatricula();
            Alumno matriculaNew = asignacionGrupoexamenextemporaneo.getMatricula();
            if (idGrupoexamenextemporaneoNew != null) {
                idGrupoexamenextemporaneoNew = em.getReference(idGrupoexamenextemporaneoNew.getClass(), idGrupoexamenextemporaneoNew.getIdGrupoexamenextemporaneo());
                asignacionGrupoexamenextemporaneo.setIdGrupoexamenextemporaneo(idGrupoexamenextemporaneoNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                asignacionGrupoexamenextemporaneo.setMatricula(matriculaNew);
            }
            asignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneo);
            if (idGrupoexamenextemporaneoOld != null && !idGrupoexamenextemporaneoOld.equals(idGrupoexamenextemporaneoNew)) {
                idGrupoexamenextemporaneoOld.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneo);
                idGrupoexamenextemporaneoOld = em.merge(idGrupoexamenextemporaneoOld);
            }
            if (idGrupoexamenextemporaneoNew != null && !idGrupoexamenextemporaneoNew.equals(idGrupoexamenextemporaneoOld)) {
                idGrupoexamenextemporaneoNew.getAsignacionGrupoexamenextemporaneoList().add(asignacionGrupoexamenextemporaneo);
                idGrupoexamenextemporaneoNew = em.merge(idGrupoexamenextemporaneoNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneo);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAsignacionGrupoexamenextemporaneoList().add(asignacionGrupoexamenextemporaneo);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionGrupoexamenextemporaneo.getIdAsignaciongrupoexamenextemporaneo();
                if (findAsignacionGrupoexamenextemporaneo(id) == null) {
                    throw new NonexistentEntityException("The asignacionGrupoexamenextemporaneo with id " + id + " no longer exists.");
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
            AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneo;
            try {
                asignacionGrupoexamenextemporaneo = em.getReference(AsignacionGrupoexamenextemporaneo.class, id);
                asignacionGrupoexamenextemporaneo.getIdAsignaciongrupoexamenextemporaneo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionGrupoexamenextemporaneo with id " + id + " no longer exists.", enfe);
            }
            GrupoExamenextemporaneo idGrupoexamenextemporaneo = asignacionGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo();
            if (idGrupoexamenextemporaneo != null) {
                idGrupoexamenextemporaneo.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneo);
                idGrupoexamenextemporaneo = em.merge(idGrupoexamenextemporaneo);
            }
            Alumno matricula = asignacionGrupoexamenextemporaneo.getMatricula();
            if (matricula != null) {
                matricula.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneo);
                matricula = em.merge(matricula);
            }
            em.remove(asignacionGrupoexamenextemporaneo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionGrupoexamenextemporaneo> findAsignacionGrupoexamenextemporaneoEntities() {
        return findAsignacionGrupoexamenextemporaneoEntities(true, -1, -1);
    }

    public List<AsignacionGrupoexamenextemporaneo> findAsignacionGrupoexamenextemporaneoEntities(int maxResults, int firstResult) {
        return findAsignacionGrupoexamenextemporaneoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionGrupoexamenextemporaneo> findAsignacionGrupoexamenextemporaneoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionGrupoexamenextemporaneo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionGrupoexamenextemporaneo findAsignacionGrupoexamenextemporaneo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionGrupoexamenextemporaneo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionGrupoexamenextemporaneoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionGrupoexamenextemporaneo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
