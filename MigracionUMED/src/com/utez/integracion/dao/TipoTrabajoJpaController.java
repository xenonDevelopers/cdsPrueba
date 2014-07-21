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
import com.utez.integracion.entity.TipoTrabajo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoTrabajoJpaController implements Serializable {

    public TipoTrabajoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoTrabajo tipoTrabajo) {
        if (tipoTrabajo.getExperienciaLaboralList() == null) {
            tipoTrabajo.setExperienciaLaboralList(new ArrayList<ExperienciaLaboral>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExperienciaLaboral> attachedExperienciaLaboralList = new ArrayList<ExperienciaLaboral>();
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboralToAttach : tipoTrabajo.getExperienciaLaboralList()) {
                experienciaLaboralListExperienciaLaboralToAttach = em.getReference(experienciaLaboralListExperienciaLaboralToAttach.getClass(), experienciaLaboralListExperienciaLaboralToAttach.getIdExperiencialaboral());
                attachedExperienciaLaboralList.add(experienciaLaboralListExperienciaLaboralToAttach);
            }
            tipoTrabajo.setExperienciaLaboralList(attachedExperienciaLaboralList);
            em.persist(tipoTrabajo);
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboral : tipoTrabajo.getExperienciaLaboralList()) {
                TipoTrabajo oldIdTipotrabajoOfExperienciaLaboralListExperienciaLaboral = experienciaLaboralListExperienciaLaboral.getIdTipotrabajo();
                experienciaLaboralListExperienciaLaboral.setIdTipotrabajo(tipoTrabajo);
                experienciaLaboralListExperienciaLaboral = em.merge(experienciaLaboralListExperienciaLaboral);
                if (oldIdTipotrabajoOfExperienciaLaboralListExperienciaLaboral != null) {
                    oldIdTipotrabajoOfExperienciaLaboralListExperienciaLaboral.getExperienciaLaboralList().remove(experienciaLaboralListExperienciaLaboral);
                    oldIdTipotrabajoOfExperienciaLaboralListExperienciaLaboral = em.merge(oldIdTipotrabajoOfExperienciaLaboralListExperienciaLaboral);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTrabajo tipoTrabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTrabajo persistentTipoTrabajo = em.find(TipoTrabajo.class, tipoTrabajo.getIdTipotrabajo());
            List<ExperienciaLaboral> experienciaLaboralListOld = persistentTipoTrabajo.getExperienciaLaboralList();
            List<ExperienciaLaboral> experienciaLaboralListNew = tipoTrabajo.getExperienciaLaboralList();
            List<ExperienciaLaboral> attachedExperienciaLaboralListNew = new ArrayList<ExperienciaLaboral>();
            for (ExperienciaLaboral experienciaLaboralListNewExperienciaLaboralToAttach : experienciaLaboralListNew) {
                experienciaLaboralListNewExperienciaLaboralToAttach = em.getReference(experienciaLaboralListNewExperienciaLaboralToAttach.getClass(), experienciaLaboralListNewExperienciaLaboralToAttach.getIdExperiencialaboral());
                attachedExperienciaLaboralListNew.add(experienciaLaboralListNewExperienciaLaboralToAttach);
            }
            experienciaLaboralListNew = attachedExperienciaLaboralListNew;
            tipoTrabajo.setExperienciaLaboralList(experienciaLaboralListNew);
            tipoTrabajo = em.merge(tipoTrabajo);
            for (ExperienciaLaboral experienciaLaboralListOldExperienciaLaboral : experienciaLaboralListOld) {
                if (!experienciaLaboralListNew.contains(experienciaLaboralListOldExperienciaLaboral)) {
                    experienciaLaboralListOldExperienciaLaboral.setIdTipotrabajo(null);
                    experienciaLaboralListOldExperienciaLaboral = em.merge(experienciaLaboralListOldExperienciaLaboral);
                }
            }
            for (ExperienciaLaboral experienciaLaboralListNewExperienciaLaboral : experienciaLaboralListNew) {
                if (!experienciaLaboralListOld.contains(experienciaLaboralListNewExperienciaLaboral)) {
                    TipoTrabajo oldIdTipotrabajoOfExperienciaLaboralListNewExperienciaLaboral = experienciaLaboralListNewExperienciaLaboral.getIdTipotrabajo();
                    experienciaLaboralListNewExperienciaLaboral.setIdTipotrabajo(tipoTrabajo);
                    experienciaLaboralListNewExperienciaLaboral = em.merge(experienciaLaboralListNewExperienciaLaboral);
                    if (oldIdTipotrabajoOfExperienciaLaboralListNewExperienciaLaboral != null && !oldIdTipotrabajoOfExperienciaLaboralListNewExperienciaLaboral.equals(tipoTrabajo)) {
                        oldIdTipotrabajoOfExperienciaLaboralListNewExperienciaLaboral.getExperienciaLaboralList().remove(experienciaLaboralListNewExperienciaLaboral);
                        oldIdTipotrabajoOfExperienciaLaboralListNewExperienciaLaboral = em.merge(oldIdTipotrabajoOfExperienciaLaboralListNewExperienciaLaboral);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoTrabajo.getIdTipotrabajo();
                if (findTipoTrabajo(id) == null) {
                    throw new NonexistentEntityException("The tipoTrabajo with id " + id + " no longer exists.");
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
            TipoTrabajo tipoTrabajo;
            try {
                tipoTrabajo = em.getReference(TipoTrabajo.class, id);
                tipoTrabajo.getIdTipotrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTrabajo with id " + id + " no longer exists.", enfe);
            }
            List<ExperienciaLaboral> experienciaLaboralList = tipoTrabajo.getExperienciaLaboralList();
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboral : experienciaLaboralList) {
                experienciaLaboralListExperienciaLaboral.setIdTipotrabajo(null);
                experienciaLaboralListExperienciaLaboral = em.merge(experienciaLaboralListExperienciaLaboral);
            }
            em.remove(tipoTrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTrabajo> findTipoTrabajoEntities() {
        return findTipoTrabajoEntities(true, -1, -1);
    }

    public List<TipoTrabajo> findTipoTrabajoEntities(int maxResults, int firstResult) {
        return findTipoTrabajoEntities(false, maxResults, firstResult);
    }

    private List<TipoTrabajo> findTipoTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoTrabajo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoTrabajo findTipoTrabajo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoTrabajo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
