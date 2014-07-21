/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.VigenciaCalificacion;
import com.utez.integracion.entity.FechaExamen;
import com.utez.integracion.entity.AsignacionEncuestadocente;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionAplicador;
import com.utez.integracion.entity.EntregaExamen;
import com.utez.integracion.entity.FechaExamenprogramado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechaExamenprogramadoJpaController implements Serializable {

    public FechaExamenprogramadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FechaExamenprogramado fechaExamenprogramado) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (fechaExamenprogramado.getAsignacionEncuestadocenteList() == null) {
            fechaExamenprogramado.setAsignacionEncuestadocenteList(new ArrayList<AsignacionEncuestadocente>());
        }
        if (fechaExamenprogramado.getAsignacionAplicadorList() == null) {
            fechaExamenprogramado.setAsignacionAplicadorList(new ArrayList<AsignacionAplicador>());
        }
        if (fechaExamenprogramado.getEntregaExamenList() == null) {
            fechaExamenprogramado.setEntregaExamenList(new ArrayList<EntregaExamen>());
        }
        List<String> illegalOrphanMessages = null;
        FechaExamen fechaExamen1OrphanCheck = fechaExamenprogramado.getFechaExamen1();
        if (fechaExamen1OrphanCheck != null) {
            FechaExamenprogramado oldFechaExamenprogramadoOfFechaExamen1 = fechaExamen1OrphanCheck.getFechaExamenprogramado();
            if (oldFechaExamenprogramadoOfFechaExamen1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The FechaExamen " + fechaExamen1OrphanCheck + " already has an item of type FechaExamenprogramado whose fechaExamen1 column cannot be null. Please make another selection for the fechaExamen1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VigenciaCalificacion vigenciaCalificacion = fechaExamenprogramado.getVigenciaCalificacion();
            if (vigenciaCalificacion != null) {
                vigenciaCalificacion = em.getReference(vigenciaCalificacion.getClass(), vigenciaCalificacion.getIdExamen());
                fechaExamenprogramado.setVigenciaCalificacion(vigenciaCalificacion);
            }
            FechaExamen fechaExamen1 = fechaExamenprogramado.getFechaExamen1();
            if (fechaExamen1 != null) {
                fechaExamen1 = em.getReference(fechaExamen1.getClass(), fechaExamen1.getIdFechaexamen());
                fechaExamenprogramado.setFechaExamen1(fechaExamen1);
            }
            List<AsignacionEncuestadocente> attachedAsignacionEncuestadocenteList = new ArrayList<AsignacionEncuestadocente>();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach : fechaExamenprogramado.getAsignacionEncuestadocenteList()) {
                asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach = em.getReference(asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach.getClass(), asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach.getIdAsignacionencuestadocente());
                attachedAsignacionEncuestadocenteList.add(asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach);
            }
            fechaExamenprogramado.setAsignacionEncuestadocenteList(attachedAsignacionEncuestadocenteList);
            List<AsignacionAplicador> attachedAsignacionAplicadorList = new ArrayList<AsignacionAplicador>();
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicadorToAttach : fechaExamenprogramado.getAsignacionAplicadorList()) {
                asignacionAplicadorListAsignacionAplicadorToAttach = em.getReference(asignacionAplicadorListAsignacionAplicadorToAttach.getClass(), asignacionAplicadorListAsignacionAplicadorToAttach.getIdAsignacionaplicador());
                attachedAsignacionAplicadorList.add(asignacionAplicadorListAsignacionAplicadorToAttach);
            }
            fechaExamenprogramado.setAsignacionAplicadorList(attachedAsignacionAplicadorList);
            List<EntregaExamen> attachedEntregaExamenList = new ArrayList<EntregaExamen>();
            for (EntregaExamen entregaExamenListEntregaExamenToAttach : fechaExamenprogramado.getEntregaExamenList()) {
                entregaExamenListEntregaExamenToAttach = em.getReference(entregaExamenListEntregaExamenToAttach.getClass(), entregaExamenListEntregaExamenToAttach.getIdEntregaexamen());
                attachedEntregaExamenList.add(entregaExamenListEntregaExamenToAttach);
            }
            fechaExamenprogramado.setEntregaExamenList(attachedEntregaExamenList);
            em.persist(fechaExamenprogramado);
            if (vigenciaCalificacion != null) {
                FechaExamenprogramado oldFechaExamenprogramadoOfVigenciaCalificacion = vigenciaCalificacion.getFechaExamenprogramado();
                if (oldFechaExamenprogramadoOfVigenciaCalificacion != null) {
                    oldFechaExamenprogramadoOfVigenciaCalificacion.setVigenciaCalificacion(null);
                    oldFechaExamenprogramadoOfVigenciaCalificacion = em.merge(oldFechaExamenprogramadoOfVigenciaCalificacion);
                }
                vigenciaCalificacion.setFechaExamenprogramado(fechaExamenprogramado);
                vigenciaCalificacion = em.merge(vigenciaCalificacion);
            }
            if (fechaExamen1 != null) {
                fechaExamen1.setFechaExamenprogramado(fechaExamenprogramado);
                fechaExamen1 = em.merge(fechaExamen1);
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocente : fechaExamenprogramado.getAsignacionEncuestadocenteList()) {
                FechaExamenprogramado oldIdFechaexamenOfAsignacionEncuestadocenteListAsignacionEncuestadocente = asignacionEncuestadocenteListAsignacionEncuestadocente.getIdFechaexamen();
                asignacionEncuestadocenteListAsignacionEncuestadocente.setIdFechaexamen(fechaExamenprogramado);
                asignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListAsignacionEncuestadocente);
                if (oldIdFechaexamenOfAsignacionEncuestadocenteListAsignacionEncuestadocente != null) {
                    oldIdFechaexamenOfAsignacionEncuestadocenteListAsignacionEncuestadocente.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocenteListAsignacionEncuestadocente);
                    oldIdFechaexamenOfAsignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(oldIdFechaexamenOfAsignacionEncuestadocenteListAsignacionEncuestadocente);
                }
            }
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicador : fechaExamenprogramado.getAsignacionAplicadorList()) {
                FechaExamenprogramado oldIdFechaexamenprogramadoOfAsignacionAplicadorListAsignacionAplicador = asignacionAplicadorListAsignacionAplicador.getIdFechaexamenprogramado();
                asignacionAplicadorListAsignacionAplicador.setIdFechaexamenprogramado(fechaExamenprogramado);
                asignacionAplicadorListAsignacionAplicador = em.merge(asignacionAplicadorListAsignacionAplicador);
                if (oldIdFechaexamenprogramadoOfAsignacionAplicadorListAsignacionAplicador != null) {
                    oldIdFechaexamenprogramadoOfAsignacionAplicadorListAsignacionAplicador.getAsignacionAplicadorList().remove(asignacionAplicadorListAsignacionAplicador);
                    oldIdFechaexamenprogramadoOfAsignacionAplicadorListAsignacionAplicador = em.merge(oldIdFechaexamenprogramadoOfAsignacionAplicadorListAsignacionAplicador);
                }
            }
            for (EntregaExamen entregaExamenListEntregaExamen : fechaExamenprogramado.getEntregaExamenList()) {
                FechaExamenprogramado oldIdFechaexamenprogramadoOfEntregaExamenListEntregaExamen = entregaExamenListEntregaExamen.getIdFechaexamenprogramado();
                entregaExamenListEntregaExamen.setIdFechaexamenprogramado(fechaExamenprogramado);
                entregaExamenListEntregaExamen = em.merge(entregaExamenListEntregaExamen);
                if (oldIdFechaexamenprogramadoOfEntregaExamenListEntregaExamen != null) {
                    oldIdFechaexamenprogramadoOfEntregaExamenListEntregaExamen.getEntregaExamenList().remove(entregaExamenListEntregaExamen);
                    oldIdFechaexamenprogramadoOfEntregaExamenListEntregaExamen = em.merge(oldIdFechaexamenprogramadoOfEntregaExamenListEntregaExamen);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFechaExamenprogramado(fechaExamenprogramado.getIdFechaExamen()) != null) {
                throw new PreexistingEntityException("FechaExamenprogramado " + fechaExamenprogramado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FechaExamenprogramado fechaExamenprogramado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenprogramado persistentFechaExamenprogramado = em.find(FechaExamenprogramado.class, fechaExamenprogramado.getIdFechaExamen());
            VigenciaCalificacion vigenciaCalificacionOld = persistentFechaExamenprogramado.getVigenciaCalificacion();
            VigenciaCalificacion vigenciaCalificacionNew = fechaExamenprogramado.getVigenciaCalificacion();
            FechaExamen fechaExamen1Old = persistentFechaExamenprogramado.getFechaExamen1();
            FechaExamen fechaExamen1New = fechaExamenprogramado.getFechaExamen1();
            List<AsignacionEncuestadocente> asignacionEncuestadocenteListOld = persistentFechaExamenprogramado.getAsignacionEncuestadocenteList();
            List<AsignacionEncuestadocente> asignacionEncuestadocenteListNew = fechaExamenprogramado.getAsignacionEncuestadocenteList();
            List<AsignacionAplicador> asignacionAplicadorListOld = persistentFechaExamenprogramado.getAsignacionAplicadorList();
            List<AsignacionAplicador> asignacionAplicadorListNew = fechaExamenprogramado.getAsignacionAplicadorList();
            List<EntregaExamen> entregaExamenListOld = persistentFechaExamenprogramado.getEntregaExamenList();
            List<EntregaExamen> entregaExamenListNew = fechaExamenprogramado.getEntregaExamenList();
            List<String> illegalOrphanMessages = null;
            if (vigenciaCalificacionOld != null && !vigenciaCalificacionOld.equals(vigenciaCalificacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain VigenciaCalificacion " + vigenciaCalificacionOld + " since its fechaExamenprogramado field is not nullable.");
            }
            if (fechaExamen1New != null && !fechaExamen1New.equals(fechaExamen1Old)) {
                FechaExamenprogramado oldFechaExamenprogramadoOfFechaExamen1 = fechaExamen1New.getFechaExamenprogramado();
                if (oldFechaExamenprogramadoOfFechaExamen1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The FechaExamen " + fechaExamen1New + " already has an item of type FechaExamenprogramado whose fechaExamen1 column cannot be null. Please make another selection for the fechaExamen1 field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (vigenciaCalificacionNew != null) {
                vigenciaCalificacionNew = em.getReference(vigenciaCalificacionNew.getClass(), vigenciaCalificacionNew.getIdExamen());
                fechaExamenprogramado.setVigenciaCalificacion(vigenciaCalificacionNew);
            }
            if (fechaExamen1New != null) {
                fechaExamen1New = em.getReference(fechaExamen1New.getClass(), fechaExamen1New.getIdFechaexamen());
                fechaExamenprogramado.setFechaExamen1(fechaExamen1New);
            }
            List<AsignacionEncuestadocente> attachedAsignacionEncuestadocenteListNew = new ArrayList<AsignacionEncuestadocente>();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach : asignacionEncuestadocenteListNew) {
                asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach = em.getReference(asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach.getClass(), asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach.getIdAsignacionencuestadocente());
                attachedAsignacionEncuestadocenteListNew.add(asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach);
            }
            asignacionEncuestadocenteListNew = attachedAsignacionEncuestadocenteListNew;
            fechaExamenprogramado.setAsignacionEncuestadocenteList(asignacionEncuestadocenteListNew);
            List<AsignacionAplicador> attachedAsignacionAplicadorListNew = new ArrayList<AsignacionAplicador>();
            for (AsignacionAplicador asignacionAplicadorListNewAsignacionAplicadorToAttach : asignacionAplicadorListNew) {
                asignacionAplicadorListNewAsignacionAplicadorToAttach = em.getReference(asignacionAplicadorListNewAsignacionAplicadorToAttach.getClass(), asignacionAplicadorListNewAsignacionAplicadorToAttach.getIdAsignacionaplicador());
                attachedAsignacionAplicadorListNew.add(asignacionAplicadorListNewAsignacionAplicadorToAttach);
            }
            asignacionAplicadorListNew = attachedAsignacionAplicadorListNew;
            fechaExamenprogramado.setAsignacionAplicadorList(asignacionAplicadorListNew);
            List<EntregaExamen> attachedEntregaExamenListNew = new ArrayList<EntregaExamen>();
            for (EntregaExamen entregaExamenListNewEntregaExamenToAttach : entregaExamenListNew) {
                entregaExamenListNewEntregaExamenToAttach = em.getReference(entregaExamenListNewEntregaExamenToAttach.getClass(), entregaExamenListNewEntregaExamenToAttach.getIdEntregaexamen());
                attachedEntregaExamenListNew.add(entregaExamenListNewEntregaExamenToAttach);
            }
            entregaExamenListNew = attachedEntregaExamenListNew;
            fechaExamenprogramado.setEntregaExamenList(entregaExamenListNew);
            fechaExamenprogramado = em.merge(fechaExamenprogramado);
            if (vigenciaCalificacionNew != null && !vigenciaCalificacionNew.equals(vigenciaCalificacionOld)) {
                FechaExamenprogramado oldFechaExamenprogramadoOfVigenciaCalificacion = vigenciaCalificacionNew.getFechaExamenprogramado();
                if (oldFechaExamenprogramadoOfVigenciaCalificacion != null) {
                    oldFechaExamenprogramadoOfVigenciaCalificacion.setVigenciaCalificacion(null);
                    oldFechaExamenprogramadoOfVigenciaCalificacion = em.merge(oldFechaExamenprogramadoOfVigenciaCalificacion);
                }
                vigenciaCalificacionNew.setFechaExamenprogramado(fechaExamenprogramado);
                vigenciaCalificacionNew = em.merge(vigenciaCalificacionNew);
            }
            if (fechaExamen1Old != null && !fechaExamen1Old.equals(fechaExamen1New)) {
                fechaExamen1Old.setFechaExamenprogramado(null);
                fechaExamen1Old = em.merge(fechaExamen1Old);
            }
            if (fechaExamen1New != null && !fechaExamen1New.equals(fechaExamen1Old)) {
                fechaExamen1New.setFechaExamenprogramado(fechaExamenprogramado);
                fechaExamen1New = em.merge(fechaExamen1New);
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListOldAsignacionEncuestadocente : asignacionEncuestadocenteListOld) {
                if (!asignacionEncuestadocenteListNew.contains(asignacionEncuestadocenteListOldAsignacionEncuestadocente)) {
                    asignacionEncuestadocenteListOldAsignacionEncuestadocente.setIdFechaexamen(null);
                    asignacionEncuestadocenteListOldAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListOldAsignacionEncuestadocente);
                }
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListNewAsignacionEncuestadocente : asignacionEncuestadocenteListNew) {
                if (!asignacionEncuestadocenteListOld.contains(asignacionEncuestadocenteListNewAsignacionEncuestadocente)) {
                    FechaExamenprogramado oldIdFechaexamenOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente = asignacionEncuestadocenteListNewAsignacionEncuestadocente.getIdFechaexamen();
                    asignacionEncuestadocenteListNewAsignacionEncuestadocente.setIdFechaexamen(fechaExamenprogramado);
                    asignacionEncuestadocenteListNewAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListNewAsignacionEncuestadocente);
                    if (oldIdFechaexamenOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente != null && !oldIdFechaexamenOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente.equals(fechaExamenprogramado)) {
                        oldIdFechaexamenOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocenteListNewAsignacionEncuestadocente);
                        oldIdFechaexamenOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente = em.merge(oldIdFechaexamenOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente);
                    }
                }
            }
            for (AsignacionAplicador asignacionAplicadorListOldAsignacionAplicador : asignacionAplicadorListOld) {
                if (!asignacionAplicadorListNew.contains(asignacionAplicadorListOldAsignacionAplicador)) {
                    asignacionAplicadorListOldAsignacionAplicador.setIdFechaexamenprogramado(null);
                    asignacionAplicadorListOldAsignacionAplicador = em.merge(asignacionAplicadorListOldAsignacionAplicador);
                }
            }
            for (AsignacionAplicador asignacionAplicadorListNewAsignacionAplicador : asignacionAplicadorListNew) {
                if (!asignacionAplicadorListOld.contains(asignacionAplicadorListNewAsignacionAplicador)) {
                    FechaExamenprogramado oldIdFechaexamenprogramadoOfAsignacionAplicadorListNewAsignacionAplicador = asignacionAplicadorListNewAsignacionAplicador.getIdFechaexamenprogramado();
                    asignacionAplicadorListNewAsignacionAplicador.setIdFechaexamenprogramado(fechaExamenprogramado);
                    asignacionAplicadorListNewAsignacionAplicador = em.merge(asignacionAplicadorListNewAsignacionAplicador);
                    if (oldIdFechaexamenprogramadoOfAsignacionAplicadorListNewAsignacionAplicador != null && !oldIdFechaexamenprogramadoOfAsignacionAplicadorListNewAsignacionAplicador.equals(fechaExamenprogramado)) {
                        oldIdFechaexamenprogramadoOfAsignacionAplicadorListNewAsignacionAplicador.getAsignacionAplicadorList().remove(asignacionAplicadorListNewAsignacionAplicador);
                        oldIdFechaexamenprogramadoOfAsignacionAplicadorListNewAsignacionAplicador = em.merge(oldIdFechaexamenprogramadoOfAsignacionAplicadorListNewAsignacionAplicador);
                    }
                }
            }
            for (EntregaExamen entregaExamenListOldEntregaExamen : entregaExamenListOld) {
                if (!entregaExamenListNew.contains(entregaExamenListOldEntregaExamen)) {
                    entregaExamenListOldEntregaExamen.setIdFechaexamenprogramado(null);
                    entregaExamenListOldEntregaExamen = em.merge(entregaExamenListOldEntregaExamen);
                }
            }
            for (EntregaExamen entregaExamenListNewEntregaExamen : entregaExamenListNew) {
                if (!entregaExamenListOld.contains(entregaExamenListNewEntregaExamen)) {
                    FechaExamenprogramado oldIdFechaexamenprogramadoOfEntregaExamenListNewEntregaExamen = entregaExamenListNewEntregaExamen.getIdFechaexamenprogramado();
                    entregaExamenListNewEntregaExamen.setIdFechaexamenprogramado(fechaExamenprogramado);
                    entregaExamenListNewEntregaExamen = em.merge(entregaExamenListNewEntregaExamen);
                    if (oldIdFechaexamenprogramadoOfEntregaExamenListNewEntregaExamen != null && !oldIdFechaexamenprogramadoOfEntregaExamenListNewEntregaExamen.equals(fechaExamenprogramado)) {
                        oldIdFechaexamenprogramadoOfEntregaExamenListNewEntregaExamen.getEntregaExamenList().remove(entregaExamenListNewEntregaExamen);
                        oldIdFechaexamenprogramadoOfEntregaExamenListNewEntregaExamen = em.merge(oldIdFechaexamenprogramadoOfEntregaExamenListNewEntregaExamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fechaExamenprogramado.getIdFechaExamen();
                if (findFechaExamenprogramado(id) == null) {
                    throw new NonexistentEntityException("The fechaExamenprogramado with id " + id + " no longer exists.");
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
            FechaExamenprogramado fechaExamenprogramado;
            try {
                fechaExamenprogramado = em.getReference(FechaExamenprogramado.class, id);
                fechaExamenprogramado.getIdFechaExamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechaExamenprogramado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            VigenciaCalificacion vigenciaCalificacionOrphanCheck = fechaExamenprogramado.getVigenciaCalificacion();
            if (vigenciaCalificacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FechaExamenprogramado (" + fechaExamenprogramado + ") cannot be destroyed since the VigenciaCalificacion " + vigenciaCalificacionOrphanCheck + " in its vigenciaCalificacion field has a non-nullable fechaExamenprogramado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            FechaExamen fechaExamen1 = fechaExamenprogramado.getFechaExamen1();
            if (fechaExamen1 != null) {
                fechaExamen1.setFechaExamenprogramado(null);
                fechaExamen1 = em.merge(fechaExamen1);
            }
            List<AsignacionEncuestadocente> asignacionEncuestadocenteList = fechaExamenprogramado.getAsignacionEncuestadocenteList();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocente : asignacionEncuestadocenteList) {
                asignacionEncuestadocenteListAsignacionEncuestadocente.setIdFechaexamen(null);
                asignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListAsignacionEncuestadocente);
            }
            List<AsignacionAplicador> asignacionAplicadorList = fechaExamenprogramado.getAsignacionAplicadorList();
            for (AsignacionAplicador asignacionAplicadorListAsignacionAplicador : asignacionAplicadorList) {
                asignacionAplicadorListAsignacionAplicador.setIdFechaexamenprogramado(null);
                asignacionAplicadorListAsignacionAplicador = em.merge(asignacionAplicadorListAsignacionAplicador);
            }
            List<EntregaExamen> entregaExamenList = fechaExamenprogramado.getEntregaExamenList();
            for (EntregaExamen entregaExamenListEntregaExamen : entregaExamenList) {
                entregaExamenListEntregaExamen.setIdFechaexamenprogramado(null);
                entregaExamenListEntregaExamen = em.merge(entregaExamenListEntregaExamen);
            }
            em.remove(fechaExamenprogramado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FechaExamenprogramado> findFechaExamenprogramadoEntities() {
        return findFechaExamenprogramadoEntities(true, -1, -1);
    }

    public List<FechaExamenprogramado> findFechaExamenprogramadoEntities(int maxResults, int firstResult) {
        return findFechaExamenprogramadoEntities(false, maxResults, firstResult);
    }

    private List<FechaExamenprogramado> findFechaExamenprogramadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from FechaExamenprogramado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FechaExamenprogramado findFechaExamenprogramado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FechaExamenprogramado.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechaExamenprogramadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from FechaExamenprogramado as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
