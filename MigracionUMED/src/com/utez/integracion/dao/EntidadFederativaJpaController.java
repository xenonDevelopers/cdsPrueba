/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.EntidadFederativa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EntidadFederativaJpaController implements Serializable {

    public EntidadFederativaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntidadFederativa entidadFederativa) {
        if (entidadFederativa.getMunicipioList() == null) {
            entidadFederativa.setMunicipioList(new ArrayList<Municipio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Municipio> attachedMunicipioList = new ArrayList<Municipio>();
            for (Municipio municipioListMunicipioToAttach : entidadFederativa.getMunicipioList()) {
                municipioListMunicipioToAttach = em.getReference(municipioListMunicipioToAttach.getClass(), municipioListMunicipioToAttach.getIdMunicipio());
                attachedMunicipioList.add(municipioListMunicipioToAttach);
            }
            entidadFederativa.setMunicipioList(attachedMunicipioList);
            em.persist(entidadFederativa);
            for (Municipio municipioListMunicipio : entidadFederativa.getMunicipioList()) {
                EntidadFederativa oldIdEntidadfederativaOfMunicipioListMunicipio = municipioListMunicipio.getIdEntidadfederativa();
                municipioListMunicipio.setIdEntidadfederativa(entidadFederativa);
                municipioListMunicipio = em.merge(municipioListMunicipio);
                if (oldIdEntidadfederativaOfMunicipioListMunicipio != null) {
                    oldIdEntidadfederativaOfMunicipioListMunicipio.getMunicipioList().remove(municipioListMunicipio);
                    oldIdEntidadfederativaOfMunicipioListMunicipio = em.merge(oldIdEntidadfederativaOfMunicipioListMunicipio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntidadFederativa entidadFederativa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntidadFederativa persistentEntidadFederativa = em.find(EntidadFederativa.class, entidadFederativa.getIdEntidadfederativa());
            List<Municipio> municipioListOld = persistentEntidadFederativa.getMunicipioList();
            List<Municipio> municipioListNew = entidadFederativa.getMunicipioList();
            List<Municipio> attachedMunicipioListNew = new ArrayList<Municipio>();
            for (Municipio municipioListNewMunicipioToAttach : municipioListNew) {
                municipioListNewMunicipioToAttach = em.getReference(municipioListNewMunicipioToAttach.getClass(), municipioListNewMunicipioToAttach.getIdMunicipio());
                attachedMunicipioListNew.add(municipioListNewMunicipioToAttach);
            }
            municipioListNew = attachedMunicipioListNew;
            entidadFederativa.setMunicipioList(municipioListNew);
            entidadFederativa = em.merge(entidadFederativa);
            for (Municipio municipioListOldMunicipio : municipioListOld) {
                if (!municipioListNew.contains(municipioListOldMunicipio)) {
                    municipioListOldMunicipio.setIdEntidadfederativa(null);
                    municipioListOldMunicipio = em.merge(municipioListOldMunicipio);
                }
            }
            for (Municipio municipioListNewMunicipio : municipioListNew) {
                if (!municipioListOld.contains(municipioListNewMunicipio)) {
                    EntidadFederativa oldIdEntidadfederativaOfMunicipioListNewMunicipio = municipioListNewMunicipio.getIdEntidadfederativa();
                    municipioListNewMunicipio.setIdEntidadfederativa(entidadFederativa);
                    municipioListNewMunicipio = em.merge(municipioListNewMunicipio);
                    if (oldIdEntidadfederativaOfMunicipioListNewMunicipio != null && !oldIdEntidadfederativaOfMunicipioListNewMunicipio.equals(entidadFederativa)) {
                        oldIdEntidadfederativaOfMunicipioListNewMunicipio.getMunicipioList().remove(municipioListNewMunicipio);
                        oldIdEntidadfederativaOfMunicipioListNewMunicipio = em.merge(oldIdEntidadfederativaOfMunicipioListNewMunicipio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entidadFederativa.getIdEntidadfederativa();
                if (findEntidadFederativa(id) == null) {
                    throw new NonexistentEntityException("The entidadFederativa with id " + id + " no longer exists.");
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
            EntidadFederativa entidadFederativa;
            try {
                entidadFederativa = em.getReference(EntidadFederativa.class, id);
                entidadFederativa.getIdEntidadfederativa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidadFederativa with id " + id + " no longer exists.", enfe);
            }
            List<Municipio> municipioList = entidadFederativa.getMunicipioList();
            for (Municipio municipioListMunicipio : municipioList) {
                municipioListMunicipio.setIdEntidadfederativa(null);
                municipioListMunicipio = em.merge(municipioListMunicipio);
            }
            em.remove(entidadFederativa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntidadFederativa> findEntidadFederativaEntities() {
        return findEntidadFederativaEntities(true, -1, -1);
    }

    public List<EntidadFederativa> findEntidadFederativaEntities(int maxResults, int firstResult) {
        return findEntidadFederativaEntities(false, maxResults, firstResult);
    }

    private List<EntidadFederativa> findEntidadFederativaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EntidadFederativa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EntidadFederativa findEntidadFederativa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntidadFederativa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadFederativaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from EntidadFederativa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
