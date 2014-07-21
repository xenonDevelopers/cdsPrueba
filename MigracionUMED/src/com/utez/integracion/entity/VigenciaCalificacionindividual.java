/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "vigencia_calificacionindividual")
@NamedQueries({
    @NamedQuery(name = "VigenciaCalificacionindividual.findAll", query = "SELECT v FROM VigenciaCalificacionindividual v"),
    @NamedQuery(name = "VigenciaCalificacionindividual.findByIdExamenindividual", query = "SELECT v FROM VigenciaCalificacionindividual v WHERE v.idExamenindividual = :idExamenindividual"),
    @NamedQuery(name = "VigenciaCalificacionindividual.findByFechaInicio", query = "SELECT v FROM VigenciaCalificacionindividual v WHERE v.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "VigenciaCalificacionindividual.findByFechaFin", query = "SELECT v FROM VigenciaCalificacionindividual v WHERE v.fechaFin = :fechaFin"),
    @NamedQuery(name = "VigenciaCalificacionindividual.findByObservaciones", query = "SELECT v FROM VigenciaCalificacionindividual v WHERE v.observaciones = :observaciones")})
public class VigenciaCalificacionindividual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_examenindividual")
    private Long idExamenindividual;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_examenindividual", referencedColumnName = "id_examenindividual", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ExamenIndividual examenIndividual;

    public VigenciaCalificacionindividual() {
    }

    public VigenciaCalificacionindividual(Long idExamenindividual) {
        this.idExamenindividual = idExamenindividual;
    }

    public Long getIdExamenindividual() {
        return idExamenindividual;
    }

    public void setIdExamenindividual(Long idExamenindividual) {
        this.idExamenindividual = idExamenindividual;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ExamenIndividual getExamenIndividual() {
        return examenIndividual;
    }

    public void setExamenIndividual(ExamenIndividual examenIndividual) {
        this.examenIndividual = examenIndividual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenindividual != null ? idExamenindividual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VigenciaCalificacionindividual)) {
            return false;
        }
        VigenciaCalificacionindividual other = (VigenciaCalificacionindividual) object;
        if ((this.idExamenindividual == null && other.idExamenindividual != null) || (this.idExamenindividual != null && !this.idExamenindividual.equals(other.idExamenindividual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.VigenciaCalificacionindividual[ idExamenindividual=" + idExamenindividual + " ]";
    }
    
}
