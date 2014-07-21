/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.integracion.entity.AsignacionGrupoexamenextemporaneo;
import com.utez.integracion.entity.GrupoExamenextemporaneo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class GrupoExamenextemporaneoJpaController implements Serializable {

    public GrupoExamenextemporaneoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoExamenextemporaneo grupoExamenextemporaneo) {
        if (grupoExamenextemporaneo.getAsignacionGrupoexamenextemporaneoList() == null) {
            grupoExamenextemporaneo.setAsignacionGrupoexamenextemporaneoList(new ArrayList<AsignacionGrupoexamenextemporaneo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesor idAsesor = grupoExamenextemporaneo.getIdAsesor();
            if (idAsesor != null) {
                idAsesor = em.getReference(idAsesor.getClass(), idAsesor.getIdasesor());
                grupoExamenextemporaneo.setIdAsesor(idAsesor);
            }
            List<AsignacionGrupoexamenextemporaneo> attachedAsignacionGrupoexamenextemporaneoList = new ArrayList<AsignacionGrupoexamenextemporaneo>();
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach : grupoExamenextemporaneo.getAsignacionGrupoexamenextemporaneoList()) {
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach = em.getReference(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach.getClass(), asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach.getIdAsignaciongrupoexamenextemporaneo());
                attachedAsignacionGrupoexamenextemporaneoList.add(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach);
            }
            grupoExamenextemporaneo.setAsignacionGrupoexamenextemporaneoList(attachedAsignacionGrupoexamenextemporaneoList);
            em.persist(grupoExamenextemporaneo);
            if (idAsesor != null) {
                idAsesor.getGrupoExamenextemporaneoList().add(grupoExamenextemporaneo);
                idAsesor = em.merge(idAsesor);
            }
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo : grupoExamenextemporaneo.getAsignacionGrupoexamenextemporaneoList()) {
                GrupoExamenextemporaneo oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo();
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.setIdGrupoexamenextemporaneo(grupoExamenextemporaneo);
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
                if (oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo != null) {
                    oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
                    oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = em.merge(oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoExamenextemporaneo grupoExamenextemporaneo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoExamenextemporaneo persistentGrupoExamenextemporaneo = em.find(GrupoExamenextemporaneo.class, grupoExamenextemporaneo.getIdGrupoexamenextemporaneo());
            Asesor idAsesorOld = persistentGrupoExamenextemporaneo.getIdAsesor();
            Asesor idAsesorNew = grupoExamenextemporaneo.getIdAsesor();
            List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoListOld = persistentGrupoExamenextemporaneo.getAsignacionGrupoexamenextemporaneoList();
            List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoListNew = grupoExamenextemporaneo.getAsignacionGrupoexamenextemporaneoList();
            if (idAsesorNew != null) {
                idAsesorNew = em.getReference(idAsesorNew.getClass(), idAsesorNew.getIdasesor());
                grupoExamenextemporaneo.setIdAsesor(idAsesorNew);
            }
            List<AsignacionGrupoexamenextemporaneo> attachedAsignacionGrupoexamenextemporaneoListNew = new ArrayList<AsignacionGrupoexamenextemporaneo>();
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach : asignacionGrupoexamenextemporaneoListNew) {
                asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach = em.getReference(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach.getClass(), asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach.getIdAsignaciongrupoexamenextemporaneo());
                attachedAsignacionGrupoexamenextemporaneoListNew.add(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach);
            }
            asignacionGrupoexamenextemporaneoListNew = attachedAsignacionGrupoexamenextemporaneoListNew;
            grupoExamenextemporaneo.setAsignacionGrupoexamenextemporaneoList(asignacionGrupoexamenextemporaneoListNew);
            grupoExamenextemporaneo = em.merge(grupoExamenextemporaneo);
            if (idAsesorOld != null && !idAsesorOld.equals(idAsesorNew)) {
                idAsesorOld.getGrupoExamenextemporaneoList().remove(grupoExamenextemporaneo);
                idAsesorOld = em.merge(idAsesorOld);
            }
            if (idAsesorNew != null && !idAsesorNew.equals(idAsesorOld)) {
                idAsesorNew.getGrupoExamenextemporaneoList().add(grupoExamenextemporaneo);
                idAsesorNew = em.merge(idAsesorNew);
            }
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo : asignacionGrupoexamenextemporaneoListOld) {
                if (!asignacionGrupoexamenextemporaneoListNew.contains(asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo)) {
                    asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo.setIdGrupoexamenextemporaneo(null);
                    asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo);
                }
            }
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo : asignacionGrupoexamenextemporaneoListNew) {
                if (!asignacionGrupoexamenextemporaneoListOld.contains(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo)) {
                    GrupoExamenextemporaneo oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo = asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.getIdGrupoexamenextemporaneo();
                    asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.setIdGrupoexamenextemporaneo(grupoExamenextemporaneo);
                    asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo);
                    if (oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo != null && !oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.equals(grupoExamenextemporaneo)) {
                        oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo);
                        oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo = em.merge(oldIdGrupoexamenextemporaneoOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupoExamenextemporaneo.getIdGrupoexamenextemporaneo();
                if (findGrupoExamenextemporaneo(id) == null) {
                    throw new NonexistentEntityException("The grupoExamenextemporaneo with id " + id + " no longer exists.");
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
            GrupoExamenextemporaneo grupoExamenextemporaneo;
            try {
                grupoExamenextemporaneo = em.getReference(GrupoExamenextemporaneo.class, id);
                grupoExamenextemporaneo.getIdGrupoexamenextemporaneo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoExamenextemporaneo with id " + id + " no longer exists.", enfe);
            }
            Asesor idAsesor = grupoExamenextemporaneo.getIdAsesor();
            if (idAsesor != null) {
                idAsesor.getGrupoExamenextemporaneoList().remove(grupoExamenextemporaneo);
                idAsesor = em.merge(idAsesor);
            }
            List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoList = grupoExamenextemporaneo.getAsignacionGrupoexamenextemporaneoList();
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo : asignacionGrupoexamenextemporaneoList) {
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.setIdGrupoexamenextemporaneo(null);
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
            }
            em.remove(grupoExamenextemporaneo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GrupoExamenextemporaneo> findGrupoExamenextemporaneoEntities() {
        return findGrupoExamenextemporaneoEntities(true, -1, -1);
    }

    public List<GrupoExamenextemporaneo> findGrupoExamenextemporaneoEntities(int maxResults, int firstResult) {
        return findGrupoExamenextemporaneoEntities(false, maxResults, firstResult);
    }

    private List<GrupoExamenextemporaneo> findGrupoExamenextemporaneoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from GrupoExamenextemporaneo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public GrupoExamenextemporaneo findGrupoExamenextemporaneo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoExamenextemporaneo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoExamenextemporaneoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from GrupoExamenextemporaneo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
