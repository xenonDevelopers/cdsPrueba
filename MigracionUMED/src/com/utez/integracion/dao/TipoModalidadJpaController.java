/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FormacionAcademica;
import com.utez.integracion.entity.TipoModalidad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoModalidadJpaController implements Serializable {

    public TipoModalidadJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoModalidad tipoModalidad) {
        if (tipoModalidad.getFormacionAcademicaList() == null) {
            tipoModalidad.setFormacionAcademicaList(new ArrayList<FormacionAcademica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<FormacionAcademica> attachedFormacionAcademicaList = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListFormacionAcademicaToAttach : tipoModalidad.getFormacionAcademicaList()) {
                formacionAcademicaListFormacionAcademicaToAttach = em.getReference(formacionAcademicaListFormacionAcademicaToAttach.getClass(), formacionAcademicaListFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaList.add(formacionAcademicaListFormacionAcademicaToAttach);
            }
            tipoModalidad.setFormacionAcademicaList(attachedFormacionAcademicaList);
            em.persist(tipoModalidad);
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : tipoModalidad.getFormacionAcademicaList()) {
                TipoModalidad oldIdTipomodalidadOfFormacionAcademicaListFormacionAcademica = formacionAcademicaListFormacionAcademica.getIdTipomodalidad();
                formacionAcademicaListFormacionAcademica.setIdTipomodalidad(tipoModalidad);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
                if (oldIdTipomodalidadOfFormacionAcademicaListFormacionAcademica != null) {
                    oldIdTipomodalidadOfFormacionAcademicaListFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListFormacionAcademica);
                    oldIdTipomodalidadOfFormacionAcademicaListFormacionAcademica = em.merge(oldIdTipomodalidadOfFormacionAcademicaListFormacionAcademica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoModalidad tipoModalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoModalidad persistentTipoModalidad = em.find(TipoModalidad.class, tipoModalidad.getIdTipomodalidad());
            List<FormacionAcademica> formacionAcademicaListOld = persistentTipoModalidad.getFormacionAcademicaList();
            List<FormacionAcademica> formacionAcademicaListNew = tipoModalidad.getFormacionAcademicaList();
            List<FormacionAcademica> attachedFormacionAcademicaListNew = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademicaToAttach : formacionAcademicaListNew) {
                formacionAcademicaListNewFormacionAcademicaToAttach = em.getReference(formacionAcademicaListNewFormacionAcademicaToAttach.getClass(), formacionAcademicaListNewFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaListNew.add(formacionAcademicaListNewFormacionAcademicaToAttach);
            }
            formacionAcademicaListNew = attachedFormacionAcademicaListNew;
            tipoModalidad.setFormacionAcademicaList(formacionAcademicaListNew);
            tipoModalidad = em.merge(tipoModalidad);
            for (FormacionAcademica formacionAcademicaListOldFormacionAcademica : formacionAcademicaListOld) {
                if (!formacionAcademicaListNew.contains(formacionAcademicaListOldFormacionAcademica)) {
                    formacionAcademicaListOldFormacionAcademica.setIdTipomodalidad(null);
                    formacionAcademicaListOldFormacionAcademica = em.merge(formacionAcademicaListOldFormacionAcademica);
                }
            }
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademica : formacionAcademicaListNew) {
                if (!formacionAcademicaListOld.contains(formacionAcademicaListNewFormacionAcademica)) {
                    TipoModalidad oldIdTipomodalidadOfFormacionAcademicaListNewFormacionAcademica = formacionAcademicaListNewFormacionAcademica.getIdTipomodalidad();
                    formacionAcademicaListNewFormacionAcademica.setIdTipomodalidad(tipoModalidad);
                    formacionAcademicaListNewFormacionAcademica = em.merge(formacionAcademicaListNewFormacionAcademica);
                    if (oldIdTipomodalidadOfFormacionAcademicaListNewFormacionAcademica != null && !oldIdTipomodalidadOfFormacionAcademicaListNewFormacionAcademica.equals(tipoModalidad)) {
                        oldIdTipomodalidadOfFormacionAcademicaListNewFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListNewFormacionAcademica);
                        oldIdTipomodalidadOfFormacionAcademicaListNewFormacionAcademica = em.merge(oldIdTipomodalidadOfFormacionAcademicaListNewFormacionAcademica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoModalidad.getIdTipomodalidad();
                if (findTipoModalidad(id) == null) {
                    throw new NonexistentEntityException("The tipoModalidad with id " + id + " no longer exists.");
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
            TipoModalidad tipoModalidad;
            try {
                tipoModalidad = em.getReference(TipoModalidad.class, id);
                tipoModalidad.getIdTipomodalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoModalidad with id " + id + " no longer exists.", enfe);
            }
            List<FormacionAcademica> formacionAcademicaList = tipoModalidad.getFormacionAcademicaList();
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : formacionAcademicaList) {
                formacionAcademicaListFormacionAcademica.setIdTipomodalidad(null);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
            }
            em.remove(tipoModalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoModalidad> findTipoModalidadEntities() {
        return findTipoModalidadEntities(true, -1, -1);
    }

    public List<TipoModalidad> findTipoModalidadEntities(int maxResults, int firstResult) {
        return findTipoModalidadEntities(false, maxResults, firstResult);
    }

    private List<TipoModalidad> findTipoModalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoModalidad as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoModalidad findTipoModalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoModalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoModalidadCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoModalidad as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
