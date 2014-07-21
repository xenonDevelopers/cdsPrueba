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
@Table(name = "vigencia_calificacion")
@NamedQueries({
    @NamedQuery(name = "VigenciaCalificacion.findAll", query = "SELECT v FROM VigenciaCalificacion v"),
    @NamedQuery(name = "VigenciaCalificacion.findByIdExamen", query = "SELECT v FROM VigenciaCalificacion v WHERE v.idExamen = :idExamen"),
    @NamedQuery(name = "VigenciaCalificacion.findByVigenciaInicio", query = "SELECT v FROM VigenciaCalificacion v WHERE v.vigenciaInicio = :vigenciaInicio"),
    @NamedQuery(name = "VigenciaCalificacion.findByVigenciaFin", query = "SELECT v FROM VigenciaCalificacion v WHERE v.vigenciaFin = :vigenciaFin"),
    @NamedQuery(name = "VigenciaCalificacion.findByObservaciones", query = "SELECT v FROM VigenciaCalificacion v WHERE v.observaciones = :observaciones")})
public class VigenciaCalificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_examen")
    private Integer idExamen;
    @Column(name = "vigencia_inicio")
    @Temporal(TemporalType.DATE)
    private Date vigenciaInicio;
    @Column(name = "vigencia_fin")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFin;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_examen", referencedColumnName = "id_fecha_examen", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private FechaExamenprogramado fechaExamenprogramado;

    public VigenciaCalificacion() {
    }

    public VigenciaCalificacion(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Date getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public Date getVigenciaFin() {
        return vigenciaFin;
    }

    public void setVigenciaFin(Date vigenciaFin) {
        this.vigenciaFin = vigenciaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public FechaExamenprogramado getFechaExamenprogramado() {
        return fechaExamenprogramado;
    }

    public void setFechaExamenprogramado(FechaExamenprogramado fechaExamenprogramado) {
        this.fechaExamenprogramado = fechaExamenprogramado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VigenciaCalificacion)) {
            return false;
        }
        VigenciaCalificacion other = (VigenciaCalificacion) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.VigenciaCalificacion[ idExamen=" + idExamen + " ]";
    }
    
}
