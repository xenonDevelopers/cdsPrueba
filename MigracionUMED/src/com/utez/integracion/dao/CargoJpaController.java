/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AreaCargo;
import com.utez.integracion.entity.Cargo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CargoJpaController implements Serializable {

    public CargoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cargo cargo) {
        if (cargo.getAreaCargoList() == null) {
            cargo.setAreaCargoList(new ArrayList<AreaCargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AreaCargo> attachedAreaCargoList = new ArrayList<AreaCargo>();
            for (AreaCargo areaCargoListAreaCargoToAttach : cargo.getAreaCargoList()) {
                areaCargoListAreaCargoToAttach = em.getReference(areaCargoListAreaCargoToAttach.getClass(), areaCargoListAreaCargoToAttach.getIdCargoarea());
                attachedAreaCargoList.add(areaCargoListAreaCargoToAttach);
            }
            cargo.setAreaCargoList(attachedAreaCargoList);
            em.persist(cargo);
            for (AreaCargo areaCargoListAreaCargo : cargo.getAreaCargoList()) {
                Cargo oldIdCargoOfAreaCargoListAreaCargo = areaCargoListAreaCargo.getIdCargo();
                areaCargoListAreaCargo.setIdCargo(cargo);
                areaCargoListAreaCargo = em.merge(areaCargoListAreaCargo);
                if (oldIdCargoOfAreaCargoListAreaCargo != null) {
                    oldIdCargoOfAreaCargoListAreaCargo.getAreaCargoList().remove(areaCargoListAreaCargo);
                    oldIdCargoOfAreaCargoListAreaCargo = em.merge(oldIdCargoOfAreaCargoListAreaCargo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getIdCargo());
            List<AreaCargo> areaCargoListOld = persistentCargo.getAreaCargoList();
            List<AreaCargo> areaCargoListNew = cargo.getAreaCargoList();
            List<AreaCargo> attachedAreaCargoListNew = new ArrayList<AreaCargo>();
            for (AreaCargo areaCargoListNewAreaCargoToAttach : areaCargoListNew) {
                areaCargoListNewAreaCargoToAttach = em.getReference(areaCargoListNewAreaCargoToAttach.getClass(), areaCargoListNewAreaCargoToAttach.getIdCargoarea());
                attachedAreaCargoListNew.add(areaCargoListNewAreaCargoToAttach);
            }
            areaCargoListNew = attachedAreaCargoListNew;
            cargo.setAreaCargoList(areaCargoListNew);
            cargo = em.merge(cargo);
            for (AreaCargo areaCargoListOldAreaCargo : areaCargoListOld) {
                if (!areaCargoListNew.contains(areaCargoListOldAreaCargo)) {
                    areaCargoListOldAreaCargo.setIdCargo(null);
                    areaCargoListOldAreaCargo = em.merge(areaCargoListOldAreaCargo);
                }
            }
            for (AreaCargo areaCargoListNewAreaCargo : areaCargoListNew) {
                if (!areaCargoListOld.contains(areaCargoListNewAreaCargo)) {
                    Cargo oldIdCargoOfAreaCargoListNewAreaCargo = areaCargoListNewAreaCargo.getIdCargo();
                    areaCargoListNewAreaCargo.setIdCargo(cargo);
                    areaCargoListNewAreaCargo = em.merge(areaCargoListNewAreaCargo);
                    if (oldIdCargoOfAreaCargoListNewAreaCargo != null && !oldIdCargoOfAreaCargoListNewAreaCargo.equals(cargo)) {
                        oldIdCargoOfAreaCargoListNewAreaCargo.getAreaCargoList().remove(areaCargoListNewAreaCargo);
                        oldIdCargoOfAreaCargoListNewAreaCargo = em.merge(oldIdCargoOfAreaCargoListNewAreaCargo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cargo.getIdCargo();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
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
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getIdCargo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            List<AreaCargo> areaCargoList = cargo.getAreaCargoList();
            for (AreaCargo areaCargoListAreaCargo : areaCargoList) {
                areaCargoListAreaCargo.setIdCargo(null);
                areaCargoListAreaCargo = em.merge(areaCargoListAreaCargo);
            }
            em.remove(cargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cargo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cargo findCargo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cargo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
