/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Asentamiento;
import com.utez.secretariaAcademica.entity.Mesopcionc;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Recursohumano;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Calendariorectoria;
import com.utez.secretariaAcademica.entity.Calendario;
import com.utez.secretariaAcademica.entity.Planestudios;
import com.utez.integracion.entity.SuspensionLabores;
import com.utez.secretariaAcademica.entity.Administrativo;
import com.utez.integracion.entity.Vacacion;
import com.utez.integracion.entity.Evento;
import com.utez.integracion.entity.AsignacionRecursoplantel;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Plantel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PlantelJpaController implements Serializable {

    public PlantelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plantel plantel) {
        if (plantel.getMesopcioncList() == null) {
            plantel.setMesopcioncList(new ArrayList<Mesopcionc>());
        }
        if (plantel.getRecursohumanoList() == null) {
            plantel.setRecursohumanoList(new ArrayList<Recursohumano>());
        }
        if (plantel.getGrupoList() == null) {
            plantel.setGrupoList(new ArrayList<Grupo>());
        }
        if (plantel.getCalendariorectoriaList() == null) {
            plantel.setCalendariorectoriaList(new ArrayList<Calendariorectoria>());
        }
        if (plantel.getCalendarioList() == null) {
            plantel.setCalendarioList(new ArrayList<Calendario>());
        }
        if (plantel.getPlanestudiosList() == null) {
            plantel.setPlanestudiosList(new ArrayList<Planestudios>());
        }
        if (plantel.getSuspensionLaboresList() == null) {
            plantel.setSuspensionLaboresList(new ArrayList<SuspensionLabores>());
        }
        if (plantel.getAdministrativoList() == null) {
            plantel.setAdministrativoList(new ArrayList<Administrativo>());
        }
        if (plantel.getVacacionList() == null) {
            plantel.setVacacionList(new ArrayList<Vacacion>());
        }
        if (plantel.getEventoList() == null) {
            plantel.setEventoList(new ArrayList<Evento>());
        }
        if (plantel.getAsignacionRecursoplantelList() == null) {
            plantel.setAsignacionRecursoplantelList(new ArrayList<AsignacionRecursoplantel>());
        }
        if (plantel.getPlanEstudioList() == null) {
            plantel.setPlanEstudioList(new ArrayList<PlanEstudio>());
        }
        if (plantel.getGrupoInduccionList() == null) {
            plantel.setGrupoInduccionList(new ArrayList<GrupoInduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asentamiento idAsentamiento = plantel.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento = em.getReference(idAsentamiento.getClass(), idAsentamiento.getIdAsentamiento());
                plantel.setIdAsentamiento(idAsentamiento);
            }
            List<Mesopcionc> attachedMesopcioncList = new ArrayList<Mesopcionc>();
            for (Mesopcionc mesopcioncListMesopcioncToAttach : plantel.getMesopcioncList()) {
                mesopcioncListMesopcioncToAttach = em.getReference(mesopcioncListMesopcioncToAttach.getClass(), mesopcioncListMesopcioncToAttach.getIdmesopcionc());
                attachedMesopcioncList.add(mesopcioncListMesopcioncToAttach);
            }
            plantel.setMesopcioncList(attachedMesopcioncList);
            List<Recursohumano> attachedRecursohumanoList = new ArrayList<Recursohumano>();
            for (Recursohumano recursohumanoListRecursohumanoToAttach : plantel.getRecursohumanoList()) {
                recursohumanoListRecursohumanoToAttach = em.getReference(recursohumanoListRecursohumanoToAttach.getClass(), recursohumanoListRecursohumanoToAttach.getIdrecursohumano());
                attachedRecursohumanoList.add(recursohumanoListRecursohumanoToAttach);
            }
            plantel.setRecursohumanoList(attachedRecursohumanoList);
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : plantel.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            plantel.setGrupoList(attachedGrupoList);
            List<Calendariorectoria> attachedCalendariorectoriaList = new ArrayList<Calendariorectoria>();
            for (Calendariorectoria calendariorectoriaListCalendariorectoriaToAttach : plantel.getCalendariorectoriaList()) {
                calendariorectoriaListCalendariorectoriaToAttach = em.getReference(calendariorectoriaListCalendariorectoriaToAttach.getClass(), calendariorectoriaListCalendariorectoriaToAttach.getIdcalendariorectoria());
                attachedCalendariorectoriaList.add(calendariorectoriaListCalendariorectoriaToAttach);
            }
            plantel.setCalendariorectoriaList(attachedCalendariorectoriaList);
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : plantel.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdcalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            plantel.setCalendarioList(attachedCalendarioList);
            List<Planestudios> attachedPlanestudiosList = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListPlanestudiosToAttach : plantel.getPlanestudiosList()) {
                planestudiosListPlanestudiosToAttach = em.getReference(planestudiosListPlanestudiosToAttach.getClass(), planestudiosListPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosList.add(planestudiosListPlanestudiosToAttach);
            }
            plantel.setPlanestudiosList(attachedPlanestudiosList);
            List<SuspensionLabores> attachedSuspensionLaboresList = new ArrayList<SuspensionLabores>();
            for (SuspensionLabores suspensionLaboresListSuspensionLaboresToAttach : plantel.getSuspensionLaboresList()) {
                suspensionLaboresListSuspensionLaboresToAttach = em.getReference(suspensionLaboresListSuspensionLaboresToAttach.getClass(), suspensionLaboresListSuspensionLaboresToAttach.getIdSuspensionlabores());
                attachedSuspensionLaboresList.add(suspensionLaboresListSuspensionLaboresToAttach);
            }
            plantel.setSuspensionLaboresList(attachedSuspensionLaboresList);
            List<Administrativo> attachedAdministrativoList = new ArrayList<Administrativo>();
            for (Administrativo administrativoListAdministrativoToAttach : plantel.getAdministrativoList()) {
                administrativoListAdministrativoToAttach = em.getReference(administrativoListAdministrativoToAttach.getClass(), administrativoListAdministrativoToAttach.getIdadministrativo());
                attachedAdministrativoList.add(administrativoListAdministrativoToAttach);
            }
            plantel.setAdministrativoList(attachedAdministrativoList);
            List<Vacacion> attachedVacacionList = new ArrayList<Vacacion>();
            for (Vacacion vacacionListVacacionToAttach : plantel.getVacacionList()) {
                vacacionListVacacionToAttach = em.getReference(vacacionListVacacionToAttach.getClass(), vacacionListVacacionToAttach.getIdVacacion());
                attachedVacacionList.add(vacacionListVacacionToAttach);
            }
            plantel.setVacacionList(attachedVacacionList);
            List<Evento> attachedEventoList = new ArrayList<Evento>();
            for (Evento eventoListEventoToAttach : plantel.getEventoList()) {
                eventoListEventoToAttach = em.getReference(eventoListEventoToAttach.getClass(), eventoListEventoToAttach.getIdEvento());
                attachedEventoList.add(eventoListEventoToAttach);
            }
            plantel.setEventoList(attachedEventoList);
            List<AsignacionRecursoplantel> attachedAsignacionRecursoplantelList = new ArrayList<AsignacionRecursoplantel>();
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantelToAttach : plantel.getAsignacionRecursoplantelList()) {
                asignacionRecursoplantelListAsignacionRecursoplantelToAttach = em.getReference(asignacionRecursoplantelListAsignacionRecursoplantelToAttach.getClass(), asignacionRecursoplantelListAsignacionRecursoplantelToAttach.getIdAsignacionrecursoplantel());
                attachedAsignacionRecursoplantelList.add(asignacionRecursoplantelListAsignacionRecursoplantelToAttach);
            }
            plantel.setAsignacionRecursoplantelList(attachedAsignacionRecursoplantelList);
            List<PlanEstudio> attachedPlanEstudioList = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListPlanEstudioToAttach : plantel.getPlanEstudioList()) {
                planEstudioListPlanEstudioToAttach = em.getReference(planEstudioListPlanEstudioToAttach.getClass(), planEstudioListPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioList.add(planEstudioListPlanEstudioToAttach);
            }
            plantel.setPlanEstudioList(attachedPlanEstudioList);
            List<GrupoInduccion> attachedGrupoInduccionList = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListGrupoInduccionToAttach : plantel.getGrupoInduccionList()) {
                grupoInduccionListGrupoInduccionToAttach = em.getReference(grupoInduccionListGrupoInduccionToAttach.getClass(), grupoInduccionListGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionList.add(grupoInduccionListGrupoInduccionToAttach);
            }
            plantel.setGrupoInduccionList(attachedGrupoInduccionList);
            em.persist(plantel);
            if (idAsentamiento != null) {
                idAsentamiento.getPlantelList().add(plantel);
                idAsentamiento = em.merge(idAsentamiento);
            }
            for (Mesopcionc mesopcioncListMesopcionc : plantel.getMesopcioncList()) {
                Plantel oldIdplantelOfMesopcioncListMesopcionc = mesopcioncListMesopcionc.getIdplantel();
                mesopcioncListMesopcionc.setIdplantel(plantel);
                mesopcioncListMesopcionc = em.merge(mesopcioncListMesopcionc);
                if (oldIdplantelOfMesopcioncListMesopcionc != null) {
                    oldIdplantelOfMesopcioncListMesopcionc.getMesopcioncList().remove(mesopcioncListMesopcionc);
                    oldIdplantelOfMesopcioncListMesopcionc = em.merge(oldIdplantelOfMesopcioncListMesopcionc);
                }
            }
            for (Recursohumano recursohumanoListRecursohumano : plantel.getRecursohumanoList()) {
                Plantel oldIdplantelOfRecursohumanoListRecursohumano = recursohumanoListRecursohumano.getIdplantel();
                recursohumanoListRecursohumano.setIdplantel(plantel);
                recursohumanoListRecursohumano = em.merge(recursohumanoListRecursohumano);
                if (oldIdplantelOfRecursohumanoListRecursohumano != null) {
                    oldIdplantelOfRecursohumanoListRecursohumano.getRecursohumanoList().remove(recursohumanoListRecursohumano);
                    oldIdplantelOfRecursohumanoListRecursohumano = em.merge(oldIdplantelOfRecursohumanoListRecursohumano);
                }
            }
            for (Grupo grupoListGrupo : plantel.getGrupoList()) {
                Plantel oldIdplantelOfGrupoListGrupo = grupoListGrupo.getIdplantel();
                grupoListGrupo.setIdplantel(plantel);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdplantelOfGrupoListGrupo != null) {
                    oldIdplantelOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdplantelOfGrupoListGrupo = em.merge(oldIdplantelOfGrupoListGrupo);
                }
            }
            for (Calendariorectoria calendariorectoriaListCalendariorectoria : plantel.getCalendariorectoriaList()) {
                Plantel oldIdplantelOfCalendariorectoriaListCalendariorectoria = calendariorectoriaListCalendariorectoria.getIdplantel();
                calendariorectoriaListCalendariorectoria.setIdplantel(plantel);
                calendariorectoriaListCalendariorectoria = em.merge(calendariorectoriaListCalendariorectoria);
                if (oldIdplantelOfCalendariorectoriaListCalendariorectoria != null) {
                    oldIdplantelOfCalendariorectoriaListCalendariorectoria.getCalendariorectoriaList().remove(calendariorectoriaListCalendariorectoria);
                    oldIdplantelOfCalendariorectoriaListCalendariorectoria = em.merge(oldIdplantelOfCalendariorectoriaListCalendariorectoria);
                }
            }
            for (Calendario calendarioListCalendario : plantel.getCalendarioList()) {
                Plantel oldIdplantelOfCalendarioListCalendario = calendarioListCalendario.getIdplantel();
                calendarioListCalendario.setIdplantel(plantel);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldIdplantelOfCalendarioListCalendario != null) {
                    oldIdplantelOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldIdplantelOfCalendarioListCalendario = em.merge(oldIdplantelOfCalendarioListCalendario);
                }
            }
            for (Planestudios planestudiosListPlanestudios : plantel.getPlanestudiosList()) {
                Plantel oldIdplantelOfPlanestudiosListPlanestudios = planestudiosListPlanestudios.getIdplantel();
                planestudiosListPlanestudios.setIdplantel(plantel);
                planestudiosListPlanestudios = em.merge(planestudiosListPlanestudios);
                if (oldIdplantelOfPlanestudiosListPlanestudios != null) {
                    oldIdplantelOfPlanestudiosListPlanestudios.getPlanestudiosList().remove(planestudiosListPlanestudios);
                    oldIdplantelOfPlanestudiosListPlanestudios = em.merge(oldIdplantelOfPlanestudiosListPlanestudios);
                }
            }
            for (SuspensionLabores suspensionLaboresListSuspensionLabores : plantel.getSuspensionLaboresList()) {
                suspensionLaboresListSuspensionLabores.getPlantelList().add(plantel);
                suspensionLaboresListSuspensionLabores = em.merge(suspensionLaboresListSuspensionLabores);
            }
            for (Administrativo administrativoListAdministrativo : plantel.getAdministrativoList()) {
                administrativoListAdministrativo.getPlantelList().add(plantel);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
            }
            for (Vacacion vacacionListVacacion : plantel.getVacacionList()) {
                vacacionListVacacion.getPlantelList().add(plantel);
                vacacionListVacacion = em.merge(vacacionListVacacion);
            }
            for (Evento eventoListEvento : plantel.getEventoList()) {
                Plantel oldIdPlantelOfEventoListEvento = eventoListEvento.getIdPlantel();
                eventoListEvento.setIdPlantel(plantel);
                eventoListEvento = em.merge(eventoListEvento);
                if (oldIdPlantelOfEventoListEvento != null) {
                    oldIdPlantelOfEventoListEvento.getEventoList().remove(eventoListEvento);
                    oldIdPlantelOfEventoListEvento = em.merge(oldIdPlantelOfEventoListEvento);
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantel : plantel.getAsignacionRecursoplantelList()) {
                Plantel oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel = asignacionRecursoplantelListAsignacionRecursoplantel.getIdPlantel();
                asignacionRecursoplantelListAsignacionRecursoplantel.setIdPlantel(plantel);
                asignacionRecursoplantelListAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListAsignacionRecursoplantel);
                if (oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel != null) {
                    oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantelListAsignacionRecursoplantel);
                    oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel = em.merge(oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel);
                }
            }
            for (PlanEstudio planEstudioListPlanEstudio : plantel.getPlanEstudioList()) {
                Plantel oldIdPlantelOfPlanEstudioListPlanEstudio = planEstudioListPlanEstudio.getIdPlantel();
                planEstudioListPlanEstudio.setIdPlantel(plantel);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
                if (oldIdPlantelOfPlanEstudioListPlanEstudio != null) {
                    oldIdPlantelOfPlanEstudioListPlanEstudio.getPlanEstudioList().remove(planEstudioListPlanEstudio);
                    oldIdPlantelOfPlanEstudioListPlanEstudio = em.merge(oldIdPlantelOfPlanEstudioListPlanEstudio);
                }
            }
            for (GrupoInduccion grupoInduccionListGrupoInduccion : plantel.getGrupoInduccionList()) {
                Plantel oldIdPlantelOfGrupoInduccionListGrupoInduccion = grupoInduccionListGrupoInduccion.getIdPlantel();
                grupoInduccionListGrupoInduccion.setIdPlantel(plantel);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
                if (oldIdPlantelOfGrupoInduccionListGrupoInduccion != null) {
                    oldIdPlantelOfGrupoInduccionListGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListGrupoInduccion);
                    oldIdPlantelOfGrupoInduccionListGrupoInduccion = em.merge(oldIdPlantelOfGrupoInduccionListGrupoInduccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Plantel plantel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel persistentPlantel = em.find(Plantel.class, plantel.getIdplantel());
            Asentamiento idAsentamientoOld = persistentPlantel.getIdAsentamiento();
            Asentamiento idAsentamientoNew = plantel.getIdAsentamiento();
            List<Mesopcionc> mesopcioncListOld = persistentPlantel.getMesopcioncList();
            List<Mesopcionc> mesopcioncListNew = plantel.getMesopcioncList();
            List<Recursohumano> recursohumanoListOld = persistentPlantel.getRecursohumanoList();
            List<Recursohumano> recursohumanoListNew = plantel.getRecursohumanoList();
            List<Grupo> grupoListOld = persistentPlantel.getGrupoList();
            List<Grupo> grupoListNew = plantel.getGrupoList();
            List<Calendariorectoria> calendariorectoriaListOld = persistentPlantel.getCalendariorectoriaList();
            List<Calendariorectoria> calendariorectoriaListNew = plantel.getCalendariorectoriaList();
            List<Calendario> calendarioListOld = persistentPlantel.getCalendarioList();
            List<Calendario> calendarioListNew = plantel.getCalendarioList();
            List<Planestudios> planestudiosListOld = persistentPlantel.getPlanestudiosList();
            List<Planestudios> planestudiosListNew = plantel.getPlanestudiosList();
            List<SuspensionLabores> suspensionLaboresListOld = persistentPlantel.getSuspensionLaboresList();
            List<SuspensionLabores> suspensionLaboresListNew = plantel.getSuspensionLaboresList();
            List<Administrativo> administrativoListOld = persistentPlantel.getAdministrativoList();
            List<Administrativo> administrativoListNew = plantel.getAdministrativoList();
            List<Vacacion> vacacionListOld = persistentPlantel.getVacacionList();
            List<Vacacion> vacacionListNew = plantel.getVacacionList();
            List<Evento> eventoListOld = persistentPlantel.getEventoList();
            List<Evento> eventoListNew = plantel.getEventoList();
            List<AsignacionRecursoplantel> asignacionRecursoplantelListOld = persistentPlantel.getAsignacionRecursoplantelList();
            List<AsignacionRecursoplantel> asignacionRecursoplantelListNew = plantel.getAsignacionRecursoplantelList();
            List<PlanEstudio> planEstudioListOld = persistentPlantel.getPlanEstudioList();
            List<PlanEstudio> planEstudioListNew = plantel.getPlanEstudioList();
            List<GrupoInduccion> grupoInduccionListOld = persistentPlantel.getGrupoInduccionList();
            List<GrupoInduccion> grupoInduccionListNew = plantel.getGrupoInduccionList();
            List<String> illegalOrphanMessages = null;
            for (Planestudios planestudiosListOldPlanestudios : planestudiosListOld) {
                if (!planestudiosListNew.contains(planestudiosListOldPlanestudios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planestudios " + planestudiosListOldPlanestudios + " since its idplantel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAsentamientoNew != null) {
                idAsentamientoNew = em.getReference(idAsentamientoNew.getClass(), idAsentamientoNew.getIdAsentamiento());
                plantel.setIdAsentamiento(idAsentamientoNew);
            }
            List<Mesopcionc> attachedMesopcioncListNew = new ArrayList<Mesopcionc>();
            for (Mesopcionc mesopcioncListNewMesopcioncToAttach : mesopcioncListNew) {
                mesopcioncListNewMesopcioncToAttach = em.getReference(mesopcioncListNewMesopcioncToAttach.getClass(), mesopcioncListNewMesopcioncToAttach.getIdmesopcionc());
                attachedMesopcioncListNew.add(mesopcioncListNewMesopcioncToAttach);
            }
            mesopcioncListNew = attachedMesopcioncListNew;
            plantel.setMesopcioncList(mesopcioncListNew);
            List<Recursohumano> attachedRecursohumanoListNew = new ArrayList<Recursohumano>();
            for (Recursohumano recursohumanoListNewRecursohumanoToAttach : recursohumanoListNew) {
                recursohumanoListNewRecursohumanoToAttach = em.getReference(recursohumanoListNewRecursohumanoToAttach.getClass(), recursohumanoListNewRecursohumanoToAttach.getIdrecursohumano());
                attachedRecursohumanoListNew.add(recursohumanoListNewRecursohumanoToAttach);
            }
            recursohumanoListNew = attachedRecursohumanoListNew;
            plantel.setRecursohumanoList(recursohumanoListNew);
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            plantel.setGrupoList(grupoListNew);
            List<Calendariorectoria> attachedCalendariorectoriaListNew = new ArrayList<Calendariorectoria>();
            for (Calendariorectoria calendariorectoriaListNewCalendariorectoriaToAttach : calendariorectoriaListNew) {
                calendariorectoriaListNewCalendariorectoriaToAttach = em.getReference(calendariorectoriaListNewCalendariorectoriaToAttach.getClass(), calendariorectoriaListNewCalendariorectoriaToAttach.getIdcalendariorectoria());
                attachedCalendariorectoriaListNew.add(calendariorectoriaListNewCalendariorectoriaToAttach);
            }
            calendariorectoriaListNew = attachedCalendariorectoriaListNew;
            plantel.setCalendariorectoriaList(calendariorectoriaListNew);
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdcalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            plantel.setCalendarioList(calendarioListNew);
            List<Planestudios> attachedPlanestudiosListNew = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListNewPlanestudiosToAttach : planestudiosListNew) {
                planestudiosListNewPlanestudiosToAttach = em.getReference(planestudiosListNewPlanestudiosToAttach.getClass(), planestudiosListNewPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosListNew.add(planestudiosListNewPlanestudiosToAttach);
            }
            planestudiosListNew = attachedPlanestudiosListNew;
            plantel.setPlanestudiosList(planestudiosListNew);
            List<SuspensionLabores> attachedSuspensionLaboresListNew = new ArrayList<SuspensionLabores>();
            for (SuspensionLabores suspensionLaboresListNewSuspensionLaboresToAttach : suspensionLaboresListNew) {
                suspensionLaboresListNewSuspensionLaboresToAttach = em.getReference(suspensionLaboresListNewSuspensionLaboresToAttach.getClass(), suspensionLaboresListNewSuspensionLaboresToAttach.getIdSuspensionlabores());
                attachedSuspensionLaboresListNew.add(suspensionLaboresListNewSuspensionLaboresToAttach);
            }
            suspensionLaboresListNew = attachedSuspensionLaboresListNew;
            plantel.setSuspensionLaboresList(suspensionLaboresListNew);
            List<Administrativo> attachedAdministrativoListNew = new ArrayList<Administrativo>();
            for (Administrativo administrativoListNewAdministrativoToAttach : administrativoListNew) {
                administrativoListNewAdministrativoToAttach = em.getReference(administrativoListNewAdministrativoToAttach.getClass(), administrativoListNewAdministrativoToAttach.getIdadministrativo());
                attachedAdministrativoListNew.add(administrativoListNewAdministrativoToAttach);
            }
            administrativoListNew = attachedAdministrativoListNew;
            plantel.setAdministrativoList(administrativoListNew);
            List<Vacacion> attachedVacacionListNew = new ArrayList<Vacacion>();
            for (Vacacion vacacionListNewVacacionToAttach : vacacionListNew) {
                vacacionListNewVacacionToAttach = em.getReference(vacacionListNewVacacionToAttach.getClass(), vacacionListNewVacacionToAttach.getIdVacacion());
                attachedVacacionListNew.add(vacacionListNewVacacionToAttach);
            }
            vacacionListNew = attachedVacacionListNew;
            plantel.setVacacionList(vacacionListNew);
            List<Evento> attachedEventoListNew = new ArrayList<Evento>();
            for (Evento eventoListNewEventoToAttach : eventoListNew) {
                eventoListNewEventoToAttach = em.getReference(eventoListNewEventoToAttach.getClass(), eventoListNewEventoToAttach.getIdEvento());
                attachedEventoListNew.add(eventoListNewEventoToAttach);
            }
            eventoListNew = attachedEventoListNew;
            plantel.setEventoList(eventoListNew);
            List<AsignacionRecursoplantel> attachedAsignacionRecursoplantelListNew = new ArrayList<AsignacionRecursoplantel>();
            for (AsignacionRecursoplantel asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach : asignacionRecursoplantelListNew) {
                asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach = em.getReference(asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach.getClass(), asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach.getIdAsignacionrecursoplantel());
                attachedAsignacionRecursoplantelListNew.add(asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach);
            }
            asignacionRecursoplantelListNew = attachedAsignacionRecursoplantelListNew;
            plantel.setAsignacionRecursoplantelList(asignacionRecursoplantelListNew);
            List<PlanEstudio> attachedPlanEstudioListNew = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListNewPlanEstudioToAttach : planEstudioListNew) {
                planEstudioListNewPlanEstudioToAttach = em.getReference(planEstudioListNewPlanEstudioToAttach.getClass(), planEstudioListNewPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioListNew.add(planEstudioListNewPlanEstudioToAttach);
            }
            planEstudioListNew = attachedPlanEstudioListNew;
            plantel.setPlanEstudioList(planEstudioListNew);
            List<GrupoInduccion> attachedGrupoInduccionListNew = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListNewGrupoInduccionToAttach : grupoInduccionListNew) {
                grupoInduccionListNewGrupoInduccionToAttach = em.getReference(grupoInduccionListNewGrupoInduccionToAttach.getClass(), grupoInduccionListNewGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionListNew.add(grupoInduccionListNewGrupoInduccionToAttach);
            }
            grupoInduccionListNew = attachedGrupoInduccionListNew;
            plantel.setGrupoInduccionList(grupoInduccionListNew);
            plantel = em.merge(plantel);
            if (idAsentamientoOld != null && !idAsentamientoOld.equals(idAsentamientoNew)) {
                idAsentamientoOld.getPlantelList().remove(plantel);
                idAsentamientoOld = em.merge(idAsentamientoOld);
            }
            if (idAsentamientoNew != null && !idAsentamientoNew.equals(idAsentamientoOld)) {
                idAsentamientoNew.getPlantelList().add(plantel);
                idAsentamientoNew = em.merge(idAsentamientoNew);
            }
            for (Mesopcionc mesopcioncListOldMesopcionc : mesopcioncListOld) {
                if (!mesopcioncListNew.contains(mesopcioncListOldMesopcionc)) {
                    mesopcioncListOldMesopcionc.setIdplantel(null);
                    mesopcioncListOldMesopcionc = em.merge(mesopcioncListOldMesopcionc);
                }
            }
            for (Mesopcionc mesopcioncListNewMesopcionc : mesopcioncListNew) {
                if (!mesopcioncListOld.contains(mesopcioncListNewMesopcionc)) {
                    Plantel oldIdplantelOfMesopcioncListNewMesopcionc = mesopcioncListNewMesopcionc.getIdplantel();
                    mesopcioncListNewMesopcionc.setIdplantel(plantel);
                    mesopcioncListNewMesopcionc = em.merge(mesopcioncListNewMesopcionc);
                    if (oldIdplantelOfMesopcioncListNewMesopcionc != null && !oldIdplantelOfMesopcioncListNewMesopcionc.equals(plantel)) {
                        oldIdplantelOfMesopcioncListNewMesopcionc.getMesopcioncList().remove(mesopcioncListNewMesopcionc);
                        oldIdplantelOfMesopcioncListNewMesopcionc = em.merge(oldIdplantelOfMesopcioncListNewMesopcionc);
                    }
                }
            }
            for (Recursohumano recursohumanoListOldRecursohumano : recursohumanoListOld) {
                if (!recursohumanoListNew.contains(recursohumanoListOldRecursohumano)) {
                    recursohumanoListOldRecursohumano.setIdplantel(null);
                    recursohumanoListOldRecursohumano = em.merge(recursohumanoListOldRecursohumano);
                }
            }
            for (Recursohumano recursohumanoListNewRecursohumano : recursohumanoListNew) {
                if (!recursohumanoListOld.contains(recursohumanoListNewRecursohumano)) {
                    Plantel oldIdplantelOfRecursohumanoListNewRecursohumano = recursohumanoListNewRecursohumano.getIdplantel();
                    recursohumanoListNewRecursohumano.setIdplantel(plantel);
                    recursohumanoListNewRecursohumano = em.merge(recursohumanoListNewRecursohumano);
                    if (oldIdplantelOfRecursohumanoListNewRecursohumano != null && !oldIdplantelOfRecursohumanoListNewRecursohumano.equals(plantel)) {
                        oldIdplantelOfRecursohumanoListNewRecursohumano.getRecursohumanoList().remove(recursohumanoListNewRecursohumano);
                        oldIdplantelOfRecursohumanoListNewRecursohumano = em.merge(oldIdplantelOfRecursohumanoListNewRecursohumano);
                    }
                }
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setIdplantel(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Plantel oldIdplantelOfGrupoListNewGrupo = grupoListNewGrupo.getIdplantel();
                    grupoListNewGrupo.setIdplantel(plantel);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdplantelOfGrupoListNewGrupo != null && !oldIdplantelOfGrupoListNewGrupo.equals(plantel)) {
                        oldIdplantelOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdplantelOfGrupoListNewGrupo = em.merge(oldIdplantelOfGrupoListNewGrupo);
                    }
                }
            }
            for (Calendariorectoria calendariorectoriaListOldCalendariorectoria : calendariorectoriaListOld) {
                if (!calendariorectoriaListNew.contains(calendariorectoriaListOldCalendariorectoria)) {
                    calendariorectoriaListOldCalendariorectoria.setIdplantel(null);
                    calendariorectoriaListOldCalendariorectoria = em.merge(calendariorectoriaListOldCalendariorectoria);
                }
            }
            for (Calendariorectoria calendariorectoriaListNewCalendariorectoria : calendariorectoriaListNew) {
                if (!calendariorectoriaListOld.contains(calendariorectoriaListNewCalendariorectoria)) {
                    Plantel oldIdplantelOfCalendariorectoriaListNewCalendariorectoria = calendariorectoriaListNewCalendariorectoria.getIdplantel();
                    calendariorectoriaListNewCalendariorectoria.setIdplantel(plantel);
                    calendariorectoriaListNewCalendariorectoria = em.merge(calendariorectoriaListNewCalendariorectoria);
                    if (oldIdplantelOfCalendariorectoriaListNewCalendariorectoria != null && !oldIdplantelOfCalendariorectoriaListNewCalendariorectoria.equals(plantel)) {
                        oldIdplantelOfCalendariorectoriaListNewCalendariorectoria.getCalendariorectoriaList().remove(calendariorectoriaListNewCalendariorectoria);
                        oldIdplantelOfCalendariorectoriaListNewCalendariorectoria = em.merge(oldIdplantelOfCalendariorectoriaListNewCalendariorectoria);
                    }
                }
            }
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    calendarioListOldCalendario.setIdplantel(null);
                    calendarioListOldCalendario = em.merge(calendarioListOldCalendario);
                }
            }
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Plantel oldIdplantelOfCalendarioListNewCalendario = calendarioListNewCalendario.getIdplantel();
                    calendarioListNewCalendario.setIdplantel(plantel);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldIdplantelOfCalendarioListNewCalendario != null && !oldIdplantelOfCalendarioListNewCalendario.equals(plantel)) {
                        oldIdplantelOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldIdplantelOfCalendarioListNewCalendario = em.merge(oldIdplantelOfCalendarioListNewCalendario);
                    }
                }
            }
            for (Planestudios planestudiosListNewPlanestudios : planestudiosListNew) {
                if (!planestudiosListOld.contains(planestudiosListNewPlanestudios)) {
                    Plantel oldIdplantelOfPlanestudiosListNewPlanestudios = planestudiosListNewPlanestudios.getIdplantel();
                    planestudiosListNewPlanestudios.setIdplantel(plantel);
                    planestudiosListNewPlanestudios = em.merge(planestudiosListNewPlanestudios);
                    if (oldIdplantelOfPlanestudiosListNewPlanestudios != null && !oldIdplantelOfPlanestudiosListNewPlanestudios.equals(plantel)) {
                        oldIdplantelOfPlanestudiosListNewPlanestudios.getPlanestudiosList().remove(planestudiosListNewPlanestudios);
                        oldIdplantelOfPlanestudiosListNewPlanestudios = em.merge(oldIdplantelOfPlanestudiosListNewPlanestudios);
                    }
                }
            }
            for (SuspensionLabores suspensionLaboresListOldSuspensionLabores : suspensionLaboresListOld) {
                if (!suspensionLaboresListNew.contains(suspensionLaboresListOldSuspensionLabores)) {
                    suspensionLaboresListOldSuspensionLabores.getPlantelList().remove(plantel);
                    suspensionLaboresListOldSuspensionLabores = em.merge(suspensionLaboresListOldSuspensionLabores);
                }
            }
            for (SuspensionLabores suspensionLaboresListNewSuspensionLabores : suspensionLaboresListNew) {
                if (!suspensionLaboresListOld.contains(suspensionLaboresListNewSuspensionLabores)) {
                    suspensionLaboresListNewSuspensionLabores.getPlantelList().add(plantel);
                    suspensionLaboresListNewSuspensionLabores = em.merge(suspensionLaboresListNewSuspensionLabores);
                }
            }
            for (Administrativo administrativoListOldAdministrativo : administrativoListOld) {
                if (!administrativoListNew.contains(administrativoListOldAdministrativo)) {
                    administrativoListOldAdministrativo.getPlantelList().remove(plantel);
                    administrativoListOldAdministrativo = em.merge(administrativoListOldAdministrativo);
                }
            }
            for (Administrativo administrativoListNewAdministrativo : administrativoListNew) {
                if (!administrativoListOld.contains(administrativoListNewAdministrativo)) {
                    administrativoListNewAdministrativo.getPlantelList().add(plantel);
                    administrativoListNewAdministrativo = em.merge(administrativoListNewAdministrativo);
                }
            }
            for (Vacacion vacacionListOldVacacion : vacacionListOld) {
                if (!vacacionListNew.contains(vacacionListOldVacacion)) {
                    vacacionListOldVacacion.getPlantelList().remove(plantel);
                    vacacionListOldVacacion = em.merge(vacacionListOldVacacion);
                }
            }
            for (Vacacion vacacionListNewVacacion : vacacionListNew) {
                if (!vacacionListOld.contains(vacacionListNewVacacion)) {
                    vacacionListNewVacacion.getPlantelList().add(plantel);
                    vacacionListNewVacacion = em.merge(vacacionListNewVacacion);
                }
            }
            for (Evento eventoListOldEvento : eventoListOld) {
                if (!eventoListNew.contains(eventoListOldEvento)) {
                    eventoListOldEvento.setIdPlantel(null);
                    eventoListOldEvento = em.merge(eventoListOldEvento);
                }
            }
            for (Evento eventoListNewEvento : eventoListNew) {
                if (!eventoListOld.contains(eventoListNewEvento)) {
                    Plantel oldIdPlantelOfEventoListNewEvento = eventoListNewEvento.getIdPlantel();
                    eventoListNewEvento.setIdPlantel(plantel);
                    eventoListNewEvento = em.merge(eventoListNewEvento);
                    if (oldIdPlantelOfEventoListNewEvento != null && !oldIdPlantelOfEventoListNewEvento.equals(plantel)) {
                        oldIdPlantelOfEventoListNewEvento.getEventoList().remove(eventoListNewEvento);
                        oldIdPlantelOfEventoListNewEvento = em.merge(oldIdPlantelOfEventoListNewEvento);
                    }
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListOldAsignacionRecursoplantel : asignacionRecursoplantelListOld) {
                if (!asignacionRecursoplantelListNew.contains(asignacionRecursoplantelListOldAsignacionRecursoplantel)) {
                    asignacionRecursoplantelListOldAsignacionRecursoplantel.setIdPlantel(null);
                    asignacionRecursoplantelListOldAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListOldAsignacionRecursoplantel);
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListNewAsignacionRecursoplantel : asignacionRecursoplantelListNew) {
                if (!asignacionRecursoplantelListOld.contains(asignacionRecursoplantelListNewAsignacionRecursoplantel)) {
                    Plantel oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel = asignacionRecursoplantelListNewAsignacionRecursoplantel.getIdPlantel();
                    asignacionRecursoplantelListNewAsignacionRecursoplantel.setIdPlantel(plantel);
                    asignacionRecursoplantelListNewAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListNewAsignacionRecursoplantel);
                    if (oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel != null && !oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel.equals(plantel)) {
                        oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantelListNewAsignacionRecursoplantel);
                        oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel = em.merge(oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel);
                    }
                }
            }
            for (PlanEstudio planEstudioListOldPlanEstudio : planEstudioListOld) {
                if (!planEstudioListNew.contains(planEstudioListOldPlanEstudio)) {
                    planEstudioListOldPlanEstudio.setIdPlantel(null);
                    planEstudioListOldPlanEstudio = em.merge(planEstudioListOldPlanEstudio);
                }
            }
            for (PlanEstudio planEstudioListNewPlanEstudio : planEstudioListNew) {
                if (!planEstudioListOld.contains(planEstudioListNewPlanEstudio)) {
                    Plantel oldIdPlantelOfPlanEstudioListNewPlanEstudio = planEstudioListNewPlanEstudio.getIdPlantel();
                    planEstudioListNewPlanEstudio.setIdPlantel(plantel);
                    planEstudioListNewPlanEstudio = em.merge(planEstudioListNewPlanEstudio);
                    if (oldIdPlantelOfPlanEstudioListNewPlanEstudio != null && !oldIdPlantelOfPlanEstudioListNewPlanEstudio.equals(plantel)) {
                        oldIdPlantelOfPlanEstudioListNewPlanEstudio.getPlanEstudioList().remove(planEstudioListNewPlanEstudio);
                        oldIdPlantelOfPlanEstudioListNewPlanEstudio = em.merge(oldIdPlantelOfPlanEstudioListNewPlanEstudio);
                    }
                }
            }
            for (GrupoInduccion grupoInduccionListOldGrupoInduccion : grupoInduccionListOld) {
                if (!grupoInduccionListNew.contains(grupoInduccionListOldGrupoInduccion)) {
                    grupoInduccionListOldGrupoInduccion.setIdPlantel(null);
                    grupoInduccionListOldGrupoInduccion = em.merge(grupoInduccionListOldGrupoInduccion);
                }
            }
            for (GrupoInduccion grupoInduccionListNewGrupoInduccion : grupoInduccionListNew) {
                if (!grupoInduccionListOld.contains(grupoInduccionListNewGrupoInduccion)) {
                    Plantel oldIdPlantelOfGrupoInduccionListNewGrupoInduccion = grupoInduccionListNewGrupoInduccion.getIdPlantel();
                    grupoInduccionListNewGrupoInduccion.setIdPlantel(plantel);
                    grupoInduccionListNewGrupoInduccion = em.merge(grupoInduccionListNewGrupoInduccion);
                    if (oldIdPlantelOfGrupoInduccionListNewGrupoInduccion != null && !oldIdPlantelOfGrupoInduccionListNewGrupoInduccion.equals(plantel)) {
                        oldIdPlantelOfGrupoInduccionListNewGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListNewGrupoInduccion);
                        oldIdPlantelOfGrupoInduccionListNewGrupoInduccion = em.merge(oldIdPlantelOfGrupoInduccionListNewGrupoInduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = plantel.getIdplantel();
                if (findPlantel(id) == null) {
                    throw new NonexistentEntityException("The plantel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel plantel;
            try {
                plantel = em.getReference(Plantel.class, id);
                plantel.getIdplantel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plantel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Planestudios> planestudiosListOrphanCheck = plantel.getPlanestudiosList();
            for (Planestudios planestudiosListOrphanCheckPlanestudios : planestudiosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Plantel (" + plantel + ") cannot be destroyed since the Planestudios " + planestudiosListOrphanCheckPlanestudios + " in its planestudiosList field has a non-nullable idplantel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Asentamiento idAsentamiento = plantel.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento.getPlantelList().remove(plantel);
                idAsentamiento = em.merge(idAsentamiento);
            }
            List<Mesopcionc> mesopcioncList = plantel.getMesopcioncList();
            for (Mesopcionc mesopcioncListMesopcionc : mesopcioncList) {
                mesopcioncListMesopcionc.setIdplantel(null);
                mesopcioncListMesopcionc = em.merge(mesopcioncListMesopcionc);
            }
            List<Recursohumano> recursohumanoList = plantel.getRecursohumanoList();
            for (Recursohumano recursohumanoListRecursohumano : recursohumanoList) {
                recursohumanoListRecursohumano.setIdplantel(null);
                recursohumanoListRecursohumano = em.merge(recursohumanoListRecursohumano);
            }
            List<Grupo> grupoList = plantel.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setIdplantel(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            List<Calendariorectoria> calendariorectoriaList = plantel.getCalendariorectoriaList();
            for (Calendariorectoria calendariorectoriaListCalendariorectoria : calendariorectoriaList) {
                calendariorectoriaListCalendariorectoria.setIdplantel(null);
                calendariorectoriaListCalendariorectoria = em.merge(calendariorectoriaListCalendariorectoria);
            }
            List<Calendario> calendarioList = plantel.getCalendarioList();
            for (Calendario calendarioListCalendario : calendarioList) {
                calendarioListCalendario.setIdplantel(null);
                calendarioListCalendario = em.merge(calendarioListCalendario);
            }
            List<SuspensionLabores> suspensionLaboresList = plantel.getSuspensionLaboresList();
            for (SuspensionLabores suspensionLaboresListSuspensionLabores : suspensionLaboresList) {
                suspensionLaboresListSuspensionLabores.getPlantelList().remove(plantel);
                suspensionLaboresListSuspensionLabores = em.merge(suspensionLaboresListSuspensionLabores);
            }
            List<Administrativo> administrativoList = plantel.getAdministrativoList();
            for (Administrativo administrativoListAdministrativo : administrativoList) {
                administrativoListAdministrativo.getPlantelList().remove(plantel);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
            }
            List<Vacacion> vacacionList = plantel.getVacacionList();
            for (Vacacion vacacionListVacacion : vacacionList) {
                vacacionListVacacion.getPlantelList().remove(plantel);
                vacacionListVacacion = em.merge(vacacionListVacacion);
            }
            List<Evento> eventoList = plantel.getEventoList();
            for (Evento eventoListEvento : eventoList) {
                eventoListEvento.setIdPlantel(null);
                eventoListEvento = em.merge(eventoListEvento);
            }
            List<AsignacionRecursoplantel> asignacionRecursoplantelList = plantel.getAsignacionRecursoplantelList();
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantel : asignacionRecursoplantelList) {
                asignacionRecursoplantelListAsignacionRecursoplantel.setIdPlantel(null);
                asignacionRecursoplantelListAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListAsignacionRecursoplantel);
            }
            List<PlanEstudio> planEstudioList = plantel.getPlanEstudioList();
            for (PlanEstudio planEstudioListPlanEstudio : planEstudioList) {
                planEstudioListPlanEstudio.setIdPlantel(null);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
            }
            List<GrupoInduccion> grupoInduccionList = plantel.getGrupoInduccionList();
            for (GrupoInduccion grupoInduccionListGrupoInduccion : grupoInduccionList) {
                grupoInduccionListGrupoInduccion.setIdPlantel(null);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
            }
            em.remove(plantel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Plantel> findPlantelEntities() {
        return findPlantelEntities(true, -1, -1);
    }

    public List<Plantel> findPlantelEntities(int maxResults, int firstResult) {
        return findPlantelEntities(false, maxResults, firstResult);
    }

    private List<Plantel> findPlantelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Plantel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Plantel findPlantel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plantel.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlantelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Plantel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
