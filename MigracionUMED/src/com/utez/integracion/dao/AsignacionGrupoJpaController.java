/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.AsignacionGrupo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionGrupoJpaController implements Serializable {

    public AsignacionGrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionGrupo asignacionGrupo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo idGrupo = asignacionGrupo.getIdGrupo();
            if (idGrupo != null) {
                idGrupo = em.getReference(idGrupo.getClass(), idGrupo.getIdgrupo());
                asignacionGrupo.setIdGrupo(idGrupo);
            }
            Alumno matricula = asignacionGrupo.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatricula());
                asignacionGrupo.setMatricula(matricula);
            }
            em.persist(asignacionGrupo);
            if (idGrupo != null) {
                idGrupo.getAsignacionGrupoList().add(asignacionGrupo);
                idGrupo = em.merge(idGrupo);
            }
            if (matricula != null) {
                matricula.getAsignacionGrupoList().add(asignacionGrupo);
                matricula = em.merge(matricula);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionGrupo asignacionGrupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionGrupo persistentAsignacionGrupo = em.find(AsignacionGrupo.class, asignacionGrupo.getIdAsignaciongrupo());
            Grupo idGrupoOld = persistentAsignacionGrupo.getIdGrupo();
            Grupo idGrupoNew = asignacionGrupo.getIdGrupo();
            Alumno matriculaOld = persistentAsignacionGrupo.getMatricula();
            Alumno matriculaNew = asignacionGrupo.getMatricula();
            if (idGrupoNew != null) {
                idGrupoNew = em.getReference(idGrupoNew.getClass(), idGrupoNew.getIdgrupo());
                asignacionGrupo.setIdGrupo(idGrupoNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatricula());
                asignacionGrupo.setMatricula(matriculaNew);
            }
            asignacionGrupo = em.merge(asignacionGrupo);
            if (idGrupoOld != null && !idGrupoOld.equals(idGrupoNew)) {
                idGrupoOld.getAsignacionGrupoList().remove(asignacionGrupo);
                idGrupoOld = em.merge(idGrupoOld);
            }
            if (idGrupoNew != null && !idGrupoNew.equals(idGrupoOld)) {
                idGrupoNew.getAsignacionGrupoList().add(asignacionGrupo);
                idGrupoNew = em.merge(idGrupoNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getAsignacionGrupoList().remove(asignacionGrupo);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getAsignacionGrupoList().add(asignacionGrupo);
                matriculaNew = em.merge(matriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionGrupo.getIdAsignaciongrupo();
                if (findAsignacionGrupo(id) == null) {
                    throw new NonexistentEntityException("The asignacionGrupo with id " + id + " no longer exists.");
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
            AsignacionGrupo asignacionGrupo;
            try {
                asignacionGrupo = em.getReference(AsignacionGrupo.class, id);
                asignacionGrupo.getIdAsignaciongrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionGrupo with id " + id + " no longer exists.", enfe);
            }
            Grupo idGrupo = asignacionGrupo.getIdGrupo();
            if (idGrupo != null) {
                idGrupo.getAsignacionGrupoList().remove(asignacionGrupo);
                idGrupo = em.merge(idGrupo);
            }
            Alumno matricula = asignacionGrupo.getMatricula();
            if (matricula != null) {
                matricula.getAsignacionGrupoList().remove(asignacionGrupo);
                matricula = em.merge(matricula);
            }
            em.remove(asignacionGrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionGrupo> findAsignacionGrupoEntities() {
        return findAsignacionGrupoEntities(true, -1, -1);
    }

    public List<AsignacionGrupo> findAsignacionGrupoEntities(int maxResults, int firstResult) {
        return findAsignacionGrupoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionGrupo> findAsignacionGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionGrupo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionGrupo findAsignacionGrupo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionGrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionGrupo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
