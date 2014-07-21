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
@Table(name = "tipo_modalidad")
@NamedQueries({
    @NamedQuery(name = "TipoModalidad.findAll", query = "SELECT t FROM TipoModalidad t"),
    @NamedQuery(name = "TipoModalidad.findByIdTipomodalidad", query = "SELECT t FROM TipoModalidad t WHERE t.idTipomodalidad = :idTipomodalidad"),
    @NamedQuery(name = "TipoModalidad.findByDescripcionTipomodalidad", query = "SELECT t FROM TipoModalidad t WHERE t.descripcionTipomodalidad = :descripcionTipomodalidad")})
public class TipoModalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipomodalidad")
    private Long idTipomodalidad;
    @Column(name = "descripcion_tipomodalidad")
    private String descripcionTipomodalidad;
    @OneToMany(mappedBy = "idTipomodalidad")
    private List<FormacionAcademica> formacionAcademicaList;

    public TipoModalidad() {
    }

    public TipoModalidad(Long idTipomodalidad) {
        this.idTipomodalidad = idTipomodalidad;
    }

    public Long getIdTipomodalidad() {
        return idTipomodalidad;
    }

    public void setIdTipomodalidad(Long idTipomodalidad) {
        this.idTipomodalidad = idTipomodalidad;
    }

    public String getDescripcionTipomodalidad() {
        return descripcionTipomodalidad;
    }

    public void setDescripcionTipomodalidad(String descripcionTipomodalidad) {
        this.descripcionTipomodalidad = descripcionTipomodalidad;
    }

    public List<FormacionAcademica> getFormacionAcademicaList() {
        return formacionAcademicaList;
    }

    public void setFormacionAcademicaList(List<FormacionAcademica> formacionAcademicaList) {
        this.formacionAcademicaList = formacionAcademicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipomodalidad != null ? idTipomodalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoModalidad)) {
            return false;
        }
        TipoModalidad other = (TipoModalidad) object;
        if ((this.idTipomodalidad == null && other.idTipomodalidad != null) || (this.idTipomodalidad != null && !this.idTipomodalidad.equals(other.idTipomodalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoModalidad[ idTipomodalidad=" + idTipomodalidad + " ]";
    }
    
}
