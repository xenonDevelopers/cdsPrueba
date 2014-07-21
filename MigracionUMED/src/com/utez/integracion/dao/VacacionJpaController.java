/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoVacacion;
import com.utez.integracion.entity.CalendarioRectoria;
import com.utez.integracion.entity.Vacacion;
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
public class VacacionJpaController implements Serializable {

    public VacacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacacion vacacion) {
        if (vacacion.getPlantelList() == null) {
            vacacion.setPlantelList(new ArrayList<Plantel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoVacacion idTipovacacion = vacacion.getIdTipovacacion();
            if (idTipovacacion != null) {
                idTipovacacion = em.getReference(idTipovacacion.getClass(), idTipovacacion.getIdTipovacacion());
                vacacion.setIdTipovacacion(idTipovacacion);
            }
            CalendarioRectoria idCalendariorectoria = vacacion.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria = em.getReference(idCalendariorectoria.getClass(), idCalendariorectoria.getIdCalendariorectoria());
                vacacion.setIdCalendariorectoria(idCalendariorectoria);
            }
            List<Plantel> attachedPlantelList = new ArrayList<Plantel>();
            for (Plantel plantelListPlantelToAttach : vacacion.getPlantelList()) {
                plantelListPlantelToAttach = em.getReference(plantelListPlantelToAttach.getClass(), plantelListPlantelToAttach.getIdplantel());
                attachedPlantelList.add(plantelListPlantelToAttach);
            }
            vacacion.setPlantelList(attachedPlantelList);
            em.persist(vacacion);
            if (idTipovacacion != null) {
                idTipovacacion.getVacacionList().add(vacacion);
                idTipovacacion = em.merge(idTipovacacion);
            }
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getVacacionList().add(vacacion);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            for (Plantel plantelListPlantel : vacacion.getPlantelList()) {
                plantelListPlantel.getVacacionList().add(vacacion);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vacacion vacacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacacion persistentVacacion = em.find(Vacacion.class, vacacion.getIdVacacion());
            TipoVacacion idTipovacacionOld = persistentVacacion.getIdTipovacacion();
            TipoVacacion idTipovacacionNew = vacacion.getIdTipovacacion();
            CalendarioRectoria idCalendariorectoriaOld = persistentVacacion.getIdCalendariorectoria();
            CalendarioRectoria idCalendariorectoriaNew = vacacion.getIdCalendariorectoria();
            List<Plantel> plantelListOld = persistentVacacion.getPlantelList();
            List<Plantel> plantelListNew = vacacion.getPlantelList();
            if (idTipovacacionNew != null) {
                idTipovacacionNew = em.getReference(idTipovacacionNew.getClass(), idTipovacacionNew.getIdTipovacacion());
                vacacion.setIdTipovacacion(idTipovacacionNew);
            }
            if (idCalendariorectoriaNew != null) {
                idCalendariorectoriaNew = em.getReference(idCalendariorectoriaNew.getClass(), idCalendariorectoriaNew.getIdCalendariorectoria());
                vacacion.setIdCalendariorectoria(idCalendariorectoriaNew);
            }
            List<Plantel> attachedPlantelListNew = new ArrayList<Plantel>();
            for (Plantel plantelListNewPlantelToAttach : plantelListNew) {
                plantelListNewPlantelToAttach = em.getReference(plantelListNewPlantelToAttach.getClass(), plantelListNewPlantelToAttach.getIdplantel());
                attachedPlantelListNew.add(plantelListNewPlantelToAttach);
            }
            plantelListNew = attachedPlantelListNew;
            vacacion.setPlantelList(plantelListNew);
            vacacion = em.merge(vacacion);
            if (idTipovacacionOld != null && !idTipovacacionOld.equals(idTipovacacionNew)) {
                idTipovacacionOld.getVacacionList().remove(vacacion);
                idTipovacacionOld = em.merge(idTipovacacionOld);
            }
            if (idTipovacacionNew != null && !idTipovacacionNew.equals(idTipovacacionOld)) {
                idTipovacacionNew.getVacacionList().add(vacacion);
                idTipovacacionNew = em.merge(idTipovacacionNew);
            }
            if (idCalendariorectoriaOld != null && !idCalendariorectoriaOld.equals(idCalendariorectoriaNew)) {
                idCalendariorectoriaOld.getVacacionList().remove(vacacion);
                idCalendariorectoriaOld = em.merge(idCalendariorectoriaOld);
            }
            if (idCalendariorectoriaNew != null && !idCalendariorectoriaNew.equals(idCalendariorectoriaOld)) {
                idCalendariorectoriaNew.getVacacionList().add(vacacion);
                idCalendariorectoriaNew = em.merge(idCalendariorectoriaNew);
            }
            for (Plantel plantelListOldPlantel : plantelListOld) {
                if (!plantelListNew.contains(plantelListOldPlantel)) {
                    plantelListOldPlantel.getVacacionList().remove(vacacion);
                    plantelListOldPlantel = em.merge(plantelListOldPlantel);
                }
            }
            for (Plantel plantelListNewPlantel : plantelListNew) {
                if (!plantelListOld.contains(plantelListNewPlantel)) {
                    plantelListNewPlantel.getVacacionList().add(vacacion);
                    plantelListNewPlantel = em.merge(plantelListNewPlantel);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vacacion.getIdVacacion();
                if (findVacacion(id) == null) {
                    throw new NonexistentEntityException("The vacacion with id " + id + " no longer exists.");
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
            Vacacion vacacion;
            try {
                vacacion = em.getReference(Vacacion.class, id);
                vacacion.getIdVacacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacacion with id " + id + " no longer exists.", enfe);
            }
            TipoVacacion idTipovacacion = vacacion.getIdTipovacacion();
            if (idTipovacacion != null) {
                idTipovacacion.getVacacionList().remove(vacacion);
                idTipovacacion = em.merge(idTipovacacion);
            }
            CalendarioRectoria idCalendariorectoria = vacacion.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getVacacionList().remove(vacacion);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            List<Plantel> plantelList = vacacion.getPlantelList();
            for (Plantel plantelListPlantel : plantelList) {
                plantelListPlantel.getVacacionList().remove(vacacion);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            em.remove(vacacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vacacion> findVacacionEntities() {
        return findVacacionEntities(true, -1, -1);
    }

    public List<Vacacion> findVacacionEntities(int maxResults, int firstResult) {
        return findVacacionEntities(false, maxResults, firstResult);
    }

    private List<Vacacion> findVacacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Vacacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vacacion findVacacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Vacacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
