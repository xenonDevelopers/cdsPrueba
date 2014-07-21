/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TabuladorConcepto;
import com.utez.integracion.entity.AreaCargo;
import com.utez.integracion.entity.Tabulador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TabuladorJpaController implements Serializable {

    public TabuladorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabulador tabulador) {
        if (tabulador.getAreaCargoList() == null) {
            tabulador.setAreaCargoList(new ArrayList<AreaCargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TabuladorConcepto idTabuladorconcepto = tabulador.getIdTabuladorconcepto();
            if (idTabuladorconcepto != null) {
                idTabuladorconcepto = em.getReference(idTabuladorconcepto.getClass(), idTabuladorconcepto.getIdTabuladorconcepto());
                tabulador.setIdTabuladorconcepto(idTabuladorconcepto);
            }
            List<AreaCargo> attachedAreaCargoList = new ArrayList<AreaCargo>();
            for (AreaCargo areaCargoListAreaCargoToAttach : tabulador.getAreaCargoList()) {
                areaCargoListAreaCargoToAttach = em.getReference(areaCargoListAreaCargoToAttach.getClass(), areaCargoListAreaCargoToAttach.getIdCargoarea());
                attachedAreaCargoList.add(areaCargoListAreaCargoToAttach);
            }
            tabulador.setAreaCargoList(attachedAreaCargoList);
            em.persist(tabulador);
            if (idTabuladorconcepto != null) {
                idTabuladorconcepto.getTabuladorList().add(tabulador);
                idTabuladorconcepto = em.merge(idTabuladorconcepto);
            }
            for (AreaCargo areaCargoListAreaCargo : tabulador.getAreaCargoList()) {
                areaCargoListAreaCargo.getTabuladorList().add(tabulador);
                areaCargoListAreaCargo = em.merge(areaCargoListAreaCargo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabulador tabulador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabulador persistentTabulador = em.find(Tabulador.class, tabulador.getIdTabulador());
            TabuladorConcepto idTabuladorconceptoOld = persistentTabulador.getIdTabuladorconcepto();
            TabuladorConcepto idTabuladorconceptoNew = tabulador.getIdTabuladorconcepto();
            List<AreaCargo> areaCargoListOld = persistentTabulador.getAreaCargoList();
            List<AreaCargo> areaCargoListNew = tabulador.getAreaCargoList();
            if (idTabuladorconceptoNew != null) {
                idTabuladorconceptoNew = em.getReference(idTabuladorconceptoNew.getClass(), idTabuladorconceptoNew.getIdTabuladorconcepto());
                tabulador.setIdTabuladorconcepto(idTabuladorconceptoNew);
            }
            List<AreaCargo> attachedAreaCargoListNew = new ArrayList<AreaCargo>();
            for (AreaCargo areaCargoListNewAreaCargoToAttach : areaCargoListNew) {
                areaCargoListNewAreaCargoToAttach = em.getReference(areaCargoListNewAreaCargoToAttach.getClass(), areaCargoListNewAreaCargoToAttach.getIdCargoarea());
                attachedAreaCargoListNew.add(areaCargoListNewAreaCargoToAttach);
            }
            areaCargoListNew = attachedAreaCargoListNew;
            tabulador.setAreaCargoList(areaCargoListNew);
            tabulador = em.merge(tabulador);
            if (idTabuladorconceptoOld != null && !idTabuladorconceptoOld.equals(idTabuladorconceptoNew)) {
                idTabuladorconceptoOld.getTabuladorList().remove(tabulador);
                idTabuladorconceptoOld = em.merge(idTabuladorconceptoOld);
            }
            if (idTabuladorconceptoNew != null && !idTabuladorconceptoNew.equals(idTabuladorconceptoOld)) {
                idTabuladorconceptoNew.getTabuladorList().add(tabulador);
                idTabuladorconceptoNew = em.merge(idTabuladorconceptoNew);
            }
            for (AreaCargo areaCargoListOldAreaCargo : areaCargoListOld) {
                if (!areaCargoListNew.contains(areaCargoListOldAreaCargo)) {
                    areaCargoListOldAreaCargo.getTabuladorList().remove(tabulador);
                    areaCargoListOldAreaCargo = em.merge(areaCargoListOldAreaCargo);
                }
            }
            for (AreaCargo areaCargoListNewAreaCargo : areaCargoListNew) {
                if (!areaCargoListOld.contains(areaCargoListNewAreaCargo)) {
                    areaCargoListNewAreaCargo.getTabuladorList().add(tabulador);
                    areaCargoListNewAreaCargo = em.merge(areaCargoListNewAreaCargo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tabulador.getIdTabulador();
                if (findTabulador(id) == null) {
                    throw new NonexistentEntityException("The tabulador with id " + id + " no longer exists.");
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
            Tabulador tabulador;
            try {
                tabulador = em.getReference(Tabulador.class, id);
                tabulador.getIdTabulador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabulador with id " + id + " no longer exists.", enfe);
            }
            TabuladorConcepto idTabuladorconcepto = tabulador.getIdTabuladorconcepto();
            if (idTabuladorconcepto != null) {
                idTabuladorconcepto.getTabuladorList().remove(tabulador);
                idTabuladorconcepto = em.merge(idTabuladorconcepto);
            }
            List<AreaCargo> areaCargoList = tabulador.getAreaCargoList();
            for (AreaCargo areaCargoListAreaCargo : areaCargoList) {
                areaCargoListAreaCargo.getTabuladorList().remove(tabulador);
                areaCargoListAreaCargo = em.merge(areaCargoListAreaCargo);
            }
            em.remove(tabulador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabulador> findTabuladorEntities() {
        return findTabuladorEntities(true, -1, -1);
    }

    public List<Tabulador> findTabuladorEntities(int maxResults, int firstResult) {
        return findTabuladorEntities(false, maxResults, firstResult);
    }

    private List<Tabulador> findTabuladorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tabulador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tabulador findTabulador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabulador.class, id);
        } finally {
            em.close();
        }
    }

    public int getTabuladorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tabulador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
