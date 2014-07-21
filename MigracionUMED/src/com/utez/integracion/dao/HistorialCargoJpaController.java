/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AreaCargo;
import com.utez.integracion.entity.HistorialCargo;
import com.utez.secretariaAcademica.entity.Administrativo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class HistorialCargoJpaController implements Serializable {

    public HistorialCargoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialCargo historialCargo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaCargo idCargoarea = historialCargo.getIdCargoarea();
            if (idCargoarea != null) {
                idCargoarea = em.getReference(idCargoarea.getClass(), idCargoarea.getIdCargoarea());
                historialCargo.setIdCargoarea(idCargoarea);
            }
            Administrativo idAdministrativo = historialCargo.getIdAdministrativo();
            if (idAdministrativo != null) {
                idAdministrativo = em.getReference(idAdministrativo.getClass(), idAdministrativo.getIdadministrativo());
                historialCargo.setIdAdministrativo(idAdministrativo);
            }
            em.persist(historialCargo);
            if (idCargoarea != null) {
                idCargoarea.getHistorialCargoList().add(historialCargo);
                idCargoarea = em.merge(idCargoarea);
            }
            if (idAdministrativo != null) {
                idAdministrativo.getHistorialCargoList().add(historialCargo);
                idAdministrativo = em.merge(idAdministrativo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialCargo historialCargo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistorialCargo persistentHistorialCargo = em.find(HistorialCargo.class, historialCargo.getIdHistorialcargo());
            AreaCargo idCargoareaOld = persistentHistorialCargo.getIdCargoarea();
            AreaCargo idCargoareaNew = historialCargo.getIdCargoarea();
            Administrativo idAdministrativoOld = persistentHistorialCargo.getIdAdministrativo();
            Administrativo idAdministrativoNew = historialCargo.getIdAdministrativo();
            if (idCargoareaNew != null) {
                idCargoareaNew = em.getReference(idCargoareaNew.getClass(), idCargoareaNew.getIdCargoarea());
                historialCargo.setIdCargoarea(idCargoareaNew);
            }
            if (idAdministrativoNew != null) {
                idAdministrativoNew = em.getReference(idAdministrativoNew.getClass(), idAdministrativoNew.getIdadministrativo());
                historialCargo.setIdAdministrativo(idAdministrativoNew);
            }
            historialCargo = em.merge(historialCargo);
            if (idCargoareaOld != null && !idCargoareaOld.equals(idCargoareaNew)) {
                idCargoareaOld.getHistorialCargoList().remove(historialCargo);
                idCargoareaOld = em.merge(idCargoareaOld);
            }
            if (idCargoareaNew != null && !idCargoareaNew.equals(idCargoareaOld)) {
                idCargoareaNew.getHistorialCargoList().add(historialCargo);
                idCargoareaNew = em.merge(idCargoareaNew);
            }
            if (idAdministrativoOld != null && !idAdministrativoOld.equals(idAdministrativoNew)) {
                idAdministrativoOld.getHistorialCargoList().remove(historialCargo);
                idAdministrativoOld = em.merge(idAdministrativoOld);
            }
            if (idAdministrativoNew != null && !idAdministrativoNew.equals(idAdministrativoOld)) {
                idAdministrativoNew.getHistorialCargoList().add(historialCargo);
                idAdministrativoNew = em.merge(idAdministrativoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = historialCargo.getIdHistorialcargo();
                if (findHistorialCargo(id) == null) {
                    throw new NonexistentEntityException("The historialCargo with id " + id + " no longer exists.");
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
            HistorialCargo historialCargo;
            try {
                historialCargo = em.getReference(HistorialCargo.class, id);
                historialCargo.getIdHistorialcargo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialCargo with id " + id + " no longer exists.", enfe);
            }
            AreaCargo idCargoarea = historialCargo.getIdCargoarea();
            if (idCargoarea != null) {
                idCargoarea.getHistorialCargoList().remove(historialCargo);
                idCargoarea = em.merge(idCargoarea);
            }
            Administrativo idAdministrativo = historialCargo.getIdAdministrativo();
            if (idAdministrativo != null) {
                idAdministrativo.getHistorialCargoList().remove(historialCargo);
                idAdministrativo = em.merge(idAdministrativo);
            }
            em.remove(historialCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialCargo> findHistorialCargoEntities() {
        return findHistorialCargoEntities(true, -1, -1);
    }

    public List<HistorialCargo> findHistorialCargoEntities(int maxResults, int firstResult) {
        return findHistorialCargoEntities(false, maxResults, firstResult);
    }

    private List<HistorialCargo> findHistorialCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HistorialCargo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistorialCargo findHistorialCargo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialCargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialCargoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HistorialCargo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
