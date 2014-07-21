/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tema_solicitudautorizacion")
@NamedQueries({
    @NamedQuery(name = "TemaSolicitudautorizacion.findAll", query = "SELECT t FROM TemaSolicitudautorizacion t"),
    @NamedQuery(name = "TemaSolicitudautorizacion.findByIdSolicitudtitulacion", query = "SELECT t FROM TemaSolicitudautorizacion t WHERE t.idSolicitudtitulacion = :idSolicitudtitulacion"),
    @NamedQuery(name = "TemaSolicitudautorizacion.findByTema", query = "SELECT t FROM TemaSolicitudautorizacion t WHERE t.tema = :tema")})
public class TemaSolicitudautorizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_solicitudtitulacion")
    private Long idSolicitudtitulacion;
    @Column(name = "tema")
    private String tema;
    @JoinColumn(name = "id_solicitudtitulacion", referencedColumnName = "id_solicitudtitulacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SolicitudTitulacion solicitudTitulacion;

    public TemaSolicitudautorizacion() {
    }

    public TemaSolicitudautorizacion(Long idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public Long getIdSolicitudtitulacion() {
        return idSolicitudtitulacion;
    }

    public void setIdSolicitudtitulacion(Long idSolicitudtitulacion) {
        this.idSolicitudtitulacion = idSolicitudtitulacion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public SolicitudTitulacion getSolicitudTitulacion() {
        return solicitudTitulacion;
    }

    public void setSolicitudTitulacion(SolicitudTitulacion solicitudTitulacion) {
        this.solicitudTitulacion = solicitudTitulacion;
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
        if (!(object instanceof TemaSolicitudautorizacion)) {
            return false;
        }
        TemaSolicitudautorizacion other = (TemaSolicitudautorizacion) object;
        if ((this.idSolicitudtitulacion == null && other.idSolicitudtitulacion != null) || (this.idSolicitudtitulacion != null && !this.idSolicitudtitulacion.equals(other.idSolicitudtitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TemaSolicitudautorizacion[ idSolicitudtitulacion=" + idSolicitudtitulacion + " ]";
    }
    
}
