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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tipo_titulacion")
@NamedQueries({
    @NamedQuery(name = "TipoTitulacion.findAll", query = "SELECT t FROM TipoTitulacion t"),
    @NamedQuery(name = "TipoTitulacion.findByIdTipotitulacion", query = "SELECT t FROM TipoTitulacion t WHERE t.idTipotitulacion = :idTipotitulacion"),
    @NamedQuery(name = "TipoTitulacion.findByDescripcionTipotitulacion", query = "SELECT t FROM TipoTitulacion t WHERE t.descripcionTipotitulacion = :descripcionTipotitulacion")})
public class TipoTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipotitulacion")
    private Long idTipotitulacion;
    @Column(name = "descripcion_tipotitulacion")
    private String descripcionTipotitulacion;
    @ManyToMany(mappedBy = "tipoTitulacionList")
    private List<PlanEstudio> planEstudioList;
    @ManyToMany(mappedBy = "tipoTitulacionList")
    private List<Documento> documentoList;
    @OneToMany(mappedBy = "idTipotitulacion")
    private List<SolicitudTitulacion> solicitudTitulacionList;

    public TipoTitulacion() {
    }

    public TipoTitulacion(Long idTipotitulacion) {
        this.idTipotitulacion = idTipotitulacion;
    }

    public Long getIdTipotitulacion() {
        return idTipotitulacion;
    }

    public void setIdTipotitulacion(Long idTipotitulacion) {
        this.idTipotitulacion = idTipotitulacion;
    }

    public String getDescripcionTipotitulacion() {
        return descripcionTipotitulacion;
    }

    public void setDescripcionTipotitulacion(String descripcionTipotitulacion) {
        this.descripcionTipotitulacion = descripcionTipotitulacion;
    }

    public List<PlanEstudio> getPlanEstudioList() {
        return planEstudioList;
    }

    public void setPlanEstudioList(List<PlanEstudio> planEstudioList) {
        this.planEstudioList = planEstudioList;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public List<SolicitudTitulacion> getSolicitudTitulacionList() {
        return solicitudTitulacionList;
    }

    public void setSolicitudTitulacionList(List<SolicitudTitulacion> solicitudTitulacionList) {
        this.solicitudTitulacionList = solicitudTitulacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipotitulacion != null ? idTipotitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTitulacion)) {
            return false;
        }
        TipoTitulacion other = (TipoTitulacion) object;
        if ((this.idTipotitulacion == null && other.idTipotitulacion != null) || (this.idTipotitulacion != null && !this.idTipotitulacion.equals(other.idTipotitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoTitulacion[ idTipotitulacion=" + idTipotitulacion + " ]";
    }
    
}
