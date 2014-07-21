/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.TipoEstadocivil;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoEstadocivilJpaController implements Serializable {

    public TipoEstadocivilJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEstadocivil tipoEstadocivil) {
        if (tipoEstadocivil.getPersonaList() == null) {
            tipoEstadocivil.setPersonaList(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : tipoEstadocivil.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getCurp());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            tipoEstadocivil.setPersonaList(attachedPersonaList);
            em.persist(tipoEstadocivil);
            for (Persona personaListPersona : tipoEstadocivil.getPersonaList()) {
                TipoEstadocivil oldIdTipoestadocivilOfPersonaListPersona = personaListPersona.getIdTipoestadocivil();
                personaListPersona.setIdTipoestadocivil(tipoEstadocivil);
                personaListPersona = em.merge(personaListPersona);
                if (oldIdTipoestadocivilOfPersonaListPersona != null) {
                    oldIdTipoestadocivilOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldIdTipoestadocivilOfPersonaListPersona = em.merge(oldIdTipoestadocivilOfPersonaListPersona);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEstadocivil tipoEstadocivil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEstadocivil persistentTipoEstadocivil = em.find(TipoEstadocivil.class, tipoEstadocivil.getIdTipoestadocivil());
            List<Persona> personaListOld = persistentTipoEstadocivil.getPersonaList();
            List<Persona> personaListNew = tipoEstadocivil.getPersonaList();
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getCurp());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            tipoEstadocivil.setPersonaList(personaListNew);
            tipoEstadocivil = em.merge(tipoEstadocivil);
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    personaListOldPersona.setIdTipoestadocivil(null);
                    personaListOldPersona = em.merge(personaListOldPersona);
                }
            }
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    TipoEstadocivil oldIdTipoestadocivilOfPersonaListNewPersona = personaListNewPersona.getIdTipoestadocivil();
                    personaListNewPersona.setIdTipoestadocivil(tipoEstadocivil);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldIdTipoestadocivilOfPersonaListNewPersona != null && !oldIdTipoestadocivilOfPersonaListNewPersona.equals(tipoEstadocivil)) {
                        oldIdTipoestadocivilOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldIdTipoestadocivilOfPersonaListNewPersona = em.merge(oldIdTipoestadocivilOfPersonaListNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoEstadocivil.getIdTipoestadocivil();
                if (findTipoEstadocivil(id) == null) {
                    throw new NonexistentEntityException("The tipoEstadocivil with id " + id + " no longer exists.");
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
            TipoEstadocivil tipoEstadocivil;
            try {
                tipoEstadocivil = em.getReference(TipoEstadocivil.class, id);
                tipoEstadocivil.getIdTipoestadocivil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEstadocivil with id " + id + " no longer exists.", enfe);
            }
            List<Persona> personaList = tipoEstadocivil.getPersonaList();
            for (Persona personaListPersona : personaList) {
                personaListPersona.setIdTipoestadocivil(null);
                personaListPersona = em.merge(personaListPersona);
            }
            em.remove(tipoEstadocivil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEstadocivil> findTipoEstadocivilEntities() {
        return findTipoEstadocivilEntities(true, -1, -1);
    }

    public List<TipoEstadocivil> findTipoEstadocivilEntities(int maxResults, int firstResult) {
        return findTipoEstadocivilEntities(false, maxResults, firstResult);
    }

    private List<TipoEstadocivil> findTipoEstadocivilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoEstadocivil as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoEstadocivil findTipoEstadocivil(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEstadocivil.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEstadocivilCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoEstadocivil as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
