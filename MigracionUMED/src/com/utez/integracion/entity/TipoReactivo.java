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
@Table(name = "tipo_reactivo")
@NamedQueries({
    @NamedQuery(name = "TipoReactivo.findAll", query = "SELECT t FROM TipoReactivo t"),
    @NamedQuery(name = "TipoReactivo.findByIdTiporeactivo", query = "SELECT t FROM TipoReactivo t WHERE t.idTiporeactivo = :idTiporeactivo"),
    @NamedQuery(name = "TipoReactivo.findByDescripcionTiporeactivo", query = "SELECT t FROM TipoReactivo t WHERE t.descripcionTiporeactivo = :descripcionTiporeactivo")})
public class TipoReactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tiporeactivo")
    private Long idTiporeactivo;
    @Column(name = "descripcion_tiporeactivo")
    private String descripcionTiporeactivo;
    @OneToMany(mappedBy = "idTiporeactivo")
    private List<Reactivo> reactivoList;

    public TipoReactivo() {
    }

    public TipoReactivo(Long idTiporeactivo) {
        this.idTiporeactivo = idTiporeactivo;
    }

    public Long getIdTiporeactivo() {
        return idTiporeactivo;
    }

    public void setIdTiporeactivo(Long idTiporeactivo) {
        this.idTiporeactivo = idTiporeactivo;
    }

    public String getDescripcionTiporeactivo() {
        return descripcionTiporeactivo;
    }

    public void setDescripcionTiporeactivo(String descripcionTiporeactivo) {
        this.descripcionTiporeactivo = descripcionTiporeactivo;
    }

    public List<Reactivo> getReactivoList() {
        return reactivoList;
    }

    public void setReactivoList(List<Reactivo> reactivoList) {
        this.reactivoList = reactivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiporeactivo != null ? idTiporeactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReactivo)) {
            return false;
        }
        TipoReactivo other = (TipoReactivo) object;
        if ((this.idTiporeactivo == null && other.idTiporeactivo != null) || (this.idTiporeactivo != null && !this.idTiporeactivo.equals(other.idTiporeactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoReactivo[ idTiporeactivo=" + idTiporeactivo + " ]";
    }
    
}
