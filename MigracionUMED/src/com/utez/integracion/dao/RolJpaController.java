/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.RolRecursopermiso;
import com.utez.integracion.entity.BitacoraMovimiento;
import com.utez.integracion.entity.Rol;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RolJpaController implements Serializable {

    public RolJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getUsuarioList() == null) {
            rol.setUsuarioList(new ArrayList<Usuario>());
        }
        if (rol.getRolRecursopermisoList() == null) {
            rol.setRolRecursopermisoList(new ArrayList<RolRecursopermiso>());
        }
        if (rol.getBitacoraMovimientoList() == null) {
            rol.setBitacoraMovimientoList(new ArrayList<BitacoraMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : rol.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getCurp());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            rol.setUsuarioList(attachedUsuarioList);
            List<RolRecursopermiso> attachedRolRecursopermisoList = new ArrayList<RolRecursopermiso>();
            for (RolRecursopermiso rolRecursopermisoListRolRecursopermisoToAttach : rol.getRolRecursopermisoList()) {
                rolRecursopermisoListRolRecursopermisoToAttach = em.getReference(rolRecursopermisoListRolRecursopermisoToAttach.getClass(), rolRecursopermisoListRolRecursopermisoToAttach.getRolRecursopermisoPK());
                attachedRolRecursopermisoList.add(rolRecursopermisoListRolRecursopermisoToAttach);
            }
            rol.setRolRecursopermisoList(attachedRolRecursopermisoList);
            List<BitacoraMovimiento> attachedBitacoraMovimientoList = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimientoToAttach : rol.getBitacoraMovimientoList()) {
                bitacoraMovimientoListBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoList.add(bitacoraMovimientoListBitacoraMovimientoToAttach);
            }
            rol.setBitacoraMovimientoList(attachedBitacoraMovimientoList);
            em.persist(rol);
            for (Usuario usuarioListUsuario : rol.getUsuarioList()) {
                usuarioListUsuario.getRolList().add(rol);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            for (RolRecursopermiso rolRecursopermisoListRolRecursopermiso : rol.getRolRecursopermisoList()) {
                Rol oldRolOfRolRecursopermisoListRolRecursopermiso = rolRecursopermisoListRolRecursopermiso.getRol();
                rolRecursopermisoListRolRecursopermiso.setRol(rol);
                rolRecursopermisoListRolRecursopermiso = em.merge(rolRecursopermisoListRolRecursopermiso);
                if (oldRolOfRolRecursopermisoListRolRecursopermiso != null) {
                    oldRolOfRolRecursopermisoListRolRecursopermiso.getRolRecursopermisoList().remove(rolRecursopermisoListRolRecursopermiso);
                    oldRolOfRolRecursopermisoListRolRecursopermiso = em.merge(oldRolOfRolRecursopermisoListRolRecursopermiso);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : rol.getBitacoraMovimientoList()) {
                Rol oldIdRolOfBitacoraMovimientoListBitacoraMovimiento = bitacoraMovimientoListBitacoraMovimiento.getIdRol();
                bitacoraMovimientoListBitacoraMovimiento.setIdRol(rol);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
                if (oldIdRolOfBitacoraMovimientoListBitacoraMovimiento != null) {
                    oldIdRolOfBitacoraMovimientoListBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListBitacoraMovimiento);
                    oldIdRolOfBitacoraMovimientoListBitacoraMovimiento = em.merge(oldIdRolOfBitacoraMovimientoListBitacoraMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getIdRol());
            List<Usuario> usuarioListOld = persistentRol.getUsuarioList();
            List<Usuario> usuarioListNew = rol.getUsuarioList();
            List<RolRecursopermiso> rolRecursopermisoListOld = persistentRol.getRolRecursopermisoList();
            List<RolRecursopermiso> rolRecursopermisoListNew = rol.getRolRecursopermisoList();
            List<BitacoraMovimiento> bitacoraMovimientoListOld = persistentRol.getBitacoraMovimientoList();
            List<BitacoraMovimiento> bitacoraMovimientoListNew = rol.getBitacoraMovimientoList();
            List<String> illegalOrphanMessages = null;
            for (RolRecursopermiso rolRecursopermisoListOldRolRecursopermiso : rolRecursopermisoListOld) {
                if (!rolRecursopermisoListNew.contains(rolRecursopermisoListOldRolRecursopermiso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RolRecursopermiso " + rolRecursopermisoListOldRolRecursopermiso + " since its rol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getCurp());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            rol.setUsuarioList(usuarioListNew);
            List<RolRecursopermiso> attachedRolRecursopermisoListNew = new ArrayList<RolRecursopermiso>();
            for (RolRecursopermiso rolRecursopermisoListNewRolRecursopermisoToAttach : rolRecursopermisoListNew) {
                rolRecursopermisoListNewRolRecursopermisoToAttach = em.getReference(rolRecursopermisoListNewRolRecursopermisoToAttach.getClass(), rolRecursopermisoListNewRolRecursopermisoToAttach.getRolRecursopermisoPK());
                attachedRolRecursopermisoListNew.add(rolRecursopermisoListNewRolRecursopermisoToAttach);
            }
            rolRecursopermisoListNew = attachedRolRecursopermisoListNew;
            rol.setRolRecursopermisoList(rolRecursopermisoListNew);
            List<BitacoraMovimiento> attachedBitacoraMovimientoListNew = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimientoToAttach : bitacoraMovimientoListNew) {
                bitacoraMovimientoListNewBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListNewBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListNewBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoListNew.add(bitacoraMovimientoListNewBitacoraMovimientoToAttach);
            }
            bitacoraMovimientoListNew = attachedBitacoraMovimientoListNew;
            rol.setBitacoraMovimientoList(bitacoraMovimientoListNew);
            rol = em.merge(rol);
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.getRolList().remove(rol);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    usuarioListNewUsuario.getRolList().add(rol);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                }
            }
            for (RolRecursopermiso rolRecursopermisoListNewRolRecursopermiso : rolRecursopermisoListNew) {
                if (!rolRecursopermisoListOld.contains(rolRecursopermisoListNewRolRecursopermiso)) {
                    Rol oldRolOfRolRecursopermisoListNewRolRecursopermiso = rolRecursopermisoListNewRolRecursopermiso.getRol();
                    rolRecursopermisoListNewRolRecursopermiso.setRol(rol);
                    rolRecursopermisoListNewRolRecursopermiso = em.merge(rolRecursopermisoListNewRolRecursopermiso);
                    if (oldRolOfRolRecursopermisoListNewRolRecursopermiso != null && !oldRolOfRolRecursopermisoListNewRolRecursopermiso.equals(rol)) {
                        oldRolOfRolRecursopermisoListNewRolRecursopermiso.getRolRecursopermisoList().remove(rolRecursopermisoListNewRolRecursopermiso);
                        oldRolOfRolRecursopermisoListNewRolRecursopermiso = em.merge(oldRolOfRolRecursopermisoListNewRolRecursopermiso);
                    }
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListOldBitacoraMovimiento : bitacoraMovimientoListOld) {
                if (!bitacoraMovimientoListNew.contains(bitacoraMovimientoListOldBitacoraMovimiento)) {
                    bitacoraMovimientoListOldBitacoraMovimiento.setIdRol(null);
                    bitacoraMovimientoListOldBitacoraMovimiento = em.merge(bitacoraMovimientoListOldBitacoraMovimiento);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimiento : bitacoraMovimientoListNew) {
                if (!bitacoraMovimientoListOld.contains(bitacoraMovimientoListNewBitacoraMovimiento)) {
                    Rol oldIdRolOfBitacoraMovimientoListNewBitacoraMovimiento = bitacoraMovimientoListNewBitacoraMovimiento.getIdRol();
                    bitacoraMovimientoListNewBitacoraMovimiento.setIdRol(rol);
                    bitacoraMovimientoListNewBitacoraMovimiento = em.merge(bitacoraMovimientoListNewBitacoraMovimiento);
                    if (oldIdRolOfBitacoraMovimientoListNewBitacoraMovimiento != null && !oldIdRolOfBitacoraMovimientoListNewBitacoraMovimiento.equals(rol)) {
                        oldIdRolOfBitacoraMovimientoListNewBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListNewBitacoraMovimiento);
                        oldIdRolOfBitacoraMovimientoListNewBitacoraMovimiento = em.merge(oldIdRolOfBitacoraMovimientoListNewBitacoraMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rol.getIdRol();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getIdRol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RolRecursopermiso> rolRecursopermisoListOrphanCheck = rol.getRolRecursopermisoList();
            for (RolRecursopermiso rolRecursopermisoListOrphanCheckRolRecursopermiso : rolRecursopermisoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the RolRecursopermiso " + rolRecursopermisoListOrphanCheckRolRecursopermiso + " in its rolRecursopermisoList field has a non-nullable rol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuario> usuarioList = rol.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.getRolList().remove(rol);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<BitacoraMovimiento> bitacoraMovimientoList = rol.getBitacoraMovimientoList();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : bitacoraMovimientoList) {
                bitacoraMovimientoListBitacoraMovimiento.setIdRol(null);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Rol as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rol findRol(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Rol as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
