/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.CatalogoRecurso;
import com.utez.integracion.entity.Recurso;
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
public class RecursoJpaController implements Serializable {

    public RecursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recurso recurso) {
        if (recurso.getRolRecursopermisoList() == null) {
            recurso.setRolRecursopermisoList(new ArrayList<RolRecursopermiso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoRecurso idCatalogo = recurso.getIdCatalogo();
            if (idCatalogo != null) {
                idCatalogo = em.getReference(idCatalogo.getClass(), idCatalogo.getIdCatalogo());
                recurso.setIdCatalogo(idCatalogo);
            }
            List<RolRecursopermiso> attachedRolRecursopermisoList = new ArrayList<RolRecursopermiso>();
            for (RolRecursopermiso rolRecursopermisoListRolRecursopermisoToAttach : recurso.getRolRecursopermisoList()) {
                rolRecursopermisoListRolRecursopermisoToAttach = em.getReference(rolRecursopermisoListRolRecursopermisoToAttach.getClass(), rolRecursopermisoListRolRecursopermisoToAttach.getRolRecursopermisoPK());
                attachedRolRecursopermisoList.add(rolRecursopermisoListRolRecursopermisoToAttach);
            }
            recurso.setRolRecursopermisoList(attachedRolRecursopermisoList);
            em.persist(recurso);
            if (idCatalogo != null) {
                idCatalogo.getRecursoList().add(recurso);
                idCatalogo = em.merge(idCatalogo);
            }
            for (RolRecursopermiso rolRecursopermisoListRolRecursopermiso : recurso.getRolRecursopermisoList()) {
                Recurso oldRecursoOfRolRecursopermisoListRolRecursopermiso = rolRecursopermisoListRolRecursopermiso.getRecurso();
                rolRecursopermisoListRolRecursopermiso.setRecurso(recurso);
                rolRecursopermisoListRolRecursopermiso = em.merge(rolRecursopermisoListRolRecursopermiso);
                if (oldRecursoOfRolRecursopermisoListRolRecursopermiso != null) {
                    oldRecursoOfRolRecursopermisoListRolRecursopermiso.getRolRecursopermisoList().remove(rolRecursopermisoListRolRecursopermiso);
                    oldRecursoOfRolRecursopermisoListRolRecursopermiso = em.merge(oldRecursoOfRolRecursopermisoListRolRecursopermiso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recurso recurso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recurso persistentRecurso = em.find(Recurso.class, recurso.getIdRecurso());
            CatalogoRecurso idCatalogoOld = persistentRecurso.getIdCatalogo();
            CatalogoRecurso idCatalogoNew = recurso.getIdCatalogo();
            List<RolRecursopermiso> rolRecursopermisoListOld = persistentRecurso.getRolRecursopermisoList();
            List<RolRecursopermiso> rolRecursopermisoListNew = recurso.getRolRecursopermisoList();
            List<String> illegalOrphanMessages = null;
            for (RolRecursopermiso rolRecursopermisoListOldRolRecursopermiso : rolRecursopermisoListOld) {
                if (!rolRecursopermisoListNew.contains(rolRecursopermisoListOldRolRecursopermiso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RolRecursopermiso " + rolRecursopermisoListOldRolRecursopermiso + " since its recurso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCatalogoNew != null) {
                idCatalogoNew = em.getReference(idCatalogoNew.getClass(), idCatalogoNew.getIdCatalogo());
                recurso.setIdCatalogo(idCatalogoNew);
            }
            List<RolRecursopermiso> attachedRolRecursopermisoListNew = new ArrayList<RolRecursopermiso>();
            for (RolRecursopermiso rolRecursopermisoListNewRolRecursopermisoToAttach : rolRecursopermisoListNew) {
                rolRecursopermisoListNewRolRecursopermisoToAttach = em.getReference(rolRecursopermisoListNewRolRecursopermisoToAttach.getClass(), rolRecursopermisoListNewRolRecursopermisoToAttach.getRolRecursopermisoPK());
                attachedRolRecursopermisoListNew.add(rolRecursopermisoListNewRolRecursopermisoToAttach);
            }
            rolRecursopermisoListNew = attachedRolRecursopermisoListNew;
            recurso.setRolRecursopermisoList(rolRecursopermisoListNew);
            recurso = em.merge(recurso);
            if (idCatalogoOld != null && !idCatalogoOld.equals(idCatalogoNew)) {
                idCatalogoOld.getRecursoList().remove(recurso);
                idCatalogoOld = em.merge(idCatalogoOld);
            }
            if (idCatalogoNew != null && !idCatalogoNew.equals(idCatalogoOld)) {
                idCatalogoNew.getRecursoList().add(recurso);
                idCatalogoNew = em.merge(idCatalogoNew);
            }
            for (RolRecursopermiso rolRecursopermisoListNewRolRecursopermiso : rolRecursopermisoListNew) {
                if (!rolRecursopermisoListOld.contains(rolRecursopermisoListNewRolRecursopermiso)) {
                    Recurso oldRecursoOfRolRecursopermisoListNewRolRecursopermiso = rolRecursopermisoListNewRolRecursopermiso.getRecurso();
                    rolRecursopermisoListNewRolRecursopermiso.setRecurso(recurso);
                    rolRecursopermisoListNewRolRecursopermiso = em.merge(rolRecursopermisoListNewRolRecursopermiso);
                    if (oldRecursoOfRolRecursopermisoListNewRolRecursopermiso != null && !oldRecursoOfRolRecursopermisoListNewRolRecursopermiso.equals(recurso)) {
                        oldRecursoOfRolRecursopermisoListNewRolRecursopermiso.getRolRecursopermisoList().remove(rolRecursopermisoListNewRolRecursopermiso);
                        oldRecursoOfRolRecursopermisoListNewRolRecursopermiso = em.merge(oldRecursoOfRolRecursopermisoListNewRolRecursopermiso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = recurso.getIdRecurso();
                if (findRecurso(id) == null) {
                    throw new NonexistentEntityException("The recurso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recurso recurso;
            try {
                recurso = em.getReference(Recurso.class, id);
                recurso.getIdRecurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recurso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RolRecursopermiso> rolRecursopermisoListOrphanCheck = recurso.getRolRecursopermisoList();
            for (RolRecursopermiso rolRecursopermisoListOrphanCheckRolRecursopermiso : rolRecursopermisoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recurso (" + recurso + ") cannot be destroyed since the RolRecursopermiso " + rolRecursopermisoListOrphanCheckRolRecursopermiso + " in its rolRecursopermisoList field has a non-nullable recurso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CatalogoRecurso idCatalogo = recurso.getIdCatalogo();
            if (idCatalogo != null) {
                idCatalogo.getRecursoList().remove(recurso);
                idCatalogo = em.merge(idCatalogo);
            }
            em.remove(recurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recurso> findRecursoEntities() {
        return findRecursoEntities(true, -1, -1);
    }

    public List<Recurso> findRecursoEntities(int maxResults, int firstResult) {
        return findRecursoEntities(false, maxResults, firstResult);
    }

    private List<Recurso> findRecursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Recurso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Recurso findRecurso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecursoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Recurso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
