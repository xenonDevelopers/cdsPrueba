/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.BitacoraMovimiento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Usuario;
import com.utez.integracion.entity.TipoMovimiento;
import com.utez.integracion.entity.Rol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class BitacoraMovimientoJpaController implements Serializable {

    public BitacoraMovimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BitacoraMovimiento bitacoraMovimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = bitacoraMovimiento.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getCurp());
                bitacoraMovimiento.setIdUsuario(idUsuario);
            }
            TipoMovimiento idTipomovimiento = bitacoraMovimiento.getIdTipomovimiento();
            if (idTipomovimiento != null) {
                idTipomovimiento = em.getReference(idTipomovimiento.getClass(), idTipomovimiento.getIdTipomovimiento());
                bitacoraMovimiento.setIdTipomovimiento(idTipomovimiento);
            }
            Rol idRol = bitacoraMovimiento.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                bitacoraMovimiento.setIdRol(idRol);
            }
            em.persist(bitacoraMovimiento);
            if (idUsuario != null) {
                idUsuario.getBitacoraMovimientoList().add(bitacoraMovimiento);
                idUsuario = em.merge(idUsuario);
            }
            if (idTipomovimiento != null) {
                idTipomovimiento.getBitacoraMovimientoList().add(bitacoraMovimiento);
                idTipomovimiento = em.merge(idTipomovimiento);
            }
            if (idRol != null) {
                idRol.getBitacoraMovimientoList().add(bitacoraMovimiento);
                idRol = em.merge(idRol);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BitacoraMovimiento bitacoraMovimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BitacoraMovimiento persistentBitacoraMovimiento = em.find(BitacoraMovimiento.class, bitacoraMovimiento.getIdBitacoramovimiento());
            Usuario idUsuarioOld = persistentBitacoraMovimiento.getIdUsuario();
            Usuario idUsuarioNew = bitacoraMovimiento.getIdUsuario();
            TipoMovimiento idTipomovimientoOld = persistentBitacoraMovimiento.getIdTipomovimiento();
            TipoMovimiento idTipomovimientoNew = bitacoraMovimiento.getIdTipomovimiento();
            Rol idRolOld = persistentBitacoraMovimiento.getIdRol();
            Rol idRolNew = bitacoraMovimiento.getIdRol();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getCurp());
                bitacoraMovimiento.setIdUsuario(idUsuarioNew);
            }
            if (idTipomovimientoNew != null) {
                idTipomovimientoNew = em.getReference(idTipomovimientoNew.getClass(), idTipomovimientoNew.getIdTipomovimiento());
                bitacoraMovimiento.setIdTipomovimiento(idTipomovimientoNew);
            }
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                bitacoraMovimiento.setIdRol(idRolNew);
            }
            bitacoraMovimiento = em.merge(bitacoraMovimiento);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getBitacoraMovimientoList().remove(bitacoraMovimiento);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getBitacoraMovimientoList().add(bitacoraMovimiento);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            if (idTipomovimientoOld != null && !idTipomovimientoOld.equals(idTipomovimientoNew)) {
                idTipomovimientoOld.getBitacoraMovimientoList().remove(bitacoraMovimiento);
                idTipomovimientoOld = em.merge(idTipomovimientoOld);
            }
            if (idTipomovimientoNew != null && !idTipomovimientoNew.equals(idTipomovimientoOld)) {
                idTipomovimientoNew.getBitacoraMovimientoList().add(bitacoraMovimiento);
                idTipomovimientoNew = em.merge(idTipomovimientoNew);
            }
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getBitacoraMovimientoList().remove(bitacoraMovimiento);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getBitacoraMovimientoList().add(bitacoraMovimiento);
                idRolNew = em.merge(idRolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bitacoraMovimiento.getIdBitacoramovimiento();
                if (findBitacoraMovimiento(id) == null) {
                    throw new NonexistentEntityException("The bitacoraMovimiento with id " + id + " no longer exists.");
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
            BitacoraMovimiento bitacoraMovimiento;
            try {
                bitacoraMovimiento = em.getReference(BitacoraMovimiento.class, id);
                bitacoraMovimiento.getIdBitacoramovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bitacoraMovimiento with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = bitacoraMovimiento.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getBitacoraMovimientoList().remove(bitacoraMovimiento);
                idUsuario = em.merge(idUsuario);
            }
            TipoMovimiento idTipomovimiento = bitacoraMovimiento.getIdTipomovimiento();
            if (idTipomovimiento != null) {
                idTipomovimiento.getBitacoraMovimientoList().remove(bitacoraMovimiento);
                idTipomovimiento = em.merge(idTipomovimiento);
            }
            Rol idRol = bitacoraMovimiento.getIdRol();
            if (idRol != null) {
                idRol.getBitacoraMovimientoList().remove(bitacoraMovimiento);
                idRol = em.merge(idRol);
            }
            em.remove(bitacoraMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BitacoraMovimiento> findBitacoraMovimientoEntities() {
        return findBitacoraMovimientoEntities(true, -1, -1);
    }

    public List<BitacoraMovimiento> findBitacoraMovimientoEntities(int maxResults, int firstResult) {
        return findBitacoraMovimientoEntities(false, maxResults, firstResult);
    }

    private List<BitacoraMovimiento> findBitacoraMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BitacoraMovimiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BitacoraMovimiento findBitacoraMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BitacoraMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getBitacoraMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BitacoraMovimiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
