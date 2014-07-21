/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Horario;
import com.utez.integracion.entity.CalendarioEscolar;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.integracion.entity.AsignacionIntegradoragrupo;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Asesoria;
import com.utez.integracion.entity.HistoricoCalendarioasignatura;
import com.utez.integracion.entity.FechaExamen;
import com.utez.integracion.entity.AsignaturaAsesor;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.ProgramacionEspecifica;
import com.utez.integracion.entity.HistoricoAsesorasignatura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalendarioAsignaturaJpaController implements Serializable {

    public CalendarioAsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CalendarioAsignatura calendarioAsignatura) {
        if (calendarioAsignatura.getAsignacionIntegradoragrupoList() == null) {
            calendarioAsignatura.setAsignacionIntegradoragrupoList(new ArrayList<AsignacionIntegradoragrupo>());
        }
        if (calendarioAsignatura.getAsesoriaList() == null) {
            calendarioAsignatura.setAsesoriaList(new ArrayList<Asesoria>());
        }
        if (calendarioAsignatura.getHistoricoCalendarioasignaturaList() == null) {
            calendarioAsignatura.setHistoricoCalendarioasignaturaList(new ArrayList<HistoricoCalendarioasignatura>());
        }
        if (calendarioAsignatura.getFechaExamenList() == null) {
            calendarioAsignatura.setFechaExamenList(new ArrayList<FechaExamen>());
        }
        if (calendarioAsignatura.getAsignaturaAsesorList() == null) {
            calendarioAsignatura.setAsignaturaAsesorList(new ArrayList<AsignaturaAsesor>());
        }
        if (calendarioAsignatura.getProgramacionEspecificaList() == null) {
            calendarioAsignatura.setProgramacionEspecificaList(new ArrayList<ProgramacionEspecifica>());
        }
        if (calendarioAsignatura.getHistoricoAsesorasignaturaList() == null) {
            calendarioAsignatura.setHistoricoAsesorasignaturaList(new ArrayList<HistoricoAsesorasignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario idHorario = calendarioAsignatura.getIdHorario();
            if (idHorario != null) {
                idHorario = em.getReference(idHorario.getClass(), idHorario.getIdHorario());
                calendarioAsignatura.setIdHorario(idHorario);
            }
            CalendarioEscolar idCalendarioescolar = calendarioAsignatura.getIdCalendarioescolar();
            if (idCalendarioescolar != null) {
                idCalendarioescolar = em.getReference(idCalendarioescolar.getClass(), idCalendarioescolar.getIdCalendarioescolar());
                calendarioAsignatura.setIdCalendarioescolar(idCalendarioescolar);
            }
            Asignatura idAsignatura = calendarioAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdasignatura());
                calendarioAsignatura.setIdAsignatura(idAsignatura);
            }
            List<AsignacionIntegradoragrupo> attachedAsignacionIntegradoragrupoList = new ArrayList<AsignacionIntegradoragrupo>();
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach : calendarioAsignatura.getAsignacionIntegradoragrupoList()) {
                asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach = em.getReference(asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach.getClass(), asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach.getIdAsignacionintegradoragrupo());
                attachedAsignacionIntegradoragrupoList.add(asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach);
            }
            calendarioAsignatura.setAsignacionIntegradoragrupoList(attachedAsignacionIntegradoragrupoList);
            List<Asesoria> attachedAsesoriaList = new ArrayList<Asesoria>();
            for (Asesoria asesoriaListAsesoriaToAttach : calendarioAsignatura.getAsesoriaList()) {
                asesoriaListAsesoriaToAttach = em.getReference(asesoriaListAsesoriaToAttach.getClass(), asesoriaListAsesoriaToAttach.getIdAsesoria());
                attachedAsesoriaList.add(asesoriaListAsesoriaToAttach);
            }
            calendarioAsignatura.setAsesoriaList(attachedAsesoriaList);
            List<HistoricoCalendarioasignatura> attachedHistoricoCalendarioasignaturaList = new ArrayList<HistoricoCalendarioasignatura>();
            for (HistoricoCalendarioasignatura historicoCalendarioasignaturaListHistoricoCalendarioasignaturaToAttach : calendarioAsignatura.getHistoricoCalendarioasignaturaList()) {
                historicoCalendarioasignaturaListHistoricoCalendarioasignaturaToAttach = em.getReference(historicoCalendarioasignaturaListHistoricoCalendarioasignaturaToAttach.getClass(), historicoCalendarioasignaturaListHistoricoCalendarioasignaturaToAttach.getIdHistoricocalendarioasignatura());
                attachedHistoricoCalendarioasignaturaList.add(historicoCalendarioasignaturaListHistoricoCalendarioasignaturaToAttach);
            }
            calendarioAsignatura.setHistoricoCalendarioasignaturaList(attachedHistoricoCalendarioasignaturaList);
            List<FechaExamen> attachedFechaExamenList = new ArrayList<FechaExamen>();
            for (FechaExamen fechaExamenListFechaExamenToAttach : calendarioAsignatura.getFechaExamenList()) {
                fechaExamenListFechaExamenToAttach = em.getReference(fechaExamenListFechaExamenToAttach.getClass(), fechaExamenListFechaExamenToAttach.getIdFechaexamen());
                attachedFechaExamenList.add(fechaExamenListFechaExamenToAttach);
            }
            calendarioAsignatura.setFechaExamenList(attachedFechaExamenList);
            List<AsignaturaAsesor> attachedAsignaturaAsesorList = new ArrayList<AsignaturaAsesor>();
            for (AsignaturaAsesor asignaturaAsesorListAsignaturaAsesorToAttach : calendarioAsignatura.getAsignaturaAsesorList()) {
                asignaturaAsesorListAsignaturaAsesorToAttach = em.getReference(asignaturaAsesorListAsignaturaAsesorToAttach.getClass(), asignaturaAsesorListAsignaturaAsesorToAttach.getIdAsignaturaasesor());
                attachedAsignaturaAsesorList.add(asignaturaAsesorListAsignaturaAsesorToAttach);
            }
            calendarioAsignatura.setAsignaturaAsesorList(attachedAsignaturaAsesorList);
            List<ProgramacionEspecifica> attachedProgramacionEspecificaList = new ArrayList<ProgramacionEspecifica>();
            for (ProgramacionEspecifica programacionEspecificaListProgramacionEspecificaToAttach : calendarioAsignatura.getProgramacionEspecificaList()) {
                programacionEspecificaListProgramacionEspecificaToAttach = em.getReference(programacionEspecificaListProgramacionEspecificaToAttach.getClass(), programacionEspecificaListProgramacionEspecificaToAttach.getIdProgramacionasignatura());
                attachedProgramacionEspecificaList.add(programacionEspecificaListProgramacionEspecificaToAttach);
            }
            calendarioAsignatura.setProgramacionEspecificaList(attachedProgramacionEspecificaList);
            List<HistoricoAsesorasignatura> attachedHistoricoAsesorasignaturaList = new ArrayList<HistoricoAsesorasignatura>();
            for (HistoricoAsesorasignatura historicoAsesorasignaturaListHistoricoAsesorasignaturaToAttach : calendarioAsignatura.getHistoricoAsesorasignaturaList()) {
                historicoAsesorasignaturaListHistoricoAsesorasignaturaToAttach = em.getReference(historicoAsesorasignaturaListHistoricoAsesorasignaturaToAttach.getClass(), historicoAsesorasignaturaListHistoricoAsesorasignaturaToAttach.getIdHistoricoidasignaturaasesor());
                attachedHistoricoAsesorasignaturaList.add(historicoAsesorasignaturaListHistoricoAsesorasignaturaToAttach);
            }
            calendarioAsignatura.setHistoricoAsesorasignaturaList(attachedHistoricoAsesorasignaturaList);
            em.persist(calendarioAsignatura);
            if (idHorario != null) {
                idHorario.getCalendarioAsignaturaList().add(calendarioAsignatura);
                idHorario = em.merge(idHorario);
            }
            if (idCalendarioescolar != null) {
                idCalendarioescolar.getCalendarioAsignaturaList().add(calendarioAsignatura);
                idCalendarioescolar = em.merge(idCalendarioescolar);
            }
            if (idAsignatura != null) {
                idAsignatura.getCalendarioAsignaturaList().add(calendarioAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListAsignacionIntegradoragrupo : calendarioAsignatura.getAsignacionIntegradoragrupoList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo = asignacionIntegradoragrupoListAsignacionIntegradoragrupo.getIdCalendarioasignatura();
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo.setIdCalendarioasignatura(calendarioAsignatura);
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListAsignacionIntegradoragrupo);
                if (oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo != null) {
                    oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupoListAsignacionIntegradoragrupo);
                    oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo = em.merge(oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo);
                }
            }
            for (Asesoria asesoriaListAsesoria : calendarioAsignatura.getAsesoriaList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfAsesoriaListAsesoria = asesoriaListAsesoria.getIdCalendarioasignatura();
                asesoriaListAsesoria.setIdCalendarioasignatura(calendarioAsignatura);
                asesoriaListAsesoria = em.merge(asesoriaListAsesoria);
                if (oldIdCalendarioasignaturaOfAsesoriaListAsesoria != null) {
                    oldIdCalendarioasignaturaOfAsesoriaListAsesoria.getAsesoriaList().remove(asesoriaListAsesoria);
                    oldIdCalendarioasignaturaOfAsesoriaListAsesoria = em.merge(oldIdCalendarioasignaturaOfAsesoriaListAsesoria);
                }
            }
            for (HistoricoCalendarioasignatura historicoCalendarioasignaturaListHistoricoCalendarioasignatura : calendarioAsignatura.getHistoricoCalendarioasignaturaList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListHistoricoCalendarioasignatura = historicoCalendarioasignaturaListHistoricoCalendarioasignatura.getIdCalendarioasignatura();
                historicoCalendarioasignaturaListHistoricoCalendarioasignatura.setIdCalendarioasignatura(calendarioAsignatura);
                historicoCalendarioasignaturaListHistoricoCalendarioasignatura = em.merge(historicoCalendarioasignaturaListHistoricoCalendarioasignatura);
                if (oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListHistoricoCalendarioasignatura != null) {
                    oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListHistoricoCalendarioasignatura.getHistoricoCalendarioasignaturaList().remove(historicoCalendarioasignaturaListHistoricoCalendarioasignatura);
                    oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListHistoricoCalendarioasignatura = em.merge(oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListHistoricoCalendarioasignatura);
                }
            }
            for (FechaExamen fechaExamenListFechaExamen : calendarioAsignatura.getFechaExamenList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfFechaExamenListFechaExamen = fechaExamenListFechaExamen.getIdCalendarioasignatura();
                fechaExamenListFechaExamen.setIdCalendarioasignatura(calendarioAsignatura);
                fechaExamenListFechaExamen = em.merge(fechaExamenListFechaExamen);
                if (oldIdCalendarioasignaturaOfFechaExamenListFechaExamen != null) {
                    oldIdCalendarioasignaturaOfFechaExamenListFechaExamen.getFechaExamenList().remove(fechaExamenListFechaExamen);
                    oldIdCalendarioasignaturaOfFechaExamenListFechaExamen = em.merge(oldIdCalendarioasignaturaOfFechaExamenListFechaExamen);
                }
            }
            for (AsignaturaAsesor asignaturaAsesorListAsignaturaAsesor : calendarioAsignatura.getAsignaturaAsesorList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfAsignaturaAsesorListAsignaturaAsesor = asignaturaAsesorListAsignaturaAsesor.getIdCalendarioasignatura();
                asignaturaAsesorListAsignaturaAsesor.setIdCalendarioasignatura(calendarioAsignatura);
                asignaturaAsesorListAsignaturaAsesor = em.merge(asignaturaAsesorListAsignaturaAsesor);
                if (oldIdCalendarioasignaturaOfAsignaturaAsesorListAsignaturaAsesor != null) {
                    oldIdCalendarioasignaturaOfAsignaturaAsesorListAsignaturaAsesor.getAsignaturaAsesorList().remove(asignaturaAsesorListAsignaturaAsesor);
                    oldIdCalendarioasignaturaOfAsignaturaAsesorListAsignaturaAsesor = em.merge(oldIdCalendarioasignaturaOfAsignaturaAsesorListAsignaturaAsesor);
                }
            }
            for (ProgramacionEspecifica programacionEspecificaListProgramacionEspecifica : calendarioAsignatura.getProgramacionEspecificaList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfProgramacionEspecificaListProgramacionEspecifica = programacionEspecificaListProgramacionEspecifica.getIdCalendarioasignatura();
                programacionEspecificaListProgramacionEspecifica.setIdCalendarioasignatura(calendarioAsignatura);
                programacionEspecificaListProgramacionEspecifica = em.merge(programacionEspecificaListProgramacionEspecifica);
                if (oldIdCalendarioasignaturaOfProgramacionEspecificaListProgramacionEspecifica != null) {
                    oldIdCalendarioasignaturaOfProgramacionEspecificaListProgramacionEspecifica.getProgramacionEspecificaList().remove(programacionEspecificaListProgramacionEspecifica);
                    oldIdCalendarioasignaturaOfProgramacionEspecificaListProgramacionEspecifica = em.merge(oldIdCalendarioasignaturaOfProgramacionEspecificaListProgramacionEspecifica);
                }
            }
            for (HistoricoAsesorasignatura historicoAsesorasignaturaListHistoricoAsesorasignatura : calendarioAsignatura.getHistoricoAsesorasignaturaList()) {
                CalendarioAsignatura oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListHistoricoAsesorasignatura = historicoAsesorasignaturaListHistoricoAsesorasignatura.getIdCalendarioasignatura();
                historicoAsesorasignaturaListHistoricoAsesorasignatura.setIdCalendarioasignatura(calendarioAsignatura);
                historicoAsesorasignaturaListHistoricoAsesorasignatura = em.merge(historicoAsesorasignaturaListHistoricoAsesorasignatura);
                if (oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListHistoricoAsesorasignatura != null) {
                    oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListHistoricoAsesorasignatura.getHistoricoAsesorasignaturaList().remove(historicoAsesorasignaturaListHistoricoAsesorasignatura);
                    oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListHistoricoAsesorasignatura = em.merge(oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListHistoricoAsesorasignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CalendarioAsignatura calendarioAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioAsignatura persistentCalendarioAsignatura = em.find(CalendarioAsignatura.class, calendarioAsignatura.getIdCalendarioasignatura());
            Horario idHorarioOld = persistentCalendarioAsignatura.getIdHorario();
            Horario idHorarioNew = calendarioAsignatura.getIdHorario();
            CalendarioEscolar idCalendarioescolarOld = persistentCalendarioAsignatura.getIdCalendarioescolar();
            CalendarioEscolar idCalendarioescolarNew = calendarioAsignatura.getIdCalendarioescolar();
            Asignatura idAsignaturaOld = persistentCalendarioAsignatura.getIdAsignatura();
            Asignatura idAsignaturaNew = calendarioAsignatura.getIdAsignatura();
            List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoListOld = persistentCalendarioAsignatura.getAsignacionIntegradoragrupoList();
            List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoListNew = calendarioAsignatura.getAsignacionIntegradoragrupoList();
            List<Asesoria> asesoriaListOld = persistentCalendarioAsignatura.getAsesoriaList();
            List<Asesoria> asesoriaListNew = calendarioAsignatura.getAsesoriaList();
            List<HistoricoCalendarioasignatura> historicoCalendarioasignaturaListOld = persistentCalendarioAsignatura.getHistoricoCalendarioasignaturaList();
            List<HistoricoCalendarioasignatura> historicoCalendarioasignaturaListNew = calendarioAsignatura.getHistoricoCalendarioasignaturaList();
            List<FechaExamen> fechaExamenListOld = persistentCalendarioAsignatura.getFechaExamenList();
            List<FechaExamen> fechaExamenListNew = calendarioAsignatura.getFechaExamenList();
            List<AsignaturaAsesor> asignaturaAsesorListOld = persistentCalendarioAsignatura.getAsignaturaAsesorList();
            List<AsignaturaAsesor> asignaturaAsesorListNew = calendarioAsignatura.getAsignaturaAsesorList();
            List<ProgramacionEspecifica> programacionEspecificaListOld = persistentCalendarioAsignatura.getProgramacionEspecificaList();
            List<ProgramacionEspecifica> programacionEspecificaListNew = calendarioAsignatura.getProgramacionEspecificaList();
            List<HistoricoAsesorasignatura> historicoAsesorasignaturaListOld = persistentCalendarioAsignatura.getHistoricoAsesorasignaturaList();
            List<HistoricoAsesorasignatura> historicoAsesorasignaturaListNew = calendarioAsignatura.getHistoricoAsesorasignaturaList();
            if (idHorarioNew != null) {
                idHorarioNew = em.getReference(idHorarioNew.getClass(), idHorarioNew.getIdHorario());
                calendarioAsignatura.setIdHorario(idHorarioNew);
            }
            if (idCalendarioescolarNew != null) {
                idCalendarioescolarNew = em.getReference(idCalendarioescolarNew.getClass(), idCalendarioescolarNew.getIdCalendarioescolar());
                calendarioAsignatura.setIdCalendarioescolar(idCalendarioescolarNew);
            }
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdasignatura());
                calendarioAsignatura.setIdAsignatura(idAsignaturaNew);
            }
            List<AsignacionIntegradoragrupo> attachedAsignacionIntegradoragrupoListNew = new ArrayList<AsignacionIntegradoragrupo>();
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach : asignacionIntegradoragrupoListNew) {
                asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach = em.getReference(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach.getClass(), asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach.getIdAsignacionintegradoragrupo());
                attachedAsignacionIntegradoragrupoListNew.add(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach);
            }
            asignacionIntegradoragrupoListNew = attachedAsignacionIntegradoragrupoListNew;
            calendarioAsignatura.setAsignacionIntegradoragrupoList(asignacionIntegradoragrupoListNew);
            List<Asesoria> attachedAsesoriaListNew = new ArrayList<Asesoria>();
            for (Asesoria asesoriaListNewAsesoriaToAttach : asesoriaListNew) {
                asesoriaListNewAsesoriaToAttach = em.getReference(asesoriaListNewAsesoriaToAttach.getClass(), asesoriaListNewAsesoriaToAttach.getIdAsesoria());
                attachedAsesoriaListNew.add(asesoriaListNewAsesoriaToAttach);
            }
            asesoriaListNew = attachedAsesoriaListNew;
            calendarioAsignatura.setAsesoriaList(asesoriaListNew);
            List<HistoricoCalendarioasignatura> attachedHistoricoCalendarioasignaturaListNew = new ArrayList<HistoricoCalendarioasignatura>();
            for (HistoricoCalendarioasignatura historicoCalendarioasignaturaListNewHistoricoCalendarioasignaturaToAttach : historicoCalendarioasignaturaListNew) {
                historicoCalendarioasignaturaListNewHistoricoCalendarioasignaturaToAttach = em.getReference(historicoCalendarioasignaturaListNewHistoricoCalendarioasignaturaToAttach.getClass(), historicoCalendarioasignaturaListNewHistoricoCalendarioasignaturaToAttach.getIdHistoricocalendarioasignatura());
                attachedHistoricoCalendarioasignaturaListNew.add(historicoCalendarioasignaturaListNewHistoricoCalendarioasignaturaToAttach);
            }
            historicoCalendarioasignaturaListNew = attachedHistoricoCalendarioasignaturaListNew;
            calendarioAsignatura.setHistoricoCalendarioasignaturaList(historicoCalendarioasignaturaListNew);
            List<FechaExamen> attachedFechaExamenListNew = new ArrayList<FechaExamen>();
            for (FechaExamen fechaExamenListNewFechaExamenToAttach : fechaExamenListNew) {
                fechaExamenListNewFechaExamenToAttach = em.getReference(fechaExamenListNewFechaExamenToAttach.getClass(), fechaExamenListNewFechaExamenToAttach.getIdFechaexamen());
                attachedFechaExamenListNew.add(fechaExamenListNewFechaExamenToAttach);
            }
            fechaExamenListNew = attachedFechaExamenListNew;
            calendarioAsignatura.setFechaExamenList(fechaExamenListNew);
            List<AsignaturaAsesor> attachedAsignaturaAsesorListNew = new ArrayList<AsignaturaAsesor>();
            for (AsignaturaAsesor asignaturaAsesorListNewAsignaturaAsesorToAttach : asignaturaAsesorListNew) {
                asignaturaAsesorListNewAsignaturaAsesorToAttach = em.getReference(asignaturaAsesorListNewAsignaturaAsesorToAttach.getClass(), asignaturaAsesorListNewAsignaturaAsesorToAttach.getIdAsignaturaasesor());
                attachedAsignaturaAsesorListNew.add(asignaturaAsesorListNewAsignaturaAsesorToAttach);
            }
            asignaturaAsesorListNew = attachedAsignaturaAsesorListNew;
            calendarioAsignatura.setAsignaturaAsesorList(asignaturaAsesorListNew);
            List<ProgramacionEspecifica> attachedProgramacionEspecificaListNew = new ArrayList<ProgramacionEspecifica>();
            for (ProgramacionEspecifica programacionEspecificaListNewProgramacionEspecificaToAttach : programacionEspecificaListNew) {
                programacionEspecificaListNewProgramacionEspecificaToAttach = em.getReference(programacionEspecificaListNewProgramacionEspecificaToAttach.getClass(), programacionEspecificaListNewProgramacionEspecificaToAttach.getIdProgramacionasignatura());
                attachedProgramacionEspecificaListNew.add(programacionEspecificaListNewProgramacionEspecificaToAttach);
            }
            programacionEspecificaListNew = attachedProgramacionEspecificaListNew;
            calendarioAsignatura.setProgramacionEspecificaList(programacionEspecificaListNew);
            List<HistoricoAsesorasignatura> attachedHistoricoAsesorasignaturaListNew = new ArrayList<HistoricoAsesorasignatura>();
            for (HistoricoAsesorasignatura historicoAsesorasignaturaListNewHistoricoAsesorasignaturaToAttach : historicoAsesorasignaturaListNew) {
                historicoAsesorasignaturaListNewHistoricoAsesorasignaturaToAttach = em.getReference(historicoAsesorasignaturaListNewHistoricoAsesorasignaturaToAttach.getClass(), historicoAsesorasignaturaListNewHistoricoAsesorasignaturaToAttach.getIdHistoricoidasignaturaasesor());
                attachedHistoricoAsesorasignaturaListNew.add(historicoAsesorasignaturaListNewHistoricoAsesorasignaturaToAttach);
            }
            historicoAsesorasignaturaListNew = attachedHistoricoAsesorasignaturaListNew;
            calendarioAsignatura.setHistoricoAsesorasignaturaList(historicoAsesorasignaturaListNew);
            calendarioAsignatura = em.merge(calendarioAsignatura);
            if (idHorarioOld != null && !idHorarioOld.equals(idHorarioNew)) {
                idHorarioOld.getCalendarioAsignaturaList().remove(calendarioAsignatura);
                idHorarioOld = em.merge(idHorarioOld);
            }
            if (idHorarioNew != null && !idHorarioNew.equals(idHorarioOld)) {
                idHorarioNew.getCalendarioAsignaturaList().add(calendarioAsignatura);
                idHorarioNew = em.merge(idHorarioNew);
            }
            if (idCalendarioescolarOld != null && !idCalendarioescolarOld.equals(idCalendarioescolarNew)) {
                idCalendarioescolarOld.getCalendarioAsignaturaList().remove(calendarioAsignatura);
                idCalendarioescolarOld = em.merge(idCalendarioescolarOld);
            }
            if (idCalendarioescolarNew != null && !idCalendarioescolarNew.equals(idCalendarioescolarOld)) {
                idCalendarioescolarNew.getCalendarioAsignaturaList().add(calendarioAsignatura);
                idCalendarioescolarNew = em.merge(idCalendarioescolarNew);
            }
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getCalendarioAsignaturaList().remove(calendarioAsignatura);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getCalendarioAsignaturaList().add(calendarioAsignatura);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo : asignacionIntegradoragrupoListOld) {
                if (!asignacionIntegradoragrupoListNew.contains(asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo)) {
                    asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo.setIdCalendarioasignatura(null);
                    asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo);
                }
            }
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo : asignacionIntegradoragrupoListNew) {
                if (!asignacionIntegradoragrupoListOld.contains(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo = asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.getIdCalendarioasignatura();
                    asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.setIdCalendarioasignatura(calendarioAsignatura);
                    asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo);
                    if (oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo != null && !oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo);
                        oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo = em.merge(oldIdCalendarioasignaturaOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo);
                    }
                }
            }
            for (Asesoria asesoriaListOldAsesoria : asesoriaListOld) {
                if (!asesoriaListNew.contains(asesoriaListOldAsesoria)) {
                    asesoriaListOldAsesoria.setIdCalendarioasignatura(null);
                    asesoriaListOldAsesoria = em.merge(asesoriaListOldAsesoria);
                }
            }
            for (Asesoria asesoriaListNewAsesoria : asesoriaListNew) {
                if (!asesoriaListOld.contains(asesoriaListNewAsesoria)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfAsesoriaListNewAsesoria = asesoriaListNewAsesoria.getIdCalendarioasignatura();
                    asesoriaListNewAsesoria.setIdCalendarioasignatura(calendarioAsignatura);
                    asesoriaListNewAsesoria = em.merge(asesoriaListNewAsesoria);
                    if (oldIdCalendarioasignaturaOfAsesoriaListNewAsesoria != null && !oldIdCalendarioasignaturaOfAsesoriaListNewAsesoria.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfAsesoriaListNewAsesoria.getAsesoriaList().remove(asesoriaListNewAsesoria);
                        oldIdCalendarioasignaturaOfAsesoriaListNewAsesoria = em.merge(oldIdCalendarioasignaturaOfAsesoriaListNewAsesoria);
                    }
                }
            }
            for (HistoricoCalendarioasignatura historicoCalendarioasignaturaListOldHistoricoCalendarioasignatura : historicoCalendarioasignaturaListOld) {
                if (!historicoCalendarioasignaturaListNew.contains(historicoCalendarioasignaturaListOldHistoricoCalendarioasignatura)) {
                    historicoCalendarioasignaturaListOldHistoricoCalendarioasignatura.setIdCalendarioasignatura(null);
                    historicoCalendarioasignaturaListOldHistoricoCalendarioasignatura = em.merge(historicoCalendarioasignaturaListOldHistoricoCalendarioasignatura);
                }
            }
            for (HistoricoCalendarioasignatura historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura : historicoCalendarioasignaturaListNew) {
                if (!historicoCalendarioasignaturaListOld.contains(historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListNewHistoricoCalendarioasignatura = historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura.getIdCalendarioasignatura();
                    historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura.setIdCalendarioasignatura(calendarioAsignatura);
                    historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura = em.merge(historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura);
                    if (oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListNewHistoricoCalendarioasignatura != null && !oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListNewHistoricoCalendarioasignatura.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListNewHistoricoCalendarioasignatura.getHistoricoCalendarioasignaturaList().remove(historicoCalendarioasignaturaListNewHistoricoCalendarioasignatura);
                        oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListNewHistoricoCalendarioasignatura = em.merge(oldIdCalendarioasignaturaOfHistoricoCalendarioasignaturaListNewHistoricoCalendarioasignatura);
                    }
                }
            }
            for (FechaExamen fechaExamenListOldFechaExamen : fechaExamenListOld) {
                if (!fechaExamenListNew.contains(fechaExamenListOldFechaExamen)) {
                    fechaExamenListOldFechaExamen.setIdCalendarioasignatura(null);
                    fechaExamenListOldFechaExamen = em.merge(fechaExamenListOldFechaExamen);
                }
            }
            for (FechaExamen fechaExamenListNewFechaExamen : fechaExamenListNew) {
                if (!fechaExamenListOld.contains(fechaExamenListNewFechaExamen)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfFechaExamenListNewFechaExamen = fechaExamenListNewFechaExamen.getIdCalendarioasignatura();
                    fechaExamenListNewFechaExamen.setIdCalendarioasignatura(calendarioAsignatura);
                    fechaExamenListNewFechaExamen = em.merge(fechaExamenListNewFechaExamen);
                    if (oldIdCalendarioasignaturaOfFechaExamenListNewFechaExamen != null && !oldIdCalendarioasignaturaOfFechaExamenListNewFechaExamen.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfFechaExamenListNewFechaExamen.getFechaExamenList().remove(fechaExamenListNewFechaExamen);
                        oldIdCalendarioasignaturaOfFechaExamenListNewFechaExamen = em.merge(oldIdCalendarioasignaturaOfFechaExamenListNewFechaExamen);
                    }
                }
            }
            for (AsignaturaAsesor asignaturaAsesorListOldAsignaturaAsesor : asignaturaAsesorListOld) {
                if (!asignaturaAsesorListNew.contains(asignaturaAsesorListOldAsignaturaAsesor)) {
                    asignaturaAsesorListOldAsignaturaAsesor.setIdCalendarioasignatura(null);
                    asignaturaAsesorListOldAsignaturaAsesor = em.merge(asignaturaAsesorListOldAsignaturaAsesor);
                }
            }
            for (AsignaturaAsesor asignaturaAsesorListNewAsignaturaAsesor : asignaturaAsesorListNew) {
                if (!asignaturaAsesorListOld.contains(asignaturaAsesorListNewAsignaturaAsesor)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfAsignaturaAsesorListNewAsignaturaAsesor = asignaturaAsesorListNewAsignaturaAsesor.getIdCalendarioasignatura();
                    asignaturaAsesorListNewAsignaturaAsesor.setIdCalendarioasignatura(calendarioAsignatura);
                    asignaturaAsesorListNewAsignaturaAsesor = em.merge(asignaturaAsesorListNewAsignaturaAsesor);
                    if (oldIdCalendarioasignaturaOfAsignaturaAsesorListNewAsignaturaAsesor != null && !oldIdCalendarioasignaturaOfAsignaturaAsesorListNewAsignaturaAsesor.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfAsignaturaAsesorListNewAsignaturaAsesor.getAsignaturaAsesorList().remove(asignaturaAsesorListNewAsignaturaAsesor);
                        oldIdCalendarioasignaturaOfAsignaturaAsesorListNewAsignaturaAsesor = em.merge(oldIdCalendarioasignaturaOfAsignaturaAsesorListNewAsignaturaAsesor);
                    }
                }
            }
            for (ProgramacionEspecifica programacionEspecificaListOldProgramacionEspecifica : programacionEspecificaListOld) {
                if (!programacionEspecificaListNew.contains(programacionEspecificaListOldProgramacionEspecifica)) {
                    programacionEspecificaListOldProgramacionEspecifica.setIdCalendarioasignatura(null);
                    programacionEspecificaListOldProgramacionEspecifica = em.merge(programacionEspecificaListOldProgramacionEspecifica);
                }
            }
            for (ProgramacionEspecifica programacionEspecificaListNewProgramacionEspecifica : programacionEspecificaListNew) {
                if (!programacionEspecificaListOld.contains(programacionEspecificaListNewProgramacionEspecifica)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfProgramacionEspecificaListNewProgramacionEspecifica = programacionEspecificaListNewProgramacionEspecifica.getIdCalendarioasignatura();
                    programacionEspecificaListNewProgramacionEspecifica.setIdCalendarioasignatura(calendarioAsignatura);
                    programacionEspecificaListNewProgramacionEspecifica = em.merge(programacionEspecificaListNewProgramacionEspecifica);
                    if (oldIdCalendarioasignaturaOfProgramacionEspecificaListNewProgramacionEspecifica != null && !oldIdCalendarioasignaturaOfProgramacionEspecificaListNewProgramacionEspecifica.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfProgramacionEspecificaListNewProgramacionEspecifica.getProgramacionEspecificaList().remove(programacionEspecificaListNewProgramacionEspecifica);
                        oldIdCalendarioasignaturaOfProgramacionEspecificaListNewProgramacionEspecifica = em.merge(oldIdCalendarioasignaturaOfProgramacionEspecificaListNewProgramacionEspecifica);
                    }
                }
            }
            for (HistoricoAsesorasignatura historicoAsesorasignaturaListOldHistoricoAsesorasignatura : historicoAsesorasignaturaListOld) {
                if (!historicoAsesorasignaturaListNew.contains(historicoAsesorasignaturaListOldHistoricoAsesorasignatura)) {
                    historicoAsesorasignaturaListOldHistoricoAsesorasignatura.setIdCalendarioasignatura(null);
                    historicoAsesorasignaturaListOldHistoricoAsesorasignatura = em.merge(historicoAsesorasignaturaListOldHistoricoAsesorasignatura);
                }
            }
            for (HistoricoAsesorasignatura historicoAsesorasignaturaListNewHistoricoAsesorasignatura : historicoAsesorasignaturaListNew) {
                if (!historicoAsesorasignaturaListOld.contains(historicoAsesorasignaturaListNewHistoricoAsesorasignatura)) {
                    CalendarioAsignatura oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListNewHistoricoAsesorasignatura = historicoAsesorasignaturaListNewHistoricoAsesorasignatura.getIdCalendarioasignatura();
                    historicoAsesorasignaturaListNewHistoricoAsesorasignatura.setIdCalendarioasignatura(calendarioAsignatura);
                    historicoAsesorasignaturaListNewHistoricoAsesorasignatura = em.merge(historicoAsesorasignaturaListNewHistoricoAsesorasignatura);
                    if (oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListNewHistoricoAsesorasignatura != null && !oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListNewHistoricoAsesorasignatura.equals(calendarioAsignatura)) {
                        oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListNewHistoricoAsesorasignatura.getHistoricoAsesorasignaturaList().remove(historicoAsesorasignaturaListNewHistoricoAsesorasignatura);
                        oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListNewHistoricoAsesorasignatura = em.merge(oldIdCalendarioasignaturaOfHistoricoAsesorasignaturaListNewHistoricoAsesorasignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = calendarioAsignatura.getIdCalendarioasignatura();
                if (findCalendarioAsignatura(id) == null) {
                    throw new NonexistentEntityException("The calendarioAsignatura with id " + id + " no longer exists.");
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
            CalendarioAsignatura calendarioAsignatura;
            try {
                calendarioAsignatura = em.getReference(CalendarioAsignatura.class, id);
                calendarioAsignatura.getIdCalendarioasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendarioAsignatura with id " + id + " no longer exists.", enfe);
            }
            Horario idHorario = calendarioAsignatura.getIdHorario();
            if (idHorario != null) {
                idHorario.getCalendarioAsignaturaList().remove(calendarioAsignatura);
                idHorario = em.merge(idHorario);
            }
            CalendarioEscolar idCalendarioescolar = calendarioAsignatura.getIdCalendarioescolar();
            if (idCalendarioescolar != null) {
                idCalendarioescolar.getCalendarioAsignaturaList().remove(calendarioAsignatura);
                idCalendarioescolar = em.merge(idCalendarioescolar);
            }
            Asignatura idAsignatura = calendarioAsignatura.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignatura);
                idAsignatura = em.merge(idAsignatura);
            }
            List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoList = calendarioAsignatura.getAsignacionIntegradoragrupoList();
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListAsignacionIntegradoragrupo : asignacionIntegradoragrupoList) {
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo.setIdCalendarioasignatura(null);
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListAsignacionIntegradoragrupo);
            }
            List<Asesoria> asesoriaList = calendarioAsignatura.getAsesoriaList();
            for (Asesoria asesoriaListAsesoria : asesoriaList) {
                asesoriaListAsesoria.setIdCalendarioasignatura(null);
                asesoriaListAsesoria = em.merge(asesoriaListAsesoria);
            }
            List<HistoricoCalendarioasignatura> historicoCalendarioasignaturaList = calendarioAsignatura.getHistoricoCalendarioasignaturaList();
            for (HistoricoCalendarioasignatura historicoCalendarioasignaturaListHistoricoCalendarioasignatura : historicoCalendarioasignaturaList) {
                historicoCalendarioasignaturaListHistoricoCalendarioasignatura.setIdCalendarioasignatura(null);
                historicoCalendarioasignaturaListHistoricoCalendarioasignatura = em.merge(historicoCalendarioasignaturaListHistoricoCalendarioasignatura);
            }
            List<FechaExamen> fechaExamenList = calendarioAsignatura.getFechaExamenList();
            for (FechaExamen fechaExamenListFechaExamen : fechaExamenList) {
                fechaExamenListFechaExamen.setIdCalendarioasignatura(null);
                fechaExamenListFechaExamen = em.merge(fechaExamenListFechaExamen);
            }
            List<AsignaturaAsesor> asignaturaAsesorList = calendarioAsignatura.getAsignaturaAsesorList();
            for (AsignaturaAsesor asignaturaAsesorListAsignaturaAsesor : asignaturaAsesorList) {
                asignaturaAsesorListAsignaturaAsesor.setIdCalendarioasignatura(null);
                asignaturaAsesorListAsignaturaAsesor = em.merge(asignaturaAsesorListAsignaturaAsesor);
            }
            List<ProgramacionEspecifica> programacionEspecificaList = calendarioAsignatura.getProgramacionEspecificaList();
            for (ProgramacionEspecifica programacionEspecificaListProgramacionEspecifica : programacionEspecificaList) {
                programacionEspecificaListProgramacionEspecifica.setIdCalendarioasignatura(null);
                programacionEspecificaListProgramacionEspecifica = em.merge(programacionEspecificaListProgramacionEspecifica);
            }
            List<HistoricoAsesorasignatura> historicoAsesorasignaturaList = calendarioAsignatura.getHistoricoAsesorasignaturaList();
            for (HistoricoAsesorasignatura historicoAsesorasignaturaListHistoricoAsesorasignatura : historicoAsesorasignaturaList) {
                historicoAsesorasignaturaListHistoricoAsesorasignatura.setIdCalendarioasignatura(null);
                historicoAsesorasignaturaListHistoricoAsesorasignatura = em.merge(historicoAsesorasignaturaListHistoricoAsesorasignatura);
            }
            em.remove(calendarioAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CalendarioAsignatura> findCalendarioAsignaturaEntities() {
        return findCalendarioAsignaturaEntities(true, -1, -1);
    }

    public List<CalendarioAsignatura> findCalendarioAsignaturaEntities(int maxResults, int firstResult) {
        return findCalendarioAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<CalendarioAsignatura> findCalendarioAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CalendarioAsignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CalendarioAsignatura findCalendarioAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CalendarioAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CalendarioAsignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
