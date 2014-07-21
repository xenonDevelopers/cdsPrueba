/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Rol;
import com.utez.integracion.entity.Recurso;
import com.utez.integracion.entity.Permiso;
import com.utez.integracion.entity.RolRecursopermiso;
import com.utez.integracion.entity.RolRecursopermisoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RolRecursopermisoJpaController implements Serializable {

    public RolRecursopermisoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RolRecursopermiso rolRecursopermiso) throws PreexistingEntityException, Exception {
        if (rolRecursopermiso.getRolRecursopermisoPK() == null) {
            rolRecursopermiso.setRolRecursopermisoPK(new RolRecursopermisoPK());
        }
        rolRecursopermiso.getRolRecursopermisoPK().setIdRol(rolRecursopermiso.getRol().getIdRol());
        rolRecursopermiso.getRolRecursopermisoPK().setIdPermiso(rolRecursopermiso.getPermiso().getIdPermiso());
        rolRecursopermiso.getRolRecursopermisoPK().setIdRecurso(rolRecursopermiso.getRecurso().getIdRecurso());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol = rolRecursopermiso.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdRol());
                rolRecursopermiso.setRol(rol);
            }
            Recurso recurso = rolRecursopermiso.getRecurso();
            if (recurso != null) {
                recurso = em.getReference(recurso.getClass(), recurso.getIdRecurso());
                rolRecursopermiso.setRecurso(recurso);
            }
            Permiso permiso = rolRecursopermiso.getPermiso();
            if (permiso != null) {
                permiso = em.getReference(permiso.getClass(), permiso.getIdPermiso());
                rolRecursopermiso.setPermiso(permiso);
            }
            em.persist(rolRecursopermiso);
            if (rol != null) {
                rol.getRolRecursopermisoList().add(rolRecursopermiso);
                rol = em.merge(rol);
            }
            if (recurso != null) {
                recurso.getRolRecursopermisoList().add(rolRecursopermiso);
                recurso = em.merge(recurso);
            }
            if (permiso != null) {
                permiso.getRolRecursopermisoList().add(rolRecursopermiso);
                permiso = em.merge(permiso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRolRecursopermiso(rolRecursopermiso.getRolRecursopermisoPK()) != null) {
                throw new PreexistingEntityException("RolRecursopermiso " + rolRecursopermiso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolRecursopermiso rolRecursopermiso) throws NonexistentEntityException, Exception {
        rolRecursopermiso.getRolRecursopermisoPK().setIdRol(rolRecursopermiso.getRol().getIdRol());
        rolRecursopermiso.getRolRecursopermisoPK().setIdPermiso(rolRecursopermiso.getPermiso().getIdPermiso());
        rolRecursopermiso.getRolRecursopermisoPK().setIdRecurso(rolRecursopermiso.getRecurso().getIdRecurso());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolRecursopermiso persistentRolRecursopermiso = em.find(RolRecursopermiso.class, rolRecursopermiso.getRolRecursopermisoPK());
            Rol rolOld = persistentRolRecursopermiso.getRol();
            Rol rolNew = rolRecursopermiso.getRol();
            Recurso recursoOld = persistentRolRecursopermiso.getRecurso();
            Recurso recursoNew = rolRecursopermiso.getRecurso();
            Permiso permisoOld = persistentRolRecursopermiso.getPermiso();
            Permiso permisoNew = rolRecursopermiso.getPermiso();
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdRol());
                rolRecursopermiso.setRol(rolNew);
            }
            if (recursoNew != null) {
                recursoNew = em.getReference(recursoNew.getClass(), recursoNew.getIdRecurso());
                rolRecursopermiso.setRecurso(recursoNew);
            }
            if (permisoNew != null) {
                permisoNew = em.getReference(permisoNew.getClass(), permisoNew.getIdPermiso());
                rolRecursopermiso.setPermiso(permisoNew);
            }
            rolRecursopermiso = em.merge(rolRecursopermiso);
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getRolRecursopermisoList().remove(rolRecursopermiso);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getRolRecursopermisoList().add(rolRecursopermiso);
                rolNew = em.merge(rolNew);
            }
            if (recursoOld != null && !recursoOld.equals(recursoNew)) {
                recursoOld.getRolRecursopermisoList().remove(rolRecursopermiso);
                recursoOld = em.merge(recursoOld);
            }
            if (recursoNew != null && !recursoNew.equals(recursoOld)) {
                recursoNew.getRolRecursopermisoList().add(rolRecursopermiso);
                recursoNew = em.merge(recursoNew);
            }
            if (permisoOld != null && !permisoOld.equals(permisoNew)) {
                permisoOld.getRolRecursopermisoList().remove(rolRecursopermiso);
                permisoOld = em.merge(permisoOld);
            }
            if (permisoNew != null && !permisoNew.equals(permisoOld)) {
                permisoNew.getRolRecursopermisoList().add(rolRecursopermiso);
                permisoNew = em.merge(permisoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RolRecursopermisoPK id = rolRecursopermiso.getRolRecursopermisoPK();
                if (findRolRecursopermiso(id) == null) {
                    throw new NonexistentEntityException("The rolRecursopermiso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RolRecursopermisoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolRecursopermiso rolRecursopermiso;
            try {
                rolRecursopermiso = em.getReference(RolRecursopermiso.class, id);
                rolRecursopermiso.getRolRecursopermisoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolRecursopermiso with id " + id + " no longer exists.", enfe);
            }
            Rol rol = rolRecursopermiso.getRol();
            if (rol != null) {
                rol.getRolRecursopermisoList().remove(rolRecursopermiso);
                rol = em.merge(rol);
            }
            Recurso recurso = rolRecursopermiso.getRecurso();
            if (recurso != null) {
                recurso.getRolRecursopermisoList().remove(rolRecursopermiso);
                recurso = em.merge(recurso);
            }
            Permiso permiso = rolRecursopermiso.getPermiso();
            if (permiso != null) {
                permiso.getRolRecursopermisoList().remove(rolRecursopermiso);
                permiso = em.merge(permiso);
            }
            em.remove(rolRecursopermiso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolRecursopermiso> findRolRecursopermisoEntities() {
        return findRolRecursopermisoEntities(true, -1, -1);
    }

    public List<RolRecursopermiso> findRolRecursopermisoEntities(int maxResults, int firstResult) {
        return findRolRecursopermisoEntities(false, maxResults, firstResult);
    }

    private List<RolRecursopermiso> findRolRecursopermisoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RolRecursopermiso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RolRecursopermiso findRolRecursopermiso(RolRecursopermisoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolRecursopermiso.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolRecursopermisoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RolRecursopermiso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
