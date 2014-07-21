/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.TipoSexo;
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
public class TipoSexoJpaController implements Serializable {

    public TipoSexoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoSexo tipoSexo) {
        if (tipoSexo.getPersonaList() == null) {
            tipoSexo.setPersonaList(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : tipoSexo.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getCurp());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            tipoSexo.setPersonaList(attachedPersonaList);
            em.persist(tipoSexo);
            for (Persona personaListPersona : tipoSexo.getPersonaList()) {
                TipoSexo oldIdTiposexoOfPersonaListPersona = personaListPersona.getIdTiposexo();
                personaListPersona.setIdTiposexo(tipoSexo);
                personaListPersona = em.merge(personaListPersona);
                if (oldIdTiposexoOfPersonaListPersona != null) {
                    oldIdTiposexoOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldIdTiposexoOfPersonaListPersona = em.merge(oldIdTiposexoOfPersonaListPersona);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoSexo tipoSexo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoSexo persistentTipoSexo = em.find(TipoSexo.class, tipoSexo.getIdTiposexo());
            List<Persona> personaListOld = persistentTipoSexo.getPersonaList();
            List<Persona> personaListNew = tipoSexo.getPersonaList();
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getCurp());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            tipoSexo.setPersonaList(personaListNew);
            tipoSexo = em.merge(tipoSexo);
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    personaListOldPersona.setIdTiposexo(null);
                    personaListOldPersona = em.merge(personaListOldPersona);
                }
            }
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    TipoSexo oldIdTiposexoOfPersonaListNewPersona = personaListNewPersona.getIdTiposexo();
                    personaListNewPersona.setIdTiposexo(tipoSexo);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldIdTiposexoOfPersonaListNewPersona != null && !oldIdTiposexoOfPersonaListNewPersona.equals(tipoSexo)) {
                        oldIdTiposexoOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldIdTiposexoOfPersonaListNewPersona = em.merge(oldIdTiposexoOfPersonaListNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoSexo.getIdTiposexo();
                if (findTipoSexo(id) == null) {
                    throw new NonexistentEntityException("The tipoSexo with id " + id + " no longer exists.");
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
            TipoSexo tipoSexo;
            try {
                tipoSexo = em.getReference(TipoSexo.class, id);
                tipoSexo.getIdTiposexo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoSexo with id " + id + " no longer exists.", enfe);
            }
            List<Persona> personaList = tipoSexo.getPersonaList();
            for (Persona personaListPersona : personaList) {
                personaListPersona.setIdTiposexo(null);
                personaListPersona = em.merge(personaListPersona);
            }
            em.remove(tipoSexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoSexo> findTipoSexoEntities() {
        return findTipoSexoEntities(true, -1, -1);
    }

    public List<TipoSexo> findTipoSexoEntities(int maxResults, int firstResult) {
        return findTipoSexoEntities(false, maxResults, firstResult);
    }

    private List<TipoSexo> findTipoSexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoSexo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoSexo findTipoSexo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoSexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoSexoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoSexo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
