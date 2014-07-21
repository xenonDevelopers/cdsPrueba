/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_motivo")
@NamedQueries({
    @NamedQuery(name = "TipoMotivo.findAll", query = "SELECT t FROM TipoMotivo t"),
    @NamedQuery(name = "TipoMotivo.findByIdTipomotivo", query = "SELECT t FROM TipoMotivo t WHERE t.idTipomotivo = :idTipomotivo"),
    @NamedQuery(name = "TipoMotivo.findByDescripcionTipomotivo", query = "SELECT t FROM TipoMotivo t WHERE t.descripcionTipomotivo = :descripcionTipomotivo")})
public class TipoMotivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipomotivo")
    private Long idTipomotivo;
    @Column(name = "descripcion_tipomotivo")
    private String descripcionTipomotivo;

    public TipoMotivo() {
    }

    public TipoMotivo(Long idTipomotivo) {
        this.idTipomotivo = idTipomotivo;
    }

    public Long getIdTipomotivo() {
        return idTipomotivo;
    }

    public void setIdTipomotivo(Long idTipomotivo) {
        this.idTipomotivo = idTipomotivo;
    }

    public String getDescripcionTipomotivo() {
        return descripcionTipomotivo;
    }

    public void setDescripcionTipomotivo(String descripcionTipomotivo) {
        this.descripcionTipomotivo = descripcionTipomotivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipomotivo != null ? idTipomotivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMotivo)) {
            return false;
        }
        TipoMotivo other = (TipoMotivo) object;
        if ((this.idTipomotivo == null && other.idTipomotivo != null) || (this.idTipomotivo != null && !this.idTipomotivo.equals(other.idTipomotivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoMotivo[ idTipomotivo=" + idTipomotivo + " ]";
    }
    
}
