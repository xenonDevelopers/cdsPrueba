/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.MovimientoAdeudo;
import com.utez.integracion.entity.TipoAlumno;
import com.utez.integracion.entity.Aspirante;
import com.utez.secretariaAcademica.entity.Adeudo;
import java.util.ArrayList;
import java.util.List;
import com.utez.secretariaAcademica.entity.Movimientocie;
import com.utez.integracion.entity.Convenio;
import com.utez.integracion.entity.AsignacionEncuestadocente;
import com.utez.integracion.entity.AlumnoAsignatura;
import com.utez.integracion.entity.AsignacionGrupoinduccion;
import com.utez.integracion.entity.ExamenExtemporaneo;
import com.utez.integracion.entity.SolicitudBaja;
import com.utez.integracion.entity.ResultadoEvaluacion;
import com.utez.integracion.entity.AsignacionEncuestaalumno;
import com.utez.integracion.entity.SeguimientoAlumno;
import com.utez.integracion.entity.MovimientoCie;
import com.utez.integracion.entity.AsignacionGrupoexamenextemporaneo;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.ServicioSocial;
import com.utez.integracion.entity.Factura;
import com.utez.integracion.entity.InscripcionEquivalencia;
import com.utez.integracion.entity.AsignacionGrupo;
import com.utez.integracion.entity.ControlDocumento;
import com.utez.integracion.entity.CompromisoDocumentacion;
import com.utez.integracion.entity.SolicitudProgramacion;
import com.utez.secretariaAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secretariaAcademica.dao.exceptions.PreexistingEntityException;
import com.utez.secretariaAcademica.entity.Alumno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) throws PreexistingEntityException, Exception {
        if (alumno.getAdeudoList() == null) {
            alumno.setAdeudoList(new ArrayList<Adeudo>());
        }
        if (alumno.getMovimientocieList() == null) {
            alumno.setMovimientocieList(new ArrayList<Movimientocie>());
        }
        if (alumno.getConvenioList() == null) {
            alumno.setConvenioList(new ArrayList<Convenio>());
        }
        if (alumno.getAsignacionEncuestadocenteList() == null) {
            alumno.setAsignacionEncuestadocenteList(new ArrayList<AsignacionEncuestadocente>());
        }
        if (alumno.getAlumnoAsignaturaList() == null) {
            alumno.setAlumnoAsignaturaList(new ArrayList<AlumnoAsignatura>());
        }
        if (alumno.getAsignacionGrupoinduccionList() == null) {
            alumno.setAsignacionGrupoinduccionList(new ArrayList<AsignacionGrupoinduccion>());
        }
        if (alumno.getExamenExtemporaneoList() == null) {
            alumno.setExamenExtemporaneoList(new ArrayList<ExamenExtemporaneo>());
        }
        if (alumno.getSolicitudBajaList() == null) {
            alumno.setSolicitudBajaList(new ArrayList<SolicitudBaja>());
        }
        if (alumno.getResultadoEvaluacionList() == null) {
            alumno.setResultadoEvaluacionList(new ArrayList<ResultadoEvaluacion>());
        }
        if (alumno.getAsignacionEncuestaalumnoList() == null) {
            alumno.setAsignacionEncuestaalumnoList(new ArrayList<AsignacionEncuestaalumno>());
        }
        if (alumno.getSeguimientoAlumnoList() == null) {
            alumno.setSeguimientoAlumnoList(new ArrayList<SeguimientoAlumno>());
        }
        if (alumno.getMovimientoCieList() == null) {
            alumno.setMovimientoCieList(new ArrayList<MovimientoCie>());
        }
        if (alumno.getAsignacionGrupoexamenextemporaneoList() == null) {
            alumno.setAsignacionGrupoexamenextemporaneoList(new ArrayList<AsignacionGrupoexamenextemporaneo>());
        }
        if (alumno.getSolicitudTitulacionList() == null) {
            alumno.setSolicitudTitulacionList(new ArrayList<SolicitudTitulacion>());
        }
        if (alumno.getServicioSocialList() == null) {
            alumno.setServicioSocialList(new ArrayList<ServicioSocial>());
        }
        if (alumno.getFacturaList() == null) {
            alumno.setFacturaList(new ArrayList<Factura>());
        }
        if (alumno.getInscripcionEquivalenciaList() == null) {
            alumno.setInscripcionEquivalenciaList(new ArrayList<InscripcionEquivalencia>());
        }
        if (alumno.getAsignacionGrupoList() == null) {
            alumno.setAsignacionGrupoList(new ArrayList<AsignacionGrupo>());
        }
        if (alumno.getControlDocumentoList() == null) {
            alumno.setControlDocumentoList(new ArrayList<ControlDocumento>());
        }
        if (alumno.getCompromisoDocumentacionList() == null) {
            alumno.setCompromisoDocumentacionList(new ArrayList<CompromisoDocumentacion>());
        }
        if (alumno.getSolicitudProgramacionList() == null) {
            alumno.setSolicitudProgramacionList(new ArrayList<SolicitudProgramacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MovimientoAdeudo movimientoAdeudo = alumno.getMovimientoAdeudo();
            if (movimientoAdeudo != null) {
                movimientoAdeudo = em.getReference(movimientoAdeudo.getClass(), movimientoAdeudo.getMatricula());
                alumno.setMovimientoAdeudo(movimientoAdeudo);
            }
            TipoAlumno idTipoalumno = alumno.getIdTipoalumno();
            if (idTipoalumno != null) {
                idTipoalumno = em.getReference(idTipoalumno.getClass(), idTipoalumno.getIdTipoalumno());
                alumno.setIdTipoalumno(idTipoalumno);
            }
            Aspirante idAspirante = alumno.getIdAspirante();
            if (idAspirante != null) {
                idAspirante = em.getReference(idAspirante.getClass(), idAspirante.getIdAspirante());
                alumno.setIdAspirante(idAspirante);
            }
            List<Adeudo> attachedAdeudoList = new ArrayList<Adeudo>();
            for (Adeudo adeudoListAdeudoToAttach : alumno.getAdeudoList()) {
                adeudoListAdeudoToAttach = em.getReference(adeudoListAdeudoToAttach.getClass(), adeudoListAdeudoToAttach.getIdadeudo());
                attachedAdeudoList.add(adeudoListAdeudoToAttach);
            }
            alumno.setAdeudoList(attachedAdeudoList);
            List<Movimientocie> attachedMovimientocieList = new ArrayList<Movimientocie>();
            for (Movimientocie movimientocieListMovimientocieToAttach : alumno.getMovimientocieList()) {
                movimientocieListMovimientocieToAttach = em.getReference(movimientocieListMovimientocieToAttach.getClass(), movimientocieListMovimientocieToAttach.getIdmovimientocie());
                attachedMovimientocieList.add(movimientocieListMovimientocieToAttach);
            }
            alumno.setMovimientocieList(attachedMovimientocieList);
            List<Convenio> attachedConvenioList = new ArrayList<Convenio>();
            for (Convenio convenioListConvenioToAttach : alumno.getConvenioList()) {
                convenioListConvenioToAttach = em.getReference(convenioListConvenioToAttach.getClass(), convenioListConvenioToAttach.getIdConvenio());
                attachedConvenioList.add(convenioListConvenioToAttach);
            }
            alumno.setConvenioList(attachedConvenioList);
            List<AsignacionEncuestadocente> attachedAsignacionEncuestadocenteList = new ArrayList<AsignacionEncuestadocente>();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach : alumno.getAsignacionEncuestadocenteList()) {
                asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach = em.getReference(asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach.getClass(), asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach.getIdAsignacionencuestadocente());
                attachedAsignacionEncuestadocenteList.add(asignacionEncuestadocenteListAsignacionEncuestadocenteToAttach);
            }
            alumno.setAsignacionEncuestadocenteList(attachedAsignacionEncuestadocenteList);
            List<AlumnoAsignatura> attachedAlumnoAsignaturaList = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignaturaToAttach : alumno.getAlumnoAsignaturaList()) {
                alumnoAsignaturaListAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListAlumnoAsignaturaToAttach.getIdAlumnoasignatura());
                attachedAlumnoAsignaturaList.add(alumnoAsignaturaListAlumnoAsignaturaToAttach);
            }
            alumno.setAlumnoAsignaturaList(attachedAlumnoAsignaturaList);
            List<AsignacionGrupoinduccion> attachedAsignacionGrupoinduccionList = new ArrayList<AsignacionGrupoinduccion>();
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach : alumno.getAsignacionGrupoinduccionList()) {
                asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach = em.getReference(asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach.getClass(), asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach.getIdAsignacionGrupoinduccion());
                attachedAsignacionGrupoinduccionList.add(asignacionGrupoinduccionListAsignacionGrupoinduccionToAttach);
            }
            alumno.setAsignacionGrupoinduccionList(attachedAsignacionGrupoinduccionList);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoList = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneoToAttach : alumno.getExamenExtemporaneoList()) {
                examenExtemporaneoListExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoList.add(examenExtemporaneoListExamenExtemporaneoToAttach);
            }
            alumno.setExamenExtemporaneoList(attachedExamenExtemporaneoList);
            List<SolicitudBaja> attachedSolicitudBajaList = new ArrayList<SolicitudBaja>();
            for (SolicitudBaja solicitudBajaListSolicitudBajaToAttach : alumno.getSolicitudBajaList()) {
                solicitudBajaListSolicitudBajaToAttach = em.getReference(solicitudBajaListSolicitudBajaToAttach.getClass(), solicitudBajaListSolicitudBajaToAttach.getIdSolicitudbaja());
                attachedSolicitudBajaList.add(solicitudBajaListSolicitudBajaToAttach);
            }
            alumno.setSolicitudBajaList(attachedSolicitudBajaList);
            List<ResultadoEvaluacion> attachedResultadoEvaluacionList = new ArrayList<ResultadoEvaluacion>();
            for (ResultadoEvaluacion resultadoEvaluacionListResultadoEvaluacionToAttach : alumno.getResultadoEvaluacionList()) {
                resultadoEvaluacionListResultadoEvaluacionToAttach = em.getReference(resultadoEvaluacionListResultadoEvaluacionToAttach.getClass(), resultadoEvaluacionListResultadoEvaluacionToAttach.getIdResultadoevaluacion());
                attachedResultadoEvaluacionList.add(resultadoEvaluacionListResultadoEvaluacionToAttach);
            }
            alumno.setResultadoEvaluacionList(attachedResultadoEvaluacionList);
            List<AsignacionEncuestaalumno> attachedAsignacionEncuestaalumnoList = new ArrayList<AsignacionEncuestaalumno>();
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach : alumno.getAsignacionEncuestaalumnoList()) {
                asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach = em.getReference(asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach.getClass(), asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach.getIdAsignacionencuestaalumno());
                attachedAsignacionEncuestaalumnoList.add(asignacionEncuestaalumnoListAsignacionEncuestaalumnoToAttach);
            }
            alumno.setAsignacionEncuestaalumnoList(attachedAsignacionEncuestaalumnoList);
            List<SeguimientoAlumno> attachedSeguimientoAlumnoList = new ArrayList<SeguimientoAlumno>();
            for (SeguimientoAlumno seguimientoAlumnoListSeguimientoAlumnoToAttach : alumno.getSeguimientoAlumnoList()) {
                seguimientoAlumnoListSeguimientoAlumnoToAttach = em.getReference(seguimientoAlumnoListSeguimientoAlumnoToAttach.getClass(), seguimientoAlumnoListSeguimientoAlumnoToAttach.getIdSeguimiento());
                attachedSeguimientoAlumnoList.add(seguimientoAlumnoListSeguimientoAlumnoToAttach);
            }
            alumno.setSeguimientoAlumnoList(attachedSeguimientoAlumnoList);
            List<MovimientoCie> attachedMovimientoCieList = new ArrayList<MovimientoCie>();
            for (MovimientoCie movimientoCieListMovimientoCieToAttach : alumno.getMovimientoCieList()) {
                movimientoCieListMovimientoCieToAttach = em.getReference(movimientoCieListMovimientoCieToAttach.getClass(), movimientoCieListMovimientoCieToAttach.getIdMovimientocie());
                attachedMovimientoCieList.add(movimientoCieListMovimientoCieToAttach);
            }
            alumno.setMovimientoCieList(attachedMovimientoCieList);
            List<AsignacionGrupoexamenextemporaneo> attachedAsignacionGrupoexamenextemporaneoList = new ArrayList<AsignacionGrupoexamenextemporaneo>();
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach : alumno.getAsignacionGrupoexamenextemporaneoList()) {
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach = em.getReference(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach.getClass(), asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach.getIdAsignaciongrupoexamenextemporaneo());
                attachedAsignacionGrupoexamenextemporaneoList.add(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneoToAttach);
            }
            alumno.setAsignacionGrupoexamenextemporaneoList(attachedAsignacionGrupoexamenextemporaneoList);
            List<SolicitudTitulacion> attachedSolicitudTitulacionList = new ArrayList<SolicitudTitulacion>();
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacionToAttach : alumno.getSolicitudTitulacionList()) {
                solicitudTitulacionListSolicitudTitulacionToAttach = em.getReference(solicitudTitulacionListSolicitudTitulacionToAttach.getClass(), solicitudTitulacionListSolicitudTitulacionToAttach.getIdSolicitudtitulacion());
                attachedSolicitudTitulacionList.add(solicitudTitulacionListSolicitudTitulacionToAttach);
            }
            alumno.setSolicitudTitulacionList(attachedSolicitudTitulacionList);
            List<ServicioSocial> attachedServicioSocialList = new ArrayList<ServicioSocial>();
            for (ServicioSocial servicioSocialListServicioSocialToAttach : alumno.getServicioSocialList()) {
                servicioSocialListServicioSocialToAttach = em.getReference(servicioSocialListServicioSocialToAttach.getClass(), servicioSocialListServicioSocialToAttach.getIdServiciosocial());
                attachedServicioSocialList.add(servicioSocialListServicioSocialToAttach);
            }
            alumno.setServicioSocialList(attachedServicioSocialList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : alumno.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            alumno.setFacturaList(attachedFacturaList);
            List<InscripcionEquivalencia> attachedInscripcionEquivalenciaList = new ArrayList<InscripcionEquivalencia>();
            for (InscripcionEquivalencia inscripcionEquivalenciaListInscripcionEquivalenciaToAttach : alumno.getInscripcionEquivalenciaList()) {
                inscripcionEquivalenciaListInscripcionEquivalenciaToAttach = em.getReference(inscripcionEquivalenciaListInscripcionEquivalenciaToAttach.getClass(), inscripcionEquivalenciaListInscripcionEquivalenciaToAttach.getIdInscripcionequivalencia());
                attachedInscripcionEquivalenciaList.add(inscripcionEquivalenciaListInscripcionEquivalenciaToAttach);
            }
            alumno.setInscripcionEquivalenciaList(attachedInscripcionEquivalenciaList);
            List<AsignacionGrupo> attachedAsignacionGrupoList = new ArrayList<AsignacionGrupo>();
            for (AsignacionGrupo asignacionGrupoListAsignacionGrupoToAttach : alumno.getAsignacionGrupoList()) {
                asignacionGrupoListAsignacionGrupoToAttach = em.getReference(asignacionGrupoListAsignacionGrupoToAttach.getClass(), asignacionGrupoListAsignacionGrupoToAttach.getIdAsignaciongrupo());
                attachedAsignacionGrupoList.add(asignacionGrupoListAsignacionGrupoToAttach);
            }
            alumno.setAsignacionGrupoList(attachedAsignacionGrupoList);
            List<ControlDocumento> attachedControlDocumentoList = new ArrayList<ControlDocumento>();
            for (ControlDocumento controlDocumentoListControlDocumentoToAttach : alumno.getControlDocumentoList()) {
                controlDocumentoListControlDocumentoToAttach = em.getReference(controlDocumentoListControlDocumentoToAttach.getClass(), controlDocumentoListControlDocumentoToAttach.getIdControldocumento());
                attachedControlDocumentoList.add(controlDocumentoListControlDocumentoToAttach);
            }
            alumno.setControlDocumentoList(attachedControlDocumentoList);
            List<CompromisoDocumentacion> attachedCompromisoDocumentacionList = new ArrayList<CompromisoDocumentacion>();
            for (CompromisoDocumentacion compromisoDocumentacionListCompromisoDocumentacionToAttach : alumno.getCompromisoDocumentacionList()) {
                compromisoDocumentacionListCompromisoDocumentacionToAttach = em.getReference(compromisoDocumentacionListCompromisoDocumentacionToAttach.getClass(), compromisoDocumentacionListCompromisoDocumentacionToAttach.getIdCompromisodocumentacion());
                attachedCompromisoDocumentacionList.add(compromisoDocumentacionListCompromisoDocumentacionToAttach);
            }
            alumno.setCompromisoDocumentacionList(attachedCompromisoDocumentacionList);
            List<SolicitudProgramacion> attachedSolicitudProgramacionList = new ArrayList<SolicitudProgramacion>();
            for (SolicitudProgramacion solicitudProgramacionListSolicitudProgramacionToAttach : alumno.getSolicitudProgramacionList()) {
                solicitudProgramacionListSolicitudProgramacionToAttach = em.getReference(solicitudProgramacionListSolicitudProgramacionToAttach.getClass(), solicitudProgramacionListSolicitudProgramacionToAttach.getIdSolicitudprogramacion());
                attachedSolicitudProgramacionList.add(solicitudProgramacionListSolicitudProgramacionToAttach);
            }
            alumno.setSolicitudProgramacionList(attachedSolicitudProgramacionList);
            em.persist(alumno);
            if (movimientoAdeudo != null) {
                Alumno oldAlumnoOfMovimientoAdeudo = movimientoAdeudo.getAlumno();
                if (oldAlumnoOfMovimientoAdeudo != null) {
                    oldAlumnoOfMovimientoAdeudo.setMovimientoAdeudo(null);
                    oldAlumnoOfMovimientoAdeudo = em.merge(oldAlumnoOfMovimientoAdeudo);
                }
                movimientoAdeudo.setAlumno(alumno);
                movimientoAdeudo = em.merge(movimientoAdeudo);
            }
            if (idTipoalumno != null) {
                idTipoalumno.getAlumnoList().add(alumno);
                idTipoalumno = em.merge(idTipoalumno);
            }
            if (idAspirante != null) {
                idAspirante.getAlumnoList().add(alumno);
                idAspirante = em.merge(idAspirante);
            }
            for (Adeudo adeudoListAdeudo : alumno.getAdeudoList()) {
                Alumno oldMatriculaOfAdeudoListAdeudo = adeudoListAdeudo.getMatricula();
                adeudoListAdeudo.setMatricula(alumno);
                adeudoListAdeudo = em.merge(adeudoListAdeudo);
                if (oldMatriculaOfAdeudoListAdeudo != null) {
                    oldMatriculaOfAdeudoListAdeudo.getAdeudoList().remove(adeudoListAdeudo);
                    oldMatriculaOfAdeudoListAdeudo = em.merge(oldMatriculaOfAdeudoListAdeudo);
                }
            }
            for (Movimientocie movimientocieListMovimientocie : alumno.getMovimientocieList()) {
                Alumno oldMatriculaOfMovimientocieListMovimientocie = movimientocieListMovimientocie.getMatricula();
                movimientocieListMovimientocie.setMatricula(alumno);
                movimientocieListMovimientocie = em.merge(movimientocieListMovimientocie);
                if (oldMatriculaOfMovimientocieListMovimientocie != null) {
                    oldMatriculaOfMovimientocieListMovimientocie.getMovimientocieList().remove(movimientocieListMovimientocie);
                    oldMatriculaOfMovimientocieListMovimientocie = em.merge(oldMatriculaOfMovimientocieListMovimientocie);
                }
            }
            for (Convenio convenioListConvenio : alumno.getConvenioList()) {
                convenioListConvenio.getAlumnoList().add(alumno);
                convenioListConvenio = em.merge(convenioListConvenio);
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocente : alumno.getAsignacionEncuestadocenteList()) {
                Alumno oldMatriculaOfAsignacionEncuestadocenteListAsignacionEncuestadocente = asignacionEncuestadocenteListAsignacionEncuestadocente.getMatricula();
                asignacionEncuestadocenteListAsignacionEncuestadocente.setMatricula(alumno);
                asignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListAsignacionEncuestadocente);
                if (oldMatriculaOfAsignacionEncuestadocenteListAsignacionEncuestadocente != null) {
                    oldMatriculaOfAsignacionEncuestadocenteListAsignacionEncuestadocente.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocenteListAsignacionEncuestadocente);
                    oldMatriculaOfAsignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(oldMatriculaOfAsignacionEncuestadocenteListAsignacionEncuestadocente);
                }
            }
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : alumno.getAlumnoAsignaturaList()) {
                Alumno oldMatriculaOfAlumnoAsignaturaListAlumnoAsignatura = alumnoAsignaturaListAlumnoAsignatura.getMatricula();
                alumnoAsignaturaListAlumnoAsignatura.setMatricula(alumno);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
                if (oldMatriculaOfAlumnoAsignaturaListAlumnoAsignatura != null) {
                    oldMatriculaOfAlumnoAsignaturaListAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListAlumnoAsignatura);
                    oldMatriculaOfAlumnoAsignaturaListAlumnoAsignatura = em.merge(oldMatriculaOfAlumnoAsignaturaListAlumnoAsignatura);
                }
            }
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListAsignacionGrupoinduccion : alumno.getAsignacionGrupoinduccionList()) {
                Alumno oldMatriculaOfAsignacionGrupoinduccionListAsignacionGrupoinduccion = asignacionGrupoinduccionListAsignacionGrupoinduccion.getMatricula();
                asignacionGrupoinduccionListAsignacionGrupoinduccion.setMatricula(alumno);
                asignacionGrupoinduccionListAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListAsignacionGrupoinduccion);
                if (oldMatriculaOfAsignacionGrupoinduccionListAsignacionGrupoinduccion != null) {
                    oldMatriculaOfAsignacionGrupoinduccionListAsignacionGrupoinduccion.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccionListAsignacionGrupoinduccion);
                    oldMatriculaOfAsignacionGrupoinduccionListAsignacionGrupoinduccion = em.merge(oldMatriculaOfAsignacionGrupoinduccionListAsignacionGrupoinduccion);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : alumno.getExamenExtemporaneoList()) {
                Alumno oldMatriculaOfExamenExtemporaneoListExamenExtemporaneo = examenExtemporaneoListExamenExtemporaneo.getMatricula();
                examenExtemporaneoListExamenExtemporaneo.setMatricula(alumno);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
                if (oldMatriculaOfExamenExtemporaneoListExamenExtemporaneo != null) {
                    oldMatriculaOfExamenExtemporaneoListExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListExamenExtemporaneo);
                    oldMatriculaOfExamenExtemporaneoListExamenExtemporaneo = em.merge(oldMatriculaOfExamenExtemporaneoListExamenExtemporaneo);
                }
            }
            for (SolicitudBaja solicitudBajaListSolicitudBaja : alumno.getSolicitudBajaList()) {
                Alumno oldMatriculaOfSolicitudBajaListSolicitudBaja = solicitudBajaListSolicitudBaja.getMatricula();
                solicitudBajaListSolicitudBaja.setMatricula(alumno);
                solicitudBajaListSolicitudBaja = em.merge(solicitudBajaListSolicitudBaja);
                if (oldMatriculaOfSolicitudBajaListSolicitudBaja != null) {
                    oldMatriculaOfSolicitudBajaListSolicitudBaja.getSolicitudBajaList().remove(solicitudBajaListSolicitudBaja);
                    oldMatriculaOfSolicitudBajaListSolicitudBaja = em.merge(oldMatriculaOfSolicitudBajaListSolicitudBaja);
                }
            }
            for (ResultadoEvaluacion resultadoEvaluacionListResultadoEvaluacion : alumno.getResultadoEvaluacionList()) {
                Alumno oldMatriculaOfResultadoEvaluacionListResultadoEvaluacion = resultadoEvaluacionListResultadoEvaluacion.getMatricula();
                resultadoEvaluacionListResultadoEvaluacion.setMatricula(alumno);
                resultadoEvaluacionListResultadoEvaluacion = em.merge(resultadoEvaluacionListResultadoEvaluacion);
                if (oldMatriculaOfResultadoEvaluacionListResultadoEvaluacion != null) {
                    oldMatriculaOfResultadoEvaluacionListResultadoEvaluacion.getResultadoEvaluacionList().remove(resultadoEvaluacionListResultadoEvaluacion);
                    oldMatriculaOfResultadoEvaluacionListResultadoEvaluacion = em.merge(oldMatriculaOfResultadoEvaluacionListResultadoEvaluacion);
                }
            }
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListAsignacionEncuestaalumno : alumno.getAsignacionEncuestaalumnoList()) {
                Alumno oldMatriculaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno = asignacionEncuestaalumnoListAsignacionEncuestaalumno.getMatricula();
                asignacionEncuestaalumnoListAsignacionEncuestaalumno.setMatricula(alumno);
                asignacionEncuestaalumnoListAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListAsignacionEncuestaalumno);
                if (oldMatriculaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno != null) {
                    oldMatriculaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumnoListAsignacionEncuestaalumno);
                    oldMatriculaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno = em.merge(oldMatriculaOfAsignacionEncuestaalumnoListAsignacionEncuestaalumno);
                }
            }
            for (SeguimientoAlumno seguimientoAlumnoListSeguimientoAlumno : alumno.getSeguimientoAlumnoList()) {
                Alumno oldMatriculaOfSeguimientoAlumnoListSeguimientoAlumno = seguimientoAlumnoListSeguimientoAlumno.getMatricula();
                seguimientoAlumnoListSeguimientoAlumno.setMatricula(alumno);
                seguimientoAlumnoListSeguimientoAlumno = em.merge(seguimientoAlumnoListSeguimientoAlumno);
                if (oldMatriculaOfSeguimientoAlumnoListSeguimientoAlumno != null) {
                    oldMatriculaOfSeguimientoAlumnoListSeguimientoAlumno.getSeguimientoAlumnoList().remove(seguimientoAlumnoListSeguimientoAlumno);
                    oldMatriculaOfSeguimientoAlumnoListSeguimientoAlumno = em.merge(oldMatriculaOfSeguimientoAlumnoListSeguimientoAlumno);
                }
            }
            for (MovimientoCie movimientoCieListMovimientoCie : alumno.getMovimientoCieList()) {
                Alumno oldMatriculaOfMovimientoCieListMovimientoCie = movimientoCieListMovimientoCie.getMatricula();
                movimientoCieListMovimientoCie.setMatricula(alumno);
                movimientoCieListMovimientoCie = em.merge(movimientoCieListMovimientoCie);
                if (oldMatriculaOfMovimientoCieListMovimientoCie != null) {
                    oldMatriculaOfMovimientoCieListMovimientoCie.getMovimientoCieList().remove(movimientoCieListMovimientoCie);
                    oldMatriculaOfMovimientoCieListMovimientoCie = em.merge(oldMatriculaOfMovimientoCieListMovimientoCie);
                }
            }
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo : alumno.getAsignacionGrupoexamenextemporaneoList()) {
                Alumno oldMatriculaOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.getMatricula();
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.setMatricula(alumno);
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
                if (oldMatriculaOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo != null) {
                    oldMatriculaOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
                    oldMatriculaOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = em.merge(oldMatriculaOfAsignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacion : alumno.getSolicitudTitulacionList()) {
                Alumno oldMatriculaOfSolicitudTitulacionListSolicitudTitulacion = solicitudTitulacionListSolicitudTitulacion.getMatricula();
                solicitudTitulacionListSolicitudTitulacion.setMatricula(alumno);
                solicitudTitulacionListSolicitudTitulacion = em.merge(solicitudTitulacionListSolicitudTitulacion);
                if (oldMatriculaOfSolicitudTitulacionListSolicitudTitulacion != null) {
                    oldMatriculaOfSolicitudTitulacionListSolicitudTitulacion.getSolicitudTitulacionList().remove(solicitudTitulacionListSolicitudTitulacion);
                    oldMatriculaOfSolicitudTitulacionListSolicitudTitulacion = em.merge(oldMatriculaOfSolicitudTitulacionListSolicitudTitulacion);
                }
            }
            for (ServicioSocial servicioSocialListServicioSocial : alumno.getServicioSocialList()) {
                Alumno oldMatriculaOfServicioSocialListServicioSocial = servicioSocialListServicioSocial.getMatricula();
                servicioSocialListServicioSocial.setMatricula(alumno);
                servicioSocialListServicioSocial = em.merge(servicioSocialListServicioSocial);
                if (oldMatriculaOfServicioSocialListServicioSocial != null) {
                    oldMatriculaOfServicioSocialListServicioSocial.getServicioSocialList().remove(servicioSocialListServicioSocial);
                    oldMatriculaOfServicioSocialListServicioSocial = em.merge(oldMatriculaOfServicioSocialListServicioSocial);
                }
            }
            for (Factura facturaListFactura : alumno.getFacturaList()) {
                Alumno oldMatriculaOfFacturaListFactura = facturaListFactura.getMatricula();
                facturaListFactura.setMatricula(alumno);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldMatriculaOfFacturaListFactura != null) {
                    oldMatriculaOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldMatriculaOfFacturaListFactura = em.merge(oldMatriculaOfFacturaListFactura);
                }
            }
            for (InscripcionEquivalencia inscripcionEquivalenciaListInscripcionEquivalencia : alumno.getInscripcionEquivalenciaList()) {
                Alumno oldMatriculaOfInscripcionEquivalenciaListInscripcionEquivalencia = inscripcionEquivalenciaListInscripcionEquivalencia.getMatricula();
                inscripcionEquivalenciaListInscripcionEquivalencia.setMatricula(alumno);
                inscripcionEquivalenciaListInscripcionEquivalencia = em.merge(inscripcionEquivalenciaListInscripcionEquivalencia);
                if (oldMatriculaOfInscripcionEquivalenciaListInscripcionEquivalencia != null) {
                    oldMatriculaOfInscripcionEquivalenciaListInscripcionEquivalencia.getInscripcionEquivalenciaList().remove(inscripcionEquivalenciaListInscripcionEquivalencia);
                    oldMatriculaOfInscripcionEquivalenciaListInscripcionEquivalencia = em.merge(oldMatriculaOfInscripcionEquivalenciaListInscripcionEquivalencia);
                }
            }
            for (AsignacionGrupo asignacionGrupoListAsignacionGrupo : alumno.getAsignacionGrupoList()) {
                Alumno oldMatriculaOfAsignacionGrupoListAsignacionGrupo = asignacionGrupoListAsignacionGrupo.getMatricula();
                asignacionGrupoListAsignacionGrupo.setMatricula(alumno);
                asignacionGrupoListAsignacionGrupo = em.merge(asignacionGrupoListAsignacionGrupo);
                if (oldMatriculaOfAsignacionGrupoListAsignacionGrupo != null) {
                    oldMatriculaOfAsignacionGrupoListAsignacionGrupo.getAsignacionGrupoList().remove(asignacionGrupoListAsignacionGrupo);
                    oldMatriculaOfAsignacionGrupoListAsignacionGrupo = em.merge(oldMatriculaOfAsignacionGrupoListAsignacionGrupo);
                }
            }
            for (ControlDocumento controlDocumentoListControlDocumento : alumno.getControlDocumentoList()) {
                Alumno oldMatriculaOfControlDocumentoListControlDocumento = controlDocumentoListControlDocumento.getMatricula();
                controlDocumentoListControlDocumento.setMatricula(alumno);
                controlDocumentoListControlDocumento = em.merge(controlDocumentoListControlDocumento);
                if (oldMatriculaOfControlDocumentoListControlDocumento != null) {
                    oldMatriculaOfControlDocumentoListControlDocumento.getControlDocumentoList().remove(controlDocumentoListControlDocumento);
                    oldMatriculaOfControlDocumentoListControlDocumento = em.merge(oldMatriculaOfControlDocumentoListControlDocumento);
                }
            }
            for (CompromisoDocumentacion compromisoDocumentacionListCompromisoDocumentacion : alumno.getCompromisoDocumentacionList()) {
                Alumno oldMatriculaOfCompromisoDocumentacionListCompromisoDocumentacion = compromisoDocumentacionListCompromisoDocumentacion.getMatricula();
                compromisoDocumentacionListCompromisoDocumentacion.setMatricula(alumno);
                compromisoDocumentacionListCompromisoDocumentacion = em.merge(compromisoDocumentacionListCompromisoDocumentacion);
                if (oldMatriculaOfCompromisoDocumentacionListCompromisoDocumentacion != null) {
                    oldMatriculaOfCompromisoDocumentacionListCompromisoDocumentacion.getCompromisoDocumentacionList().remove(compromisoDocumentacionListCompromisoDocumentacion);
                    oldMatriculaOfCompromisoDocumentacionListCompromisoDocumentacion = em.merge(oldMatriculaOfCompromisoDocumentacionListCompromisoDocumentacion);
                }
            }
            for (SolicitudProgramacion solicitudProgramacionListSolicitudProgramacion : alumno.getSolicitudProgramacionList()) {
                Alumno oldMatriculaOfSolicitudProgramacionListSolicitudProgramacion = solicitudProgramacionListSolicitudProgramacion.getMatricula();
                solicitudProgramacionListSolicitudProgramacion.setMatricula(alumno);
                solicitudProgramacionListSolicitudProgramacion = em.merge(solicitudProgramacionListSolicitudProgramacion);
                if (oldMatriculaOfSolicitudProgramacionListSolicitudProgramacion != null) {
                    oldMatriculaOfSolicitudProgramacionListSolicitudProgramacion.getSolicitudProgramacionList().remove(solicitudProgramacionListSolicitudProgramacion);
                    oldMatriculaOfSolicitudProgramacionListSolicitudProgramacion = em.merge(oldMatriculaOfSolicitudProgramacionListSolicitudProgramacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumno(alumno.getMatricula()) != null) {
                throw new PreexistingEntityException("Alumno " + alumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getMatricula());
            MovimientoAdeudo movimientoAdeudoOld = persistentAlumno.getMovimientoAdeudo();
            MovimientoAdeudo movimientoAdeudoNew = alumno.getMovimientoAdeudo();
            TipoAlumno idTipoalumnoOld = persistentAlumno.getIdTipoalumno();
            TipoAlumno idTipoalumnoNew = alumno.getIdTipoalumno();
            Aspirante idAspiranteOld = persistentAlumno.getIdAspirante();
            Aspirante idAspiranteNew = alumno.getIdAspirante();
            List<Adeudo> adeudoListOld = persistentAlumno.getAdeudoList();
            List<Adeudo> adeudoListNew = alumno.getAdeudoList();
            List<Movimientocie> movimientocieListOld = persistentAlumno.getMovimientocieList();
            List<Movimientocie> movimientocieListNew = alumno.getMovimientocieList();
            List<Convenio> convenioListOld = persistentAlumno.getConvenioList();
            List<Convenio> convenioListNew = alumno.getConvenioList();
            List<AsignacionEncuestadocente> asignacionEncuestadocenteListOld = persistentAlumno.getAsignacionEncuestadocenteList();
            List<AsignacionEncuestadocente> asignacionEncuestadocenteListNew = alumno.getAsignacionEncuestadocenteList();
            List<AlumnoAsignatura> alumnoAsignaturaListOld = persistentAlumno.getAlumnoAsignaturaList();
            List<AlumnoAsignatura> alumnoAsignaturaListNew = alumno.getAlumnoAsignaturaList();
            List<AsignacionGrupoinduccion> asignacionGrupoinduccionListOld = persistentAlumno.getAsignacionGrupoinduccionList();
            List<AsignacionGrupoinduccion> asignacionGrupoinduccionListNew = alumno.getAsignacionGrupoinduccionList();
            List<ExamenExtemporaneo> examenExtemporaneoListOld = persistentAlumno.getExamenExtemporaneoList();
            List<ExamenExtemporaneo> examenExtemporaneoListNew = alumno.getExamenExtemporaneoList();
            List<SolicitudBaja> solicitudBajaListOld = persistentAlumno.getSolicitudBajaList();
            List<SolicitudBaja> solicitudBajaListNew = alumno.getSolicitudBajaList();
            List<ResultadoEvaluacion> resultadoEvaluacionListOld = persistentAlumno.getResultadoEvaluacionList();
            List<ResultadoEvaluacion> resultadoEvaluacionListNew = alumno.getResultadoEvaluacionList();
            List<AsignacionEncuestaalumno> asignacionEncuestaalumnoListOld = persistentAlumno.getAsignacionEncuestaalumnoList();
            List<AsignacionEncuestaalumno> asignacionEncuestaalumnoListNew = alumno.getAsignacionEncuestaalumnoList();
            List<SeguimientoAlumno> seguimientoAlumnoListOld = persistentAlumno.getSeguimientoAlumnoList();
            List<SeguimientoAlumno> seguimientoAlumnoListNew = alumno.getSeguimientoAlumnoList();
            List<MovimientoCie> movimientoCieListOld = persistentAlumno.getMovimientoCieList();
            List<MovimientoCie> movimientoCieListNew = alumno.getMovimientoCieList();
            List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoListOld = persistentAlumno.getAsignacionGrupoexamenextemporaneoList();
            List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoListNew = alumno.getAsignacionGrupoexamenextemporaneoList();
            List<SolicitudTitulacion> solicitudTitulacionListOld = persistentAlumno.getSolicitudTitulacionList();
            List<SolicitudTitulacion> solicitudTitulacionListNew = alumno.getSolicitudTitulacionList();
            List<ServicioSocial> servicioSocialListOld = persistentAlumno.getServicioSocialList();
            List<ServicioSocial> servicioSocialListNew = alumno.getServicioSocialList();
            List<Factura> facturaListOld = persistentAlumno.getFacturaList();
            List<Factura> facturaListNew = alumno.getFacturaList();
            List<InscripcionEquivalencia> inscripcionEquivalenciaListOld = persistentAlumno.getInscripcionEquivalenciaList();
            List<InscripcionEquivalencia> inscripcionEquivalenciaListNew = alumno.getInscripcionEquivalenciaList();
            List<AsignacionGrupo> asignacionGrupoListOld = persistentAlumno.getAsignacionGrupoList();
            List<AsignacionGrupo> asignacionGrupoListNew = alumno.getAsignacionGrupoList();
            List<ControlDocumento> controlDocumentoListOld = persistentAlumno.getControlDocumentoList();
            List<ControlDocumento> controlDocumentoListNew = alumno.getControlDocumentoList();
            List<CompromisoDocumentacion> compromisoDocumentacionListOld = persistentAlumno.getCompromisoDocumentacionList();
            List<CompromisoDocumentacion> compromisoDocumentacionListNew = alumno.getCompromisoDocumentacionList();
            List<SolicitudProgramacion> solicitudProgramacionListOld = persistentAlumno.getSolicitudProgramacionList();
            List<SolicitudProgramacion> solicitudProgramacionListNew = alumno.getSolicitudProgramacionList();
            List<String> illegalOrphanMessages = null;
            if (movimientoAdeudoOld != null && !movimientoAdeudoOld.equals(movimientoAdeudoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain MovimientoAdeudo " + movimientoAdeudoOld + " since its alumno field is not nullable.");
            }
            for (AsignacionGrupo asignacionGrupoListOldAsignacionGrupo : asignacionGrupoListOld) {
                if (!asignacionGrupoListNew.contains(asignacionGrupoListOldAsignacionGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignacionGrupo " + asignacionGrupoListOldAsignacionGrupo + " since its matricula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (movimientoAdeudoNew != null) {
                movimientoAdeudoNew = em.getReference(movimientoAdeudoNew.getClass(), movimientoAdeudoNew.getMatricula());
                alumno.setMovimientoAdeudo(movimientoAdeudoNew);
            }
            if (idTipoalumnoNew != null) {
                idTipoalumnoNew = em.getReference(idTipoalumnoNew.getClass(), idTipoalumnoNew.getIdTipoalumno());
                alumno.setIdTipoalumno(idTipoalumnoNew);
            }
            if (idAspiranteNew != null) {
                idAspiranteNew = em.getReference(idAspiranteNew.getClass(), idAspiranteNew.getIdAspirante());
                alumno.setIdAspirante(idAspiranteNew);
            }
            List<Adeudo> attachedAdeudoListNew = new ArrayList<Adeudo>();
            for (Adeudo adeudoListNewAdeudoToAttach : adeudoListNew) {
                adeudoListNewAdeudoToAttach = em.getReference(adeudoListNewAdeudoToAttach.getClass(), adeudoListNewAdeudoToAttach.getIdadeudo());
                attachedAdeudoListNew.add(adeudoListNewAdeudoToAttach);
            }
            adeudoListNew = attachedAdeudoListNew;
            alumno.setAdeudoList(adeudoListNew);
            List<Movimientocie> attachedMovimientocieListNew = new ArrayList<Movimientocie>();
            for (Movimientocie movimientocieListNewMovimientocieToAttach : movimientocieListNew) {
                movimientocieListNewMovimientocieToAttach = em.getReference(movimientocieListNewMovimientocieToAttach.getClass(), movimientocieListNewMovimientocieToAttach.getIdmovimientocie());
                attachedMovimientocieListNew.add(movimientocieListNewMovimientocieToAttach);
            }
            movimientocieListNew = attachedMovimientocieListNew;
            alumno.setMovimientocieList(movimientocieListNew);
            List<Convenio> attachedConvenioListNew = new ArrayList<Convenio>();
            for (Convenio convenioListNewConvenioToAttach : convenioListNew) {
                convenioListNewConvenioToAttach = em.getReference(convenioListNewConvenioToAttach.getClass(), convenioListNewConvenioToAttach.getIdConvenio());
                attachedConvenioListNew.add(convenioListNewConvenioToAttach);
            }
            convenioListNew = attachedConvenioListNew;
            alumno.setConvenioList(convenioListNew);
            List<AsignacionEncuestadocente> attachedAsignacionEncuestadocenteListNew = new ArrayList<AsignacionEncuestadocente>();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach : asignacionEncuestadocenteListNew) {
                asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach = em.getReference(asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach.getClass(), asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach.getIdAsignacionencuestadocente());
                attachedAsignacionEncuestadocenteListNew.add(asignacionEncuestadocenteListNewAsignacionEncuestadocenteToAttach);
            }
            asignacionEncuestadocenteListNew = attachedAsignacionEncuestadocenteListNew;
            alumno.setAsignacionEncuestadocenteList(asignacionEncuestadocenteListNew);
            List<AlumnoAsignatura> attachedAlumnoAsignaturaListNew = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignaturaToAttach : alumnoAsignaturaListNew) {
                alumnoAsignaturaListNewAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getIdAlumnoasignatura());
                attachedAlumnoAsignaturaListNew.add(alumnoAsignaturaListNewAlumnoAsignaturaToAttach);
            }
            alumnoAsignaturaListNew = attachedAlumnoAsignaturaListNew;
            alumno.setAlumnoAsignaturaList(alumnoAsignaturaListNew);
            List<AsignacionGrupoinduccion> attachedAsignacionGrupoinduccionListNew = new ArrayList<AsignacionGrupoinduccion>();
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach : asignacionGrupoinduccionListNew) {
                asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach = em.getReference(asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach.getClass(), asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach.getIdAsignacionGrupoinduccion());
                attachedAsignacionGrupoinduccionListNew.add(asignacionGrupoinduccionListNewAsignacionGrupoinduccionToAttach);
            }
            asignacionGrupoinduccionListNew = attachedAsignacionGrupoinduccionListNew;
            alumno.setAsignacionGrupoinduccionList(asignacionGrupoinduccionListNew);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoListNew = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneoToAttach : examenExtemporaneoListNew) {
                examenExtemporaneoListNewExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListNewExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListNewExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoListNew.add(examenExtemporaneoListNewExamenExtemporaneoToAttach);
            }
            examenExtemporaneoListNew = attachedExamenExtemporaneoListNew;
            alumno.setExamenExtemporaneoList(examenExtemporaneoListNew);
            List<SolicitudBaja> attachedSolicitudBajaListNew = new ArrayList<SolicitudBaja>();
            for (SolicitudBaja solicitudBajaListNewSolicitudBajaToAttach : solicitudBajaListNew) {
                solicitudBajaListNewSolicitudBajaToAttach = em.getReference(solicitudBajaListNewSolicitudBajaToAttach.getClass(), solicitudBajaListNewSolicitudBajaToAttach.getIdSolicitudbaja());
                attachedSolicitudBajaListNew.add(solicitudBajaListNewSolicitudBajaToAttach);
            }
            solicitudBajaListNew = attachedSolicitudBajaListNew;
            alumno.setSolicitudBajaList(solicitudBajaListNew);
            List<ResultadoEvaluacion> attachedResultadoEvaluacionListNew = new ArrayList<ResultadoEvaluacion>();
            for (ResultadoEvaluacion resultadoEvaluacionListNewResultadoEvaluacionToAttach : resultadoEvaluacionListNew) {
                resultadoEvaluacionListNewResultadoEvaluacionToAttach = em.getReference(resultadoEvaluacionListNewResultadoEvaluacionToAttach.getClass(), resultadoEvaluacionListNewResultadoEvaluacionToAttach.getIdResultadoevaluacion());
                attachedResultadoEvaluacionListNew.add(resultadoEvaluacionListNewResultadoEvaluacionToAttach);
            }
            resultadoEvaluacionListNew = attachedResultadoEvaluacionListNew;
            alumno.setResultadoEvaluacionList(resultadoEvaluacionListNew);
            List<AsignacionEncuestaalumno> attachedAsignacionEncuestaalumnoListNew = new ArrayList<AsignacionEncuestaalumno>();
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach : asignacionEncuestaalumnoListNew) {
                asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach = em.getReference(asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach.getClass(), asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach.getIdAsignacionencuestaalumno());
                attachedAsignacionEncuestaalumnoListNew.add(asignacionEncuestaalumnoListNewAsignacionEncuestaalumnoToAttach);
            }
            asignacionEncuestaalumnoListNew = attachedAsignacionEncuestaalumnoListNew;
            alumno.setAsignacionEncuestaalumnoList(asignacionEncuestaalumnoListNew);
            List<SeguimientoAlumno> attachedSeguimientoAlumnoListNew = new ArrayList<SeguimientoAlumno>();
            for (SeguimientoAlumno seguimientoAlumnoListNewSeguimientoAlumnoToAttach : seguimientoAlumnoListNew) {
                seguimientoAlumnoListNewSeguimientoAlumnoToAttach = em.getReference(seguimientoAlumnoListNewSeguimientoAlumnoToAttach.getClass(), seguimientoAlumnoListNewSeguimientoAlumnoToAttach.getIdSeguimiento());
                attachedSeguimientoAlumnoListNew.add(seguimientoAlumnoListNewSeguimientoAlumnoToAttach);
            }
            seguimientoAlumnoListNew = attachedSeguimientoAlumnoListNew;
            alumno.setSeguimientoAlumnoList(seguimientoAlumnoListNew);
            List<MovimientoCie> attachedMovimientoCieListNew = new ArrayList<MovimientoCie>();
            for (MovimientoCie movimientoCieListNewMovimientoCieToAttach : movimientoCieListNew) {
                movimientoCieListNewMovimientoCieToAttach = em.getReference(movimientoCieListNewMovimientoCieToAttach.getClass(), movimientoCieListNewMovimientoCieToAttach.getIdMovimientocie());
                attachedMovimientoCieListNew.add(movimientoCieListNewMovimientoCieToAttach);
            }
            movimientoCieListNew = attachedMovimientoCieListNew;
            alumno.setMovimientoCieList(movimientoCieListNew);
            List<AsignacionGrupoexamenextemporaneo> attachedAsignacionGrupoexamenextemporaneoListNew = new ArrayList<AsignacionGrupoexamenextemporaneo>();
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach : asignacionGrupoexamenextemporaneoListNew) {
                asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach = em.getReference(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach.getClass(), asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach.getIdAsignaciongrupoexamenextemporaneo());
                attachedAsignacionGrupoexamenextemporaneoListNew.add(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneoToAttach);
            }
            asignacionGrupoexamenextemporaneoListNew = attachedAsignacionGrupoexamenextemporaneoListNew;
            alumno.setAsignacionGrupoexamenextemporaneoList(asignacionGrupoexamenextemporaneoListNew);
            List<SolicitudTitulacion> attachedSolicitudTitulacionListNew = new ArrayList<SolicitudTitulacion>();
            for (SolicitudTitulacion solicitudTitulacionListNewSolicitudTitulacionToAttach : solicitudTitulacionListNew) {
                solicitudTitulacionListNewSolicitudTitulacionToAttach = em.getReference(solicitudTitulacionListNewSolicitudTitulacionToAttach.getClass(), solicitudTitulacionListNewSolicitudTitulacionToAttach.getIdSolicitudtitulacion());
                attachedSolicitudTitulacionListNew.add(solicitudTitulacionListNewSolicitudTitulacionToAttach);
            }
            solicitudTitulacionListNew = attachedSolicitudTitulacionListNew;
            alumno.setSolicitudTitulacionList(solicitudTitulacionListNew);
            List<ServicioSocial> attachedServicioSocialListNew = new ArrayList<ServicioSocial>();
            for (ServicioSocial servicioSocialListNewServicioSocialToAttach : servicioSocialListNew) {
                servicioSocialListNewServicioSocialToAttach = em.getReference(servicioSocialListNewServicioSocialToAttach.getClass(), servicioSocialListNewServicioSocialToAttach.getIdServiciosocial());
                attachedServicioSocialListNew.add(servicioSocialListNewServicioSocialToAttach);
            }
            servicioSocialListNew = attachedServicioSocialListNew;
            alumno.setServicioSocialList(servicioSocialListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            alumno.setFacturaList(facturaListNew);
            List<InscripcionEquivalencia> attachedInscripcionEquivalenciaListNew = new ArrayList<InscripcionEquivalencia>();
            for (InscripcionEquivalencia inscripcionEquivalenciaListNewInscripcionEquivalenciaToAttach : inscripcionEquivalenciaListNew) {
                inscripcionEquivalenciaListNewInscripcionEquivalenciaToAttach = em.getReference(inscripcionEquivalenciaListNewInscripcionEquivalenciaToAttach.getClass(), inscripcionEquivalenciaListNewInscripcionEquivalenciaToAttach.getIdInscripcionequivalencia());
                attachedInscripcionEquivalenciaListNew.add(inscripcionEquivalenciaListNewInscripcionEquivalenciaToAttach);
            }
            inscripcionEquivalenciaListNew = attachedInscripcionEquivalenciaListNew;
            alumno.setInscripcionEquivalenciaList(inscripcionEquivalenciaListNew);
            List<AsignacionGrupo> attachedAsignacionGrupoListNew = new ArrayList<AsignacionGrupo>();
            for (AsignacionGrupo asignacionGrupoListNewAsignacionGrupoToAttach : asignacionGrupoListNew) {
                asignacionGrupoListNewAsignacionGrupoToAttach = em.getReference(asignacionGrupoListNewAsignacionGrupoToAttach.getClass(), asignacionGrupoListNewAsignacionGrupoToAttach.getIdAsignaciongrupo());
                attachedAsignacionGrupoListNew.add(asignacionGrupoListNewAsignacionGrupoToAttach);
            }
            asignacionGrupoListNew = attachedAsignacionGrupoListNew;
            alumno.setAsignacionGrupoList(asignacionGrupoListNew);
            List<ControlDocumento> attachedControlDocumentoListNew = new ArrayList<ControlDocumento>();
            for (ControlDocumento controlDocumentoListNewControlDocumentoToAttach : controlDocumentoListNew) {
                controlDocumentoListNewControlDocumentoToAttach = em.getReference(controlDocumentoListNewControlDocumentoToAttach.getClass(), controlDocumentoListNewControlDocumentoToAttach.getIdControldocumento());
                attachedControlDocumentoListNew.add(controlDocumentoListNewControlDocumentoToAttach);
            }
            controlDocumentoListNew = attachedControlDocumentoListNew;
            alumno.setControlDocumentoList(controlDocumentoListNew);
            List<CompromisoDocumentacion> attachedCompromisoDocumentacionListNew = new ArrayList<CompromisoDocumentacion>();
            for (CompromisoDocumentacion compromisoDocumentacionListNewCompromisoDocumentacionToAttach : compromisoDocumentacionListNew) {
                compromisoDocumentacionListNewCompromisoDocumentacionToAttach = em.getReference(compromisoDocumentacionListNewCompromisoDocumentacionToAttach.getClass(), compromisoDocumentacionListNewCompromisoDocumentacionToAttach.getIdCompromisodocumentacion());
                attachedCompromisoDocumentacionListNew.add(compromisoDocumentacionListNewCompromisoDocumentacionToAttach);
            }
            compromisoDocumentacionListNew = attachedCompromisoDocumentacionListNew;
            alumno.setCompromisoDocumentacionList(compromisoDocumentacionListNew);
            List<SolicitudProgramacion> attachedSolicitudProgramacionListNew = new ArrayList<SolicitudProgramacion>();
            for (SolicitudProgramacion solicitudProgramacionListNewSolicitudProgramacionToAttach : solicitudProgramacionListNew) {
                solicitudProgramacionListNewSolicitudProgramacionToAttach = em.getReference(solicitudProgramacionListNewSolicitudProgramacionToAttach.getClass(), solicitudProgramacionListNewSolicitudProgramacionToAttach.getIdSolicitudprogramacion());
                attachedSolicitudProgramacionListNew.add(solicitudProgramacionListNewSolicitudProgramacionToAttach);
            }
            solicitudProgramacionListNew = attachedSolicitudProgramacionListNew;
            alumno.setSolicitudProgramacionList(solicitudProgramacionListNew);
            alumno = em.merge(alumno);
            if (movimientoAdeudoNew != null && !movimientoAdeudoNew.equals(movimientoAdeudoOld)) {
                Alumno oldAlumnoOfMovimientoAdeudo = movimientoAdeudoNew.getAlumno();
                if (oldAlumnoOfMovimientoAdeudo != null) {
                    oldAlumnoOfMovimientoAdeudo.setMovimientoAdeudo(null);
                    oldAlumnoOfMovimientoAdeudo = em.merge(oldAlumnoOfMovimientoAdeudo);
                }
                movimientoAdeudoNew.setAlumno(alumno);
                movimientoAdeudoNew = em.merge(movimientoAdeudoNew);
            }
            if (idTipoalumnoOld != null && !idTipoalumnoOld.equals(idTipoalumnoNew)) {
                idTipoalumnoOld.getAlumnoList().remove(alumno);
                idTipoalumnoOld = em.merge(idTipoalumnoOld);
            }
            if (idTipoalumnoNew != null && !idTipoalumnoNew.equals(idTipoalumnoOld)) {
                idTipoalumnoNew.getAlumnoList().add(alumno);
                idTipoalumnoNew = em.merge(idTipoalumnoNew);
            }
            if (idAspiranteOld != null && !idAspiranteOld.equals(idAspiranteNew)) {
                idAspiranteOld.getAlumnoList().remove(alumno);
                idAspiranteOld = em.merge(idAspiranteOld);
            }
            if (idAspiranteNew != null && !idAspiranteNew.equals(idAspiranteOld)) {
                idAspiranteNew.getAlumnoList().add(alumno);
                idAspiranteNew = em.merge(idAspiranteNew);
            }
            for (Adeudo adeudoListOldAdeudo : adeudoListOld) {
                if (!adeudoListNew.contains(adeudoListOldAdeudo)) {
                    adeudoListOldAdeudo.setMatricula(null);
                    adeudoListOldAdeudo = em.merge(adeudoListOldAdeudo);
                }
            }
            for (Adeudo adeudoListNewAdeudo : adeudoListNew) {
                if (!adeudoListOld.contains(adeudoListNewAdeudo)) {
                    Alumno oldMatriculaOfAdeudoListNewAdeudo = adeudoListNewAdeudo.getMatricula();
                    adeudoListNewAdeudo.setMatricula(alumno);
                    adeudoListNewAdeudo = em.merge(adeudoListNewAdeudo);
                    if (oldMatriculaOfAdeudoListNewAdeudo != null && !oldMatriculaOfAdeudoListNewAdeudo.equals(alumno)) {
                        oldMatriculaOfAdeudoListNewAdeudo.getAdeudoList().remove(adeudoListNewAdeudo);
                        oldMatriculaOfAdeudoListNewAdeudo = em.merge(oldMatriculaOfAdeudoListNewAdeudo);
                    }
                }
            }
            for (Movimientocie movimientocieListOldMovimientocie : movimientocieListOld) {
                if (!movimientocieListNew.contains(movimientocieListOldMovimientocie)) {
                    movimientocieListOldMovimientocie.setMatricula(null);
                    movimientocieListOldMovimientocie = em.merge(movimientocieListOldMovimientocie);
                }
            }
            for (Movimientocie movimientocieListNewMovimientocie : movimientocieListNew) {
                if (!movimientocieListOld.contains(movimientocieListNewMovimientocie)) {
                    Alumno oldMatriculaOfMovimientocieListNewMovimientocie = movimientocieListNewMovimientocie.getMatricula();
                    movimientocieListNewMovimientocie.setMatricula(alumno);
                    movimientocieListNewMovimientocie = em.merge(movimientocieListNewMovimientocie);
                    if (oldMatriculaOfMovimientocieListNewMovimientocie != null && !oldMatriculaOfMovimientocieListNewMovimientocie.equals(alumno)) {
                        oldMatriculaOfMovimientocieListNewMovimientocie.getMovimientocieList().remove(movimientocieListNewMovimientocie);
                        oldMatriculaOfMovimientocieListNewMovimientocie = em.merge(oldMatriculaOfMovimientocieListNewMovimientocie);
                    }
                }
            }
            for (Convenio convenioListOldConvenio : convenioListOld) {
                if (!convenioListNew.contains(convenioListOldConvenio)) {
                    convenioListOldConvenio.getAlumnoList().remove(alumno);
                    convenioListOldConvenio = em.merge(convenioListOldConvenio);
                }
            }
            for (Convenio convenioListNewConvenio : convenioListNew) {
                if (!convenioListOld.contains(convenioListNewConvenio)) {
                    convenioListNewConvenio.getAlumnoList().add(alumno);
                    convenioListNewConvenio = em.merge(convenioListNewConvenio);
                }
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListOldAsignacionEncuestadocente : asignacionEncuestadocenteListOld) {
                if (!asignacionEncuestadocenteListNew.contains(asignacionEncuestadocenteListOldAsignacionEncuestadocente)) {
                    asignacionEncuestadocenteListOldAsignacionEncuestadocente.setMatricula(null);
                    asignacionEncuestadocenteListOldAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListOldAsignacionEncuestadocente);
                }
            }
            for (AsignacionEncuestadocente asignacionEncuestadocenteListNewAsignacionEncuestadocente : asignacionEncuestadocenteListNew) {
                if (!asignacionEncuestadocenteListOld.contains(asignacionEncuestadocenteListNewAsignacionEncuestadocente)) {
                    Alumno oldMatriculaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente = asignacionEncuestadocenteListNewAsignacionEncuestadocente.getMatricula();
                    asignacionEncuestadocenteListNewAsignacionEncuestadocente.setMatricula(alumno);
                    asignacionEncuestadocenteListNewAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListNewAsignacionEncuestadocente);
                    if (oldMatriculaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente != null && !oldMatriculaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente.equals(alumno)) {
                        oldMatriculaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente.getAsignacionEncuestadocenteList().remove(asignacionEncuestadocenteListNewAsignacionEncuestadocente);
                        oldMatriculaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente = em.merge(oldMatriculaOfAsignacionEncuestadocenteListNewAsignacionEncuestadocente);
                    }
                }
            }
            for (AlumnoAsignatura alumnoAsignaturaListOldAlumnoAsignatura : alumnoAsignaturaListOld) {
                if (!alumnoAsignaturaListNew.contains(alumnoAsignaturaListOldAlumnoAsignatura)) {
                    alumnoAsignaturaListOldAlumnoAsignatura.setMatricula(null);
                    alumnoAsignaturaListOldAlumnoAsignatura = em.merge(alumnoAsignaturaListOldAlumnoAsignatura);
                }
            }
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignatura : alumnoAsignaturaListNew) {
                if (!alumnoAsignaturaListOld.contains(alumnoAsignaturaListNewAlumnoAsignatura)) {
                    Alumno oldMatriculaOfAlumnoAsignaturaListNewAlumnoAsignatura = alumnoAsignaturaListNewAlumnoAsignatura.getMatricula();
                    alumnoAsignaturaListNewAlumnoAsignatura.setMatricula(alumno);
                    alumnoAsignaturaListNewAlumnoAsignatura = em.merge(alumnoAsignaturaListNewAlumnoAsignatura);
                    if (oldMatriculaOfAlumnoAsignaturaListNewAlumnoAsignatura != null && !oldMatriculaOfAlumnoAsignaturaListNewAlumnoAsignatura.equals(alumno)) {
                        oldMatriculaOfAlumnoAsignaturaListNewAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListNewAlumnoAsignatura);
                        oldMatriculaOfAlumnoAsignaturaListNewAlumnoAsignatura = em.merge(oldMatriculaOfAlumnoAsignaturaListNewAlumnoAsignatura);
                    }
                }
            }
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListOldAsignacionGrupoinduccion : asignacionGrupoinduccionListOld) {
                if (!asignacionGrupoinduccionListNew.contains(asignacionGrupoinduccionListOldAsignacionGrupoinduccion)) {
                    asignacionGrupoinduccionListOldAsignacionGrupoinduccion.setMatricula(null);
                    asignacionGrupoinduccionListOldAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListOldAsignacionGrupoinduccion);
                }
            }
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListNewAsignacionGrupoinduccion : asignacionGrupoinduccionListNew) {
                if (!asignacionGrupoinduccionListOld.contains(asignacionGrupoinduccionListNewAsignacionGrupoinduccion)) {
                    Alumno oldMatriculaOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion = asignacionGrupoinduccionListNewAsignacionGrupoinduccion.getMatricula();
                    asignacionGrupoinduccionListNewAsignacionGrupoinduccion.setMatricula(alumno);
                    asignacionGrupoinduccionListNewAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListNewAsignacionGrupoinduccion);
                    if (oldMatriculaOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion != null && !oldMatriculaOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion.equals(alumno)) {
                        oldMatriculaOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion.getAsignacionGrupoinduccionList().remove(asignacionGrupoinduccionListNewAsignacionGrupoinduccion);
                        oldMatriculaOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion = em.merge(oldMatriculaOfAsignacionGrupoinduccionListNewAsignacionGrupoinduccion);
                    }
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListOldExamenExtemporaneo : examenExtemporaneoListOld) {
                if (!examenExtemporaneoListNew.contains(examenExtemporaneoListOldExamenExtemporaneo)) {
                    examenExtemporaneoListOldExamenExtemporaneo.setMatricula(null);
                    examenExtemporaneoListOldExamenExtemporaneo = em.merge(examenExtemporaneoListOldExamenExtemporaneo);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneo : examenExtemporaneoListNew) {
                if (!examenExtemporaneoListOld.contains(examenExtemporaneoListNewExamenExtemporaneo)) {
                    Alumno oldMatriculaOfExamenExtemporaneoListNewExamenExtemporaneo = examenExtemporaneoListNewExamenExtemporaneo.getMatricula();
                    examenExtemporaneoListNewExamenExtemporaneo.setMatricula(alumno);
                    examenExtemporaneoListNewExamenExtemporaneo = em.merge(examenExtemporaneoListNewExamenExtemporaneo);
                    if (oldMatriculaOfExamenExtemporaneoListNewExamenExtemporaneo != null && !oldMatriculaOfExamenExtemporaneoListNewExamenExtemporaneo.equals(alumno)) {
                        oldMatriculaOfExamenExtemporaneoListNewExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListNewExamenExtemporaneo);
                        oldMatriculaOfExamenExtemporaneoListNewExamenExtemporaneo = em.merge(oldMatriculaOfExamenExtemporaneoListNewExamenExtemporaneo);
                    }
                }
            }
            for (SolicitudBaja solicitudBajaListOldSolicitudBaja : solicitudBajaListOld) {
                if (!solicitudBajaListNew.contains(solicitudBajaListOldSolicitudBaja)) {
                    solicitudBajaListOldSolicitudBaja.setMatricula(null);
                    solicitudBajaListOldSolicitudBaja = em.merge(solicitudBajaListOldSolicitudBaja);
                }
            }
            for (SolicitudBaja solicitudBajaListNewSolicitudBaja : solicitudBajaListNew) {
                if (!solicitudBajaListOld.contains(solicitudBajaListNewSolicitudBaja)) {
                    Alumno oldMatriculaOfSolicitudBajaListNewSolicitudBaja = solicitudBajaListNewSolicitudBaja.getMatricula();
                    solicitudBajaListNewSolicitudBaja.setMatricula(alumno);
                    solicitudBajaListNewSolicitudBaja = em.merge(solicitudBajaListNewSolicitudBaja);
                    if (oldMatriculaOfSolicitudBajaListNewSolicitudBaja != null && !oldMatriculaOfSolicitudBajaListNewSolicitudBaja.equals(alumno)) {
                        oldMatriculaOfSolicitudBajaListNewSolicitudBaja.getSolicitudBajaList().remove(solicitudBajaListNewSolicitudBaja);
                        oldMatriculaOfSolicitudBajaListNewSolicitudBaja = em.merge(oldMatriculaOfSolicitudBajaListNewSolicitudBaja);
                    }
                }
            }
            for (ResultadoEvaluacion resultadoEvaluacionListOldResultadoEvaluacion : resultadoEvaluacionListOld) {
                if (!resultadoEvaluacionListNew.contains(resultadoEvaluacionListOldResultadoEvaluacion)) {
                    resultadoEvaluacionListOldResultadoEvaluacion.setMatricula(null);
                    resultadoEvaluacionListOldResultadoEvaluacion = em.merge(resultadoEvaluacionListOldResultadoEvaluacion);
                }
            }
            for (ResultadoEvaluacion resultadoEvaluacionListNewResultadoEvaluacion : resultadoEvaluacionListNew) {
                if (!resultadoEvaluacionListOld.contains(resultadoEvaluacionListNewResultadoEvaluacion)) {
                    Alumno oldMatriculaOfResultadoEvaluacionListNewResultadoEvaluacion = resultadoEvaluacionListNewResultadoEvaluacion.getMatricula();
                    resultadoEvaluacionListNewResultadoEvaluacion.setMatricula(alumno);
                    resultadoEvaluacionListNewResultadoEvaluacion = em.merge(resultadoEvaluacionListNewResultadoEvaluacion);
                    if (oldMatriculaOfResultadoEvaluacionListNewResultadoEvaluacion != null && !oldMatriculaOfResultadoEvaluacionListNewResultadoEvaluacion.equals(alumno)) {
                        oldMatriculaOfResultadoEvaluacionListNewResultadoEvaluacion.getResultadoEvaluacionList().remove(resultadoEvaluacionListNewResultadoEvaluacion);
                        oldMatriculaOfResultadoEvaluacionListNewResultadoEvaluacion = em.merge(oldMatriculaOfResultadoEvaluacionListNewResultadoEvaluacion);
                    }
                }
            }
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListOldAsignacionEncuestaalumno : asignacionEncuestaalumnoListOld) {
                if (!asignacionEncuestaalumnoListNew.contains(asignacionEncuestaalumnoListOldAsignacionEncuestaalumno)) {
                    asignacionEncuestaalumnoListOldAsignacionEncuestaalumno.setMatricula(null);
                    asignacionEncuestaalumnoListOldAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListOldAsignacionEncuestaalumno);
                }
            }
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListNewAsignacionEncuestaalumno : asignacionEncuestaalumnoListNew) {
                if (!asignacionEncuestaalumnoListOld.contains(asignacionEncuestaalumnoListNewAsignacionEncuestaalumno)) {
                    Alumno oldMatriculaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno = asignacionEncuestaalumnoListNewAsignacionEncuestaalumno.getMatricula();
                    asignacionEncuestaalumnoListNewAsignacionEncuestaalumno.setMatricula(alumno);
                    asignacionEncuestaalumnoListNewAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListNewAsignacionEncuestaalumno);
                    if (oldMatriculaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno != null && !oldMatriculaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno.equals(alumno)) {
                        oldMatriculaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno.getAsignacionEncuestaalumnoList().remove(asignacionEncuestaalumnoListNewAsignacionEncuestaalumno);
                        oldMatriculaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno = em.merge(oldMatriculaOfAsignacionEncuestaalumnoListNewAsignacionEncuestaalumno);
                    }
                }
            }
            for (SeguimientoAlumno seguimientoAlumnoListOldSeguimientoAlumno : seguimientoAlumnoListOld) {
                if (!seguimientoAlumnoListNew.contains(seguimientoAlumnoListOldSeguimientoAlumno)) {
                    seguimientoAlumnoListOldSeguimientoAlumno.setMatricula(null);
                    seguimientoAlumnoListOldSeguimientoAlumno = em.merge(seguimientoAlumnoListOldSeguimientoAlumno);
                }
            }
            for (SeguimientoAlumno seguimientoAlumnoListNewSeguimientoAlumno : seguimientoAlumnoListNew) {
                if (!seguimientoAlumnoListOld.contains(seguimientoAlumnoListNewSeguimientoAlumno)) {
                    Alumno oldMatriculaOfSeguimientoAlumnoListNewSeguimientoAlumno = seguimientoAlumnoListNewSeguimientoAlumno.getMatricula();
                    seguimientoAlumnoListNewSeguimientoAlumno.setMatricula(alumno);
                    seguimientoAlumnoListNewSeguimientoAlumno = em.merge(seguimientoAlumnoListNewSeguimientoAlumno);
                    if (oldMatriculaOfSeguimientoAlumnoListNewSeguimientoAlumno != null && !oldMatriculaOfSeguimientoAlumnoListNewSeguimientoAlumno.equals(alumno)) {
                        oldMatriculaOfSeguimientoAlumnoListNewSeguimientoAlumno.getSeguimientoAlumnoList().remove(seguimientoAlumnoListNewSeguimientoAlumno);
                        oldMatriculaOfSeguimientoAlumnoListNewSeguimientoAlumno = em.merge(oldMatriculaOfSeguimientoAlumnoListNewSeguimientoAlumno);
                    }
                }
            }
            for (MovimientoCie movimientoCieListOldMovimientoCie : movimientoCieListOld) {
                if (!movimientoCieListNew.contains(movimientoCieListOldMovimientoCie)) {
                    movimientoCieListOldMovimientoCie.setMatricula(null);
                    movimientoCieListOldMovimientoCie = em.merge(movimientoCieListOldMovimientoCie);
                }
            }
            for (MovimientoCie movimientoCieListNewMovimientoCie : movimientoCieListNew) {
                if (!movimientoCieListOld.contains(movimientoCieListNewMovimientoCie)) {
                    Alumno oldMatriculaOfMovimientoCieListNewMovimientoCie = movimientoCieListNewMovimientoCie.getMatricula();
                    movimientoCieListNewMovimientoCie.setMatricula(alumno);
                    movimientoCieListNewMovimientoCie = em.merge(movimientoCieListNewMovimientoCie);
                    if (oldMatriculaOfMovimientoCieListNewMovimientoCie != null && !oldMatriculaOfMovimientoCieListNewMovimientoCie.equals(alumno)) {
                        oldMatriculaOfMovimientoCieListNewMovimientoCie.getMovimientoCieList().remove(movimientoCieListNewMovimientoCie);
                        oldMatriculaOfMovimientoCieListNewMovimientoCie = em.merge(oldMatriculaOfMovimientoCieListNewMovimientoCie);
                    }
                }
            }
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo : asignacionGrupoexamenextemporaneoListOld) {
                if (!asignacionGrupoexamenextemporaneoListNew.contains(asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo)) {
                    asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo.setMatricula(null);
                    asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListOldAsignacionGrupoexamenextemporaneo);
                }
            }
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo : asignacionGrupoexamenextemporaneoListNew) {
                if (!asignacionGrupoexamenextemporaneoListOld.contains(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo)) {
                    Alumno oldMatriculaOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo = asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.getMatricula();
                    asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.setMatricula(alumno);
                    asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo);
                    if (oldMatriculaOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo != null && !oldMatriculaOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.equals(alumno)) {
                        oldMatriculaOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo.getAsignacionGrupoexamenextemporaneoList().remove(asignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo);
                        oldMatriculaOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo = em.merge(oldMatriculaOfAsignacionGrupoexamenextemporaneoListNewAsignacionGrupoexamenextemporaneo);
                    }
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListOldSolicitudTitulacion : solicitudTitulacionListOld) {
                if (!solicitudTitulacionListNew.contains(solicitudTitulacionListOldSolicitudTitulacion)) {
                    solicitudTitulacionListOldSolicitudTitulacion.setMatricula(null);
                    solicitudTitulacionListOldSolicitudTitulacion = em.merge(solicitudTitulacionListOldSolicitudTitulacion);
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListNewSolicitudTitulacion : solicitudTitulacionListNew) {
                if (!solicitudTitulacionListOld.contains(solicitudTitulacionListNewSolicitudTitulacion)) {
                    Alumno oldMatriculaOfSolicitudTitulacionListNewSolicitudTitulacion = solicitudTitulacionListNewSolicitudTitulacion.getMatricula();
                    solicitudTitulacionListNewSolicitudTitulacion.setMatricula(alumno);
                    solicitudTitulacionListNewSolicitudTitulacion = em.merge(solicitudTitulacionListNewSolicitudTitulacion);
                    if (oldMatriculaOfSolicitudTitulacionListNewSolicitudTitulacion != null && !oldMatriculaOfSolicitudTitulacionListNewSolicitudTitulacion.equals(alumno)) {
                        oldMatriculaOfSolicitudTitulacionListNewSolicitudTitulacion.getSolicitudTitulacionList().remove(solicitudTitulacionListNewSolicitudTitulacion);
                        oldMatriculaOfSolicitudTitulacionListNewSolicitudTitulacion = em.merge(oldMatriculaOfSolicitudTitulacionListNewSolicitudTitulacion);
                    }
                }
            }
            for (ServicioSocial servicioSocialListOldServicioSocial : servicioSocialListOld) {
                if (!servicioSocialListNew.contains(servicioSocialListOldServicioSocial)) {
                    servicioSocialListOldServicioSocial.setMatricula(null);
                    servicioSocialListOldServicioSocial = em.merge(servicioSocialListOldServicioSocial);
                }
            }
            for (ServicioSocial servicioSocialListNewServicioSocial : servicioSocialListNew) {
                if (!servicioSocialListOld.contains(servicioSocialListNewServicioSocial)) {
                    Alumno oldMatriculaOfServicioSocialListNewServicioSocial = servicioSocialListNewServicioSocial.getMatricula();
                    servicioSocialListNewServicioSocial.setMatricula(alumno);
                    servicioSocialListNewServicioSocial = em.merge(servicioSocialListNewServicioSocial);
                    if (oldMatriculaOfServicioSocialListNewServicioSocial != null && !oldMatriculaOfServicioSocialListNewServicioSocial.equals(alumno)) {
                        oldMatriculaOfServicioSocialListNewServicioSocial.getServicioSocialList().remove(servicioSocialListNewServicioSocial);
                        oldMatriculaOfServicioSocialListNewServicioSocial = em.merge(oldMatriculaOfServicioSocialListNewServicioSocial);
                    }
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    facturaListOldFactura.setMatricula(null);
                    facturaListOldFactura = em.merge(facturaListOldFactura);
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Alumno oldMatriculaOfFacturaListNewFactura = facturaListNewFactura.getMatricula();
                    facturaListNewFactura.setMatricula(alumno);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldMatriculaOfFacturaListNewFactura != null && !oldMatriculaOfFacturaListNewFactura.equals(alumno)) {
                        oldMatriculaOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldMatriculaOfFacturaListNewFactura = em.merge(oldMatriculaOfFacturaListNewFactura);
                    }
                }
            }
            for (InscripcionEquivalencia inscripcionEquivalenciaListOldInscripcionEquivalencia : inscripcionEquivalenciaListOld) {
                if (!inscripcionEquivalenciaListNew.contains(inscripcionEquivalenciaListOldInscripcionEquivalencia)) {
                    inscripcionEquivalenciaListOldInscripcionEquivalencia.setMatricula(null);
                    inscripcionEquivalenciaListOldInscripcionEquivalencia = em.merge(inscripcionEquivalenciaListOldInscripcionEquivalencia);
                }
            }
            for (InscripcionEquivalencia inscripcionEquivalenciaListNewInscripcionEquivalencia : inscripcionEquivalenciaListNew) {
                if (!inscripcionEquivalenciaListOld.contains(inscripcionEquivalenciaListNewInscripcionEquivalencia)) {
                    Alumno oldMatriculaOfInscripcionEquivalenciaListNewInscripcionEquivalencia = inscripcionEquivalenciaListNewInscripcionEquivalencia.getMatricula();
                    inscripcionEquivalenciaListNewInscripcionEquivalencia.setMatricula(alumno);
                    inscripcionEquivalenciaListNewInscripcionEquivalencia = em.merge(inscripcionEquivalenciaListNewInscripcionEquivalencia);
                    if (oldMatriculaOfInscripcionEquivalenciaListNewInscripcionEquivalencia != null && !oldMatriculaOfInscripcionEquivalenciaListNewInscripcionEquivalencia.equals(alumno)) {
                        oldMatriculaOfInscripcionEquivalenciaListNewInscripcionEquivalencia.getInscripcionEquivalenciaList().remove(inscripcionEquivalenciaListNewInscripcionEquivalencia);
                        oldMatriculaOfInscripcionEquivalenciaListNewInscripcionEquivalencia = em.merge(oldMatriculaOfInscripcionEquivalenciaListNewInscripcionEquivalencia);
                    }
                }
            }
            for (AsignacionGrupo asignacionGrupoListNewAsignacionGrupo : asignacionGrupoListNew) {
                if (!asignacionGrupoListOld.contains(asignacionGrupoListNewAsignacionGrupo)) {
                    Alumno oldMatriculaOfAsignacionGrupoListNewAsignacionGrupo = asignacionGrupoListNewAsignacionGrupo.getMatricula();
                    asignacionGrupoListNewAsignacionGrupo.setMatricula(alumno);
                    asignacionGrupoListNewAsignacionGrupo = em.merge(asignacionGrupoListNewAsignacionGrupo);
                    if (oldMatriculaOfAsignacionGrupoListNewAsignacionGrupo != null && !oldMatriculaOfAsignacionGrupoListNewAsignacionGrupo.equals(alumno)) {
                        oldMatriculaOfAsignacionGrupoListNewAsignacionGrupo.getAsignacionGrupoList().remove(asignacionGrupoListNewAsignacionGrupo);
                        oldMatriculaOfAsignacionGrupoListNewAsignacionGrupo = em.merge(oldMatriculaOfAsignacionGrupoListNewAsignacionGrupo);
                    }
                }
            }
            for (ControlDocumento controlDocumentoListOldControlDocumento : controlDocumentoListOld) {
                if (!controlDocumentoListNew.contains(controlDocumentoListOldControlDocumento)) {
                    controlDocumentoListOldControlDocumento.setMatricula(null);
                    controlDocumentoListOldControlDocumento = em.merge(controlDocumentoListOldControlDocumento);
                }
            }
            for (ControlDocumento controlDocumentoListNewControlDocumento : controlDocumentoListNew) {
                if (!controlDocumentoListOld.contains(controlDocumentoListNewControlDocumento)) {
                    Alumno oldMatriculaOfControlDocumentoListNewControlDocumento = controlDocumentoListNewControlDocumento.getMatricula();
                    controlDocumentoListNewControlDocumento.setMatricula(alumno);
                    controlDocumentoListNewControlDocumento = em.merge(controlDocumentoListNewControlDocumento);
                    if (oldMatriculaOfControlDocumentoListNewControlDocumento != null && !oldMatriculaOfControlDocumentoListNewControlDocumento.equals(alumno)) {
                        oldMatriculaOfControlDocumentoListNewControlDocumento.getControlDocumentoList().remove(controlDocumentoListNewControlDocumento);
                        oldMatriculaOfControlDocumentoListNewControlDocumento = em.merge(oldMatriculaOfControlDocumentoListNewControlDocumento);
                    }
                }
            }
            for (CompromisoDocumentacion compromisoDocumentacionListOldCompromisoDocumentacion : compromisoDocumentacionListOld) {
                if (!compromisoDocumentacionListNew.contains(compromisoDocumentacionListOldCompromisoDocumentacion)) {
                    compromisoDocumentacionListOldCompromisoDocumentacion.setMatricula(null);
                    compromisoDocumentacionListOldCompromisoDocumentacion = em.merge(compromisoDocumentacionListOldCompromisoDocumentacion);
                }
            }
            for (CompromisoDocumentacion compromisoDocumentacionListNewCompromisoDocumentacion : compromisoDocumentacionListNew) {
                if (!compromisoDocumentacionListOld.contains(compromisoDocumentacionListNewCompromisoDocumentacion)) {
                    Alumno oldMatriculaOfCompromisoDocumentacionListNewCompromisoDocumentacion = compromisoDocumentacionListNewCompromisoDocumentacion.getMatricula();
                    compromisoDocumentacionListNewCompromisoDocumentacion.setMatricula(alumno);
                    compromisoDocumentacionListNewCompromisoDocumentacion = em.merge(compromisoDocumentacionListNewCompromisoDocumentacion);
                    if (oldMatriculaOfCompromisoDocumentacionListNewCompromisoDocumentacion != null && !oldMatriculaOfCompromisoDocumentacionListNewCompromisoDocumentacion.equals(alumno)) {
                        oldMatriculaOfCompromisoDocumentacionListNewCompromisoDocumentacion.getCompromisoDocumentacionList().remove(compromisoDocumentacionListNewCompromisoDocumentacion);
                        oldMatriculaOfCompromisoDocumentacionListNewCompromisoDocumentacion = em.merge(oldMatriculaOfCompromisoDocumentacionListNewCompromisoDocumentacion);
                    }
                }
            }
            for (SolicitudProgramacion solicitudProgramacionListOldSolicitudProgramacion : solicitudProgramacionListOld) {
                if (!solicitudProgramacionListNew.contains(solicitudProgramacionListOldSolicitudProgramacion)) {
                    solicitudProgramacionListOldSolicitudProgramacion.setMatricula(null);
                    solicitudProgramacionListOldSolicitudProgramacion = em.merge(solicitudProgramacionListOldSolicitudProgramacion);
                }
            }
            for (SolicitudProgramacion solicitudProgramacionListNewSolicitudProgramacion : solicitudProgramacionListNew) {
                if (!solicitudProgramacionListOld.contains(solicitudProgramacionListNewSolicitudProgramacion)) {
                    Alumno oldMatriculaOfSolicitudProgramacionListNewSolicitudProgramacion = solicitudProgramacionListNewSolicitudProgramacion.getMatricula();
                    solicitudProgramacionListNewSolicitudProgramacion.setMatricula(alumno);
                    solicitudProgramacionListNewSolicitudProgramacion = em.merge(solicitudProgramacionListNewSolicitudProgramacion);
                    if (oldMatriculaOfSolicitudProgramacionListNewSolicitudProgramacion != null && !oldMatriculaOfSolicitudProgramacionListNewSolicitudProgramacion.equals(alumno)) {
                        oldMatriculaOfSolicitudProgramacionListNewSolicitudProgramacion.getSolicitudProgramacionList().remove(solicitudProgramacionListNewSolicitudProgramacion);
                        oldMatriculaOfSolicitudProgramacionListNewSolicitudProgramacion = em.merge(oldMatriculaOfSolicitudProgramacionListNewSolicitudProgramacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = alumno.getMatricula();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            MovimientoAdeudo movimientoAdeudoOrphanCheck = alumno.getMovimientoAdeudo();
            if (movimientoAdeudoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the MovimientoAdeudo " + movimientoAdeudoOrphanCheck + " in its movimientoAdeudo field has a non-nullable alumno field.");
            }
            List<AsignacionGrupo> asignacionGrupoListOrphanCheck = alumno.getAsignacionGrupoList();
            for (AsignacionGrupo asignacionGrupoListOrphanCheckAsignacionGrupo : asignacionGrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the AsignacionGrupo " + asignacionGrupoListOrphanCheckAsignacionGrupo + " in its asignacionGrupoList field has a non-nullable matricula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoAlumno idTipoalumno = alumno.getIdTipoalumno();
            if (idTipoalumno != null) {
                idTipoalumno.getAlumnoList().remove(alumno);
                idTipoalumno = em.merge(idTipoalumno);
            }
            Aspirante idAspirante = alumno.getIdAspirante();
            if (idAspirante != null) {
                idAspirante.getAlumnoList().remove(alumno);
                idAspirante = em.merge(idAspirante);
            }
            List<Adeudo> adeudoList = alumno.getAdeudoList();
            for (Adeudo adeudoListAdeudo : adeudoList) {
                adeudoListAdeudo.setMatricula(null);
                adeudoListAdeudo = em.merge(adeudoListAdeudo);
            }
            List<Movimientocie> movimientocieList = alumno.getMovimientocieList();
            for (Movimientocie movimientocieListMovimientocie : movimientocieList) {
                movimientocieListMovimientocie.setMatricula(null);
                movimientocieListMovimientocie = em.merge(movimientocieListMovimientocie);
            }
            List<Convenio> convenioList = alumno.getConvenioList();
            for (Convenio convenioListConvenio : convenioList) {
                convenioListConvenio.getAlumnoList().remove(alumno);
                convenioListConvenio = em.merge(convenioListConvenio);
            }
            List<AsignacionEncuestadocente> asignacionEncuestadocenteList = alumno.getAsignacionEncuestadocenteList();
            for (AsignacionEncuestadocente asignacionEncuestadocenteListAsignacionEncuestadocente : asignacionEncuestadocenteList) {
                asignacionEncuestadocenteListAsignacionEncuestadocente.setMatricula(null);
                asignacionEncuestadocenteListAsignacionEncuestadocente = em.merge(asignacionEncuestadocenteListAsignacionEncuestadocente);
            }
            List<AlumnoAsignatura> alumnoAsignaturaList = alumno.getAlumnoAsignaturaList();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : alumnoAsignaturaList) {
                alumnoAsignaturaListAlumnoAsignatura.setMatricula(null);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
            }
            List<AsignacionGrupoinduccion> asignacionGrupoinduccionList = alumno.getAsignacionGrupoinduccionList();
            for (AsignacionGrupoinduccion asignacionGrupoinduccionListAsignacionGrupoinduccion : asignacionGrupoinduccionList) {
                asignacionGrupoinduccionListAsignacionGrupoinduccion.setMatricula(null);
                asignacionGrupoinduccionListAsignacionGrupoinduccion = em.merge(asignacionGrupoinduccionListAsignacionGrupoinduccion);
            }
            List<ExamenExtemporaneo> examenExtemporaneoList = alumno.getExamenExtemporaneoList();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : examenExtemporaneoList) {
                examenExtemporaneoListExamenExtemporaneo.setMatricula(null);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
            }
            List<SolicitudBaja> solicitudBajaList = alumno.getSolicitudBajaList();
            for (SolicitudBaja solicitudBajaListSolicitudBaja : solicitudBajaList) {
                solicitudBajaListSolicitudBaja.setMatricula(null);
                solicitudBajaListSolicitudBaja = em.merge(solicitudBajaListSolicitudBaja);
            }
            List<ResultadoEvaluacion> resultadoEvaluacionList = alumno.getResultadoEvaluacionList();
            for (ResultadoEvaluacion resultadoEvaluacionListResultadoEvaluacion : resultadoEvaluacionList) {
                resultadoEvaluacionListResultadoEvaluacion.setMatricula(null);
                resultadoEvaluacionListResultadoEvaluacion = em.merge(resultadoEvaluacionListResultadoEvaluacion);
            }
            List<AsignacionEncuestaalumno> asignacionEncuestaalumnoList = alumno.getAsignacionEncuestaalumnoList();
            for (AsignacionEncuestaalumno asignacionEncuestaalumnoListAsignacionEncuestaalumno : asignacionEncuestaalumnoList) {
                asignacionEncuestaalumnoListAsignacionEncuestaalumno.setMatricula(null);
                asignacionEncuestaalumnoListAsignacionEncuestaalumno = em.merge(asignacionEncuestaalumnoListAsignacionEncuestaalumno);
            }
            List<SeguimientoAlumno> seguimientoAlumnoList = alumno.getSeguimientoAlumnoList();
            for (SeguimientoAlumno seguimientoAlumnoListSeguimientoAlumno : seguimientoAlumnoList) {
                seguimientoAlumnoListSeguimientoAlumno.setMatricula(null);
                seguimientoAlumnoListSeguimientoAlumno = em.merge(seguimientoAlumnoListSeguimientoAlumno);
            }
            List<MovimientoCie> movimientoCieList = alumno.getMovimientoCieList();
            for (MovimientoCie movimientoCieListMovimientoCie : movimientoCieList) {
                movimientoCieListMovimientoCie.setMatricula(null);
                movimientoCieListMovimientoCie = em.merge(movimientoCieListMovimientoCie);
            }
            List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoList = alumno.getAsignacionGrupoexamenextemporaneoList();
            for (AsignacionGrupoexamenextemporaneo asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo : asignacionGrupoexamenextemporaneoList) {
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo.setMatricula(null);
                asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo = em.merge(asignacionGrupoexamenextemporaneoListAsignacionGrupoexamenextemporaneo);
            }
            List<SolicitudTitulacion> solicitudTitulacionList = alumno.getSolicitudTitulacionList();
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacion : solicitudTitulacionList) {
                solicitudTitulacionListSolicitudTitulacion.setMatricula(null);
                solicitudTitulacionListSolicitudTitulacion = em.merge(solicitudTitulacionListSolicitudTitulacion);
            }
            List<ServicioSocial> servicioSocialList = alumno.getServicioSocialList();
            for (ServicioSocial servicioSocialListServicioSocial : servicioSocialList) {
                servicioSocialListServicioSocial.setMatricula(null);
                servicioSocialListServicioSocial = em.merge(servicioSocialListServicioSocial);
            }
            List<Factura> facturaList = alumno.getFacturaList();
            for (Factura facturaListFactura : facturaList) {
                facturaListFactura.setMatricula(null);
                facturaListFactura = em.merge(facturaListFactura);
            }
            List<InscripcionEquivalencia> inscripcionEquivalenciaList = alumno.getInscripcionEquivalenciaList();
            for (InscripcionEquivalencia inscripcionEquivalenciaListInscripcionEquivalencia : inscripcionEquivalenciaList) {
                inscripcionEquivalenciaListInscripcionEquivalencia.setMatricula(null);
                inscripcionEquivalenciaListInscripcionEquivalencia = em.merge(inscripcionEquivalenciaListInscripcionEquivalencia);
            }
            List<ControlDocumento> controlDocumentoList = alumno.getControlDocumentoList();
            for (ControlDocumento controlDocumentoListControlDocumento : controlDocumentoList) {
                controlDocumentoListControlDocumento.setMatricula(null);
                controlDocumentoListControlDocumento = em.merge(controlDocumentoListControlDocumento);
            }
            List<CompromisoDocumentacion> compromisoDocumentacionList = alumno.getCompromisoDocumentacionList();
            for (CompromisoDocumentacion compromisoDocumentacionListCompromisoDocumentacion : compromisoDocumentacionList) {
                compromisoDocumentacionListCompromisoDocumentacion.setMatricula(null);
                compromisoDocumentacionListCompromisoDocumentacion = em.merge(compromisoDocumentacionListCompromisoDocumentacion);
            }
            List<SolicitudProgramacion> solicitudProgramacionList = alumno.getSolicitudProgramacionList();
            for (SolicitudProgramacion solicitudProgramacionListSolicitudProgramacion : solicitudProgramacionList) {
                solicitudProgramacionListSolicitudProgramacion.setMatricula(null);
                solicitudProgramacionListSolicitudProgramacion = em.merge(solicitudProgramacionListSolicitudProgramacion);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Alumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Alumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
