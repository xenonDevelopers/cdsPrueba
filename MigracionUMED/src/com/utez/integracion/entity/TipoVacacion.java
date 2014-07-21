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
@Table(name = "tipo_vacacion")
@NamedQueries({
    @NamedQuery(name = "TipoVacacion.findAll", query = "SELECT t FROM TipoVacacion t"),
    @NamedQuery(name = "TipoVacacion.findByIdTipovacacion", query = "SELECT t FROM TipoVacacion t WHERE t.idTipovacacion = :idTipovacacion"),
    @NamedQuery(name = "TipoVacacion.findByDescripcionTipovacacion", query = "SELECT t FROM TipoVacacion t WHERE t.descripcionTipovacacion = :descripcionTipovacacion")})
public class TipoVacacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipovacacion")
    private Long idTipovacacion;
    @Column(name = "descripcion_tipovacacion")
    private String descripcionTipovacacion;
    @OneToMany(mappedBy = "idTipovacacion")
    private List<Vacacion> vacacionList;

    public TipoVacacion() {
    }

    public TipoVacacion(Long idTipovacacion) {
        this.idTipovacacion = idTipovacacion;
    }

    public Long getIdTipovacacion() {
        return idTipovacacion;
    }

    public void setIdTipovacacion(Long idTipovacacion) {
        this.idTipovacacion = idTipovacacion;
    }

    public String getDescripcionTipovacacion() {
        return descripcionTipovacacion;
    }

    public void setDescripcionTipovacacion(String descripcionTipovacacion) {
        this.descripcionTipovacacion = descripcionTipovacacion;
    }

    public List<Vacacion> getVacacionList() {
        return vacacionList;
    }

    public void setVacacionList(List<Vacacion> vacacionList) {
        this.vacacionList = vacacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipovacacion != null ? idTipovacacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVacacion)) {
            return false;
        }
        TipoVacacion other = (TipoVacacion) object;
        if ((this.idTipovacacion == null && other.idTipovacacion != null) || (this.idTipovacacion != null && !this.idTipovacacion.equals(other.idTipovacacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoVacacion[ idTipovacacion=" + idTipovacacion + " ]";
    }
    
}
