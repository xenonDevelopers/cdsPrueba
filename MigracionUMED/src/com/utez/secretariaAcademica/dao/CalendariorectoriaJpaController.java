/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Calendariorectoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.secretariaAcademica.entity.Vacaciones;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Suspensionlabores;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalendariorectoriaJpaController implements Serializable {

    public CalendariorectoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calendariorectoria calendariorectoria) {
        if (calendariorectoria.getVacacionesList() == null) {
            calendariorectoria.setVacacionesList(new ArrayList<Vacaciones>());
        }
        if (calendariorectoria.getSuspensionlaboresList() == null) {
            calendariorectoria.setSuspensionlaboresList(new ArrayList<Suspensionlabores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idplantel = calendariorectoria.getIdplantel();
            if (idplantel != null) {
                idplantel = em.getReference(idplantel.getClass(), idplantel.getIdplantel());
                calendariorectoria.setIdplantel(idplantel);
            }
            List<Vacaciones> attachedVacacionesList = new ArrayList<Vacaciones>();
            for (Vacaciones vacacionesListVacacionesToAttach : calendariorectoria.getVacacionesList()) {
                vacacionesListVacacionesToAttach = em.getReference(vacacionesListVacacionesToAttach.getClass(), vacacionesListVacacionesToAttach.getIdvacaciones());
                attachedVacacionesList.add(vacacionesListVacacionesToAttach);
            }
            calendariorectoria.setVacacionesList(attachedVacacionesList);
            List<Suspensionlabores> attachedSuspensionlaboresList = new ArrayList<Suspensionlabores>();
            for (Suspensionlabores suspensionlaboresListSuspensionlaboresToAttach : calendariorectoria.getSuspensionlaboresList()) {
                suspensionlaboresListSuspensionlaboresToAttach = em.getReference(suspensionlaboresListSuspensionlaboresToAttach.getClass(), suspensionlaboresListSuspensionlaboresToAttach.getIdsuspensionlabores());
                attachedSuspensionlaboresList.add(suspensionlaboresListSuspensionlaboresToAttach);
            }
            calendariorectoria.setSuspensionlaboresList(attachedSuspensionlaboresList);
            em.persist(calendariorectoria);
            if (idplantel != null) {
                idplantel.getCalendariorectoriaList().add(calendariorectoria);
                idplantel = em.merge(idplantel);
            }
            for (Vacaciones vacacionesListVacaciones : calendariorectoria.getVacacionesList()) {
                Calendariorectoria oldIdcalendariorectoriaOfVacacionesListVacaciones = vacacionesListVacaciones.getIdcalendariorectoria();
                vacacionesListVacaciones.setIdcalendariorectoria(calendariorectoria);
                vacacionesListVacaciones = em.merge(vacacionesListVacaciones);
                if (oldIdcalendariorectoriaOfVacacionesListVacaciones != null) {
                    oldIdcalendariorectoriaOfVacacionesListVacaciones.getVacacionesList().remove(vacacionesListVacaciones);
                    oldIdcalendariorectoriaOfVacacionesListVacaciones = em.merge(oldIdcalendariorectoriaOfVacacionesListVacaciones);
                }
            }
            for (Suspensionlabores suspensionlaboresListSuspensionlabores : calendariorectoria.getSuspensionlaboresList()) {
                Calendariorectoria oldIdcalendariorectoriaOfSuspensionlaboresListSuspensionlabores = suspensionlaboresListSuspensionlabores.getIdcalendariorectoria();
                suspensionlaboresListSuspensionlabores.setIdcalendariorectoria(calendariorectoria);
                suspensionlaboresListSuspensionlabores = em.merge(suspensionlaboresListSuspensionlabores);
                if (oldIdcalendariorectoriaOfSuspensionlaboresListSuspensionlabores != null) {
                    oldIdcalendariorectoriaOfSuspensionlaboresListSuspensionlabores.getSuspensionlaboresList().remove(suspensionlaboresListSuspensionlabores);
                    oldIdcalendariorectoriaOfSuspensionlaboresListSuspensionlabores = em.merge(oldIdcalendariorectoriaOfSuspensionlaboresListSuspensionlabores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calendariorectoria calendariorectoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendariorectoria persistentCalendariorectoria = em.find(Calendariorectoria.class, calendariorectoria.getIdcalendariorectoria());
            Plantel idplantelOld = persistentCalendariorectoria.getIdplantel();
            Plantel idplantelNew = calendariorectoria.getIdplantel();
            List<Vacaciones> vacacionesListOld = persistentCalendariorectoria.getVacacionesList();
            List<Vacaciones> vacacionesListNew = calendariorectoria.getVacacionesList();
            List<Suspensionlabores> suspensionlaboresListOld = persistentCalendariorectoria.getSuspensionlaboresList();
            List<Suspensionlabores> suspensionlaboresListNew = calendariorectoria.getSuspensionlaboresList();
            if (idplantelNew != null) {
                idplantelNew = em.getReference(idplantelNew.getClass(), idplantelNew.getIdplantel());
                calendariorectoria.setIdplantel(idplantelNew);
            }
            List<Vacaciones> attachedVacacionesListNew = new ArrayList<Vacaciones>();
            for (Vacaciones vacacionesListNewVacacionesToAttach : vacacionesListNew) {
                vacacionesListNewVacacionesToAttach = em.getReference(vacacionesListNewVacacionesToAttach.getClass(), vacacionesListNewVacacionesToAttach.getIdvacaciones());
                attachedVacacionesListNew.add(vacacionesListNewVacacionesToAttach);
            }
            vacacionesListNew = attachedVacacionesListNew;
            calendariorectoria.setVacacionesList(vacacionesListNew);
            List<Suspensionlabores> attachedSuspensionlaboresListNew = new ArrayList<Suspensionlabores>();
            for (Suspensionlabores suspensionlaboresListNewSuspensionlaboresToAttach : suspensionlaboresListNew) {
                suspensionlaboresListNewSuspensionlaboresToAttach = em.getReference(suspensionlaboresListNewSuspensionlaboresToAttach.getClass(), suspensionlaboresListNewSuspensionlaboresToAttach.getIdsuspensionlabores());
                attachedSuspensionlaboresListNew.add(suspensionlaboresListNewSuspensionlaboresToAttach);
            }
            suspensionlaboresListNew = attachedSuspensionlaboresListNew;
            calendariorectoria.setSuspensionlaboresList(suspensionlaboresListNew);
            calendariorectoria = em.merge(calendariorectoria);
            if (idplantelOld != null && !idplantelOld.equals(idplantelNew)) {
                idplantelOld.getCalendariorectoriaList().remove(calendariorectoria);
                idplantelOld = em.merge(idplantelOld);
            }
            if (idplantelNew != null && !idplantelNew.equals(idplantelOld)) {
                idplantelNew.getCalendariorectoriaList().add(calendariorectoria);
                idplantelNew = em.merge(idplantelNew);
            }
            for (Vacaciones vacacionesListOldVacaciones : vacacionesListOld) {
                if (!vacacionesListNew.contains(vacacionesListOldVacaciones)) {
                    vacacionesListOldVacaciones.setIdcalendariorectoria(null);
                    vacacionesListOldVacaciones = em.merge(vacacionesListOldVacaciones);
                }
            }
            for (Vacaciones vacacionesListNewVacaciones : vacacionesListNew) {
                if (!vacacionesListOld.contains(vacacionesListNewVacaciones)) {
                    Calendariorectoria oldIdcalendariorectoriaOfVacacionesListNewVacaciones = vacacionesListNewVacaciones.getIdcalendariorectoria();
                    vacacionesListNewVacaciones.setIdcalendariorectoria(calendariorectoria);
                    vacacionesListNewVacaciones = em.merge(vacacionesListNewVacaciones);
                    if (oldIdcalendariorectoriaOfVacacionesListNewVacaciones != null && !oldIdcalendariorectoriaOfVacacionesListNewVacaciones.equals(calendariorectoria)) {
                        oldIdcalendariorectoriaOfVacacionesListNewVacaciones.getVacacionesList().remove(vacacionesListNewVacaciones);
                        oldIdcalendariorectoriaOfVacacionesListNewVacaciones = em.merge(oldIdcalendariorectoriaOfVacacionesListNewVacaciones);
                    }
                }
            }
            for (Suspensionlabores suspensionlaboresListOldSuspensionlabores : suspensionlaboresListOld) {
                if (!suspensionlaboresListNew.contains(suspensionlaboresListOldSuspensionlabores)) {
                    suspensionlaboresListOldSuspensionlabores.setIdcalendariorectoria(null);
                    suspensionlaboresListOldSuspensionlabores = em.merge(suspensionlaboresListOldSuspensionlabores);
                }
            }
            for (Suspensionlabores suspensionlaboresListNewSuspensionlabores : suspensionlaboresListNew) {
                if (!suspensionlaboresListOld.contains(suspensionlaboresListNewSuspensionlabores)) {
                    Calendariorectoria oldIdcalendariorectoriaOfSuspensionlaboresListNewSuspensionlabores = suspensionlaboresListNewSuspensionlabores.getIdcalendariorectoria();
                    suspensionlaboresListNewSuspensionlabores.setIdcalendariorectoria(calendariorectoria);
                    suspensionlaboresListNewSuspensionlabores = em.merge(suspensionlaboresListNewSuspensionlabores);
                    if (oldIdcalendariorectoriaOfSuspensionlaboresListNewSuspensionlabores != null && !oldIdcalendariorectoriaOfSuspensionlaboresListNewSuspensionlabores.equals(calendariorectoria)) {
                        oldIdcalendariorectoriaOfSuspensionlaboresListNewSuspensionlabores.getSuspensionlaboresList().remove(suspensionlaboresListNewSuspensionlabores);
                        oldIdcalendariorectoriaOfSuspensionlaboresListNewSuspensionlabores = em.merge(oldIdcalendariorectoriaOfSuspensionlaboresListNewSuspensionlabores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calendariorectoria.getIdcalendariorectoria();
                if (findCalendariorectoria(id) == null) {
                    throw new NonexistentEntityException("The calendariorectoria with id " + id + " no longer exists.");
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
            Calendariorectoria calendariorectoria;
            try {
                calendariorectoria = em.getReference(Calendariorectoria.class, id);
                calendariorectoria.getIdcalendariorectoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendariorectoria with id " + id + " no longer exists.", enfe);
            }
            Plantel idplantel = calendariorectoria.getIdplantel();
            if (idplantel != null) {
                idplantel.getCalendariorectoriaList().remove(calendariorectoria);
                idplantel = em.merge(idplantel);
            }
            List<Vacaciones> vacacionesList = calendariorectoria.getVacacionesList();
            for (Vacaciones vacacionesListVacaciones : vacacionesList) {
                vacacionesListVacaciones.setIdcalendariorectoria(null);
                vacacionesListVacaciones = em.merge(vacacionesListVacaciones);
            }
            List<Suspensionlabores> suspensionlaboresList = calendariorectoria.getSuspensionlaboresList();
            for (Suspensionlabores suspensionlaboresListSuspensionlabores : suspensionlaboresList) {
                suspensionlaboresListSuspensionlabores.setIdcalendariorectoria(null);
                suspensionlaboresListSuspensionlabores = em.merge(suspensionlaboresListSuspensionlabores);
            }
            em.remove(calendariorectoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calendariorectoria> findCalendariorectoriaEntities() {
        return findCalendariorectoriaEntities(true, -1, -1);
    }

    public List<Calendariorectoria> findCalendariorectoriaEntities(int maxResults, int firstResult) {
        return findCalendariorectoriaEntities(false, maxResults, firstResult);
    }

    private List<Calendariorectoria> findCalendariorectoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Calendariorectoria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calendariorectoria findCalendariorectoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calendariorectoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendariorectoriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Calendariorectoria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
