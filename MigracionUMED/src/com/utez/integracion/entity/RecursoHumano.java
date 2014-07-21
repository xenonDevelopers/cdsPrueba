/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Administrativo;
import com.utez.secretariaAcademica.entity.Asesor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "recurso_humano")
@NamedQueries({
    @NamedQuery(name = "RecursoHumano.findAll", query = "SELECT r FROM RecursoHumano r"),
    @NamedQuery(name = "RecursoHumano.findByIdRecursohumano", query = "SELECT r FROM RecursoHumano r WHERE r.idRecursohumano = :idRecursohumano"),
    @NamedQuery(name = "RecursoHumano.findByFechaIngreso", query = "SELECT r FROM RecursoHumano r WHERE r.fechaIngreso = :fechaIngreso")})
public class RecursoHumano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recursohumano")
    private Long idRecursohumano;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @JoinTable(name = "asignacion_tipomovimientorecurso", joinColumns = {
        @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tipomovimiento", referencedColumnName = "id_tipomovimiento")})
    @ManyToMany
    private List<TipoMovimiento> tipoMovimientoList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<ExperienciaLaboral> experienciaLaboralList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<Idioma> idiomaList;
    @OneToMany(mappedBy = "responsableActa")
    private List<Acta> actaList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<DestinatarioNotificacion> destinatarioNotificacionList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<Administrativo> administrativoList;
    @JoinColumn(name = "curp", referencedColumnName = "curp")
    @ManyToOne
    private Persona curp;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<AsignacionRecursoplantel> asignacionRecursoplantelList;
    @OneToMany(mappedBy = "idResponsable")
    private List<SeguimientoAlumno> seguimientoAlumnoList;
    @OneToMany(mappedBy = "idInstructor")
    private List<OfertaEvento> ofertaEventoList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<Asesor> asesorList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<Aplicador> aplicadorList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<Curso> cursoList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<ControlDocumento> controlDocumentoList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<FormacionAcademica> formacionAcademicaList;
    @OneToMany(mappedBy = "idRecursohumano")
    private List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoList;

    public RecursoHumano() {
    }

    public RecursoHumano(Long idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public Long getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(Long idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<TipoMovimiento> getTipoMovimientoList() {
        return tipoMovimientoList;
    }

    public void setTipoMovimientoList(List<TipoMovimiento> tipoMovimientoList) {
        this.tipoMovimientoList = tipoMovimientoList;
    }

    public List<ExperienciaLaboral> getExperienciaLaboralList() {
        return experienciaLaboralList;
    }

    public void setExperienciaLaboralList(List<ExperienciaLaboral> experienciaLaboralList) {
        this.experienciaLaboralList = experienciaLaboralList;
    }

    public List<Idioma> getIdiomaList() {
        return idiomaList;
    }

    public void setIdiomaList(List<Idioma> idiomaList) {
        this.idiomaList = idiomaList;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    public List<DestinatarioNotificacion> getDestinatarioNotificacionList() {
        return destinatarioNotificacionList;
    }

    public void setDestinatarioNotificacionList(List<DestinatarioNotificacion> destinatarioNotificacionList) {
        this.destinatarioNotificacionList = destinatarioNotificacionList;
    }

    public List<Administrativo> getAdministrativoList() {
        return administrativoList;
    }

    public void setAdministrativoList(List<Administrativo> administrativoList) {
        this.administrativoList = administrativoList;
    }

    public Persona getCurp() {
        return curp;
    }

    public void setCurp(Persona curp) {
        this.curp = curp;
    }

    public List<AsignacionRecursoplantel> getAsignacionRecursoplantelList() {
        return asignacionRecursoplantelList;
    }

    public void setAsignacionRecursoplantelList(List<AsignacionRecursoplantel> asignacionRecursoplantelList) {
        this.asignacionRecursoplantelList = asignacionRecursoplantelList;
    }

    public List<SeguimientoAlumno> getSeguimientoAlumnoList() {
        return seguimientoAlumnoList;
    }

    public void setSeguimientoAlumnoList(List<SeguimientoAlumno> seguimientoAlumnoList) {
        this.seguimientoAlumnoList = seguimientoAlumnoList;
    }

    public List<OfertaEvento> getOfertaEventoList() {
        return ofertaEventoList;
    }

    public void setOfertaEventoList(List<OfertaEvento> ofertaEventoList) {
        this.ofertaEventoList = ofertaEventoList;
    }

    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    public List<Aplicador> getAplicadorList() {
        return aplicadorList;
    }

    public void setAplicadorList(List<Aplicador> aplicadorList) {
        this.aplicadorList = aplicadorList;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public List<ControlDocumento> getControlDocumentoList() {
        return controlDocumentoList;
    }

    public void setControlDocumentoList(List<ControlDocumento> controlDocumentoList) {
        this.controlDocumentoList = controlDocumentoList;
    }

    public List<FormacionAcademica> getFormacionAcademicaList() {
        return formacionAcademicaList;
    }

    public void setFormacionAcademicaList(List<FormacionAcademica> formacionAcademicaList) {
        this.formacionAcademicaList = formacionAcademicaList;
    }

    public List<AsignacionRecursohumanodocumento> getAsignacionRecursohumanodocumentoList() {
        return asignacionRecursohumanodocumentoList;
    }

    public void setAsignacionRecursohumanodocumentoList(List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoList) {
        this.asignacionRecursohumanodocumentoList = asignacionRecursohumanodocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecursohumano != null ? idRecursohumano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecursoHumano)) {
            return false;
        }
        RecursoHumano other = (RecursoHumano) object;
        if ((this.idRecursohumano == null && other.idRecursohumano != null) || (this.idRecursohumano != null && !this.idRecursohumano.equals(other.idRecursohumano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.RecursoHumano[ idRecursohumano=" + idRecursohumano + " ]";
    }
    
}
