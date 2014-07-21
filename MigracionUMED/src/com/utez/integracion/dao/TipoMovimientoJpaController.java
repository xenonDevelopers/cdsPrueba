/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.TipoAmbitomovimiento;
import com.utez.integracion.entity.FolioCambioscalendario;
import com.utez.integracion.entity.BitacoraMovimiento;
import com.utez.integracion.entity.TipoMovimiento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoMovimientoJpaController implements Serializable {

    public TipoMovimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMovimiento tipoMovimiento) {
        if (tipoMovimiento.getRecursoHumanoList() == null) {
            tipoMovimiento.setRecursoHumanoList(new ArrayList<RecursoHumano>());
        }
        if (tipoMovimiento.getTipoAmbitomovimientoList() == null) {
            tipoMovimiento.setTipoAmbitomovimientoList(new ArrayList<TipoAmbitomovimiento>());
        }
        if (tipoMovimiento.getFolioCambioscalendarioList() == null) {
            tipoMovimiento.setFolioCambioscalendarioList(new ArrayList<FolioCambioscalendario>());
        }
        if (tipoMovimiento.getBitacoraMovimientoList() == null) {
            tipoMovimiento.setBitacoraMovimientoList(new ArrayList<BitacoraMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RecursoHumano> attachedRecursoHumanoList = new ArrayList<RecursoHumano>();
            for (RecursoHumano recursoHumanoListRecursoHumanoToAttach : tipoMovimiento.getRecursoHumanoList()) {
                recursoHumanoListRecursoHumanoToAttach = em.getReference(recursoHumanoListRecursoHumanoToAttach.getClass(), recursoHumanoListRecursoHumanoToAttach.getIdRecursohumano());
                attachedRecursoHumanoList.add(recursoHumanoListRecursoHumanoToAttach);
            }
            tipoMovimiento.setRecursoHumanoList(attachedRecursoHumanoList);
            List<TipoAmbitomovimiento> attachedTipoAmbitomovimientoList = new ArrayList<TipoAmbitomovimiento>();
            for (TipoAmbitomovimiento tipoAmbitomovimientoListTipoAmbitomovimientoToAttach : tipoMovimiento.getTipoAmbitomovimientoList()) {
                tipoAmbitomovimientoListTipoAmbitomovimientoToAttach = em.getReference(tipoAmbitomovimientoListTipoAmbitomovimientoToAttach.getClass(), tipoAmbitomovimientoListTipoAmbitomovimientoToAttach.getIdTipoambitomovimiento());
                attachedTipoAmbitomovimientoList.add(tipoAmbitomovimientoListTipoAmbitomovimientoToAttach);
            }
            tipoMovimiento.setTipoAmbitomovimientoList(attachedTipoAmbitomovimientoList);
            List<FolioCambioscalendario> attachedFolioCambioscalendarioList = new ArrayList<FolioCambioscalendario>();
            for (FolioCambioscalendario folioCambioscalendarioListFolioCambioscalendarioToAttach : tipoMovimiento.getFolioCambioscalendarioList()) {
                folioCambioscalendarioListFolioCambioscalendarioToAttach = em.getReference(folioCambioscalendarioListFolioCambioscalendarioToAttach.getClass(), folioCambioscalendarioListFolioCambioscalendarioToAttach.getFolioCambioscalendarioPK());
                attachedFolioCambioscalendarioList.add(folioCambioscalendarioListFolioCambioscalendarioToAttach);
            }
            tipoMovimiento.setFolioCambioscalendarioList(attachedFolioCambioscalendarioList);
            List<BitacoraMovimiento> attachedBitacoraMovimientoList = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimientoToAttach : tipoMovimiento.getBitacoraMovimientoList()) {
                bitacoraMovimientoListBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoList.add(bitacoraMovimientoListBitacoraMovimientoToAttach);
            }
            tipoMovimiento.setBitacoraMovimientoList(attachedBitacoraMovimientoList);
            em.persist(tipoMovimiento);
            for (RecursoHumano recursoHumanoListRecursoHumano : tipoMovimiento.getRecursoHumanoList()) {
                recursoHumanoListRecursoHumano.getTipoMovimientoList().add(tipoMovimiento);
                recursoHumanoListRecursoHumano = em.merge(recursoHumanoListRecursoHumano);
            }
            for (TipoAmbitomovimiento tipoAmbitomovimientoListTipoAmbitomovimiento : tipoMovimiento.getTipoAmbitomovimientoList()) {
                tipoAmbitomovimientoListTipoAmbitomovimiento.getTipoMovimientoList().add(tipoMovimiento);
                tipoAmbitomovimientoListTipoAmbitomovimiento = em.merge(tipoAmbitomovimientoListTipoAmbitomovimiento);
            }
            for (FolioCambioscalendario folioCambioscalendarioListFolioCambioscalendario : tipoMovimiento.getFolioCambioscalendarioList()) {
                TipoMovimiento oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario = folioCambioscalendarioListFolioCambioscalendario.getIdMovimiento();
                folioCambioscalendarioListFolioCambioscalendario.setIdMovimiento(tipoMovimiento);
                folioCambioscalendarioListFolioCambioscalendario = em.merge(folioCambioscalendarioListFolioCambioscalendario);
                if (oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario != null) {
                    oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario.getFolioCambioscalendarioList().remove(folioCambioscalendarioListFolioCambioscalendario);
                    oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario = em.merge(oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : tipoMovimiento.getBitacoraMovimientoList()) {
                TipoMovimiento oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento = bitacoraMovimientoListBitacoraMovimiento.getIdTipomovimiento();
                bitacoraMovimientoListBitacoraMovimiento.setIdTipomovimiento(tipoMovimiento);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
                if (oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento != null) {
                    oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListBitacoraMovimiento);
                    oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento = em.merge(oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMovimiento tipoMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovimiento persistentTipoMovimiento = em.find(TipoMovimiento.class, tipoMovimiento.getIdTipomovimiento());
            List<RecursoHumano> recursoHumanoListOld = persistentTipoMovimiento.getRecursoHumanoList();
            List<RecursoHumano> recursoHumanoListNew = tipoMovimiento.getRecursoHumanoList();
            List<TipoAmbitomovimiento> tipoAmbitomovimientoListOld = persistentTipoMovimiento.getTipoAmbitomovimientoList();
            List<TipoAmbitomovimiento> tipoAmbitomovimientoListNew = tipoMovimiento.getTipoAmbitomovimientoList();
            List<FolioCambioscalendario> folioCambioscalendarioListOld = persistentTipoMovimiento.getFolioCambioscalendarioList();
            List<FolioCambioscalendario> folioCambioscalendarioListNew = tipoMovimiento.getFolioCambioscalendarioList();
            List<BitacoraMovimiento> bitacoraMovimientoListOld = persistentTipoMovimiento.getBitacoraMovimientoList();
            List<BitacoraMovimiento> bitacoraMovimientoListNew = tipoMovimiento.getBitacoraMovimientoList();
            List<RecursoHumano> attachedRecursoHumanoListNew = new ArrayList<RecursoHumano>();
            for (RecursoHumano recursoHumanoListNewRecursoHumanoToAttach : recursoHumanoListNew) {
                recursoHumanoListNewRecursoHumanoToAttach = em.getReference(recursoHumanoListNewRecursoHumanoToAttach.getClass(), recursoHumanoListNewRecursoHumanoToAttach.getIdRecursohumano());
                attachedRecursoHumanoListNew.add(recursoHumanoListNewRecursoHumanoToAttach);
            }
            recursoHumanoListNew = attachedRecursoHumanoListNew;
            tipoMovimiento.setRecursoHumanoList(recursoHumanoListNew);
            List<TipoAmbitomovimiento> attachedTipoAmbitomovimientoListNew = new ArrayList<TipoAmbitomovimiento>();
            for (TipoAmbitomovimiento tipoAmbitomovimientoListNewTipoAmbitomovimientoToAttach : tipoAmbitomovimientoListNew) {
                tipoAmbitomovimientoListNewTipoAmbitomovimientoToAttach = em.getReference(tipoAmbitomovimientoListNewTipoAmbitomovimientoToAttach.getClass(), tipoAmbitomovimientoListNewTipoAmbitomovimientoToAttach.getIdTipoambitomovimiento());
                attachedTipoAmbitomovimientoListNew.add(tipoAmbitomovimientoListNewTipoAmbitomovimientoToAttach);
            }
            tipoAmbitomovimientoListNew = attachedTipoAmbitomovimientoListNew;
            tipoMovimiento.setTipoAmbitomovimientoList(tipoAmbitomovimientoListNew);
            List<FolioCambioscalendario> attachedFolioCambioscalendarioListNew = new ArrayList<FolioCambioscalendario>();
            for (FolioCambioscalendario folioCambioscalendarioListNewFolioCambioscalendarioToAttach : folioCambioscalendarioListNew) {
                folioCambioscalendarioListNewFolioCambioscalendarioToAttach = em.getReference(folioCambioscalendarioListNewFolioCambioscalendarioToAttach.getClass(), folioCambioscalendarioListNewFolioCambioscalendarioToAttach.getFolioCambioscalendarioPK());
                attachedFolioCambioscalendarioListNew.add(folioCambioscalendarioListNewFolioCambioscalendarioToAttach);
            }
            folioCambioscalendarioListNew = attachedFolioCambioscalendarioListNew;
            tipoMovimiento.setFolioCambioscalendarioList(folioCambioscalendarioListNew);
            List<BitacoraMovimiento> attachedBitacoraMovimientoListNew = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimientoToAttach : bitacoraMovimientoListNew) {
                bitacoraMovimientoListNewBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListNewBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListNewBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoListNew.add(bitacoraMovimientoListNewBitacoraMovimientoToAttach);
            }
            bitacoraMovimientoListNew = attachedBitacoraMovimientoListNew;
            tipoMovimiento.setBitacoraMovimientoList(bitacoraMovimientoListNew);
            tipoMovimiento = em.merge(tipoMovimiento);
            for (RecursoHumano recursoHumanoListOldRecursoHumano : recursoHumanoListOld) {
                if (!recursoHumanoListNew.contains(recursoHumanoListOldRecursoHumano)) {
                    recursoHumanoListOldRecursoHumano.getTipoMovimientoList().remove(tipoMovimiento);
                    recursoHumanoListOldRecursoHumano = em.merge(recursoHumanoListOldRecursoHumano);
                }
            }
            for (RecursoHumano recursoHumanoListNewRecursoHumano : recursoHumanoListNew) {
                if (!recursoHumanoListOld.contains(recursoHumanoListNewRecursoHumano)) {
                    recursoHumanoListNewRecursoHumano.getTipoMovimientoList().add(tipoMovimiento);
                    recursoHumanoListNewRecursoHumano = em.merge(recursoHumanoListNewRecursoHumano);
                }
            }
            for (TipoAmbitomovimiento tipoAmbitomovimientoListOldTipoAmbitomovimiento : tipoAmbitomovimientoListOld) {
                if (!tipoAmbitomovimientoListNew.contains(tipoAmbitomovimientoListOldTipoAmbitomovimiento)) {
                    tipoAmbitomovimientoListOldTipoAmbitomovimiento.getTipoMovimientoList().remove(tipoMovimiento);
                    tipoAmbitomovimientoListOldTipoAmbitomovimiento = em.merge(tipoAmbitomovimientoListOldTipoAmbitomovimiento);
                }
            }
            for (TipoAmbitomovimiento tipoAmbitomovimientoListNewTipoAmbitomovimiento : tipoAmbitomovimientoListNew) {
                if (!tipoAmbitomovimientoListOld.contains(tipoAmbitomovimientoListNewTipoAmbitomovimiento)) {
                    tipoAmbitomovimientoListNewTipoAmbitomovimiento.getTipoMovimientoList().add(tipoMovimiento);
                    tipoAmbitomovimientoListNewTipoAmbitomovimiento = em.merge(tipoAmbitomovimientoListNewTipoAmbitomovimiento);
                }
            }
            for (FolioCambioscalendario folioCambioscalendarioListOldFolioCambioscalendario : folioCambioscalendarioListOld) {
                if (!folioCambioscalendarioListNew.contains(folioCambioscalendarioListOldFolioCambioscalendario)) {
                    folioCambioscalendarioListOldFolioCambioscalendario.setIdMovimiento(null);
                    folioCambioscalendarioListOldFolioCambioscalendario = em.merge(folioCambioscalendarioListOldFolioCambioscalendario);
                }
            }
            for (FolioCambioscalendario folioCambioscalendarioListNewFolioCambioscalendario : folioCambioscalendarioListNew) {
                if (!folioCambioscalendarioListOld.contains(folioCambioscalendarioListNewFolioCambioscalendario)) {
                    TipoMovimiento oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario = folioCambioscalendarioListNewFolioCambioscalendario.getIdMovimiento();
                    folioCambioscalendarioListNewFolioCambioscalendario.setIdMovimiento(tipoMovimiento);
                    folioCambioscalendarioListNewFolioCambioscalendario = em.merge(folioCambioscalendarioListNewFolioCambioscalendario);
                    if (oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario != null && !oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario.equals(tipoMovimiento)) {
                        oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario.getFolioCambioscalendarioList().remove(folioCambioscalendarioListNewFolioCambioscalendario);
                        oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario = em.merge(oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario);
                    }
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListOldBitacoraMovimiento : bitacoraMovimientoListOld) {
                if (!bitacoraMovimientoListNew.contains(bitacoraMovimientoListOldBitacoraMovimiento)) {
                    bitacoraMovimientoListOldBitacoraMovimiento.setIdTipomovimiento(null);
                    bitacoraMovimientoListOldBitacoraMovimiento = em.merge(bitacoraMovimientoListOldBitacoraMovimiento);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimiento : bitacoraMovimientoListNew) {
                if (!bitacoraMovimientoListOld.contains(bitacoraMovimientoListNewBitacoraMovimiento)) {
                    TipoMovimiento oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento = bitacoraMovimientoListNewBitacoraMovimiento.getIdTipomovimiento();
                    bitacoraMovimientoListNewBitacoraMovimiento.setIdTipomovimiento(tipoMovimiento);
                    bitacoraMovimientoListNewBitacoraMovimiento = em.merge(bitacoraMovimientoListNewBitacoraMovimiento);
                    if (oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento != null && !oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento.equals(tipoMovimiento)) {
                        oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListNewBitacoraMovimiento);
                        oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento = em.merge(oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoMovimiento.getIdTipomovimiento();
                if (findTipoMovimiento(id) == null) {
                    throw new NonexistentEntityException("The tipoMovimiento with id " + id + " no longer exists.");
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
            TipoMovimiento tipoMovimiento;
            try {
                tipoMovimiento = em.getReference(TipoMovimiento.class, id);
                tipoMovimiento.getIdTipomovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMovimiento with id " + id + " no longer exists.", enfe);
            }
            List<RecursoHumano> recursoHumanoList = tipoMovimiento.getRecursoHumanoList();
            for (RecursoHumano recursoHumanoListRecursoHumano : recursoHumanoList) {
                recursoHumanoListRecursoHumano.getTipoMovimientoList().remove(tipoMovimiento);
                recursoHumanoListRecursoHumano = em.merge(recursoHumanoListRecursoHumano);
            }
            List<TipoAmbitomovimiento> tipoAmbitomovimientoList = tipoMovimiento.getTipoAmbitomovimientoList();
            for (TipoAmbitomovimiento tipoAmbitomovimientoListTipoAmbitomovimiento : tipoAmbitomovimientoList) {
                tipoAmbitomovimientoListTipoAmbitomovimiento.getTipoMovimientoList().remove(tipoMovimiento);
                tipoAmbitomovimientoListTipoAmbitomovimiento = em.merge(tipoAmbitomovimientoListTipoAmbitomovimiento);
            }
            List<FolioCambioscalendario> folioCambioscalendarioList = tipoMovimiento.getFolioCambioscalendarioList();
            for (FolioCambioscalendario folioCambioscalendarioListFolioCambioscalendario : folioCambioscalendarioList) {
                folioCambioscalendarioListFolioCambioscalendario.setIdMovimiento(null);
                folioCambioscalendarioListFolioCambioscalendario = em.merge(folioCambioscalendarioListFolioCambioscalendario);
            }
            List<BitacoraMovimiento> bitacoraMovimientoList = tipoMovimiento.getBitacoraMovimientoList();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : bitacoraMovimientoList) {
                bitacoraMovimientoListBitacoraMovimiento.setIdTipomovimiento(null);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
            }
            em.remove(tipoMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMovimiento> findTipoMovimientoEntities() {
        return findTipoMovimientoEntities(true, -1, -1);
    }

    public List<TipoMovimiento> findTipoMovimientoEntities(int maxResults, int firstResult) {
        return findTipoMovimientoEntities(false, maxResults, firstResult);
    }

    private List<TipoMovimiento> findTipoMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoMovimiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoMovimiento findTipoMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoMovimiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
