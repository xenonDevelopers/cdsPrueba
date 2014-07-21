/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.TipoNivelasignatura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secretariaAcademica.entity.Asignatura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoNivelasignaturaJpaController implements Serializable {

    public TipoNivelasignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoNivelasignatura tipoNivelasignatura) {
        if (tipoNivelasignatura.getAsignaturaList() == null) {
            tipoNivelasignatura.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : tipoNivelasignatura.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            tipoNivelasignatura.setAsignaturaList(attachedAsignaturaList);
            em.persist(tipoNivelasignatura);
            for (Asignatura asignaturaListAsignatura : tipoNivelasignatura.getAsignaturaList()) {
                TipoNivelasignatura oldIdTiponivelasignaturaOfAsignaturaListAsignatura = asignaturaListAsignatura.getIdTiponivelasignatura();
                asignaturaListAsignatura.setIdTiponivelasignatura(tipoNivelasignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldIdTiponivelasignaturaOfAsignaturaListAsignatura != null) {
                    oldIdTiponivelasignaturaOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldIdTiponivelasignaturaOfAsignaturaListAsignatura = em.merge(oldIdTiponivelasignaturaOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoNivelasignatura tipoNivelasignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelasignatura persistentTipoNivelasignatura = em.find(TipoNivelasignatura.class, tipoNivelasignatura.getIdTiponivelasignatura());
            List<Asignatura> asignaturaListOld = persistentTipoNivelasignatura.getAsignaturaList();
            List<Asignatura> asignaturaListNew = tipoNivelasignatura.getAsignaturaList();
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            tipoNivelasignatura.setAsignaturaList(asignaturaListNew);
            tipoNivelasignatura = em.merge(tipoNivelasignatura);
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.setIdTiponivelasignatura(null);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    TipoNivelasignatura oldIdTiponivelasignaturaOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getIdTiponivelasignatura();
                    asignaturaListNewAsignatura.setIdTiponivelasignatura(tipoNivelasignatura);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldIdTiponivelasignaturaOfAsignaturaListNewAsignatura != null && !oldIdTiponivelasignaturaOfAsignaturaListNewAsignatura.equals(tipoNivelasignatura)) {
                        oldIdTiponivelasignaturaOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldIdTiponivelasignaturaOfAsignaturaListNewAsignatura = em.merge(oldIdTiponivelasignaturaOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoNivelasignatura.getIdTiponivelasignatura();
                if (findTipoNivelasignatura(id) == null) {
                    throw new NonexistentEntityException("The tipoNivelasignatura with id " + id + " no longer exists.");
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
            TipoNivelasignatura tipoNivelasignatura;
            try {
                tipoNivelasignatura = em.getReference(TipoNivelasignatura.class, id);
                tipoNivelasignatura.getIdTiponivelasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoNivelasignatura with id " + id + " no longer exists.", enfe);
            }
            List<Asignatura> asignaturaList = tipoNivelasignatura.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.setIdTiponivelasignatura(null);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            em.remove(tipoNivelasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoNivelasignatura> findTipoNivelasignaturaEntities() {
        return findTipoNivelasignaturaEntities(true, -1, -1);
    }

    public List<TipoNivelasignatura> findTipoNivelasignaturaEntities(int maxResults, int firstResult) {
        return findTipoNivelasignaturaEntities(false, maxResults, firstResult);
    }

    private List<TipoNivelasignatura> findTipoNivelasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoNivelasignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoNivelasignatura findTipoNivelasignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoNivelasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoNivelasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoNivelasignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
