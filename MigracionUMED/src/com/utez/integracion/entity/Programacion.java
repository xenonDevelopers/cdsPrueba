/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "programacion")
@NamedQueries({
    @NamedQuery(name = "Programacion.findAll", query = "SELECT p FROM Programacion p"),
    @NamedQuery(name = "Programacion.findByIdProgramacion", query = "SELECT p FROM Programacion p WHERE p.idProgramacion = :idProgramacion"),
    @NamedQuery(name = "Programacion.findByFechaAutorizacion", query = "SELECT p FROM Programacion p WHERE p.fechaAutorizacion = :fechaAutorizacion"),
    @NamedQuery(name = "Programacion.findByEstadoProgramacion", query = "SELECT p FROM Programacion p WHERE p.estadoProgramacion = :estadoProgramacion")})
public class Programacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_programacion")
    private Long idProgramacion;
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAutorizacion;
    @Column(name = "estado_programacion")
    private String estadoProgramacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "programacion")
    private ProgramacionCancelada programacionCancelada;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "programacion")
    private Equivalencia equivalencia;
    @OneToMany(mappedBy = "idProgramacion")
    private List<ProgramacionAsignatura> programacionAsignaturaList;
    @JoinColumn(name = "id_tipoprogramacion", referencedColumnName = "id_tipoprogramacion")
    @ManyToOne
    private TipoProgramacion idTipoprogramacion;
    @JoinColumn(name = "id_solicitudprogramacion", referencedColumnName = "id_solicitudprogramacion")
    @ManyToOne
    private SolicitudProgramacion idSolicitudprogramacion;

    public Programacion() {
    }

    public Programacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getEstadoProgramacion() {
        return estadoProgramacion;
    }

    public void setEstadoProgramacion(String estadoProgramacion) {
        this.estadoProgramacion = estadoProgramacion;
    }

    public ProgramacionCancelada getProgramacionCancelada() {
        return programacionCancelada;
    }

    public void setProgramacionCancelada(ProgramacionCancelada programacionCancelada) {
        this.programacionCancelada = programacionCancelada;
    }

    public Equivalencia getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Equivalencia equivalencia) {
        this.equivalencia = equivalencia;
    }

    public List<ProgramacionAsignatura> getProgramacionAsignaturaList() {
        return programacionAsignaturaList;
    }

    public void setProgramacionAsignaturaList(List<ProgramacionAsignatura> programacionAsignaturaList) {
        this.programacionAsignaturaList = programacionAsignaturaList;
    }

    public TipoProgramacion getIdTipoprogramacion() {
        return idTipoprogramacion;
    }

    public void setIdTipoprogramacion(TipoProgramacion idTipoprogramacion) {
        this.idTipoprogramacion = idTipoprogramacion;
    }

    public SolicitudProgramacion getIdSolicitudprogramacion() {
        return idSolicitudprogramacion;
    }

    public void setIdSolicitudprogramacion(SolicitudProgramacion idSolicitudprogramacion) {
        this.idSolicitudprogramacion = idSolicitudprogramacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramacion != null ? idProgramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacion)) {
            return false;
        }
        Programacion other = (Programacion) object;
        if ((this.idProgramacion == null && other.idProgramacion != null) || (this.idProgramacion != null && !this.idProgramacion.equals(other.idProgramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Programacion[ idProgramacion=" + idProgramacion + " ]";
    }
    
}
