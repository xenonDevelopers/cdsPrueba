/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import com.utez.integracion.entity.TipoAsignatura;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
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
public class TipoAsignaturaJpaController implements Serializable {

    public TipoAsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAsignatura tipoAsignatura) {
        if (tipoAsignatura.getAsignaturaList() == null) {
            tipoAsignatura.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : tipoAsignatura.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            tipoAsignatura.setAsignaturaList(attachedAsignaturaList);
            em.persist(tipoAsignatura);
            for (Asignatura asignaturaListAsignatura : tipoAsignatura.getAsignaturaList()) {
                TipoAsignatura oldIdTipoasignaturaOfAsignaturaListAsignatura = asignaturaListAsignatura.getIdTipoasignatura();
                asignaturaListAsignatura.setIdTipoasignatura(tipoAsignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldIdTipoasignaturaOfAsignaturaListAsignatura != null) {
                    oldIdTipoasignaturaOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldIdTipoasignaturaOfAsignaturaListAsignatura = em.merge(oldIdTipoasignaturaOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAsignatura tipoAsignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAsignatura persistentTipoAsignatura = em.find(TipoAsignatura.class, tipoAsignatura.getIdTipoasignatura());
            List<Asignatura> asignaturaListOld = persistentTipoAsignatura.getAsignaturaList();
            List<Asignatura> asignaturaListNew = tipoAsignatura.getAsignaturaList();
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            tipoAsignatura.setAsignaturaList(asignaturaListNew);
            tipoAsignatura = em.merge(tipoAsignatura);
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.setIdTipoasignatura(null);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    TipoAsignatura oldIdTipoasignaturaOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getIdTipoasignatura();
                    asignaturaListNewAsignatura.setIdTipoasignatura(tipoAsignatura);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldIdTipoasignaturaOfAsignaturaListNewAsignatura != null && !oldIdTipoasignaturaOfAsignaturaListNewAsignatura.equals(tipoAsignatura)) {
                        oldIdTipoasignaturaOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldIdTipoasignaturaOfAsignaturaListNewAsignatura = em.merge(oldIdTipoasignaturaOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAsignatura.getIdTipoasignatura();
                if (findTipoAsignatura(id) == null) {
                    throw new NonexistentEntityException("The tipoAsignatura with id " + id + " no longer exists.");
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
            TipoAsignatura tipoAsignatura;
            try {
                tipoAsignatura = em.getReference(TipoAsignatura.class, id);
                tipoAsignatura.getIdTipoasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAsignatura with id " + id + " no longer exists.", enfe);
            }
            List<Asignatura> asignaturaList = tipoAsignatura.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.setIdTipoasignatura(null);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            em.remove(tipoAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAsignatura> findTipoAsignaturaEntities() {
        return findTipoAsignaturaEntities(true, -1, -1);
    }

    public List<TipoAsignatura> findTipoAsignaturaEntities(int maxResults, int firstResult) {
        return findTipoAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<TipoAsignatura> findTipoAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAsignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAsignatura findTipoAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAsignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
