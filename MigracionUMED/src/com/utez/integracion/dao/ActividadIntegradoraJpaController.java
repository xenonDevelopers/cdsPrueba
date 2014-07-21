/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.ActividadIntegradora;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionAsignaturaintegradora;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionIntegradoragrupo;
import com.utez.integracion.entity.AsignacionAutorintegradora;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ActividadIntegradoraJpaController implements Serializable {

    public ActividadIntegradoraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ActividadIntegradora actividadIntegradora) {
        if (actividadIntegradora.getAsignacionAsignaturaintegradoraList() == null) {
            actividadIntegradora.setAsignacionAsignaturaintegradoraList(new ArrayList<AsignacionAsignaturaintegradora>());
        }
        if (actividadIntegradora.getAsignacionIntegradoragrupoList() == null) {
            actividadIntegradora.setAsignacionIntegradoragrupoList(new ArrayList<AsignacionIntegradoragrupo>());
        }
        if (actividadIntegradora.getAsignacionAutorintegradoraList() == null) {
            actividadIntegradora.setAsignacionAutorintegradoraList(new ArrayList<AsignacionAutorintegradora>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionAsignaturaintegradora> attachedAsignacionAsignaturaintegradoraList = new ArrayList<AsignacionAsignaturaintegradora>();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach : actividadIntegradora.getAsignacionAsignaturaintegradoraList()) {
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach = em.getReference(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach.getClass(), asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach.getIdAsignacionasignaturaintegradora());
                attachedAsignacionAsignaturaintegradoraList.add(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach);
            }
            actividadIntegradora.setAsignacionAsignaturaintegradoraList(attachedAsignacionAsignaturaintegradoraList);
            List<AsignacionIntegradoragrupo> attachedAsignacionIntegradoragrupoList = new ArrayList<AsignacionIntegradoragrupo>();
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach : actividadIntegradora.getAsignacionIntegradoragrupoList()) {
                asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach = em.getReference(asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach.getClass(), asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach.getIdAsignacionintegradoragrupo());
                attachedAsignacionIntegradoragrupoList.add(asignacionIntegradoragrupoListAsignacionIntegradoragrupoToAttach);
            }
            actividadIntegradora.setAsignacionIntegradoragrupoList(attachedAsignacionIntegradoragrupoList);
            List<AsignacionAutorintegradora> attachedAsignacionAutorintegradoraList = new ArrayList<AsignacionAutorintegradora>();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach : actividadIntegradora.getAsignacionAutorintegradoraList()) {
                asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach = em.getReference(asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach.getClass(), asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach.getIdAsignacionautorintegradora());
                attachedAsignacionAutorintegradoraList.add(asignacionAutorintegradoraListAsignacionAutorintegradoraToAttach);
            }
            actividadIntegradora.setAsignacionAutorintegradoraList(attachedAsignacionAutorintegradoraList);
            em.persist(actividadIntegradora);
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora : actividadIntegradora.getAsignacionAsignaturaintegradoraList()) {
                ActividadIntegradora oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.getIdActividadintegradora();
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.setIdActividadintegradora(actividadIntegradora);
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                if (oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora != null) {
                    oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                    oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                }
            }
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListAsignacionIntegradoragrupo : actividadIntegradora.getAsignacionIntegradoragrupoList()) {
                ActividadIntegradora oldIdActividadintegradoraOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo = asignacionIntegradoragrupoListAsignacionIntegradoragrupo.getIdActividadintegradora();
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo.setIdActividadintegradora(actividadIntegradora);
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListAsignacionIntegradoragrupo);
                if (oldIdActividadintegradoraOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo != null) {
                    oldIdActividadintegradoraOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupoListAsignacionIntegradoragrupo);
                    oldIdActividadintegradoraOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo = em.merge(oldIdActividadintegradoraOfAsignacionIntegradoragrupoListAsignacionIntegradoragrupo);
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradora : actividadIntegradora.getAsignacionAutorintegradoraList()) {
                ActividadIntegradora oldIdActividadintegradoraOfAsignacionAutorintegradoraListAsignacionAutorintegradora = asignacionAutorintegradoraListAsignacionAutorintegradora.getIdActividadintegradora();
                asignacionAutorintegradoraListAsignacionAutorintegradora.setIdActividadintegradora(actividadIntegradora);
                asignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListAsignacionAutorintegradora);
                if (oldIdActividadintegradoraOfAsignacionAutorintegradoraListAsignacionAutorintegradora != null) {
                    oldIdActividadintegradoraOfAsignacionAutorintegradoraListAsignacionAutorintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradoraListAsignacionAutorintegradora);
                    oldIdActividadintegradoraOfAsignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(oldIdActividadintegradoraOfAsignacionAutorintegradoraListAsignacionAutorintegradora);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadIntegradora actividadIntegradora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadIntegradora persistentActividadIntegradora = em.find(ActividadIntegradora.class, actividadIntegradora.getIdActividadintegradora());
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraListOld = persistentActividadIntegradora.getAsignacionAsignaturaintegradoraList();
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraListNew = actividadIntegradora.getAsignacionAsignaturaintegradoraList();
            List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoListOld = persistentActividadIntegradora.getAsignacionIntegradoragrupoList();
            List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoListNew = actividadIntegradora.getAsignacionIntegradoragrupoList();
            List<AsignacionAutorintegradora> asignacionAutorintegradoraListOld = persistentActividadIntegradora.getAsignacionAutorintegradoraList();
            List<AsignacionAutorintegradora> asignacionAutorintegradoraListNew = actividadIntegradora.getAsignacionAutorintegradoraList();
            List<AsignacionAsignaturaintegradora> attachedAsignacionAsignaturaintegradoraListNew = new ArrayList<AsignacionAsignaturaintegradora>();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach : asignacionAsignaturaintegradoraListNew) {
                asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach = em.getReference(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach.getClass(), asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach.getIdAsignacionasignaturaintegradora());
                attachedAsignacionAsignaturaintegradoraListNew.add(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach);
            }
            asignacionAsignaturaintegradoraListNew = attachedAsignacionAsignaturaintegradoraListNew;
            actividadIntegradora.setAsignacionAsignaturaintegradoraList(asignacionAsignaturaintegradoraListNew);
            List<AsignacionIntegradoragrupo> attachedAsignacionIntegradoragrupoListNew = new ArrayList<AsignacionIntegradoragrupo>();
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach : asignacionIntegradoragrupoListNew) {
                asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach = em.getReference(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach.getClass(), asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach.getIdAsignacionintegradoragrupo());
                attachedAsignacionIntegradoragrupoListNew.add(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupoToAttach);
            }
            asignacionIntegradoragrupoListNew = attachedAsignacionIntegradoragrupoListNew;
            actividadIntegradora.setAsignacionIntegradoragrupoList(asignacionIntegradoragrupoListNew);
            List<AsignacionAutorintegradora> attachedAsignacionAutorintegradoraListNew = new ArrayList<AsignacionAutorintegradora>();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach : asignacionAutorintegradoraListNew) {
                asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach = em.getReference(asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach.getClass(), asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach.getIdAsignacionautorintegradora());
                attachedAsignacionAutorintegradoraListNew.add(asignacionAutorintegradoraListNewAsignacionAutorintegradoraToAttach);
            }
            asignacionAutorintegradoraListNew = attachedAsignacionAutorintegradoraListNew;
            actividadIntegradora.setAsignacionAutorintegradoraList(asignacionAutorintegradoraListNew);
            actividadIntegradora = em.merge(actividadIntegradora);
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraListOld) {
                if (!asignacionAsignaturaintegradoraListNew.contains(asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora)) {
                    asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora.setIdActividadintegradora(null);
                    asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora);
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraListNew) {
                if (!asignacionAsignaturaintegradoraListOld.contains(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora)) {
                    ActividadIntegradora oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.getIdActividadintegradora();
                    asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.setIdActividadintegradora(actividadIntegradora);
                    asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                    if (oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora != null && !oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.equals(actividadIntegradora)) {
                        oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                        oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = em.merge(oldIdActividadintegradoraOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                    }
                }
            }
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo : asignacionIntegradoragrupoListOld) {
                if (!asignacionIntegradoragrupoListNew.contains(asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo)) {
                    asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo.setIdActividadintegradora(null);
                    asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListOldAsignacionIntegradoragrupo);
                }
            }
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo : asignacionIntegradoragrupoListNew) {
                if (!asignacionIntegradoragrupoListOld.contains(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo)) {
                    ActividadIntegradora oldIdActividadintegradoraOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo = asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.getIdActividadintegradora();
                    asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.setIdActividadintegradora(actividadIntegradora);
                    asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo);
                    if (oldIdActividadintegradoraOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo != null && !oldIdActividadintegradoraOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.equals(actividadIntegradora)) {
                        oldIdActividadintegradoraOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo.getAsignacionIntegradoragrupoList().remove(asignacionIntegradoragrupoListNewAsignacionIntegradoragrupo);
                        oldIdActividadintegradoraOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo = em.merge(oldIdActividadintegradoraOfAsignacionIntegradoragrupoListNewAsignacionIntegradoragrupo);
                    }
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListOldAsignacionAutorintegradora : asignacionAutorintegradoraListOld) {
                if (!asignacionAutorintegradoraListNew.contains(asignacionAutorintegradoraListOldAsignacionAutorintegradora)) {
                    asignacionAutorintegradoraListOldAsignacionAutorintegradora.setIdActividadintegradora(null);
                    asignacionAutorintegradoraListOldAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListOldAsignacionAutorintegradora);
                }
            }
            for (AsignacionAutorintegradora asignacionAutorintegradoraListNewAsignacionAutorintegradora : asignacionAutorintegradoraListNew) {
                if (!asignacionAutorintegradoraListOld.contains(asignacionAutorintegradoraListNewAsignacionAutorintegradora)) {
                    ActividadIntegradora oldIdActividadintegradoraOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora = asignacionAutorintegradoraListNewAsignacionAutorintegradora.getIdActividadintegradora();
                    asignacionAutorintegradoraListNewAsignacionAutorintegradora.setIdActividadintegradora(actividadIntegradora);
                    asignacionAutorintegradoraListNewAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListNewAsignacionAutorintegradora);
                    if (oldIdActividadintegradoraOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora != null && !oldIdActividadintegradoraOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora.equals(actividadIntegradora)) {
                        oldIdActividadintegradoraOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora.getAsignacionAutorintegradoraList().remove(asignacionAutorintegradoraListNewAsignacionAutorintegradora);
                        oldIdActividadintegradoraOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora = em.merge(oldIdActividadintegradoraOfAsignacionAutorintegradoraListNewAsignacionAutorintegradora);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = actividadIntegradora.getIdActividadintegradora();
                if (findActividadIntegradora(id) == null) {
                    throw new NonexistentEntityException("The actividadIntegradora with id " + id + " no longer exists.");
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
            ActividadIntegradora actividadIntegradora;
            try {
                actividadIntegradora = em.getReference(ActividadIntegradora.class, id);
                actividadIntegradora.getIdActividadintegradora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadIntegradora with id " + id + " no longer exists.", enfe);
            }
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList = actividadIntegradora.getAsignacionAsignaturaintegradoraList();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraList) {
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.setIdActividadintegradora(null);
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
            }
            List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoList = actividadIntegradora.getAsignacionIntegradoragrupoList();
            for (AsignacionIntegradoragrupo asignacionIntegradoragrupoListAsignacionIntegradoragrupo : asignacionIntegradoragrupoList) {
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo.setIdActividadintegradora(null);
                asignacionIntegradoragrupoListAsignacionIntegradoragrupo = em.merge(asignacionIntegradoragrupoListAsignacionIntegradoragrupo);
            }
            List<AsignacionAutorintegradora> asignacionAutorintegradoraList = actividadIntegradora.getAsignacionAutorintegradoraList();
            for (AsignacionAutorintegradora asignacionAutorintegradoraListAsignacionAutorintegradora : asignacionAutorintegradoraList) {
                asignacionAutorintegradoraListAsignacionAutorintegradora.setIdActividadintegradora(null);
                asignacionAutorintegradoraListAsignacionAutorintegradora = em.merge(asignacionAutorintegradoraListAsignacionAutorintegradora);
            }
            em.remove(actividadIntegradora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ActividadIntegradora> findActividadIntegradoraEntities() {
        return findActividadIntegradoraEntities(true, -1, -1);
    }

    public List<ActividadIntegradora> findActividadIntegradoraEntities(int maxResults, int firstResult) {
        return findActividadIntegradoraEntities(false, maxResults, firstResult);
    }

    private List<ActividadIntegradora> findActividadIntegradoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ActividadIntegradora as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ActividadIntegradora findActividadIntegradora(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadIntegradora.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadIntegradoraCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ActividadIntegradora as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
