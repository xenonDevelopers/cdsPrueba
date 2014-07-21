/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.CalendarioRectoria;
import com.utez.integracion.entity.SuspensionLabores;
import com.utez.secretariaAcademica.entity.Plantel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SuspensionLaboresJpaController implements Serializable {

    public SuspensionLaboresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SuspensionLabores suspensionLabores) {
        if (suspensionLabores.getPlantelList() == null) {
            suspensionLabores.setPlantelList(new ArrayList<Plantel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioRectoria idCalendariorectoria = suspensionLabores.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria = em.getReference(idCalendariorectoria.getClass(), idCalendariorectoria.getIdCalendariorectoria());
                suspensionLabores.setIdCalendariorectoria(idCalendariorectoria);
            }
            List<Plantel> attachedPlantelList = new ArrayList<Plantel>();
            for (Plantel plantelListPlantelToAttach : suspensionLabores.getPlantelList()) {
                plantelListPlantelToAttach = em.getReference(plantelListPlantelToAttach.getClass(), plantelListPlantelToAttach.getIdplantel());
                attachedPlantelList.add(plantelListPlantelToAttach);
            }
            suspensionLabores.setPlantelList(attachedPlantelList);
            em.persist(suspensionLabores);
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getSuspensionLaboresList().add(suspensionLabores);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            for (Plantel plantelListPlantel : suspensionLabores.getPlantelList()) {
                plantelListPlantel.getSuspensionLaboresList().add(suspensionLabores);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SuspensionLabores suspensionLabores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuspensionLabores persistentSuspensionLabores = em.find(SuspensionLabores.class, suspensionLabores.getIdSuspensionlabores());
            CalendarioRectoria idCalendariorectoriaOld = persistentSuspensionLabores.getIdCalendariorectoria();
            CalendarioRectoria idCalendariorectoriaNew = suspensionLabores.getIdCalendariorectoria();
            List<Plantel> plantelListOld = persistentSuspensionLabores.getPlantelList();
            List<Plantel> plantelListNew = suspensionLabores.getPlantelList();
            if (idCalendariorectoriaNew != null) {
                idCalendariorectoriaNew = em.getReference(idCalendariorectoriaNew.getClass(), idCalendariorectoriaNew.getIdCalendariorectoria());
                suspensionLabores.setIdCalendariorectoria(idCalendariorectoriaNew);
            }
            List<Plantel> attachedPlantelListNew = new ArrayList<Plantel>();
            for (Plantel plantelListNewPlantelToAttach : plantelListNew) {
                plantelListNewPlantelToAttach = em.getReference(plantelListNewPlantelToAttach.getClass(), plantelListNewPlantelToAttach.getIdplantel());
                attachedPlantelListNew.add(plantelListNewPlantelToAttach);
            }
            plantelListNew = attachedPlantelListNew;
            suspensionLabores.setPlantelList(plantelListNew);
            suspensionLabores = em.merge(suspensionLabores);
            if (idCalendariorectoriaOld != null && !idCalendariorectoriaOld.equals(idCalendariorectoriaNew)) {
                idCalendariorectoriaOld.getSuspensionLaboresList().remove(suspensionLabores);
                idCalendariorectoriaOld = em.merge(idCalendariorectoriaOld);
            }
            if (idCalendariorectoriaNew != null && !idCalendariorectoriaNew.equals(idCalendariorectoriaOld)) {
                idCalendariorectoriaNew.getSuspensionLaboresList().add(suspensionLabores);
                idCalendariorectoriaNew = em.merge(idCalendariorectoriaNew);
            }
            for (Plantel plantelListOldPlantel : plantelListOld) {
                if (!plantelListNew.contains(plantelListOldPlantel)) {
                    plantelListOldPlantel.getSuspensionLaboresList().remove(suspensionLabores);
                    plantelListOldPlantel = em.merge(plantelListOldPlantel);
                }
            }
            for (Plantel plantelListNewPlantel : plantelListNew) {
                if (!plantelListOld.contains(plantelListNewPlantel)) {
                    plantelListNewPlantel.getSuspensionLaboresList().add(suspensionLabores);
                    plantelListNewPlantel = em.merge(plantelListNewPlantel);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = suspensionLabores.getIdSuspensionlabores();
                if (findSuspensionLabores(id) == null) {
                    throw new NonexistentEntityException("The suspensionLabores with id " + id + " no longer exists.");
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
            SuspensionLabores suspensionLabores;
            try {
                suspensionLabores = em.getReference(SuspensionLabores.class, id);
                suspensionLabores.getIdSuspensionlabores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suspensionLabores with id " + id + " no longer exists.", enfe);
            }
            CalendarioRectoria idCalendariorectoria = suspensionLabores.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getSuspensionLaboresList().remove(suspensionLabores);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            List<Plantel> plantelList = suspensionLabores.getPlantelList();
            for (Plantel plantelListPlantel : plantelList) {
                plantelListPlantel.getSuspensionLaboresList().remove(suspensionLabores);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            em.remove(suspensionLabores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SuspensionLabores> findSuspensionLaboresEntities() {
        return findSuspensionLaboresEntities(true, -1, -1);
    }

    public List<SuspensionLabores> findSuspensionLaboresEntities(int maxResults, int firstResult) {
        return findSuspensionLaboresEntities(false, maxResults, firstResult);
    }

    private List<SuspensionLabores> findSuspensionLaboresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SuspensionLabores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SuspensionLabores findSuspensionLabores(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SuspensionLabores.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuspensionLaboresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SuspensionLabores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
