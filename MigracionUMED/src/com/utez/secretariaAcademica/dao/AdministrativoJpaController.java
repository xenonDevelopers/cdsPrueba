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
import com.utez.secretariaAcademica.entity.Plantel;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.HistorialCargo;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.entity.Administrativo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AdministrativoJpaController implements Serializable {

    public AdministrativoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrativo administrativo) {
        if (administrativo.getPlantelList() == null) {
            administrativo.setPlantelList(new ArrayList<Plantel>());
        }
        if (administrativo.getHistorialCargoList() == null) {
            administrativo.setHistorialCargoList(new ArrayList<HistorialCargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recursohumano idrecursohumano = administrativo.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano = em.getReference(idrecursohumano.getClass(), idrecursohumano.getIdrecursohumano());
                administrativo.setIdrecursohumano(idrecursohumano);
            }
            RecursoHumano idRecursohumano = administrativo.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                administrativo.setIdRecursohumano(idRecursohumano);
            }
            List<Plantel> attachedPlantelList = new ArrayList<Plantel>();
            for (Plantel plantelListPlantelToAttach : administrativo.getPlantelList()) {
                plantelListPlantelToAttach = em.getReference(plantelListPlantelToAttach.getClass(), plantelListPlantelToAttach.getIdplantel());
                attachedPlantelList.add(plantelListPlantelToAttach);
            }
            administrativo.setPlantelList(attachedPlantelList);
            List<HistorialCargo> attachedHistorialCargoList = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListHistorialCargoToAttach : administrativo.getHistorialCargoList()) {
                historialCargoListHistorialCargoToAttach = em.getReference(historialCargoListHistorialCargoToAttach.getClass(), historialCargoListHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoList.add(historialCargoListHistorialCargoToAttach);
            }
            administrativo.setHistorialCargoList(attachedHistorialCargoList);
            em.persist(administrativo);
            if (idrecursohumano != null) {
                idrecursohumano.getAdministrativoList().add(administrativo);
                idrecursohumano = em.merge(idrecursohumano);
            }
            if (idRecursohumano != null) {
                idRecursohumano.getAdministrativoList().add(administrativo);
                idRecursohumano = em.merge(idRecursohumano);
            }
            for (Plantel plantelListPlantel : administrativo.getPlantelList()) {
                plantelListPlantel.getAdministrativoList().add(administrativo);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            for (HistorialCargo historialCargoListHistorialCargo : administrativo.getHistorialCargoList()) {
                Administrativo oldIdAdministrativoOfHistorialCargoListHistorialCargo = historialCargoListHistorialCargo.getIdAdministrativo();
                historialCargoListHistorialCargo.setIdAdministrativo(administrativo);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
                if (oldIdAdministrativoOfHistorialCargoListHistorialCargo != null) {
                    oldIdAdministrativoOfHistorialCargoListHistorialCargo.getHistorialCargoList().remove(historialCargoListHistorialCargo);
                    oldIdAdministrativoOfHistorialCargoListHistorialCargo = em.merge(oldIdAdministrativoOfHistorialCargoListHistorialCargo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrativo administrativo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo persistentAdministrativo = em.find(Administrativo.class, administrativo.getIdadministrativo());
            Recursohumano idrecursohumanoOld = persistentAdministrativo.getIdrecursohumano();
            Recursohumano idrecursohumanoNew = administrativo.getIdrecursohumano();
            RecursoHumano idRecursohumanoOld = persistentAdministrativo.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = administrativo.getIdRecursohumano();
            List<Plantel> plantelListOld = persistentAdministrativo.getPlantelList();
            List<Plantel> plantelListNew = administrativo.getPlantelList();
            List<HistorialCargo> historialCargoListOld = persistentAdministrativo.getHistorialCargoList();
            List<HistorialCargo> historialCargoListNew = administrativo.getHistorialCargoList();
            if (idrecursohumanoNew != null) {
                idrecursohumanoNew = em.getReference(idrecursohumanoNew.getClass(), idrecursohumanoNew.getIdrecursohumano());
                administrativo.setIdrecursohumano(idrecursohumanoNew);
            }
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                administrativo.setIdRecursohumano(idRecursohumanoNew);
            }
            List<Plantel> attachedPlantelListNew = new ArrayList<Plantel>();
            for (Plantel plantelListNewPlantelToAttach : plantelListNew) {
                plantelListNewPlantelToAttach = em.getReference(plantelListNewPlantelToAttach.getClass(), plantelListNewPlantelToAttach.getIdplantel());
                attachedPlantelListNew.add(plantelListNewPlantelToAttach);
            }
            plantelListNew = attachedPlantelListNew;
            administrativo.setPlantelList(plantelListNew);
            List<HistorialCargo> attachedHistorialCargoListNew = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListNewHistorialCargoToAttach : historialCargoListNew) {
                historialCargoListNewHistorialCargoToAttach = em.getReference(historialCargoListNewHistorialCargoToAttach.getClass(), historialCargoListNewHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoListNew.add(historialCargoListNewHistorialCargoToAttach);
            }
            historialCargoListNew = attachedHistorialCargoListNew;
            administrativo.setHistorialCargoList(historialCargoListNew);
            administrativo = em.merge(administrativo);
            if (idrecursohumanoOld != null && !idrecursohumanoOld.equals(idrecursohumanoNew)) {
                idrecursohumanoOld.getAdministrativoList().remove(administrativo);
                idrecursohumanoOld = em.merge(idrecursohumanoOld);
            }
            if (idrecursohumanoNew != null && !idrecursohumanoNew.equals(idrecursohumanoOld)) {
                idrecursohumanoNew.getAdministrativoList().add(administrativo);
                idrecursohumanoNew = em.merge(idrecursohumanoNew);
            }
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getAdministrativoList().remove(administrativo);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getAdministrativoList().add(administrativo);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            for (Plantel plantelListOldPlantel : plantelListOld) {
                if (!plantelListNew.contains(plantelListOldPlantel)) {
                    plantelListOldPlantel.getAdministrativoList().remove(administrativo);
                    plantelListOldPlantel = em.merge(plantelListOldPlantel);
                }
            }
            for (Plantel plantelListNewPlantel : plantelListNew) {
                if (!plantelListOld.contains(plantelListNewPlantel)) {
                    plantelListNewPlantel.getAdministrativoList().add(administrativo);
                    plantelListNewPlantel = em.merge(plantelListNewPlantel);
                }
            }
            for (HistorialCargo historialCargoListOldHistorialCargo : historialCargoListOld) {
                if (!historialCargoListNew.contains(historialCargoListOldHistorialCargo)) {
                    historialCargoListOldHistorialCargo.setIdAdministrativo(null);
                    historialCargoListOldHistorialCargo = em.merge(historialCargoListOldHistorialCargo);
                }
            }
            for (HistorialCargo historialCargoListNewHistorialCargo : historialCargoListNew) {
                if (!historialCargoListOld.contains(historialCargoListNewHistorialCargo)) {
                    Administrativo oldIdAdministrativoOfHistorialCargoListNewHistorialCargo = historialCargoListNewHistorialCargo.getIdAdministrativo();
                    historialCargoListNewHistorialCargo.setIdAdministrativo(administrativo);
                    historialCargoListNewHistorialCargo = em.merge(historialCargoListNewHistorialCargo);
                    if (oldIdAdministrativoOfHistorialCargoListNewHistorialCargo != null && !oldIdAdministrativoOfHistorialCargoListNewHistorialCargo.equals(administrativo)) {
                        oldIdAdministrativoOfHistorialCargoListNewHistorialCargo.getHistorialCargoList().remove(historialCargoListNewHistorialCargo);
                        oldIdAdministrativoOfHistorialCargoListNewHistorialCargo = em.merge(oldIdAdministrativoOfHistorialCargoListNewHistorialCargo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrativo.getIdadministrativo();
                if (findAdministrativo(id) == null) {
                    throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.");
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
            Administrativo administrativo;
            try {
                administrativo = em.getReference(Administrativo.class, id);
                administrativo.getIdadministrativo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.", enfe);
            }
            Recursohumano idrecursohumano = administrativo.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano.getAdministrativoList().remove(administrativo);
                idrecursohumano = em.merge(idrecursohumano);
            }
            RecursoHumano idRecursohumano = administrativo.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getAdministrativoList().remove(administrativo);
                idRecursohumano = em.merge(idRecursohumano);
            }
            List<Plantel> plantelList = administrativo.getPlantelList();
            for (Plantel plantelListPlantel : plantelList) {
                plantelListPlantel.getAdministrativoList().remove(administrativo);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            List<HistorialCargo> historialCargoList = administrativo.getHistorialCargoList();
            for (HistorialCargo historialCargoListHistorialCargo : historialCargoList) {
                historialCargoListHistorialCargo.setIdAdministrativo(null);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
            }
            em.remove(administrativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrativo> findAdministrativoEntities() {
        return findAdministrativoEntities(true, -1, -1);
    }

    public List<Administrativo> findAdministrativoEntities(int maxResults, int firstResult) {
        return findAdministrativoEntities(false, maxResults, firstResult);
    }

    private List<Administrativo> findAdministrativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Administrativo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Administrativo findAdministrativo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministrativoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Administrativo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
