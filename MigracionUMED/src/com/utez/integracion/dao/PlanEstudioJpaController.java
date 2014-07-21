/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoNivelacademico;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.integracion.entity.InstitucionRegistro;
import com.utez.integracion.entity.TipoTitulacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Aspirante;
import com.utez.integracion.entity.Generacion;
import com.utez.integracion.entity.AsignacionAsesorplan;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.secretariaAcademica.entity.Asignatura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PlanEstudioJpaController implements Serializable {

    public PlanEstudioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanEstudio planEstudio) {
        if (planEstudio.getTipoTitulacionList() == null) {
            planEstudio.setTipoTitulacionList(new ArrayList<TipoTitulacion>());
        }
        if (planEstudio.getAspiranteList() == null) {
            planEstudio.setAspiranteList(new ArrayList<Aspirante>());
        }
        if (planEstudio.getGeneracionList() == null) {
            planEstudio.setGeneracionList(new ArrayList<Generacion>());
        }
        if (planEstudio.getAsignacionAsesorplanList() == null) {
            planEstudio.setAsignacionAsesorplanList(new ArrayList<AsignacionAsesorplan>());
        }
        if (planEstudio.getAsignaturaList() == null) {
            planEstudio.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelacademico idTiponivelacademico = planEstudio.getIdTiponivelacademico();
            if (idTiponivelacademico != null) {
                idTiponivelacademico = em.getReference(idTiponivelacademico.getClass(), idTiponivelacademico.getIdTiponivelacademico());
                planEstudio.setIdTiponivelacademico(idTiponivelacademico);
            }
            Plantel idPlantel = planEstudio.getIdPlantel();
            if (idPlantel != null) {
                idPlantel = em.getReference(idPlantel.getClass(), idPlantel.getIdplantel());
                planEstudio.setIdPlantel(idPlantel);
            }
            InstitucionRegistro idInstitucion = planEstudio.getIdInstitucion();
            if (idInstitucion != null) {
                idInstitucion = em.getReference(idInstitucion.getClass(), idInstitucion.getIdInstitucion());
                planEstudio.setIdInstitucion(idInstitucion);
            }
            List<TipoTitulacion> attachedTipoTitulacionList = new ArrayList<TipoTitulacion>();
            for (TipoTitulacion tipoTitulacionListTipoTitulacionToAttach : planEstudio.getTipoTitulacionList()) {
                tipoTitulacionListTipoTitulacionToAttach = em.getReference(tipoTitulacionListTipoTitulacionToAttach.getClass(), tipoTitulacionListTipoTitulacionToAttach.getIdTipotitulacion());
                attachedTipoTitulacionList.add(tipoTitulacionListTipoTitulacionToAttach);
            }
            planEstudio.setTipoTitulacionList(attachedTipoTitulacionList);
            List<Aspirante> attachedAspiranteList = new ArrayList<Aspirante>();
            for (Aspirante aspiranteListAspiranteToAttach : planEstudio.getAspiranteList()) {
                aspiranteListAspiranteToAttach = em.getReference(aspiranteListAspiranteToAttach.getClass(), aspiranteListAspiranteToAttach.getIdAspirante());
                attachedAspiranteList.add(aspiranteListAspiranteToAttach);
            }
            planEstudio.setAspiranteList(attachedAspiranteList);
            List<Generacion> attachedGeneracionList = new ArrayList<Generacion>();
            for (Generacion generacionListGeneracionToAttach : planEstudio.getGeneracionList()) {
                generacionListGeneracionToAttach = em.getReference(generacionListGeneracionToAttach.getClass(), generacionListGeneracionToAttach.getIdGeneracion());
                attachedGeneracionList.add(generacionListGeneracionToAttach);
            }
            planEstudio.setGeneracionList(attachedGeneracionList);
            List<AsignacionAsesorplan> attachedAsignacionAsesorplanList = new ArrayList<AsignacionAsesorplan>();
            for (AsignacionAsesorplan asignacionAsesorplanListAsignacionAsesorplanToAttach : planEstudio.getAsignacionAsesorplanList()) {
                asignacionAsesorplanListAsignacionAsesorplanToAttach = em.getReference(asignacionAsesorplanListAsignacionAsesorplanToAttach.getClass(), asignacionAsesorplanListAsignacionAsesorplanToAttach.getIdAsignacionasesorplan());
                attachedAsignacionAsesorplanList.add(asignacionAsesorplanListAsignacionAsesorplanToAttach);
            }
            planEstudio.setAsignacionAsesorplanList(attachedAsignacionAsesorplanList);
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : planEstudio.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            planEstudio.setAsignaturaList(attachedAsignaturaList);
            em.persist(planEstudio);
            if (idTiponivelacademico != null) {
                idTiponivelacademico.getPlanEstudioList().add(planEstudio);
                idTiponivelacademico = em.merge(idTiponivelacademico);
            }
            if (idPlantel != null) {
                idPlantel.getPlanEstudioList().add(planEstudio);
                idPlantel = em.merge(idPlantel);
            }
            if (idInstitucion != null) {
                idInstitucion.getPlanEstudioList().add(planEstudio);
                idInstitucion = em.merge(idInstitucion);
            }
            for (TipoTitulacion tipoTitulacionListTipoTitulacion : planEstudio.getTipoTitulacionList()) {
                tipoTitulacionListTipoTitulacion.getPlanEstudioList().add(planEstudio);
                tipoTitulacionListTipoTitulacion = em.merge(tipoTitulacionListTipoTitulacion);
            }
            for (Aspirante aspiranteListAspirante : planEstudio.getAspiranteList()) {
                PlanEstudio oldIdPlanestudioOfAspiranteListAspirante = aspiranteListAspirante.getIdPlanestudio();
                aspiranteListAspirante.setIdPlanestudio(planEstudio);
                aspiranteListAspirante = em.merge(aspiranteListAspirante);
                if (oldIdPlanestudioOfAspiranteListAspirante != null) {
                    oldIdPlanestudioOfAspiranteListAspirante.getAspiranteList().remove(aspiranteListAspirante);
                    oldIdPlanestudioOfAspiranteListAspirante = em.merge(oldIdPlanestudioOfAspiranteListAspirante);
                }
            }
            for (Generacion generacionListGeneracion : planEstudio.getGeneracionList()) {
                PlanEstudio oldIdPlanestudioOfGeneracionListGeneracion = generacionListGeneracion.getIdPlanestudio();
                generacionListGeneracion.setIdPlanestudio(planEstudio);
                generacionListGeneracion = em.merge(generacionListGeneracion);
                if (oldIdPlanestudioOfGeneracionListGeneracion != null) {
                    oldIdPlanestudioOfGeneracionListGeneracion.getGeneracionList().remove(generacionListGeneracion);
                    oldIdPlanestudioOfGeneracionListGeneracion = em.merge(oldIdPlanestudioOfGeneracionListGeneracion);
                }
            }
            for (AsignacionAsesorplan asignacionAsesorplanListAsignacionAsesorplan : planEstudio.getAsignacionAsesorplanList()) {
                PlanEstudio oldIdPlanestudioOfAsignacionAsesorplanListAsignacionAsesorplan = asignacionAsesorplanListAsignacionAsesorplan.getIdPlanestudio();
                asignacionAsesorplanListAsignacionAsesorplan.setIdPlanestudio(planEstudio);
                asignacionAsesorplanListAsignacionAsesorplan = em.merge(asignacionAsesorplanListAsignacionAsesorplan);
                if (oldIdPlanestudioOfAsignacionAsesorplanListAsignacionAsesorplan != null) {
                    oldIdPlanestudioOfAsignacionAsesorplanListAsignacionAsesorplan.getAsignacionAsesorplanList().remove(asignacionAsesorplanListAsignacionAsesorplan);
                    oldIdPlanestudioOfAsignacionAsesorplanListAsignacionAsesorplan = em.merge(oldIdPlanestudioOfAsignacionAsesorplanListAsignacionAsesorplan);
                }
            }
            for (Asignatura asignaturaListAsignatura : planEstudio.getAsignaturaList()) {
                PlanEstudio oldIdPlanestudioOfAsignaturaListAsignatura = asignaturaListAsignatura.getIdPlanestudio();
                asignaturaListAsignatura.setIdPlanestudio(planEstudio);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldIdPlanestudioOfAsignaturaListAsignatura != null) {
                    oldIdPlanestudioOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldIdPlanestudioOfAsignaturaListAsignatura = em.merge(oldIdPlanestudioOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanEstudio planEstudio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEstudio persistentPlanEstudio = em.find(PlanEstudio.class, planEstudio.getIdPlanestudio());
            TipoNivelacademico idTiponivelacademicoOld = persistentPlanEstudio.getIdTiponivelacademico();
            TipoNivelacademico idTiponivelacademicoNew = planEstudio.getIdTiponivelacademico();
            Plantel idPlantelOld = persistentPlanEstudio.getIdPlantel();
            Plantel idPlantelNew = planEstudio.getIdPlantel();
            InstitucionRegistro idInstitucionOld = persistentPlanEstudio.getIdInstitucion();
            InstitucionRegistro idInstitucionNew = planEstudio.getIdInstitucion();
            List<TipoTitulacion> tipoTitulacionListOld = persistentPlanEstudio.getTipoTitulacionList();
            List<TipoTitulacion> tipoTitulacionListNew = planEstudio.getTipoTitulacionList();
            List<Aspirante> aspiranteListOld = persistentPlanEstudio.getAspiranteList();
            List<Aspirante> aspiranteListNew = planEstudio.getAspiranteList();
            List<Generacion> generacionListOld = persistentPlanEstudio.getGeneracionList();
            List<Generacion> generacionListNew = planEstudio.getGeneracionList();
            List<AsignacionAsesorplan> asignacionAsesorplanListOld = persistentPlanEstudio.getAsignacionAsesorplanList();
            List<AsignacionAsesorplan> asignacionAsesorplanListNew = planEstudio.getAsignacionAsesorplanList();
            List<Asignatura> asignaturaListOld = persistentPlanEstudio.getAsignaturaList();
            List<Asignatura> asignaturaListNew = planEstudio.getAsignaturaList();
            if (idTiponivelacademicoNew != null) {
                idTiponivelacademicoNew = em.getReference(idTiponivelacademicoNew.getClass(), idTiponivelacademicoNew.getIdTiponivelacademico());
                planEstudio.setIdTiponivelacademico(idTiponivelacademicoNew);
            }
            if (idPlantelNew != null) {
                idPlantelNew = em.getReference(idPlantelNew.getClass(), idPlantelNew.getIdplantel());
                planEstudio.setIdPlantel(idPlantelNew);
            }
            if (idInstitucionNew != null) {
                idInstitucionNew = em.getReference(idInstitucionNew.getClass(), idInstitucionNew.getIdInstitucion());
                planEstudio.setIdInstitucion(idInstitucionNew);
            }
            List<TipoTitulacion> attachedTipoTitulacionListNew = new ArrayList<TipoTitulacion>();
            for (TipoTitulacion tipoTitulacionListNewTipoTitulacionToAttach : tipoTitulacionListNew) {
                tipoTitulacionListNewTipoTitulacionToAttach = em.getReference(tipoTitulacionListNewTipoTitulacionToAttach.getClass(), tipoTitulacionListNewTipoTitulacionToAttach.getIdTipotitulacion());
                attachedTipoTitulacionListNew.add(tipoTitulacionListNewTipoTitulacionToAttach);
            }
            tipoTitulacionListNew = attachedTipoTitulacionListNew;
            planEstudio.setTipoTitulacionList(tipoTitulacionListNew);
            List<Aspirante> attachedAspiranteListNew = new ArrayList<Aspirante>();
            for (Aspirante aspiranteListNewAspiranteToAttach : aspiranteListNew) {
                aspiranteListNewAspiranteToAttach = em.getReference(aspiranteListNewAspiranteToAttach.getClass(), aspiranteListNewAspiranteToAttach.getIdAspirante());
                attachedAspiranteListNew.add(aspiranteListNewAspiranteToAttach);
            }
            aspiranteListNew = attachedAspiranteListNew;
            planEstudio.setAspiranteList(aspiranteListNew);
            List<Generacion> attachedGeneracionListNew = new ArrayList<Generacion>();
            for (Generacion generacionListNewGeneracionToAttach : generacionListNew) {
                generacionListNewGeneracionToAttach = em.getReference(generacionListNewGeneracionToAttach.getClass(), generacionListNewGeneracionToAttach.getIdGeneracion());
                attachedGeneracionListNew.add(generacionListNewGeneracionToAttach);
            }
            generacionListNew = attachedGeneracionListNew;
            planEstudio.setGeneracionList(generacionListNew);
            List<AsignacionAsesorplan> attachedAsignacionAsesorplanListNew = new ArrayList<AsignacionAsesorplan>();
            for (AsignacionAsesorplan asignacionAsesorplanListNewAsignacionAsesorplanToAttach : asignacionAsesorplanListNew) {
                asignacionAsesorplanListNewAsignacionAsesorplanToAttach = em.getReference(asignacionAsesorplanListNewAsignacionAsesorplanToAttach.getClass(), asignacionAsesorplanListNewAsignacionAsesorplanToAttach.getIdAsignacionasesorplan());
                attachedAsignacionAsesorplanListNew.add(asignacionAsesorplanListNewAsignacionAsesorplanToAttach);
            }
            asignacionAsesorplanListNew = attachedAsignacionAsesorplanListNew;
            planEstudio.setAsignacionAsesorplanList(asignacionAsesorplanListNew);
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            planEstudio.setAsignaturaList(asignaturaListNew);
            planEstudio = em.merge(planEstudio);
            if (idTiponivelacademicoOld != null && !idTiponivelacademicoOld.equals(idTiponivelacademicoNew)) {
                idTiponivelacademicoOld.getPlanEstudioList().remove(planEstudio);
                idTiponivelacademicoOld = em.merge(idTiponivelacademicoOld);
            }
            if (idTiponivelacademicoNew != null && !idTiponivelacademicoNew.equals(idTiponivelacademicoOld)) {
                idTiponivelacademicoNew.getPlanEstudioList().add(planEstudio);
                idTiponivelacademicoNew = em.merge(idTiponivelacademicoNew);
            }
            if (idPlantelOld != null && !idPlantelOld.equals(idPlantelNew)) {
                idPlantelOld.getPlanEstudioList().remove(planEstudio);
                idPlantelOld = em.merge(idPlantelOld);
            }
            if (idPlantelNew != null && !idPlantelNew.equals(idPlantelOld)) {
                idPlantelNew.getPlanEstudioList().add(planEstudio);
                idPlantelNew = em.merge(idPlantelNew);
            }
            if (idInstitucionOld != null && !idInstitucionOld.equals(idInstitucionNew)) {
                idInstitucionOld.getPlanEstudioList().remove(planEstudio);
                idInstitucionOld = em.merge(idInstitucionOld);
            }
            if (idInstitucionNew != null && !idInstitucionNew.equals(idInstitucionOld)) {
                idInstitucionNew.getPlanEstudioList().add(planEstudio);
                idInstitucionNew = em.merge(idInstitucionNew);
            }
            for (TipoTitulacion tipoTitulacionListOldTipoTitulacion : tipoTitulacionListOld) {
                if (!tipoTitulacionListNew.contains(tipoTitulacionListOldTipoTitulacion)) {
                    tipoTitulacionListOldTipoTitulacion.getPlanEstudioList().remove(planEstudio);
                    tipoTitulacionListOldTipoTitulacion = em.merge(tipoTitulacionListOldTipoTitulacion);
                }
            }
            for (TipoTitulacion tipoTitulacionListNewTipoTitulacion : tipoTitulacionListNew) {
                if (!tipoTitulacionListOld.contains(tipoTitulacionListNewTipoTitulacion)) {
                    tipoTitulacionListNewTipoTitulacion.getPlanEstudioList().add(planEstudio);
                    tipoTitulacionListNewTipoTitulacion = em.merge(tipoTitulacionListNewTipoTitulacion);
                }
            }
            for (Aspirante aspiranteListOldAspirante : aspiranteListOld) {
                if (!aspiranteListNew.contains(aspiranteListOldAspirante)) {
                    aspiranteListOldAspirante.setIdPlanestudio(null);
                    aspiranteListOldAspirante = em.merge(aspiranteListOldAspirante);
                }
            }
            for (Aspirante aspiranteListNewAspirante : aspiranteListNew) {
                if (!aspiranteListOld.contains(aspiranteListNewAspirante)) {
                    PlanEstudio oldIdPlanestudioOfAspiranteListNewAspirante = aspiranteListNewAspirante.getIdPlanestudio();
                    aspiranteListNewAspirante.setIdPlanestudio(planEstudio);
                    aspiranteListNewAspirante = em.merge(aspiranteListNewAspirante);
                    if (oldIdPlanestudioOfAspiranteListNewAspirante != null && !oldIdPlanestudioOfAspiranteListNewAspirante.equals(planEstudio)) {
                        oldIdPlanestudioOfAspiranteListNewAspirante.getAspiranteList().remove(aspiranteListNewAspirante);
                        oldIdPlanestudioOfAspiranteListNewAspirante = em.merge(oldIdPlanestudioOfAspiranteListNewAspirante);
                    }
                }
            }
            for (Generacion generacionListOldGeneracion : generacionListOld) {
                if (!generacionListNew.contains(generacionListOldGeneracion)) {
                    generacionListOldGeneracion.setIdPlanestudio(null);
                    generacionListOldGeneracion = em.merge(generacionListOldGeneracion);
                }
            }
            for (Generacion generacionListNewGeneracion : generacionListNew) {
                if (!generacionListOld.contains(generacionListNewGeneracion)) {
                    PlanEstudio oldIdPlanestudioOfGeneracionListNewGeneracion = generacionListNewGeneracion.getIdPlanestudio();
                    generacionListNewGeneracion.setIdPlanestudio(planEstudio);
                    generacionListNewGeneracion = em.merge(generacionListNewGeneracion);
                    if (oldIdPlanestudioOfGeneracionListNewGeneracion != null && !oldIdPlanestudioOfGeneracionListNewGeneracion.equals(planEstudio)) {
                        oldIdPlanestudioOfGeneracionListNewGeneracion.getGeneracionList().remove(generacionListNewGeneracion);
                        oldIdPlanestudioOfGeneracionListNewGeneracion = em.merge(oldIdPlanestudioOfGeneracionListNewGeneracion);
                    }
                }
            }
            for (AsignacionAsesorplan asignacionAsesorplanListOldAsignacionAsesorplan : asignacionAsesorplanListOld) {
                if (!asignacionAsesorplanListNew.contains(asignacionAsesorplanListOldAsignacionAsesorplan)) {
                    asignacionAsesorplanListOldAsignacionAsesorplan.setIdPlanestudio(null);
                    asignacionAsesorplanListOldAsignacionAsesorplan = em.merge(asignacionAsesorplanListOldAsignacionAsesorplan);
                }
            }
            for (AsignacionAsesorplan asignacionAsesorplanListNewAsignacionAsesorplan : asignacionAsesorplanListNew) {
                if (!asignacionAsesorplanListOld.contains(asignacionAsesorplanListNewAsignacionAsesorplan)) {
                    PlanEstudio oldIdPlanestudioOfAsignacionAsesorplanListNewAsignacionAsesorplan = asignacionAsesorplanListNewAsignacionAsesorplan.getIdPlanestudio();
                    asignacionAsesorplanListNewAsignacionAsesorplan.setIdPlanestudio(planEstudio);
                    asignacionAsesorplanListNewAsignacionAsesorplan = em.merge(asignacionAsesorplanListNewAsignacionAsesorplan);
                    if (oldIdPlanestudioOfAsignacionAsesorplanListNewAsignacionAsesorplan != null && !oldIdPlanestudioOfAsignacionAsesorplanListNewAsignacionAsesorplan.equals(planEstudio)) {
                        oldIdPlanestudioOfAsignacionAsesorplanListNewAsignacionAsesorplan.getAsignacionAsesorplanList().remove(asignacionAsesorplanListNewAsignacionAsesorplan);
                        oldIdPlanestudioOfAsignacionAsesorplanListNewAsignacionAsesorplan = em.merge(oldIdPlanestudioOfAsignacionAsesorplanListNewAsignacionAsesorplan);
                    }
                }
            }
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.setIdPlanestudio(null);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    PlanEstudio oldIdPlanestudioOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getIdPlanestudio();
                    asignaturaListNewAsignatura.setIdPlanestudio(planEstudio);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldIdPlanestudioOfAsignaturaListNewAsignatura != null && !oldIdPlanestudioOfAsignaturaListNewAsignatura.equals(planEstudio)) {
                        oldIdPlanestudioOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldIdPlanestudioOfAsignaturaListNewAsignatura = em.merge(oldIdPlanestudioOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planEstudio.getIdPlanestudio();
                if (findPlanEstudio(id) == null) {
                    throw new NonexistentEntityException("The planEstudio with id " + id + " no longer exists.");
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
            PlanEstudio planEstudio;
            try {
                planEstudio = em.getReference(PlanEstudio.class, id);
                planEstudio.getIdPlanestudio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planEstudio with id " + id + " no longer exists.", enfe);
            }
            TipoNivelacademico idTiponivelacademico = planEstudio.getIdTiponivelacademico();
            if (idTiponivelacademico != null) {
                idTiponivelacademico.getPlanEstudioList().remove(planEstudio);
                idTiponivelacademico = em.merge(idTiponivelacademico);
            }
            Plantel idPlantel = planEstudio.getIdPlantel();
            if (idPlantel != null) {
                idPlantel.getPlanEstudioList().remove(planEstudio);
                idPlantel = em.merge(idPlantel);
            }
            InstitucionRegistro idInstitucion = planEstudio.getIdInstitucion();
            if (idInstitucion != null) {
                idInstitucion.getPlanEstudioList().remove(planEstudio);
                idInstitucion = em.merge(idInstitucion);
            }
            List<TipoTitulacion> tipoTitulacionList = planEstudio.getTipoTitulacionList();
            for (TipoTitulacion tipoTitulacionListTipoTitulacion : tipoTitulacionList) {
                tipoTitulacionListTipoTitulacion.getPlanEstudioList().remove(planEstudio);
                tipoTitulacionListTipoTitulacion = em.merge(tipoTitulacionListTipoTitulacion);
            }
            List<Aspirante> aspiranteList = planEstudio.getAspiranteList();
            for (Aspirante aspiranteListAspirante : aspiranteList) {
                aspiranteListAspirante.setIdPlanestudio(null);
                aspiranteListAspirante = em.merge(aspiranteListAspirante);
            }
            List<Generacion> generacionList = planEstudio.getGeneracionList();
            for (Generacion generacionListGeneracion : generacionList) {
                generacionListGeneracion.setIdPlanestudio(null);
                generacionListGeneracion = em.merge(generacionListGeneracion);
            }
            List<AsignacionAsesorplan> asignacionAsesorplanList = planEstudio.getAsignacionAsesorplanList();
            for (AsignacionAsesorplan asignacionAsesorplanListAsignacionAsesorplan : asignacionAsesorplanList) {
                asignacionAsesorplanListAsignacionAsesorplan.setIdPlanestudio(null);
                asignacionAsesorplanListAsignacionAsesorplan = em.merge(asignacionAsesorplanListAsignacionAsesorplan);
            }
            List<Asignatura> asignaturaList = planEstudio.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.setIdPlanestudio(null);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            em.remove(planEstudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanEstudio> findPlanEstudioEntities() {
        return findPlanEstudioEntities(true, -1, -1);
    }

    public List<PlanEstudio> findPlanEstudioEntities(int maxResults, int firstResult) {
        return findPlanEstudioEntities(false, maxResults, firstResult);
    }

    private List<PlanEstudio> findPlanEstudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PlanEstudio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PlanEstudio findPlanEstudio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanEstudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanEstudioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PlanEstudio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
