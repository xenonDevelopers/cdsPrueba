/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "orden_trabajo")
@NamedQueries({
    @NamedQuery(name = "OrdenTrabajo.findAll", query = "SELECT o FROM OrdenTrabajo o"),
    @NamedQuery(name = "OrdenTrabajo.findByIdOrdentrabajo", query = "SELECT o FROM OrdenTrabajo o WHERE o.idOrdentrabajo = :idOrdentrabajo"),
    @NamedQuery(name = "OrdenTrabajo.findByNumeroOrden", query = "SELECT o FROM OrdenTrabajo o WHERE o.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "OrdenTrabajo.findByFechaOrdentrabajo", query = "SELECT o FROM OrdenTrabajo o WHERE o.fechaOrdentrabajo = :fechaOrdentrabajo"),
    @NamedQuery(name = "OrdenTrabajo.findByImporte", query = "SELECT o FROM OrdenTrabajo o WHERE o.importe = :importe")})
public class OrdenTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ordentrabajo")
    private Integer idOrdentrabajo;
    @Column(name = "numero_orden")
    private Integer numeroOrden;
    @Column(name = "fecha_ordentrabajo")
    @Temporal(TemporalType.DATE)
    private Date fechaOrdentrabajo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Float importe;
    @JoinTable(name = "ordentrabajo_titulacion", joinColumns = {
        @JoinColumn(name = "id_ordentrabajo", referencedColumnName = "id_ordentrabajo")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tramitetitulacion", referencedColumnName = "id_tramitetitulacion")})
    @ManyToMany
    private List<TramiteTitulacion> tramiteTitulacionList;
    @JoinColumn(name = "id_tipoordentrabajo", referencedColumnName = "id_tipoordentrabajo")
    @ManyToOne
    private TipoOrdentrabajo idTipoordentrabajo;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(Integer idOrdentrabajo) {
        this.idOrdentrabajo = idOrdentrabajo;
    }

    public Integer getIdOrdentrabajo() {
        return idOrdentrabajo;
    }

    public void setIdOrdentrabajo(Integer idOrdentrabajo) {
        this.idOrdentrabajo = idOrdentrabajo;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaOrdentrabajo() {
        return fechaOrdentrabajo;
    }

    public void setFechaOrdentrabajo(Date fechaOrdentrabajo) {
        this.fechaOrdentrabajo = fechaOrdentrabajo;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public List<TramiteTitulacion> getTramiteTitulacionList() {
        return tramiteTitulacionList;
    }

    public void setTramiteTitulacionList(List<TramiteTitulacion> tramiteTitulacionList) {
        this.tramiteTitulacionList = tramiteTitulacionList;
    }

    public TipoOrdentrabajo getIdTipoordentrabajo() {
        return idTipoordentrabajo;
    }

    public void setIdTipoordentrabajo(TipoOrdentrabajo idTipoordentrabajo) {
        this.idTipoordentrabajo = idTipoordentrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdentrabajo != null ? idOrdentrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenTrabajo)) {
            return false;
        }
        OrdenTrabajo other = (OrdenTrabajo) object;
        if ((this.idOrdentrabajo == null && other.idOrdentrabajo != null) || (this.idOrdentrabajo != null && !this.idOrdentrabajo.equals(other.idOrdentrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.OrdenTrabajo[ idOrdentrabajo=" + idOrdentrabajo + " ]";
    }
    
}
