/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionGrupoencuesta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.integracion.entity.Encuesta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionGrupoencuestaJpaController implements Serializable {

    public AsignacionGrupoencuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionGrupoencuesta asignacionGrupoencuesta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo idGrupo = asignacionGrupoencuesta.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                asignacionGrupoencuesta.setIdGrupo(idGrupo);
            }
            Encuesta idEncuesta = asignacionGrupoencuesta.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta = em.getReference(idEncuesta.getClass(), idEncuesta.getIdEncuesta());
                asignacionGrupoencuesta.setIdEncuesta(idEncuesta);
            }
            em.persist(asignacionGrupoencuesta);
            if (idGrupo != null) {
                idGrupo.getAsignacionGrupoencuestaList().add(asignacionGrupoencuesta);
                idGrupo = em.merge(idGrupo);
            }
            if (idEncuesta != null) {
                idEncuesta.getAsignacionGrupoencuestaList().add(asignacionGrupoencuesta);
                idEncuesta = em.merge(idEncuesta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionGrupoencuesta asignacionGrupoencuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionGrupoencuesta persistentAsignacionGrupoencuesta = em.find(AsignacionGrupoencuesta.class, asignacionGrupoencuesta.getIdAsignaciongrupoencuesta());
            Grupo idGrupoOld = persistentAsignacionGrupoencuesta.getIdGrupo();
            Grupo idGrupoNew = asignacionGrupoencuesta.getIdGrupo();
            Encuesta idEncuestaOld = persistentAsignacionGrupoencuesta.getIdEncuesta();
            Encuesta idEncuestaNew = asignacionGrupoencuesta.getIdEncuesta();
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                asignacionGrupoencuesta.setIdGrupo(idGrupoNew);
            }
            if (idEncuestaNew != null) {
                idEncuestaNew = em.getReference(idEncuestaNew.getClass(), idEncuestaNew.getIdEncuesta());
                asignacionGrupoencuesta.setIdEncuesta(idEncuestaNew);
            }
            asignacionGrupoencuesta = em.merge(asignacionGrupoencuesta);
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuesta);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getAsignacionGrupoencuestaList().add(asignacionGrupoencuesta);
                idGrupoNew = em.merge(idGrupoNew);
            }
            if (idEncuestaOld != null && !idEncuestaOld.equals(idEncuestaNew)) {
                idEncuestaOld.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuesta);
                idEncuestaOld = em.merge(idEncuestaOld);
            }
            if (idEncuestaNew != null && !idEncuestaNew.equals(idEncuestaOld)) {
                idEncuestaNew.getAsignacionGrupoencuestaList().add(asignacionGrupoencuesta);
                idEncuestaNew = em.merge(idEncuestaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionGrupoencuesta.getIdAsignaciongrupoencuesta();
                if (findAsignacionGrupoencuesta(id) == null) {
                    throw new NonexistentEntityException("The asignacionGrupoencuesta with id " + id + " no longer exists.");
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
            AsignacionGrupoencuesta asignacionGrupoencuesta;
            try {
                asignacionGrupoencuesta = em.getReference(AsignacionGrupoencuesta.class, id);
                asignacionGrupoencuesta.getIdAsignaciongrupoencuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionGrupoencuesta with id " + id + " no longer exists.", enfe);
            }
            Grupo idGrupo = asignacionGrupoencuesta.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuesta);
                idGrupo = em.merge(idGrupo);
            }
            Encuesta idEncuesta = asignacionGrupoencuesta.getIdEncuesta();
            if (idEncuesta != null) {
                idEncuesta.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuesta);
                idEncuesta = em.merge(idEncuesta);
            }
            em.remove(asignacionGrupoencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionGrupoencuesta> findAsignacionGrupoencuestaEntities() {
        return findAsignacionGrupoencuestaEntities(true, -1, -1);
    }

    public List<AsignacionGrupoencuesta> findAsignacionGrupoencuestaEntities(int maxResults, int firstResult) {
        return findAsignacionGrupoencuestaEntities(false, maxResults, firstResult);
    }

    private List<AsignacionGrupoencuesta> findAsignacionGrupoencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionGrupoencuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionGrupoencuesta findAsignacionGrupoencuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionGrupoencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionGrupoencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionGrupoencuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
