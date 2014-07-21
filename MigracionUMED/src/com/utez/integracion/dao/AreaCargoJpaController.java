/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Cargo;
import com.utez.integracion.entity.Area;
import com.utez.integracion.entity.AreaCargo;
import com.utez.integracion.entity.Tabulador;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.HistorialCargo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AreaCargoJpaController implements Serializable {

    public AreaCargoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaCargo areaCargo) {
        if (areaCargo.getTabuladorList() == null) {
            areaCargo.setTabuladorList(new ArrayList<Tabulador>());
        }
        if (areaCargo.getHistorialCargoList() == null) {
            areaCargo.setHistorialCargoList(new ArrayList<HistorialCargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo idCargo = areaCargo.getIdCargo();
            if (idCargo != null) {
                idCargo = em.getReference(idCargo.getClass(), idCargo.getIdCargo());
                areaCargo.setIdCargo(idCargo);
            }
            Area idArea = areaCargo.getIdArea();
            if (idArea != null) {
                idArea = em.getReference(idArea.getClass(), idArea.getIdArea());
                areaCargo.setIdArea(idArea);
            }
            List<Tabulador> attachedTabuladorList = new ArrayList<Tabulador>();
            for (Tabulador tabuladorListTabuladorToAttach : areaCargo.getTabuladorList()) {
                tabuladorListTabuladorToAttach = em.getReference(tabuladorListTabuladorToAttach.getClass(), tabuladorListTabuladorToAttach.getIdTabulador());
                attachedTabuladorList.add(tabuladorListTabuladorToAttach);
            }
            areaCargo.setTabuladorList(attachedTabuladorList);
            List<HistorialCargo> attachedHistorialCargoList = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListHistorialCargoToAttach : areaCargo.getHistorialCargoList()) {
                historialCargoListHistorialCargoToAttach = em.getReference(historialCargoListHistorialCargoToAttach.getClass(), historialCargoListHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoList.add(historialCargoListHistorialCargoToAttach);
            }
            areaCargo.setHistorialCargoList(attachedHistorialCargoList);
            em.persist(areaCargo);
            if (idCargo != null) {
                idCargo.getAreaCargoList().add(areaCargo);
                idCargo = em.merge(idCargo);
            }
            if (idArea != null) {
                idArea.getAreaCargoList().add(areaCargo);
                idArea = em.merge(idArea);
            }
            for (Tabulador tabuladorListTabulador : areaCargo.getTabuladorList()) {
                tabuladorListTabulador.getAreaCargoList().add(areaCargo);
                tabuladorListTabulador = em.merge(tabuladorListTabulador);
            }
            for (HistorialCargo historialCargoListHistorialCargo : areaCargo.getHistorialCargoList()) {
                AreaCargo oldIdCargoareaOfHistorialCargoListHistorialCargo = historialCargoListHistorialCargo.getIdCargoarea();
                historialCargoListHistorialCargo.setIdCargoarea(areaCargo);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
                if (oldIdCargoareaOfHistorialCargoListHistorialCargo != null) {
                    oldIdCargoareaOfHistorialCargoListHistorialCargo.getHistorialCargoList().remove(historialCargoListHistorialCargo);
                    oldIdCargoareaOfHistorialCargoListHistorialCargo = em.merge(oldIdCargoareaOfHistorialCargoListHistorialCargo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaCargo areaCargo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaCargo persistentAreaCargo = em.find(AreaCargo.class, areaCargo.getIdCargoarea());
            Cargo idCargoOld = persistentAreaCargo.getIdCargo();
            Cargo idCargoNew = areaCargo.getIdCargo();
            Area idAreaOld = persistentAreaCargo.getIdArea();
            Area idAreaNew = areaCargo.getIdArea();
            List<Tabulador> tabuladorListOld = persistentAreaCargo.getTabuladorList();
            List<Tabulador> tabuladorListNew = areaCargo.getTabuladorList();
            List<HistorialCargo> historialCargoListOld = persistentAreaCargo.getHistorialCargoList();
            List<HistorialCargo> historialCargoListNew = areaCargo.getHistorialCargoList();
            if (idCargoNew != null) {
                idCargoNew = em.getReference(idCargoNew.getClass(), idCargoNew.getIdCargo());
                areaCargo.setIdCargo(idCargoNew);
            }
            if (idAreaNew != null) {
                idAreaNew = em.getReference(idAreaNew.getClass(), idAreaNew.getIdArea());
                areaCargo.setIdArea(idAreaNew);
            }
            List<Tabulador> attachedTabuladorListNew = new ArrayList<Tabulador>();
            for (Tabulador tabuladorListNewTabuladorToAttach : tabuladorListNew) {
                tabuladorListNewTabuladorToAttach = em.getReference(tabuladorListNewTabuladorToAttach.getClass(), tabuladorListNewTabuladorToAttach.getIdTabulador());
                attachedTabuladorListNew.add(tabuladorListNewTabuladorToAttach);
            }
            tabuladorListNew = attachedTabuladorListNew;
            areaCargo.setTabuladorList(tabuladorListNew);
            List<HistorialCargo> attachedHistorialCargoListNew = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListNewHistorialCargoToAttach : historialCargoListNew) {
                historialCargoListNewHistorialCargoToAttach = em.getReference(historialCargoListNewHistorialCargoToAttach.getClass(), historialCargoListNewHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoListNew.add(historialCargoListNewHistorialCargoToAttach);
            }
            historialCargoListNew = attachedHistorialCargoListNew;
            areaCargo.setHistorialCargoList(historialCargoListNew);
            areaCargo = em.merge(areaCargo);
            if (idCargoOld != null && !idCargoOld.equals(idCargoNew)) {
                idCargoOld.getAreaCargoList().remove(areaCargo);
                idCargoOld = em.merge(idCargoOld);
            }
            if (idCargoNew != null && !idCargoNew.equals(idCargoOld)) {
                idCargoNew.getAreaCargoList().add(areaCargo);
                idCargoNew = em.merge(idCargoNew);
            }
            if (idAreaOld != null && !idAreaOld.equals(idAreaNew)) {
                idAreaOld.getAreaCargoList().remove(areaCargo);
                idAreaOld = em.merge(idAreaOld);
            }
            if (idAreaNew != null && !idAreaNew.equals(idAreaOld)) {
                idAreaNew.getAreaCargoList().add(areaCargo);
                idAreaNew = em.merge(idAreaNew);
            }
            for (Tabulador tabuladorListOldTabulador : tabuladorListOld) {
                if (!tabuladorListNew.contains(tabuladorListOldTabulador)) {
                    tabuladorListOldTabulador.getAreaCargoList().remove(areaCargo);
                    tabuladorListOldTabulador = em.merge(tabuladorListOldTabulador);
                }
            }
            for (Tabulador tabuladorListNewTabulador : tabuladorListNew) {
                if (!tabuladorListOld.contains(tabuladorListNewTabulador)) {
                    tabuladorListNewTabulador.getAreaCargoList().add(areaCargo);
                    tabuladorListNewTabulador = em.merge(tabuladorListNewTabulador);
                }
            }
            for (HistorialCargo historialCargoListOldHistorialCargo : historialCargoListOld) {
                if (!historialCargoListNew.contains(historialCargoListOldHistorialCargo)) {
                    historialCargoListOldHistorialCargo.setIdCargoarea(null);
                    historialCargoListOldHistorialCargo = em.merge(historialCargoListOldHistorialCargo);
                }
            }
            for (HistorialCargo historialCargoListNewHistorialCargo : historialCargoListNew) {
                if (!historialCargoListOld.contains(historialCargoListNewHistorialCargo)) {
                    AreaCargo oldIdCargoareaOfHistorialCargoListNewHistorialCargo = historialCargoListNewHistorialCargo.getIdCargoarea();
                    historialCargoListNewHistorialCargo.setIdCargoarea(areaCargo);
                    historialCargoListNewHistorialCargo = em.merge(historialCargoListNewHistorialCargo);
                    if (oldIdCargoareaOfHistorialCargoListNewHistorialCargo != null && !oldIdCargoareaOfHistorialCargoListNewHistorialCargo.equals(areaCargo)) {
                        oldIdCargoareaOfHistorialCargoListNewHistorialCargo.getHistorialCargoList().remove(historialCargoListNewHistorialCargo);
                        oldIdCargoareaOfHistorialCargoListNewHistorialCargo = em.merge(oldIdCargoareaOfHistorialCargoListNewHistorialCargo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = areaCargo.getIdCargoarea();
                if (findAreaCargo(id) == null) {
                    throw new NonexistentEntityException("The areaCargo with id " + id + " no longer exists.");
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
            AreaCargo areaCargo;
            try {
                areaCargo = em.getReference(AreaCargo.class, id);
                areaCargo.getIdCargoarea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaCargo with id " + id + " no longer exists.", enfe);
            }
            Cargo idCargo = areaCargo.getIdCargo();
            if (idCargo != null) {
                idCargo.getAreaCargoList().remove(areaCargo);
                idCargo = em.merge(idCargo);
            }
            Area idArea = areaCargo.getIdArea();
            if (idArea != null) {
                idArea.getAreaCargoList().remove(areaCargo);
                idArea = em.merge(idArea);
            }
            List<Tabulador> tabuladorList = areaCargo.getTabuladorList();
            for (Tabulador tabuladorListTabulador : tabuladorList) {
                tabuladorListTabulador.getAreaCargoList().remove(areaCargo);
                tabuladorListTabulador = em.merge(tabuladorListTabulador);
            }
            List<HistorialCargo> historialCargoList = areaCargo.getHistorialCargoList();
            for (HistorialCargo historialCargoListHistorialCargo : historialCargoList) {
                historialCargoListHistorialCargo.setIdCargoarea(null);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
            }
            em.remove(areaCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaCargo> findAreaCargoEntities() {
        return findAreaCargoEntities(true, -1, -1);
    }

    public List<AreaCargo> findAreaCargoEntities(int maxResults, int firstResult) {
        return findAreaCargoEntities(false, maxResults, firstResult);
    }

    private List<AreaCargo> findAreaCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AreaCargo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AreaCargo findAreaCargo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaCargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCargoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AreaCargo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
