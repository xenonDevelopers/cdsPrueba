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
@Table(name = "tipo_ordentrabajo")
@NamedQueries({
    @NamedQuery(name = "TipoOrdentrabajo.findAll", query = "SELECT t FROM TipoOrdentrabajo t"),
    @NamedQuery(name = "TipoOrdentrabajo.findByIdTipoordentrabajo", query = "SELECT t FROM TipoOrdentrabajo t WHERE t.idTipoordentrabajo = :idTipoordentrabajo"),
    @NamedQuery(name = "TipoOrdentrabajo.findByDescripcion", query = "SELECT t FROM TipoOrdentrabajo t WHERE t.descripcion = :descripcion")})
public class TipoOrdentrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoordentrabajo")
    private Integer idTipoordentrabajo;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoordentrabajo")
    private List<OrdenTrabajo> ordenTrabajoList;

    public TipoOrdentrabajo() {
    }

    public TipoOrdentrabajo(Integer idTipoordentrabajo) {
        this.idTipoordentrabajo = idTipoordentrabajo;
    }

    public Integer getIdTipoordentrabajo() {
        return idTipoordentrabajo;
    }

    public void setIdTipoordentrabajo(Integer idTipoordentrabajo) {
        this.idTipoordentrabajo = idTipoordentrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<OrdenTrabajo> getOrdenTrabajoList() {
        return ordenTrabajoList;
    }

    public void setOrdenTrabajoList(List<OrdenTrabajo> ordenTrabajoList) {
        this.ordenTrabajoList = ordenTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoordentrabajo != null ? idTipoordentrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOrdentrabajo)) {
            return false;
        }
        TipoOrdentrabajo other = (TipoOrdentrabajo) object;
        if ((this.idTipoordentrabajo == null && other.idTipoordentrabajo != null) || (this.idTipoordentrabajo != null && !this.idTipoordentrabajo.equals(other.idTipoordentrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoOrdentrabajo[ idTipoordentrabajo=" + idTipoordentrabajo + " ]";
    }
    
}
