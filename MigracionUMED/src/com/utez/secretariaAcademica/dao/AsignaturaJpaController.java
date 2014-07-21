/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Planestudios;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.integracion.entity.TipoNivelasignatura;
import com.utez.integracion.entity.TipoAsignatura;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.secretariaAcademica.entity.Cuadernoprogramacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Asesoriaasignatura;
import com.utez.secretariaAcademica.entity.Bloqueasignatura;
import com.utez.integracion.entity.AlumnoAsignatura;
import com.utez.integracion.entity.AsignacionAsignaturaintegradora;
import com.utez.integracion.entity.ExamenExtemporaneo;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.BloqueAsignatura;
import com.utez.integracion.entity.ProgramacionAsignatura;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getCuadernoprogramacionList() == null) {
            asignatura.setCuadernoprogramacionList(new ArrayList<Cuadernoprogramacion>());
        }
        if (asignatura.getAsignaturaList() == null) {
            asignatura.setAsignaturaList(new ArrayList<Asignatura>());
        }
        if (asignatura.getAsesoriaasignaturaList() == null) {
            asignatura.setAsesoriaasignaturaList(new ArrayList<Asesoriaasignatura>());
        }
        if (asignatura.getBloqueasignaturaList() == null) {
            asignatura.setBloqueasignaturaList(new ArrayList<Bloqueasignatura>());
        }
        if (asignatura.getAsignaturaList2() == null) {
            asignatura.setAsignaturaList2(new ArrayList<Asignatura>());
        }
        if (asignatura.getAlumnoAsignaturaList() == null) {
            asignatura.setAlumnoAsignaturaList(new ArrayList<AlumnoAsignatura>());
        }
        if (asignatura.getAsignacionAsignaturaintegradoraList() == null) {
            asignatura.setAsignacionAsignaturaintegradoraList(new ArrayList<AsignacionAsignaturaintegradora>());
        }
        if (asignatura.getExamenExtemporaneoList() == null) {
            asignatura.setExamenExtemporaneoList(new ArrayList<ExamenExtemporaneo>());
        }
        if (asignatura.getActaList() == null) {
            asignatura.setActaList(new ArrayList<Acta>());
        }
        if (asignatura.getAsignacionAsignaturabancoList() == null) {
            asignatura.setAsignacionAsignaturabancoList(new ArrayList<AsignacionAsignaturabanco>());
        }
        if (asignatura.getCalendarioAsignaturaList() == null) {
            asignatura.setCalendarioAsignaturaList(new ArrayList<CalendarioAsignatura>());
        }
        if (asignatura.getBloqueAsignaturaList() == null) {
            asignatura.setBloqueAsignaturaList(new ArrayList<BloqueAsignatura>());
        }
        if (asignatura.getProgramacionAsignaturaList() == null) {
            asignatura.setProgramacionAsignaturaList(new ArrayList<ProgramacionAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planestudios rvoe = asignatura.getRvoe();
            if (rvoe != null) {
                rvoe = em.getReference(rvoe.getClass(), rvoe.getRvoe());
                asignatura.setRvoe(rvoe);
            }
            Asignatura seriadacon = asignatura.getSeriadacon();
            if (seriadacon != null) {
                seriadacon = em.getReference(seriadacon.getClass(), seriadacon.getIdasignatura());
                asignatura.setSeriadacon(seriadacon);
            }
            TipoNivelasignatura idTiponivelasignatura = asignatura.getIdTiponivelasignatura();
            if (idTiponivelasignatura != null) {
                idTiponivelasignatura = em.getReference(idTiponivelasignatura.getClass(), idTiponivelasignatura.getIdTiponivelasignatura());
                asignatura.setIdTiponivelasignatura(idTiponivelasignatura);
            }
            TipoAsignatura idTipoasignatura = asignatura.getIdTipoasignatura();
            if (idTipoasignatura != null) {
                idTipoasignatura = em.getReference(idTipoasignatura.getClass(), idTipoasignatura.getIdTipoasignatura());
                asignatura.setIdTipoasignatura(idTipoasignatura);
            }
            PlanEstudio idPlanestudio = asignatura.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio = em.getReference(idPlanestudio.getClass(), idPlanestudio.getIdPlanestudio());
                asignatura.setIdPlanestudio(idPlanestudio);
            }
            Asignatura idAsignaturaseriada = asignatura.getIdAsignaturaseriada();
            if (idAsignaturaseriada != null) {
                idAsignaturaseriada = em.getReference(idAsignaturaseriada.getClass(), idAsignaturaseriada.getIdasignatura());
                asignatura.setIdAsignaturaseriada(idAsignaturaseriada);
            }
            List<Cuadernoprogramacion> attachedCuadernoprogramacionList = new ArrayList<Cuadernoprogramacion>();
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacionToAttach : asignatura.getCuadernoprogramacionList()) {
                cuadernoprogramacionListCuadernoprogramacionToAttach = em.getReference(cuadernoprogramacionListCuadernoprogramacionToAttach.getClass(), cuadernoprogramacionListCuadernoprogramacionToAttach.getIdcuadernoprogramacion());
                attachedCuadernoprogramacionList.add(cuadernoprogramacionListCuadernoprogramacionToAttach);
            }
            asignatura.setCuadernoprogramacionList(attachedCuadernoprogramacionList);
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : asignatura.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            asignatura.setAsignaturaList(attachedAsignaturaList);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaList = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignaturaToAttach : asignatura.getAsesoriaasignaturaList()) {
                asesoriaasignaturaListAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaList.add(asesoriaasignaturaListAsesoriaasignaturaToAttach);
            }
            asignatura.setAsesoriaasignaturaList(attachedAsesoriaasignaturaList);
            List<Bloqueasignatura> attachedBloqueasignaturaList = new ArrayList<Bloqueasignatura>();
            for (Bloqueasignatura bloqueasignaturaListBloqueasignaturaToAttach : asignatura.getBloqueasignaturaList()) {
                bloqueasignaturaListBloqueasignaturaToAttach = em.getReference(bloqueasignaturaListBloqueasignaturaToAttach.getClass(), bloqueasignaturaListBloqueasignaturaToAttach.getIdbloqueasignatura());
                attachedBloqueasignaturaList.add(bloqueasignaturaListBloqueasignaturaToAttach);
            }
            asignatura.setBloqueasignaturaList(attachedBloqueasignaturaList);
            List<Asignatura> attachedAsignaturaList2 = new ArrayList<Asignatura>();
            for (Asignatura asignaturaList2AsignaturaToAttach : asignatura.getAsignaturaList2()) {
                asignaturaList2AsignaturaToAttach = em.getReference(asignaturaList2AsignaturaToAttach.getClass(), asignaturaList2AsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList2.add(asignaturaList2AsignaturaToAttach);
            }
            asignatura.setAsignaturaList2(attachedAsignaturaList2);
            List<AlumnoAsignatura> attachedAlumnoAsignaturaList = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignaturaToAttach : asignatura.getAlumnoAsignaturaList()) {
                alumnoAsignaturaListAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListAlumnoAsignaturaToAttach.getIdAlumnoasignatura());
                attachedAlumnoAsignaturaList.add(alumnoAsignaturaListAlumnoAsignaturaToAttach);
            }
            asignatura.setAlumnoAsignaturaList(attachedAlumnoAsignaturaList);
            List<AsignacionAsignaturaintegradora> attachedAsignacionAsignaturaintegradoraList = new ArrayList<AsignacionAsignaturaintegradora>();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach : asignatura.getAsignacionAsignaturaintegradoraList()) {
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach = em.getReference(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach.getClass(), asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach.getIdAsignacionasignaturaintegradora());
                attachedAsignacionAsignaturaintegradoraList.add(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach);
            }
            asignatura.setAsignacionAsignaturaintegradoraList(attachedAsignacionAsignaturaintegradoraList);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoList = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneoToAttach : asignatura.getExamenExtemporaneoList()) {
                examenExtemporaneoListExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoList.add(examenExtemporaneoListExamenExtemporaneoToAttach);
            }
            asignatura.setExamenExtemporaneoList(attachedExamenExtemporaneoList);
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : asignatura.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            asignatura.setActaList(attachedActaList);
            List<AsignacionAsignaturabanco> attachedAsignacionAsignaturabancoList = new ArrayList<AsignacionAsignaturabanco>();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach : asignatura.getAsignacionAsignaturabancoList()) {
                asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach = em.getReference(asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach.getClass(), asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach.getIdAsignacionasignaturabanco());
                attachedAsignacionAsignaturabancoList.add(asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach);
            }
            asignatura.setAsignacionAsignaturabancoList(attachedAsignacionAsignaturabancoList);
            List<CalendarioAsignatura> attachedCalendarioAsignaturaList = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignaturaToAttach : asignatura.getCalendarioAsignaturaList()) {
                calendarioAsignaturaListCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaList.add(calendarioAsignaturaListCalendarioAsignaturaToAttach);
            }
            asignatura.setCalendarioAsignaturaList(attachedCalendarioAsignaturaList);
            List<BloqueAsignatura> attachedBloqueAsignaturaList = new ArrayList<BloqueAsignatura>();
            for (BloqueAsignatura bloqueAsignaturaListBloqueAsignaturaToAttach : asignatura.getBloqueAsignaturaList()) {
                bloqueAsignaturaListBloqueAsignaturaToAttach = em.getReference(bloqueAsignaturaListBloqueAsignaturaToAttach.getClass(), bloqueAsignaturaListBloqueAsignaturaToAttach.getIdBloqueasignatura());
                attachedBloqueAsignaturaList.add(bloqueAsignaturaListBloqueAsignaturaToAttach);
            }
            asignatura.setBloqueAsignaturaList(attachedBloqueAsignaturaList);
            List<ProgramacionAsignatura> attachedProgramacionAsignaturaList = new ArrayList<ProgramacionAsignatura>();
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignaturaToAttach : asignatura.getProgramacionAsignaturaList()) {
                programacionAsignaturaListProgramacionAsignaturaToAttach = em.getReference(programacionAsignaturaListProgramacionAsignaturaToAttach.getClass(), programacionAsignaturaListProgramacionAsignaturaToAttach.getIdProgramacionasignatura());
                attachedProgramacionAsignaturaList.add(programacionAsignaturaListProgramacionAsignaturaToAttach);
            }
            asignatura.setProgramacionAsignaturaList(attachedProgramacionAsignaturaList);
            em.persist(asignatura);
            if (rvoe != null) {
                rvoe.getAsignaturaList().add(asignatura);
                rvoe = em.merge(rvoe);
            }
            if (seriadacon != null) {
                seriadacon.getAsignaturaList().add(asignatura);
                seriadacon = em.merge(seriadacon);
            }
            if (idTiponivelasignatura != null) {
                idTiponivelasignatura.getAsignaturaList().add(asignatura);
                idTiponivelasignatura = em.merge(idTiponivelasignatura);
            }
            if (idTipoasignatura != null) {
                idTipoasignatura.getAsignaturaList().add(asignatura);
                idTipoasignatura = em.merge(idTipoasignatura);
            }
            if (idPlanestudio != null) {
                idPlanestudio.getAsignaturaList().add(asignatura);
                idPlanestudio = em.merge(idPlanestudio);
            }
            if (idAsignaturaseriada != null) {
                idAsignaturaseriada.getAsignaturaList().add(asignatura);
                idAsignaturaseriada = em.merge(idAsignaturaseriada);
            }
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacion : asignatura.getCuadernoprogramacionList()) {
                Asignatura oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion = cuadernoprogramacionListCuadernoprogramacion.getIdasignatura();
                cuadernoprogramacionListCuadernoprogramacion.setIdasignatura(asignatura);
                cuadernoprogramacionListCuadernoprogramacion = em.merge(cuadernoprogramacionListCuadernoprogramacion);
                if (oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion != null) {
                    oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacionListCuadernoprogramacion);
                    oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion = em.merge(oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion);
                }
            }
            for (Asignatura asignaturaListAsignatura : asignatura.getAsignaturaList()) {
                asignaturaListAsignatura.getAsignaturaList().add(asignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asignatura.getAsesoriaasignaturaList()) {
                Asignatura oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura = asesoriaasignaturaListAsesoriaasignatura.getIdasignatura();
                asesoriaasignaturaListAsesoriaasignatura.setIdasignatura(asignatura);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
                if (oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura != null) {
                    oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListAsesoriaasignatura);
                    oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura = em.merge(oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura);
                }
            }
            for (Bloqueasignatura bloqueasignaturaListBloqueasignatura : asignatura.getBloqueasignaturaList()) {
                Asignatura oldIdasignaturaOfBloqueasignaturaListBloqueasignatura = bloqueasignaturaListBloqueasignatura.getIdasignatura();
                bloqueasignaturaListBloqueasignatura.setIdasignatura(asignatura);
                bloqueasignaturaListBloqueasignatura = em.merge(bloqueasignaturaListBloqueasignatura);
                if (oldIdasignaturaOfBloqueasignaturaListBloqueasignatura != null) {
                    oldIdasignaturaOfBloqueasignaturaListBloqueasignatura.getBloqueasignaturaList().remove(bloqueasignaturaListBloqueasignatura);
                    oldIdasignaturaOfBloqueasignaturaListBloqueasignatura = em.merge(oldIdasignaturaOfBloqueasignaturaListBloqueasignatura);
                }
            }
            for (Asignatura asignaturaList2Asignatura : asignatura.getAsignaturaList2()) {
                asignaturaList2Asignatura.getAsignaturaList().add(asignatura);
                asignaturaList2Asignatura = em.merge(asignaturaList2Asignatura);
            }
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : asignatura.getAlumnoAsignaturaList()) {
                Asignatura oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura = alumnoAsignaturaListAlumnoAsignatura.getIdAsignatura();
                alumnoAsignaturaListAlumnoAsignatura.setIdAsignatura(asignatura);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
                if (oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura != null) {
                    oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListAlumnoAsignatura);
                    oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura = em.merge(oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura);
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora : asignatura.getAsignacionAsignaturaintegradoraList()) {
                Asignatura oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.getIdAsignatura();
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.setIdAsignatura(asignatura);
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                if (oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora != null) {
                    oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                    oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : asignatura.getExamenExtemporaneoList()) {
                Asignatura oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo = examenExtemporaneoListExamenExtemporaneo.getIdAsignatura();
                examenExtemporaneoListExamenExtemporaneo.setIdAsignatura(asignatura);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
                if (oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo != null) {
                    oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListExamenExtemporaneo);
                    oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo = em.merge(oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo);
                }
            }
            for (Acta actaListActa : asignatura.getActaList()) {
                Asignatura oldIdAsignaturaOfActaListActa = actaListActa.getIdAsignatura();
                actaListActa.setIdAsignatura(asignatura);
                actaListActa = em.merge(actaListActa);
                if (oldIdAsignaturaOfActaListActa != null) {
                    oldIdAsignaturaOfActaListActa.getActaList().remove(actaListActa);
                    oldIdAsignaturaOfActaListActa = em.merge(oldIdAsignaturaOfActaListActa);
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabanco : asignatura.getAsignacionAsignaturabancoList()) {
                Asignatura oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco = asignacionAsignaturabancoListAsignacionAsignaturabanco.getIdAsignatura();
                asignacionAsignaturabancoListAsignacionAsignaturabanco.setIdAsignatura(asignatura);
                asignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListAsignacionAsignaturabanco);
                if (oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco != null) {
                    oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabancoListAsignacionAsignaturabanco);
                    oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : asignatura.getCalendarioAsignaturaList()) {
                Asignatura oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura = calendarioAsignaturaListCalendarioAsignatura.getIdAsignatura();
                calendarioAsignaturaListCalendarioAsignatura.setIdAsignatura(asignatura);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
                if (oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura != null) {
                    oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListCalendarioAsignatura);
                    oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura = em.merge(oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura);
                }
            }
            for (BloqueAsignatura bloqueAsignaturaListBloqueAsignatura : asignatura.getBloqueAsignaturaList()) {
                Asignatura oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura = bloqueAsignaturaListBloqueAsignatura.getIdAsignatura();
                bloqueAsignaturaListBloqueAsignatura.setIdAsignatura(asignatura);
                bloqueAsignaturaListBloqueAsignatura = em.merge(bloqueAsignaturaListBloqueAsignatura);
                if (oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura != null) {
                    oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura.getBloqueAsignaturaList().remove(bloqueAsignaturaListBloqueAsignatura);
                    oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura = em.merge(oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura);
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignatura : asignatura.getProgramacionAsignaturaList()) {
                Asignatura oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura = programacionAsignaturaListProgramacionAsignatura.getIdAsignatura();
                programacionAsignaturaListProgramacionAsignatura.setIdAsignatura(asignatura);
                programacionAsignaturaListProgramacionAsignatura = em.merge(programacionAsignaturaListProgramacionAsignatura);
                if (oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura != null) {
                    oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura.getProgramacionAsignaturaList().remove(programacionAsignaturaListProgramacionAsignatura);
                    oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura = em.merge(oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getIdasignatura());
            Planestudios rvoeOld = persistentAsignatura.getRvoe();
            Planestudios rvoeNew = asignatura.getRvoe();
            Asignatura seriadaconOld = persistentAsignatura.getSeriadacon();
            Asignatura seriadaconNew = asignatura.getSeriadacon();
            TipoNivelasignatura idTiponivelasignaturaOld = persistentAsignatura.getIdTiponivelasignatura();
            TipoNivelasignatura idTiponivelasignaturaNew = asignatura.getIdTiponivelasignatura();
            TipoAsignatura idTipoasignaturaOld = persistentAsignatura.getIdTipoasignatura();
            TipoAsignatura idTipoasignaturaNew = asignatura.getIdTipoasignatura();
            PlanEstudio idPlanestudioOld = persistentAsignatura.getIdPlanestudio();
            PlanEstudio idPlanestudioNew = asignatura.getIdPlanestudio();
            Asignatura idAsignaturaseriadaOld = persistentAsignatura.getIdAsignaturaseriada();
            Asignatura idAsignaturaseriadaNew = asignatura.getIdAsignaturaseriada();
            List<Cuadernoprogramacion> cuadernoprogramacionListOld = persistentAsignatura.getCuadernoprogramacionList();
            List<Cuadernoprogramacion> cuadernoprogramacionListNew = asignatura.getCuadernoprogramacionList();
            List<Asignatura> asignaturaListOld = persistentAsignatura.getAsignaturaList();
            List<Asignatura> asignaturaListNew = asignatura.getAsignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListOld = persistentAsignatura.getAsesoriaasignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListNew = asignatura.getAsesoriaasignaturaList();
            List<Bloqueasignatura> bloqueasignaturaListOld = persistentAsignatura.getBloqueasignaturaList();
            List<Bloqueasignatura> bloqueasignaturaListNew = asignatura.getBloqueasignaturaList();
            List<Asignatura> asignaturaList2Old = persistentAsignatura.getAsignaturaList2();
            List<Asignatura> asignaturaList2New = asignatura.getAsignaturaList2();
            List<AlumnoAsignatura> alumnoAsignaturaListOld = persistentAsignatura.getAlumnoAsignaturaList();
            List<AlumnoAsignatura> alumnoAsignaturaListNew = asignatura.getAlumnoAsignaturaList();
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraListOld = persistentAsignatura.getAsignacionAsignaturaintegradoraList();
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraListNew = asignatura.getAsignacionAsignaturaintegradoraList();
            List<ExamenExtemporaneo> examenExtemporaneoListOld = persistentAsignatura.getExamenExtemporaneoList();
            List<ExamenExtemporaneo> examenExtemporaneoListNew = asignatura.getExamenExtemporaneoList();
            List<Acta> actaListOld = persistentAsignatura.getActaList();
            List<Acta> actaListNew = asignatura.getActaList();
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoListOld = persistentAsignatura.getAsignacionAsignaturabancoList();
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoListNew = asignatura.getAsignacionAsignaturabancoList();
            List<CalendarioAsignatura> calendarioAsignaturaListOld = persistentAsignatura.getCalendarioAsignaturaList();
            List<CalendarioAsignatura> calendarioAsignaturaListNew = asignatura.getCalendarioAsignaturaList();
            List<BloqueAsignatura> bloqueAsignaturaListOld = persistentAsignatura.getBloqueAsignaturaList();
            List<BloqueAsignatura> bloqueAsignaturaListNew = asignatura.getBloqueAsignaturaList();
            List<ProgramacionAsignatura> programacionAsignaturaListOld = persistentAsignatura.getProgramacionAsignaturaList();
            List<ProgramacionAsignatura> programacionAsignaturaListNew = asignatura.getProgramacionAsignaturaList();
            if (rvoeNew != null) {
                rvoeNew = em.getReference(rvoeNew.getClass(), rvoeNew.getRvoe());
                asignatura.setRvoe(rvoeNew);
            }
            if (seriadaconNew != null) {
                seriadaconNew = em.getReference(seriadaconNew.getClass(), seriadaconNew.getIdasignatura());
                asignatura.setSeriadacon(seriadaconNew);
            }
            if (idTiponivelasignaturaNew != null) {
                idTiponivelasignaturaNew = em.getReference(idTiponivelasignaturaNew.getClass(), idTiponivelasignaturaNew.getIdTiponivelasignatura());
                asignatura.setIdTiponivelasignatura(idTiponivelasignaturaNew);
            }
            if (idTipoasignaturaNew != null) {
                idTipoasignaturaNew = em.getReference(idTipoasignaturaNew.getClass(), idTipoasignaturaNew.getIdTipoasignatura());
                asignatura.setIdTipoasignatura(idTipoasignaturaNew);
            }
            if (idPlanestudioNew != null) {
                idPlanestudioNew = em.getReference(idPlanestudioNew.getClass(), idPlanestudioNew.getIdPlanestudio());
                asignatura.setIdPlanestudio(idPlanestudioNew);
            }
            if (idAsignaturaseriadaNew != null) {
                idAsignaturaseriadaNew = em.getReference(idAsignaturaseriadaNew.getClass(), idAsignaturaseriadaNew.getIdasignatura());
                asignatura.setIdAsignaturaseriada(idAsignaturaseriadaNew);
            }
            List<Cuadernoprogramacion> attachedCuadernoprogramacionListNew = new ArrayList<Cuadernoprogramacion>();
            for (Cuadernoprogramacion cuadernoprogramacionListNewCuadernoprogramacionToAttach : cuadernoprogramacionListNew) {
                cuadernoprogramacionListNewCuadernoprogramacionToAttach = em.getReference(cuadernoprogramacionListNewCuadernoprogramacionToAttach.getClass(), cuadernoprogramacionListNewCuadernoprogramacionToAttach.getIdcuadernoprogramacion());
                attachedCuadernoprogramacionListNew.add(cuadernoprogramacionListNewCuadernoprogramacionToAttach);
            }
            cuadernoprogramacionListNew = attachedCuadernoprogramacionListNew;
            asignatura.setCuadernoprogramacionList(cuadernoprogramacionListNew);
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            asignatura.setAsignaturaList(asignaturaListNew);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaListNew = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignaturaToAttach : asesoriaasignaturaListNew) {
                asesoriaasignaturaListNewAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaListNew.add(asesoriaasignaturaListNewAsesoriaasignaturaToAttach);
            }
            asesoriaasignaturaListNew = attachedAsesoriaasignaturaListNew;
            asignatura.setAsesoriaasignaturaList(asesoriaasignaturaListNew);
            List<Bloqueasignatura> attachedBloqueasignaturaListNew = new ArrayList<Bloqueasignatura>();
            for (Bloqueasignatura bloqueasignaturaListNewBloqueasignaturaToAttach : bloqueasignaturaListNew) {
                bloqueasignaturaListNewBloqueasignaturaToAttach = em.getReference(bloqueasignaturaListNewBloqueasignaturaToAttach.getClass(), bloqueasignaturaListNewBloqueasignaturaToAttach.getIdbloqueasignatura());
                attachedBloqueasignaturaListNew.add(bloqueasignaturaListNewBloqueasignaturaToAttach);
            }
            bloqueasignaturaListNew = attachedBloqueasignaturaListNew;
            asignatura.setBloqueasignaturaList(bloqueasignaturaListNew);
            List<Asignatura> attachedAsignaturaList2New = new ArrayList<Asignatura>();
            for (Asignatura asignaturaList2NewAsignaturaToAttach : asignaturaList2New) {
                asignaturaList2NewAsignaturaToAttach = em.getReference(asignaturaList2NewAsignaturaToAttach.getClass(), asignaturaList2NewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList2New.add(asignaturaList2NewAsignaturaToAttach);
            }
            asignaturaList2New = attachedAsignaturaList2New;
            asignatura.setAsignaturaList2(asignaturaList2New);
            List<AlumnoAsignatura> attachedAlumnoAsignaturaListNew = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignaturaToAttach : alumnoAsignaturaListNew) {
                alumnoAsignaturaListNewAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getIdAlumnoasignatura());
                attachedAlumnoAsignaturaListNew.add(alumnoAsignaturaListNewAlumnoAsignaturaToAttach);
            }
            alumnoAsignaturaListNew = attachedAlumnoAsignaturaListNew;
            asignatura.setAlumnoAsignaturaList(alumnoAsignaturaListNew);
            List<AsignacionAsignaturaintegradora> attachedAsignacionAsignaturaintegradoraListNew = new ArrayList<AsignacionAsignaturaintegradora>();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach : asignacionAsignaturaintegradoraListNew) {
                asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach = em.getReference(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach.getClass(), asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach.getIdAsignacionasignaturaintegradora());
                attachedAsignacionAsignaturaintegradoraListNew.add(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach);
            }
            asignacionAsignaturaintegradoraListNew = attachedAsignacionAsignaturaintegradoraListNew;
            asignatura.setAsignacionAsignaturaintegradoraList(asignacionAsignaturaintegradoraListNew);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoListNew = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneoToAttach : examenExtemporaneoListNew) {
                examenExtemporaneoListNewExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListNewExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListNewExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoListNew.add(examenExtemporaneoListNewExamenExtemporaneoToAttach);
            }
            examenExtemporaneoListNew = attachedExamenExtemporaneoListNew;
            asignatura.setExamenExtemporaneoList(examenExtemporaneoListNew);
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            asignatura.setActaList(actaListNew);
            List<AsignacionAsignaturabanco> attachedAsignacionAsignaturabancoListNew = new ArrayList<AsignacionAsignaturabanco>();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach : asignacionAsignaturabancoListNew) {
                asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach = em.getReference(asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach.getClass(), asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach.getIdAsignacionasignaturabanco());
                attachedAsignacionAsignaturabancoListNew.add(asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach);
            }
            asignacionAsignaturabancoListNew = attachedAsignacionAsignaturabancoListNew;
            asignatura.setAsignacionAsignaturabancoList(asignacionAsignaturabancoListNew);
            List<CalendarioAsignatura> attachedCalendarioAsignaturaListNew = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignaturaToAttach : calendarioAsignaturaListNew) {
                calendarioAsignaturaListNewCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaListNew.add(calendarioAsignaturaListNewCalendarioAsignaturaToAttach);
            }
            calendarioAsignaturaListNew = attachedCalendarioAsignaturaListNew;
            asignatura.setCalendarioAsignaturaList(calendarioAsignaturaListNew);
            List<BloqueAsignatura> attachedBloqueAsignaturaListNew = new ArrayList<BloqueAsignatura>();
            for (BloqueAsignatura bloqueAsignaturaListNewBloqueAsignaturaToAttach : bloqueAsignaturaListNew) {
                bloqueAsignaturaListNewBloqueAsignaturaToAttach = em.getReference(bloqueAsignaturaListNewBloqueAsignaturaToAttach.getClass(), bloqueAsignaturaListNewBloqueAsignaturaToAttach.getIdBloqueasignatura());
                attachedBloqueAsignaturaListNew.add(bloqueAsignaturaListNewBloqueAsignaturaToAttach);
            }
            bloqueAsignaturaListNew = attachedBloqueAsignaturaListNew;
            asignatura.setBloqueAsignaturaList(bloqueAsignaturaListNew);
            List<ProgramacionAsignatura> attachedProgramacionAsignaturaListNew = new ArrayList<ProgramacionAsignatura>();
            for (ProgramacionAsignatura programacionAsignaturaListNewProgramacionAsignaturaToAttach : programacionAsignaturaListNew) {
                programacionAsignaturaListNewProgramacionAsignaturaToAttach = em.getReference(programacionAsignaturaListNewProgramacionAsignaturaToAttach.getClass(), programacionAsignaturaListNewProgramacionAsignaturaToAttach.getIdProgramacionasignatura());
                attachedProgramacionAsignaturaListNew.add(programacionAsignaturaListNewProgramacionAsignaturaToAttach);
            }
            programacionAsignaturaListNew = attachedProgramacionAsignaturaListNew;
            asignatura.setProgramacionAsignaturaList(programacionAsignaturaListNew);
            asignatura = em.merge(asignatura);
            if (rvoeOld != null && !rvoeOld.equals(rvoeNew)) {
                rvoeOld.getAsignaturaList().remove(asignatura);
                rvoeOld = em.merge(rvoeOld);
            }
            if (rvoeNew != null && !rvoeNew.equals(rvoeOld)) {
                rvoeNew.getAsignaturaList().add(asignatura);
                rvoeNew = em.merge(rvoeNew);
            }
            if (seriadaconOld != null && !seriadaconOld.equals(seriadaconNew)) {
                seriadaconOld.getAsignaturaList().remove(asignatura);
                seriadaconOld = em.merge(seriadaconOld);
            }
            if (seriadaconNew != null && !seriadaconNew.equals(seriadaconOld)) {
                seriadaconNew.getAsignaturaList().add(asignatura);
                seriadaconNew = em.merge(seriadaconNew);
            }
            if (idTiponivelasignaturaOld != null && !idTiponivelasignaturaOld.equals(idTiponivelasignaturaNew)) {
                idTiponivelasignaturaOld.getAsignaturaList().remove(asignatura);
                idTiponivelasignaturaOld = em.merge(idTiponivelasignaturaOld);
            }
            if (idTiponivelasignaturaNew != null && !idTiponivelasignaturaNew.equals(idTiponivelasignaturaOld)) {
                idTiponivelasignaturaNew.getAsignaturaList().add(asignatura);
                idTiponivelasignaturaNew = em.merge(idTiponivelasignaturaNew);
            }
            if (idTipoasignaturaOld != null && !idTipoasignaturaOld.equals(idTipoasignaturaNew)) {
                idTipoasignaturaOld.getAsignaturaList().remove(asignatura);
                idTipoasignaturaOld = em.merge(idTipoasignaturaOld);
            }
            if (idTipoasignaturaNew != null && !idTipoasignaturaNew.equals(idTipoasignaturaOld)) {
                idTipoasignaturaNew.getAsignaturaList().add(asignatura);
                idTipoasignaturaNew = em.merge(idTipoasignaturaNew);
            }
            if (idPlanestudioOld != null && !idPlanestudioOld.equals(idPlanestudioNew)) {
                idPlanestudioOld.getAsignaturaList().remove(asignatura);
                idPlanestudioOld = em.merge(idPlanestudioOld);
            }
            if (idPlanestudioNew != null && !idPlanestudioNew.equals(idPlanestudioOld)) {
                idPlanestudioNew.getAsignaturaList().add(asignatura);
                idPlanestudioNew = em.merge(idPlanestudioNew);
            }
            if (idAsignaturaseriadaOld != null && !idAsignaturaseriadaOld.equals(idAsignaturaseriadaNew)) {
                idAsignaturaseriadaOld.getAsignaturaList().remove(asignatura);
                idAsignaturaseriadaOld = em.merge(idAsignaturaseriadaOld);
            }
            if (idAsignaturaseriadaNew != null && !idAsignaturaseriadaNew.equals(idAsignaturaseriadaOld)) {
                idAsignaturaseriadaNew.getAsignaturaList().add(asignatura);
                idAsignaturaseriadaNew = em.merge(idAsignaturaseriadaNew);
            }
            for (Cuadernoprogramacion cuadernoprogramacionListOldCuadernoprogramacion : cuadernoprogramacionListOld) {
                if (!cuadernoprogramacionListNew.contains(cuadernoprogramacionListOldCuadernoprogramacion)) {
                    cuadernoprogramacionListOldCuadernoprogramacion.setIdasignatura(null);
                    cuadernoprogramacionListOldCuadernoprogramacion = em.merge(cuadernoprogramacionListOldCuadernoprogramacion);
                }
            }
            for (Cuadernoprogramacion cuadernoprogramacionListNewCuadernoprogramacion : cuadernoprogramacionListNew) {
                if (!cuadernoprogramacionListOld.contains(cuadernoprogramacionListNewCuadernoprogramacion)) {
                    Asignatura oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion = cuadernoprogramacionListNewCuadernoprogramacion.getIdasignatura();
                    cuadernoprogramacionListNewCuadernoprogramacion.setIdasignatura(asignatura);
                    cuadernoprogramacionListNewCuadernoprogramacion = em.merge(cuadernoprogramacionListNewCuadernoprogramacion);
                    if (oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion != null && !oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion.equals(asignatura)) {
                        oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacionListNewCuadernoprogramacion);
                        oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion = em.merge(oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion);
                    }
                }
            }
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.getAsignaturaList().remove(asignatura);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    asignaturaListNewAsignatura.getAsignaturaList().add(asignatura);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListOldAsesoriaasignatura : asesoriaasignaturaListOld) {
                if (!asesoriaasignaturaListNew.contains(asesoriaasignaturaListOldAsesoriaasignatura)) {
                    asesoriaasignaturaListOldAsesoriaasignatura.setIdasignatura(null);
                    asesoriaasignaturaListOldAsesoriaasignatura = em.merge(asesoriaasignaturaListOldAsesoriaasignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignatura : asesoriaasignaturaListNew) {
                if (!asesoriaasignaturaListOld.contains(asesoriaasignaturaListNewAsesoriaasignatura)) {
                    Asignatura oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura = asesoriaasignaturaListNewAsesoriaasignatura.getIdasignatura();
                    asesoriaasignaturaListNewAsesoriaasignatura.setIdasignatura(asignatura);
                    asesoriaasignaturaListNewAsesoriaasignatura = em.merge(asesoriaasignaturaListNewAsesoriaasignatura);
                    if (oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura != null && !oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura.equals(asignatura)) {
                        oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListNewAsesoriaasignatura);
                        oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura = em.merge(oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura);
                    }
                }
            }
            for (Bloqueasignatura bloqueasignaturaListOldBloqueasignatura : bloqueasignaturaListOld) {
                if (!bloqueasignaturaListNew.contains(bloqueasignaturaListOldBloqueasignatura)) {
                    bloqueasignaturaListOldBloqueasignatura.setIdasignatura(null);
                    bloqueasignaturaListOldBloqueasignatura = em.merge(bloqueasignaturaListOldBloqueasignatura);
                }
            }
            for (Bloqueasignatura bloqueasignaturaListNewBloqueasignatura : bloqueasignaturaListNew) {
                if (!bloqueasignaturaListOld.contains(bloqueasignaturaListNewBloqueasignatura)) {
                    Asignatura oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura = bloqueasignaturaListNewBloqueasignatura.getIdasignatura();
                    bloqueasignaturaListNewBloqueasignatura.setIdasignatura(asignatura);
                    bloqueasignaturaListNewBloqueasignatura = em.merge(bloqueasignaturaListNewBloqueasignatura);
                    if (oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura != null && !oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura.equals(asignatura)) {
                        oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura.getBloqueasignaturaList().remove(bloqueasignaturaListNewBloqueasignatura);
                        oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura = em.merge(oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura);
                    }
                }
            }
            for (Asignatura asignaturaList2OldAsignatura : asignaturaList2Old) {
                if (!asignaturaList2New.contains(asignaturaList2OldAsignatura)) {
                    asignaturaList2OldAsignatura.getAsignaturaList().remove(asignatura);
                    asignaturaList2OldAsignatura = em.merge(asignaturaList2OldAsignatura);
                }
            }
            for (Asignatura asignaturaList2NewAsignatura : asignaturaList2New) {
                if (!asignaturaList2Old.contains(asignaturaList2NewAsignatura)) {
                    asignaturaList2NewAsignatura.getAsignaturaList().add(asignatura);
                    asignaturaList2NewAsignatura = em.merge(asignaturaList2NewAsignatura);
                }
            }
            for (AlumnoAsignatura alumnoAsignaturaListOldAlumnoAsignatura : alumnoAsignaturaListOld) {
                if (!alumnoAsignaturaListNew.contains(alumnoAsignaturaListOldAlumnoAsignatura)) {
                    alumnoAsignaturaListOldAlumnoAsignatura.setIdAsignatura(null);
                    alumnoAsignaturaListOldAlumnoAsignatura = em.merge(alumnoAsignaturaListOldAlumnoAsignatura);
                }
            }
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignatura : alumnoAsignaturaListNew) {
                if (!alumnoAsignaturaListOld.contains(alumnoAsignaturaListNewAlumnoAsignatura)) {
                    Asignatura oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura = alumnoAsignaturaListNewAlumnoAsignatura.getIdAsignatura();
                    alumnoAsignaturaListNewAlumnoAsignatura.setIdAsignatura(asignatura);
                    alumnoAsignaturaListNewAlumnoAsignatura = em.merge(alumnoAsignaturaListNewAlumnoAsignatura);
                    if (oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura != null && !oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListNewAlumnoAsignatura);
                        oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura = em.merge(oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura);
                    }
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraListOld) {
                if (!asignacionAsignaturaintegradoraListNew.contains(asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora)) {
                    asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora.setIdAsignatura(null);
                    asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora);
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraListNew) {
                if (!asignacionAsignaturaintegradoraListOld.contains(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora)) {
                    Asignatura oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.getIdAsignatura();
                    asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.setIdAsignatura(asignatura);
                    asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                    if (oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora != null && !oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.equals(asignatura)) {
                        oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                        oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = em.merge(oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                    }
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListOldExamenExtemporaneo : examenExtemporaneoListOld) {
                if (!examenExtemporaneoListNew.contains(examenExtemporaneoListOldExamenExtemporaneo)) {
                    examenExtemporaneoListOldExamenExtemporaneo.setIdAsignatura(null);
                    examenExtemporaneoListOldExamenExtemporaneo = em.merge(examenExtemporaneoListOldExamenExtemporaneo);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneo : examenExtemporaneoListNew) {
                if (!examenExtemporaneoListOld.contains(examenExtemporaneoListNewExamenExtemporaneo)) {
                    Asignatura oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo = examenExtemporaneoListNewExamenExtemporaneo.getIdAsignatura();
                    examenExtemporaneoListNewExamenExtemporaneo.setIdAsignatura(asignatura);
                    examenExtemporaneoListNewExamenExtemporaneo = em.merge(examenExtemporaneoListNewExamenExtemporaneo);
                    if (oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo != null && !oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo.equals(asignatura)) {
                        oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListNewExamenExtemporaneo);
                        oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo = em.merge(oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo);
                    }
                }
            }
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setIdAsignatura(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    Asignatura oldIdAsignaturaOfActaListNewActa = actaListNewActa.getIdAsignatura();
                    actaListNewActa.setIdAsignatura(asignatura);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldIdAsignaturaOfActaListNewActa != null && !oldIdAsignaturaOfActaListNewActa.equals(asignatura)) {
                        oldIdAsignaturaOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldIdAsignaturaOfActaListNewActa = em.merge(oldIdAsignaturaOfActaListNewActa);
                    }
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListOldAsignacionAsignaturabanco : asignacionAsignaturabancoListOld) {
                if (!asignacionAsignaturabancoListNew.contains(asignacionAsignaturabancoListOldAsignacionAsignaturabanco)) {
                    asignacionAsignaturabancoListOldAsignacionAsignaturabanco.setIdAsignatura(null);
                    asignacionAsignaturabancoListOldAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListOldAsignacionAsignaturabanco);
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListNewAsignacionAsignaturabanco : asignacionAsignaturabancoListNew) {
                if (!asignacionAsignaturabancoListOld.contains(asignacionAsignaturabancoListNewAsignacionAsignaturabanco)) {
                    Asignatura oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco = asignacionAsignaturabancoListNewAsignacionAsignaturabanco.getIdAsignatura();
                    asignacionAsignaturabancoListNewAsignacionAsignaturabanco.setIdAsignatura(asignatura);
                    asignacionAsignaturabancoListNewAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                    if (oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco != null && !oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco.equals(asignatura)) {
                        oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                        oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco = em.merge(oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                    }
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListOldCalendarioAsignatura : calendarioAsignaturaListOld) {
                if (!calendarioAsignaturaListNew.contains(calendarioAsignaturaListOldCalendarioAsignatura)) {
                    calendarioAsignaturaListOldCalendarioAsignatura.setIdAsignatura(null);
                    calendarioAsignaturaListOldCalendarioAsignatura = em.merge(calendarioAsignaturaListOldCalendarioAsignatura);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignatura : calendarioAsignaturaListNew) {
                if (!calendarioAsignaturaListOld.contains(calendarioAsignaturaListNewCalendarioAsignatura)) {
                    Asignatura oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura = calendarioAsignaturaListNewCalendarioAsignatura.getIdAsignatura();
                    calendarioAsignaturaListNewCalendarioAsignatura.setIdAsignatura(asignatura);
                    calendarioAsignaturaListNewCalendarioAsignatura = em.merge(calendarioAsignaturaListNewCalendarioAsignatura);
                    if (oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura != null && !oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListNewCalendarioAsignatura);
                        oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura = em.merge(oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura);
                    }
                }
            }
            for (BloqueAsignatura bloqueAsignaturaListOldBloqueAsignatura : bloqueAsignaturaListOld) {
                if (!bloqueAsignaturaListNew.contains(bloqueAsignaturaListOldBloqueAsignatura)) {
                    bloqueAsignaturaListOldBloqueAsignatura.setIdAsignatura(null);
                    bloqueAsignaturaListOldBloqueAsignatura = em.merge(bloqueAsignaturaListOldBloqueAsignatura);
                }
            }
            for (BloqueAsignatura bloqueAsignaturaListNewBloqueAsignatura : bloqueAsignaturaListNew) {
                if (!bloqueAsignaturaListOld.contains(bloqueAsignaturaListNewBloqueAsignatura)) {
                    Asignatura oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura = bloqueAsignaturaListNewBloqueAsignatura.getIdAsignatura();
                    bloqueAsignaturaListNewBloqueAsignatura.setIdAsignatura(asignatura);
                    bloqueAsignaturaListNewBloqueAsignatura = em.merge(bloqueAsignaturaListNewBloqueAsignatura);
                    if (oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura != null && !oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura.getBloqueAsignaturaList().remove(bloqueAsignaturaListNewBloqueAsignatura);
                        oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura = em.merge(oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura);
                    }
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListOldProgramacionAsignatura : programacionAsignaturaListOld) {
                if (!programacionAsignaturaListNew.contains(programacionAsignaturaListOldProgramacionAsignatura)) {
                    programacionAsignaturaListOldProgramacionAsignatura.setIdAsignatura(null);
                    programacionAsignaturaListOldProgramacionAsignatura = em.merge(programacionAsignaturaListOldProgramacionAsignatura);
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListNewProgramacionAsignatura : programacionAsignaturaListNew) {
                if (!programacionAsignaturaListOld.contains(programacionAsignaturaListNewProgramacionAsignatura)) {
                    Asignatura oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura = programacionAsignaturaListNewProgramacionAsignatura.getIdAsignatura();
                    programacionAsignaturaListNewProgramacionAsignatura.setIdAsignatura(asignatura);
                    programacionAsignaturaListNewProgramacionAsignatura = em.merge(programacionAsignaturaListNewProgramacionAsignatura);
                    if (oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura != null && !oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura.getProgramacionAsignaturaList().remove(programacionAsignaturaListNewProgramacionAsignatura);
                        oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura = em.merge(oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getIdasignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
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
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getIdasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            Planestudios rvoe = asignatura.getRvoe();
            if (rvoe != null) {
                rvoe.getAsignaturaList().remove(asignatura);
                rvoe = em.merge(rvoe);
            }
            Asignatura seriadacon = asignatura.getSeriadacon();
            if (seriadacon != null) {
                seriadacon.getAsignaturaList().remove(asignatura);
                seriadacon = em.merge(seriadacon);
            }
            TipoNivelasignatura idTiponivelasignatura = asignatura.getIdTiponivelasignatura();
            if (idTiponivelasignatura != null) {
                idTiponivelasignatura.getAsignaturaList().remove(asignatura);
                idTiponivelasignatura = em.merge(idTiponivelasignatura);
            }
            TipoAsignatura idTipoasignatura = asignatura.getIdTipoasignatura();
            if (idTipoasignatura != null) {
                idTipoasignatura.getAsignaturaList().remove(asignatura);
                idTipoasignatura = em.merge(idTipoasignatura);
            }
            PlanEstudio idPlanestudio = asignatura.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio.getAsignaturaList().remove(asignatura);
                idPlanestudio = em.merge(idPlanestudio);
            }
            Asignatura idAsignaturaseriada = asignatura.getIdAsignaturaseriada();
            if (idAsignaturaseriada != null) {
                idAsignaturaseriada.getAsignaturaList().remove(asignatura);
                idAsignaturaseriada = em.merge(idAsignaturaseriada);
            }
            List<Cuadernoprogramacion> cuadernoprogramacionList = asignatura.getCuadernoprogramacionList();
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacion : cuadernoprogramacionList) {
                cuadernoprogramacionListCuadernoprogramacion.setIdasignatura(null);
                cuadernoprogramacionListCuadernoprogramacion = em.merge(cuadernoprogramacionListCuadernoprogramacion);
            }
            List<Asignatura> asignaturaList = asignatura.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.getAsignaturaList().remove(asignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            List<Asesoriaasignatura> asesoriaasignaturaList = asignatura.getAsesoriaasignaturaList();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesoriaasignaturaList) {
                asesoriaasignaturaListAsesoriaasignatura.setIdasignatura(null);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
            }
            List<Bloqueasignatura> bloqueasignaturaList = asignatura.getBloqueasignaturaList();
            for (Bloqueasignatura bloqueasignaturaListBloqueasignatura : bloqueasignaturaList) {
                bloqueasignaturaListBloqueasignatura.setIdasignatura(null);
                bloqueasignaturaListBloqueasignatura = em.merge(bloqueasignaturaListBloqueasignatura);
            }
            List<Asignatura> asignaturaList2 = asignatura.getAsignaturaList2();
            for (Asignatura asignaturaList2Asignatura : asignaturaList2) {
                asignaturaList2Asignatura.getAsignaturaList().remove(asignatura);
                asignaturaList2Asignatura = em.merge(asignaturaList2Asignatura);
            }
            List<AlumnoAsignatura> alumnoAsignaturaList = asignatura.getAlumnoAsignaturaList();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : alumnoAsignaturaList) {
                alumnoAsignaturaListAlumnoAsignatura.setIdAsignatura(null);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
            }
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList = asignatura.getAsignacionAsignaturaintegradoraList();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraList) {
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.setIdAsignatura(null);
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
            }
            List<ExamenExtemporaneo> examenExtemporaneoList = asignatura.getExamenExtemporaneoList();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : examenExtemporaneoList) {
                examenExtemporaneoListExamenExtemporaneo.setIdAsignatura(null);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
            }
            List<Acta> actaList = asignatura.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setIdAsignatura(null);
                actaListActa = em.merge(actaListActa);
            }
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoList = asignatura.getAsignacionAsignaturabancoList();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabanco : asignacionAsignaturabancoList) {
                asignacionAsignaturabancoListAsignacionAsignaturabanco.setIdAsignatura(null);
                asignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListAsignacionAsignaturabanco);
            }
            List<CalendarioAsignatura> calendarioAsignaturaList = asignatura.getCalendarioAsignaturaList();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : calendarioAsignaturaList) {
                calendarioAsignaturaListCalendarioAsignatura.setIdAsignatura(null);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
            }
            List<BloqueAsignatura> bloqueAsignaturaList = asignatura.getBloqueAsignaturaList();
            for (BloqueAsignatura bloqueAsignaturaListBloqueAsignatura : bloqueAsignaturaList) {
                bloqueAsignaturaListBloqueAsignatura.setIdAsignatura(null);
                bloqueAsignaturaListBloqueAsignatura = em.merge(bloqueAsignaturaListBloqueAsignatura);
            }
            List<ProgramacionAsignatura> programacionAsignaturaList = asignatura.getProgramacionAsignaturaList();
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignatura : programacionAsignaturaList) {
                programacionAsignaturaListProgramacionAsignatura.setIdAsignatura(null);
                programacionAsignaturaListProgramacionAsignatura = em.merge(programacionAsignaturaListProgramacionAsignatura);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
