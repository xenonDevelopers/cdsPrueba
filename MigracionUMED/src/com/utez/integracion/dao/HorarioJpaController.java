/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaInduccion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.Horario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HorarioJpaController implements Serializable {

    public HorarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) {
        if (horario.getFechaInduccionList() == null) {
            horario.setFechaInduccionList(new ArrayList<FechaInduccion>());
        }
        if (horario.getCalendarioAsignaturaList() == null) {
            horario.setCalendarioAsignaturaList(new ArrayList<CalendarioAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<FechaInduccion> attachedFechaInduccionList = new ArrayList<FechaInduccion>();
            for (FechaInduccion fechaInduccionListFechaInduccionToAttach : horario.getFechaInduccionList()) {
                fechaInduccionListFechaInduccionToAttach = em.getReference(fechaInduccionListFechaInduccionToAttach.getClass(), fechaInduccionListFechaInduccionToAttach.getIdFechainduccion());
                attachedFechaInduccionList.add(fechaInduccionListFechaInduccionToAttach);
            }
            horario.setFechaInduccionList(attachedFechaInduccionList);
            List<CalendarioAsignatura> attachedCalendarioAsignaturaList = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignaturaToAttach : horario.getCalendarioAsignaturaList()) {
                calendarioAsignaturaListCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaList.add(calendarioAsignaturaListCalendarioAsignaturaToAttach);
            }
            horario.setCalendarioAsignaturaList(attachedCalendarioAsignaturaList);
            em.persist(horario);
            for (FechaInduccion fechaInduccionListFechaInduccion : horario.getFechaInduccionList()) {
                Horario oldIdHorarioOfFechaInduccionListFechaInduccion = fechaInduccionListFechaInduccion.getIdHorario();
                fechaInduccionListFechaInduccion.setIdHorario(horario);
                fechaInduccionListFechaInduccion = em.merge(fechaInduccionListFechaInduccion);
                if (oldIdHorarioOfFechaInduccionListFechaInduccion != null) {
                    oldIdHorarioOfFechaInduccionListFechaInduccion.getFechaInduccionList().remove(fechaInduccionListFechaInduccion);
                    oldIdHorarioOfFechaInduccionListFechaInduccion = em.merge(oldIdHorarioOfFechaInduccionListFechaInduccion);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : horario.getCalendarioAsignaturaList()) {
                Horario oldIdHorarioOfCalendarioAsignaturaListCalendarioAsignatura = calendarioAsignaturaListCalendarioAsignatura.getIdHorario();
                calendarioAsignaturaListCalendarioAsignatura.setIdHorario(horario);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
                if (oldIdHorarioOfCalendarioAsignaturaListCalendarioAsignatura != null) {
                    oldIdHorarioOfCalendarioAsignaturaListCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListCalendarioAsignatura);
                    oldIdHorarioOfCalendarioAsignaturaListCalendarioAsignatura = em.merge(oldIdHorarioOfCalendarioAsignaturaListCalendarioAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getIdHorario());
            List<FechaInduccion> fechaInduccionListOld = persistentHorario.getFechaInduccionList();
            List<FechaInduccion> fechaInduccionListNew = horario.getFechaInduccionList();
            List<CalendarioAsignatura> calendarioAsignaturaListOld = persistentHorario.getCalendarioAsignaturaList();
            List<CalendarioAsignatura> calendarioAsignaturaListNew = horario.getCalendarioAsignaturaList();
            List<FechaInduccion> attachedFechaInduccionListNew = new ArrayList<FechaInduccion>();
            for (FechaInduccion fechaInduccionListNewFechaInduccionToAttach : fechaInduccionListNew) {
                fechaInduccionListNewFechaInduccionToAttach = em.getReference(fechaInduccionListNewFechaInduccionToAttach.getClass(), fechaInduccionListNewFechaInduccionToAttach.getIdFechainduccion());
                attachedFechaInduccionListNew.add(fechaInduccionListNewFechaInduccionToAttach);
            }
            fechaInduccionListNew = attachedFechaInduccionListNew;
            horario.setFechaInduccionList(fechaInduccionListNew);
            List<CalendarioAsignatura> attachedCalendarioAsignaturaListNew = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignaturaToAttach : calendarioAsignaturaListNew) {
                calendarioAsignaturaListNewCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaListNew.add(calendarioAsignaturaListNewCalendarioAsignaturaToAttach);
            }
            calendarioAsignaturaListNew = attachedCalendarioAsignaturaListNew;
            horario.setCalendarioAsignaturaList(calendarioAsignaturaListNew);
            horario = em.merge(horario);
            for (FechaInduccion fechaInduccionListOldFechaInduccion : fechaInduccionListOld) {
                if (!fechaInduccionListNew.contains(fechaInduccionListOldFechaInduccion)) {
                    fechaInduccionListOldFechaInduccion.setIdHorario(null);
                    fechaInduccionListOldFechaInduccion = em.merge(fechaInduccionListOldFechaInduccion);
                }
            }
            for (FechaInduccion fechaInduccionListNewFechaInduccion : fechaInduccionListNew) {
                if (!fechaInduccionListOld.contains(fechaInduccionListNewFechaInduccion)) {
                    Horario oldIdHorarioOfFechaInduccionListNewFechaInduccion = fechaInduccionListNewFechaInduccion.getIdHorario();
                    fechaInduccionListNewFechaInduccion.setIdHorario(horario);
                    fechaInduccionListNewFechaInduccion = em.merge(fechaInduccionListNewFechaInduccion);
                    if (oldIdHorarioOfFechaInduccionListNewFechaInduccion != null && !oldIdHorarioOfFechaInduccionListNewFechaInduccion.equals(horario)) {
                        oldIdHorarioOfFechaInduccionListNewFechaInduccion.getFechaInduccionList().remove(fechaInduccionListNewFechaInduccion);
                        oldIdHorarioOfFechaInduccionListNewFechaInduccion = em.merge(oldIdHorarioOfFechaInduccionListNewFechaInduccion);
                    }
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListOldCalendarioAsignatura : calendarioAsignaturaListOld) {
                if (!calendarioAsignaturaListNew.contains(calendarioAsignaturaListOldCalendarioAsignatura)) {
                    calendarioAsignaturaListOldCalendarioAsignatura.setIdHorario(null);
                    calendarioAsignaturaListOldCalendarioAsignatura = em.merge(calendarioAsignaturaListOldCalendarioAsignatura);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignatura : calendarioAsignaturaListNew) {
                if (!calendarioAsignaturaListOld.contains(calendarioAsignaturaListNewCalendarioAsignatura)) {
                    Horario oldIdHorarioOfCalendarioAsignaturaListNewCalendarioAsignatura = calendarioAsignaturaListNewCalendarioAsignatura.getIdHorario();
                    calendarioAsignaturaListNewCalendarioAsignatura.setIdHorario(horario);
                    calendarioAsignaturaListNewCalendarioAsignatura = em.merge(calendarioAsignaturaListNewCalendarioAsignatura);
                    if (oldIdHorarioOfCalendarioAsignaturaListNewCalendarioAsignatura != null && !oldIdHorarioOfCalendarioAsignaturaListNewCalendarioAsignatura.equals(horario)) {
                        oldIdHorarioOfCalendarioAsignaturaListNewCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListNewCalendarioAsignatura);
                        oldIdHorarioOfCalendarioAsignaturaListNewCalendarioAsignatura = em.merge(oldIdHorarioOfCalendarioAsignaturaListNewCalendarioAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = horario.getIdHorario();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getIdHorario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<FechaInduccion> fechaInduccionList = horario.getFechaInduccionList();
            for (FechaInduccion fechaInduccionListFechaInduccion : fechaInduccionList) {
                fechaInduccionListFechaInduccion.setIdHorario(null);
                fechaInduccionListFechaInduccion = em.merge(fechaInduccionListFechaInduccion);
            }
            List<CalendarioAsignatura> calendarioAsignaturaList = horario.getCalendarioAsignaturaList();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : calendarioAsignaturaList) {
                calendarioAsignaturaListCalendarioAsignatura.setIdHorario(null);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Horario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Horario findHorario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Horario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
