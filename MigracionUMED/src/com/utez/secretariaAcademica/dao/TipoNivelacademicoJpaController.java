/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudio;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.FormacionAcademica;
import com.utez.integracion.entity.TipoNivelacademico;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoNivelacademicoJpaController implements Serializable {

    public TipoNivelacademicoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoNivelacademico tipoNivelacademico) {
        if (tipoNivelacademico.getPlanEstudioList() == null) {
            tipoNivelacademico.setPlanEstudioList(new ArrayList<PlanEstudio>());
        }
        if (tipoNivelacademico.getFormacionAcademicaList() == null) {
            tipoNivelacademico.setFormacionAcademicaList(new ArrayList<FormacionAcademica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanEstudio> attachedPlanEstudioList = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListPlanEstudioToAttach : tipoNivelacademico.getPlanEstudioList()) {
                planEstudioListPlanEstudioToAttach = em.getReference(planEstudioListPlanEstudioToAttach.getClass(), planEstudioListPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioList.add(planEstudioListPlanEstudioToAttach);
            }
            tipoNivelacademico.setPlanEstudioList(attachedPlanEstudioList);
            List<FormacionAcademica> attachedFormacionAcademicaList = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListFormacionAcademicaToAttach : tipoNivelacademico.getFormacionAcademicaList()) {
                formacionAcademicaListFormacionAcademicaToAttach = em.getReference(formacionAcademicaListFormacionAcademicaToAttach.getClass(), formacionAcademicaListFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaList.add(formacionAcademicaListFormacionAcademicaToAttach);
            }
            tipoNivelacademico.setFormacionAcademicaList(attachedFormacionAcademicaList);
            em.persist(tipoNivelacademico);
            for (PlanEstudio planEstudioListPlanEstudio : tipoNivelacademico.getPlanEstudioList()) {
                TipoNivelacademico oldIdTiponivelacademicoOfPlanEstudioListPlanEstudio = planEstudioListPlanEstudio.getIdTiponivelacademico();
                planEstudioListPlanEstudio.setIdTiponivelacademico(tipoNivelacademico);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
                if (oldIdTiponivelacademicoOfPlanEstudioListPlanEstudio != null) {
                    oldIdTiponivelacademicoOfPlanEstudioListPlanEstudio.getPlanEstudioList().remove(planEstudioListPlanEstudio);
                    oldIdTiponivelacademicoOfPlanEstudioListPlanEstudio = em.merge(oldIdTiponivelacademicoOfPlanEstudioListPlanEstudio);
                }
            }
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : tipoNivelacademico.getFormacionAcademicaList()) {
                TipoNivelacademico oldIdTiponivelacademicoOfFormacionAcademicaListFormacionAcademica = formacionAcademicaListFormacionAcademica.getIdTiponivelacademico();
                formacionAcademicaListFormacionAcademica.setIdTiponivelacademico(tipoNivelacademico);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
                if (oldIdTiponivelacademicoOfFormacionAcademicaListFormacionAcademica != null) {
                    oldIdTiponivelacademicoOfFormacionAcademicaListFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListFormacionAcademica);
                    oldIdTiponivelacademicoOfFormacionAcademicaListFormacionAcademica = em.merge(oldIdTiponivelacademicoOfFormacionAcademicaListFormacionAcademica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoNivelacademico tipoNivelacademico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelacademico persistentTipoNivelacademico = em.find(TipoNivelacademico.class, tipoNivelacademico.getIdTiponivelacademico());
            List<PlanEstudio> planEstudioListOld = persistentTipoNivelacademico.getPlanEstudioList();
            List<PlanEstudio> planEstudioListNew = tipoNivelacademico.getPlanEstudioList();
            List<FormacionAcademica> formacionAcademicaListOld = persistentTipoNivelacademico.getFormacionAcademicaList();
            List<FormacionAcademica> formacionAcademicaListNew = tipoNivelacademico.getFormacionAcademicaList();
            List<PlanEstudio> attachedPlanEstudioListNew = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListNewPlanEstudioToAttach : planEstudioListNew) {
                planEstudioListNewPlanEstudioToAttach = em.getReference(planEstudioListNewPlanEstudioToAttach.getClass(), planEstudioListNewPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioListNew.add(planEstudioListNewPlanEstudioToAttach);
            }
            planEstudioListNew = attachedPlanEstudioListNew;
            tipoNivelacademico.setPlanEstudioList(planEstudioListNew);
            List<FormacionAcademica> attachedFormacionAcademicaListNew = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademicaToAttach : formacionAcademicaListNew) {
                formacionAcademicaListNewFormacionAcademicaToAttach = em.getReference(formacionAcademicaListNewFormacionAcademicaToAttach.getClass(), formacionAcademicaListNewFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaListNew.add(formacionAcademicaListNewFormacionAcademicaToAttach);
            }
            formacionAcademicaListNew = attachedFormacionAcademicaListNew;
            tipoNivelacademico.setFormacionAcademicaList(formacionAcademicaListNew);
            tipoNivelacademico = em.merge(tipoNivelacademico);
            for (PlanEstudio planEstudioListOldPlanEstudio : planEstudioListOld) {
                if (!planEstudioListNew.contains(planEstudioListOldPlanEstudio)) {
                    planEstudioListOldPlanEstudio.setIdTiponivelacademico(null);
                    planEstudioListOldPlanEstudio = em.merge(planEstudioListOldPlanEstudio);
                }
            }
            for (PlanEstudio planEstudioListNewPlanEstudio : planEstudioListNew) {
                if (!planEstudioListOld.contains(planEstudioListNewPlanEstudio)) {
                    TipoNivelacademico oldIdTiponivelacademicoOfPlanEstudioListNewPlanEstudio = planEstudioListNewPlanEstudio.getIdTiponivelacademico();
                    planEstudioListNewPlanEstudio.setIdTiponivelacademico(tipoNivelacademico);
                    planEstudioListNewPlanEstudio = em.merge(planEstudioListNewPlanEstudio);
                    if (oldIdTiponivelacademicoOfPlanEstudioListNewPlanEstudio != null && !oldIdTiponivelacademicoOfPlanEstudioListNewPlanEstudio.equals(tipoNivelacademico)) {
                        oldIdTiponivelacademicoOfPlanEstudioListNewPlanEstudio.getPlanEstudioList().remove(planEstudioListNewPlanEstudio);
                        oldIdTiponivelacademicoOfPlanEstudioListNewPlanEstudio = em.merge(oldIdTiponivelacademicoOfPlanEstudioListNewPlanEstudio);
                    }
                }
            }
            for (FormacionAcademica formacionAcademicaListOldFormacionAcademica : formacionAcademicaListOld) {
                if (!formacionAcademicaListNew.contains(formacionAcademicaListOldFormacionAcademica)) {
                    formacionAcademicaListOldFormacionAcademica.setIdTiponivelacademico(null);
                    formacionAcademicaListOldFormacionAcademica = em.merge(formacionAcademicaListOldFormacionAcademica);
                }
            }
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademica : formacionAcademicaListNew) {
                if (!formacionAcademicaListOld.contains(formacionAcademicaListNewFormacionAcademica)) {
                    TipoNivelacademico oldIdTiponivelacademicoOfFormacionAcademicaListNewFormacionAcademica = formacionAcademicaListNewFormacionAcademica.getIdTiponivelacademico();
                    formacionAcademicaListNewFormacionAcademica.setIdTiponivelacademico(tipoNivelacademico);
                    formacionAcademicaListNewFormacionAcademica = em.merge(formacionAcademicaListNewFormacionAcademica);
                    if (oldIdTiponivelacademicoOfFormacionAcademicaListNewFormacionAcademica != null && !oldIdTiponivelacademicoOfFormacionAcademicaListNewFormacionAcademica.equals(tipoNivelacademico)) {
                        oldIdTiponivelacademicoOfFormacionAcademicaListNewFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListNewFormacionAcademica);
                        oldIdTiponivelacademicoOfFormacionAcademicaListNewFormacionAcademica = em.merge(oldIdTiponivelacademicoOfFormacionAcademicaListNewFormacionAcademica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoNivelacademico.getIdTiponivelacademico();
                if (findTipoNivelacademico(id) == null) {
                    throw new NonexistentEntityException("The tipoNivelacademico with id " + id + " no longer exists.");
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
            TipoNivelacademico tipoNivelacademico;
            try {
                tipoNivelacademico = em.getReference(TipoNivelacademico.class, id);
                tipoNivelacademico.getIdTiponivelacademico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoNivelacademico with id " + id + " no longer exists.", enfe);
            }
            List<PlanEstudio> planEstudioList = tipoNivelacademico.getPlanEstudioList();
            for (PlanEstudio planEstudioListPlanEstudio : planEstudioList) {
                planEstudioListPlanEstudio.setIdTiponivelacademico(null);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
            }
            List<FormacionAcademica> formacionAcademicaList = tipoNivelacademico.getFormacionAcademicaList();
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : formacionAcademicaList) {
                formacionAcademicaListFormacionAcademica.setIdTiponivelacademico(null);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
            }
            em.remove(tipoNivelacademico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoNivelacademico> findTipoNivelacademicoEntities() {
        return findTipoNivelacademicoEntities(true, -1, -1);
    }

    public List<TipoNivelacademico> findTipoNivelacademicoEntities(int maxResults, int firstResult) {
        return findTipoNivelacademicoEntities(false, maxResults, firstResult);
    }

    private List<TipoNivelacademico> findTipoNivelacademicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoNivelacademico as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoNivelacademico findTipoNivelacademico(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoNivelacademico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoNivelacademicoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoNivelacademico as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
