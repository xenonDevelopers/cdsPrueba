/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudio;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Documento;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.TipoTitulacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoTitulacionJpaController implements Serializable {

    public TipoTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoTitulacion tipoTitulacion) {
        if (tipoTitulacion.getPlanEstudioList() == null) {
            tipoTitulacion.setPlanEstudioList(new ArrayList<PlanEstudio>());
        }
        if (tipoTitulacion.getDocumentoList() == null) {
            tipoTitulacion.setDocumentoList(new ArrayList<Documento>());
        }
        if (tipoTitulacion.getSolicitudTitulacionList() == null) {
            tipoTitulacion.setSolicitudTitulacionList(new ArrayList<SolicitudTitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanEstudio> attachedPlanEstudioList = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListPlanEstudioToAttach : tipoTitulacion.getPlanEstudioList()) {
                planEstudioListPlanEstudioToAttach = em.getReference(planEstudioListPlanEstudioToAttach.getClass(), planEstudioListPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioList.add(planEstudioListPlanEstudioToAttach);
            }
            tipoTitulacion.setPlanEstudioList(attachedPlanEstudioList);
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : tipoTitulacion.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getIdDocumento());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            tipoTitulacion.setDocumentoList(attachedDocumentoList);
            List<SolicitudTitulacion> attachedSolicitudTitulacionList = new ArrayList<SolicitudTitulacion>();
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacionToAttach : tipoTitulacion.getSolicitudTitulacionList()) {
                solicitudTitulacionListSolicitudTitulacionToAttach = em.getReference(solicitudTitulacionListSolicitudTitulacionToAttach.getClass(), solicitudTitulacionListSolicitudTitulacionToAttach.getIdSolicitudtitulacion());
                attachedSolicitudTitulacionList.add(solicitudTitulacionListSolicitudTitulacionToAttach);
            }
            tipoTitulacion.setSolicitudTitulacionList(attachedSolicitudTitulacionList);
            em.persist(tipoTitulacion);
            for (PlanEstudio planEstudioListPlanEstudio : tipoTitulacion.getPlanEstudioList()) {
                planEstudioListPlanEstudio.getTipoTitulacionList().add(tipoTitulacion);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
            }
            for (Documento documentoListDocumento : tipoTitulacion.getDocumentoList()) {
                documentoListDocumento.getTipoTitulacionList().add(tipoTitulacion);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacion : tipoTitulacion.getSolicitudTitulacionList()) {
                TipoTitulacion oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion = solicitudTitulacionListSolicitudTitulacion.getIdTipotitulacion();
                solicitudTitulacionListSolicitudTitulacion.setIdTipotitulacion(tipoTitulacion);
                solicitudTitulacionListSolicitudTitulacion = em.merge(solicitudTitulacionListSolicitudTitulacion);
                if (oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion != null) {
                    oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion.getSolicitudTitulacionList().remove(solicitudTitulacionListSolicitudTitulacion);
                    oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion = em.merge(oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTitulacion tipoTitulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTitulacion persistentTipoTitulacion = em.find(TipoTitulacion.class, tipoTitulacion.getIdTipotitulacion());
            List<PlanEstudio> planEstudioListOld = persistentTipoTitulacion.getPlanEstudioList();
            List<PlanEstudio> planEstudioListNew = tipoTitulacion.getPlanEstudioList();
            List<Documento> documentoListOld = persistentTipoTitulacion.getDocumentoList();
            List<Documento> documentoListNew = tipoTitulacion.getDocumentoList();
            List<SolicitudTitulacion> solicitudTitulacionListOld = persistentTipoTitulacion.getSolicitudTitulacionList();
            List<SolicitudTitulacion> solicitudTitulacionListNew = tipoTitulacion.getSolicitudTitulacionList();
            List<PlanEstudio> attachedPlanEstudioListNew = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListNewPlanEstudioToAttach : planEstudioListNew) {
                planEstudioListNewPlanEstudioToAttach = em.getReference(planEstudioListNewPlanEstudioToAttach.getClass(), planEstudioListNewPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioListNew.add(planEstudioListNewPlanEstudioToAttach);
            }
            planEstudioListNew = attachedPlanEstudioListNew;
            tipoTitulacion.setPlanEstudioList(planEstudioListNew);
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getIdDocumento());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            tipoTitulacion.setDocumentoList(documentoListNew);
            List<SolicitudTitulacion> attachedSolicitudTitulacionListNew = new ArrayList<SolicitudTitulacion>();
            for (SolicitudTitulacion solicitudTitulacionListNewSolicitudTitulacionToAttach : solicitudTitulacionListNew) {
                solicitudTitulacionListNewSolicitudTitulacionToAttach = em.getReference(solicitudTitulacionListNewSolicitudTitulacionToAttach.getClass(), solicitudTitulacionListNewSolicitudTitulacionToAttach.getIdSolicitudtitulacion());
                attachedSolicitudTitulacionListNew.add(solicitudTitulacionListNewSolicitudTitulacionToAttach);
            }
            solicitudTitulacionListNew = attachedSolicitudTitulacionListNew;
            tipoTitulacion.setSolicitudTitulacionList(solicitudTitulacionListNew);
            tipoTitulacion = em.merge(tipoTitulacion);
            for (PlanEstudio planEstudioListOldPlanEstudio : planEstudioListOld) {
                if (!planEstudioListNew.contains(planEstudioListOldPlanEstudio)) {
                    planEstudioListOldPlanEstudio.getTipoTitulacionList().remove(tipoTitulacion);
                    planEstudioListOldPlanEstudio = em.merge(planEstudioListOldPlanEstudio);
                }
            }
            for (PlanEstudio planEstudioListNewPlanEstudio : planEstudioListNew) {
                if (!planEstudioListOld.contains(planEstudioListNewPlanEstudio)) {
                    planEstudioListNewPlanEstudio.getTipoTitulacionList().add(tipoTitulacion);
                    planEstudioListNewPlanEstudio = em.merge(planEstudioListNewPlanEstudio);
                }
            }
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.getTipoTitulacionList().remove(tipoTitulacion);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    documentoListNewDocumento.getTipoTitulacionList().add(tipoTitulacion);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListOldSolicitudTitulacion : solicitudTitulacionListOld) {
                if (!solicitudTitulacionListNew.contains(solicitudTitulacionListOldSolicitudTitulacion)) {
                    solicitudTitulacionListOldSolicitudTitulacion.setIdTipotitulacion(null);
                    solicitudTitulacionListOldSolicitudTitulacion = em.merge(solicitudTitulacionListOldSolicitudTitulacion);
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListNewSolicitudTitulacion : solicitudTitulacionListNew) {
                if (!solicitudTitulacionListOld.contains(solicitudTitulacionListNewSolicitudTitulacion)) {
                    TipoTitulacion oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion = solicitudTitulacionListNewSolicitudTitulacion.getIdTipotitulacion();
                    solicitudTitulacionListNewSolicitudTitulacion.setIdTipotitulacion(tipoTitulacion);
                    solicitudTitulacionListNewSolicitudTitulacion = em.merge(solicitudTitulacionListNewSolicitudTitulacion);
                    if (oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion != null && !oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion.equals(tipoTitulacion)) {
                        oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion.getSolicitudTitulacionList().remove(solicitudTitulacionListNewSolicitudTitulacion);
                        oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion = em.merge(oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoTitulacion.getIdTipotitulacion();
                if (findTipoTitulacion(id) == null) {
                    throw new NonexistentEntityException("The tipoTitulacion with id " + id + " no longer exists.");
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
            TipoTitulacion tipoTitulacion;
            try {
                tipoTitulacion = em.getReference(TipoTitulacion.class, id);
                tipoTitulacion.getIdTipotitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTitulacion with id " + id + " no longer exists.", enfe);
            }
            List<PlanEstudio> planEstudioList = tipoTitulacion.getPlanEstudioList();
            for (PlanEstudio planEstudioListPlanEstudio : planEstudioList) {
                planEstudioListPlanEstudio.getTipoTitulacionList().remove(tipoTitulacion);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
            }
            List<Documento> documentoList = tipoTitulacion.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.getTipoTitulacionList().remove(tipoTitulacion);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            List<SolicitudTitulacion> solicitudTitulacionList = tipoTitulacion.getSolicitudTitulacionList();
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacion : solicitudTitulacionList) {
                solicitudTitulacionListSolicitudTitulacion.setIdTipotitulacion(null);
                solicitudTitulacionListSolicitudTitulacion = em.merge(solicitudTitulacionListSolicitudTitulacion);
            }
            em.remove(tipoTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTitulacion> findTipoTitulacionEntities() {
        return findTipoTitulacionEntities(true, -1, -1);
    }

    public List<TipoTitulacion> findTipoTitulacionEntities(int maxResults, int firstResult) {
        return findTipoTitulacionEntities(false, maxResults, firstResult);
    }

    private List<TipoTitulacion> findTipoTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoTitulacion findTipoTitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
