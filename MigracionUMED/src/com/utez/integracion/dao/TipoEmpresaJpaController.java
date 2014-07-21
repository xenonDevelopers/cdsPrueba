/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ExperienciaLaboral;
import com.utez.integracion.entity.TipoEmpresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoEmpresaJpaController implements Serializable {

    public TipoEmpresaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEmpresa tipoEmpresa) {
        if (tipoEmpresa.getExperienciaLaboralList() == null) {
            tipoEmpresa.setExperienciaLaboralList(new ArrayList<ExperienciaLaboral>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExperienciaLaboral> attachedExperienciaLaboralList = new ArrayList<ExperienciaLaboral>();
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboralToAttach : tipoEmpresa.getExperienciaLaboralList()) {
                experienciaLaboralListExperienciaLaboralToAttach = em.getReference(experienciaLaboralListExperienciaLaboralToAttach.getClass(), experienciaLaboralListExperienciaLaboralToAttach.getIdExperiencialaboral());
                attachedExperienciaLaboralList.add(experienciaLaboralListExperienciaLaboralToAttach);
            }
            tipoEmpresa.setExperienciaLaboralList(attachedExperienciaLaboralList);
            em.persist(tipoEmpresa);
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboral : tipoEmpresa.getExperienciaLaboralList()) {
                TipoEmpresa oldIdTipoinstitucionOfExperienciaLaboralListExperienciaLaboral = experienciaLaboralListExperienciaLaboral.getIdTipoinstitucion();
                experienciaLaboralListExperienciaLaboral.setIdTipoinstitucion(tipoEmpresa);
                experienciaLaboralListExperienciaLaboral = em.merge(experienciaLaboralListExperienciaLaboral);
                if (oldIdTipoinstitucionOfExperienciaLaboralListExperienciaLaboral != null) {
                    oldIdTipoinstitucionOfExperienciaLaboralListExperienciaLaboral.getExperienciaLaboralList().remove(experienciaLaboralListExperienciaLaboral);
                    oldIdTipoinstitucionOfExperienciaLaboralListExperienciaLaboral = em.merge(oldIdTipoinstitucionOfExperienciaLaboralListExperienciaLaboral);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEmpresa tipoEmpresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEmpresa persistentTipoEmpresa = em.find(TipoEmpresa.class, tipoEmpresa.getIdTipoempresa());
            List<ExperienciaLaboral> experienciaLaboralListOld = persistentTipoEmpresa.getExperienciaLaboralList();
            List<ExperienciaLaboral> experienciaLaboralListNew = tipoEmpresa.getExperienciaLaboralList();
            List<ExperienciaLaboral> attachedExperienciaLaboralListNew = new ArrayList<ExperienciaLaboral>();
            for (ExperienciaLaboral experienciaLaboralListNewExperienciaLaboralToAttach : experienciaLaboralListNew) {
                experienciaLaboralListNewExperienciaLaboralToAttach = em.getReference(experienciaLaboralListNewExperienciaLaboralToAttach.getClass(), experienciaLaboralListNewExperienciaLaboralToAttach.getIdExperiencialaboral());
                attachedExperienciaLaboralListNew.add(experienciaLaboralListNewExperienciaLaboralToAttach);
            }
            experienciaLaboralListNew = attachedExperienciaLaboralListNew;
            tipoEmpresa.setExperienciaLaboralList(experienciaLaboralListNew);
            tipoEmpresa = em.merge(tipoEmpresa);
            for (ExperienciaLaboral experienciaLaboralListOldExperienciaLaboral : experienciaLaboralListOld) {
                if (!experienciaLaboralListNew.contains(experienciaLaboralListOldExperienciaLaboral)) {
                    experienciaLaboralListOldExperienciaLaboral.setIdTipoinstitucion(null);
                    experienciaLaboralListOldExperienciaLaboral = em.merge(experienciaLaboralListOldExperienciaLaboral);
                }
            }
            for (ExperienciaLaboral experienciaLaboralListNewExperienciaLaboral : experienciaLaboralListNew) {
                if (!experienciaLaboralListOld.contains(experienciaLaboralListNewExperienciaLaboral)) {
                    TipoEmpresa oldIdTipoinstitucionOfExperienciaLaboralListNewExperienciaLaboral = experienciaLaboralListNewExperienciaLaboral.getIdTipoinstitucion();
                    experienciaLaboralListNewExperienciaLaboral.setIdTipoinstitucion(tipoEmpresa);
                    experienciaLaboralListNewExperienciaLaboral = em.merge(experienciaLaboralListNewExperienciaLaboral);
                    if (oldIdTipoinstitucionOfExperienciaLaboralListNewExperienciaLaboral != null && !oldIdTipoinstitucionOfExperienciaLaboralListNewExperienciaLaboral.equals(tipoEmpresa)) {
                        oldIdTipoinstitucionOfExperienciaLaboralListNewExperienciaLaboral.getExperienciaLaboralList().remove(experienciaLaboralListNewExperienciaLaboral);
                        oldIdTipoinstitucionOfExperienciaLaboralListNewExperienciaLaboral = em.merge(oldIdTipoinstitucionOfExperienciaLaboralListNewExperienciaLaboral);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoEmpresa.getIdTipoempresa();
                if (findTipoEmpresa(id) == null) {
                    throw new NonexistentEntityException("The tipoEmpresa with id " + id + " no longer exists.");
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
            TipoEmpresa tipoEmpresa;
            try {
                tipoEmpresa = em.getReference(TipoEmpresa.class, id);
                tipoEmpresa.getIdTipoempresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEmpresa with id " + id + " no longer exists.", enfe);
            }
            List<ExperienciaLaboral> experienciaLaboralList = tipoEmpresa.getExperienciaLaboralList();
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboral : experienciaLaboralList) {
                experienciaLaboralListExperienciaLaboral.setIdTipoinstitucion(null);
                experienciaLaboralListExperienciaLaboral = em.merge(experienciaLaboralListExperienciaLaboral);
            }
            em.remove(tipoEmpresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEmpresa> findTipoEmpresaEntities() {
        return findTipoEmpresaEntities(true, -1, -1);
    }

    public List<TipoEmpresa> findTipoEmpresaEntities(int maxResults, int firstResult) {
        return findTipoEmpresaEntities(false, maxResults, firstResult);
    }

    private List<TipoEmpresa> findTipoEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoEmpresa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoEmpresa findTipoEmpresa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoEmpresa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
