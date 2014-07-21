/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.integracion.entity.ActividadIntegradora;
import com.utez.integracion.entity.AsignacionAsignaturaintegradora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAsignaturaintegradoraJpaController implements Serializable {

    public AsignacionAsignaturaintegradoraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAsignaturaintegradora asignacionAsignaturaintegradora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura idAsignatura = asignacionAsignaturaintegradora.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                asignacionAsignaturaintegradora.setIdAsignatura(idAsignatura);
            }
            ActividadIntegradora idActividadintegradora = asignacionAsignaturaintegradora.getIdActividadintegradora();
            if (idActividadintegradora != null) {
                idActividadintegradora = em.getReference(idActividadintegradora.getClass(), idActividadintegradora.getIdActividadintegradora());
                asignacionAsignaturaintegradora.setIdActividadintegradora(idActividadintegradora);
            }
            em.persist(asignacionAsignaturaintegradora);
            if (idAsignatura != null) {
                idAsignatura.getAsignacionAsignaturaintegradoraList().add(asignacionAsignaturaintegradora);
                idAsignatura = em.merge(idAsignatura);
            }
            if (idActividadintegradora != null) {
                idActividadintegradora.getAsignacionAsignaturaintegradoraList().add(asignacionAsignaturaintegradora);
                idActividadintegradora = em.merge(idActividadintegradora);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAsignaturaintegradora asignacionAsignaturaintegradora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAsignaturaintegradora persistentAsignacionAsignaturaintegradora = em.find(AsignacionAsignaturaintegradora.class, asignacionAsignaturaintegradora.getIdAsignacionasignaturaintegradora());
            Asignatura idAsignaturaOld = persistentAsignacionAsignaturaintegradora.getIdAsignatura();
            Asignatura idAsignaturaNew = asignacionAsignaturaintegradora.getIdAsignatura();
            ActividadIntegradora idActividadintegradoraOld = persistentAsignacionAsignaturaintegradora.getIdActividadintegradora();
            ActividadIntegradora idActividadintegradoraNew = asignacionAsignaturaintegradora.getIdActividadintegradora();
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                asignacionAsignaturaintegradora.setIdAsignatura(idAsignaturaNew);
            }
            if (idActividadintegradoraNew != null) {
                idActividadintegradoraNew = em.getReference(idActividadintegradoraNew.getClass(), idActividadintegradoraNew.getIdActividadintegradora());
                asignacionAsignaturaintegradora.setIdActividadintegradora(idActividadintegradoraNew);
            }
            asignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradora);
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradora);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getAsignacionAsignaturaintegradoraList().add(asignacionAsignaturaintegradora);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            if (idActividadintegradoraOld != null && !idActividadintegradoraOld.equals(idActividadintegradoraNew)) {
                idActividadintegradoraOld.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradora);
                idActividadintegradoraOld = em.merge(idActividadintegradoraOld);
            }
            if (idActividadintegradoraNew != null && !idActividadintegradoraNew.equals(idActividadintegradoraOld)) {
                idActividadintegradoraNew.getAsignacionAsignaturaintegradoraList().add(asignacionAsignaturaintegradora);
                idActividadintegradoraNew = em.merge(idActividadintegradoraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAsignaturaintegradora.getIdAsignacionasignaturaintegradora();
                if (findAsignacionAsignaturaintegradora(id) == null) {
                    throw new NonexistentEntityException("The asignacionAsignaturaintegradora with id " + id + " no longer exists.");
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
            AsignacionAsignaturaintegradora asignacionAsignaturaintegradora;
            try {
                asignacionAsignaturaintegradora = em.getReference(AsignacionAsignaturaintegradora.class, id);
                asignacionAsignaturaintegradora.getIdAsignacionasignaturaintegradora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAsignaturaintegradora with id " + id + " no longer exists.", enfe);
            }
            Asignatura idAsignatura = asignacionAsignaturaintegradora.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradora);
                idAsignatura = em.merge(idAsignatura);
            }
            ActividadIntegradora idActividadintegradora = asignacionAsignaturaintegradora.getIdActividadintegradora();
            if (idActividadintegradora != null) {
                idActividadintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradora);
                idActividadintegradora = em.merge(idActividadintegradora);
            }
            em.remove(asignacionAsignaturaintegradora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAsignaturaintegradora> findAsignacionAsignaturaintegradoraEntities() {
        return findAsignacionAsignaturaintegradoraEntities(true, -1, -1);
    }

    public List<AsignacionAsignaturaintegradora> findAsignacionAsignaturaintegradoraEntities(int maxResults, int firstResult) {
        return findAsignacionAsignaturaintegradoraEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAsignaturaintegradora> findAsignacionAsignaturaintegradoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAsignaturaintegradora as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAsignaturaintegradora findAsignacionAsignaturaintegradora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAsignaturaintegradora.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAsignaturaintegradoraCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAsignaturaintegradora as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
