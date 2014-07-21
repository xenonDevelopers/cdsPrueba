/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoExamen;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.EncuestaDocente;
import com.utez.integracion.entity.FechaExamenprogramado;
import com.utez.integracion.entity.ExamenImpreso;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.FechaExamen;
import com.utez.integracion.entity.HistoricoFechaexamen;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechaExamenJpaController implements Serializable {

    public FechaExamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FechaExamen fechaExamen) {
        if (fechaExamen.getExamenImpresoList() == null) {
            fechaExamen.setExamenImpresoList(new ArrayList<ExamenImpreso>());
        }
        if (fechaExamen.getActaList() == null) {
            fechaExamen.setActaList(new ArrayList<Acta>());
        }
        if (fechaExamen.getHistoricoFechaexamenList() == null) {
            fechaExamen.setHistoricoFechaexamenList(new ArrayList<HistoricoFechaexamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen idTipoexamen = fechaExamen.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen = em.getReference(idTipoexamen.getClass(), idTipoexamen.getIdTipoexamen());
                fechaExamen.setIdTipoexamen(idTipoexamen);
            }
            CalendarioAsignatura idCalendarioasignatura = fechaExamen.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura = em.getReference(idCalendarioasignatura.getClass(), idCalendarioasignatura.getIdCalendarioasignatura());
                fechaExamen.setIdCalendarioasignatura(idCalendarioasignatura);
            }
            EncuestaDocente encuestaDocente = fechaExamen.getEncuestaDocente();
            if (encuestaDocente != null) {
                encuestaDocente = em.getReference(encuestaDocente.getClass(), encuestaDocente.getIdFechaexamen());
                fechaExamen.setEncuestaDocente(encuestaDocente);
            }
            FechaExamenprogramado fechaExamenprogramado = fechaExamen.getFechaExamenprogramado();
            if (fechaExamenprogramado != null) {
                fechaExamenprogramado = em.getReference(fechaExamenprogramado.getClass(), fechaExamenprogramado.getIdFechaExamen());
                fechaExamen.setFechaExamenprogramado(fechaExamenprogramado);
            }
            List<ExamenImpreso> attachedExamenImpresoList = new ArrayList<ExamenImpreso>();
            for (ExamenImpreso examenImpresoListExamenImpresoToAttach : fechaExamen.getExamenImpresoList()) {
                examenImpresoListExamenImpresoToAttach = em.getReference(examenImpresoListExamenImpresoToAttach.getClass(), examenImpresoListExamenImpresoToAttach.getIdExamenimpreso());
                attachedExamenImpresoList.add(examenImpresoListExamenImpresoToAttach);
            }
            fechaExamen.setExamenImpresoList(attachedExamenImpresoList);
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : fechaExamen.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            fechaExamen.setActaList(attachedActaList);
            List<HistoricoFechaexamen> attachedHistoricoFechaexamenList = new ArrayList<HistoricoFechaexamen>();
            for (HistoricoFechaexamen historicoFechaexamenListHistoricoFechaexamenToAttach : fechaExamen.getHistoricoFechaexamenList()) {
                historicoFechaexamenListHistoricoFechaexamenToAttach = em.getReference(historicoFechaexamenListHistoricoFechaexamenToAttach.getClass(), historicoFechaexamenListHistoricoFechaexamenToAttach.getIdHistoricoFechaexamen());
                attachedHistoricoFechaexamenList.add(historicoFechaexamenListHistoricoFechaexamenToAttach);
            }
            fechaExamen.setHistoricoFechaexamenList(attachedHistoricoFechaexamenList);
            em.persist(fechaExamen);
            if (idTipoexamen != null) {
                idTipoexamen.getFechaExamenList().add(fechaExamen);
                idTipoexamen = em.merge(idTipoexamen);
            }
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getFechaExamenList().add(fechaExamen);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            if (encuestaDocente != null) {
                FechaExamen oldFechaExamenOfEncuestaDocente = encuestaDocente.getFechaExamen();
                if (oldFechaExamenOfEncuestaDocente != null) {
                    oldFechaExamenOfEncuestaDocente.setEncuestaDocente(null);
                    oldFechaExamenOfEncuestaDocente = em.merge(oldFechaExamenOfEncuestaDocente);
                }
                encuestaDocente.setFechaExamen(fechaExamen);
                encuestaDocente = em.merge(encuestaDocente);
            }
            if (fechaExamenprogramado != null) {
                FechaExamen oldFechaExamen1OfFechaExamenprogramado = fechaExamenprogramado.getFechaExamen1();
                if (oldFechaExamen1OfFechaExamenprogramado != null) {
                    oldFechaExamen1OfFechaExamenprogramado.setFechaExamenprogramado(null);
                    oldFechaExamen1OfFechaExamenprogramado = em.merge(oldFechaExamen1OfFechaExamenprogramado);
                }
                fechaExamenprogramado.setFechaExamen1(fechaExamen);
                fechaExamenprogramado = em.merge(fechaExamenprogramado);
            }
            for (ExamenImpreso examenImpresoListExamenImpreso : fechaExamen.getExamenImpresoList()) {
                examenImpresoListExamenImpreso.getFechaExamenList().add(fechaExamen);
                examenImpresoListExamenImpreso = em.merge(examenImpresoListExamenImpreso);
            }
            for (Acta actaListActa : fechaExamen.getActaList()) {
                FechaExamen oldIdFechaexamenOfActaListActa = actaListActa.getIdFechaexamen();
                actaListActa.setIdFechaexamen(fechaExamen);
                actaListActa = em.merge(actaListActa);
                if (oldIdFechaexamenOfActaListActa != null) {
                    oldIdFechaexamenOfActaListActa.getActaList().remove(actaListActa);
                    oldIdFechaexamenOfActaListActa = em.merge(oldIdFechaexamenOfActaListActa);
                }
            }
            for (HistoricoFechaexamen historicoFechaexamenListHistoricoFechaexamen : fechaExamen.getHistoricoFechaexamenList()) {
                FechaExamen oldIdFechaexamenOfHistoricoFechaexamenListHistoricoFechaexamen = historicoFechaexamenListHistoricoFechaexamen.getIdFechaexamen();
                historicoFechaexamenListHistoricoFechaexamen.setIdFechaexamen(fechaExamen);
                historicoFechaexamenListHistoricoFechaexamen = em.merge(historicoFechaexamenListHistoricoFechaexamen);
                if (oldIdFechaexamenOfHistoricoFechaexamenListHistoricoFechaexamen != null) {
                    oldIdFechaexamenOfHistoricoFechaexamenListHistoricoFechaexamen.getHistoricoFechaexamenList().remove(historicoFechaexamenListHistoricoFechaexamen);
                    oldIdFechaexamenOfHistoricoFechaexamenListHistoricoFechaexamen = em.merge(oldIdFechaexamenOfHistoricoFechaexamenListHistoricoFechaexamen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FechaExamen fechaExamen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamen persistentFechaExamen = em.find(FechaExamen.class, fechaExamen.getIdFechaexamen());
            TipoExamen idTipoexamenOld = persistentFechaExamen.getIdTipoexamen();
            TipoExamen idTipoexamenNew = fechaExamen.getIdTipoexamen();
            CalendarioAsignatura idCalendarioasignaturaOld = persistentFechaExamen.getIdCalendarioasignatura();
            CalendarioAsignatura idCalendarioasignaturaNew = fechaExamen.getIdCalendarioasignatura();
            EncuestaDocente encuestaDocenteOld = persistentFechaExamen.getEncuestaDocente();
            EncuestaDocente encuestaDocenteNew = fechaExamen.getEncuestaDocente();
            FechaExamenprogramado fechaExamenprogramadoOld = persistentFechaExamen.getFechaExamenprogramado();
            FechaExamenprogramado fechaExamenprogramadoNew = fechaExamen.getFechaExamenprogramado();
            List<ExamenImpreso> examenImpresoListOld = persistentFechaExamen.getExamenImpresoList();
            List<ExamenImpreso> examenImpresoListNew = fechaExamen.getExamenImpresoList();
            List<Acta> actaListOld = persistentFechaExamen.getActaList();
            List<Acta> actaListNew = fechaExamen.getActaList();
            List<HistoricoFechaexamen> historicoFechaexamenListOld = persistentFechaExamen.getHistoricoFechaexamenList();
            List<HistoricoFechaexamen> historicoFechaexamenListNew = fechaExamen.getHistoricoFechaexamenList();
            List<String> illegalOrphanMessages = null;
            if (encuestaDocenteOld != null && !encuestaDocenteOld.equals(encuestaDocenteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain EncuestaDocente " + encuestaDocenteOld + " since its fechaExamen field is not nullable.");
            }
            if (fechaExamenprogramadoOld != null && !fechaExamenprogramadoOld.equals(fechaExamenprogramadoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain FechaExamenprogramado " + fechaExamenprogramadoOld + " since its fechaExamen1 field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoexamenNew != null) {
                idTipoexamenNew = em.getReference(idTipoexamenNew.getClass(), idTipoexamenNew.getIdTipoexamen());
                fechaExamen.setIdTipoexamen(idTipoexamenNew);
            }
            if (idCalendarioasignaturaNew != null) {
                idCalendarioasignaturaNew = em.getReference(idCalendarioasignaturaNew.getClass(), idCalendarioasignaturaNew.getIdCalendarioasignatura());
                fechaExamen.setIdCalendarioasignatura(idCalendarioasignaturaNew);
            }
            if (encuestaDocenteNew != null) {
                encuestaDocenteNew = em.getReference(encuestaDocenteNew.getClass(), encuestaDocenteNew.getIdFechaexamen());
                fechaExamen.setEncuestaDocente(encuestaDocenteNew);
            }
            if (fechaExamenprogramadoNew != null) {
                fechaExamenprogramadoNew = em.getReference(fechaExamenprogramadoNew.getClass(), fechaExamenprogramadoNew.getIdFechaExamen());
                fechaExamen.setFechaExamenprogramado(fechaExamenprogramadoNew);
            }
            List<ExamenImpreso> attachedExamenImpresoListNew = new ArrayList<ExamenImpreso>();
            for (ExamenImpreso examenImpresoListNewExamenImpresoToAttach : examenImpresoListNew) {
                examenImpresoListNewExamenImpresoToAttach = em.getReference(examenImpresoListNewExamenImpresoToAttach.getClass(), examenImpresoListNewExamenImpresoToAttach.getIdExamenimpreso());
                attachedExamenImpresoListNew.add(examenImpresoListNewExamenImpresoToAttach);
            }
            examenImpresoListNew = attachedExamenImpresoListNew;
            fechaExamen.setExamenImpresoList(examenImpresoListNew);
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            fechaExamen.setActaList(actaListNew);
            List<HistoricoFechaexamen> attachedHistoricoFechaexamenListNew = new ArrayList<HistoricoFechaexamen>();
            for (HistoricoFechaexamen historicoFechaexamenListNewHistoricoFechaexamenToAttach : historicoFechaexamenListNew) {
                historicoFechaexamenListNewHistoricoFechaexamenToAttach = em.getReference(historicoFechaexamenListNewHistoricoFechaexamenToAttach.getClass(), historicoFechaexamenListNewHistoricoFechaexamenToAttach.getIdHistoricoFechaexamen());
                attachedHistoricoFechaexamenListNew.add(historicoFechaexamenListNewHistoricoFechaexamenToAttach);
            }
            historicoFechaexamenListNew = attachedHistoricoFechaexamenListNew;
            fechaExamen.setHistoricoFechaexamenList(historicoFechaexamenListNew);
            fechaExamen = em.merge(fechaExamen);
            if (idTipoexamenOld != null && !idTipoexamenOld.equals(idTipoexamenNew)) {
                idTipoexamenOld.getFechaExamenList().remove(fechaExamen);
                idTipoexamenOld = em.merge(idTipoexamenOld);
            }
            if (idTipoexamenNew != null && !idTipoexamenNew.equals(idTipoexamenOld)) {
                idTipoexamenNew.getFechaExamenList().add(fechaExamen);
                idTipoexamenNew = em.merge(idTipoexamenNew);
            }
            if (idCalendarioasignaturaOld != null && !idCalendarioasignaturaOld.equals(idCalendarioasignaturaNew)) {
                idCalendarioasignaturaOld.getFechaExamenList().remove(fechaExamen);
                idCalendarioasignaturaOld = em.merge(idCalendarioasignaturaOld);
            }
            if (idCalendarioasignaturaNew != null && !idCalendarioasignaturaNew.equals(idCalendarioasignaturaOld)) {
                idCalendarioasignaturaNew.getFechaExamenList().add(fechaExamen);
                idCalendarioasignaturaNew = em.merge(idCalendarioasignaturaNew);
            }
            if (encuestaDocenteNew != null && !encuestaDocenteNew.equals(encuestaDocenteOld)) {
                FechaExamen oldFechaExamenOfEncuestaDocente = encuestaDocenteNew.getFechaExamen();
                if (oldFechaExamenOfEncuestaDocente != null) {
                    oldFechaExamenOfEncuestaDocente.setEncuestaDocente(null);
                    oldFechaExamenOfEncuestaDocente = em.merge(oldFechaExamenOfEncuestaDocente);
                }
                encuestaDocenteNew.setFechaExamen(fechaExamen);
                encuestaDocenteNew = em.merge(encuestaDocenteNew);
            }
            if (fechaExamenprogramadoNew != null && !fechaExamenprogramadoNew.equals(fechaExamenprogramadoOld)) {
                FechaExamen oldFechaExamen1OfFechaExamenprogramado = fechaExamenprogramadoNew.getFechaExamen1();
                if (oldFechaExamen1OfFechaExamenprogramado != null) {
                    oldFechaExamen1OfFechaExamenprogramado.setFechaExamenprogramado(null);
                    oldFechaExamen1OfFechaExamenprogramado = em.merge(oldFechaExamen1OfFechaExamenprogramado);
                }
                fechaExamenprogramadoNew.setFechaExamen1(fechaExamen);
                fechaExamenprogramadoNew = em.merge(fechaExamenprogramadoNew);
            }
            for (ExamenImpreso examenImpresoListOldExamenImpreso : examenImpresoListOld) {
                if (!examenImpresoListNew.contains(examenImpresoListOldExamenImpreso)) {
                    examenImpresoListOldExamenImpreso.getFechaExamenList().remove(fechaExamen);
                    examenImpresoListOldExamenImpreso = em.merge(examenImpresoListOldExamenImpreso);
                }
            }
            for (ExamenImpreso examenImpresoListNewExamenImpreso : examenImpresoListNew) {
                if (!examenImpresoListOld.contains(examenImpresoListNewExamenImpreso)) {
                    examenImpresoListNewExamenImpreso.getFechaExamenList().add(fechaExamen);
                    examenImpresoListNewExamenImpreso = em.merge(examenImpresoListNewExamenImpreso);
                }
            }
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setIdFechaexamen(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    FechaExamen oldIdFechaexamenOfActaListNewActa = actaListNewActa.getIdFechaexamen();
                    actaListNewActa.setIdFechaexamen(fechaExamen);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldIdFechaexamenOfActaListNewActa != null && !oldIdFechaexamenOfActaListNewActa.equals(fechaExamen)) {
                        oldIdFechaexamenOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldIdFechaexamenOfActaListNewActa = em.merge(oldIdFechaexamenOfActaListNewActa);
                    }
                }
            }
            for (HistoricoFechaexamen historicoFechaexamenListOldHistoricoFechaexamen : historicoFechaexamenListOld) {
                if (!historicoFechaexamenListNew.contains(historicoFechaexamenListOldHistoricoFechaexamen)) {
                    historicoFechaexamenListOldHistoricoFechaexamen.setIdFechaexamen(null);
                    historicoFechaexamenListOldHistoricoFechaexamen = em.merge(historicoFechaexamenListOldHistoricoFechaexamen);
                }
            }
            for (HistoricoFechaexamen historicoFechaexamenListNewHistoricoFechaexamen : historicoFechaexamenListNew) {
                if (!historicoFechaexamenListOld.contains(historicoFechaexamenListNewHistoricoFechaexamen)) {
                    FechaExamen oldIdFechaexamenOfHistoricoFechaexamenListNewHistoricoFechaexamen = historicoFechaexamenListNewHistoricoFechaexamen.getIdFechaexamen();
                    historicoFechaexamenListNewHistoricoFechaexamen.setIdFechaexamen(fechaExamen);
                    historicoFechaexamenListNewHistoricoFechaexamen = em.merge(historicoFechaexamenListNewHistoricoFechaexamen);
                    if (oldIdFechaexamenOfHistoricoFechaexamenListNewHistoricoFechaexamen != null && !oldIdFechaexamenOfHistoricoFechaexamenListNewHistoricoFechaexamen.equals(fechaExamen)) {
                        oldIdFechaexamenOfHistoricoFechaexamenListNewHistoricoFechaexamen.getHistoricoFechaexamenList().remove(historicoFechaexamenListNewHistoricoFechaexamen);
                        oldIdFechaexamenOfHistoricoFechaexamenListNewHistoricoFechaexamen = em.merge(oldIdFechaexamenOfHistoricoFechaexamenListNewHistoricoFechaexamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fechaExamen.getIdFechaexamen();
                if (findFechaExamen(id) == null) {
                    throw new NonexistentEntityException("The fechaExamen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamen fechaExamen;
            try {
                fechaExamen = em.getReference(FechaExamen.class, id);
                fechaExamen.getIdFechaexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaExamen with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            EncuestaDocente encuestaDocenteOrphanCheck = fechaExamen.getEncuestaDocente();
            if (encuestaDocenteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FechaExamen (" + fechaExamen + ") cannot be destroyed since the EncuestaDocente " + encuestaDocenteOrphanCheck + " in its encuestaDocente field has a non-nullable fechaExamen field.");
            }
            FechaExamenprogramado fechaExamenprogramadoOrphanCheck = fechaExamen.getFechaExamenprogramado();
            if (fechaExamenprogramadoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FechaExamen (" + fechaExamen + ") cannot be destroyed since the FechaExamenprogramado " + fechaExamenprogramadoOrphanCheck + " in its fechaExamenprogramado field has a non-nullable fechaExamen1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoExamen idTipoexamen = fechaExamen.getIdTipoexamen();
            if (idTipoexamen != null) {
                idTipoexamen.getFechaExamenList().remove(fechaExamen);
                idTipoexamen = em.merge(idTipoexamen);
            }
            CalendarioAsignatura idCalendarioasignatura = fechaExamen.getIdCalendarioasignatura();
            if (idCalendarioasignatura != null) {
                idCalendarioasignatura.getFechaExamenList().remove(fechaExamen);
                idCalendarioasignatura = em.merge(idCalendarioasignatura);
            }
            List<ExamenImpreso> examenImpresoList = fechaExamen.getExamenImpresoList();
            for (ExamenImpreso examenImpresoListExamenImpreso : examenImpresoList) {
                examenImpresoListExamenImpreso.getFechaExamenList().remove(fechaExamen);
                examenImpresoListExamenImpreso = em.merge(examenImpresoListExamenImpreso);
            }
            List<Acta> actaList = fechaExamen.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setIdFechaexamen(null);
                actaListActa = em.merge(actaListActa);
            }
            List<HistoricoFechaexamen> historicoFechaexamenList = fechaExamen.getHistoricoFechaexamenList();
            for (HistoricoFechaexamen historicoFechaexamenListHistoricoFechaexamen : historicoFechaexamenList) {
                historicoFechaexamenListHistoricoFechaexamen.setIdFechaexamen(null);
                historicoFechaexamenListHistoricoFechaexamen = em.merge(historicoFechaexamenListHistoricoFechaexamen);
            }
            em.remove(fechaExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FechaExamen> findFechaExamenEntities() {
        return findFechaExamenEntities(true, -1, -1);
    }

    public List<FechaExamen> findFechaExamenEntities(int maxResults, int firstResult) {
        return findFechaExamenEntities(false, maxResults, firstResult);
    }

    private List<FechaExamen> findFechaExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FechaExamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FechaExamen findFechaExamen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FechaExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaExamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FechaExamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
