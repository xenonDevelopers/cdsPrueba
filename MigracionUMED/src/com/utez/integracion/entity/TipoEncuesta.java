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
@Table(name = "tipo_encuesta")
@NamedQueries({
    @NamedQuery(name = "TipoEncuesta.findAll", query = "SELECT t FROM TipoEncuesta t"),
    @NamedQuery(name = "TipoEncuesta.findByIdTipoencuesta", query = "SELECT t FROM TipoEncuesta t WHERE t.idTipoencuesta = :idTipoencuesta"),
    @NamedQuery(name = "TipoEncuesta.findByDescripcionTipoencuesta", query = "SELECT t FROM TipoEncuesta t WHERE t.descripcionTipoencuesta = :descripcionTipoencuesta")})
public class TipoEncuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoencuesta")
    private Long idTipoencuesta;
    @Column(name = "descripcion_tipoencuesta")
    private String descripcionTipoencuesta;
    @OneToMany(mappedBy = "idTipoencuesta")
    private List<Encuesta> encuestaList;

    public TipoEncuesta() {
    }

    public TipoEncuesta(Long idTipoencuesta) {
        this.idTipoencuesta = idTipoencuesta;
    }

    public Long getIdTipoencuesta() {
        return idTipoencuesta;
    }

    public void setIdTipoencuesta(Long idTipoencuesta) {
        this.idTipoencuesta = idTipoencuesta;
    }

    public String getDescripcionTipoencuesta() {
        return descripcionTipoencuesta;
    }

    public void setDescripcionTipoencuesta(String descripcionTipoencuesta) {
        this.descripcionTipoencuesta = descripcionTipoencuesta;
    }

    public List<Encuesta> getEncuestaList() {
        return encuestaList;
    }

    public void setEncuestaList(List<Encuesta> encuestaList) {
        this.encuestaList = encuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoencuesta != null ? idTipoencuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEncuesta)) {
            return false;
        }
        TipoEncuesta other = (TipoEncuesta) object;
        if ((this.idTipoencuesta == null && other.idTipoencuesta != null) || (this.idTipoencuesta != null && !this.idTipoencuesta.equals(other.idTipoencuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoEncuesta[ idTipoencuesta=" + idTipoencuesta + " ]";
    }
    
}
