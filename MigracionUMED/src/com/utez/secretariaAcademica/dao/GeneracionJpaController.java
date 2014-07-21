/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.integracion.entity.Generacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Periodo;
import com.utez.secretariaAcademica.entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class GeneracionJpaController implements Serializable {

    public GeneracionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Generacion generacion) {
        if (generacion.getGrupoList() == null) {
            generacion.setGrupoList(new ArrayList<Grupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEstudio idPlanestudio = generacion.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio = em.getReference(idPlanestudio.getClass(), idPlanestudio.getIdPlanestudio());
                generacion.setIdPlanestudio(idPlanestudio);
            }
            Periodo idPeriodo = generacion.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo = em.getReference(idPeriodo.getClass(), idPeriodo.getPeriodo());
                generacion.setIdPeriodo(idPeriodo);
            }
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : generacion.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            generacion.setGrupoList(attachedGrupoList);
            em.persist(generacion);
            if (idPlanestudio != null) {
                idPlanestudio.getGeneracionList().add(generacion);
                idPlanestudio = em.merge(idPlanestudio);
            }
            if (idPeriodo != null) {
                idPeriodo.getGeneracionList().add(generacion);
                idPeriodo = em.merge(idPeriodo);
            }
            for (Grupo grupoListGrupo : generacion.getGrupoList()) {
                Generacion oldIdGeneracionOfGrupoListGrupo = grupoListGrupo.getIdGeneracion();
                grupoListGrupo.setIdGeneracion(generacion);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdGeneracionOfGrupoListGrupo != null) {
                    oldIdGeneracionOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdGeneracionOfGrupoListGrupo = em.merge(oldIdGeneracionOfGrupoListGrupo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Generacion generacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Generacion persistentGeneracion = em.find(Generacion.class, generacion.getIdGeneracion());
            PlanEstudio idPlanestudioOld = persistentGeneracion.getIdPlanestudio();
            PlanEstudio idPlanestudioNew = generacion.getIdPlanestudio();
            Periodo idPeriodoOld = persistentGeneracion.getIdPeriodo();
            Periodo idPeriodoNew = generacion.getIdPeriodo();
            List<Grupo> grupoListOld = persistentGeneracion.getGrupoList();
            List<Grupo> grupoListNew = generacion.getGrupoList();
            if (idPlanestudioNew != null) {
                idPlanestudioNew = em.getReference(idPlanestudioNew.getClass(), idPlanestudioNew.getIdPlanestudio());
                generacion.setIdPlanestudio(idPlanestudioNew);
            }
            if (idPeriodoNew != null) {
                idPeriodoNew = em.getReference(idPeriodoNew.getClass(), idPeriodoNew.getPeriodo());
                generacion.setIdPeriodo(idPeriodoNew);
            }
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            generacion.setGrupoList(grupoListNew);
            generacion = em.merge(generacion);
            if (idPlanestudioOld != null && !idPlanestudioOld.equals(idPlanestudioNew)) {
                idPlanestudioOld.getGeneracionList().remove(generacion);
                idPlanestudioOld = em.merge(idPlanestudioOld);
            }
            if (idPlanestudioNew != null && !idPlanestudioNew.equals(idPlanestudioOld)) {
                idPlanestudioNew.getGeneracionList().add(generacion);
                idPlanestudioNew = em.merge(idPlanestudioNew);
            }
            if (idPeriodoOld != null && !idPeriodoOld.equals(idPeriodoNew)) {
                idPeriodoOld.getGeneracionList().remove(generacion);
                idPeriodoOld = em.merge(idPeriodoOld);
            }
            if (idPeriodoNew != null && !idPeriodoNew.equals(idPeriodoOld)) {
                idPeriodoNew.getGeneracionList().add(generacion);
                idPeriodoNew = em.merge(idPeriodoNew);
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setIdGeneracion(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Generacion oldIdGeneracionOfGrupoListNewGrupo = grupoListNewGrupo.getIdGeneracion();
                    grupoListNewGrupo.setIdGeneracion(generacion);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdGeneracionOfGrupoListNewGrupo != null && !oldIdGeneracionOfGrupoListNewGrupo.equals(generacion)) {
                        oldIdGeneracionOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdGeneracionOfGrupoListNewGrupo = em.merge(oldIdGeneracionOfGrupoListNewGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = generacion.getIdGeneracion();
                if (findGeneracion(id) == null) {
                    throw new NonexistentEntityException("The generacion with id " + id + " no longer exists.");
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
            Generacion generacion;
            try {
                generacion = em.getReference(Generacion.class, id);
                generacion.getIdGeneracion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generacion with id " + id + " no longer exists.", enfe);
            }
            PlanEstudio idPlanestudio = generacion.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio.getGeneracionList().remove(generacion);
                idPlanestudio = em.merge(idPlanestudio);
            }
            Periodo idPeriodo = generacion.getIdPeriodo();
            if (idPeriodo != null) {
                idPeriodo.getGeneracionList().remove(generacion);
                idPeriodo = em.merge(idPeriodo);
            }
            List<Grupo> grupoList = generacion.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setIdGeneracion(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            em.remove(generacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Generacion> findGeneracionEntities() {
        return findGeneracionEntities(true, -1, -1);
    }

    public List<Generacion> findGeneracionEntities(int maxResults, int firstResult) {
        return findGeneracionEntities(false, maxResults, firstResult);
    }

    private List<Generacion> findGeneracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Generacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Generacion findGeneracion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Generacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneracionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Generacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
