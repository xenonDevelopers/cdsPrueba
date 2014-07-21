/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.TipoMovimiento;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.ExperienciaLaboral;
import com.utez.integracion.entity.Idioma;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.DestinatarioNotificacion;
import com.utez.secretariaAcademica.entity.Administrativo;
import com.utez.integracion.entity.AsignacionRecursoplantel;
import com.utez.integracion.entity.SeguimientoAlumno;
import com.utez.integracion.entity.OfertaEvento;
import com.utez.secretariaAcademica.entity.Asesor;
import com.utez.integracion.entity.Aplicador;
import com.utez.integracion.entity.Curso;
import com.utez.integracion.entity.ControlDocumento;
import com.utez.integracion.entity.FormacionAcademica;
import com.utez.integracion.entity.AsignacionRecursohumanodocumento;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.secretariaAcademica.dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RecursoHumanoJpaController implements Serializable {

    public RecursoHumanoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecursoHumano recursoHumano) {
        if (recursoHumano.getTipoMovimientoList() == null) {
            recursoHumano.setTipoMovimientoList(new ArrayList<TipoMovimiento>());
        }
        if (recursoHumano.getExperienciaLaboralList() == null) {
            recursoHumano.setExperienciaLaboralList(new ArrayList<ExperienciaLaboral>());
        }
        if (recursoHumano.getIdiomaList() == null) {
            recursoHumano.setIdiomaList(new ArrayList<Idioma>());
        }
        if (recursoHumano.getActaList() == null) {
            recursoHumano.setActaList(new ArrayList<Acta>());
        }
        if (recursoHumano.getDestinatarioNotificacionList() == null) {
            recursoHumano.setDestinatarioNotificacionList(new ArrayList<DestinatarioNotificacion>());
        }
        if (recursoHumano.getAdministrativoList() == null) {
            recursoHumano.setAdministrativoList(new ArrayList<Administrativo>());
        }
        if (recursoHumano.getAsignacionRecursoplantelList() == null) {
            recursoHumano.setAsignacionRecursoplantelList(new ArrayList<AsignacionRecursoplantel>());
        }
        if (recursoHumano.getSeguimientoAlumnoList() == null) {
            recursoHumano.setSeguimientoAlumnoList(new ArrayList<SeguimientoAlumno>());
        }
        if (recursoHumano.getOfertaEventoList() == null) {
            recursoHumano.setOfertaEventoList(new ArrayList<OfertaEvento>());
        }
        if (recursoHumano.getAsesorList() == null) {
            recursoHumano.setAsesorList(new ArrayList<Asesor>());
        }
        if (recursoHumano.getAplicadorList() == null) {
            recursoHumano.setAplicadorList(new ArrayList<Aplicador>());
        }
        if (recursoHumano.getCursoList() == null) {
            recursoHumano.setCursoList(new ArrayList<Curso>());
        }
        if (recursoHumano.getControlDocumentoList() == null) {
            recursoHumano.setControlDocumentoList(new ArrayList<ControlDocumento>());
        }
        if (recursoHumano.getFormacionAcademicaList() == null) {
            recursoHumano.setFormacionAcademicaList(new ArrayList<FormacionAcademica>());
        }
        if (recursoHumano.getAsignacionRecursohumanodocumentoList() == null) {
            recursoHumano.setAsignacionRecursohumanodocumentoList(new ArrayList<AsignacionRecursohumanodocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona curp = recursoHumano.getCurp();
            if (curp != null) {
                curp = em.getReference(curp.getClass(), curp.getCurp());
                recursoHumano.setCurp(curp);
            }
            List<TipoMovimiento> attachedTipoMovimientoList = new ArrayList<TipoMovimiento>();
            for (TipoMovimiento tipoMovimientoListTipoMovimientoToAttach : recursoHumano.getTipoMovimientoList()) {
                tipoMovimientoListTipoMovimientoToAttach = em.getReference(tipoMovimientoListTipoMovimientoToAttach.getClass(), tipoMovimientoListTipoMovimientoToAttach.getIdTipomovimiento());
                attachedTipoMovimientoList.add(tipoMovimientoListTipoMovimientoToAttach);
            }
            recursoHumano.setTipoMovimientoList(attachedTipoMovimientoList);
            List<ExperienciaLaboral> attachedExperienciaLaboralList = new ArrayList<ExperienciaLaboral>();
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboralToAttach : recursoHumano.getExperienciaLaboralList()) {
                experienciaLaboralListExperienciaLaboralToAttach = em.getReference(experienciaLaboralListExperienciaLaboralToAttach.getClass(), experienciaLaboralListExperienciaLaboralToAttach.getIdExperiencialaboral());
                attachedExperienciaLaboralList.add(experienciaLaboralListExperienciaLaboralToAttach);
            }
            recursoHumano.setExperienciaLaboralList(attachedExperienciaLaboralList);
            List<Idioma> attachedIdiomaList = new ArrayList<Idioma>();
            for (Idioma idiomaListIdiomaToAttach : recursoHumano.getIdiomaList()) {
                idiomaListIdiomaToAttach = em.getReference(idiomaListIdiomaToAttach.getClass(), idiomaListIdiomaToAttach.getIdIdioma());
                attachedIdiomaList.add(idiomaListIdiomaToAttach);
            }
            recursoHumano.setIdiomaList(attachedIdiomaList);
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : recursoHumano.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            recursoHumano.setActaList(attachedActaList);
            List<DestinatarioNotificacion> attachedDestinatarioNotificacionList = new ArrayList<DestinatarioNotificacion>();
            for (DestinatarioNotificacion destinatarioNotificacionListDestinatarioNotificacionToAttach : recursoHumano.getDestinatarioNotificacionList()) {
                destinatarioNotificacionListDestinatarioNotificacionToAttach = em.getReference(destinatarioNotificacionListDestinatarioNotificacionToAttach.getClass(), destinatarioNotificacionListDestinatarioNotificacionToAttach.getIdDestinatarionotificacion());
                attachedDestinatarioNotificacionList.add(destinatarioNotificacionListDestinatarioNotificacionToAttach);
            }
            recursoHumano.setDestinatarioNotificacionList(attachedDestinatarioNotificacionList);
            List<Administrativo> attachedAdministrativoList = new ArrayList<Administrativo>();
            for (Administrativo administrativoListAdministrativoToAttach : recursoHumano.getAdministrativoList()) {
                administrativoListAdministrativoToAttach = em.getReference(administrativoListAdministrativoToAttach.getClass(), administrativoListAdministrativoToAttach.getIdadministrativo());
                attachedAdministrativoList.add(administrativoListAdministrativoToAttach);
            }
            recursoHumano.setAdministrativoList(attachedAdministrativoList);
            List<AsignacionRecursoplantel> attachedAsignacionRecursoplantelList = new ArrayList<AsignacionRecursoplantel>();
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantelToAttach : recursoHumano.getAsignacionRecursoplantelList()) {
                asignacionRecursoplantelListAsignacionRecursoplantelToAttach = em.getReference(asignacionRecursoplantelListAsignacionRecursoplantelToAttach.getClass(), asignacionRecursoplantelListAsignacionRecursoplantelToAttach.getIdAsignacionrecursoplantel());
                attachedAsignacionRecursoplantelList.add(asignacionRecursoplantelListAsignacionRecursoplantelToAttach);
            }
            recursoHumano.setAsignacionRecursoplantelList(attachedAsignacionRecursoplantelList);
            List<SeguimientoAlumno> attachedSeguimientoAlumnoList = new ArrayList<SeguimientoAlumno>();
            for (SeguimientoAlumno seguimientoAlumnoListSeguimientoAlumnoToAttach : recursoHumano.getSeguimientoAlumnoList()) {
                seguimientoAlumnoListSeguimientoAlumnoToAttach = em.getReference(seguimientoAlumnoListSeguimientoAlumnoToAttach.getClass(), seguimientoAlumnoListSeguimientoAlumnoToAttach.getIdSeguimiento());
                attachedSeguimientoAlumnoList.add(seguimientoAlumnoListSeguimientoAlumnoToAttach);
            }
            recursoHumano.setSeguimientoAlumnoList(attachedSeguimientoAlumnoList);
            List<OfertaEvento> attachedOfertaEventoList = new ArrayList<OfertaEvento>();
            for (OfertaEvento ofertaEventoListOfertaEventoToAttach : recursoHumano.getOfertaEventoList()) {
                ofertaEventoListOfertaEventoToAttach = em.getReference(ofertaEventoListOfertaEventoToAttach.getClass(), ofertaEventoListOfertaEventoToAttach.getIdOfertaevento());
                attachedOfertaEventoList.add(ofertaEventoListOfertaEventoToAttach);
            }
            recursoHumano.setOfertaEventoList(attachedOfertaEventoList);
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : recursoHumano.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getIdasesor());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            recursoHumano.setAsesorList(attachedAsesorList);
            List<Aplicador> attachedAplicadorList = new ArrayList<Aplicador>();
            for (Aplicador aplicadorListAplicadorToAttach : recursoHumano.getAplicadorList()) {
                aplicadorListAplicadorToAttach = em.getReference(aplicadorListAplicadorToAttach.getClass(), aplicadorListAplicadorToAttach.getIdAplicador());
                attachedAplicadorList.add(aplicadorListAplicadorToAttach);
            }
            recursoHumano.setAplicadorList(attachedAplicadorList);
            List<Curso> attachedCursoList = new ArrayList<Curso>();
            for (Curso cursoListCursoToAttach : recursoHumano.getCursoList()) {
                cursoListCursoToAttach = em.getReference(cursoListCursoToAttach.getClass(), cursoListCursoToAttach.getIdCurso());
                attachedCursoList.add(cursoListCursoToAttach);
            }
            recursoHumano.setCursoList(attachedCursoList);
            List<ControlDocumento> attachedControlDocumentoList = new ArrayList<ControlDocumento>();
            for (ControlDocumento controlDocumentoListControlDocumentoToAttach : recursoHumano.getControlDocumentoList()) {
                controlDocumentoListControlDocumentoToAttach = em.getReference(controlDocumentoListControlDocumentoToAttach.getClass(), controlDocumentoListControlDocumentoToAttach.getIdControldocumento());
                attachedControlDocumentoList.add(controlDocumentoListControlDocumentoToAttach);
            }
            recursoHumano.setControlDocumentoList(attachedControlDocumentoList);
            List<FormacionAcademica> attachedFormacionAcademicaList = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListFormacionAcademicaToAttach : recursoHumano.getFormacionAcademicaList()) {
                formacionAcademicaListFormacionAcademicaToAttach = em.getReference(formacionAcademicaListFormacionAcademicaToAttach.getClass(), formacionAcademicaListFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaList.add(formacionAcademicaListFormacionAcademicaToAttach);
            }
            recursoHumano.setFormacionAcademicaList(attachedFormacionAcademicaList);
            List<AsignacionRecursohumanodocumento> attachedAsignacionRecursohumanodocumentoList = new ArrayList<AsignacionRecursohumanodocumento>();
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach : recursoHumano.getAsignacionRecursohumanodocumentoList()) {
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach = em.getReference(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach.getClass(), asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach.getIdAsignacionrecursohumanodocumento());
                attachedAsignacionRecursohumanodocumentoList.add(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach);
            }
            recursoHumano.setAsignacionRecursohumanodocumentoList(attachedAsignacionRecursohumanodocumentoList);
            em.persist(recursoHumano);
            if (curp != null) {
                curp.getRecursoHumanoList().add(recursoHumano);
                curp = em.merge(curp);
            }
            for (TipoMovimiento tipoMovimientoListTipoMovimiento : recursoHumano.getTipoMovimientoList()) {
                tipoMovimientoListTipoMovimiento.getRecursoHumanoList().add(recursoHumano);
                tipoMovimientoListTipoMovimiento = em.merge(tipoMovimientoListTipoMovimiento);
            }
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboral : recursoHumano.getExperienciaLaboralList()) {
                RecursoHumano oldIdRecursohumanoOfExperienciaLaboralListExperienciaLaboral = experienciaLaboralListExperienciaLaboral.getIdRecursohumano();
                experienciaLaboralListExperienciaLaboral.setIdRecursohumano(recursoHumano);
                experienciaLaboralListExperienciaLaboral = em.merge(experienciaLaboralListExperienciaLaboral);
                if (oldIdRecursohumanoOfExperienciaLaboralListExperienciaLaboral != null) {
                    oldIdRecursohumanoOfExperienciaLaboralListExperienciaLaboral.getExperienciaLaboralList().remove(experienciaLaboralListExperienciaLaboral);
                    oldIdRecursohumanoOfExperienciaLaboralListExperienciaLaboral = em.merge(oldIdRecursohumanoOfExperienciaLaboralListExperienciaLaboral);
                }
            }
            for (Idioma idiomaListIdioma : recursoHumano.getIdiomaList()) {
                RecursoHumano oldIdRecursohumanoOfIdiomaListIdioma = idiomaListIdioma.getIdRecursohumano();
                idiomaListIdioma.setIdRecursohumano(recursoHumano);
                idiomaListIdioma = em.merge(idiomaListIdioma);
                if (oldIdRecursohumanoOfIdiomaListIdioma != null) {
                    oldIdRecursohumanoOfIdiomaListIdioma.getIdiomaList().remove(idiomaListIdioma);
                    oldIdRecursohumanoOfIdiomaListIdioma = em.merge(oldIdRecursohumanoOfIdiomaListIdioma);
                }
            }
            for (Acta actaListActa : recursoHumano.getActaList()) {
                RecursoHumano oldResponsableActaOfActaListActa = actaListActa.getResponsableActa();
                actaListActa.setResponsableActa(recursoHumano);
                actaListActa = em.merge(actaListActa);
                if (oldResponsableActaOfActaListActa != null) {
                    oldResponsableActaOfActaListActa.getActaList().remove(actaListActa);
                    oldResponsableActaOfActaListActa = em.merge(oldResponsableActaOfActaListActa);
                }
            }
            for (DestinatarioNotificacion destinatarioNotificacionListDestinatarioNotificacion : recursoHumano.getDestinatarioNotificacionList()) {
                RecursoHumano oldIdRecursohumanoOfDestinatarioNotificacionListDestinatarioNotificacion = destinatarioNotificacionListDestinatarioNotificacion.getIdRecursohumano();
                destinatarioNotificacionListDestinatarioNotificacion.setIdRecursohumano(recursoHumano);
                destinatarioNotificacionListDestinatarioNotificacion = em.merge(destinatarioNotificacionListDestinatarioNotificacion);
                if (oldIdRecursohumanoOfDestinatarioNotificacionListDestinatarioNotificacion != null) {
                    oldIdRecursohumanoOfDestinatarioNotificacionListDestinatarioNotificacion.getDestinatarioNotificacionList().remove(destinatarioNotificacionListDestinatarioNotificacion);
                    oldIdRecursohumanoOfDestinatarioNotificacionListDestinatarioNotificacion = em.merge(oldIdRecursohumanoOfDestinatarioNotificacionListDestinatarioNotificacion);
                }
            }
            for (Administrativo administrativoListAdministrativo : recursoHumano.getAdministrativoList()) {
                RecursoHumano oldIdRecursohumanoOfAdministrativoListAdministrativo = administrativoListAdministrativo.getIdRecursohumano();
                administrativoListAdministrativo.setIdRecursohumano(recursoHumano);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
                if (oldIdRecursohumanoOfAdministrativoListAdministrativo != null) {
                    oldIdRecursohumanoOfAdministrativoListAdministrativo.getAdministrativoList().remove(administrativoListAdministrativo);
                    oldIdRecursohumanoOfAdministrativoListAdministrativo = em.merge(oldIdRecursohumanoOfAdministrativoListAdministrativo);
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantel : recursoHumano.getAsignacionRecursoplantelList()) {
                RecursoHumano oldIdRecursohumanoOfAsignacionRecursoplantelListAsignacionRecursoplantel = asignacionRecursoplantelListAsignacionRecursoplantel.getIdRecursohumano();
                asignacionRecursoplantelListAsignacionRecursoplantel.setIdRecursohumano(recursoHumano);
                asignacionRecursoplantelListAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListAsignacionRecursoplantel);
                if (oldIdRecursohumanoOfAsignacionRecursoplantelListAsignacionRecursoplantel != null) {
                    oldIdRecursohumanoOfAsignacionRecursoplantelListAsignacionRecursoplantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantelListAsignacionRecursoplantel);
                    oldIdRecursohumanoOfAsignacionRecursoplantelListAsignacionRecursoplantel = em.merge(oldIdRecursohumanoOfAsignacionRecursoplantelListAsignacionRecursoplantel);
                }
            }
            for (SeguimientoAlumno seguimientoAlumnoListSeguimientoAlumno : recursoHumano.getSeguimientoAlumnoList()) {
                RecursoHumano oldIdResponsableOfSeguimientoAlumnoListSeguimientoAlumno = seguimientoAlumnoListSeguimientoAlumno.getIdResponsable();
                seguimientoAlumnoListSeguimientoAlumno.setIdResponsable(recursoHumano);
                seguimientoAlumnoListSeguimientoAlumno = em.merge(seguimientoAlumnoListSeguimientoAlumno);
                if (oldIdResponsableOfSeguimientoAlumnoListSeguimientoAlumno != null) {
                    oldIdResponsableOfSeguimientoAlumnoListSeguimientoAlumno.getSeguimientoAlumnoList().remove(seguimientoAlumnoListSeguimientoAlumno);
                    oldIdResponsableOfSeguimientoAlumnoListSeguimientoAlumno = em.merge(oldIdResponsableOfSeguimientoAlumnoListSeguimientoAlumno);
                }
            }
            for (OfertaEvento ofertaEventoListOfertaEvento : recursoHumano.getOfertaEventoList()) {
                RecursoHumano oldIdInstructorOfOfertaEventoListOfertaEvento = ofertaEventoListOfertaEvento.getIdInstructor();
                ofertaEventoListOfertaEvento.setIdInstructor(recursoHumano);
                ofertaEventoListOfertaEvento = em.merge(ofertaEventoListOfertaEvento);
                if (oldIdInstructorOfOfertaEventoListOfertaEvento != null) {
                    oldIdInstructorOfOfertaEventoListOfertaEvento.getOfertaEventoList().remove(ofertaEventoListOfertaEvento);
                    oldIdInstructorOfOfertaEventoListOfertaEvento = em.merge(oldIdInstructorOfOfertaEventoListOfertaEvento);
                }
            }
            for (Asesor asesorListAsesor : recursoHumano.getAsesorList()) {
                RecursoHumano oldIdRecursohumanoOfAsesorListAsesor = asesorListAsesor.getIdRecursohumano();
                asesorListAsesor.setIdRecursohumano(recursoHumano);
                asesorListAsesor = em.merge(asesorListAsesor);
                if (oldIdRecursohumanoOfAsesorListAsesor != null) {
                    oldIdRecursohumanoOfAsesorListAsesor.getAsesorList().remove(asesorListAsesor);
                    oldIdRecursohumanoOfAsesorListAsesor = em.merge(oldIdRecursohumanoOfAsesorListAsesor);
                }
            }
            for (Aplicador aplicadorListAplicador : recursoHumano.getAplicadorList()) {
                RecursoHumano oldIdRecursohumanoOfAplicadorListAplicador = aplicadorListAplicador.getIdRecursohumano();
                aplicadorListAplicador.setIdRecursohumano(recursoHumano);
                aplicadorListAplicador = em.merge(aplicadorListAplicador);
                if (oldIdRecursohumanoOfAplicadorListAplicador != null) {
                    oldIdRecursohumanoOfAplicadorListAplicador.getAplicadorList().remove(aplicadorListAplicador);
                    oldIdRecursohumanoOfAplicadorListAplicador = em.merge(oldIdRecursohumanoOfAplicadorListAplicador);
                }
            }
            for (Curso cursoListCurso : recursoHumano.getCursoList()) {
                RecursoHumano oldIdRecursohumanoOfCursoListCurso = cursoListCurso.getIdRecursohumano();
                cursoListCurso.setIdRecursohumano(recursoHumano);
                cursoListCurso = em.merge(cursoListCurso);
                if (oldIdRecursohumanoOfCursoListCurso != null) {
                    oldIdRecursohumanoOfCursoListCurso.getCursoList().remove(cursoListCurso);
                    oldIdRecursohumanoOfCursoListCurso = em.merge(oldIdRecursohumanoOfCursoListCurso);
                }
            }
            for (ControlDocumento controlDocumentoListControlDocumento : recursoHumano.getControlDocumentoList()) {
                RecursoHumano oldIdRecursohumanoOfControlDocumentoListControlDocumento = controlDocumentoListControlDocumento.getIdRecursohumano();
                controlDocumentoListControlDocumento.setIdRecursohumano(recursoHumano);
                controlDocumentoListControlDocumento = em.merge(controlDocumentoListControlDocumento);
                if (oldIdRecursohumanoOfControlDocumentoListControlDocumento != null) {
                    oldIdRecursohumanoOfControlDocumentoListControlDocumento.getControlDocumentoList().remove(controlDocumentoListControlDocumento);
                    oldIdRecursohumanoOfControlDocumentoListControlDocumento = em.merge(oldIdRecursohumanoOfControlDocumentoListControlDocumento);
                }
            }
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : recursoHumano.getFormacionAcademicaList()) {
                RecursoHumano oldIdRecursohumanoOfFormacionAcademicaListFormacionAcademica = formacionAcademicaListFormacionAcademica.getIdRecursohumano();
                formacionAcademicaListFormacionAcademica.setIdRecursohumano(recursoHumano);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
                if (oldIdRecursohumanoOfFormacionAcademicaListFormacionAcademica != null) {
                    oldIdRecursohumanoOfFormacionAcademicaListFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListFormacionAcademica);
                    oldIdRecursohumanoOfFormacionAcademicaListFormacionAcademica = em.merge(oldIdRecursohumanoOfFormacionAcademicaListFormacionAcademica);
                }
            }
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento : recursoHumano.getAsignacionRecursohumanodocumentoList()) {
                RecursoHumano oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.getIdRecursohumano();
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.setIdRecursohumano(recursoHumano);
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
                if (oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento != null) {
                    oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
                    oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = em.merge(oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecursoHumano recursoHumano) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano persistentRecursoHumano = em.find(RecursoHumano.class, recursoHumano.getIdRecursohumano());
            Persona curpOld = persistentRecursoHumano.getCurp();
            Persona curpNew = recursoHumano.getCurp();
            List<TipoMovimiento> tipoMovimientoListOld = persistentRecursoHumano.getTipoMovimientoList();
            List<TipoMovimiento> tipoMovimientoListNew = recursoHumano.getTipoMovimientoList();
            List<ExperienciaLaboral> experienciaLaboralListOld = persistentRecursoHumano.getExperienciaLaboralList();
            List<ExperienciaLaboral> experienciaLaboralListNew = recursoHumano.getExperienciaLaboralList();
            List<Idioma> idiomaListOld = persistentRecursoHumano.getIdiomaList();
            List<Idioma> idiomaListNew = recursoHumano.getIdiomaList();
            List<Acta> actaListOld = persistentRecursoHumano.getActaList();
            List<Acta> actaListNew = recursoHumano.getActaList();
            List<DestinatarioNotificacion> destinatarioNotificacionListOld = persistentRecursoHumano.getDestinatarioNotificacionList();
            List<DestinatarioNotificacion> destinatarioNotificacionListNew = recursoHumano.getDestinatarioNotificacionList();
            List<Administrativo> administrativoListOld = persistentRecursoHumano.getAdministrativoList();
            List<Administrativo> administrativoListNew = recursoHumano.getAdministrativoList();
            List<AsignacionRecursoplantel> asignacionRecursoplantelListOld = persistentRecursoHumano.getAsignacionRecursoplantelList();
            List<AsignacionRecursoplantel> asignacionRecursoplantelListNew = recursoHumano.getAsignacionRecursoplantelList();
            List<SeguimientoAlumno> seguimientoAlumnoListOld = persistentRecursoHumano.getSeguimientoAlumnoList();
            List<SeguimientoAlumno> seguimientoAlumnoListNew = recursoHumano.getSeguimientoAlumnoList();
            List<OfertaEvento> ofertaEventoListOld = persistentRecursoHumano.getOfertaEventoList();
            List<OfertaEvento> ofertaEventoListNew = recursoHumano.getOfertaEventoList();
            List<Asesor> asesorListOld = persistentRecursoHumano.getAsesorList();
            List<Asesor> asesorListNew = recursoHumano.getAsesorList();
            List<Aplicador> aplicadorListOld = persistentRecursoHumano.getAplicadorList();
            List<Aplicador> aplicadorListNew = recursoHumano.getAplicadorList();
            List<Curso> cursoListOld = persistentRecursoHumano.getCursoList();
            List<Curso> cursoListNew = recursoHumano.getCursoList();
            List<ControlDocumento> controlDocumentoListOld = persistentRecursoHumano.getControlDocumentoList();
            List<ControlDocumento> controlDocumentoListNew = recursoHumano.getControlDocumentoList();
            List<FormacionAcademica> formacionAcademicaListOld = persistentRecursoHumano.getFormacionAcademicaList();
            List<FormacionAcademica> formacionAcademicaListNew = recursoHumano.getFormacionAcademicaList();
            List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoListOld = persistentRecursoHumano.getAsignacionRecursohumanodocumentoList();
            List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoListNew = recursoHumano.getAsignacionRecursohumanodocumentoList();
            if (curpNew != null) {
                curpNew = em.getReference(curpNew.getClass(), curpNew.getCurp());
                recursoHumano.setCurp(curpNew);
            }
            List<TipoMovimiento> attachedTipoMovimientoListNew = new ArrayList<TipoMovimiento>();
            for (TipoMovimiento tipoMovimientoListNewTipoMovimientoToAttach : tipoMovimientoListNew) {
                tipoMovimientoListNewTipoMovimientoToAttach = em.getReference(tipoMovimientoListNewTipoMovimientoToAttach.getClass(), tipoMovimientoListNewTipoMovimientoToAttach.getIdTipomovimiento());
                attachedTipoMovimientoListNew.add(tipoMovimientoListNewTipoMovimientoToAttach);
            }
            tipoMovimientoListNew = attachedTipoMovimientoListNew;
            recursoHumano.setTipoMovimientoList(tipoMovimientoListNew);
            List<ExperienciaLaboral> attachedExperienciaLaboralListNew = new ArrayList<ExperienciaLaboral>();
            for (ExperienciaLaboral experienciaLaboralListNewExperienciaLaboralToAttach : experienciaLaboralListNew) {
                experienciaLaboralListNewExperienciaLaboralToAttach = em.getReference(experienciaLaboralListNewExperienciaLaboralToAttach.getClass(), experienciaLaboralListNewExperienciaLaboralToAttach.getIdExperiencialaboral());
                attachedExperienciaLaboralListNew.add(experienciaLaboralListNewExperienciaLaboralToAttach);
            }
            experienciaLaboralListNew = attachedExperienciaLaboralListNew;
            recursoHumano.setExperienciaLaboralList(experienciaLaboralListNew);
            List<Idioma> attachedIdiomaListNew = new ArrayList<Idioma>();
            for (Idioma idiomaListNewIdiomaToAttach : idiomaListNew) {
                idiomaListNewIdiomaToAttach = em.getReference(idiomaListNewIdiomaToAttach.getClass(), idiomaListNewIdiomaToAttach.getIdIdioma());
                attachedIdiomaListNew.add(idiomaListNewIdiomaToAttach);
            }
            idiomaListNew = attachedIdiomaListNew;
            recursoHumano.setIdiomaList(idiomaListNew);
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            recursoHumano.setActaList(actaListNew);
            List<DestinatarioNotificacion> attachedDestinatarioNotificacionListNew = new ArrayList<DestinatarioNotificacion>();
            for (DestinatarioNotificacion destinatarioNotificacionListNewDestinatarioNotificacionToAttach : destinatarioNotificacionListNew) {
                destinatarioNotificacionListNewDestinatarioNotificacionToAttach = em.getReference(destinatarioNotificacionListNewDestinatarioNotificacionToAttach.getClass(), destinatarioNotificacionListNewDestinatarioNotificacionToAttach.getIdDestinatarionotificacion());
                attachedDestinatarioNotificacionListNew.add(destinatarioNotificacionListNewDestinatarioNotificacionToAttach);
            }
            destinatarioNotificacionListNew = attachedDestinatarioNotificacionListNew;
            recursoHumano.setDestinatarioNotificacionList(destinatarioNotificacionListNew);
            List<Administrativo> attachedAdministrativoListNew = new ArrayList<Administrativo>();
            for (Administrativo administrativoListNewAdministrativoToAttach : administrativoListNew) {
                administrativoListNewAdministrativoToAttach = em.getReference(administrativoListNewAdministrativoToAttach.getClass(), administrativoListNewAdministrativoToAttach.getIdadministrativo());
                attachedAdministrativoListNew.add(administrativoListNewAdministrativoToAttach);
            }
            administrativoListNew = attachedAdministrativoListNew;
            recursoHumano.setAdministrativoList(administrativoListNew);
            List<AsignacionRecursoplantel> attachedAsignacionRecursoplantelListNew = new ArrayList<AsignacionRecursoplantel>();
            for (AsignacionRecursoplantel asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach : asignacionRecursoplantelListNew) {
                asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach = em.getReference(asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach.getClass(), asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach.getIdAsignacionrecursoplantel());
                attachedAsignacionRecursoplantelListNew.add(asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach);
            }
            asignacionRecursoplantelListNew = attachedAsignacionRecursoplantelListNew;
            recursoHumano.setAsignacionRecursoplantelList(asignacionRecursoplantelListNew);
            List<SeguimientoAlumno> attachedSeguimientoAlumnoListNew = new ArrayList<SeguimientoAlumno>();
            for (SeguimientoAlumno seguimientoAlumnoListNewSeguimientoAlumnoToAttach : seguimientoAlumnoListNew) {
                seguimientoAlumnoListNewSeguimientoAlumnoToAttach = em.getReference(seguimientoAlumnoListNewSeguimientoAlumnoToAttach.getClass(), seguimientoAlumnoListNewSeguimientoAlumnoToAttach.getIdSeguimiento());
                attachedSeguimientoAlumnoListNew.add(seguimientoAlumnoListNewSeguimientoAlumnoToAttach);
            }
            seguimientoAlumnoListNew = attachedSeguimientoAlumnoListNew;
            recursoHumano.setSeguimientoAlumnoList(seguimientoAlumnoListNew);
            List<OfertaEvento> attachedOfertaEventoListNew = new ArrayList<OfertaEvento>();
            for (OfertaEvento ofertaEventoListNewOfertaEventoToAttach : ofertaEventoListNew) {
                ofertaEventoListNewOfertaEventoToAttach = em.getReference(ofertaEventoListNewOfertaEventoToAttach.getClass(), ofertaEventoListNewOfertaEventoToAttach.getIdOfertaevento());
                attachedOfertaEventoListNew.add(ofertaEventoListNewOfertaEventoToAttach);
            }
            ofertaEventoListNew = attachedOfertaEventoListNew;
            recursoHumano.setOfertaEventoList(ofertaEventoListNew);
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getIdasesor());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            recursoHumano.setAsesorList(asesorListNew);
            List<Aplicador> attachedAplicadorListNew = new ArrayList<Aplicador>();
            for (Aplicador aplicadorListNewAplicadorToAttach : aplicadorListNew) {
                aplicadorListNewAplicadorToAttach = em.getReference(aplicadorListNewAplicadorToAttach.getClass(), aplicadorListNewAplicadorToAttach.getIdAplicador());
                attachedAplicadorListNew.add(aplicadorListNewAplicadorToAttach);
            }
            aplicadorListNew = attachedAplicadorListNew;
            recursoHumano.setAplicadorList(aplicadorListNew);
            List<Curso> attachedCursoListNew = new ArrayList<Curso>();
            for (Curso cursoListNewCursoToAttach : cursoListNew) {
                cursoListNewCursoToAttach = em.getReference(cursoListNewCursoToAttach.getClass(), cursoListNewCursoToAttach.getIdCurso());
                attachedCursoListNew.add(cursoListNewCursoToAttach);
            }
            cursoListNew = attachedCursoListNew;
            recursoHumano.setCursoList(cursoListNew);
            List<ControlDocumento> attachedControlDocumentoListNew = new ArrayList<ControlDocumento>();
            for (ControlDocumento controlDocumentoListNewControlDocumentoToAttach : controlDocumentoListNew) {
                controlDocumentoListNewControlDocumentoToAttach = em.getReference(controlDocumentoListNewControlDocumentoToAttach.getClass(), controlDocumentoListNewControlDocumentoToAttach.getIdControldocumento());
                attachedControlDocumentoListNew.add(controlDocumentoListNewControlDocumentoToAttach);
            }
            controlDocumentoListNew = attachedControlDocumentoListNew;
            recursoHumano.setControlDocumentoList(controlDocumentoListNew);
            List<FormacionAcademica> attachedFormacionAcademicaListNew = new ArrayList<FormacionAcademica>();
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademicaToAttach : formacionAcademicaListNew) {
                formacionAcademicaListNewFormacionAcademicaToAttach = em.getReference(formacionAcademicaListNewFormacionAcademicaToAttach.getClass(), formacionAcademicaListNewFormacionAcademicaToAttach.getIdFormacionacademica());
                attachedFormacionAcademicaListNew.add(formacionAcademicaListNewFormacionAcademicaToAttach);
            }
            formacionAcademicaListNew = attachedFormacionAcademicaListNew;
            recursoHumano.setFormacionAcademicaList(formacionAcademicaListNew);
            List<AsignacionRecursohumanodocumento> attachedAsignacionRecursohumanodocumentoListNew = new ArrayList<AsignacionRecursohumanodocumento>();
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach : asignacionRecursohumanodocumentoListNew) {
                asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach = em.getReference(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach.getClass(), asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach.getIdAsignacionrecursohumanodocumento());
                attachedAsignacionRecursohumanodocumentoListNew.add(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach);
            }
            asignacionRecursohumanodocumentoListNew = attachedAsignacionRecursohumanodocumentoListNew;
            recursoHumano.setAsignacionRecursohumanodocumentoList(asignacionRecursohumanodocumentoListNew);
            recursoHumano = em.merge(recursoHumano);
            if (curpOld != null && !curpOld.equals(curpNew)) {
                curpOld.getRecursoHumanoList().remove(recursoHumano);
                curpOld = em.merge(curpOld);
            }
            if (curpNew != null && !curpNew.equals(curpOld)) {
                curpNew.getRecursoHumanoList().add(recursoHumano);
                curpNew = em.merge(curpNew);
            }
            for (TipoMovimiento tipoMovimientoListOldTipoMovimiento : tipoMovimientoListOld) {
                if (!tipoMovimientoListNew.contains(tipoMovimientoListOldTipoMovimiento)) {
                    tipoMovimientoListOldTipoMovimiento.getRecursoHumanoList().remove(recursoHumano);
                    tipoMovimientoListOldTipoMovimiento = em.merge(tipoMovimientoListOldTipoMovimiento);
                }
            }
            for (TipoMovimiento tipoMovimientoListNewTipoMovimiento : tipoMovimientoListNew) {
                if (!tipoMovimientoListOld.contains(tipoMovimientoListNewTipoMovimiento)) {
                    tipoMovimientoListNewTipoMovimiento.getRecursoHumanoList().add(recursoHumano);
                    tipoMovimientoListNewTipoMovimiento = em.merge(tipoMovimientoListNewTipoMovimiento);
                }
            }
            for (ExperienciaLaboral experienciaLaboralListOldExperienciaLaboral : experienciaLaboralListOld) {
                if (!experienciaLaboralListNew.contains(experienciaLaboralListOldExperienciaLaboral)) {
                    experienciaLaboralListOldExperienciaLaboral.setIdRecursohumano(null);
                    experienciaLaboralListOldExperienciaLaboral = em.merge(experienciaLaboralListOldExperienciaLaboral);
                }
            }
            for (ExperienciaLaboral experienciaLaboralListNewExperienciaLaboral : experienciaLaboralListNew) {
                if (!experienciaLaboralListOld.contains(experienciaLaboralListNewExperienciaLaboral)) {
                    RecursoHumano oldIdRecursohumanoOfExperienciaLaboralListNewExperienciaLaboral = experienciaLaboralListNewExperienciaLaboral.getIdRecursohumano();
                    experienciaLaboralListNewExperienciaLaboral.setIdRecursohumano(recursoHumano);
                    experienciaLaboralListNewExperienciaLaboral = em.merge(experienciaLaboralListNewExperienciaLaboral);
                    if (oldIdRecursohumanoOfExperienciaLaboralListNewExperienciaLaboral != null && !oldIdRecursohumanoOfExperienciaLaboralListNewExperienciaLaboral.equals(recursoHumano)) {
                        oldIdRecursohumanoOfExperienciaLaboralListNewExperienciaLaboral.getExperienciaLaboralList().remove(experienciaLaboralListNewExperienciaLaboral);
                        oldIdRecursohumanoOfExperienciaLaboralListNewExperienciaLaboral = em.merge(oldIdRecursohumanoOfExperienciaLaboralListNewExperienciaLaboral);
                    }
                }
            }
            for (Idioma idiomaListOldIdioma : idiomaListOld) {
                if (!idiomaListNew.contains(idiomaListOldIdioma)) {
                    idiomaListOldIdioma.setIdRecursohumano(null);
                    idiomaListOldIdioma = em.merge(idiomaListOldIdioma);
                }
            }
            for (Idioma idiomaListNewIdioma : idiomaListNew) {
                if (!idiomaListOld.contains(idiomaListNewIdioma)) {
                    RecursoHumano oldIdRecursohumanoOfIdiomaListNewIdioma = idiomaListNewIdioma.getIdRecursohumano();
                    idiomaListNewIdioma.setIdRecursohumano(recursoHumano);
                    idiomaListNewIdioma = em.merge(idiomaListNewIdioma);
                    if (oldIdRecursohumanoOfIdiomaListNewIdioma != null && !oldIdRecursohumanoOfIdiomaListNewIdioma.equals(recursoHumano)) {
                        oldIdRecursohumanoOfIdiomaListNewIdioma.getIdiomaList().remove(idiomaListNewIdioma);
                        oldIdRecursohumanoOfIdiomaListNewIdioma = em.merge(oldIdRecursohumanoOfIdiomaListNewIdioma);
                    }
                }
            }
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setResponsableActa(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    RecursoHumano oldResponsableActaOfActaListNewActa = actaListNewActa.getResponsableActa();
                    actaListNewActa.setResponsableActa(recursoHumano);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldResponsableActaOfActaListNewActa != null && !oldResponsableActaOfActaListNewActa.equals(recursoHumano)) {
                        oldResponsableActaOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldResponsableActaOfActaListNewActa = em.merge(oldResponsableActaOfActaListNewActa);
                    }
                }
            }
            for (DestinatarioNotificacion destinatarioNotificacionListOldDestinatarioNotificacion : destinatarioNotificacionListOld) {
                if (!destinatarioNotificacionListNew.contains(destinatarioNotificacionListOldDestinatarioNotificacion)) {
                    destinatarioNotificacionListOldDestinatarioNotificacion.setIdRecursohumano(null);
                    destinatarioNotificacionListOldDestinatarioNotificacion = em.merge(destinatarioNotificacionListOldDestinatarioNotificacion);
                }
            }
            for (DestinatarioNotificacion destinatarioNotificacionListNewDestinatarioNotificacion : destinatarioNotificacionListNew) {
                if (!destinatarioNotificacionListOld.contains(destinatarioNotificacionListNewDestinatarioNotificacion)) {
                    RecursoHumano oldIdRecursohumanoOfDestinatarioNotificacionListNewDestinatarioNotificacion = destinatarioNotificacionListNewDestinatarioNotificacion.getIdRecursohumano();
                    destinatarioNotificacionListNewDestinatarioNotificacion.setIdRecursohumano(recursoHumano);
                    destinatarioNotificacionListNewDestinatarioNotificacion = em.merge(destinatarioNotificacionListNewDestinatarioNotificacion);
                    if (oldIdRecursohumanoOfDestinatarioNotificacionListNewDestinatarioNotificacion != null && !oldIdRecursohumanoOfDestinatarioNotificacionListNewDestinatarioNotificacion.equals(recursoHumano)) {
                        oldIdRecursohumanoOfDestinatarioNotificacionListNewDestinatarioNotificacion.getDestinatarioNotificacionList().remove(destinatarioNotificacionListNewDestinatarioNotificacion);
                        oldIdRecursohumanoOfDestinatarioNotificacionListNewDestinatarioNotificacion = em.merge(oldIdRecursohumanoOfDestinatarioNotificacionListNewDestinatarioNotificacion);
                    }
                }
            }
            for (Administrativo administrativoListOldAdministrativo : administrativoListOld) {
                if (!administrativoListNew.contains(administrativoListOldAdministrativo)) {
                    administrativoListOldAdministrativo.setIdRecursohumano(null);
                    administrativoListOldAdministrativo = em.merge(administrativoListOldAdministrativo);
                }
            }
            for (Administrativo administrativoListNewAdministrativo : administrativoListNew) {
                if (!administrativoListOld.contains(administrativoListNewAdministrativo)) {
                    RecursoHumano oldIdRecursohumanoOfAdministrativoListNewAdministrativo = administrativoListNewAdministrativo.getIdRecursohumano();
                    administrativoListNewAdministrativo.setIdRecursohumano(recursoHumano);
                    administrativoListNewAdministrativo = em.merge(administrativoListNewAdministrativo);
                    if (oldIdRecursohumanoOfAdministrativoListNewAdministrativo != null && !oldIdRecursohumanoOfAdministrativoListNewAdministrativo.equals(recursoHumano)) {
                        oldIdRecursohumanoOfAdministrativoListNewAdministrativo.getAdministrativoList().remove(administrativoListNewAdministrativo);
                        oldIdRecursohumanoOfAdministrativoListNewAdministrativo = em.merge(oldIdRecursohumanoOfAdministrativoListNewAdministrativo);
                    }
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListOldAsignacionRecursoplantel : asignacionRecursoplantelListOld) {
                if (!asignacionRecursoplantelListNew.contains(asignacionRecursoplantelListOldAsignacionRecursoplantel)) {
                    asignacionRecursoplantelListOldAsignacionRecursoplantel.setIdRecursohumano(null);
                    asignacionRecursoplantelListOldAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListOldAsignacionRecursoplantel);
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListNewAsignacionRecursoplantel : asignacionRecursoplantelListNew) {
                if (!asignacionRecursoplantelListOld.contains(asignacionRecursoplantelListNewAsignacionRecursoplantel)) {
                    RecursoHumano oldIdRecursohumanoOfAsignacionRecursoplantelListNewAsignacionRecursoplantel = asignacionRecursoplantelListNewAsignacionRecursoplantel.getIdRecursohumano();
                    asignacionRecursoplantelListNewAsignacionRecursoplantel.setIdRecursohumano(recursoHumano);
                    asignacionRecursoplantelListNewAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListNewAsignacionRecursoplantel);
                    if (oldIdRecursohumanoOfAsignacionRecursoplantelListNewAsignacionRecursoplantel != null && !oldIdRecursohumanoOfAsignacionRecursoplantelListNewAsignacionRecursoplantel.equals(recursoHumano)) {
                        oldIdRecursohumanoOfAsignacionRecursoplantelListNewAsignacionRecursoplantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantelListNewAsignacionRecursoplantel);
                        oldIdRecursohumanoOfAsignacionRecursoplantelListNewAsignacionRecursoplantel = em.merge(oldIdRecursohumanoOfAsignacionRecursoplantelListNewAsignacionRecursoplantel);
                    }
                }
            }
            for (SeguimientoAlumno seguimientoAlumnoListOldSeguimientoAlumno : seguimientoAlumnoListOld) {
                if (!seguimientoAlumnoListNew.contains(seguimientoAlumnoListOldSeguimientoAlumno)) {
                    seguimientoAlumnoListOldSeguimientoAlumno.setIdResponsable(null);
                    seguimientoAlumnoListOldSeguimientoAlumno = em.merge(seguimientoAlumnoListOldSeguimientoAlumno);
                }
            }
            for (SeguimientoAlumno seguimientoAlumnoListNewSeguimientoAlumno : seguimientoAlumnoListNew) {
                if (!seguimientoAlumnoListOld.contains(seguimientoAlumnoListNewSeguimientoAlumno)) {
                    RecursoHumano oldIdResponsableOfSeguimientoAlumnoListNewSeguimientoAlumno = seguimientoAlumnoListNewSeguimientoAlumno.getIdResponsable();
                    seguimientoAlumnoListNewSeguimientoAlumno.setIdResponsable(recursoHumano);
                    seguimientoAlumnoListNewSeguimientoAlumno = em.merge(seguimientoAlumnoListNewSeguimientoAlumno);
                    if (oldIdResponsableOfSeguimientoAlumnoListNewSeguimientoAlumno != null && !oldIdResponsableOfSeguimientoAlumnoListNewSeguimientoAlumno.equals(recursoHumano)) {
                        oldIdResponsableOfSeguimientoAlumnoListNewSeguimientoAlumno.getSeguimientoAlumnoList().remove(seguimientoAlumnoListNewSeguimientoAlumno);
                        oldIdResponsableOfSeguimientoAlumnoListNewSeguimientoAlumno = em.merge(oldIdResponsableOfSeguimientoAlumnoListNewSeguimientoAlumno);
                    }
                }
            }
            for (OfertaEvento ofertaEventoListOldOfertaEvento : ofertaEventoListOld) {
                if (!ofertaEventoListNew.contains(ofertaEventoListOldOfertaEvento)) {
                    ofertaEventoListOldOfertaEvento.setIdInstructor(null);
                    ofertaEventoListOldOfertaEvento = em.merge(ofertaEventoListOldOfertaEvento);
                }
            }
            for (OfertaEvento ofertaEventoListNewOfertaEvento : ofertaEventoListNew) {
                if (!ofertaEventoListOld.contains(ofertaEventoListNewOfertaEvento)) {
                    RecursoHumano oldIdInstructorOfOfertaEventoListNewOfertaEvento = ofertaEventoListNewOfertaEvento.getIdInstructor();
                    ofertaEventoListNewOfertaEvento.setIdInstructor(recursoHumano);
                    ofertaEventoListNewOfertaEvento = em.merge(ofertaEventoListNewOfertaEvento);
                    if (oldIdInstructorOfOfertaEventoListNewOfertaEvento != null && !oldIdInstructorOfOfertaEventoListNewOfertaEvento.equals(recursoHumano)) {
                        oldIdInstructorOfOfertaEventoListNewOfertaEvento.getOfertaEventoList().remove(ofertaEventoListNewOfertaEvento);
                        oldIdInstructorOfOfertaEventoListNewOfertaEvento = em.merge(oldIdInstructorOfOfertaEventoListNewOfertaEvento);
                    }
                }
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    asesorListOldAsesor.setIdRecursohumano(null);
                    asesorListOldAsesor = em.merge(asesorListOldAsesor);
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    RecursoHumano oldIdRecursohumanoOfAsesorListNewAsesor = asesorListNewAsesor.getIdRecursohumano();
                    asesorListNewAsesor.setIdRecursohumano(recursoHumano);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                    if (oldIdRecursohumanoOfAsesorListNewAsesor != null && !oldIdRecursohumanoOfAsesorListNewAsesor.equals(recursoHumano)) {
                        oldIdRecursohumanoOfAsesorListNewAsesor.getAsesorList().remove(asesorListNewAsesor);
                        oldIdRecursohumanoOfAsesorListNewAsesor = em.merge(oldIdRecursohumanoOfAsesorListNewAsesor);
                    }
                }
            }
            for (Aplicador aplicadorListOldAplicador : aplicadorListOld) {
                if (!aplicadorListNew.contains(aplicadorListOldAplicador)) {
                    aplicadorListOldAplicador.setIdRecursohumano(null);
                    aplicadorListOldAplicador = em.merge(aplicadorListOldAplicador);
                }
            }
            for (Aplicador aplicadorListNewAplicador : aplicadorListNew) {
                if (!aplicadorListOld.contains(aplicadorListNewAplicador)) {
                    RecursoHumano oldIdRecursohumanoOfAplicadorListNewAplicador = aplicadorListNewAplicador.getIdRecursohumano();
                    aplicadorListNewAplicador.setIdRecursohumano(recursoHumano);
                    aplicadorListNewAplicador = em.merge(aplicadorListNewAplicador);
                    if (oldIdRecursohumanoOfAplicadorListNewAplicador != null && !oldIdRecursohumanoOfAplicadorListNewAplicador.equals(recursoHumano)) {
                        oldIdRecursohumanoOfAplicadorListNewAplicador.getAplicadorList().remove(aplicadorListNewAplicador);
                        oldIdRecursohumanoOfAplicadorListNewAplicador = em.merge(oldIdRecursohumanoOfAplicadorListNewAplicador);
                    }
                }
            }
            for (Curso cursoListOldCurso : cursoListOld) {
                if (!cursoListNew.contains(cursoListOldCurso)) {
                    cursoListOldCurso.setIdRecursohumano(null);
                    cursoListOldCurso = em.merge(cursoListOldCurso);
                }
            }
            for (Curso cursoListNewCurso : cursoListNew) {
                if (!cursoListOld.contains(cursoListNewCurso)) {
                    RecursoHumano oldIdRecursohumanoOfCursoListNewCurso = cursoListNewCurso.getIdRecursohumano();
                    cursoListNewCurso.setIdRecursohumano(recursoHumano);
                    cursoListNewCurso = em.merge(cursoListNewCurso);
                    if (oldIdRecursohumanoOfCursoListNewCurso != null && !oldIdRecursohumanoOfCursoListNewCurso.equals(recursoHumano)) {
                        oldIdRecursohumanoOfCursoListNewCurso.getCursoList().remove(cursoListNewCurso);
                        oldIdRecursohumanoOfCursoListNewCurso = em.merge(oldIdRecursohumanoOfCursoListNewCurso);
                    }
                }
            }
            for (ControlDocumento controlDocumentoListOldControlDocumento : controlDocumentoListOld) {
                if (!controlDocumentoListNew.contains(controlDocumentoListOldControlDocumento)) {
                    controlDocumentoListOldControlDocumento.setIdRecursohumano(null);
                    controlDocumentoListOldControlDocumento = em.merge(controlDocumentoListOldControlDocumento);
                }
            }
            for (ControlDocumento controlDocumentoListNewControlDocumento : controlDocumentoListNew) {
                if (!controlDocumentoListOld.contains(controlDocumentoListNewControlDocumento)) {
                    RecursoHumano oldIdRecursohumanoOfControlDocumentoListNewControlDocumento = controlDocumentoListNewControlDocumento.getIdRecursohumano();
                    controlDocumentoListNewControlDocumento.setIdRecursohumano(recursoHumano);
                    controlDocumentoListNewControlDocumento = em.merge(controlDocumentoListNewControlDocumento);
                    if (oldIdRecursohumanoOfControlDocumentoListNewControlDocumento != null && !oldIdRecursohumanoOfControlDocumentoListNewControlDocumento.equals(recursoHumano)) {
                        oldIdRecursohumanoOfControlDocumentoListNewControlDocumento.getControlDocumentoList().remove(controlDocumentoListNewControlDocumento);
                        oldIdRecursohumanoOfControlDocumentoListNewControlDocumento = em.merge(oldIdRecursohumanoOfControlDocumentoListNewControlDocumento);
                    }
                }
            }
            for (FormacionAcademica formacionAcademicaListOldFormacionAcademica : formacionAcademicaListOld) {
                if (!formacionAcademicaListNew.contains(formacionAcademicaListOldFormacionAcademica)) {
                    formacionAcademicaListOldFormacionAcademica.setIdRecursohumano(null);
                    formacionAcademicaListOldFormacionAcademica = em.merge(formacionAcademicaListOldFormacionAcademica);
                }
            }
            for (FormacionAcademica formacionAcademicaListNewFormacionAcademica : formacionAcademicaListNew) {
                if (!formacionAcademicaListOld.contains(formacionAcademicaListNewFormacionAcademica)) {
                    RecursoHumano oldIdRecursohumanoOfFormacionAcademicaListNewFormacionAcademica = formacionAcademicaListNewFormacionAcademica.getIdRecursohumano();
                    formacionAcademicaListNewFormacionAcademica.setIdRecursohumano(recursoHumano);
                    formacionAcademicaListNewFormacionAcademica = em.merge(formacionAcademicaListNewFormacionAcademica);
                    if (oldIdRecursohumanoOfFormacionAcademicaListNewFormacionAcademica != null && !oldIdRecursohumanoOfFormacionAcademicaListNewFormacionAcademica.equals(recursoHumano)) {
                        oldIdRecursohumanoOfFormacionAcademicaListNewFormacionAcademica.getFormacionAcademicaList().remove(formacionAcademicaListNewFormacionAcademica);
                        oldIdRecursohumanoOfFormacionAcademicaListNewFormacionAcademica = em.merge(oldIdRecursohumanoOfFormacionAcademicaListNewFormacionAcademica);
                    }
                }
            }
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento : asignacionRecursohumanodocumentoListOld) {
                if (!asignacionRecursohumanodocumentoListNew.contains(asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento)) {
                    asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento.setIdRecursohumano(null);
                    asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento);
                }
            }
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento : asignacionRecursohumanodocumentoListNew) {
                if (!asignacionRecursohumanodocumentoListOld.contains(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento)) {
                    RecursoHumano oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento = asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.getIdRecursohumano();
                    asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.setIdRecursohumano(recursoHumano);
                    asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento);
                    if (oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento != null && !oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.equals(recursoHumano)) {
                        oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento);
                        oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento = em.merge(oldIdRecursohumanoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = recursoHumano.getIdRecursohumano();
                if (findRecursoHumano(id) == null) {
                    throw new NonexistentEntityException("The recursoHumano with id " + id + " no longer exists.");
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
            RecursoHumano recursoHumano;
            try {
                recursoHumano = em.getReference(RecursoHumano.class, id);
                recursoHumano.getIdRecursohumano();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recursoHumano with id " + id + " no longer exists.", enfe);
            }
            Persona curp = recursoHumano.getCurp();
            if (curp != null) {
                curp.getRecursoHumanoList().remove(recursoHumano);
                curp = em.merge(curp);
            }
            List<TipoMovimiento> tipoMovimientoList = recursoHumano.getTipoMovimientoList();
            for (TipoMovimiento tipoMovimientoListTipoMovimiento : tipoMovimientoList) {
                tipoMovimientoListTipoMovimiento.getRecursoHumanoList().remove(recursoHumano);
                tipoMovimientoListTipoMovimiento = em.merge(tipoMovimientoListTipoMovimiento);
            }
            List<ExperienciaLaboral> experienciaLaboralList = recursoHumano.getExperienciaLaboralList();
            for (ExperienciaLaboral experienciaLaboralListExperienciaLaboral : experienciaLaboralList) {
                experienciaLaboralListExperienciaLaboral.setIdRecursohumano(null);
                experienciaLaboralListExperienciaLaboral = em.merge(experienciaLaboralListExperienciaLaboral);
            }
            List<Idioma> idiomaList = recursoHumano.getIdiomaList();
            for (Idioma idiomaListIdioma : idiomaList) {
                idiomaListIdioma.setIdRecursohumano(null);
                idiomaListIdioma = em.merge(idiomaListIdioma);
            }
            List<Acta> actaList = recursoHumano.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setResponsableActa(null);
                actaListActa = em.merge(actaListActa);
            }
            List<DestinatarioNotificacion> destinatarioNotificacionList = recursoHumano.getDestinatarioNotificacionList();
            for (DestinatarioNotificacion destinatarioNotificacionListDestinatarioNotificacion : destinatarioNotificacionList) {
                destinatarioNotificacionListDestinatarioNotificacion.setIdRecursohumano(null);
                destinatarioNotificacionListDestinatarioNotificacion = em.merge(destinatarioNotificacionListDestinatarioNotificacion);
            }
            List<Administrativo> administrativoList = recursoHumano.getAdministrativoList();
            for (Administrativo administrativoListAdministrativo : administrativoList) {
                administrativoListAdministrativo.setIdRecursohumano(null);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
            }
            List<AsignacionRecursoplantel> asignacionRecursoplantelList = recursoHumano.getAsignacionRecursoplantelList();
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantel : asignacionRecursoplantelList) {
                asignacionRecursoplantelListAsignacionRecursoplantel.setIdRecursohumano(null);
                asignacionRecursoplantelListAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListAsignacionRecursoplantel);
            }
            List<SeguimientoAlumno> seguimientoAlumnoList = recursoHumano.getSeguimientoAlumnoList();
            for (SeguimientoAlumno seguimientoAlumnoListSeguimientoAlumno : seguimientoAlumnoList) {
                seguimientoAlumnoListSeguimientoAlumno.setIdResponsable(null);
                seguimientoAlumnoListSeguimientoAlumno = em.merge(seguimientoAlumnoListSeguimientoAlumno);
            }
            List<OfertaEvento> ofertaEventoList = recursoHumano.getOfertaEventoList();
            for (OfertaEvento ofertaEventoListOfertaEvento : ofertaEventoList) {
                ofertaEventoListOfertaEvento.setIdInstructor(null);
                ofertaEventoListOfertaEvento = em.merge(ofertaEventoListOfertaEvento);
            }
            List<Asesor> asesorList = recursoHumano.getAsesorList();
            for (Asesor asesorListAsesor : asesorList) {
                asesorListAsesor.setIdRecursohumano(null);
                asesorListAsesor = em.merge(asesorListAsesor);
            }
            List<Aplicador> aplicadorList = recursoHumano.getAplicadorList();
            for (Aplicador aplicadorListAplicador : aplicadorList) {
                aplicadorListAplicador.setIdRecursohumano(null);
                aplicadorListAplicador = em.merge(aplicadorListAplicador);
            }
            List<Curso> cursoList = recursoHumano.getCursoList();
            for (Curso cursoListCurso : cursoList) {
                cursoListCurso.setIdRecursohumano(null);
                cursoListCurso = em.merge(cursoListCurso);
            }
            List<ControlDocumento> controlDocumentoList = recursoHumano.getControlDocumentoList();
            for (ControlDocumento controlDocumentoListControlDocumento : controlDocumentoList) {
                controlDocumentoListControlDocumento.setIdRecursohumano(null);
                controlDocumentoListControlDocumento = em.merge(controlDocumentoListControlDocumento);
            }
            List<FormacionAcademica> formacionAcademicaList = recursoHumano.getFormacionAcademicaList();
            for (FormacionAcademica formacionAcademicaListFormacionAcademica : formacionAcademicaList) {
                formacionAcademicaListFormacionAcademica.setIdRecursohumano(null);
                formacionAcademicaListFormacionAcademica = em.merge(formacionAcademicaListFormacionAcademica);
            }
            List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoList = recursoHumano.getAsignacionRecursohumanodocumentoList();
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento : asignacionRecursohumanodocumentoList) {
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.setIdRecursohumano(null);
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
            }
            em.remove(recursoHumano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecursoHumano> findRecursoHumanoEntities() {
        return findRecursoHumanoEntities(true, -1, -1);
    }

    public List<RecursoHumano> findRecursoHumanoEntities(int maxResults, int firstResult) {
        return findRecursoHumanoEntities(false, maxResults, firstResult);
    }

    private List<RecursoHumano> findRecursoHumanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RecursoHumano as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RecursoHumano findRecursoHumano(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecursoHumano.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecursoHumanoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RecursoHumano as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
