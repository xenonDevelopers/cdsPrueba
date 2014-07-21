/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.Rol;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.BitacoraMovimiento;
import com.utez.integracion.entity.Usuario;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (usuario.getRolList() == null) {
            usuario.setRolList(new ArrayList<Rol>());
        }
        if (usuario.getBitacoraMovimientoList() == null) {
            usuario.setBitacoraMovimientoList(new ArrayList<BitacoraMovimiento>());
        }
        List<String> illegalOrphanMessages = null;
        Persona personaOrphanCheck = usuario.getPersona();
        if (personaOrphanCheck != null) {
            Usuario oldUsuarioOfPersona = personaOrphanCheck.getUsuario();
            if (oldUsuarioOfPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Persona " + personaOrphanCheck + " already has an item of type Usuario whose persona column cannot be null. Please make another selection for the persona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persona = usuario.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getCurp());
                usuario.setPersona(persona);
            }
            List<Rol> attachedRolList = new ArrayList<Rol>();
            for (Rol rolListRolToAttach : usuario.getRolList()) {
                rolListRolToAttach = em.getReference(rolListRolToAttach.getClass(), rolListRolToAttach.getIdRol());
                attachedRolList.add(rolListRolToAttach);
            }
            usuario.setRolList(attachedRolList);
            List<BitacoraMovimiento> attachedBitacoraMovimientoList = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimientoToAttach : usuario.getBitacoraMovimientoList()) {
                bitacoraMovimientoListBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoList.add(bitacoraMovimientoListBitacoraMovimientoToAttach);
            }
            usuario.setBitacoraMovimientoList(attachedBitacoraMovimientoList);
            em.persist(usuario);
            if (persona != null) {
                persona.setUsuario(usuario);
                persona = em.merge(persona);
            }
            for (Rol rolListRol : usuario.getRolList()) {
                rolListRol.getUsuarioList().add(usuario);
                rolListRol = em.merge(rolListRol);
            }
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : usuario.getBitacoraMovimientoList()) {
                Usuario oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento = bitacoraMovimientoListBitacoraMovimiento.getIdUsuario();
                bitacoraMovimientoListBitacoraMovimiento.setIdUsuario(usuario);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
                if (oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento != null) {
                    oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListBitacoraMovimiento);
                    oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento = em.merge(oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCurp()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCurp());
            Persona personaOld = persistentUsuario.getPersona();
            Persona personaNew = usuario.getPersona();
            List<Rol> rolListOld = persistentUsuario.getRolList();
            List<Rol> rolListNew = usuario.getRolList();
            List<BitacoraMovimiento> bitacoraMovimientoListOld = persistentUsuario.getBitacoraMovimientoList();
            List<BitacoraMovimiento> bitacoraMovimientoListNew = usuario.getBitacoraMovimientoList();
            List<String> illegalOrphanMessages = null;
            if (personaNew != null && !personaNew.equals(personaOld)) {
                Usuario oldUsuarioOfPersona = personaNew.getUsuario();
                if (oldUsuarioOfPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Persona " + personaNew + " already has an item of type Usuario whose persona column cannot be null. Please make another selection for the persona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getCurp());
                usuario.setPersona(personaNew);
            }
            List<Rol> attachedRolListNew = new ArrayList<Rol>();
            for (Rol rolListNewRolToAttach : rolListNew) {
                rolListNewRolToAttach = em.getReference(rolListNewRolToAttach.getClass(), rolListNewRolToAttach.getIdRol());
                attachedRolListNew.add(rolListNewRolToAttach);
            }
            rolListNew = attachedRolListNew;
            usuario.setRolList(rolListNew);
            List<BitacoraMovimiento> attachedBitacoraMovimientoListNew = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimientoToAttach : bitacoraMovimientoListNew) {
                bitacoraMovimientoListNewBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListNewBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListNewBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoListNew.add(bitacoraMovimientoListNewBitacoraMovimientoToAttach);
            }
            bitacoraMovimientoListNew = attachedBitacoraMovimientoListNew;
            usuario.setBitacoraMovimientoList(bitacoraMovimientoListNew);
            usuario = em.merge(usuario);
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setUsuario(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.setUsuario(usuario);
                personaNew = em.merge(personaNew);
            }
            for (Rol rolListOldRol : rolListOld) {
                if (!rolListNew.contains(rolListOldRol)) {
                    rolListOldRol.getUsuarioList().remove(usuario);
                    rolListOldRol = em.merge(rolListOldRol);
                }
            }
            for (Rol rolListNewRol : rolListNew) {
                if (!rolListOld.contains(rolListNewRol)) {
                    rolListNewRol.getUsuarioList().add(usuario);
                    rolListNewRol = em.merge(rolListNewRol);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListOldBitacoraMovimiento : bitacoraMovimientoListOld) {
                if (!bitacoraMovimientoListNew.contains(bitacoraMovimientoListOldBitacoraMovimiento)) {
                    bitacoraMovimientoListOldBitacoraMovimiento.setIdUsuario(null);
                    bitacoraMovimientoListOldBitacoraMovimiento = em.merge(bitacoraMovimientoListOldBitacoraMovimiento);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimiento : bitacoraMovimientoListNew) {
                if (!bitacoraMovimientoListOld.contains(bitacoraMovimientoListNewBitacoraMovimiento)) {
                    Usuario oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento = bitacoraMovimientoListNewBitacoraMovimiento.getIdUsuario();
                    bitacoraMovimientoListNewBitacoraMovimiento.setIdUsuario(usuario);
                    bitacoraMovimientoListNewBitacoraMovimiento = em.merge(bitacoraMovimientoListNewBitacoraMovimiento);
                    if (oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento != null && !oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento.equals(usuario)) {
                        oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListNewBitacoraMovimiento);
                        oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento = em.merge(oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getCurp();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCurp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Persona persona = usuario.getPersona();
            if (persona != null) {
                persona.setUsuario(null);
                persona = em.merge(persona);
            }
            List<Rol> rolList = usuario.getRolList();
            for (Rol rolListRol : rolList) {
                rolListRol.getUsuarioList().remove(usuario);
                rolListRol = em.merge(rolListRol);
            }
            List<BitacoraMovimiento> bitacoraMovimientoList = usuario.getBitacoraMovimientoList();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : bitacoraMovimientoList) {
                bitacoraMovimientoListBitacoraMovimiento.setIdUsuario(null);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Usuario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Usuario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
