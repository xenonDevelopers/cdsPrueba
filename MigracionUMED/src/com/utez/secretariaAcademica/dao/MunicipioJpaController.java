/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.EntidadFederativa;
import com.utez.integracion.entity.Persona;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Asentamiento;
import com.utez.integracion.entity.Municipio;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MunicipioJpaController implements Serializable {

    public MunicipioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipio municipio) {
        if (municipio.getPersonaList() == null) {
            municipio.setPersonaList(new ArrayList<Persona>());
        }
        if (municipio.getAsentamientoList() == null) {
            municipio.setAsentamientoList(new ArrayList<Asentamiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntidadFederativa idEntidadfederativa = municipio.getIdEntidadfederativa();
            if (idEntidadfederativa != null) {
                idEntidadfederativa = em.getReference(idEntidadfederativa.getClass(), idEntidadfederativa.getIdEntidadfederativa());
                municipio.setIdEntidadfederativa(idEntidadfederativa);
            }
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : municipio.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getCurp());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            municipio.setPersonaList(attachedPersonaList);
            List<Asentamiento> attachedAsentamientoList = new ArrayList<Asentamiento>();
            for (Asentamiento asentamientoListAsentamientoToAttach : municipio.getAsentamientoList()) {
                asentamientoListAsentamientoToAttach = em.getReference(asentamientoListAsentamientoToAttach.getClass(), asentamientoListAsentamientoToAttach.getIdAsentamiento());
                attachedAsentamientoList.add(asentamientoListAsentamientoToAttach);
            }
            municipio.setAsentamientoList(attachedAsentamientoList);
            em.persist(municipio);
            if (idEntidadfederativa != null) {
                idEntidadfederativa.getMunicipioList().add(municipio);
                idEntidadfederativa = em.merge(idEntidadfederativa);
            }
            for (Persona personaListPersona : municipio.getPersonaList()) {
                Municipio oldIdMunicipionacimientoOfPersonaListPersona = personaListPersona.getIdMunicipionacimiento();
                personaListPersona.setIdMunicipionacimiento(municipio);
                personaListPersona = em.merge(personaListPersona);
                if (oldIdMunicipionacimientoOfPersonaListPersona != null) {
                    oldIdMunicipionacimientoOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldIdMunicipionacimientoOfPersonaListPersona = em.merge(oldIdMunicipionacimientoOfPersonaListPersona);
                }
            }
            for (Asentamiento asentamientoListAsentamiento : municipio.getAsentamientoList()) {
                Municipio oldIdMunicipioOfAsentamientoListAsentamiento = asentamientoListAsentamiento.getIdMunicipio();
                asentamientoListAsentamiento.setIdMunicipio(municipio);
                asentamientoListAsentamiento = em.merge(asentamientoListAsentamiento);
                if (oldIdMunicipioOfAsentamientoListAsentamiento != null) {
                    oldIdMunicipioOfAsentamientoListAsentamiento.getAsentamientoList().remove(asentamientoListAsentamiento);
                    oldIdMunicipioOfAsentamientoListAsentamiento = em.merge(oldIdMunicipioOfAsentamientoListAsentamiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipio municipio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio persistentMunicipio = em.find(Municipio.class, municipio.getIdMunicipio());
            EntidadFederativa idEntidadfederativaOld = persistentMunicipio.getIdEntidadfederativa();
            EntidadFederativa idEntidadfederativaNew = municipio.getIdEntidadfederativa();
            List<Persona> personaListOld = persistentMunicipio.getPersonaList();
            List<Persona> personaListNew = municipio.getPersonaList();
            List<Asentamiento> asentamientoListOld = persistentMunicipio.getAsentamientoList();
            List<Asentamiento> asentamientoListNew = municipio.getAsentamientoList();
            if (idEntidadfederativaNew != null) {
                idEntidadfederativaNew = em.getReference(idEntidadfederativaNew.getClass(), idEntidadfederativaNew.getIdEntidadfederativa());
                municipio.setIdEntidadfederativa(idEntidadfederativaNew);
            }
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getCurp());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            municipio.setPersonaList(personaListNew);
            List<Asentamiento> attachedAsentamientoListNew = new ArrayList<Asentamiento>();
            for (Asentamiento asentamientoListNewAsentamientoToAttach : asentamientoListNew) {
                asentamientoListNewAsentamientoToAttach = em.getReference(asentamientoListNewAsentamientoToAttach.getClass(), asentamientoListNewAsentamientoToAttach.getIdAsentamiento());
                attachedAsentamientoListNew.add(asentamientoListNewAsentamientoToAttach);
            }
            asentamientoListNew = attachedAsentamientoListNew;
            municipio.setAsentamientoList(asentamientoListNew);
            municipio = em.merge(municipio);
            if (idEntidadfederativaOld != null && !idEntidadfederativaOld.equals(idEntidadfederativaNew)) {
                idEntidadfederativaOld.getMunicipioList().remove(municipio);
                idEntidadfederativaOld = em.merge(idEntidadfederativaOld);
            }
            if (idEntidadfederativaNew != null && !idEntidadfederativaNew.equals(idEntidadfederativaOld)) {
                idEntidadfederativaNew.getMunicipioList().add(municipio);
                idEntidadfederativaNew = em.merge(idEntidadfederativaNew);
            }
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    personaListOldPersona.setIdMunicipionacimiento(null);
                    personaListOldPersona = em.merge(personaListOldPersona);
                }
            }
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    Municipio oldIdMunicipionacimientoOfPersonaListNewPersona = personaListNewPersona.getIdMunicipionacimiento();
                    personaListNewPersona.setIdMunicipionacimiento(municipio);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldIdMunicipionacimientoOfPersonaListNewPersona != null && !oldIdMunicipionacimientoOfPersonaListNewPersona.equals(municipio)) {
                        oldIdMunicipionacimientoOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldIdMunicipionacimientoOfPersonaListNewPersona = em.merge(oldIdMunicipionacimientoOfPersonaListNewPersona);
                    }
                }
            }
            for (Asentamiento asentamientoListOldAsentamiento : asentamientoListOld) {
                if (!asentamientoListNew.contains(asentamientoListOldAsentamiento)) {
                    asentamientoListOldAsentamiento.setIdMunicipio(null);
                    asentamientoListOldAsentamiento = em.merge(asentamientoListOldAsentamiento);
                }
            }
            for (Asentamiento asentamientoListNewAsentamiento : asentamientoListNew) {
                if (!asentamientoListOld.contains(asentamientoListNewAsentamiento)) {
                    Municipio oldIdMunicipioOfAsentamientoListNewAsentamiento = asentamientoListNewAsentamiento.getIdMunicipio();
                    asentamientoListNewAsentamiento.setIdMunicipio(municipio);
                    asentamientoListNewAsentamiento = em.merge(asentamientoListNewAsentamiento);
                    if (oldIdMunicipioOfAsentamientoListNewAsentamiento != null && !oldIdMunicipioOfAsentamientoListNewAsentamiento.equals(municipio)) {
                        oldIdMunicipioOfAsentamientoListNewAsentamiento.getAsentamientoList().remove(asentamientoListNewAsentamiento);
                        oldIdMunicipioOfAsentamientoListNewAsentamiento = em.merge(oldIdMunicipioOfAsentamientoListNewAsentamiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = municipio.getIdMunicipio();
                if (findMunicipio(id) == null) {
                    throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.");
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
            Municipio municipio;
            try {
                municipio = em.getReference(Municipio.class, id);
                municipio.getIdMunicipio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            EntidadFederativa idEntidadfederativa = municipio.getIdEntidadfederativa();
            if (idEntidadfederativa != null) {
                idEntidadfederativa.getMunicipioList().remove(municipio);
                idEntidadfederativa = em.merge(idEntidadfederativa);
            }
            List<Persona> personaList = municipio.getPersonaList();
            for (Persona personaListPersona : personaList) {
                personaListPersona.setIdMunicipionacimiento(null);
                personaListPersona = em.merge(personaListPersona);
            }
            List<Asentamiento> asentamientoList = municipio.getAsentamientoList();
            for (Asentamiento asentamientoListAsentamiento : asentamientoList) {
                asentamientoListAsentamiento.setIdMunicipio(null);
                asentamientoListAsentamiento = em.merge(asentamientoListAsentamiento);
            }
            em.remove(municipio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipio> findMunicipioEntities() {
        return findMunicipioEntities(true, -1, -1);
    }

    public List<Municipio> findMunicipioEntities(int maxResults, int firstResult) {
        return findMunicipioEntities(false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Municipio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Municipio findMunicipio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Municipio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
