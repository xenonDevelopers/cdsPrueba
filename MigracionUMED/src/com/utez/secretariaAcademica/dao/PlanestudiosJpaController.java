/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Plantel;
import com.utez.secretariaAcademica.entity.Asesor;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Asignatura;
import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Planestudios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PlanestudiosJpaController implements Serializable {

    public PlanestudiosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planestudios planestudios) throws PreexistingEntityException, Exception {
        if (planestudios.getAsesorList() == null) {
            planestudios.setAsesorList(new ArrayList<Asesor>());
        }
        if (planestudios.getAsignaturaList() == null) {
            planestudios.setAsignaturaList(new ArrayList<Asignatura>());
        }
        if (planestudios.getGrupoList() == null) {
            planestudios.setGrupoList(new ArrayList<Grupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idplantel = planestudios.getIdplantel();
            if (idplantel != null) {
                idplantel = em.getReference(idplantel.getClass(), idplantel.getIdplantel());
                planestudios.setIdplantel(idplantel);
            }
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : planestudios.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getIdasesor());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            planestudios.setAsesorList(attachedAsesorList);
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : planestudios.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            planestudios.setAsignaturaList(attachedAsignaturaList);
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : planestudios.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            planestudios.setGrupoList(attachedGrupoList);
            em.persist(planestudios);
            if (idplantel != null) {
                idplantel.getPlanestudiosList().add(planestudios);
                idplantel = em.merge(idplantel);
            }
            for (Asesor asesorListAsesor : planestudios.getAsesorList()) {
                asesorListAsesor.getPlanestudiosList().add(planestudios);
                asesorListAsesor = em.merge(asesorListAsesor);
            }
            for (Asignatura asignaturaListAsignatura : planestudios.getAsignaturaList()) {
                Planestudios oldRvoeOfAsignaturaListAsignatura = asignaturaListAsignatura.getRvoe();
                asignaturaListAsignatura.setRvoe(planestudios);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldRvoeOfAsignaturaListAsignatura != null) {
                    oldRvoeOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldRvoeOfAsignaturaListAsignatura = em.merge(oldRvoeOfAsignaturaListAsignatura);
                }
            }
            for (Grupo grupoListGrupo : planestudios.getGrupoList()) {
                Planestudios oldRvoeOfGrupoListGrupo = grupoListGrupo.getRvoe();
                grupoListGrupo.setRvoe(planestudios);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldRvoeOfGrupoListGrupo != null) {
                    oldRvoeOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldRvoeOfGrupoListGrupo = em.merge(oldRvoeOfGrupoListGrupo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanestudios(planestudios.getRvoe()) != null) {
                throw new PreexistingEntityException("Planestudios " + planestudios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planestudios planestudios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planestudios persistentPlanestudios = em.find(Planestudios.class, planestudios.getRvoe());
            Plantel idplantelOld = persistentPlanestudios.getIdplantel();
            Plantel idplantelNew = planestudios.getIdplantel();
            List<Asesor> asesorListOld = persistentPlanestudios.getAsesorList();
            List<Asesor> asesorListNew = planestudios.getAsesorList();
            List<Asignatura> asignaturaListOld = persistentPlanestudios.getAsignaturaList();
            List<Asignatura> asignaturaListNew = planestudios.getAsignaturaList();
            List<Grupo> grupoListOld = persistentPlanestudios.getGrupoList();
            List<Grupo> grupoListNew = planestudios.getGrupoList();
            if (idplantelNew != null) {
                idplantelNew = em.getReference(idplantelNew.getClass(), idplantelNew.getIdplantel());
                planestudios.setIdplantel(idplantelNew);
            }
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getIdasesor());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            planestudios.setAsesorList(asesorListNew);
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            planestudios.setAsignaturaList(asignaturaListNew);
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            planestudios.setGrupoList(grupoListNew);
            planestudios = em.merge(planestudios);
            if (idplantelOld != null && !idplantelOld.equals(idplantelNew)) {
                idplantelOld.getPlanestudiosList().remove(planestudios);
                idplantelOld = em.merge(idplantelOld);
            }
            if (idplantelNew != null && !idplantelNew.equals(idplantelOld)) {
                idplantelNew.getPlanestudiosList().add(planestudios);
                idplantelNew = em.merge(idplantelNew);
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    asesorListOldAsesor.getPlanestudiosList().remove(planestudios);
                    asesorListOldAsesor = em.merge(asesorListOldAsesor);
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    asesorListNewAsesor.getPlanestudiosList().add(planestudios);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                }
            }
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.setRvoe(null);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    Planestudios oldRvoeOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getRvoe();
                    asignaturaListNewAsignatura.setRvoe(planestudios);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldRvoeOfAsignaturaListNewAsignatura != null && !oldRvoeOfAsignaturaListNewAsignatura.equals(planestudios)) {
                        oldRvoeOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldRvoeOfAsignaturaListNewAsignatura = em.merge(oldRvoeOfAsignaturaListNewAsignatura);
                    }
                }
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setRvoe(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Planestudios oldRvoeOfGrupoListNewGrupo = grupoListNewGrupo.getRvoe();
                    grupoListNewGrupo.setRvoe(planestudios);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldRvoeOfGrupoListNewGrupo != null && !oldRvoeOfGrupoListNewGrupo.equals(planestudios)) {
                        oldRvoeOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldRvoeOfGrupoListNewGrupo = em.merge(oldRvoeOfGrupoListNewGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = planestudios.getRvoe();
                if (findPlanestudios(id) == null) {
                    throw new NonexistentEntityException("The planestudios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planestudios planestudios;
            try {
                planestudios = em.getReference(Planestudios.class, id);
                planestudios.getRvoe();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planestudios with id " + id + " no longer exists.", enfe);
            }
            Plantel idplantel = planestudios.getIdplantel();
            if (idplantel != null) {
                idplantel.getPlanestudiosList().remove(planestudios);
                idplantel = em.merge(idplantel);
            }
            List<Asesor> asesorList = planestudios.getAsesorList();
            for (Asesor asesorListAsesor : asesorList) {
                asesorListAsesor.getPlanestudiosList().remove(planestudios);
                asesorListAsesor = em.merge(asesorListAsesor);
            }
            List<Asignatura> asignaturaList = planestudios.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.setRvoe(null);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            List<Grupo> grupoList = planestudios.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setRvoe(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            em.remove(planestudios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planestudios> findPlanestudiosEntities() {
        return findPlanestudiosEntities(true, -1, -1);
    }

    public List<Planestudios> findPlanestudiosEntities(int maxResults, int firstResult) {
        return findPlanestudiosEntities(false, maxResults, firstResult);
    }

    private List<Planestudios> findPlanestudiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Planestudios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Planestudios findPlanestudios(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planestudios.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanestudiosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Planestudios as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
