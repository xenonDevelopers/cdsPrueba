/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.Permiso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RolRecursopermiso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PermisoJpaController implements Serializable {

    public PermisoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Permiso permiso) throws PreexistingEntityException, Exception {
        if (permiso.getRolRecursopermisoList() == null) {
            permiso.setRolRecursopermisoList(new ArrayList<RolRecursopermiso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RolRecursopermiso> attachedRolRecursopermisoList = new ArrayList<RolRecursopermiso>();
            for (RolRecursopermiso rolRecursopermisoListRolRecursopermisoToAttach : permiso.getRolRecursopermisoList()) {
                rolRecursopermisoListRolRecursopermisoToAttach = em.getReference(rolRecursopermisoListRolRecursopermisoToAttach.getClass(), rolRecursopermisoListRolRecursopermisoToAttach.getRolRecursopermisoPK());
                attachedRolRecursopermisoList.add(rolRecursopermisoListRolRecursopermisoToAttach);
            }
            permiso.setRolRecursopermisoList(attachedRolRecursopermisoList);
            em.persist(permiso);
            for (RolRecursopermiso rolRecursopermisoListRolRecursopermiso : permiso.getRolRecursopermisoList()) {
                Permiso oldPermisoOfRolRecursopermisoListRolRecursopermiso = rolRecursopermisoListRolRecursopermiso.getPermiso();
                rolRecursopermisoListRolRecursopermiso.setPermiso(permiso);
                rolRecursopermisoListRolRecursopermiso = em.merge(rolRecursopermisoListRolRecursopermiso);
                if (oldPermisoOfRolRecursopermisoListRolRecursopermiso != null) {
                    oldPermisoOfRolRecursopermisoListRolRecursopermiso.getRolRecursopermisoList().remove(rolRecursopermisoListRolRecursopermiso);
                    oldPermisoOfRolRecursopermisoListRolRecursopermiso = em.merge(oldPermisoOfRolRecursopermisoListRolRecursopermiso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPermiso(permiso.getIdPermiso()) != null) {
                throw new PreexistingEntityException("Permiso " + permiso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Permiso permiso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permiso persistentPermiso = em.find(Permiso.class, permiso.getIdPermiso());
            List<RolRecursopermiso> rolRecursopermisoListOld = persistentPermiso.getRolRecursopermisoList();
            List<RolRecursopermiso> rolRecursopermisoListNew = permiso.getRolRecursopermisoList();
            List<String> illegalOrphanMessages = null;
            for (RolRecursopermiso rolRecursopermisoListOldRolRecursopermiso : rolRecursopermisoListOld) {
                if (!rolRecursopermisoListNew.contains(rolRecursopermisoListOldRolRecursopermiso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RolRecursopermiso " + rolRecursopermisoListOldRolRecursopermiso + " since its permiso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<RolRecursopermiso> attachedRolRecursopermisoListNew = new ArrayList<RolRecursopermiso>();
            for (RolRecursopermiso rolRecursopermisoListNewRolRecursopermisoToAttach : rolRecursopermisoListNew) {
                rolRecursopermisoListNewRolRecursopermisoToAttach = em.getReference(rolRecursopermisoListNewRolRecursopermisoToAttach.getClass(), rolRecursopermisoListNewRolRecursopermisoToAttach.getRolRecursopermisoPK());
                attachedRolRecursopermisoListNew.add(rolRecursopermisoListNewRolRecursopermisoToAttach);
            }
            rolRecursopermisoListNew = attachedRolRecursopermisoListNew;
            permiso.setRolRecursopermisoList(rolRecursopermisoListNew);
            permiso = em.merge(permiso);
            for (RolRecursopermiso rolRecursopermisoListNewRolRecursopermiso : rolRecursopermisoListNew) {
                if (!rolRecursopermisoListOld.contains(rolRecursopermisoListNewRolRecursopermiso)) {
                    Permiso oldPermisoOfRolRecursopermisoListNewRolRecursopermiso = rolRecursopermisoListNewRolRecursopermiso.getPermiso();
                    rolRecursopermisoListNewRolRecursopermiso.setPermiso(permiso);
                    rolRecursopermisoListNewRolRecursopermiso = em.merge(rolRecursopermisoListNewRolRecursopermiso);
                    if (oldPermisoOfRolRecursopermisoListNewRolRecursopermiso != null && !oldPermisoOfRolRecursopermisoListNewRolRecursopermiso.equals(permiso)) {
                        oldPermisoOfRolRecursopermisoListNewRolRecursopermiso.getRolRecursopermisoList().remove(rolRecursopermisoListNewRolRecursopermiso);
                        oldPermisoOfRolRecursopermisoListNewRolRecursopermiso = em.merge(oldPermisoOfRolRecursopermisoListNewRolRecursopermiso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = permiso.getIdPermiso();
                if (findPermiso(id) == null) {
                    throw new NonexistentEntityException("The permiso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permiso permiso;
            try {
                permiso = em.getReference(Permiso.class, id);
                permiso.getIdPermiso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permiso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RolRecursopermiso> rolRecursopermisoListOrphanCheck = permiso.getRolRecursopermisoList();
            for (RolRecursopermiso rolRecursopermisoListOrphanCheckRolRecursopermiso : rolRecursopermisoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Permiso (" + permiso + ") cannot be destroyed since the RolRecursopermiso " + rolRecursopermisoListOrphanCheckRolRecursopermiso + " in its rolRecursopermisoList field has a non-nullable permiso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(permiso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Permiso> findPermisoEntities() {
        return findPermisoEntities(true, -1, -1);
    }

    public List<Permiso> findPermisoEntities(int maxResults, int firstResult) {
        return findPermisoEntities(false, maxResults, firstResult);
    }

    private List<Permiso> findPermisoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Permiso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Permiso findPermiso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Permiso.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermisoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Permiso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
