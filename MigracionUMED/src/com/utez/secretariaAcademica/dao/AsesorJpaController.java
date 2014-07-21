/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Recursohumano;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.secretariaAcademica.entity.Planestudios;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Asesoriaasignatura;
import com.utez.integracion.entity.ProgramacionOpcionc;
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import com.utez.integracion.entity.ExamenExtemporaneo;
import com.utez.integracion.entity.AsignacionAutorintegradora;
import com.utez.integracion.entity.AsesorTitulartitulacion;
import com.utez.integracion.entity.SinodoExamen;
import com.utez.integracion.entity.AsignacionAutorbanco;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.integracion.entity.AsignaturaAsesor;
import com.utez.integracion.entity.ExamenIndividual;
import com.utez.integracion.entity.GrupoExamenextemporaneo;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Asesor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesorJpaController implements Serializable {

    public AsesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesor asesor) {
        if (asesor.getPlanestudiosList() == null) {
            asesor.setPlanestudiosList(new ArrayList<Planestudios>());
        }
        if (asesor.getAsesoriaasignaturaList() == null) {
            asesor.setAsesoriaasignaturaList(new ArrayList<Asesoriaasignatura>());
        }
        if (asesor.getProgramacionOpcioncList() == null) {
            asesor.setProgramacionOpcioncList(new ArrayList<ProgramacionOpcionc>());
        }
        if (asesor.getEsquemaAlumnoasignaturaList() == null) {
            asesor.setEsquemaAlumnoasignaturaList(new ArrayList<EsquemaAlumnoasignatura>());
        }
        if (asesor.getExamenExtemporaneoList() == null) {
            asesor.setExamenExtemporaneoList(new ArrayList<ExamenExtemporaneo>());
        }
        if (asesor.getAsignacionAutorintegradoraList() == null) {
            asesor.setAsignacionAutorintegradoraList(new ArrayList<AsignacionAutorintegradora>());
        }
        if (asesor.getAsesorTitulartitulacionList() == null) {
            asesor.setAsesorTitulartitulacionList(new ArrayList<AsesorTitulartitulacion>());
        }
        if (asesor.getSinodoExamenList() == null) {
            asesor.setSinodoExamenList(new ArrayList<SinodoExamen>());
        }
        if (asesor.getAsignacionAutorbancoList() == null) {
            asesor.setAsignacionAutorbancoList(new ArrayList<AsignacionAutorbanco>());
        }
        if (asesor.getGrupoInduccionList() == null) {
            asesor.setGrupoInduccionList(new ArrayList<GrupoInduccion>());
        }
        if (asesor.getAsignaturaAsesorList() == null) {
            asesor.setAsignaturaAsesorList(new ArrayList<AsignaturaAsesor>());
        }
        if (asesor.getExamenIndividualList() == null) {
            asesor.setExamenIndividualList(new ArrayList<ExamenIndividual>());
        }
        if (asesor.getGrupoExamenextemporaneoList() == null) {
            asesor.setGrupoExamenextemporaneoList(new ArrayList<GrupoExamenextemporaneo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recursohumano idrecursohumano = asesor.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano = em.getReference(idrecursohumano.getClass(), idrecursohumano.getIdrecursohumano());
                asesor.setIdrecursohumano(idrecursohumano);
            }
            RecursoHumano idRecursohumano = asesor.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                asesor.setIdRecursohumano(idRecursohumano);
            }
            List<Planestudios> attachedPlanestudiosList = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListPlanestudiosToAttach : asesor.getPlanestudiosList()) {
                planestudiosListPlanestudiosToAttach = em.getReference(planestudiosListPlanestudiosToAttach.getClass(), planestudiosListPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosList.add(planestudiosListPlanestudiosToAttach);
            }
            asesor.setPlanestudiosList(attachedPlanestudiosList);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaList = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignaturaToAttach : asesor.getAsesoriaasignaturaList()) {
                asesoriaasignaturaListAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaList.add(asesoriaasignaturaListAsesoriaasignaturaToAttach);
            }
            asesor.setAsesoriaasignaturaList(attachedAsesoriaasignaturaList);
            List<ProgramacionOpcionc> attachedProgramacionOpcioncList = new ArrayList<ProgramacionOpcionc>();
            for (ProgramacionOpcionc programacionOpcioncListProgramacionOpcioncToAttach : asesor.getProgramacionOpcioncList()) {
                programacionOpcioncListProgramacionOpcioncToAttach = em.getReference(programacionOpcioncListProgramacionOpcioncToAttach.getClass(), programacionOpcioncListProgramacionOpcioncToAttach.getIdProgramacionasignatura());
                attachedProgramacionOpcioncList.add(programacionOpcioncListProgramacionOpcioncToAttach);
            }
            asesor.setProgramacionOpcioncList(attachedProgramacionOpcioncList);
            List<EsquemaAlumnoasignatura> attachedEsquemaAlumnoasignaturaList = new ArrayList<EsquemaAlumnoasignatura>();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach : asesor.getEsquemaAlumnoasignaturaList()) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach = em.getReference(esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach.getClass(), esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach.getIdEsquemaalumnoasignatura());
                attachedEsquemaAlumnoasignaturaList.add(esquemaAlumnoasignaturaListEsquemaAlumnoasignaturaToAttach);
            }
            asesor.setEsquemaAlumnoasignaturaList(attachedEsquemaAlumnoasignaturaList);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoList = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneoToAttach : asesor.getExamenExtemporaneoList()) {
                examenExtemporaneoListExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoList.add(examenExtemporaneoListExamenExtemporaneoToAttach);
            }
            asesor.setExamenExtemporaneoList(attachedExamenExtemporaneoList);
            List<AsignacionAutorintegradora> attachedAsignacionAutorintegradoraList = new ArrayList<AsignacionAutorintegradora>();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach : asesor.getAsignacionAutorintegradoraList()) {
                asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach = em.getReference(asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach.getClass(), asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach.getIdAsignacionautorintegradora());
                attachedAsignacionAutorintegradoraList.add(asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach);
            }
            asesor.setAsignacionAutorintegradoraList(attachedAsignacionAutorintegradoraList);
            List<AsesorTitulartitulacion> attachedAsesorTitulartitulacionList = new ArrayList<AsesorTitulartitulacion>();
            for (AsesorTitulartitulacion asesorTitulartitulacionListAsesorTitulartitulacionToAttach : asesor.getAsesorTitulartitulacionList()) {
                asesorTitulartitulacionListAsesorTitulartitulacionToAttach = em.getReference(asesorTitulartitulacionListAsesorTitulartitulacionToAttach.getClass(), asesorTitulartitulacionListAsesorTitulartitulacionToAttach.getIdTramitetitulacion());
                attachedAsesorTitulartitulacionList.add(asesorTitulartitulacionListAsesorTitulartitulacionToAttach);
            }
            asesor.setAsesorTitulartitulacionList(attachedAsesorTitulartitulacionList);
            List<SinodoExamen> attachedSinodoExamenList = new ArrayList<SinodoExamen>();
            for (SinodoExamen sinodoExamenListSinodoExamenToAttach : asesor.getSinodoExamenList()) {
                sinodoExamenListSinodoExamenToAttach = em.getReference(sinodoExamenListSinodoExamenToAttach.getClass(), sinodoExamenListSinodoExamenToAttach.getIdSinodo());
                attachedSinodoExamenList.add(sinodoExamenListSinodoExamenToAttach);
            }
            asesor.setSinodoExamenList(attachedSinodoExamenList);
            List<AsignacionAutorbanco> attachedAsignacionAutorbancoList = new ArrayList<AsignacionAutorbanco>();
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbancoToAttach : asesor.getAsignacionAutorbancoList()) {
                asignacionAutorbancoListAsignacionAutorbancoToAttach = em.getReference(asignacionAutorbancoListAsignacionAutorbancoToAttach.getClass(), asignacionAutorbancoListAsignacionAutorbancoToAttach.getIdAsignacionautorbanco());
                attachedAsignacionAutorbancoList.add(asignacionAutorbancoListAsignacionAutorbancoToAttach);
            }
            asesor.setAsignacionAutorbancoList(attachedAsignacionAutorbancoList);
            List<GrupoInduccion> attachedGrupoInduccionList = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListGrupoInduccionToAttach : asesor.getGrupoInduccionList()) {
                grupoInduccionListGrupoInduccionToAttach = em.getReference(grupoInduccionListGrupoInduccionToAttach.getClass(), grupoInduccionListGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionList.add(grupoInduccionListGrupoInduccionToAttach);
            }
            asesor.setGrupoInduccionList(attachedGrupoInduccionList);
            List<AsignaturaAsesor> attachedAsignaturaAsesorList = new ArrayList<AsignaturaAsesor>();
            for (AsignaturaAsesor asignaturaAsesorListAsignaturaAsesorToAttach : asesor.getAsignaturaAsesorList()) {
                asignaturaAsesorListAsignaturaAsesorToAttach = em.getReference(asignaturaAsesorListAsignaturaAsesorToAttach.getClass(), asignaturaAsesorListAsignaturaAsesorToAttach.getIdAsignaturaasesor());
                attachedAsignaturaAsesorList.add(asignaturaAsesorListAsignaturaAsesorToAttach);
            }
            asesor.setAsignaturaAsesorList(attachedAsignaturaAsesorList);
            List<ExamenIndividual> attachedExamenIndividualList = new ArrayList<ExamenIndividual>();
            for (ExamenIndividual examenIndividualListExamenIndividualToAttach : asesor.getExamenIndividualList()) {
                examenIndividualListExamenIndividualToAttach = em.getReference(examenIndividualListExamenIndividualToAttach.getClass(), examenIndividualListExamenIndividualToAttach.getIdExamenindividual());
                attachedExamenIndividualList.add(examenIndividualListExamenIndividualToAttach);
            }
            asesor.setExamenIndividualList(attachedExamenIndividualList);
            List<GrupoExamenextemporaneo> attachedGrupoExamenextemporaneoList = new ArrayList<GrupoExamenextemporaneo>();
            for (GrupoExamenextemporaneo grupoExamenextemporaneoListGrupoExamenextemporaneoToAttach : asesor.getGrupoExamenextemporaneoList()) {
                grupoExamenextemporaneoListGrupoExamenextemporaneoToAttach = em.getReference(grupoExamenextemporaneoListGrupoExamenextemporaneoToAttach.getClass(), grupoExamenextemporaneoListGrupoExamenextemporaneoToAttach.getIdGrupoexamenextemporaneo());
                attachedGrupoExamenextemporaneoList.add(grupoExamenextemporaneoListGrupoExamenextemporaneoToAttach);
            }
            asesor.setGrupoExamenextemporaneoList(attachedGrupoExamenextemporaneoList);
            em.persist(asesor);
            if (idrecursohumano != null) {
                idrecursohumano.getAsesorList().add(asesor);
                idrecursohumano = em.merge(idrecursohumano);
            }
            if (idRecursohumano != null) {
                idRecursohumano.getAsesorList().add(asesor);
                idRecursohumano = em.merge(idRecursohumano);
            }
            for (Planestudios planestudiosListPlanestudios : asesor.getPlanestudiosList()) {
                planestudiosListPlanestudios.getAsesorList().add(asesor);
                planestudiosListPlanestudios = em.merge(planestudiosListPlanestudios);
            }
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesor.getAsesoriaasignaturaList()) {
                Asesor oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura = asesoriaasignaturaListAsesoriaasignatura.getIdasesor();
                asesoriaasignaturaListAsesoriaasignatura.setIdasesor(asesor);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
                if (oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura != null) {
                    oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListAsesoriaasignatura);
                    oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura = em.merge(oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura);
                }
            }
            for (ProgramacionOpcionc programacionOpcioncListProgramacionOpcionc : asesor.getProgramacionOpcioncList()) {
                programacionOpcioncListProgramacionOpcionc.getAsesorList().add(asesor);
                programacionOpcioncListProgramacionOpcionc = em.merge(programacionOpcioncListProgramacionOpcionc);
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignatura : asesor.getEsquemaAlumnoasignaturaList()) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.getAsesorList().add(asesor);
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
            }
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : asesor.getExamenExtemporaneoList()) {
                Asesor oldIdAsesorcalificadorOfExamenExtemporaneoListExamenExtemporaneo = examenExtemporaneoListExamenExtemporaneo.getIdAsesorcalificador();
                examenExtemporaneoListExamenExtemporaneo.setIdAsesorcalificador(asesor);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
                if (oldIdAsesorcalificadorOfExamenExtemporaneoListExamenExtemporaneo != null) {
                    oldIdAsesorcalificadorOfExamenExtemporaneoListExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListExamenExtemporaneo);
                    oldIdAsesorcalificadorOfExamenExtemporaneoListExamenExtemporaneo = em.merge(oldIdAsesorcalificadorOfExamenExtemporaneoListExamenExtemporaneo);
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradora : asesor.getAsignacionAutorintegradoraList()) {
                Asesor oldIdAutorOfAsignacionAutorintegradoraListAsignacionAutorintegradora = asignacionAutorintegradoraListAsignacionAutorintegradora.getIdAutor();
                asignacionAutorintegradoraListAsignacionAutorintegradora.setIdAutor(asesor);
                asignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListAsignacionAutorintegradora);
                if (oldIdAutorOfAsignacionAutorintegradoraListAsignacionAutorintegradora != null) {
                    oldIdAutorOfAsignacionAutorintegradoraListAsignacionAutorintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradoraListAsignacionAutorintegradora);
                    oldIdAutorOfAsignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(oldIdAutorOfAsignacionAutorintegradoraListAsignacionAutorintegradora);
                }
            }
            for (AsesorTitulartitulacion asesorTitulartitulacionListAsesorTitulartitulacion : asesor.getAsesorTitulartitulacionList()) {
                Asesor oldIdAsesorOfAsesorTitulartitulacionListAsesorTitulartitulacion = asesorTitulartitulacionListAsesorTitulartitulacion.getIdAsesor();
                asesorTitulartitulacionListAsesorTitulartitulacion.setIdAsesor(asesor);
                asesorTitulartitulacionListAsesorTitulartitulacion = em.merge(asesorTitulartitulacionListAsesorTitulartitulacion);
                if (oldIdAsesorOfAsesorTitulartitulacionListAsesorTitulartitulacion != null) {
                    oldIdAsesorOfAsesorTitulartitulacionListAsesorTitulartitulacion.getAsesorTitulartitulacionList().remove(asesorTitulartitulacionListAsesorTitulartitulacion);
                    oldIdAsesorOfAsesorTitulartitulacionListAsesorTitulartitulacion = em.merge(oldIdAsesorOfAsesorTitulartitulacionListAsesorTitulartitulacion);
                }
            }
            for (SinodoExamen sinodoExamenListSinodoExamen : asesor.getSinodoExamenList()) {
                Asesor oldIdAsesorOfSinodoExamenListSinodoExamen = sinodoExamenListSinodoExamen.getIdAsesor();
                sinodoExamenListSinodoExamen.setIdAsesor(asesor);
                sinodoExamenListSinodoExamen = em.merge(sinodoExamenListSinodoExamen);
                if (oldIdAsesorOfSinodoExamenListSinodoExamen != null) {
                    oldIdAsesorOfSinodoExamenListSinodoExamen.getSinodoExamenList().remove(sinodoExamenListSinodoExamen);
                    oldIdAsesorOfSinodoExamenListSinodoExamen = em.merge(oldIdAsesorOfSinodoExamenListSinodoExamen);
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbanco : asesor.getAsignacionAutorbancoList()) {
                Asesor oldIdAutorOfAsignacionAutorbancoListAsignacionAutorbanco = asignacionAutorbancoListAsignacionAutorbanco.getIdAutor();
                asignacionAutorbancoListAsignacionAutorbanco.setIdAutor(asesor);
                asignacionAutorbancoListAsignacionAutorbanco = em.merge(asignacionAutorbancoListAsignacionAutorbanco);
                if (oldIdAutorOfAsignacionAutorbancoListAsignacionAutorbanco != null) {
                    oldIdAutorOfAsignacionAutorbancoListAsignacionAutorbanco.getAsignacionAutorbancoList().remove(asignacionAutorbancoListAsignacionAutorbanco);
                    oldIdAutorOfAsignacionAutorbancoListAsignacionAutorbanco = em.merge(oldIdAutorOfAsignacionAutorbancoListAsignacionAutorbanco);
                }
            }
            for (GrupoInduccion grupoInduccionListGrupoInduccion : asesor.getGrupoInduccionList()) {
                Asesor oldResponsableOfGrupoInduccionListGrupoInduccion = grupoInduccionListGrupoInduccion.getResponsable();
                grupoInduccionListGrupoInduccion.setResponsable(asesor);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
                if (oldResponsableOfGrupoInduccionListGrupoInduccion != null) {
                    oldResponsableOfGrupoInduccionListGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListGrupoInduccion);
                    oldResponsableOfGrupoInduccionListGrupoInduccion = em.merge(oldResponsableOfGrupoInduccionListGrupoInduccion);
                }
            }
            for (AsignaturaAsesor asignaturaAsesorListAsignaturaAsesor : asesor.getAsignaturaAsesorList()) {
                Asesor oldIdAsesorOfAsignaturaAsesorListAsignaturaAsesor = asignaturaAsesorListAsignaturaAsesor.getIdAsesor();
                asignaturaAsesorListAsignaturaAsesor.setIdAsesor(asesor);
                asignaturaAsesorListAsignaturaAsesor = em.merge(asignaturaAsesorListAsignaturaAsesor);
                if (oldIdAsesorOfAsignaturaAsesorListAsignaturaAsesor != null) {
                    oldIdAsesorOfAsignaturaAsesorListAsignaturaAsesor.getAsignaturaAsesorList().remove(asignaturaAsesorListAsignaturaAsesor);
                    oldIdAsesorOfAsignaturaAsesorListAsignaturaAsesor = em.merge(oldIdAsesorOfAsignaturaAsesorListAsignaturaAsesor);
                }
            }
            for (ExamenIndividual examenIndividualListExamenIndividual : asesor.getExamenIndividualList()) {
                Asesor oldIdAsesorOfExamenIndividualListExamenIndividual = examenIndividualListExamenIndividual.getIdAsesor();
                examenIndividualListExamenIndividual.setIdAsesor(asesor);
                examenIndividualListExamenIndividual = em.merge(examenIndividualListExamenIndividual);
                if (oldIdAsesorOfExamenIndividualListExamenIndividual != null) {
                    oldIdAsesorOfExamenIndividualListExamenIndividual.getExamenIndividualList().remove(examenIndividualListExamenIndividual);
                    oldIdAsesorOfExamenIndividualListExamenIndividual = em.merge(oldIdAsesorOfExamenIndividualListExamenIndividual);
                }
            }
            for (GrupoExamenextemporaneo grupoExamenextemporaneoListGrupoExamenextemporaneo : asesor.getGrupoExamenextemporaneoList()) {
                Asesor oldIdAsesorOfGrupoExamenextemporaneoListGrupoExamenextemporaneo = grupoExamenextemporaneoListGrupoExamenextemporaneo.getIdAsesor();
                grupoExamenextemporaneoListGrupoExamenextemporaneo.setIdAsesor(asesor);
                grupoExamenextemporaneoListGrupoExamenextemporaneo = em.merge(grupoExamenextemporaneoListGrupoExamenextemporaneo);
                if (oldIdAsesorOfGrupoExamenextemporaneoListGrupoExamenextemporaneo != null) {
                    oldIdAsesorOfGrupoExamenextemporaneoListGrupoExamenextemporaneo.getGrupoExamenextemporaneoList().remove(grupoExamenextemporaneoListGrupoExamenextemporaneo);
                    oldIdAsesorOfGrupoExamenextemporaneoListGrupoExamenextemporaneo = em.merge(oldIdAsesorOfGrupoExamenextemporaneoListGrupoExamenextemporaneo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesor asesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesor persistentAsesor = em.find(Asesor.class, asesor.getIdasesor());
            Recursohumano idrecursohumanoOld = persistentAsesor.getIdrecursohumano();
            Recursohumano idrecursohumanoNew = asesor.getIdrecursohumano();
            RecursoHumano idRecursohumanoOld = persistentAsesor.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = asesor.getIdRecursohumano();
            List<Planestudios> planestudiosListOld = persistentAsesor.getPlanestudiosList();
            List<Planestudios> planestudiosListNew = asesor.getPlanestudiosList();
            List<Asesoriaasignatura> asesoriaasignaturaListOld = persistentAsesor.getAsesoriaasignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListNew = asesor.getAsesoriaasignaturaList();
            List<ProgramacionOpcionc> programacionOpcioncListOld = persistentAsesor.getProgramacionOpcioncList();
            List<ProgramacionOpcionc> programacionOpcioncListNew = asesor.getProgramacionOpcioncList();
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaListOld = persistentAsesor.getEsquemaAlumnoasignaturaList();
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaListNew = asesor.getEsquemaAlumnoasignaturaList();
            List<ExamenExtemporaneo> examenExtemporaneoListOld = persistentAsesor.getExamenExtemporaneoList();
            List<ExamenExtemporaneo> examenExtemporaneoListNew = asesor.getExamenExtemporaneoList();
            List<AsignacionAutorintegradora> asignacionAutorintegradoraListOld = persistentAsesor.getAsignacionAutorintegradoraList();
            List<AsignacionAutorintegradora> asignacionAutorintegradoraListNew = asesor.getAsignacionAutorintegradoraList();
            List<AsesorTitulartitulacion> asesorTitulartitulacionListOld = persistentAsesor.getAsesorTitulartitulacionList();
            List<AsesorTitulartitulacion> asesorTitulartitulacionListNew = asesor.getAsesorTitulartitulacionList();
            List<SinodoExamen> sinodoExamenListOld = persistentAsesor.getSinodoExamenList();
            List<SinodoExamen> sinodoExamenListNew = asesor.getSinodoExamenList();
            List<AsignacionAutorbanco> asignacionAutorbancoListOld = persistentAsesor.getAsignacionAutorbancoList();
            List<AsignacionAutorbanco> asignacionAutorbancoListNew = asesor.getAsignacionAutorbancoList();
            List<GrupoInduccion> grupoInduccionListOld = persistentAsesor.getGrupoInduccionList();
            List<GrupoInduccion> grupoInduccionListNew = asesor.getGrupoInduccionList();
            List<AsignaturaAsesor> asignaturaAsesorListOld = persistentAsesor.getAsignaturaAsesorList();
            List<AsignaturaAsesor> asignaturaAsesorListNew = asesor.getAsignaturaAsesorList();
            List<ExamenIndividual> examenIndividualListOld = persistentAsesor.getExamenIndividualList();
            List<ExamenIndividual> examenIndividualListNew = asesor.getExamenIndividualList();
            List<GrupoExamenextemporaneo> grupoExamenextemporaneoListOld = persistentAsesor.getGrupoExamenextemporaneoList();
            List<GrupoExamenextemporaneo> grupoExamenextemporaneoListNew = asesor.getGrupoExamenextemporaneoList();
            if (idrecursohumanoNew != null) {
                idrecursohumanoNew = em.getReference(idrecursohumanoNew.getClass(), idrecursohumanoNew.getIdrecursohumano());
                asesor.setIdrecursohumano(idrecursohumanoNew);
            }
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                asesor.setIdRecursohumano(idRecursohumanoNew);
            }
            List<Planestudios> attachedPlanestudiosListNew = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListNewPlanestudiosToAttach : planestudiosListNew) {
                planestudiosListNewPlanestudiosToAttach = em.getReference(planestudiosListNewPlanestudiosToAttach.getClass(), planestudiosListNewPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosListNew.add(planestudiosListNewPlanestudiosToAttach);
            }
            planestudiosListNew = attachedPlanestudiosListNew;
            asesor.setPlanestudiosList(planestudiosListNew);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaListNew = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignaturaToAttach : asesoriaasignaturaListNew) {
                asesoriaasignaturaListNewAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaListNew.add(asesoriaasignaturaListNewAsesoriaasignaturaToAttach);
            }
            asesoriaasignaturaListNew = attachedAsesoriaasignaturaListNew;
            asesor.setAsesoriaasignaturaList(asesoriaasignaturaListNew);
            List<ProgramacionOpcionc> attachedProgramacionOpcioncListNew = new ArrayList<ProgramacionOpcionc>();
            for (ProgramacionOpcionc programacionOpcioncListNewProgramacionOpcioncToAttach : programacionOpcioncListNew) {
                programacionOpcioncListNewProgramacionOpcioncToAttach = em.getReference(programacionOpcioncListNewProgramacionOpcioncToAttach.getClass(), programacionOpcioncListNewProgramacionOpcioncToAttach.getIdProgramacionasignatura());
                attachedProgramacionOpcioncListNew.add(programacionOpcioncListNewProgramacionOpcioncToAttach);
            }
            programacionOpcioncListNew = attachedProgramacionOpcioncListNew;
            asesor.setProgramacionOpcioncList(programacionOpcioncListNew);
            List<EsquemaAlumnoasignatura> attachedEsquemaAlumnoasignaturaListNew = new ArrayList<EsquemaAlumnoasignatura>();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach : esquemaAlumnoasignaturaListNew) {
                esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach = em.getReference(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach.getClass(), esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach.getIdEsquemaalumnoasignatura());
                attachedEsquemaAlumnoasignaturaListNew.add(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignaturaToAttach);
            }
            esquemaAlumnoasignaturaListNew = attachedEsquemaAlumnoasignaturaListNew;
            asesor.setEsquemaAlumnoasignaturaList(esquemaAlumnoasignaturaListNew);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoListNew = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneoToAttach : examenExtemporaneoListNew) {
                examenExtemporaneoListNewExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListNewExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListNewExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoListNew.add(examenExtemporaneoListNewExamenExtemporaneoToAttach);
            }
            examenExtemporaneoListNew = attachedExamenExtemporaneoListNew;
            asesor.setExamenExtemporaneoList(examenExtemporaneoListNew);
            List<AsignacionAutorintegradora> attachedAsignacionAutorintegradoraListNew = new ArrayList<AsignacionAutorintegradora>();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach : asignacionAutorintegradoraListNew) {
                asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach = em.getReference(asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach.getClass(), asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach.getIdAsignacionautorintegradora());
                attachedAsignacionAutorintegradoraListNew.add(asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach);
            }
            asignacionAutorintegradoraListNew = attachedAsignacionAutorintegradoraListNew;
            asesor.setAsignacionAutorintegradoraList(asignacionAutorintegradoraListNew);
            List<AsesorTitulartitulacion> attachedAsesorTitulartitulacionListNew = new ArrayList<AsesorTitulartitulacion>();
            for (AsesorTitulartitulacion asesorTitulartitulacionListNewAsesorTitulartitulacionToAttach : asesorTitulartitulacionListNew) {
                asesorTitulartitulacionListNewAsesorTitulartitulacionToAttach = em.getReference(asesorTitulartitulacionListNewAsesorTitulartitulacionToAttach.getClass(), asesorTitulartitulacionListNewAsesorTitulartitulacionToAttach.getIdTramitetitulacion());
                attachedAsesorTitulartitulacionListNew.add(asesorTitulartitulacionListNewAsesorTitulartitulacionToAttach);
            }
            asesorTitulartitulacionListNew = attachedAsesorTitulartitulacionListNew;
            asesor.setAsesorTitulartitulacionList(asesorTitulartitulacionListNew);
            List<SinodoExamen> attachedSinodoExamenListNew = new ArrayList<SinodoExamen>();
            for (SinodoExamen sinodoExamenListNewSinodoExamenToAttach : sinodoExamenListNew) {
                sinodoExamenListNewSinodoExamenToAttach = em.getReference(sinodoExamenListNewSinodoExamenToAttach.getClass(), sinodoExamenListNewSinodoExamenToAttach.getIdSinodo());
                attachedSinodoExamenListNew.add(sinodoExamenListNewSinodoExamenToAttach);
            }
            sinodoExamenListNew = attachedSinodoExamenListNew;
            asesor.setSinodoExamenList(sinodoExamenListNew);
            List<AsignacionAutorbanco> attachedAsignacionAutorbancoListNew = new ArrayList<AsignacionAutorbanco>();
            for (AsignacionAutorbanco asignacionAutorbancoListNewAsignacionAutorbancoToAttach : asignacionAutorbancoListNew) {
                asignacionAutorbancoListNewAsignacionAutorbancoToAttach = em.getReference(asignacionAutorbancoListNewAsignacionAutorbancoToAttach.getClass(), asignacionAutorbancoListNewAsignacionAutorbancoToAttach.getIdAsignacionautorbanco());
                attachedAsignacionAutorbancoListNew.add(asignacionAutorbancoListNewAsignacionAutorbancoToAttach);
            }
            asignacionAutorbancoListNew = attachedAsignacionAutorbancoListNew;
            asesor.setAsignacionAutorbancoList(asignacionAutorbancoListNew);
            List<GrupoInduccion> attachedGrupoInduccionListNew = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListNewGrupoInduccionToAttach : grupoInduccionListNew) {
                grupoInduccionListNewGrupoInduccionToAttach = em.getReference(grupoInduccionListNewGrupoInduccionToAttach.getClass(), grupoInduccionListNewGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionListNew.add(grupoInduccionListNewGrupoInduccionToAttach);
            }
            grupoInduccionListNew = attachedGrupoInduccionListNew;
            asesor.setGrupoInduccionList(grupoInduccionListNew);
            List<AsignaturaAsesor> attachedAsignaturaAsesorListNew = new ArrayList<AsignaturaAsesor>();
            for (AsignaturaAsesor asignaturaAsesorListNewAsignaturaAsesorToAttach : asignaturaAsesorListNew) {
                asignaturaAsesorListNewAsignaturaAsesorToAttach = em.getReference(asignaturaAsesorListNewAsignaturaAsesorToAttach.getClass(), asignaturaAsesorListNewAsignaturaAsesorToAttach.getIdAsignaturaasesor());
                attachedAsignaturaAsesorListNew.add(asignaturaAsesorListNewAsignaturaAsesorToAttach);
            }
            asignaturaAsesorListNew = attachedAsignaturaAsesorListNew;
            asesor.setAsignaturaAsesorList(asignaturaAsesorListNew);
            List<ExamenIndividual> attachedExamenIndividualListNew = new ArrayList<ExamenIndividual>();
            for (ExamenIndividual examenIndividualListNewExamenIndividualToAttach : examenIndividualListNew) {
                examenIndividualListNewExamenIndividualToAttach = em.getReference(examenIndividualListNewExamenIndividualToAttach.getClass(), examenIndividualListNewExamenIndividualToAttach.getIdExamenindividual());
                attachedExamenIndividualListNew.add(examenIndividualListNewExamenIndividualToAttach);
            }
            examenIndividualListNew = attachedExamenIndividualListNew;
            asesor.setExamenIndividualList(examenIndividualListNew);
            List<GrupoExamenextemporaneo> attachedGrupoExamenextemporaneoListNew = new ArrayList<GrupoExamenextemporaneo>();
            for (GrupoExamenextemporaneo grupoExamenextemporaneoListNewGrupoExamenextemporaneoToAttach : grupoExamenextemporaneoListNew) {
                grupoExamenextemporaneoListNewGrupoExamenextemporaneoToAttach = em.getReference(grupoExamenextemporaneoListNewGrupoExamenextemporaneoToAttach.getClass(), grupoExamenextemporaneoListNewGrupoExamenextemporaneoToAttach.getIdGrupoexamenextemporaneo());
                attachedGrupoExamenextemporaneoListNew.add(grupoExamenextemporaneoListNewGrupoExamenextemporaneoToAttach);
            }
            grupoExamenextemporaneoListNew = attachedGrupoExamenextemporaneoListNew;
            asesor.setGrupoExamenextemporaneoList(grupoExamenextemporaneoListNew);
            asesor = em.merge(asesor);
            if (idrecursohumanoOld != null && !idrecursohumanoOld.equals(idrecursohumanoNew)) {
                idrecursohumanoOld.getAsesorList().remove(asesor);
                idrecursohumanoOld = em.merge(idrecursohumanoOld);
            }
            if (idrecursohumanoNew != null && !idrecursohumanoNew.equals(idrecursohumanoOld)) {
                idrecursohumanoNew.getAsesorList().add(asesor);
                idrecursohumanoNew = em.merge(idrecursohumanoNew);
            }
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getAsesorList().remove(asesor);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getAsesorList().add(asesor);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            for (Planestudios planestudiosListOldPlanestudios : planestudiosListOld) {
                if (!planestudiosListNew.contains(planestudiosListOldPlanestudios)) {
                    planestudiosListOldPlanestudios.getAsesorList().remove(asesor);
                    planestudiosListOldPlanestudios = em.merge(planestudiosListOldPlanestudios);
                }
            }
            for (Planestudios planestudiosListNewPlanestudios : planestudiosListNew) {
                if (!planestudiosListOld.contains(planestudiosListNewPlanestudios)) {
                    planestudiosListNewPlanestudios.getAsesorList().add(asesor);
                    planestudiosListNewPlanestudios = em.merge(planestudiosListNewPlanestudios);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListOldAsesoriaasignatura : asesoriaasignaturaListOld) {
                if (!asesoriaasignaturaListNew.contains(asesoriaasignaturaListOldAsesoriaasignatura)) {
                    asesoriaasignaturaListOldAsesoriaasignatura.setIdasesor(null);
                    asesoriaasignaturaListOldAsesoriaasignatura = em.merge(asesoriaasignaturaListOldAsesoriaasignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignatura : asesoriaasignaturaListNew) {
                if (!asesoriaasignaturaListOld.contains(asesoriaasignaturaListNewAsesoriaasignatura)) {
                    Asesor oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura = asesoriaasignaturaListNewAsesoriaasignatura.getIdasesor();
                    asesoriaasignaturaListNewAsesoriaasignatura.setIdasesor(asesor);
                    asesoriaasignaturaListNewAsesoriaasignatura = em.merge(asesoriaasignaturaListNewAsesoriaasignatura);
                    if (oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura != null && !oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura.equals(asesor)) {
                        oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListNewAsesoriaasignatura);
                        oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura = em.merge(oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura);
                    }
                }
            }
            for (ProgramacionOpcionc programacionOpcioncListOldProgramacionOpcionc : programacionOpcioncListOld) {
                if (!programacionOpcioncListNew.contains(programacionOpcioncListOldProgramacionOpcionc)) {
                    programacionOpcioncListOldProgramacionOpcionc.getAsesorList().remove(asesor);
                    programacionOpcioncListOldProgramacionOpcionc = em.merge(programacionOpcioncListOldProgramacionOpcionc);
                }
            }
            for (ProgramacionOpcionc programacionOpcioncListNewProgramacionOpcionc : programacionOpcioncListNew) {
                if (!programacionOpcioncListOld.contains(programacionOpcioncListNewProgramacionOpcionc)) {
                    programacionOpcioncListNewProgramacionOpcionc.getAsesorList().add(asesor);
                    programacionOpcioncListNewProgramacionOpcionc = em.merge(programacionOpcioncListNewProgramacionOpcionc);
                }
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura : esquemaAlumnoasignaturaListOld) {
                if (!esquemaAlumnoasignaturaListNew.contains(esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura)) {
                    esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura.getAsesorList().remove(asesor);
                    esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListOldEsquemaAlumnoasignatura);
                }
            }
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura : esquemaAlumnoasignaturaListNew) {
                if (!esquemaAlumnoasignaturaListOld.contains(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura)) {
                    esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura.getAsesorList().add(asesor);
                    esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListNewEsquemaAlumnoasignatura);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListOldExamenExtemporaneo : examenExtemporaneoListOld) {
                if (!examenExtemporaneoListNew.contains(examenExtemporaneoListOldExamenExtemporaneo)) {
                    examenExtemporaneoListOldExamenExtemporaneo.setIdAsesorcalificador(null);
                    examenExtemporaneoListOldExamenExtemporaneo = em.merge(examenExtemporaneoListOldExamenExtemporaneo);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneo : examenExtemporaneoListNew) {
                if (!examenExtemporaneoListOld.contains(examenExtemporaneoListNewExamenExtemporaneo)) {
                    Asesor oldIdAsesorcalificadorOfExamenExtemporaneoListNewExamenExtemporaneo = examenExtemporaneoListNewExamenExtemporaneo.getIdAsesorcalificador();
                    examenExtemporaneoListNewExamenExtemporaneo.setIdAsesorcalificador(asesor);
                    examenExtemporaneoListNewExamenExtemporaneo = em.merge(examenExtemporaneoListNewExamenExtemporaneo);
                    if (oldIdAsesorcalificadorOfExamenExtemporaneoListNewExamenExtemporaneo != null && !oldIdAsesorcalificadorOfExamenExtemporaneoListNewExamenExtemporaneo.equals(asesor)) {
                        oldIdAsesorcalificadorOfExamenExtemporaneoListNewExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListNewExamenExtemporaneo);
                        oldIdAsesorcalificadorOfExamenExtemporaneoListNewExamenExtemporaneo = em.merge(oldIdAsesorcalificadorOfExamenExtemporaneoListNewExamenExtemporaneo);
                    }
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListOldAsignacionAutorintegradora : asignacionAutorintegradoraListOld) {
                if (!asignacionAutorintegradoraListNew.contains(asignacionAutorintegradoraListOldAsignacionAutorintegradora)) {
                    asignacionAutorintegradoraListOldAsignacionAutorintegradora.setIdAutor(null);
                    asignacionAutorintegradoraListOldAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListOldAsignacionAutorintegradora);
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListNewAsignacionAutorintegradora : asignacionAutorintegradoraListNew) {
                if (!asignacionAutorintegradoraListOld.contains(asignacionAutorintegradoraListNewAsignacionAutorintegradora)) {
                    Asesor oldIdAutorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora = asignacionAutorintegradoraListNewAsignacionAutorintegradora.getIdAutor();
                    asignacionAutorintegradoraListNewAsignacionAutorintegradora.setIdAutor(asesor);
                    asignacionAutorintegradoraListNewAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListNewAsignacionAutorintegradora);
                    if (oldIdAutorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora != null && !oldIdAutorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora.equals(asesor)) {
                        oldIdAutorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradoraListNewAsignacionAutorintegradora);
                        oldIdAutorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora = em.merge(oldIdAutorOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora);
                    }
                }
            }
            for (AsesorTitulartitulacion asesorTitulartitulacionListOldAsesorTitulartitulacion : asesorTitulartitulacionListOld) {
                if (!asesorTitulartitulacionListNew.contains(asesorTitulartitulacionListOldAsesorTitulartitulacion)) {
                    asesorTitulartitulacionListOldAsesorTitulartitulacion.setIdAsesor(null);
                    asesorTitulartitulacionListOldAsesorTitulartitulacion = em.merge(asesorTitulartitulacionListOldAsesorTitulartitulacion);
                }
            }
            for (AsesorTitulartitulacion asesorTitulartitulacionListNewAsesorTitulartitulacion : asesorTitulartitulacionListNew) {
                if (!asesorTitulartitulacionListOld.contains(asesorTitulartitulacionListNewAsesorTitulartitulacion)) {
                    Asesor oldIdAsesorOfAsesorTitulartitulacionListNewAsesorTitulartitulacion = asesorTitulartitulacionListNewAsesorTitulartitulacion.getIdAsesor();
                    asesorTitulartitulacionListNewAsesorTitulartitulacion.setIdAsesor(asesor);
                    asesorTitulartitulacionListNewAsesorTitulartitulacion = em.merge(asesorTitulartitulacionListNewAsesorTitulartitulacion);
                    if (oldIdAsesorOfAsesorTitulartitulacionListNewAsesorTitulartitulacion != null && !oldIdAsesorOfAsesorTitulartitulacionListNewAsesorTitulartitulacion.equals(asesor)) {
                        oldIdAsesorOfAsesorTitulartitulacionListNewAsesorTitulartitulacion.getAsesorTitulartitulacionList().remove(asesorTitulartitulacionListNewAsesorTitulartitulacion);
                        oldIdAsesorOfAsesorTitulartitulacionListNewAsesorTitulartitulacion = em.merge(oldIdAsesorOfAsesorTitulartitulacionListNewAsesorTitulartitulacion);
                    }
                }
            }
            for (SinodoExamen sinodoExamenListOldSinodoExamen : sinodoExamenListOld) {
                if (!sinodoExamenListNew.contains(sinodoExamenListOldSinodoExamen)) {
                    sinodoExamenListOldSinodoExamen.setIdAsesor(null);
                    sinodoExamenListOldSinodoExamen = em.merge(sinodoExamenListOldSinodoExamen);
                }
            }
            for (SinodoExamen sinodoExamenListNewSinodoExamen : sinodoExamenListNew) {
                if (!sinodoExamenListOld.contains(sinodoExamenListNewSinodoExamen)) {
                    Asesor oldIdAsesorOfSinodoExamenListNewSinodoExamen = sinodoExamenListNewSinodoExamen.getIdAsesor();
                    sinodoExamenListNewSinodoExamen.setIdAsesor(asesor);
                    sinodoExamenListNewSinodoExamen = em.merge(sinodoExamenListNewSinodoExamen);
                    if (oldIdAsesorOfSinodoExamenListNewSinodoExamen != null && !oldIdAsesorOfSinodoExamenListNewSinodoExamen.equals(asesor)) {
                        oldIdAsesorOfSinodoExamenListNewSinodoExamen.getSinodoExamenList().remove(sinodoExamenListNewSinodoExamen);
                        oldIdAsesorOfSinodoExamenListNewSinodoExamen = em.merge(oldIdAsesorOfSinodoExamenListNewSinodoExamen);
                    }
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListOldAsignacionAutorbanco : asignacionAutorbancoListOld) {
                if (!asignacionAutorbancoListNew.contains(asignacionAutorbancoListOldAsignacionAutorbanco)) {
                    asignacionAutorbancoListOldAsignacionAutorbanco.setIdAutor(null);
                    asignacionAutorbancoListOldAsignacionAutorbanco = em.merge(asignacionAutorbancoListOldAsignacionAutorbanco);
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListNewAsignacionAutorbanco : asignacionAutorbancoListNew) {
                if (!asignacionAutorbancoListOld.contains(asignacionAutorbancoListNewAsignacionAutorbanco)) {
                    Asesor oldIdAutorOfAsignacionAutorbancoListNewAsignacionAutorbanco = asignacionAutorbancoListNewAsignacionAutorbanco.getIdAutor();
                    asignacionAutorbancoListNewAsignacionAutorbanco.setIdAutor(asesor);
                    asignacionAutorbancoListNewAsignacionAutorbanco = em.merge(asignacionAutorbancoListNewAsignacionAutorbanco);
                    if (oldIdAutorOfAsignacionAutorbancoListNewAsignacionAutorbanco != null && !oldIdAutorOfAsignacionAutorbancoListNewAsignacionAutorbanco.equals(asesor)) {
                        oldIdAutorOfAsignacionAutorbancoListNewAsignacionAutorbanco.getAsignacionAutorbancoList().remove(asignacionAutorbancoListNewAsignacionAutorbanco);
                        oldIdAutorOfAsignacionAutorbancoListNewAsignacionAutorbanco = em.merge(oldIdAutorOfAsignacionAutorbancoListNewAsignacionAutorbanco);
                    }
                }
            }
            for (GrupoInduccion grupoInduccionListOldGrupoInduccion : grupoInduccionListOld) {
                if (!grupoInduccionListNew.contains(grupoInduccionListOldGrupoInduccion)) {
                    grupoInduccionListOldGrupoInduccion.setResponsable(null);
                    grupoInduccionListOldGrupoInduccion = em.merge(grupoInduccionListOldGrupoInduccion);
                }
            }
            for (GrupoInduccion grupoInduccionListNewGrupoInduccion : grupoInduccionListNew) {
                if (!grupoInduccionListOld.contains(grupoInduccionListNewGrupoInduccion)) {
                    Asesor oldResponsableOfGrupoInduccionListNewGrupoInduccion = grupoInduccionListNewGrupoInduccion.getResponsable();
                    grupoInduccionListNewGrupoInduccion.setResponsable(asesor);
                    grupoInduccionListNewGrupoInduccion = em.merge(grupoInduccionListNewGrupoInduccion);
                    if (oldResponsableOfGrupoInduccionListNewGrupoInduccion != null && !oldResponsableOfGrupoInduccionListNewGrupoInduccion.equals(asesor)) {
                        oldResponsableOfGrupoInduccionListNewGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListNewGrupoInduccion);
                        oldResponsableOfGrupoInduccionListNewGrupoInduccion = em.merge(oldResponsableOfGrupoInduccionListNewGrupoInduccion);
                    }
                }
            }
            for (AsignaturaAsesor asignaturaAsesorListOldAsignaturaAsesor : asignaturaAsesorListOld) {
                if (!asignaturaAsesorListNew.contains(asignaturaAsesorListOldAsignaturaAsesor)) {
                    asignaturaAsesorListOldAsignaturaAsesor.setIdAsesor(null);
                    asignaturaAsesorListOldAsignaturaAsesor = em.merge(asignaturaAsesorListOldAsignaturaAsesor);
                }
            }
            for (AsignaturaAsesor asignaturaAsesorListNewAsignaturaAsesor : asignaturaAsesorListNew) {
                if (!asignaturaAsesorListOld.contains(asignaturaAsesorListNewAsignaturaAsesor)) {
                    Asesor oldIdAsesorOfAsignaturaAsesorListNewAsignaturaAsesor = asignaturaAsesorListNewAsignaturaAsesor.getIdAsesor();
                    asignaturaAsesorListNewAsignaturaAsesor.setIdAsesor(asesor);
                    asignaturaAsesorListNewAsignaturaAsesor = em.merge(asignaturaAsesorListNewAsignaturaAsesor);
                    if (oldIdAsesorOfAsignaturaAsesorListNewAsignaturaAsesor != null && !oldIdAsesorOfAsignaturaAsesorListNewAsignaturaAsesor.equals(asesor)) {
                        oldIdAsesorOfAsignaturaAsesorListNewAsignaturaAsesor.getAsignaturaAsesorList().remove(asignaturaAsesorListNewAsignaturaAsesor);
                        oldIdAsesorOfAsignaturaAsesorListNewAsignaturaAsesor = em.merge(oldIdAsesorOfAsignaturaAsesorListNewAsignaturaAsesor);
                    }
                }
            }
            for (ExamenIndividual examenIndividualListOldExamenIndividual : examenIndividualListOld) {
                if (!examenIndividualListNew.contains(examenIndividualListOldExamenIndividual)) {
                    examenIndividualListOldExamenIndividual.setIdAsesor(null);
                    examenIndividualListOldExamenIndividual = em.merge(examenIndividualListOldExamenIndividual);
                }
            }
            for (ExamenIndividual examenIndividualListNewExamenIndividual : examenIndividualListNew) {
                if (!examenIndividualListOld.contains(examenIndividualListNewExamenIndividual)) {
                    Asesor oldIdAsesorOfExamenIndividualListNewExamenIndividual = examenIndividualListNewExamenIndividual.getIdAsesor();
                    examenIndividualListNewExamenIndividual.setIdAsesor(asesor);
                    examenIndividualListNewExamenIndividual = em.merge(examenIndividualListNewExamenIndividual);
                    if (oldIdAsesorOfExamenIndividualListNewExamenIndividual != null && !oldIdAsesorOfExamenIndividualListNewExamenIndividual.equals(asesor)) {
                        oldIdAsesorOfExamenIndividualListNewExamenIndividual.getExamenIndividualList().remove(examenIndividualListNewExamenIndividual);
                        oldIdAsesorOfExamenIndividualListNewExamenIndividual = em.merge(oldIdAsesorOfExamenIndividualListNewExamenIndividual);
                    }
                }
            }
            for (GrupoExamenextemporaneo grupoExamenextemporaneoListOldGrupoExamenextemporaneo : grupoExamenextemporaneoListOld) {
                if (!grupoExamenextemporaneoListNew.contains(grupoExamenextemporaneoListOldGrupoExamenextemporaneo)) {
                    grupoExamenextemporaneoListOldGrupoExamenextemporaneo.setIdAsesor(null);
                    grupoExamenextemporaneoListOldGrupoExamenextemporaneo = em.merge(grupoExamenextemporaneoListOldGrupoExamenextemporaneo);
                }
            }
            for (GrupoExamenextemporaneo grupoExamenextemporaneoListNewGrupoExamenextemporaneo : grupoExamenextemporaneoListNew) {
                if (!grupoExamenextemporaneoListOld.contains(grupoExamenextemporaneoListNewGrupoExamenextemporaneo)) {
                    Asesor oldIdAsesorOfGrupoExamenextemporaneoListNewGrupoExamenextemporaneo = grupoExamenextemporaneoListNewGrupoExamenextemporaneo.getIdAsesor();
                    grupoExamenextemporaneoListNewGrupoExamenextemporaneo.setIdAsesor(asesor);
                    grupoExamenextemporaneoListNewGrupoExamenextemporaneo = em.merge(grupoExamenextemporaneoListNewGrupoExamenextemporaneo);
                    if (oldIdAsesorOfGrupoExamenextemporaneoListNewGrupoExamenextemporaneo != null && !oldIdAsesorOfGrupoExamenextemporaneoListNewGrupoExamenextemporaneo.equals(asesor)) {
                        oldIdAsesorOfGrupoExamenextemporaneoListNewGrupoExamenextemporaneo.getGrupoExamenextemporaneoList().remove(grupoExamenextemporaneoListNewGrupoExamenextemporaneo);
                        oldIdAsesorOfGrupoExamenextemporaneoListNewGrupoExamenextemporaneo = em.merge(oldIdAsesorOfGrupoExamenextemporaneoListNewGrupoExamenextemporaneo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesor.getIdasesor();
                if (findAsesor(id) == null) {
                    throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.");
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
            Asesor asesor;
            try {
                asesor = em.getReference(Asesor.class, id);
                asesor.getIdasesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.", enfe);
            }
            Recursohumano idrecursohumano = asesor.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano.getAsesorList().remove(asesor);
                idrecursohumano = em.merge(idrecursohumano);
            }
            RecursoHumano idRecursohumano = asesor.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getAsesorList().remove(asesor);
                idRecursohumano = em.merge(idRecursohumano);
            }
            List<Planestudios> planestudiosList = asesor.getPlanestudiosList();
            for (Planestudios planestudiosListPlanestudios : planestudiosList) {
                planestudiosListPlanestudios.getAsesorList().remove(asesor);
                planestudiosListPlanestudios = em.merge(planestudiosListPlanestudios);
            }
            List<Asesoriaasignatura> asesoriaasignaturaList = asesor.getAsesoriaasignaturaList();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesoriaasignaturaList) {
                asesoriaasignaturaListAsesoriaasignatura.setIdasesor(null);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
            }
            List<ProgramacionOpcionc> programacionOpcioncList = asesor.getProgramacionOpcioncList();
            for (ProgramacionOpcionc programacionOpcioncListProgramacionOpcionc : programacionOpcioncList) {
                programacionOpcioncListProgramacionOpcionc.getAsesorList().remove(asesor);
                programacionOpcioncListProgramacionOpcionc = em.merge(programacionOpcioncListProgramacionOpcionc);
            }
            List<EsquemaAlumnoasignatura> esquemaAlumnoasignaturaList = asesor.getEsquemaAlumnoasignaturaList();
            for (EsquemaAlumnoasignatura esquemaAlumnoasignaturaListEsquemaAlumnoasignatura : esquemaAlumnoasignaturaList) {
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura.getAsesorList().remove(asesor);
                esquemaAlumnoasignaturaListEsquemaAlumnoasignatura = em.merge(esquemaAlumnoasignaturaListEsquemaAlumnoasignatura);
            }
            List<ExamenExtemporaneo> examenExtemporaneoList = asesor.getExamenExtemporaneoList();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : examenExtemporaneoList) {
                examenExtemporaneoListExamenExtemporaneo.setIdAsesorcalificador(null);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
            }
            List<AsignacionAutorintegradora> asignacionAutorintegradoraList = asesor.getAsignacionAutorintegradoraList();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradora : asignacionAutorintegradoraList) {
                asignacionAutorintegradoraListAsignacionAutorintegradora.setIdAutor(null);
                asignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListAsignacionAutorintegradora);
            }
            List<AsesorTitulartitulacion> asesorTitulartitulacionList = asesor.getAsesorTitulartitulacionList();
            for (AsesorTitulartitulacion asesorTitulartitulacionListAsesorTitulartitulacion : asesorTitulartitulacionList) {
                asesorTitulartitulacionListAsesorTitulartitulacion.setIdAsesor(null);
                asesorTitulartitulacionListAsesorTitulartitulacion = em.merge(asesorTitulartitulacionListAsesorTitulartitulacion);
            }
            List<SinodoExamen> sinodoExamenList = asesor.getSinodoExamenList();
            for (SinodoExamen sinodoExamenListSinodoExamen : sinodoExamenList) {
                sinodoExamenListSinodoExamen.setIdAsesor(null);
                sinodoExamenListSinodoExamen = em.merge(sinodoExamenListSinodoExamen);
            }
            List<AsignacionAutorbanco> asignacionAutorbancoList = asesor.getAsignacionAutorbancoList();
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbanco : asignacionAutorbancoList) {
                asignacionAutorbancoListAsignacionAutorbanco.setIdAutor(null);
                asignacionAutorbancoListAsignacionAutorbanco = em.merge(asignacionAutorbancoListAsignacionAutorbanco);
            }
            List<GrupoInduccion> grupoInduccionList = asesor.getGrupoInduccionList();
            for (GrupoInduccion grupoInduccionListGrupoInduccion : grupoInduccionList) {
                grupoInduccionListGrupoInduccion.setResponsable(null);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
            }
            List<AsignaturaAsesor> asignaturaAsesorList = asesor.getAsignaturaAsesorList();
            for (AsignaturaAsesor asignaturaAsesorListAsignaturaAsesor : asignaturaAsesorList) {
                asignaturaAsesorListAsignaturaAsesor.setIdAsesor(null);
                asignaturaAsesorListAsignaturaAsesor = em.merge(asignaturaAsesorListAsignaturaAsesor);
            }
            List<ExamenIndividual> examenIndividualList = asesor.getExamenIndividualList();
            for (ExamenIndividual examenIndividualListExamenIndividual : examenIndividualList) {
                examenIndividualListExamenIndividual.setIdAsesor(null);
                examenIndividualListExamenIndividual = em.merge(examenIndividualListExamenIndividual);
            }
            List<GrupoExamenextemporaneo> grupoExamenextemporaneoList = asesor.getGrupoExamenextemporaneoList();
            for (GrupoExamenextemporaneo grupoExamenextemporaneoListGrupoExamenextemporaneo : grupoExamenextemporaneoList) {
                grupoExamenextemporaneoListGrupoExamenextemporaneo.setIdAsesor(null);
                grupoExamenextemporaneoListGrupoExamenextemporaneo = em.merge(grupoExamenextemporaneoListGrupoExamenextemporaneo);
            }
            em.remove(asesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesor> findAsesorEntities() {
        return findAsesorEntities(true, -1, -1);
    }

    public List<Asesor> findAsesorEntities(int maxResults, int firstResult) {
        return findAsesorEntities(false, maxResults, firstResult);
    }

    private List<Asesor> findAsesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesor findAsesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
