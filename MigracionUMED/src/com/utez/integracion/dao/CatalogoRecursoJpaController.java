/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.CatalogoRecurso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Recurso;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CatalogoRecursoJpaController implements Serializable {

    public CatalogoRecursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CatalogoRecurso catalogoRecurso) {
        if (catalogoRecurso.getRecursoList() == null) {
            catalogoRecurso.setRecursoList(new ArrayList<Recurso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Recurso> attachedRecursoList = new ArrayList<Recurso>();
            for (Recurso recursoListRecursoToAttach : catalogoRecurso.getRecursoList()) {
                recursoListRecursoToAttach = em.getReference(recursoListRecursoToAttach.getClass(), recursoListRecursoToAttach.getIdRecurso());
                attachedRecursoList.add(recursoListRecursoToAttach);
            }
            catalogoRecurso.setRecursoList(attachedRecursoList);
            em.persist(catalogoRecurso);
            for (Recurso recursoListRecurso : catalogoRecurso.getRecursoList()) {
                CatalogoRecurso oldIdCatalogoOfRecursoListRecurso = recursoListRecurso.getIdCatalogo();
                recursoListRecurso.setIdCatalogo(catalogoRecurso);
                recursoListRecurso = em.merge(recursoListRecurso);
                if (oldIdCatalogoOfRecursoListRecurso != null) {
                    oldIdCatalogoOfRecursoListRecurso.getRecursoList().remove(recursoListRecurso);
                    oldIdCatalogoOfRecursoListRecurso = em.merge(oldIdCatalogoOfRecursoListRecurso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CatalogoRecurso catalogoRecurso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoRecurso persistentCatalogoRecurso = em.find(CatalogoRecurso.class, catalogoRecurso.getIdCatalogo());
            List<Recurso> recursoListOld = persistentCatalogoRecurso.getRecursoList();
            List<Recurso> recursoListNew = catalogoRecurso.getRecursoList();
            List<Recurso> attachedRecursoListNew = new ArrayList<Recurso>();
            for (Recurso recursoListNewRecursoToAttach : recursoListNew) {
                recursoListNewRecursoToAttach = em.getReference(recursoListNewRecursoToAttach.getClass(), recursoListNewRecursoToAttach.getIdRecurso());
                attachedRecursoListNew.add(recursoListNewRecursoToAttach);
            }
            recursoListNew = attachedRecursoListNew;
            catalogoRecurso.setRecursoList(recursoListNew);
            catalogoRecurso = em.merge(catalogoRecurso);
            for (Recurso recursoListOldRecurso : recursoListOld) {
                if (!recursoListNew.contains(recursoListOldRecurso)) {
                    recursoListOldRecurso.setIdCatalogo(null);
                    recursoListOldRecurso = em.merge(recursoListOldRecurso);
                }
            }
            for (Recurso recursoListNewRecurso : recursoListNew) {
                if (!recursoListOld.contains(recursoListNewRecurso)) {
                    CatalogoRecurso oldIdCatalogoOfRecursoListNewRecurso = recursoListNewRecurso.getIdCatalogo();
                    recursoListNewRecurso.setIdCatalogo(catalogoRecurso);
                    recursoListNewRecurso = em.merge(recursoListNewRecurso);
                    if (oldIdCatalogoOfRecursoListNewRecurso != null && !oldIdCatalogoOfRecursoListNewRecurso.equals(catalogoRecurso)) {
                        oldIdCatalogoOfRecursoListNewRecurso.getRecursoList().remove(recursoListNewRecurso);
                        oldIdCatalogoOfRecursoListNewRecurso = em.merge(oldIdCatalogoOfRecursoListNewRecurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catalogoRecurso.getIdCatalogo();
                if (findCatalogoRecurso(id) == null) {
                    throw new NonexistentEntityException("The catalogoRecurso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoRecurso catalogoRecurso;
            try {
                catalogoRecurso = em.getReference(CatalogoRecurso.class, id);
                catalogoRecurso.getIdCatalogo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catalogoRecurso with id " + id + " no longer exists.", enfe);
            }
            List<Recurso> recursoList = catalogoRecurso.getRecursoList();
            for (Recurso recursoListRecurso : recursoList) {
                recursoListRecurso.setIdCatalogo(null);
                recursoListRecurso = em.merge(recursoListRecurso);
            }
            em.remove(catalogoRecurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatalogoRecurso> findCatalogoRecursoEntities() {
        return findCatalogoRecursoEntities(true, -1, -1);
    }

    public List<CatalogoRecurso> findCatalogoRecursoEntities(int maxResults, int firstResult) {
        return findCatalogoRecursoEntities(false, maxResults, firstResult);
    }

    private List<CatalogoRecurso> findCatalogoRecursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CatalogoRecurso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CatalogoRecurso findCatalogoRecurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatalogoRecurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatalogoRecursoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CatalogoRecurso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
