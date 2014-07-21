/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
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
@Table(name = "solicitud_titulacion")
@NamedQueries({
    @NamedQuery(name = "SolicitudTitulacion.findAll", query = "SELECT s FROM SolicitudTitulacion s"),
    @NamedQuery(name = "SolicitudTitulacion.findByIdSolicitudtitulacion", query = "SELECT s FROM SolicitudTitulacion s WHERE s.idSolicitudtitulacion = :idSolicitudtitulacion"),
    @NamedQuery(name = "SolicitudTitulacion.findByFechaRegistro", query = "SELECT s FROM SolicitudTitulacion s WHERE s.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "SolicitudTitulacion.findByFechaAutorizacion", query = "SELECT s FROM SolicitudTitulacion s WHERE s.fechaAutorizacion = :fechaAutorizacion"),
    @NamedQuery(name = "SolicitudTitulacion.findByEstadoSolicitudtitulacion", query = "SELECT s FROM SolicitudTitulacion s WHERE s.estadoSolicitudtitulacion = :estadoSolicitudtitulacion")})
public class SolicitudTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitudtitulacion")
    private Long idSolicitudtitulacion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAutorizacion;
    @Column(name = "estado_solicitudtitulacion")
    private String estadoSolicitudtitulacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "solicitudTitulacion")
    private TemaSolicitudautorizacion temaSolicitudautorizacion;
    @JoinColumn(name = "id_tipotitulacion", referencedColumnName = "id_tipotitulacion")
    @ManyToOne
    private TipoTitulacion idTipotitulacion;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "solicitudTitulacion")
    private ObservacionSolicitudtitulacion observacionSolicitudtitulacion;
    @OneToMany(mappedBy = "idSolicitudtitulacion")
    private List<TramiteTitulacion> tramiteTitulacionList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "solicitudTitulacion")
    private PosgradoSolicitudautorizacion posgradoSolicitudautorizacion;

    public SolicitudTitulacion() {
    }

    public SolicitudTitulacion(Long idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public Long getIdSolicitudtitulacion() {
        return idSolicitudtitulacion;
    }

    public void setIdSolicitudtitulacion(Long idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getEstadoSolicitudtitulacion() {
        return estadoSolicitudtitulacion;
    }

    public void setEstadoSolicitudtitulacion(String estadoSolicitudtitulacion) {
        this.estadoSolicitudtitulacion = estadoSolicitudtitulacion;
    }

    public TemaSolicitudautorizacion getTemaSolicitudautorizacion() {
        return temaSolicitudautorizacion;
    }

    public void setTemaSolicitudautorizacion(TemaSolicitudautorizacion temaSolicitudautorizacion) {
        this.temaSolicitudautorizacion = temaSolicitudautorizacion;
    }

    public TipoTitulacion getIdTipotitulacion() {
        return idTipotitulacion;
    }

    public void setIdTipotitulacion(TipoTitulacion idTipotitulacion) {
        this.idTipotitulacion = idTipotitulacion;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    public ObservacionSolicitudtitulacion getObservacionSolicitudtitulacion() {
        return observacionSolicitudtitulacion;
    }

    public void setObservacionSolicitudtitulacion(ObservacionSolicitudtitulacion observacionSolicitudtitulacion) {
        this.observacionSolicitudtitulacion = observacionSolicitudtitulacion;
    }

    public List<TramiteTitulacion> getTramiteTitulacionList() {
        return tramiteTitulacionList;
    }

    public void setTramiteTitulacionList(List<TramiteTitulacion> tramiteTitulacionList) {
        this.tramiteTitulacionList = tramiteTitulacionList;
    }

    public PosgradoSolicitudautorizacion getPosgradoSolicitudautorizacion() {
        return posgradoSolicitudautorizacion;
    }

    public void setPosgradoSolicitudautorizacion(PosgradoSolicitudautorizacion posgradoSolicitudautorizacion) {
        this.posgradoSolicitudautorizacion = posgradoSolicitudautorizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudtitulacion != null ? idSolicitudtitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudTitulacion)) {
            return false;
        }
        SolicitudTitulacion other = (SolicitudTitulacion) object;
        if ((this.idSolicitudtitulacion == null && other.idSolicitudtitulacion != null) || (this.idSolicitudtitulacion != null && !this.idSolicitudtitulacion.equals(other.idSolicitudtitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SolicitudTitulacion[ idSolicitudtitulacion=" + idSolicitudtitulacion + " ]";
    }
    
}
