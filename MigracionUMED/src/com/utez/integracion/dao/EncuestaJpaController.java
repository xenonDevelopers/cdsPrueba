/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoEncuesta;
import com.utez.integracion.entity.AsignacionEncuestadocente;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionSeccionencuesta;
import com.utez.integracion.entity.AsignacionEncuestaalumno;
import com.utez.integracion.entity.AsignacionGrupoencuesta;
import com.utez.integracion.entity.Encuesta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EncuestaJpaController implements Serializable {

    public EncuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encuesta encuesta) {
        if (encuesta.getAsignacionEncuestadocenteList() == null) {
            encuesta.setAsignacionEncuestadocenteList(new ArrayList<AsignacionEncuestadocente>());
        }
        if (encuesta.getAsignacionSeccionencuestaList() == null) {
            encuesta.setAsignacionSeccionencuestaList(new ArrayList<AsignacionSeccionencuesta>());
        }
        if (encuesta.getAsignacionEncuestaalumnoList() == null) {
            encuesta.setAsignacionEncuestaalumnoList(new ArrayList<AsignacionEncuestaalumno>());
        }
        if (encuesta.getAsignacionGrupoencuestaList() == null) {
            encuesta.setAsignacionGrupoencuestaList(new ArrayList<AsignacionGrupoencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEncuesta idTipoencuesta = encuesta.getIdTipoencuesta();
            if (idTipoencuesta != null) {
                idTipoencuesta = em.getReference(idTipoencuesta.getClass(), idTipoencuesta.getIdTipoencuesta());
                encuesta.setIdTipoencuesta(idTipoencuesta);
            }
            List<AsignacionEncuestadocente> attachedAsignacionEncuestadocenteList = new ArrayList<AsignacionEncuestadocente>();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach : encuesta.getAsignacionEncuestadocenteList()) {
                asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach = em.getReference(asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach.getClass(), asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach.getIdAsignacionencuestadocente());
                attachedAsignacionEncuestadocenteList.add(asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach);
            }
            encuesta.setAsignacionEncuestadocenteList(attachedAsignacionEncuestadocenteList);
            List<AsignacionSeccionencuesta> attachedAsignacionSeccionencuestaList = new ArrayList<AsignacionSeccionencuesta>();
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach : encuesta.getAsignacionSeccionencuestaList()) {
                asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach = em.getReference(asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach.getClass(), asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach.getIdAsignacionseccionencuesta());
                attachedAsignacionSeccionencuestaList.add(asignacionSeccionencuestaListAsignacionSeccionencuestaToAttach);
            }
            encuesta.setAsignacionSeccionencuestaList(attachedAsignacionSeccionencuestaList);
            List<AsignacionEncuestaalumno> attachedAsignacionEncuestaalumnoList = new ArrayList<AsignacionEncuestaalumno>();
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach : encuesta.getAsignacionEncuestaalumnoList()) {
                asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach = em.getReference(asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach.getClass(), asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach.getIdAsignacionencuestaalumno());
                attachedAsignacionEncuestaalumnoList.add(asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach);
            }
            encuesta.setAsignacionEncuestaalumnoList(attachedAsignacionEncuestaalumnoList);
            List<AsignacionGrupoencuesta> attachedAsignacionGrupoencuestaList = new ArrayList<AsignacionGrupoencuesta>();
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach : encuesta.getAsignacionGrupoencuestaList()) {
                asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach = em.getReference(asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach.getClass(), asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach.getIdAsignaciongrupoencuesta());
                attachedAsignacionGrupoencuestaList.add(asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach);
            }
            encuesta.setAsignacionGrupoencuestaList(attachedAsignacionGrupoencuestaList);
            em.persist(encuesta);
            if (idTipoencuesta != null) {
                idTipoencuesta.getEncuestaList().add(encuesta);
                idTipoencuesta = em.merge(idTipoencuesta);
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocente : encuesta.getAsignacionEncuestadocenteList()) {
                Encuesta oldIdEncuestaOfAsignacionEncuestadocenteListAsignacionEncuestadocente = asignacionEncuestadocenteListAsignacionEncuestadocente.getIdEncuesta();
                asignacionEncuestadocenteListAsignacionEncuestadocente.setIdEncuesta(encuesta);
                asignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListAsignacionEncuestadocente);
                if (oldIdEncuestaOfAsignacionEncuestadocenteListAsignacionEncuestadocente != null) {
                    oldIdEncuestaOfAsignacionEncuestadocenteListAsignacionEncuestadocente.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocenteListAsignacionEncuestadocente);
                    oldIdEncuestaOfAsignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(oldIdEncuestaOfAsignacionEncuestadocenteListAsignacionEncuestadocente);
                }
            }
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListAsignacionSeccionencuesta : encuesta.getAsignacionSeccionencuestaList()) {
                Encuesta oldIdEncuestaOfAsignacionSeccionencuestaListAsignacionSeccionencuesta = asignacionSeccionencuestaListAsignacionSeccionencuesta.getIdEncuesta();
                asignacionSeccionencuestaListAsignacionSeccionencuesta.setIdEncuesta(encuesta);
                asignacionSeccionencuestaListAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListAsignacionSeccionencuesta);
                if (oldIdEncuestaOfAsignacionSeccionencuestaListAsignacionSeccionencuesta != null) {
                    oldIdEncuestaOfAsignacionSeccionencuestaListAsignacionSeccionencuesta.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuestaListAsignacionSeccionencuesta);
                    oldIdEncuestaOfAsignacionSeccionencuestaListAsignacionSeccionencuesta = em.merge(oldIdEncuestaOfAsignacionSeccionencuestaListAsignacionSeccionencuesta);
                }
            }
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListAsignacionEncuestaalumno : encuesta.getAsignacionEncuestaalumnoList()) {
                Encuesta oldIdEncuestaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno = asignacionEncuestaalumnoListAsignacionEncuestaalumno.getIdEncuesta();
                asignacionEncuestaalumnoListAsignacionEncuestaalumno.setIdEncuesta(encuesta);
                asignacionEncuestaalumnoListAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListAsignacionEncuestaalumno);
                if (oldIdEncuestaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno != null) {
                    oldIdEncuestaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumnoListAsignacionEncuestaalumno);
                    oldIdEncuestaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno = em.merge(oldIdEncuestaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno);
                }
            }
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListAsignacionGrupoencuesta : encuesta.getAsignacionGrupoencuestaList()) {
                Encuesta oldIdEncuestaOfAsignacionGrupoencuestaListAsignacionGrupoencuesta = asignacionGrupoencuestaListAsignacionGrupoencuesta.getIdEncuesta();
                asignacionGrupoencuestaListAsignacionGrupoencuesta.setIdEncuesta(encuesta);
                asignacionGrupoencuestaListAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListAsignacionGrupoencuesta);
                if (oldIdEncuestaOfAsignacionGrupoencuestaListAsignacionGrupoencuesta != null) {
                    oldIdEncuestaOfAsignacionGrupoencuestaListAsignacionGrupoencuesta.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuestaListAsignacionGrupoencuesta);
                    oldIdEncuestaOfAsignacionGrupoencuestaListAsignacionGrupoencuesta = em.merge(oldIdEncuestaOfAsignacionGrupoencuestaListAsignacionGrupoencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encuesta encuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encuesta persistentEncuesta = em.find(Encuesta.class, encuesta.getIdEncuesta());
            TipoEncuesta idTipoencuestaOld = persistentEncuesta.getIdTipoencuesta();
            TipoEncuesta idTipoencuestaNew = encuesta.getIdTipoencuesta();
            List<AsignacionEncuestadocente> asignacionEncuestadocenteListOld = persistentEncuesta.getAsignacionEncuestadocenteList();
            List<AsignacionEncuestadocente> asignacionEncuestadocenteListNew = encuesta.getAsignacionEncuestadocenteList();
            List<AsignacionSeccionencuesta> asignacionSeccionencuestaListOld = persistentEncuesta.getAsignacionSeccionencuestaList();
            List<AsignacionSeccionencuesta> asignacionSeccionencuestaListNew = encuesta.getAsignacionSeccionencuestaList();
            List<AsignacionEncuestaalumno> asignacionEncuestaalumnoListOld = persistentEncuesta.getAsignacionEncuestaalumnoList();
            List<AsignacionEncuestaalumno> asignacionEncuestaalumnoListNew = encuesta.getAsignacionEncuestaalumnoList();
            List<AsignacionGrupoencuesta> asignacionGrupoencuestaListOld = persistentEncuesta.getAsignacionGrupoencuestaList();
            List<AsignacionGrupoencuesta> asignacionGrupoencuestaListNew = encuesta.getAsignacionGrupoencuestaList();
            if (idTipoencuestaNew != null) {
                idTipoencuestaNew = em.getReference(idTipoencuestaNew.getClass(), idTipoencuestaNew.getIdTipoencuesta());
                encuesta.setIdTipoencuesta(idTipoencuestaNew);
            }
            List<AsignacionEncuestadocente> attachedAsignacionEncuestadocenteListNew = new ArrayList<AsignacionEncuestadocente>();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach : asignacionEncuestadocenteListNew) {
                asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach = em.getReference(asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach.getClass(), asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach.getIdAsignacionencuestadocente());
                attachedAsignacionEncuestadocenteListNew.add(asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach);
            }
            asignacionEncuestadocenteListNew = attachedAsignacionEncuestadocenteListNew;
            encuesta.setAsignacionEncuestadocenteList(asignacionEncuestadocenteListNew);
            List<AsignacionSeccionencuesta> attachedAsignacionSeccionencuestaListNew = new ArrayList<AsignacionSeccionencuesta>();
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach : asignacionSeccionencuestaListNew) {
                asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach = em.getReference(asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach.getClass(), asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach.getIdAsignacionseccionencuesta());
                attachedAsignacionSeccionencuestaListNew.add(asignacionSeccionencuestaListNewAsignacionSeccionencuestaToAttach);
            }
            asignacionSeccionencuestaListNew = attachedAsignacionSeccionencuestaListNew;
            encuesta.setAsignacionSeccionencuestaList(asignacionSeccionencuestaListNew);
            List<AsignacionEncuestaalumno> attachedAsignacionEncuestaalumnoListNew = new ArrayList<AsignacionEncuestaalumno>();
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach : asignacionEncuestaalumnoListNew) {
                asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach = em.getReference(asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach.getClass(), asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach.getIdAsignacionencuestaalumno());
                attachedAsignacionEncuestaalumnoListNew.add(asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach);
            }
            asignacionEncuestaalumnoListNew = attachedAsignacionEncuestaalumnoListNew;
            encuesta.setAsignacionEncuestaalumnoList(asignacionEncuestaalumnoListNew);
            List<AsignacionGrupoencuesta> attachedAsignacionGrupoencuestaListNew = new ArrayList<AsignacionGrupoencuesta>();
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach : asignacionGrupoencuestaListNew) {
                asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach = em.getReference(asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach.getClass(), asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach.getIdAsignaciongrupoencuesta());
                attachedAsignacionGrupoencuestaListNew.add(asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach);
            }
            asignacionGrupoencuestaListNew = attachedAsignacionGrupoencuestaListNew;
            encuesta.setAsignacionGrupoencuestaList(asignacionGrupoencuestaListNew);
            encuesta = em.merge(encuesta);
            if (idTipoencuestaOld != null && !idTipoencuestaOld.equals(idTipoencuestaNew)) {
                idTipoencuestaOld.getEncuestaList().remove(encuesta);
                idTipoencuestaOld = em.merge(idTipoencuestaOld);
            }
            if (idTipoencuestaNew != null && !idTipoencuestaNew.equals(idTipoencuestaOld)) {
                idTipoencuestaNew.getEncuestaList().add(encuesta);
                idTipoencuestaNew = em.merge(idTipoencuestaNew);
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListOldAsignacionEncuestadocente : asignacionEncuestadocenteListOld) {
                if (!asignacionEncuestadocenteListNew.contains(asignacionEncuestadocenteListOldAsignacionEncuestadocente)) {
                    asignacionEncuestadocenteListOldAsignacionEncuestadocente.setIdEncuesta(null);
                    asignacionEncuestadocenteListOldAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListOldAsignacionEncuestadocente);
                }
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListNewAsignacionEncuestadocente : asignacionEncuestadocenteListNew) {
                if (!asignacionEncuestadocenteListOld.contains(asignacionEncuestadocenteListNewAsignacionEncuestadocente)) {
                    Encuesta oldIdEncuestaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente = asignacionEncuestadocenteListNewAsignacionEncuestadocente.getIdEncuesta();
                    asignacionEncuestadocenteListNewAsignacionEncuestadocente.setIdEncuesta(encuesta);
                    asignacionEncuestadocenteListNewAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListNewAsignacionEncuestadocente);
                    if (oldIdEncuestaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente != null && !oldIdEncuestaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente.equals(encuesta)) {
                        oldIdEncuestaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocenteListNewAsignacionEncuestadocente);
                        oldIdEncuestaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente = em.merge(oldIdEncuestaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente);
                    }
                }
            }
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListOldAsignacionSeccionencuesta : asignacionSeccionencuestaListOld) {
                if (!asignacionSeccionencuestaListNew.contains(asignacionSeccionencuestaListOldAsignacionSeccionencuesta)) {
                    asignacionSeccionencuestaListOldAsignacionSeccionencuesta.setIdEncuesta(null);
                    asignacionSeccionencuestaListOldAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListOldAsignacionSeccionencuesta);
                }
            }
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListNewAsignacionSeccionencuesta : asignacionSeccionencuestaListNew) {
                if (!asignacionSeccionencuestaListOld.contains(asignacionSeccionencuestaListNewAsignacionSeccionencuesta)) {
                    Encuesta oldIdEncuestaOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta = asignacionSeccionencuestaListNewAsignacionSeccionencuesta.getIdEncuesta();
                    asignacionSeccionencuestaListNewAsignacionSeccionencuesta.setIdEncuesta(encuesta);
                    asignacionSeccionencuestaListNewAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListNewAsignacionSeccionencuesta);
                    if (oldIdEncuestaOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta != null && !oldIdEncuestaOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta.equals(encuesta)) {
                        oldIdEncuestaOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta.getAsignacionSeccionencuestaList().remove(asignacionSeccionencuestaListNewAsignacionSeccionencuesta);
                        oldIdEncuestaOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta = em.merge(oldIdEncuestaOfAsignacionSeccionencuestaListNewAsignacionSeccionencuesta);
                    }
                }
            }
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListOldAsignacionEncuestaalumno : asignacionEncuestaalumnoListOld) {
                if (!asignacionEncuestaalumnoListNew.contains(asignacionEncuestaalumnoListOldAsignacionEncuestaalumno)) {
                    asignacionEncuestaalumnoListOldAsignacionEncuestaalumno.setIdEncuesta(null);
                    asignacionEncuestaalumnoListOldAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListOldAsignacionEncuestaalumno);
                }
            }
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListNewAsignacionEncuestaalumno : asignacionEncuestaalumnoListNew) {
                if (!asignacionEncuestaalumnoListOld.contains(asignacionEncuestaalumnoListNewAsignacionEncuestaalumno)) {
                    Encuesta oldIdEncuestaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno = asignacionEncuestaalumnoListNewAsignacionEncuestaalumno.getIdEncuesta();
                    asignacionEncuestaalumnoListNewAsignacionEncuestaalumno.setIdEncuesta(encuesta);
                    asignacionEncuestaalumnoListNewAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListNewAsignacionEncuestaalumno);
                    if (oldIdEncuestaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno != null && !oldIdEncuestaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno.equals(encuesta)) {
                        oldIdEncuestaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumnoListNewAsignacionEncuestaalumno);
                        oldIdEncuestaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno = em.merge(oldIdEncuestaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno);
                    }
                }
            }
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListOldAsignacionGrupoencuesta : asignacionGrupoencuestaListOld) {
                if (!asignacionGrupoencuestaListNew.contains(asignacionGrupoencuestaListOldAsignacionGrupoencuesta)) {
                    asignacionGrupoencuestaListOldAsignacionGrupoencuesta.setIdEncuesta(null);
                    asignacionGrupoencuestaListOldAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListOldAsignacionGrupoencuesta);
                }
            }
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListNewAsignacionGrupoencuesta : asignacionGrupoencuestaListNew) {
                if (!asignacionGrupoencuestaListOld.contains(asignacionGrupoencuestaListNewAsignacionGrupoencuesta)) {
                    Encuesta oldIdEncuestaOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta = asignacionGrupoencuestaListNewAsignacionGrupoencuesta.getIdEncuesta();
                    asignacionGrupoencuestaListNewAsignacionGrupoencuesta.setIdEncuesta(encuesta);
                    asignacionGrupoencuestaListNewAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListNewAsignacionGrupoencuesta);
                    if (oldIdEncuestaOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta != null && !oldIdEncuestaOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta.equals(encuesta)) {
                        oldIdEncuestaOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuestaListNewAsignacionGrupoencuesta);
                        oldIdEncuestaOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta = em.merge(oldIdEncuestaOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = encuesta.getIdEncuesta();
                if (findEncuesta(id) == null) {
                    throw new NonexistentEntityException("The encuesta with id " + id + " no longer exists.");
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
            Encuesta encuesta;
            try {
                encuesta = em.getReference(Encuesta.class, id);
                encuesta.getIdEncuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encuesta with id " + id + " no longer exists.", enfe);
            }
            TipoEncuesta idTipoencuesta = encuesta.getIdTipoencuesta();
            if (idTipoencuesta != null) {
                idTipoencuesta.getEncuestaList().remove(encuesta);
                idTipoencuesta = em.merge(idTipoencuesta);
            }
            List<AsignacionEncuestadocente> asignacionEncuestadocenteList = encuesta.getAsignacionEncuestadocenteList();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocente : asignacionEncuestadocenteList) {
                asignacionEncuestadocenteListAsignacionEncuestadocente.setIdEncuesta(null);
                asignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListAsignacionEncuestadocente);
            }
            List<AsignacionSeccionencuesta> asignacionSeccionencuestaList = encuesta.getAsignacionSeccionencuestaList();
            for (AsignacionSeccionencuesta asignacionSeccionencuestaListAsignacionSeccionencuesta : asignacionSeccionencuestaList) {
                asignacionSeccionencuestaListAsignacionSeccionencuesta.setIdEncuesta(null);
                asignacionSeccionencuestaListAsignacionSeccionencuesta = em.merge(asignacionSeccionencuestaListAsignacionSeccionencuesta);
            }
            List<AsignacionEncuestaalumno> asignacionEncuestaalumnoList = encuesta.getAsignacionEncuestaalumnoList();
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListAsignacionEncuestaalumno : asignacionEncuestaalumnoList) {
                asignacionEncuestaalumnoListAsignacionEncuestaalumno.setIdEncuesta(null);
                asignacionEncuestaalumnoListAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListAsignacionEncuestaalumno);
            }
            List<AsignacionGrupoencuesta> asignacionGrupoencuestaList = encuesta.getAsignacionGrupoencuestaList();
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListAsignacionGrupoencuesta : asignacionGrupoencuestaList) {
                asignacionGrupoencuestaListAsignacionGrupoencuesta.setIdEncuesta(null);
                asignacionGrupoencuestaListAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListAsignacionGrupoencuesta);
            }
            em.remove(encuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encuesta> findEncuestaEntities() {
        return findEncuestaEntities(true, -1, -1);
    }

    public List<Encuesta> findEncuestaEntities(int maxResults, int firstResult) {
        return findEncuestaEntities(false, maxResults, firstResult);
    }

    private List<Encuesta> findEncuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Encuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Encuesta findEncuesta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Encuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
