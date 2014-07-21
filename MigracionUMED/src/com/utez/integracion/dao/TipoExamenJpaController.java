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
import com.utez.integracion.entity.ExamenExtemporaneo;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.AsignacionEvaluacion;
import com.utez.integracion.entity.Calificacion;
import com.utez.integracion.entity.FechaExamen;
import com.utez.integracion.entity.ExamenImpreso;
import com.utez.integracion.entity.ExamenIndividual;
import com.utez.integracion.entity.HistoricoCalificacion;
import com.utez.integracion.entity.FechaExamenopcionc;
import com.utez.integracion.entity.TipoExamen;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoExamenJpaController implements Serializable {

    public TipoExamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoExamen tipoExamen) {
        if (tipoExamen.getExamenExtemporaneoList() == null) {
            tipoExamen.setExamenExtemporaneoList(new ArrayList<ExamenExtemporaneo>());
        }
        if (tipoExamen.getActaList() == null) {
            tipoExamen.setActaList(new ArrayList<Acta>());
        }
        if (tipoExamen.getAsignacionEvaluacionList() == null) {
            tipoExamen.setAsignacionEvaluacionList(new ArrayList<AsignacionEvaluacion>());
        }
        if (tipoExamen.getCalificacionList() == null) {
            tipoExamen.setCalificacionList(new ArrayList<Calificacion>());
        }
        if (tipoExamen.getFechaExamenList() == null) {
            tipoExamen.setFechaExamenList(new ArrayList<FechaExamen>());
        }
        if (tipoExamen.getExamenImpresoList() == null) {
            tipoExamen.setExamenImpresoList(new ArrayList<ExamenImpreso>());
        }
        if (tipoExamen.getExamenIndividualList() == null) {
            tipoExamen.setExamenIndividualList(new ArrayList<ExamenIndividual>());
        }
        if (tipoExamen.getHistoricoCalificacionList() == null) {
            tipoExamen.setHistoricoCalificacionList(new ArrayList<HistoricoCalificacion>());
        }
        if (tipoExamen.getFechaExamenopcioncList() == null) {
            tipoExamen.setFechaExamenopcioncList(new ArrayList<FechaExamenopcionc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExamenExtemporaneo> attachedExamenExtemporaneoList = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneoToAttach : tipoExamen.getExamenExtemporaneoList()) {
                examenExtemporaneoListExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoList.add(examenExtemporaneoListExamenExtemporaneoToAttach);
            }
            tipoExamen.setExamenExtemporaneoList(attachedExamenExtemporaneoList);
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : tipoExamen.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            tipoExamen.setActaList(attachedActaList);
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionList = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacionToAttach : tipoExamen.getAsignacionEvaluacionList()) {
                asignacionEvaluacionListAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionList.add(asignacionEvaluacionListAsignacionEvaluacionToAttach);
            }
            tipoExamen.setAsignacionEvaluacionList(attachedAsignacionEvaluacionList);
            List<Calificacion> attachedCalificacionList = new ArrayList<Calificacion>();
            for (Calificacion calificacionListCalificacionToAttach : tipoExamen.getCalificacionList()) {
                calificacionListCalificacionToAttach = em.getReference(calificacionListCalificacionToAttach.getClass(), calificacionListCalificacionToAttach.getCalificacionPK());
                attachedCalificacionList.add(calificacionListCalificacionToAttach);
            }
            tipoExamen.setCalificacionList(attachedCalificacionList);
            List<FechaExamen> attachedFechaExamenList = new ArrayList<FechaExamen>();
            for (FechaExamen fechaExamenListFechaExamenToAttach : tipoExamen.getFechaExamenList()) {
                fechaExamenListFechaExamenToAttach = em.getReference(fechaExamenListFechaExamenToAttach.getClass(), fechaExamenListFechaExamenToAttach.getIdFechaexamen());
                attachedFechaExamenList.add(fechaExamenListFechaExamenToAttach);
            }
            tipoExamen.setFechaExamenList(attachedFechaExamenList);
            List<ExamenImpreso> attachedExamenImpresoList = new ArrayList<ExamenImpreso>();
            for (ExamenImpreso examenImpresoListExamenImpresoToAttach : tipoExamen.getExamenImpresoList()) {
                examenImpresoListExamenImpresoToAttach = em.getReference(examenImpresoListExamenImpresoToAttach.getClass(), examenImpresoListExamenImpresoToAttach.getIdExamenimpreso());
                attachedExamenImpresoList.add(examenImpresoListExamenImpresoToAttach);
            }
            tipoExamen.setExamenImpresoList(attachedExamenImpresoList);
            List<ExamenIndividual> attachedExamenIndividualList = new ArrayList<ExamenIndividual>();
            for (ExamenIndividual examenIndividualListExamenIndividualToAttach : tipoExamen.getExamenIndividualList()) {
                examenIndividualListExamenIndividualToAttach = em.getReference(examenIndividualListExamenIndividualToAttach.getClass(), examenIndividualListExamenIndividualToAttach.getIdExamenindividual());
                attachedExamenIndividualList.add(examenIndividualListExamenIndividualToAttach);
            }
            tipoExamen.setExamenIndividualList(attachedExamenIndividualList);
            List<HistoricoCalificacion> attachedHistoricoCalificacionList = new ArrayList<HistoricoCalificacion>();
            for (HistoricoCalificacion historicoCalificacionListHistoricoCalificacionToAttach : tipoExamen.getHistoricoCalificacionList()) {
                historicoCalificacionListHistoricoCalificacionToAttach = em.getReference(historicoCalificacionListHistoricoCalificacionToAttach.getClass(), historicoCalificacionListHistoricoCalificacionToAttach.getIdHistoricocalificacion());
                attachedHistoricoCalificacionList.add(historicoCalificacionListHistoricoCalificacionToAttach);
            }
            tipoExamen.setHistoricoCalificacionList(attachedHistoricoCalificacionList);
            List<FechaExamenopcionc> attachedFechaExamenopcioncList = new ArrayList<FechaExamenopcionc>();
            for (FechaExamenopcionc fechaExamenopcioncListFechaExamenopcioncToAttach : tipoExamen.getFechaExamenopcioncList()) {
                fechaExamenopcioncListFechaExamenopcioncToAttach = em.getReference(fechaExamenopcioncListFechaExamenopcioncToAttach.getClass(), fechaExamenopcioncListFechaExamenopcioncToAttach.getFechaExamenopcioncPK());
                attachedFechaExamenopcioncList.add(fechaExamenopcioncListFechaExamenopcioncToAttach);
            }
            tipoExamen.setFechaExamenopcioncList(attachedFechaExamenopcioncList);
            em.persist(tipoExamen);
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : tipoExamen.getExamenExtemporaneoList()) {
                TipoExamen oldIdTipoexamenOfExamenExtemporaneoListExamenExtemporaneo = examenExtemporaneoListExamenExtemporaneo.getIdTipoexamen();
                examenExtemporaneoListExamenExtemporaneo.setIdTipoexamen(tipoExamen);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
                if (oldIdTipoexamenOfExamenExtemporaneoListExamenExtemporaneo != null) {
                    oldIdTipoexamenOfExamenExtemporaneoListExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListExamenExtemporaneo);
                    oldIdTipoexamenOfExamenExtemporaneoListExamenExtemporaneo = em.merge(oldIdTipoexamenOfExamenExtemporaneoListExamenExtemporaneo);
                }
            }
            for (Acta actaListActa : tipoExamen.getActaList()) {
                TipoExamen oldIdTipoexamenOfActaListActa = actaListActa.getIdTipoexamen();
                actaListActa.setIdTipoexamen(tipoExamen);
                actaListActa = em.merge(actaListActa);
                if (oldIdTipoexamenOfActaListActa != null) {
                    oldIdTipoexamenOfActaListActa.getActaList().remove(actaListActa);
                    oldIdTipoexamenOfActaListActa = em.merge(oldIdTipoexamenOfActaListActa);
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : tipoExamen.getAsignacionEvaluacionList()) {
                TipoExamen oldIdTipoexamenOfAsignacionEvaluacionListAsignacionEvaluacion = asignacionEvaluacionListAsignacionEvaluacion.getIdTipoexamen();
                asignacionEvaluacionListAsignacionEvaluacion.setIdTipoexamen(tipoExamen);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
                if (oldIdTipoexamenOfAsignacionEvaluacionListAsignacionEvaluacion != null) {
                    oldIdTipoexamenOfAsignacionEvaluacionListAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListAsignacionEvaluacion);
                    oldIdTipoexamenOfAsignacionEvaluacionListAsignacionEvaluacion = em.merge(oldIdTipoexamenOfAsignacionEvaluacionListAsignacionEvaluacion);
                }
            }
            for (Calificacion calificacionListCalificacion : tipoExamen.getCalificacionList()) {
                TipoExamen oldTipoExamenOfCalificacionListCalificacion = calificacionListCalificacion.getTipoExamen();
                calificacionListCalificacion.setTipoExamen(tipoExamen);
                calificacionListCalificacion = em.merge(calificacionListCalificacion);
                if (oldTipoExamenOfCalificacionListCalificacion != null) {
                    oldTipoExamenOfCalificacionListCalificacion.getCalificacionList().remove(calificacionListCalificacion);
                    oldTipoExamenOfCalificacionListCalificacion = em.merge(oldTipoExamenOfCalificacionListCalificacion);
                }
            }
            for (FechaExamen fechaExamenListFechaExamen : tipoExamen.getFechaExamenList()) {
                TipoExamen oldIdTipoexamenOfFechaExamenListFechaExamen = fechaExamenListFechaExamen.getIdTipoexamen();
                fechaExamenListFechaExamen.setIdTipoexamen(tipoExamen);
                fechaExamenListFechaExamen = em.merge(fechaExamenListFechaExamen);
                if (oldIdTipoexamenOfFechaExamenListFechaExamen != null) {
                    oldIdTipoexamenOfFechaExamenListFechaExamen.getFechaExamenList().remove(fechaExamenListFechaExamen);
                    oldIdTipoexamenOfFechaExamenListFechaExamen = em.merge(oldIdTipoexamenOfFechaExamenListFechaExamen);
                }
            }
            for (ExamenImpreso examenImpresoListExamenImpreso : tipoExamen.getExamenImpresoList()) {
                TipoExamen oldIdTipoexamenOfExamenImpresoListExamenImpreso = examenImpresoListExamenImpreso.getIdTipoexamen();
                examenImpresoListExamenImpreso.setIdTipoexamen(tipoExamen);
                examenImpresoListExamenImpreso = em.merge(examenImpresoListExamenImpreso);
                if (oldIdTipoexamenOfExamenImpresoListExamenImpreso != null) {
                    oldIdTipoexamenOfExamenImpresoListExamenImpreso.getExamenImpresoList().remove(examenImpresoListExamenImpreso);
                    oldIdTipoexamenOfExamenImpresoListExamenImpreso = em.merge(oldIdTipoexamenOfExamenImpresoListExamenImpreso);
                }
            }
            for (ExamenIndividual examenIndividualListExamenIndividual : tipoExamen.getExamenIndividualList()) {
                TipoExamen oldIdTipoexamenOfExamenIndividualListExamenIndividual = examenIndividualListExamenIndividual.getIdTipoexamen();
                examenIndividualListExamenIndividual.setIdTipoexamen(tipoExamen);
                examenIndividualListExamenIndividual = em.merge(examenIndividualListExamenIndividual);
                if (oldIdTipoexamenOfExamenIndividualListExamenIndividual != null) {
                    oldIdTipoexamenOfExamenIndividualListExamenIndividual.getExamenIndividualList().remove(examenIndividualListExamenIndividual);
                    oldIdTipoexamenOfExamenIndividualListExamenIndividual = em.merge(oldIdTipoexamenOfExamenIndividualListExamenIndividual);
                }
            }
            for (HistoricoCalificacion historicoCalificacionListHistoricoCalificacion : tipoExamen.getHistoricoCalificacionList()) {
                TipoExamen oldIdTipoexamenOfHistoricoCalificacionListHistoricoCalificacion = historicoCalificacionListHistoricoCalificacion.getIdTipoexamen();
                historicoCalificacionListHistoricoCalificacion.setIdTipoexamen(tipoExamen);
                historicoCalificacionListHistoricoCalificacion = em.merge(historicoCalificacionListHistoricoCalificacion);
                if (oldIdTipoexamenOfHistoricoCalificacionListHistoricoCalificacion != null) {
                    oldIdTipoexamenOfHistoricoCalificacionListHistoricoCalificacion.getHistoricoCalificacionList().remove(historicoCalificacionListHistoricoCalificacion);
                    oldIdTipoexamenOfHistoricoCalificacionListHistoricoCalificacion = em.merge(oldIdTipoexamenOfHistoricoCalificacionListHistoricoCalificacion);
                }
            }
            for (FechaExamenopcionc fechaExamenopcioncListFechaExamenopcionc : tipoExamen.getFechaExamenopcioncList()) {
                TipoExamen oldTipoExamenOfFechaExamenopcioncListFechaExamenopcionc = fechaExamenopcioncListFechaExamenopcionc.getTipoExamen();
                fechaExamenopcioncListFechaExamenopcionc.setTipoExamen(tipoExamen);
                fechaExamenopcioncListFechaExamenopcionc = em.merge(fechaExamenopcioncListFechaExamenopcionc);
                if (oldTipoExamenOfFechaExamenopcioncListFechaExamenopcionc != null) {
                    oldTipoExamenOfFechaExamenopcioncListFechaExamenopcionc.getFechaExamenopcioncList().remove(fechaExamenopcioncListFechaExamenopcionc);
                    oldTipoExamenOfFechaExamenopcioncListFechaExamenopcionc = em.merge(oldTipoExamenOfFechaExamenopcioncListFechaExamenopcionc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoExamen tipoExamen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoExamen persistentTipoExamen = em.find(TipoExamen.class, tipoExamen.getIdTipoexamen());
            List<ExamenExtemporaneo> examenExtemporaneoListOld = persistentTipoExamen.getExamenExtemporaneoList();
            List<ExamenExtemporaneo> examenExtemporaneoListNew = tipoExamen.getExamenExtemporaneoList();
            List<Acta> actaListOld = persistentTipoExamen.getActaList();
            List<Acta> actaListNew = tipoExamen.getActaList();
            List<AsignacionEvaluacion> asignacionEvaluacionListOld = persistentTipoExamen.getAsignacionEvaluacionList();
            List<AsignacionEvaluacion> asignacionEvaluacionListNew = tipoExamen.getAsignacionEvaluacionList();
            List<Calificacion> calificacionListOld = persistentTipoExamen.getCalificacionList();
            List<Calificacion> calificacionListNew = tipoExamen.getCalificacionList();
            List<FechaExamen> fechaExamenListOld = persistentTipoExamen.getFechaExamenList();
            List<FechaExamen> fechaExamenListNew = tipoExamen.getFechaExamenList();
            List<ExamenImpreso> examenImpresoListOld = persistentTipoExamen.getExamenImpresoList();
            List<ExamenImpreso> examenImpresoListNew = tipoExamen.getExamenImpresoList();
            List<ExamenIndividual> examenIndividualListOld = persistentTipoExamen.getExamenIndividualList();
            List<ExamenIndividual> examenIndividualListNew = tipoExamen.getExamenIndividualList();
            List<HistoricoCalificacion> historicoCalificacionListOld = persistentTipoExamen.getHistoricoCalificacionList();
            List<HistoricoCalificacion> historicoCalificacionListNew = tipoExamen.getHistoricoCalificacionList();
            List<FechaExamenopcionc> fechaExamenopcioncListOld = persistentTipoExamen.getFechaExamenopcioncList();
            List<FechaExamenopcionc> fechaExamenopcioncListNew = tipoExamen.getFechaExamenopcioncList();
            List<String> illegalOrphanMessages = null;
            for (Calificacion calificacionListOldCalificacion : calificacionListOld) {
                if (!calificacionListNew.contains(calificacionListOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionListOldCalificacion + " since its tipoExamen field is not nullable.");
                }
            }
            for (FechaExamenopcionc fechaExamenopcioncListOldFechaExamenopcionc : fechaExamenopcioncListOld) {
                if (!fechaExamenopcioncListNew.contains(fechaExamenopcioncListOldFechaExamenopcionc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FechaExamenopcionc " + fechaExamenopcioncListOldFechaExamenopcionc + " since its tipoExamen field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ExamenExtemporaneo> attachedExamenExtemporaneoListNew = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneoToAttach : examenExtemporaneoListNew) {
                examenExtemporaneoListNewExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListNewExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListNewExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoListNew.add(examenExtemporaneoListNewExamenExtemporaneoToAttach);
            }
            examenExtemporaneoListNew = attachedExamenExtemporaneoListNew;
            tipoExamen.setExamenExtemporaneoList(examenExtemporaneoListNew);
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            tipoExamen.setActaList(actaListNew);
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionListNew = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacionToAttach : asignacionEvaluacionListNew) {
                asignacionEvaluacionListNewAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionListNew.add(asignacionEvaluacionListNewAsignacionEvaluacionToAttach);
            }
            asignacionEvaluacionListNew = attachedAsignacionEvaluacionListNew;
            tipoExamen.setAsignacionEvaluacionList(asignacionEvaluacionListNew);
            List<Calificacion> attachedCalificacionListNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionListNewCalificacionToAttach : calificacionListNew) {
                calificacionListNewCalificacionToAttach = em.getReference(calificacionListNewCalificacionToAttach.getClass(), calificacionListNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionListNew.add(calificacionListNewCalificacionToAttach);
            }
            calificacionListNew = attachedCalificacionListNew;
            tipoExamen.setCalificacionList(calificacionListNew);
            List<FechaExamen> attachedFechaExamenListNew = new ArrayList<FechaExamen>();
            for (FechaExamen fechaExamenListNewFechaExamenToAttach : fechaExamenListNew) {
                fechaExamenListNewFechaExamenToAttach = em.getReference(fechaExamenListNewFechaExamenToAttach.getClass(), fechaExamenListNewFechaExamenToAttach.getIdFechaexamen());
                attachedFechaExamenListNew.add(fechaExamenListNewFechaExamenToAttach);
            }
            fechaExamenListNew = attachedFechaExamenListNew;
            tipoExamen.setFechaExamenList(fechaExamenListNew);
            List<ExamenImpreso> attachedExamenImpresoListNew = new ArrayList<ExamenImpreso>();
            for (ExamenImpreso examenImpresoListNewExamenImpresoToAttach : examenImpresoListNew) {
                examenImpresoListNewExamenImpresoToAttach = em.getReference(examenImpresoListNewExamenImpresoToAttach.getClass(), examenImpresoListNewExamenImpresoToAttach.getIdExamenimpreso());
                attachedExamenImpresoListNew.add(examenImpresoListNewExamenImpresoToAttach);
            }
            examenImpresoListNew = attachedExamenImpresoListNew;
            tipoExamen.setExamenImpresoList(examenImpresoListNew);
            List<ExamenIndividual> attachedExamenIndividualListNew = new ArrayList<ExamenIndividual>();
            for (ExamenIndividual examenIndividualListNewExamenIndividualToAttach : examenIndividualListNew) {
                examenIndividualListNewExamenIndividualToAttach = em.getReference(examenIndividualListNewExamenIndividualToAttach.getClass(), examenIndividualListNewExamenIndividualToAttach.getIdExamenindividual());
                attachedExamenIndividualListNew.add(examenIndividualListNewExamenIndividualToAttach);
            }
            examenIndividualListNew = attachedExamenIndividualListNew;
            tipoExamen.setExamenIndividualList(examenIndividualListNew);
            List<HistoricoCalificacion> attachedHistoricoCalificacionListNew = new ArrayList<HistoricoCalificacion>();
            for (HistoricoCalificacion historicoCalificacionListNewHistoricoCalificacionToAttach : historicoCalificacionListNew) {
                historicoCalificacionListNewHistoricoCalificacionToAttach = em.getReference(historicoCalificacionListNewHistoricoCalificacionToAttach.getClass(), historicoCalificacionListNewHistoricoCalificacionToAttach.getIdHistoricocalificacion());
                attachedHistoricoCalificacionListNew.add(historicoCalificacionListNewHistoricoCalificacionToAttach);
            }
            historicoCalificacionListNew = attachedHistoricoCalificacionListNew;
            tipoExamen.setHistoricoCalificacionList(historicoCalificacionListNew);
            List<FechaExamenopcionc> attachedFechaExamenopcioncListNew = new ArrayList<FechaExamenopcionc>();
            for (FechaExamenopcionc fechaExamenopcioncListNewFechaExamenopcioncToAttach : fechaExamenopcioncListNew) {
                fechaExamenopcioncListNewFechaExamenopcioncToAttach = em.getReference(fechaExamenopcioncListNewFechaExamenopcioncToAttach.getClass(), fechaExamenopcioncListNewFechaExamenopcioncToAttach.getFechaExamenopcioncPK());
                attachedFechaExamenopcioncListNew.add(fechaExamenopcioncListNewFechaExamenopcioncToAttach);
            }
            fechaExamenopcioncListNew = attachedFechaExamenopcioncListNew;
            tipoExamen.setFechaExamenopcioncList(fechaExamenopcioncListNew);
            tipoExamen = em.merge(tipoExamen);
            for (ExamenExtemporaneo examenExtemporaneoListOldExamenExtemporaneo : examenExtemporaneoListOld) {
                if (!examenExtemporaneoListNew.contains(examenExtemporaneoListOldExamenExtemporaneo)) {
                    examenExtemporaneoListOldExamenExtemporaneo.setIdTipoexamen(null);
                    examenExtemporaneoListOldExamenExtemporaneo = em.merge(examenExtemporaneoListOldExamenExtemporaneo);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneo : examenExtemporaneoListNew) {
                if (!examenExtemporaneoListOld.contains(examenExtemporaneoListNewExamenExtemporaneo)) {
                    TipoExamen oldIdTipoexamenOfExamenExtemporaneoListNewExamenExtemporaneo = examenExtemporaneoListNewExamenExtemporaneo.getIdTipoexamen();
                    examenExtemporaneoListNewExamenExtemporaneo.setIdTipoexamen(tipoExamen);
                    examenExtemporaneoListNewExamenExtemporaneo = em.merge(examenExtemporaneoListNewExamenExtemporaneo);
                    if (oldIdTipoexamenOfExamenExtemporaneoListNewExamenExtemporaneo != null && !oldIdTipoexamenOfExamenExtemporaneoListNewExamenExtemporaneo.equals(tipoExamen)) {
                        oldIdTipoexamenOfExamenExtemporaneoListNewExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListNewExamenExtemporaneo);
                        oldIdTipoexamenOfExamenExtemporaneoListNewExamenExtemporaneo = em.merge(oldIdTipoexamenOfExamenExtemporaneoListNewExamenExtemporaneo);
                    }
                }
            }
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setIdTipoexamen(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    TipoExamen oldIdTipoexamenOfActaListNewActa = actaListNewActa.getIdTipoexamen();
                    actaListNewActa.setIdTipoexamen(tipoExamen);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldIdTipoexamenOfActaListNewActa != null && !oldIdTipoexamenOfActaListNewActa.equals(tipoExamen)) {
                        oldIdTipoexamenOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldIdTipoexamenOfActaListNewActa = em.merge(oldIdTipoexamenOfActaListNewActa);
                    }
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListOldAsignacionEvaluacion : asignacionEvaluacionListOld) {
                if (!asignacionEvaluacionListNew.contains(asignacionEvaluacionListOldAsignacionEvaluacion)) {
                    asignacionEvaluacionListOldAsignacionEvaluacion.setIdTipoexamen(null);
                    asignacionEvaluacionListOldAsignacionEvaluacion = em.merge(asignacionEvaluacionListOldAsignacionEvaluacion);
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacion : asignacionEvaluacionListNew) {
                if (!asignacionEvaluacionListOld.contains(asignacionEvaluacionListNewAsignacionEvaluacion)) {
                    TipoExamen oldIdTipoexamenOfAsignacionEvaluacionListNewAsignacionEvaluacion = asignacionEvaluacionListNewAsignacionEvaluacion.getIdTipoexamen();
                    asignacionEvaluacionListNewAsignacionEvaluacion.setIdTipoexamen(tipoExamen);
                    asignacionEvaluacionListNewAsignacionEvaluacion = em.merge(asignacionEvaluacionListNewAsignacionEvaluacion);
                    if (oldIdTipoexamenOfAsignacionEvaluacionListNewAsignacionEvaluacion != null && !oldIdTipoexamenOfAsignacionEvaluacionListNewAsignacionEvaluacion.equals(tipoExamen)) {
                        oldIdTipoexamenOfAsignacionEvaluacionListNewAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListNewAsignacionEvaluacion);
                        oldIdTipoexamenOfAsignacionEvaluacionListNewAsignacionEvaluacion = em.merge(oldIdTipoexamenOfAsignacionEvaluacionListNewAsignacionEvaluacion);
                    }
                }
            }
            for (Calificacion calificacionListNewCalificacion : calificacionListNew) {
                if (!calificacionListOld.contains(calificacionListNewCalificacion)) {
                    TipoExamen oldTipoExamenOfCalificacionListNewCalificacion = calificacionListNewCalificacion.getTipoExamen();
                    calificacionListNewCalificacion.setTipoExamen(tipoExamen);
                    calificacionListNewCalificacion = em.merge(calificacionListNewCalificacion);
                    if (oldTipoExamenOfCalificacionListNewCalificacion != null && !oldTipoExamenOfCalificacionListNewCalificacion.equals(tipoExamen)) {
                        oldTipoExamenOfCalificacionListNewCalificacion.getCalificacionList().remove(calificacionListNewCalificacion);
                        oldTipoExamenOfCalificacionListNewCalificacion = em.merge(oldTipoExamenOfCalificacionListNewCalificacion);
                    }
                }
            }
            for (FechaExamen fechaExamenListOldFechaExamen : fechaExamenListOld) {
                if (!fechaExamenListNew.contains(fechaExamenListOldFechaExamen)) {
                    fechaExamenListOldFechaExamen.setIdTipoexamen(null);
                    fechaExamenListOldFechaExamen = em.merge(fechaExamenListOldFechaExamen);
                }
            }
            for (FechaExamen fechaExamenListNewFechaExamen : fechaExamenListNew) {
                if (!fechaExamenListOld.contains(fechaExamenListNewFechaExamen)) {
                    TipoExamen oldIdTipoexamenOfFechaExamenListNewFechaExamen = fechaExamenListNewFechaExamen.getIdTipoexamen();
                    fechaExamenListNewFechaExamen.setIdTipoexamen(tipoExamen);
                    fechaExamenListNewFechaExamen = em.merge(fechaExamenListNewFechaExamen);
                    if (oldIdTipoexamenOfFechaExamenListNewFechaExamen != null && !oldIdTipoexamenOfFechaExamenListNewFechaExamen.equals(tipoExamen)) {
                        oldIdTipoexamenOfFechaExamenListNewFechaExamen.getFechaExamenList().remove(fechaExamenListNewFechaExamen);
                        oldIdTipoexamenOfFechaExamenListNewFechaExamen = em.merge(oldIdTipoexamenOfFechaExamenListNewFechaExamen);
                    }
                }
            }
            for (ExamenImpreso examenImpresoListOldExamenImpreso : examenImpresoListOld) {
                if (!examenImpresoListNew.contains(examenImpresoListOldExamenImpreso)) {
                    examenImpresoListOldExamenImpreso.setIdTipoexamen(null);
                    examenImpresoListOldExamenImpreso = em.merge(examenImpresoListOldExamenImpreso);
                }
            }
            for (ExamenImpreso examenImpresoListNewExamenImpreso : examenImpresoListNew) {
                if (!examenImpresoListOld.contains(examenImpresoListNewExamenImpreso)) {
                    TipoExamen oldIdTipoexamenOfExamenImpresoListNewExamenImpreso = examenImpresoListNewExamenImpreso.getIdTipoexamen();
                    examenImpresoListNewExamenImpreso.setIdTipoexamen(tipoExamen);
                    examenImpresoListNewExamenImpreso = em.merge(examenImpresoListNewExamenImpreso);
                    if (oldIdTipoexamenOfExamenImpresoListNewExamenImpreso != null && !oldIdTipoexamenOfExamenImpresoListNewExamenImpreso.equals(tipoExamen)) {
                        oldIdTipoexamenOfExamenImpresoListNewExamenImpreso.getExamenImpresoList().remove(examenImpresoListNewExamenImpreso);
                        oldIdTipoexamenOfExamenImpresoListNewExamenImpreso = em.merge(oldIdTipoexamenOfExamenImpresoListNewExamenImpreso);
                    }
                }
            }
            for (ExamenIndividual examenIndividualListOldExamenIndividual : examenIndividualListOld) {
                if (!examenIndividualListNew.contains(examenIndividualListOldExamenIndividual)) {
                    examenIndividualListOldExamenIndividual.setIdTipoexamen(null);
                    examenIndividualListOldExamenIndividual = em.merge(examenIndividualListOldExamenIndividual);
                }
            }
            for (ExamenIndividual examenIndividualListNewExamenIndividual : examenIndividualListNew) {
                if (!examenIndividualListOld.contains(examenIndividualListNewExamenIndividual)) {
                    TipoExamen oldIdTipoexamenOfExamenIndividualListNewExamenIndividual = examenIndividualListNewExamenIndividual.getIdTipoexamen();
                    examenIndividualListNewExamenIndividual.setIdTipoexamen(tipoExamen);
                    examenIndividualListNewExamenIndividual = em.merge(examenIndividualListNewExamenIndividual);
                    if (oldIdTipoexamenOfExamenIndividualListNewExamenIndividual != null && !oldIdTipoexamenOfExamenIndividualListNewExamenIndividual.equals(tipoExamen)) {
                        oldIdTipoexamenOfExamenIndividualListNewExamenIndividual.getExamenIndividualList().remove(examenIndividualListNewExamenIndividual);
                        oldIdTipoexamenOfExamenIndividualListNewExamenIndividual = em.merge(oldIdTipoexamenOfExamenIndividualListNewExamenIndividual);
                    }
                }
            }
            for (HistoricoCalificacion historicoCalificacionListOldHistoricoCalificacion : historicoCalificacionListOld) {
                if (!historicoCalificacionListNew.contains(historicoCalificacionListOldHistoricoCalificacion)) {
                    historicoCalificacionListOldHistoricoCalificacion.setIdTipoexamen(null);
                    historicoCalificacionListOldHistoricoCalificacion = em.merge(historicoCalificacionListOldHistoricoCalificacion);
                }
            }
            for (HistoricoCalificacion historicoCalificacionListNewHistoricoCalificacion : historicoCalificacionListNew) {
                if (!historicoCalificacionListOld.contains(historicoCalificacionListNewHistoricoCalificacion)) {
                    TipoExamen oldIdTipoexamenOfHistoricoCalificacionListNewHistoricoCalificacion = historicoCalificacionListNewHistoricoCalificacion.getIdTipoexamen();
                    historicoCalificacionListNewHistoricoCalificacion.setIdTipoexamen(tipoExamen);
                    historicoCalificacionListNewHistoricoCalificacion = em.merge(historicoCalificacionListNewHistoricoCalificacion);
                    if (oldIdTipoexamenOfHistoricoCalificacionListNewHistoricoCalificacion != null && !oldIdTipoexamenOfHistoricoCalificacionListNewHistoricoCalificacion.equals(tipoExamen)) {
                        oldIdTipoexamenOfHistoricoCalificacionListNewHistoricoCalificacion.getHistoricoCalificacionList().remove(historicoCalificacionListNewHistoricoCalificacion);
                        oldIdTipoexamenOfHistoricoCalificacionListNewHistoricoCalificacion = em.merge(oldIdTipoexamenOfHistoricoCalificacionListNewHistoricoCalificacion);
                    }
                }
            }
            for (FechaExamenopcionc fechaExamenopcioncListNewFechaExamenopcionc : fechaExamenopcioncListNew) {
                if (!fechaExamenopcioncListOld.contains(fechaExamenopcioncListNewFechaExamenopcionc)) {
                    TipoExamen oldTipoExamenOfFechaExamenopcioncListNewFechaExamenopcionc = fechaExamenopcioncListNewFechaExamenopcionc.getTipoExamen();
                    fechaExamenopcioncListNewFechaExamenopcionc.setTipoExamen(tipoExamen);
                    fechaExamenopcioncListNewFechaExamenopcionc = em.merge(fechaExamenopcioncListNewFechaExamenopcionc);
                    if (oldTipoExamenOfFechaExamenopcioncListNewFechaExamenopcionc != null && !oldTipoExamenOfFechaExamenopcioncListNewFechaExamenopcionc.equals(tipoExamen)) {
                        oldTipoExamenOfFechaExamenopcioncListNewFechaExamenopcionc.getFechaExamenopcioncList().remove(fechaExamenopcioncListNewFechaExamenopcionc);
                        oldTipoExamenOfFechaExamenopcioncListNewFechaExamenopcionc = em.merge(oldTipoExamenOfFechaExamenopcioncListNewFechaExamenopcionc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoExamen.getIdTipoexamen();
                if (findTipoExamen(id) == null) {
                    throw new NonexistentEntityException("The tipoExamen with id " + id + " no longer exists.");
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
            TipoExamen tipoExamen;
            try {
                tipoExamen = em.getReference(TipoExamen.class, id);
                tipoExamen.getIdTipoexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoExamen with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Calificacion> calificacionListOrphanCheck = tipoExamen.getCalificacionList();
            for (Calificacion calificacionListOrphanCheckCalificacion : calificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoExamen (" + tipoExamen + ") cannot be destroyed since the Calificacion " + calificacionListOrphanCheckCalificacion + " in its calificacionList field has a non-nullable tipoExamen field.");
            }
            List<FechaExamenopcionc> fechaExamenopcioncListOrphanCheck = tipoExamen.getFechaExamenopcioncList();
            for (FechaExamenopcionc fechaExamenopcioncListOrphanCheckFechaExamenopcionc : fechaExamenopcioncListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoExamen (" + tipoExamen + ") cannot be destroyed since the FechaExamenopcionc " + fechaExamenopcioncListOrphanCheckFechaExamenopcionc + " in its fechaExamenopcioncList field has a non-nullable tipoExamen field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ExamenExtemporaneo> examenExtemporaneoList = tipoExamen.getExamenExtemporaneoList();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : examenExtemporaneoList) {
                examenExtemporaneoListExamenExtemporaneo.setIdTipoexamen(null);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
            }
            List<Acta> actaList = tipoExamen.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setIdTipoexamen(null);
                actaListActa = em.merge(actaListActa);
            }
            List<AsignacionEvaluacion> asignacionEvaluacionList = tipoExamen.getAsignacionEvaluacionList();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : asignacionEvaluacionList) {
                asignacionEvaluacionListAsignacionEvaluacion.setIdTipoexamen(null);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
            }
            List<FechaExamen> fechaExamenList = tipoExamen.getFechaExamenList();
            for (FechaExamen fechaExamenListFechaExamen : fechaExamenList) {
                fechaExamenListFechaExamen.setIdTipoexamen(null);
                fechaExamenListFechaExamen = em.merge(fechaExamenListFechaExamen);
            }
            List<ExamenImpreso> examenImpresoList = tipoExamen.getExamenImpresoList();
            for (ExamenImpreso examenImpresoListExamenImpreso : examenImpresoList) {
                examenImpresoListExamenImpreso.setIdTipoexamen(null);
                examenImpresoListExamenImpreso = em.merge(examenImpresoListExamenImpreso);
            }
            List<ExamenIndividual> examenIndividualList = tipoExamen.getExamenIndividualList();
            for (ExamenIndividual examenIndividualListExamenIndividual : examenIndividualList) {
                examenIndividualListExamenIndividual.setIdTipoexamen(null);
                examenIndividualListExamenIndividual = em.merge(examenIndividualListExamenIndividual);
            }
            List<HistoricoCalificacion> historicoCalificacionList = tipoExamen.getHistoricoCalificacionList();
            for (HistoricoCalificacion historicoCalificacionListHistoricoCalificacion : historicoCalificacionList) {
                historicoCalificacionListHistoricoCalificacion.setIdTipoexamen(null);
                historicoCalificacionListHistoricoCalificacion = em.merge(historicoCalificacionListHistoricoCalificacion);
            }
            em.remove(tipoExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoExamen> findTipoExamenEntities() {
        return findTipoExamenEntities(true, -1, -1);
    }

    public List<TipoExamen> findTipoExamenEntities(int maxResults, int firstResult) {
        return findTipoExamenEntities(false, maxResults, firstResult);
    }

    private List<TipoExamen> findTipoExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoExamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoExamen findTipoExamen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoExamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoExamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
