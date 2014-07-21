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
import com.utez.integracion.entity.ActividadIntegradora;
import com.utez.integracion.entity.AsignacionIntegradoragrupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionIntegradoragrupoJpaController implements Serializable {

    public AsignacionIntegradoragrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionIntegradoragrupo asignacionIntegradoragrupo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioAsignatura idCalendarioasignatura = asignacionIntegradoragrupo.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura = em.getReference(idCalendarioasignatura.getClass(), idCalendarioasignatura.getIdCalendarioasignatura());
                asignacionIntegradoragrupo.setIdCalendarioasignatura(idCalendarioasignatura);
            }
            ActividadIntegradora idActividadintegradora = asignacionIntegradoragrupo.getIdActividadintegradora();
            if (idActividadintegradora != null) {
                idActividadintegradora = em.getReference(idActividadintegradora.getClass(), idActividadintegradora.getIdActividadintegradora());
                asignacionIntegradoragrupo.setIdActividadintegradora(idActividadintegradora);
            }
            em.persist(asignacionIntegradoragrupo);
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getAsignacionIntegradoragrupoList().add(asignacionIntegradoragrupo);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            if (idActividadintegradora != null) {
                idActividadintegradora.getAsignacionIntegradoragrupoList().add(asignacionIntegradoragrupo);
                idActividadintegradora = em.merge(idActividadintegradora);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionIntegradoragrupo asignacionIntegradoragrupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionIntegradoragrupo persistentAsignacionIntegradoragrupo = em.find(AsignacionIntegradoragrupo.class, asignacionIntegradoragrupo.getIdAsignacionintegradoragrupo());
            CalendarioAsignatura idCalendarioasignaturaOld = persistentAsignacionIntegradoragrupo.getIdCalendarioasignatura();
            CalendarioAsignatura idCalendarioasignaturaNew = asignacionIntegradoragrupo.getIdCalendarioasignatura();
            ActividadIntegradora idActividadintegradoraOld = persistentAsignacionIntegradoragrupo.getIdActividadintegradora();
            ActividadIntegradora idActividadintegradoraNew = asignacionIntegradoragrupo.getIdActividadintegradora();
            if (idCalendarioasignaturaNew != null) {
                idCalendarioasignaturaNew = em.getReference(idCalendarioasignaturaNew.getClass(), idCalendarioasignaturaNew.getIdCalendarioasignatura());
                asignacionIntegradoragrupo.setIdCalendarioasignatura(idCalendarioasignaturaNew);
            }
            if (idActividadintegradoraNew != null) {
                idActividadintegradoraNew = em.getReference(idActividadintegradoraNew.getClass(), idActividadintegradoraNew.getIdActividadintegradora());
                asignacionIntegradoragrupo.setIdActividadintegradora(idActividadintegradoraNew);
            }
            asignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupo);
            if (idCalendarioasignaturaOld != null && !idCalendarioasignaturaOld.equals(idCalendarioasignaturaNew)) {
                idCalendarioasignaturaOld.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupo);
                idCalendarioasignaturaOld = em.merge(idCalendarioasignaturaOld);
            }
            if (idCalendarioasignaturaNew != null && !idCalendarioasignaturaNew.equals(idCalendarioasignaturaOld)) {
                idCalendarioasignaturaNew.getAsignacionIntegradoragrupoList().add(asignacionIntegradoragrupo);
                idCalendarioasignaturaNew = em.merge(idCalendarioasignaturaNew);
            }
            if (idActividadintegradoraOld != null && !idActividadintegradoraOld.equals(idActividadintegradoraNew)) {
                idActividadintegradoraOld.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupo);
                idActividadintegradoraOld = em.merge(idActividadintegradoraOld);
            }
            if (idActividadintegradoraNew != null && !idActividadintegradoraNew.equals(idActividadintegradoraOld)) {
                idActividadintegradoraNew.getAsignacionIntegradoragrupoList().add(asignacionIntegradoragrupo);
                idActividadintegradoraNew = em.merge(idActividadintegradoraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionIntegradoragrupo.getIdAsignacionintegradoragrupo();
                if (findAsignacionIntegradoragrupo(id) == null) {
                    throw new NonexistentEntityException("The asignacionIntegradoragrupo with id " + id + " no longer exists.");
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
            AsignacionIntegradoragrupo asignacionIntegradoragrupo;
            try {
                asignacionIntegradoragrupo = em.getReference(AsignacionIntegradoragrupo.class, id);
                asignacionIntegradoragrupo.getIdAsignacionintegradoragrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionIntegradoragrupo with id " + id + " no longer exists.", enfe);
            }
            CalendarioAsignatura idCalendarioasignatura = asignacionIntegradoragrupo.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupo);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            ActividadIntegradora idActividadintegradora = asignacionIntegradoragrupo.getIdActividadintegradora();
            if (idActividadintegradora != null) {
                idActividadintegradora.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupo);
                idActividadintegradora = em.merge(idActividadintegradora);
            }
            em.remove(asignacionIntegradoragrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionIntegradoragrupo> findAsignacionIntegradoragrupoEntities() {
        return findAsignacionIntegradoragrupoEntities(true, -1, -1);
    }

    public List<AsignacionIntegradoragrupo> findAsignacionIntegradoragrupoEntities(int maxResults, int firstResult) {
        return findAsignacionIntegradoragrupoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionIntegradoragrupo> findAsignacionIntegradoragrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionIntegradoragrupo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionIntegradoragrupo findAsignacionIntegradoragrupo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionIntegradoragrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionIntegradoragrupoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionIntegradoragrupo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
