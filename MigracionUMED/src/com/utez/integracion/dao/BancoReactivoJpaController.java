/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Reactivo;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import com.utez.integracion.entity.AsignacionAutorbanco;
import com.utez.integracion.entity.BancoReactivo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class BancoReactivoJpaController implements Serializable {

    public BancoReactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BancoReactivo bancoReactivo) {
        if (bancoReactivo.getReactivoList() == null) {
            bancoReactivo.setReactivoList(new ArrayList<Reactivo>());
        }
        if (bancoReactivo.getAsignacionAsignaturabancoList() == null) {
            bancoReactivo.setAsignacionAsignaturabancoList(new ArrayList<AsignacionAsignaturabanco>());
        }
        if (bancoReactivo.getAsignacionAutorbancoList() == null) {
            bancoReactivo.setAsignacionAutorbancoList(new ArrayList<AsignacionAutorbanco>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reactivo> attachedReactivoList = new ArrayList<Reactivo>();
            for (Reactivo reactivoListReactivoToAttach : bancoReactivo.getReactivoList()) {
                reactivoListReactivoToAttach = em.getReference(reactivoListReactivoToAttach.getClass(), reactivoListReactivoToAttach.getIdReactivo());
                attachedReactivoList.add(reactivoListReactivoToAttach);
            }
            bancoReactivo.setReactivoList(attachedReactivoList);
            List<AsignacionAsignaturabanco> attachedAsignacionAsignaturabancoList = new ArrayList<AsignacionAsignaturabanco>();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach : bancoReactivo.getAsignacionAsignaturabancoList()) {
                asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach = em.getReference(asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach.getClass(), asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach.getIdAsignacionasignaturabanco());
                attachedAsignacionAsignaturabancoList.add(asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach);
            }
            bancoReactivo.setAsignacionAsignaturabancoList(attachedAsignacionAsignaturabancoList);
            List<AsignacionAutorbanco> attachedAsignacionAutorbancoList = new ArrayList<AsignacionAutorbanco>();
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbancoToAttach : bancoReactivo.getAsignacionAutorbancoList()) {
                asignacionAutorbancoListAsignacionAutorbancoToAttach = em.getReference(asignacionAutorbancoListAsignacionAutorbancoToAttach.getClass(), asignacionAutorbancoListAsignacionAutorbancoToAttach.getIdAsignacionautorbanco());
                attachedAsignacionAutorbancoList.add(asignacionAutorbancoListAsignacionAutorbancoToAttach);
            }
            bancoReactivo.setAsignacionAutorbancoList(attachedAsignacionAutorbancoList);
            em.persist(bancoReactivo);
            for (Reactivo reactivoListReactivo : bancoReactivo.getReactivoList()) {
                BancoReactivo oldIdBancoreactivoOfReactivoListReactivo = reactivoListReactivo.getIdBancoreactivo();
                reactivoListReactivo.setIdBancoreactivo(bancoReactivo);
                reactivoListReactivo = em.merge(reactivoListReactivo);
                if (oldIdBancoreactivoOfReactivoListReactivo != null) {
                    oldIdBancoreactivoOfReactivoListReactivo.getReactivoList().remove(reactivoListReactivo);
                    oldIdBancoreactivoOfReactivoListReactivo = em.merge(oldIdBancoreactivoOfReactivoListReactivo);
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabanco : bancoReactivo.getAsignacionAsignaturabancoList()) {
                BancoReactivo oldIdBancoreactivoOfAsignacionAsignaturabancoListAsignacionAsignaturabanco = asignacionAsignaturabancoListAsignacionAsignaturabanco.getIdBancoreactivo();
                asignacionAsignaturabancoListAsignacionAsignaturabanco.setIdBancoreactivo(bancoReactivo);
                asignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListAsignacionAsignaturabanco);
                if (oldIdBancoreactivoOfAsignacionAsignaturabancoListAsignacionAsignaturabanco != null) {
                    oldIdBancoreactivoOfAsignacionAsignaturabancoListAsignacionAsignaturabanco.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabancoListAsignacionAsignaturabanco);
                    oldIdBancoreactivoOfAsignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(oldIdBancoreactivoOfAsignacionAsignaturabancoListAsignacionAsignaturabanco);
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbanco : bancoReactivo.getAsignacionAutorbancoList()) {
                BancoReactivo oldIdBancoreactivoOfAsignacionAutorbancoListAsignacionAutorbanco = asignacionAutorbancoListAsignacionAutorbanco.getIdBancoreactivo();
                asignacionAutorbancoListAsignacionAutorbanco.setIdBancoreactivo(bancoReactivo);
                asignacionAutorbancoListAsignacionAutorbanco = em.merge(asignacionAutorbancoListAsignacionAutorbanco);
                if (oldIdBancoreactivoOfAsignacionAutorbancoListAsignacionAutorbanco != null) {
                    oldIdBancoreactivoOfAsignacionAutorbancoListAsignacionAutorbanco.getAsignacionAutorbancoList().remove(asignacionAutorbancoListAsignacionAutorbanco);
                    oldIdBancoreactivoOfAsignacionAutorbancoListAsignacionAutorbanco = em.merge(oldIdBancoreactivoOfAsignacionAutorbancoListAsignacionAutorbanco);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BancoReactivo bancoReactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BancoReactivo persistentBancoReactivo = em.find(BancoReactivo.class, bancoReactivo.getIdBancoreactivo());
            List<Reactivo> reactivoListOld = persistentBancoReactivo.getReactivoList();
            List<Reactivo> reactivoListNew = bancoReactivo.getReactivoList();
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoListOld = persistentBancoReactivo.getAsignacionAsignaturabancoList();
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoListNew = bancoReactivo.getAsignacionAsignaturabancoList();
            List<AsignacionAutorbanco> asignacionAutorbancoListOld = persistentBancoReactivo.getAsignacionAutorbancoList();
            List<AsignacionAutorbanco> asignacionAutorbancoListNew = bancoReactivo.getAsignacionAutorbancoList();
            List<Reactivo> attachedReactivoListNew = new ArrayList<Reactivo>();
            for (Reactivo reactivoListNewReactivoToAttach : reactivoListNew) {
                reactivoListNewReactivoToAttach = em.getReference(reactivoListNewReactivoToAttach.getClass(), reactivoListNewReactivoToAttach.getIdReactivo());
                attachedReactivoListNew.add(reactivoListNewReactivoToAttach);
            }
            reactivoListNew = attachedReactivoListNew;
            bancoReactivo.setReactivoList(reactivoListNew);
            List<AsignacionAsignaturabanco> attachedAsignacionAsignaturabancoListNew = new ArrayList<AsignacionAsignaturabanco>();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach : asignacionAsignaturabancoListNew) {
                asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach = em.getReference(asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach.getClass(), asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach.getIdAsignacionasignaturabanco());
                attachedAsignacionAsignaturabancoListNew.add(asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach);
            }
            asignacionAsignaturabancoListNew = attachedAsignacionAsignaturabancoListNew;
            bancoReactivo.setAsignacionAsignaturabancoList(asignacionAsignaturabancoListNew);
            List<AsignacionAutorbanco> attachedAsignacionAutorbancoListNew = new ArrayList<AsignacionAutorbanco>();
            for (AsignacionAutorbanco asignacionAutorbancoListNewAsignacionAutorbancoToAttach : asignacionAutorbancoListNew) {
                asignacionAutorbancoListNewAsignacionAutorbancoToAttach = em.getReference(asignacionAutorbancoListNewAsignacionAutorbancoToAttach.getClass(), asignacionAutorbancoListNewAsignacionAutorbancoToAttach.getIdAsignacionautorbanco());
                attachedAsignacionAutorbancoListNew.add(asignacionAutorbancoListNewAsignacionAutorbancoToAttach);
            }
            asignacionAutorbancoListNew = attachedAsignacionAutorbancoListNew;
            bancoReactivo.setAsignacionAutorbancoList(asignacionAutorbancoListNew);
            bancoReactivo = em.merge(bancoReactivo);
            for (Reactivo reactivoListOldReactivo : reactivoListOld) {
                if (!reactivoListNew.contains(reactivoListOldReactivo)) {
                    reactivoListOldReactivo.setIdBancoreactivo(null);
                    reactivoListOldReactivo = em.merge(reactivoListOldReactivo);
                }
            }
            for (Reactivo reactivoListNewReactivo : reactivoListNew) {
                if (!reactivoListOld.contains(reactivoListNewReactivo)) {
                    BancoReactivo oldIdBancoreactivoOfReactivoListNewReactivo = reactivoListNewReactivo.getIdBancoreactivo();
                    reactivoListNewReactivo.setIdBancoreactivo(bancoReactivo);
                    reactivoListNewReactivo = em.merge(reactivoListNewReactivo);
                    if (oldIdBancoreactivoOfReactivoListNewReactivo != null && !oldIdBancoreactivoOfReactivoListNewReactivo.equals(bancoReactivo)) {
                        oldIdBancoreactivoOfReactivoListNewReactivo.getReactivoList().remove(reactivoListNewReactivo);
                        oldIdBancoreactivoOfReactivoListNewReactivo = em.merge(oldIdBancoreactivoOfReactivoListNewReactivo);
                    }
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListOldAsignacionAsignaturabanco : asignacionAsignaturabancoListOld) {
                if (!asignacionAsignaturabancoListNew.contains(asignacionAsignaturabancoListOldAsignacionAsignaturabanco)) {
                    asignacionAsignaturabancoListOldAsignacionAsignaturabanco.setIdBancoreactivo(null);
                    asignacionAsignaturabancoListOldAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListOldAsignacionAsignaturabanco);
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListNewAsignacionAsignaturabanco : asignacionAsignaturabancoListNew) {
                if (!asignacionAsignaturabancoListOld.contains(asignacionAsignaturabancoListNewAsignacionAsignaturabanco)) {
                    BancoReactivo oldIdBancoreactivoOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco = asignacionAsignaturabancoListNewAsignacionAsignaturabanco.getIdBancoreactivo();
                    asignacionAsignaturabancoListNewAsignacionAsignaturabanco.setIdBancoreactivo(bancoReactivo);
                    asignacionAsignaturabancoListNewAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                    if (oldIdBancoreactivoOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco != null && !oldIdBancoreactivoOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco.equals(bancoReactivo)) {
                        oldIdBancoreactivoOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                        oldIdBancoreactivoOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco = em.merge(oldIdBancoreactivoOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                    }
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListOldAsignacionAutorbanco : asignacionAutorbancoListOld) {
                if (!asignacionAutorbancoListNew.contains(asignacionAutorbancoListOldAsignacionAutorbanco)) {
                    asignacionAutorbancoListOldAsignacionAutorbanco.setIdBancoreactivo(null);
                    asignacionAutorbancoListOldAsignacionAutorbanco = em.merge(asignacionAutorbancoListOldAsignacionAutorbanco);
                }
            }
            for (AsignacionAutorbanco asignacionAutorbancoListNewAsignacionAutorbanco : asignacionAutorbancoListNew) {
                if (!asignacionAutorbancoListOld.contains(asignacionAutorbancoListNewAsignacionAutorbanco)) {
                    BancoReactivo oldIdBancoreactivoOfAsignacionAutorbancoListNewAsignacionAutorbanco = asignacionAutorbancoListNewAsignacionAutorbanco.getIdBancoreactivo();
                    asignacionAutorbancoListNewAsignacionAutorbanco.setIdBancoreactivo(bancoReactivo);
                    asignacionAutorbancoListNewAsignacionAutorbanco = em.merge(asignacionAutorbancoListNewAsignacionAutorbanco);
                    if (oldIdBancoreactivoOfAsignacionAutorbancoListNewAsignacionAutorbanco != null && !oldIdBancoreactivoOfAsignacionAutorbancoListNewAsignacionAutorbanco.equals(bancoReactivo)) {
                        oldIdBancoreactivoOfAsignacionAutorbancoListNewAsignacionAutorbanco.getAsignacionAutorbancoList().remove(asignacionAutorbancoListNewAsignacionAutorbanco);
                        oldIdBancoreactivoOfAsignacionAutorbancoListNewAsignacionAutorbanco = em.merge(oldIdBancoreactivoOfAsignacionAutorbancoListNewAsignacionAutorbanco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bancoReactivo.getIdBancoreactivo();
                if (findBancoReactivo(id) == null) {
                    throw new NonexistentEntityException("The bancoReactivo with id " + id + " no longer exists.");
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
            BancoReactivo bancoReactivo;
            try {
                bancoReactivo = em.getReference(BancoReactivo.class, id);
                bancoReactivo.getIdBancoreactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bancoReactivo with id " + id + " no longer exists.", enfe);
            }
            List<Reactivo> reactivoList = bancoReactivo.getReactivoList();
            for (Reactivo reactivoListReactivo : reactivoList) {
                reactivoListReactivo.setIdBancoreactivo(null);
                reactivoListReactivo = em.merge(reactivoListReactivo);
            }
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoList = bancoReactivo.getAsignacionAsignaturabancoList();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabanco : asignacionAsignaturabancoList) {
                asignacionAsignaturabancoListAsignacionAsignaturabanco.setIdBancoreactivo(null);
                asignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListAsignacionAsignaturabanco);
            }
            List<AsignacionAutorbanco> asignacionAutorbancoList = bancoReactivo.getAsignacionAutorbancoList();
            for (AsignacionAutorbanco asignacionAutorbancoListAsignacionAutorbanco : asignacionAutorbancoList) {
                asignacionAutorbancoListAsignacionAutorbanco.setIdBancoreactivo(null);
                asignacionAutorbancoListAsignacionAutorbanco = em.merge(asignacionAutorbancoListAsignacionAutorbanco);
            }
            em.remove(bancoReactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BancoReactivo> findBancoReactivoEntities() {
        return findBancoReactivoEntities(true, -1, -1);
    }

    public List<BancoReactivo> findBancoReactivoEntities(int maxResults, int firstResult) {
        return findBancoReactivoEntities(false, maxResults, firstResult);
    }

    private List<BancoReactivo> findBancoReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BancoReactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BancoReactivo findBancoReactivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BancoReactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancoReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BancoReactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
