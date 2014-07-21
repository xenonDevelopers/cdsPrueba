/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoPreguntaencuesta;
import com.utez.integracion.entity.RespuestaEncuesta;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionPreguntaseccion;
import com.utez.integracion.entity.RespuestaEncuestadocente;
import com.utez.integracion.entity.AsignacionRespuestapredeterminadapregunta;
import com.utez.integracion.entity.Pregunta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PreguntaJpaController implements Serializable {

    public PreguntaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pregunta pregunta) {
        if (pregunta.getRespuestaEncuestaList() == null) {
            pregunta.setRespuestaEncuestaList(new ArrayList<RespuestaEncuesta>());
        }
        if (pregunta.getAsignacionPreguntaseccionList() == null) {
            pregunta.setAsignacionPreguntaseccionList(new ArrayList<AsignacionPreguntaseccion>());
        }
        if (pregunta.getRespuestaEncuestadocenteList() == null) {
            pregunta.setRespuestaEncuestadocenteList(new ArrayList<RespuestaEncuestadocente>());
        }
        if (pregunta.getAsignacionRespuestapredeterminadapreguntaList() == null) {
            pregunta.setAsignacionRespuestapredeterminadapreguntaList(new ArrayList<AsignacionRespuestapredeterminadapregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPreguntaencuesta idTipopregunta = pregunta.getIdTipopregunta();
            if (idTipopregunta != null) {
                idTipopregunta = em.getReference(idTipopregunta.getClass(), idTipopregunta.getIdTipopregunta());
                pregunta.setIdTipopregunta(idTipopregunta);
            }
            List<RespuestaEncuesta> attachedRespuestaEncuestaList = new ArrayList<RespuestaEncuesta>();
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuestaToAttach : pregunta.getRespuestaEncuestaList()) {
                respuestaEncuestaListRespuestaEncuestaToAttach = em.getReference(respuestaEncuestaListRespuestaEncuestaToAttach.getClass(), respuestaEncuestaListRespuestaEncuestaToAttach.getIdRespuestaencuesta());
                attachedRespuestaEncuestaList.add(respuestaEncuestaListRespuestaEncuestaToAttach);
            }
            pregunta.setRespuestaEncuestaList(attachedRespuestaEncuestaList);
            List<AsignacionPreguntaseccion> attachedAsignacionPreguntaseccionList = new ArrayList<AsignacionPreguntaseccion>();
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach : pregunta.getAsignacionPreguntaseccionList()) {
                asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach = em.getReference(asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach.getClass(), asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach.getIdAsignacionpreguntaseccion());
                attachedAsignacionPreguntaseccionList.add(asignacionPreguntaseccionListAsignacionPreguntaseccionToAttach);
            }
            pregunta.setAsignacionPreguntaseccionList(attachedAsignacionPreguntaseccionList);
            List<RespuestaEncuestadocente> attachedRespuestaEncuestadocenteList = new ArrayList<RespuestaEncuestadocente>();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach : pregunta.getRespuestaEncuestadocenteList()) {
                respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach = em.getReference(respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach.getClass(), respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach.getIdRespuestaencuestadocente());
                attachedRespuestaEncuestadocenteList.add(respuestaEncuestadocenteListRespuestaEncuestadocenteToAttach);
            }
            pregunta.setRespuestaEncuestadocenteList(attachedRespuestaEncuestadocenteList);
            List<AsignacionRespuestapredeterminadapregunta> attachedAsignacionRespuestapredeterminadapreguntaList = new ArrayList<AsignacionRespuestapredeterminadapregunta>();
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach : pregunta.getAsignacionRespuestapredeterminadapreguntaList()) {
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach = em.getReference(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach.getClass(), asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach.getIdAsignacionrespuestapredeterminadapregunta());
                attachedAsignacionRespuestapredeterminadapreguntaList.add(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapreguntaToAttach);
            }
            pregunta.setAsignacionRespuestapredeterminadapreguntaList(attachedAsignacionRespuestapredeterminadapreguntaList);
            em.persist(pregunta);
            if (idTipopregunta != null) {
                idTipopregunta.getPreguntaList().add(pregunta);
                idTipopregunta = em.merge(idTipopregunta);
            }
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuesta : pregunta.getRespuestaEncuestaList()) {
                Pregunta oldIdPreguntaOfRespuestaEncuestaListRespuestaEncuesta = respuestaEncuestaListRespuestaEncuesta.getIdPregunta();
                respuestaEncuestaListRespuestaEncuesta.setIdPregunta(pregunta);
                respuestaEncuestaListRespuestaEncuesta = em.merge(respuestaEncuestaListRespuestaEncuesta);
                if (oldIdPreguntaOfRespuestaEncuestaListRespuestaEncuesta != null) {
                    oldIdPreguntaOfRespuestaEncuestaListRespuestaEncuesta.getRespuestaEncuestaList().remove(respuestaEncuestaListRespuestaEncuesta);
                    oldIdPreguntaOfRespuestaEncuestaListRespuestaEncuesta = em.merge(oldIdPreguntaOfRespuestaEncuestaListRespuestaEncuesta);
                }
            }
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListAsignacionPreguntaseccion : pregunta.getAsignacionPreguntaseccionList()) {
                Pregunta oldIdPreguntaOfAsignacionPreguntaseccionListAsignacionPreguntaseccion = asignacionPreguntaseccionListAsignacionPreguntaseccion.getIdPregunta();
                asignacionPreguntaseccionListAsignacionPreguntaseccion.setIdPregunta(pregunta);
                asignacionPreguntaseccionListAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListAsignacionPreguntaseccion);
                if (oldIdPreguntaOfAsignacionPreguntaseccionListAsignacionPreguntaseccion != null) {
                    oldIdPreguntaOfAsignacionPreguntaseccionListAsignacionPreguntaseccion.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccionListAsignacionPreguntaseccion);
                    oldIdPreguntaOfAsignacionPreguntaseccionListAsignacionPreguntaseccion = em.merge(oldIdPreguntaOfAsignacionPreguntaseccionListAsignacionPreguntaseccion);
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocente : pregunta.getRespuestaEncuestadocenteList()) {
                Pregunta oldIdPreguntaOfRespuestaEncuestadocenteListRespuestaEncuestadocente = respuestaEncuestadocenteListRespuestaEncuestadocente.getIdPregunta();
                respuestaEncuestadocenteListRespuestaEncuestadocente.setIdPregunta(pregunta);
                respuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListRespuestaEncuestadocente);
                if (oldIdPreguntaOfRespuestaEncuestadocenteListRespuestaEncuestadocente != null) {
                    oldIdPreguntaOfRespuestaEncuestadocenteListRespuestaEncuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocenteListRespuestaEncuestadocente);
                    oldIdPreguntaOfRespuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(oldIdPreguntaOfRespuestaEncuestadocenteListRespuestaEncuestadocente);
                }
            }
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta : pregunta.getAsignacionRespuestapredeterminadapreguntaList()) {
                Pregunta oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.getIdPregunta();
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.setIdPregunta(pregunta);
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
                if (oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta != null) {
                    oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
                    oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = em.merge(oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pregunta pregunta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta persistentPregunta = em.find(Pregunta.class, pregunta.getIdPregunta());
            TipoPreguntaencuesta idTipopreguntaOld = persistentPregunta.getIdTipopregunta();
            TipoPreguntaencuesta idTipopreguntaNew = pregunta.getIdTipopregunta();
            List<RespuestaEncuesta> respuestaEncuestaListOld = persistentPregunta.getRespuestaEncuestaList();
            List<RespuestaEncuesta> respuestaEncuestaListNew = pregunta.getRespuestaEncuestaList();
            List<AsignacionPreguntaseccion> asignacionPreguntaseccionListOld = persistentPregunta.getAsignacionPreguntaseccionList();
            List<AsignacionPreguntaseccion> asignacionPreguntaseccionListNew = pregunta.getAsignacionPreguntaseccionList();
            List<RespuestaEncuestadocente> respuestaEncuestadocenteListOld = persistentPregunta.getRespuestaEncuestadocenteList();
            List<RespuestaEncuestadocente> respuestaEncuestadocenteListNew = pregunta.getRespuestaEncuestadocenteList();
            List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaListOld = persistentPregunta.getAsignacionRespuestapredeterminadapreguntaList();
            List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaListNew = pregunta.getAsignacionRespuestapredeterminadapreguntaList();
            if (idTipopreguntaNew != null) {
                idTipopreguntaNew = em.getReference(idTipopreguntaNew.getClass(), idTipopreguntaNew.getIdTipopregunta());
                pregunta.setIdTipopregunta(idTipopreguntaNew);
            }
            List<RespuestaEncuesta> attachedRespuestaEncuestaListNew = new ArrayList<RespuestaEncuesta>();
            for (RespuestaEncuesta respuestaEncuestaListNewRespuestaEncuestaToAttach : respuestaEncuestaListNew) {
                respuestaEncuestaListNewRespuestaEncuestaToAttach = em.getReference(respuestaEncuestaListNewRespuestaEncuestaToAttach.getClass(), respuestaEncuestaListNewRespuestaEncuestaToAttach.getIdRespuestaencuesta());
                attachedRespuestaEncuestaListNew.add(respuestaEncuestaListNewRespuestaEncuestaToAttach);
            }
            respuestaEncuestaListNew = attachedRespuestaEncuestaListNew;
            pregunta.setRespuestaEncuestaList(respuestaEncuestaListNew);
            List<AsignacionPreguntaseccion> attachedAsignacionPreguntaseccionListNew = new ArrayList<AsignacionPreguntaseccion>();
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach : asignacionPreguntaseccionListNew) {
                asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach = em.getReference(asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach.getClass(), asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach.getIdAsignacionpreguntaseccion());
                attachedAsignacionPreguntaseccionListNew.add(asignacionPreguntaseccionListNewAsignacionPreguntaseccionToAttach);
            }
            asignacionPreguntaseccionListNew = attachedAsignacionPreguntaseccionListNew;
            pregunta.setAsignacionPreguntaseccionList(asignacionPreguntaseccionListNew);
            List<RespuestaEncuestadocente> attachedRespuestaEncuestadocenteListNew = new ArrayList<RespuestaEncuestadocente>();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach : respuestaEncuestadocenteListNew) {
                respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach = em.getReference(respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach.getClass(), respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach.getIdRespuestaencuestadocente());
                attachedRespuestaEncuestadocenteListNew.add(respuestaEncuestadocenteListNewRespuestaEncuestadocenteToAttach);
            }
            respuestaEncuestadocenteListNew = attachedRespuestaEncuestadocenteListNew;
            pregunta.setRespuestaEncuestadocenteList(respuestaEncuestadocenteListNew);
            List<AsignacionRespuestapredeterminadapregunta> attachedAsignacionRespuestapredeterminadapreguntaListNew = new ArrayList<AsignacionRespuestapredeterminadapregunta>();
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach : asignacionRespuestapredeterminadapreguntaListNew) {
                asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach = em.getReference(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach.getClass(), asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach.getIdAsignacionrespuestapredeterminadapregunta());
                attachedAsignacionRespuestapredeterminadapreguntaListNew.add(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapreguntaToAttach);
            }
            asignacionRespuestapredeterminadapreguntaListNew = attachedAsignacionRespuestapredeterminadapreguntaListNew;
            pregunta.setAsignacionRespuestapredeterminadapreguntaList(asignacionRespuestapredeterminadapreguntaListNew);
            pregunta = em.merge(pregunta);
            if (idTipopreguntaOld != null && !idTipopreguntaOld.equals(idTipopreguntaNew)) {
                idTipopreguntaOld.getPreguntaList().remove(pregunta);
                idTipopreguntaOld = em.merge(idTipopreguntaOld);
            }
            if (idTipopreguntaNew != null && !idTipopreguntaNew.equals(idTipopreguntaOld)) {
                idTipopreguntaNew.getPreguntaList().add(pregunta);
                idTipopreguntaNew = em.merge(idTipopreguntaNew);
            }
            for (RespuestaEncuesta respuestaEncuestaListOldRespuestaEncuesta : respuestaEncuestaListOld) {
                if (!respuestaEncuestaListNew.contains(respuestaEncuestaListOldRespuestaEncuesta)) {
                    respuestaEncuestaListOldRespuestaEncuesta.setIdPregunta(null);
                    respuestaEncuestaListOldRespuestaEncuesta = em.merge(respuestaEncuestaListOldRespuestaEncuesta);
                }
            }
            for (RespuestaEncuesta respuestaEncuestaListNewRespuestaEncuesta : respuestaEncuestaListNew) {
                if (!respuestaEncuestaListOld.contains(respuestaEncuestaListNewRespuestaEncuesta)) {
                    Pregunta oldIdPreguntaOfRespuestaEncuestaListNewRespuestaEncuesta = respuestaEncuestaListNewRespuestaEncuesta.getIdPregunta();
                    respuestaEncuestaListNewRespuestaEncuesta.setIdPregunta(pregunta);
                    respuestaEncuestaListNewRespuestaEncuesta = em.merge(respuestaEncuestaListNewRespuestaEncuesta);
                    if (oldIdPreguntaOfRespuestaEncuestaListNewRespuestaEncuesta != null && !oldIdPreguntaOfRespuestaEncuestaListNewRespuestaEncuesta.equals(pregunta)) {
                        oldIdPreguntaOfRespuestaEncuestaListNewRespuestaEncuesta.getRespuestaEncuestaList().remove(respuestaEncuestaListNewRespuestaEncuesta);
                        oldIdPreguntaOfRespuestaEncuestaListNewRespuestaEncuesta = em.merge(oldIdPreguntaOfRespuestaEncuestaListNewRespuestaEncuesta);
                    }
                }
            }
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListOldAsignacionPreguntaseccion : asignacionPreguntaseccionListOld) {
                if (!asignacionPreguntaseccionListNew.contains(asignacionPreguntaseccionListOldAsignacionPreguntaseccion)) {
                    asignacionPreguntaseccionListOldAsignacionPreguntaseccion.setIdPregunta(null);
                    asignacionPreguntaseccionListOldAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListOldAsignacionPreguntaseccion);
                }
            }
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListNewAsignacionPreguntaseccion : asignacionPreguntaseccionListNew) {
                if (!asignacionPreguntaseccionListOld.contains(asignacionPreguntaseccionListNewAsignacionPreguntaseccion)) {
                    Pregunta oldIdPreguntaOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion = asignacionPreguntaseccionListNewAsignacionPreguntaseccion.getIdPregunta();
                    asignacionPreguntaseccionListNewAsignacionPreguntaseccion.setIdPregunta(pregunta);
                    asignacionPreguntaseccionListNewAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListNewAsignacionPreguntaseccion);
                    if (oldIdPreguntaOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion != null && !oldIdPreguntaOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion.equals(pregunta)) {
                        oldIdPreguntaOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion.getAsignacionPreguntaseccionList().remove(asignacionPreguntaseccionListNewAsignacionPreguntaseccion);
                        oldIdPreguntaOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion = em.merge(oldIdPreguntaOfAsignacionPreguntaseccionListNewAsignacionPreguntaseccion);
                    }
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListOldRespuestaEncuestadocente : respuestaEncuestadocenteListOld) {
                if (!respuestaEncuestadocenteListNew.contains(respuestaEncuestadocenteListOldRespuestaEncuestadocente)) {
                    respuestaEncuestadocenteListOldRespuestaEncuestadocente.setIdPregunta(null);
                    respuestaEncuestadocenteListOldRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListOldRespuestaEncuestadocente);
                }
            }
            for (RespuestaEncuestadocente respuestaEncuestadocenteListNewRespuestaEncuestadocente : respuestaEncuestadocenteListNew) {
                if (!respuestaEncuestadocenteListOld.contains(respuestaEncuestadocenteListNewRespuestaEncuestadocente)) {
                    Pregunta oldIdPreguntaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente = respuestaEncuestadocenteListNewRespuestaEncuestadocente.getIdPregunta();
                    respuestaEncuestadocenteListNewRespuestaEncuestadocente.setIdPregunta(pregunta);
                    respuestaEncuestadocenteListNewRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListNewRespuestaEncuestadocente);
                    if (oldIdPreguntaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente != null && !oldIdPreguntaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente.equals(pregunta)) {
                        oldIdPreguntaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente.getRespuestaEncuestadocenteList().remove(respuestaEncuestadocenteListNewRespuestaEncuestadocente);
                        oldIdPreguntaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente = em.merge(oldIdPreguntaOfRespuestaEncuestadocenteListNewRespuestaEncuestadocente);
                    }
                }
            }
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta : asignacionRespuestapredeterminadapreguntaListOld) {
                if (!asignacionRespuestapredeterminadapreguntaListNew.contains(asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta)) {
                    asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta.setIdPregunta(null);
                    asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListOldAsignacionRespuestapredeterminadapregunta);
                }
            }
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta : asignacionRespuestapredeterminadapreguntaListNew) {
                if (!asignacionRespuestapredeterminadapreguntaListOld.contains(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta)) {
                    Pregunta oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta = asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.getIdPregunta();
                    asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.setIdPregunta(pregunta);
                    asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta);
                    if (oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta != null && !oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.equals(pregunta)) {
                        oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta.getAsignacionRespuestapredeterminadapreguntaList().remove(asignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta);
                        oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta = em.merge(oldIdPreguntaOfAsignacionRespuestapredeterminadapreguntaListNewAsignacionRespuestapredeterminadapregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pregunta.getIdPregunta();
                if (findPregunta(id) == null) {
                    throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.");
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
            Pregunta pregunta;
            try {
                pregunta = em.getReference(Pregunta.class, id);
                pregunta.getIdPregunta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.", enfe);
            }
            TipoPreguntaencuesta idTipopregunta = pregunta.getIdTipopregunta();
            if (idTipopregunta != null) {
                idTipopregunta.getPreguntaList().remove(pregunta);
                idTipopregunta = em.merge(idTipopregunta);
            }
            List<RespuestaEncuesta> respuestaEncuestaList = pregunta.getRespuestaEncuestaList();
            for (RespuestaEncuesta respuestaEncuestaListRespuestaEncuesta : respuestaEncuestaList) {
                respuestaEncuestaListRespuestaEncuesta.setIdPregunta(null);
                respuestaEncuestaListRespuestaEncuesta = em.merge(respuestaEncuestaListRespuestaEncuesta);
            }
            List<AsignacionPreguntaseccion> asignacionPreguntaseccionList = pregunta.getAsignacionPreguntaseccionList();
            for (AsignacionPreguntaseccion asignacionPreguntaseccionListAsignacionPreguntaseccion : asignacionPreguntaseccionList) {
                asignacionPreguntaseccionListAsignacionPreguntaseccion.setIdPregunta(null);
                asignacionPreguntaseccionListAsignacionPreguntaseccion = em.merge(asignacionPreguntaseccionListAsignacionPreguntaseccion);
            }
            List<RespuestaEncuestadocente> respuestaEncuestadocenteList = pregunta.getRespuestaEncuestadocenteList();
            for (RespuestaEncuestadocente respuestaEncuestadocenteListRespuestaEncuestadocente : respuestaEncuestadocenteList) {
                respuestaEncuestadocenteListRespuestaEncuestadocente.setIdPregunta(null);
                respuestaEncuestadocenteListRespuestaEncuestadocente = em.merge(respuestaEncuestadocenteListRespuestaEncuestadocente);
            }
            List<AsignacionRespuestapredeterminadapregunta> asignacionRespuestapredeterminadapreguntaList = pregunta.getAsignacionRespuestapredeterminadapreguntaList();
            for (AsignacionRespuestapredeterminadapregunta asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta : asignacionRespuestapredeterminadapreguntaList) {
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta.setIdPregunta(null);
                asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta = em.merge(asignacionRespuestapredeterminadapreguntaListAsignacionRespuestapredeterminadapregunta);
            }
            em.remove(pregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pregunta> findPreguntaEntities() {
        return findPreguntaEntities(true, -1, -1);
    }

    public List<Pregunta> findPreguntaEntities(int maxResults, int firstResult) {
        return findPreguntaEntities(false, maxResults, firstResult);
    }

    private List<Pregunta> findPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pregunta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pregunta findPregunta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Pregunta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
