/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Asentamiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Municipio;
import com.utez.secretariaAcademica.entity.Plantel;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.FormacionAcademica;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsentamientoJpaController implements Serializable {

    public AsentamientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asentamiento asentamiento) {
        if (asentamiento.getPlantelList() == null) {
            asentamiento.setPlantelList(new ArrayList<Plantel>());
        }
        if (asentamiento.getPersonaList() == null) {
            asentamiento.setPersonaList(new ArrayList<Persona>());
        }
        if (asentamiento.getFormacionAcademicaList() == null) {
            asentamiento.setFormacionAcademicaList(new ArrayList<FormacionAcademica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio idMunicipio = asentamiento.getIdMunicipio();
            if (idMunicipio != null) {
                idMunicipio = em.getReference(idMunicipio.getClass(), idMunicipio.getIdMunicipio());
                asentamiento.setIdMunicipio(idMunicipio);
            }
            List<Plantel> attachedPlantelList = new ArrayList<Plantel>();
            for (Plantel plantelListPlantelToAttach : asentamiento.getPlantelList()) {
                plantelListPlantelToAttach = em.getReference(plantelListPlantelToAttach.getClass(), plantelListPlantelToAttach.getIdplantel());
                attachedPlantelList.add(plantelListPlantelToAttach);
            }
            asentamiento.setPlantelList(attachedPlantelList);
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : asentamiento.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getCurp());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            asentamiento.setPersonaList(attachedPersonaList);
            List<FormacionAcademica> attachedFormacionAcademicaList = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListFormacionAcademicaToAttach : asentamiento.getFormacionAcademicaList()) {
                formacionAcademicaListFormacionAcademicaToAttach = em.getReference(formacionAcademicaListFormacionAcademicaToAttach.getClass(), formacionAcademicaListFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaList.add(formacionAcademicaListFormacionAcademicaToAttach);
            }
            asentamiento.setFormacionAcademicaList(attachedFormacionAcademicaList);
            em.persist(asentamiento);
            if (idMunicipio != null) {
                idMunicipio.getAsentamientoList().add(asentamiento);
                idMunicipio = em.merge(idMunicipio);
            }
            for (Plantel plantelListPlantel : asentamiento.getPlantelList()) {
                Asentamiento oldIdAsentamientoOfPlantelListPlantel = plantelListPlantel.getIdAsentamiento();
                plantelListPlantel.setIdAsentamiento(asentamiento);
                plantelListPlantel = em.merge(plantelListPlantel);
                if (oldIdAsentamientoOfPlantelListPlantel != null) {
                    oldIdAsentamientoOfPlantelListPlantel.getPlantelList().remove(plantelListPlantel);
                    oldIdAsentamientoOfPlantelListPlantel = em.merge(oldIdAsentamientoOfPlantelListPlantel);
                }
            }
            for (Persona personaListPersona : asentamiento.getPersonaList()) {
                Asentamiento oldIdAsentamientoOfPersonaListPersona = personaListPersona.getIdAsentamiento();
                personaListPersona.setIdAsentamiento(asentamiento);
                personaListPersona = em.merge(personaListPersona);
                if (oldIdAsentamientoOfPersonaListPersona != null) {
                    oldIdAsentamientoOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldIdAsentamientoOfPersonaListPersona = em.merge(oldIdAsentamientoOfPersonaListPersona);
                }
            }
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : asentamiento.getFormacionAcademicaList()) {
                Asentamiento oldIdAsentamientoOfFormacionAcademicaListFormacionAcademica = formacionAcademicaListFormacionAcademica.getIdAsentamiento();
                formacionAcademicaListFormacionAcademica.setIdAsentamiento(asentamiento);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
                if (oldIdAsentamientoOfFormacionAcademicaListFormacionAcademica != null) {
                    oldIdAsentamientoOfFormacionAcademicaListFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListFormacionAcademica);
                    oldIdAsentamientoOfFormacionAcademicaListFormacionAcademica = em.merge(oldIdAsentamientoOfFormacionAcademicaListFormacionAcademica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asentamiento asentamiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asentamiento persistentAsentamiento = em.find(Asentamiento.class, asentamiento.getIdAsentamiento());
            Municipio idMunicipioOld = persistentAsentamiento.getIdMunicipio();
            Municipio idMunicipioNew = asentamiento.getIdMunicipio();
            List<Plantel> plantelListOld = persistentAsentamiento.getPlantelList();
            List<Plantel> plantelListNew = asentamiento.getPlantelList();
            List<Persona> personaListOld = persistentAsentamiento.getPersonaList();
            List<Persona> personaListNew = asentamiento.getPersonaList();
            List<FormacionAcademica> formacionAcademicaListOld = persistentAsentamiento.getFormacionAcademicaList();
            List<FormacionAcademica> formacionAcademicaListNew = asentamiento.getFormacionAcademicaList();
            if (idMunicipioNew != null) {
                idMunicipioNew = em.getReference(idMunicipioNew.getClass(), idMunicipioNew.getIdMunicipio());
                asentamiento.setIdMunicipio(idMunicipioNew);
            }
            List<Plantel> attachedPlantelListNew = new ArrayList<Plantel>();
            for (Plantel plantelListNewPlantelToAttach : plantelListNew) {
                plantelListNewPlantelToAttach = em.getReference(plantelListNewPlantelToAttach.getClass(), plantelListNewPlantelToAttach.getIdplantel());
                attachedPlantelListNew.add(plantelListNewPlantelToAttach);
            }
            plantelListNew = attachedPlantelListNew;
            asentamiento.setPlantelList(plantelListNew);
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getCurp());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            asentamiento.setPersonaList(personaListNew);
            List<FormacionAcademica> attachedFormacionAcademicaListNew = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademicaToAttach : formacionAcademicaListNew) {
                formacionAcademicaListNewFormacionAcademicaToAttach = em.getReference(formacionAcademicaListNewFormacionAcademicaToAttach.getClass(), formacionAcademicaListNewFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaListNew.add(formacionAcademicaListNewFormacionAcademicaToAttach);
            }
            formacionAcademicaListNew = attachedFormacionAcademicaListNew;
            asentamiento.setFormacionAcademicaList(formacionAcademicaListNew);
            asentamiento = em.merge(asentamiento);
            if (idMunicipioOld != null && !idMunicipioOld.equals(idMunicipioNew)) {
                idMunicipioOld.getAsentamientoList().remove(asentamiento);
                idMunicipioOld = em.merge(idMunicipioOld);
            }
            if (idMunicipioNew != null && !idMunicipioNew.equals(idMunicipioOld)) {
                idMunicipioNew.getAsentamientoList().add(asentamiento);
                idMunicipioNew = em.merge(idMunicipioNew);
            }
            for (Plantel plantelListOldPlantel : plantelListOld) {
                if (!plantelListNew.contains(plantelListOldPlantel)) {
                    plantelListOldPlantel.setIdAsentamiento(null);
                    plantelListOldPlantel = em.merge(plantelListOldPlantel);
                }
            }
            for (Plantel plantelListNewPlantel : plantelListNew) {
                if (!plantelListOld.contains(plantelListNewPlantel)) {
                    Asentamiento oldIdAsentamientoOfPlantelListNewPlantel = plantelListNewPlantel.getIdAsentamiento();
                    plantelListNewPlantel.setIdAsentamiento(asentamiento);
                    plantelListNewPlantel = em.merge(plantelListNewPlantel);
                    if (oldIdAsentamientoOfPlantelListNewPlantel != null && !oldIdAsentamientoOfPlantelListNewPlantel.equals(asentamiento)) {
                        oldIdAsentamientoOfPlantelListNewPlantel.getPlantelList().remove(plantelListNewPlantel);
                        oldIdAsentamientoOfPlantelListNewPlantel = em.merge(oldIdAsentamientoOfPlantelListNewPlantel);
                    }
                }
            }
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    personaListOldPersona.setIdAsentamiento(null);
                    personaListOldPersona = em.merge(personaListOldPersona);
                }
            }
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    Asentamiento oldIdAsentamientoOfPersonaListNewPersona = personaListNewPersona.getIdAsentamiento();
                    personaListNewPersona.setIdAsentamiento(asentamiento);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldIdAsentamientoOfPersonaListNewPersona != null && !oldIdAsentamientoOfPersonaListNewPersona.equals(asentamiento)) {
                        oldIdAsentamientoOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldIdAsentamientoOfPersonaListNewPersona = em.merge(oldIdAsentamientoOfPersonaListNewPersona);
                    }
                }
            }
            for (FormacionAcademica formacionAcademicaListOldFormacionAcademica : formacionAcademicaListOld) {
                if (!formacionAcademicaListNew.contains(formacionAcademicaListOldFormacionAcademica)) {
                    formacionAcademicaListOldFormacionAcademica.setIdAsentamiento(null);
                    formacionAcademicaListOldFormacionAcademica = em.merge(formacionAcademicaListOldFormacionAcademica);
                }
            }
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademica : formacionAcademicaListNew) {
                if (!formacionAcademicaListOld.contains(formacionAcademicaListNewFormacionAcademica)) {
                    Asentamiento oldIdAsentamientoOfFormacionAcademicaListNewFormacionAcademica = formacionAcademicaListNewFormacionAcademica.getIdAsentamiento();
                    formacionAcademicaListNewFormacionAcademica.setIdAsentamiento(asentamiento);
                    formacionAcademicaListNewFormacionAcademica = em.merge(formacionAcademicaListNewFormacionAcademica);
                    if (oldIdAsentamientoOfFormacionAcademicaListNewFormacionAcademica != null && !oldIdAsentamientoOfFormacionAcademicaListNewFormacionAcademica.equals(asentamiento)) {
                        oldIdAsentamientoOfFormacionAcademicaListNewFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListNewFormacionAcademica);
                        oldIdAsentamientoOfFormacionAcademicaListNewFormacionAcademica = em.merge(oldIdAsentamientoOfFormacionAcademicaListNewFormacionAcademica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asentamiento.getIdAsentamiento();
                if (findAsentamiento(id) == null) {
                    throw new NonexistentEntityException("The asentamiento with id " + id + " no longer exists.");
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
            Asentamiento asentamiento;
            try {
                asentamiento = em.getReference(Asentamiento.class, id);
                asentamiento.getIdAsentamiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asentamiento with id " + id + " no longer exists.", enfe);
            }
            Municipio idMunicipio = asentamiento.getIdMunicipio();
            if (idMunicipio != null) {
                idMunicipio.getAsentamientoList().remove(asentamiento);
                idMunicipio = em.merge(idMunicipio);
            }
            List<Plantel> plantelList = asentamiento.getPlantelList();
            for (Plantel plantelListPlantel : plantelList) {
                plantelListPlantel.setIdAsentamiento(null);
                plantelListPlantel = em.merge(plantelListPlantel);
            }
            List<Persona> personaList = asentamiento.getPersonaList();
            for (Persona personaListPersona : personaList) {
                personaListPersona.setIdAsentamiento(null);
                personaListPersona = em.merge(personaListPersona);
            }
            List<FormacionAcademica> formacionAcademicaList = asentamiento.getFormacionAcademicaList();
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : formacionAcademicaList) {
                formacionAcademicaListFormacionAcademica.setIdAsentamiento(null);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
            }
            em.remove(asentamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asentamiento> findAsentamientoEntities() {
        return findAsentamientoEntities(true, -1, -1);
    }

    public List<Asentamiento> findAsentamientoEntities(int maxResults, int firstResult) {
        return findAsentamientoEntities(false, maxResults, firstResult);
    }

    private List<Asentamiento> findAsentamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asentamiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asentamiento findAsentamiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asentamiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsentamientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asentamiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
