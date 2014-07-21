/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.CalendarioRectoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.integracion.entity.Vacacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.SuspensionLabores;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalendarioRectoriaJpaController implements Serializable {

    public CalendarioRectoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CalendarioRectoria calendarioRectoria) {
        if (calendarioRectoria.getVacacionList() == null) {
            calendarioRectoria.setVacacionList(new ArrayList<Vacacion>());
        }
        if (calendarioRectoria.getSuspensionLaboresList() == null) {
            calendarioRectoria.setSuspensionLaboresList(new ArrayList<SuspensionLabores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo idPeriodo = calendarioRectoria.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo = em.getReference(idPeriodo.getClass(), idPeriodo.getPeriodo());
                calendarioRectoria.setIdPeriodo(idPeriodo);
            }
            List<Vacacion> attachedVacacionList = new ArrayList<Vacacion>();
            for (Vacacion vacacionListVacacionToAttach : calendarioRectoria.getVacacionList()) {
                vacacionListVacacionToAttach = em.getReference(vacacionListVacacionToAttach.getClass(), vacacionListVacacionToAttach.getIdVacacion());
                attachedVacacionList.add(vacacionListVacacionToAttach);
            }
            calendarioRectoria.setVacacionList(attachedVacacionList);
            List<SuspensionLabores> attachedSuspensionLaboresList = new ArrayList<SuspensionLabores>();
            for (SuspensionLabores suspensionLaboresListSuspensionLaboresToAttach : calendarioRectoria.getSuspensionLaboresList()) {
                suspensionLaboresListSuspensionLaboresToAttach = em.getReference(suspensionLaboresListSuspensionLaboresToAttach.getClass(), suspensionLaboresListSuspensionLaboresToAttach.getIdSuspensionlabores());
                attachedSuspensionLaboresList.add(suspensionLaboresListSuspensionLaboresToAttach);
            }
            calendarioRectoria.setSuspensionLaboresList(attachedSuspensionLaboresList);
            em.persist(calendarioRectoria);
            if (idPeriodo != null) {
                idPeriodo.getCalendarioRectoriaList().add(calendarioRectoria);
                idPeriodo = em.merge(idPeriodo);
            }
            for (Vacacion vacacionListVacacion : calendarioRectoria.getVacacionList()) {
                CalendarioRectoria oldIdCalendariorectoriaOfVacacionListVacacion = vacacionListVacacion.getIdCalendariorectoria();
                vacacionListVacacion.setIdCalendariorectoria(calendarioRectoria);
                vacacionListVacacion = em.merge(vacacionListVacacion);
                if (oldIdCalendariorectoriaOfVacacionListVacacion != null) {
                    oldIdCalendariorectoriaOfVacacionListVacacion.getVacacionList().remove(vacacionListVacacion);
                    oldIdCalendariorectoriaOfVacacionListVacacion = em.merge(oldIdCalendariorectoriaOfVacacionListVacacion);
                }
            }
            for (SuspensionLabores suspensionLaboresListSuspensionLabores : calendarioRectoria.getSuspensionLaboresList()) {
                CalendarioRectoria oldIdCalendariorectoriaOfSuspensionLaboresListSuspensionLabores = suspensionLaboresListSuspensionLabores.getIdCalendariorectoria();
                suspensionLaboresListSuspensionLabores.setIdCalendariorectoria(calendarioRectoria);
                suspensionLaboresListSuspensionLabores = em.merge(suspensionLaboresListSuspensionLabores);
                if (oldIdCalendariorectoriaOfSuspensionLaboresListSuspensionLabores != null) {
                    oldIdCalendariorectoriaOfSuspensionLaboresListSuspensionLabores.getSuspensionLaboresList().remove(suspensionLaboresListSuspensionLabores);
                    oldIdCalendariorectoriaOfSuspensionLaboresListSuspensionLabores = em.merge(oldIdCalendariorectoriaOfSuspensionLaboresListSuspensionLabores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CalendarioRectoria calendarioRectoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioRectoria persistentCalendarioRectoria = em.find(CalendarioRectoria.class, calendarioRectoria.getIdCalendariorectoria());
            Periodo idPeriodoOld = persistentCalendarioRectoria.getIdPeriodo();
            Periodo idPeriodoNew = calendarioRectoria.getIdPeriodo();
            List<Vacacion> vacacionListOld = persistentCalendarioRectoria.getVacacionList();
            List<Vacacion> vacacionListNew = calendarioRectoria.getVacacionList();
            List<SuspensionLabores> suspensionLaboresListOld = persistentCalendarioRectoria.getSuspensionLaboresList();
            List<SuspensionLabores> suspensionLaboresListNew = calendarioRectoria.getSuspensionLaboresList();
            if (idPeriodoNew != null) {
                idPeriodoNew = em.getReference(idPeriodoNew.getClass(), idPeriodoNew.getPeriodo());
                calendarioRectoria.setIdPeriodo(idPeriodoNew);
            }
            List<Vacacion> attachedVacacionListNew = new ArrayList<Vacacion>();
            for (Vacacion vacacionListNewVacacionToAttach : vacacionListNew) {
                vacacionListNewVacacionToAttach = em.getReference(vacacionListNewVacacionToAttach.getClass(), vacacionListNewVacacionToAttach.getIdVacacion());
                attachedVacacionListNew.add(vacacionListNewVacacionToAttach);
            }
            vacacionListNew = attachedVacacionListNew;
            calendarioRectoria.setVacacionList(vacacionListNew);
            List<SuspensionLabores> attachedSuspensionLaboresListNew = new ArrayList<SuspensionLabores>();
            for (SuspensionLabores suspensionLaboresListNewSuspensionLaboresToAttach : suspensionLaboresListNew) {
                suspensionLaboresListNewSuspensionLaboresToAttach = em.getReference(suspensionLaboresListNewSuspensionLaboresToAttach.getClass(), suspensionLaboresListNewSuspensionLaboresToAttach.getIdSuspensionlabores());
                attachedSuspensionLaboresListNew.add(suspensionLaboresListNewSuspensionLaboresToAttach);
            }
            suspensionLaboresListNew = attachedSuspensionLaboresListNew;
            calendarioRectoria.setSuspensionLaboresList(suspensionLaboresListNew);
            calendarioRectoria = em.merge(calendarioRectoria);
            if (idPeriodoOld != null && !idPeriodoOld.equals(idPeriodoNew)) {
                idPeriodoOld.getCalendarioRectoriaList().remove(calendarioRectoria);
                idPeriodoOld = em.merge(idPeriodoOld);
            }
            if (idPeriodoNew != null && !idPeriodoNew.equals(idPeriodoOld)) {
                idPeriodoNew.getCalendarioRectoriaList().add(calendarioRectoria);
                idPeriodoNew = em.merge(idPeriodoNew);
            }
            for (Vacacion vacacionListOldVacacion : vacacionListOld) {
                if (!vacacionListNew.contains(vacacionListOldVacacion)) {
                    vacacionListOldVacacion.setIdCalendariorectoria(null);
                    vacacionListOldVacacion = em.merge(vacacionListOldVacacion);
                }
            }
            for (Vacacion vacacionListNewVacacion : vacacionListNew) {
                if (!vacacionListOld.contains(vacacionListNewVacacion)) {
                    CalendarioRectoria oldIdCalendariorectoriaOfVacacionListNewVacacion = vacacionListNewVacacion.getIdCalendariorectoria();
                    vacacionListNewVacacion.setIdCalendariorectoria(calendarioRectoria);
                    vacacionListNewVacacion = em.merge(vacacionListNewVacacion);
                    if (oldIdCalendariorectoriaOfVacacionListNewVacacion != null && !oldIdCalendariorectoriaOfVacacionListNewVacacion.equals(calendarioRectoria)) {
                        oldIdCalendariorectoriaOfVacacionListNewVacacion.getVacacionList().remove(vacacionListNewVacacion);
                        oldIdCalendariorectoriaOfVacacionListNewVacacion = em.merge(oldIdCalendariorectoriaOfVacacionListNewVacacion);
                    }
                }
            }
            for (SuspensionLabores suspensionLaboresListOldSuspensionLabores : suspensionLaboresListOld) {
                if (!suspensionLaboresListNew.contains(suspensionLaboresListOldSuspensionLabores)) {
                    suspensionLaboresListOldSuspensionLabores.setIdCalendariorectoria(null);
                    suspensionLaboresListOldSuspensionLabores = em.merge(suspensionLaboresListOldSuspensionLabores);
                }
            }
            for (SuspensionLabores suspensionLaboresListNewSuspensionLabores : suspensionLaboresListNew) {
                if (!suspensionLaboresListOld.contains(suspensionLaboresListNewSuspensionLabores)) {
                    CalendarioRectoria oldIdCalendariorectoriaOfSuspensionLaboresListNewSuspensionLabores = suspensionLaboresListNewSuspensionLabores.getIdCalendariorectoria();
                    suspensionLaboresListNewSuspensionLabores.setIdCalendariorectoria(calendarioRectoria);
                    suspensionLaboresListNewSuspensionLabores = em.merge(suspensionLaboresListNewSuspensionLabores);
                    if (oldIdCalendariorectoriaOfSuspensionLaboresListNewSuspensionLabores != null && !oldIdCalendariorectoriaOfSuspensionLaboresListNewSuspensionLabores.equals(calendarioRectoria)) {
                        oldIdCalendariorectoriaOfSuspensionLaboresListNewSuspensionLabores.getSuspensionLaboresList().remove(suspensionLaboresListNewSuspensionLabores);
                        oldIdCalendariorectoriaOfSuspensionLaboresListNewSuspensionLabores = em.merge(oldIdCalendariorectoriaOfSuspensionLaboresListNewSuspensionLabores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calendarioRectoria.getIdCalendariorectoria();
                if (findCalendarioRectoria(id) == null) {
                    throw new NonexistentEntityException("The calendarioRectoria with id " + id + " no longer exists.");
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
            CalendarioRectoria calendarioRectoria;
            try {
                calendarioRectoria = em.getReference(CalendarioRectoria.class, id);
                calendarioRectoria.getIdCalendariorectoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendarioRectoria with id " + id + " no longer exists.", enfe);
            }
            Periodo idPeriodo = calendarioRectoria.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo.getCalendarioRectoriaList().remove(calendarioRectoria);
                idPeriodo = em.merge(idPeriodo);
            }
            List<Vacacion> vacacionList = calendarioRectoria.getVacacionList();
            for (Vacacion vacacionListVacacion : vacacionList) {
                vacacionListVacacion.setIdCalendariorectoria(null);
                vacacionListVacacion = em.merge(vacacionListVacacion);
            }
            List<SuspensionLabores> suspensionLaboresList = calendarioRectoria.getSuspensionLaboresList();
            for (SuspensionLabores suspensionLaboresListSuspensionLabores : suspensionLaboresList) {
                suspensionLaboresListSuspensionLabores.setIdCalendariorectoria(null);
                suspensionLaboresListSuspensionLabores = em.merge(suspensionLaboresListSuspensionLabores);
            }
            em.remove(calendarioRectoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CalendarioRectoria> findCalendarioRectoriaEntities() {
        return findCalendarioRectoriaEntities(true, -1, -1);
    }

    public List<CalendarioRectoria> findCalendarioRectoriaEntities(int maxResults, int firstResult) {
        return findCalendarioRectoriaEntities(false, maxResults, firstResult);
    }

    private List<CalendarioRectoria> findCalendarioRectoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CalendarioRectoria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CalendarioRectoria findCalendarioRectoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CalendarioRectoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioRectoriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CalendarioRectoria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
