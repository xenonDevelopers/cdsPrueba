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
@Table(name = "tipo_baja")
@NamedQueries({
    @NamedQuery(name = "TipoBaja.findAll", query = "SELECT t FROM TipoBaja t"),
    @NamedQuery(name = "TipoBaja.findByIdTipobaja", query = "SELECT t FROM TipoBaja t WHERE t.idTipobaja = :idTipobaja"),
    @NamedQuery(name = "TipoBaja.findByDescripcionTipobaja", query = "SELECT t FROM TipoBaja t WHERE t.descripcionTipobaja = :descripcionTipobaja")})
public class TipoBaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipobaja")
    private Long idTipobaja;
    @Column(name = "descripcion_tipobaja")
    private String descripcionTipobaja;
    @OneToMany(mappedBy = "idTipobaja")
    private List<SolicitudBaja> solicitudBajaList;

    public TipoBaja() {
    }

    public TipoBaja(Long idTipobaja) {
        this.idTipobaja = idTipobaja;
    }

    public Long getIdTipobaja() {
        return idTipobaja;
    }

    public void setIdTipobaja(Long idTipobaja) {
        this.idTipobaja = idTipobaja;
    }

    public String getDescripcionTipobaja() {
        return descripcionTipobaja;
    }

    public void setDescripcionTipobaja(String descripcionTipobaja) {
        this.descripcionTipobaja = descripcionTipobaja;
    }

    public List<SolicitudBaja> getSolicitudBajaList() {
        return solicitudBajaList;
    }

    public void setSolicitudBajaList(List<SolicitudBaja> solicitudBajaList) {
        this.solicitudBajaList = solicitudBajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipobaja != null ? idTipobaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBaja)) {
            return false;
        }
        TipoBaja other = (TipoBaja) object;
        if ((this.idTipobaja == null && other.idTipobaja != null) || (this.idTipobaja != null && !this.idTipobaja.equals(other.idTipobaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoBaja[ idTipobaja=" + idTipobaja + " ]";
    }
    
}
