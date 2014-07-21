/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.TipoVacacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Vacacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoVacacionJpaController implements Serializable {

    public TipoVacacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoVacacion tipoVacacion) {
        if (tipoVacacion.getVacacionList() == null) {
            tipoVacacion.setVacacionList(new ArrayList<Vacacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vacacion> attachedVacacionList = new ArrayList<Vacacion>();
            for (Vacacion vacacionListVacacionToAttach : tipoVacacion.getVacacionList()) {
                vacacionListVacacionToAttach = em.getReference(vacacionListVacacionToAttach.getClass(), vacacionListVacacionToAttach.getIdVacacion());
                attachedVacacionList.add(vacacionListVacacionToAttach);
            }
            tipoVacacion.setVacacionList(attachedVacacionList);
            em.persist(tipoVacacion);
            for (Vacacion vacacionListVacacion : tipoVacacion.getVacacionList()) {
                TipoVacacion oldIdTipovacacionOfVacacionListVacacion = vacacionListVacacion.getIdTipovacacion();
                vacacionListVacacion.setIdTipovacacion(tipoVacacion);
                vacacionListVacacion = em.merge(vacacionListVacacion);
                if (oldIdTipovacacionOfVacacionListVacacion != null) {
                    oldIdTipovacacionOfVacacionListVacacion.getVacacionList().remove(vacacionListVacacion);
                    oldIdTipovacacionOfVacacionListVacacion = em.merge(oldIdTipovacacionOfVacacionListVacacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoVacacion tipoVacacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoVacacion persistentTipoVacacion = em.find(TipoVacacion.class, tipoVacacion.getIdTipovacacion());
            List<Vacacion> vacacionListOld = persistentTipoVacacion.getVacacionList();
            List<Vacacion> vacacionListNew = tipoVacacion.getVacacionList();
            List<Vacacion> attachedVacacionListNew = new ArrayList<Vacacion>();
            for (Vacacion vacacionListNewVacacionToAttach : vacacionListNew) {
                vacacionListNewVacacionToAttach = em.getReference(vacacionListNewVacacionToAttach.getClass(), vacacionListNewVacacionToAttach.getIdVacacion());
                attachedVacacionListNew.add(vacacionListNewVacacionToAttach);
            }
            vacacionListNew = attachedVacacionListNew;
            tipoVacacion.setVacacionList(vacacionListNew);
            tipoVacacion = em.merge(tipoVacacion);
            for (Vacacion vacacionListOldVacacion : vacacionListOld) {
                if (!vacacionListNew.contains(vacacionListOldVacacion)) {
                    vacacionListOldVacacion.setIdTipovacacion(null);
                    vacacionListOldVacacion = em.merge(vacacionListOldVacacion);
                }
            }
            for (Vacacion vacacionListNewVacacion : vacacionListNew) {
                if (!vacacionListOld.contains(vacacionListNewVacacion)) {
                    TipoVacacion oldIdTipovacacionOfVacacionListNewVacacion = vacacionListNewVacacion.getIdTipovacacion();
                    vacacionListNewVacacion.setIdTipovacacion(tipoVacacion);
                    vacacionListNewVacacion = em.merge(vacacionListNewVacacion);
                    if (oldIdTipovacacionOfVacacionListNewVacacion != null && !oldIdTipovacacionOfVacacionListNewVacacion.equals(tipoVacacion)) {
                        oldIdTipovacacionOfVacacionListNewVacacion.getVacacionList().remove(vacacionListNewVacacion);
                        oldIdTipovacacionOfVacacionListNewVacacion = em.merge(oldIdTipovacacionOfVacacionListNewVacacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoVacacion.getIdTipovacacion();
                if (findTipoVacacion(id) == null) {
                    throw new NonexistentEntityException("The tipoVacacion with id " + id + " no longer exists.");
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
            TipoVacacion tipoVacacion;
            try {
                tipoVacacion = em.getReference(TipoVacacion.class, id);
                tipoVacacion.getIdTipovacacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoVacacion with id " + id + " no longer exists.", enfe);
            }
            List<Vacacion> vacacionList = tipoVacacion.getVacacionList();
            for (Vacacion vacacionListVacacion : vacacionList) {
                vacacionListVacacion.setIdTipovacacion(null);
                vacacionListVacacion = em.merge(vacacionListVacacion);
            }
            em.remove(tipoVacacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoVacacion> findTipoVacacionEntities() {
        return findTipoVacacionEntities(true, -1, -1);
    }

    public List<TipoVacacion> findTipoVacacionEntities(int maxResults, int firstResult) {
        return findTipoVacacionEntities(false, maxResults, firstResult);
    }

    private List<TipoVacacion> findTipoVacacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoVacacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoVacacion findTipoVacacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoVacacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoVacacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoVacacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
