/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Area;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AreaCargo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AreaJpaController implements Serializable {

    public AreaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Area area) {
        if (area.getAreaCargoList() == null) {
            area.setAreaCargoList(new ArrayList<AreaCargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AreaCargo> attachedAreaCargoList = new ArrayList<AreaCargo>();
            for (AreaCargo areaCargoListAreaCargoToAttach : area.getAreaCargoList()) {
                areaCargoListAreaCargoToAttach = em.getReference(areaCargoListAreaCargoToAttach.getClass(), areaCargoListAreaCargoToAttach.getIdCargoarea());
                attachedAreaCargoList.add(areaCargoListAreaCargoToAttach);
            }
            area.setAreaCargoList(attachedAreaCargoList);
            em.persist(area);
            for (AreaCargo areaCargoListAreaCargo : area.getAreaCargoList()) {
                Area oldIdAreaOfAreaCargoListAreaCargo = areaCargoListAreaCargo.getIdArea();
                areaCargoListAreaCargo.setIdArea(area);
                areaCargoListAreaCargo = em.merge(areaCargoListAreaCargo);
                if (oldIdAreaOfAreaCargoListAreaCargo != null) {
                    oldIdAreaOfAreaCargoListAreaCargo.getAreaCargoList().remove(areaCargoListAreaCargo);
                    oldIdAreaOfAreaCargoListAreaCargo = em.merge(oldIdAreaOfAreaCargoListAreaCargo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Area area) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area persistentArea = em.find(Area.class, area.getIdArea());
            List<AreaCargo> areaCargoListOld = persistentArea.getAreaCargoList();
            List<AreaCargo> areaCargoListNew = area.getAreaCargoList();
            List<AreaCargo> attachedAreaCargoListNew = new ArrayList<AreaCargo>();
            for (AreaCargo areaCargoListNewAreaCargoToAttach : areaCargoListNew) {
                areaCargoListNewAreaCargoToAttach = em.getReference(areaCargoListNewAreaCargoToAttach.getClass(), areaCargoListNewAreaCargoToAttach.getIdCargoarea());
                attachedAreaCargoListNew.add(areaCargoListNewAreaCargoToAttach);
            }
            areaCargoListNew = attachedAreaCargoListNew;
            area.setAreaCargoList(areaCargoListNew);
            area = em.merge(area);
            for (AreaCargo areaCargoListOldAreaCargo : areaCargoListOld) {
                if (!areaCargoListNew.contains(areaCargoListOldAreaCargo)) {
                    areaCargoListOldAreaCargo.setIdArea(null);
                    areaCargoListOldAreaCargo = em.merge(areaCargoListOldAreaCargo);
                }
            }
            for (AreaCargo areaCargoListNewAreaCargo : areaCargoListNew) {
                if (!areaCargoListOld.contains(areaCargoListNewAreaCargo)) {
                    Area oldIdAreaOfAreaCargoListNewAreaCargo = areaCargoListNewAreaCargo.getIdArea();
                    areaCargoListNewAreaCargo.setIdArea(area);
                    areaCargoListNewAreaCargo = em.merge(areaCargoListNewAreaCargo);
                    if (oldIdAreaOfAreaCargoListNewAreaCargo != null && !oldIdAreaOfAreaCargoListNewAreaCargo.equals(area)) {
                        oldIdAreaOfAreaCargoListNewAreaCargo.getAreaCargoList().remove(areaCargoListNewAreaCargo);
                        oldIdAreaOfAreaCargoListNewAreaCargo = em.merge(oldIdAreaOfAreaCargoListNewAreaCargo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = area.getIdArea();
                if (findArea(id) == null) {
                    throw new NonexistentEntityException("The area with id " + id + " no longer exists.");
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
            Area area;
            try {
                area = em.getReference(Area.class, id);
                area.getIdArea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The area with id " + id + " no longer exists.", enfe);
            }
            List<AreaCargo> areaCargoList = area.getAreaCargoList();
            for (AreaCargo areaCargoListAreaCargo : areaCargoList) {
                areaCargoListAreaCargo.setIdArea(null);
                areaCargoListAreaCargo = em.merge(areaCargoListAreaCargo);
            }
            em.remove(area);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Area> findAreaEntities() {
        return findAreaEntities(true, -1, -1);
    }

    public List<Area> findAreaEntities(int maxResults, int firstResult) {
        return findAreaEntities(false, maxResults, firstResult);
    }

    private List<Area> findAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Area as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Area findArea(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Area.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Area as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
