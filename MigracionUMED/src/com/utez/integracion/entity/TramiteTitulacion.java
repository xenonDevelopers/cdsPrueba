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
import javax.persistence.ManyToMany;
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
@Table(name = "tramite_titulacion")
@NamedQueries({
    @NamedQuery(name = "TramiteTitulacion.findAll", query = "SELECT t FROM TramiteTitulacion t"),
    @NamedQuery(name = "TramiteTitulacion.findByIdTramitetitulacion", query = "SELECT t FROM TramiteTitulacion t WHERE t.idTramitetitulacion = :idTramitetitulacion"),
    @NamedQuery(name = "TramiteTitulacion.findByFechaRegistro", query = "SELECT t FROM TramiteTitulacion t WHERE t.fechaRegistro = :fechaRegistro")})
public class TramiteTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tramitetitulacion")
    private Long idTramitetitulacion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @ManyToMany(mappedBy = "tramiteTitulacionList")
    private List<OrdenTrabajo> ordenTrabajoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tramiteTitulacion")
    private AsesorTitulartitulacion asesorTitulartitulacion;
    @OneToMany(mappedBy = "idTramitetitulacion")
    private List<SinodoExamen> sinodoExamenList;
    @JoinColumn(name = "id_solicitudtitulacion", referencedColumnName = "id_solicitudtitulacion")
    @ManyToOne
    private SolicitudTitulacion idSolicitudtitulacion;
    @OneToMany(mappedBy = "idTramitetitulacion")
    private List<ExamenTitulacion> examenTitulacionList;

    public TramiteTitulacion() {
    }

    public TramiteTitulacion(Long idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public Long getIdTramitetitulacion() {
        return idTramitetitulacion;
    }

    public void setIdTramitetitulacion(Long idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<OrdenTrabajo> getOrdenTrabajoList() {
        return ordenTrabajoList;
    }

    public void setOrdenTrabajoList(List<OrdenTrabajo> ordenTrabajoList) {
        this.ordenTrabajoList = ordenTrabajoList;
    }

    public AsesorTitulartitulacion getAsesorTitulartitulacion() {
        return asesorTitulartitulacion;
    }

    public void setAsesorTitulartitulacion(AsesorTitulartitulacion asesorTitulartitulacion) {
        this.asesorTitulartitulacion = asesorTitulartitulacion;
    }

    public List<SinodoExamen> getSinodoExamenList() {
        return sinodoExamenList;
    }

    public void setSinodoExamenList(List<SinodoExamen> sinodoExamenList) {
        this.sinodoExamenList = sinodoExamenList;
    }

    public SolicitudTitulacion getIdSolicitudtitulacion() {
        return idSolicitudtitulacion;
    }

    public void setIdSolicitudtitulacion(SolicitudTitulacion idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public List<ExamenTitulacion> getExamenTitulacionList() {
        return examenTitulacionList;
    }

    public void setExamenTitulacionList(List<ExamenTitulacion> examenTitulacionList) {
        this.examenTitulacionList = examenTitulacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTramitetitulacion != null ? idTramitetitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TramiteTitulacion)) {
            return false;
        }
        TramiteTitulacion other = (TramiteTitulacion) object;
        if ((this.idTramitetitulacion == null && other.idTramitetitulacion != null) || (this.idTramitetitulacion != null && !this.idTramitetitulacion.equals(other.idTramitetitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TramiteTitulacion[ idTramitetitulacion=" + idTramitetitulacion + " ]";
    }
    
}
