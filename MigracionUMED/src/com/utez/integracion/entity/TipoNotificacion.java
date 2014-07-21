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
@Table(name = "tipo_notificacion")
@NamedQueries({
    @NamedQuery(name = "TipoNotificacion.findAll", query = "SELECT t FROM TipoNotificacion t"),
    @NamedQuery(name = "TipoNotificacion.findByIdTiponotificacion", query = "SELECT t FROM TipoNotificacion t WHERE t.idTiponotificacion = :idTiponotificacion"),
    @NamedQuery(name = "TipoNotificacion.findByDescripcionTiponotificacion", query = "SELECT t FROM TipoNotificacion t WHERE t.descripcionTiponotificacion = :descripcionTiponotificacion")})
public class TipoNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiponotificacion")
    private Long idTiponotificacion;
    @Column(name = "descripcion_tiponotificacion")
    private String descripcionTiponotificacion;
    @OneToMany(mappedBy = "idTiponotificacion")
    private List<BitacoraNotificacion> bitacoraNotificacionList;

    public TipoNotificacion() {
    }

    public TipoNotificacion(Long idTiponotificacion) {
        this.idTiponotificacion = idTiponotificacion;
    }

    public Long getIdTiponotificacion() {
        return idTiponotificacion;
    }

    public void setIdTiponotificacion(Long idTiponotificacion) {
        this.idTiponotificacion = idTiponotificacion;
    }

    public String getDescripcionTiponotificacion() {
        return descripcionTiponotificacion;
    }

    public void setDescripcionTiponotificacion(String descripcionTiponotificacion) {
        this.descripcionTiponotificacion = descripcionTiponotificacion;
    }

    public List<BitacoraNotificacion> getBitacoraNotificacionList() {
        return bitacoraNotificacionList;
    }

    public void setBitacoraNotificacionList(List<BitacoraNotificacion> bitacoraNotificacionList) {
        this.bitacoraNotificacionList = bitacoraNotificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiponotificacion != null ? idTiponotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNotificacion)) {
            return false;
        }
        TipoNotificacion other = (TipoNotificacion) object;
        if ((this.idTiponotificacion == null && other.idTiponotificacion != null) || (this.idTiponotificacion != null && !this.idTiponotificacion.equals(other.idTiponotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoNotificacion[ idTiponotificacion=" + idTiponotificacion + " ]";
    }
    
}
