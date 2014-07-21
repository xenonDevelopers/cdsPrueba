/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_evaluacion")
@NamedQueries({
    @NamedQuery(name = "TipoEvaluacion.findAll", query = "SELECT t FROM TipoEvaluacion t"),
    @NamedQuery(name = "TipoEvaluacion.findByIdTipoevaluacion", query = "SELECT t FROM TipoEvaluacion t WHERE t.idTipoevaluacion = :idTipoevaluacion"),
    @NamedQuery(name = "TipoEvaluacion.findByDescripcionTipoevaluacion", query = "SELECT t FROM TipoEvaluacion t WHERE t.descripcionTipoevaluacion = :descripcionTipoevaluacion")})
public class TipoEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoevaluacion")
    private Long idTipoevaluacion;
    @Column(name = "descripcion_tipoevaluacion")
    private String descripcionTipoevaluacion;
    @OneToMany(mappedBy = "idTipoevaluacion")
    private List<AsignacionEvaluacion> asignacionEvaluacionList;

    public TipoEvaluacion() {
    }

    public TipoEvaluacion(Long idTipoevaluacion) {
        this.idTipoevaluacion = idTipoevaluacion;
    }

    public Long getIdTipoevaluacion() {
        return idTipoevaluacion;
    }

    public void setIdTipoevaluacion(Long idTipoevaluacion) {
        this.idTipoevaluacion = idTipoevaluacion;
    }

    public String getDescripcionTipoevaluacion() {
        return descripcionTipoevaluacion;
    }

    public void setDescripcionTipoevaluacion(String descripcionTipoevaluacion) {
        this.descripcionTipoevaluacion = descripcionTipoevaluacion;
    }

    public List<AsignacionEvaluacion> getAsignacionEvaluacionList() {
        return asignacionEvaluacionList;
    }

    public void setAsignacionEvaluacionList(List<AsignacionEvaluacion> asignacionEvaluacionList) {
        this.asignacionEvaluacionList = asignacionEvaluacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoevaluacion != null ? idTipoevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvaluacion)) {
            return false;
        }
        TipoEvaluacion other = (TipoEvaluacion) object;
        if ((this.idTipoevaluacion == null && other.idTipoevaluacion != null) || (this.idTipoevaluacion != null && !this.idTipoevaluacion.equals(other.idTipoevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoEvaluacion[ idTipoevaluacion=" + idTipoevaluacion + " ]";
    }
    
}
