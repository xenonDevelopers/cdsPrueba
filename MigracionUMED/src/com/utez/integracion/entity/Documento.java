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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "documento")
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByIdDocumento", query = "SELECT d FROM Documento d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documento.findByDescripcionDocumento", query = "SELECT d FROM Documento d WHERE d.descripcionDocumento = :descripcionDocumento")})
public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Long idDocumento;
    @Column(name = "descripcion_documento")
    private String descripcionDocumento;
    @JoinTable(name = "documento_tipotitulacion", joinColumns = {
        @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tipotitulacion", referencedColumnName = "id_tipotitulacion")})
    @ManyToMany
    private List<TipoTitulacion> tipoTitulacionList;
    @JoinColumn(name = "id_tipodocumento", referencedColumnName = "id_tipodocumento")
    @ManyToOne
    private TipoDocumento idTipodocumento;
    @OneToMany(mappedBy = "idDocumento")
    private List<ControlDocumento> controlDocumentoList;
    @OneToMany(mappedBy = "idDocumento")
    private List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoList;

    public Documento() {
    }

    public Documento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }

    public List<TipoTitulacion> getTipoTitulacionList() {
        return tipoTitulacionList;
    }

    public void setTipoTitulacionList(List<TipoTitulacion> tipoTitulacionList) {
        this.tipoTitulacionList = tipoTitulacionList;
    }

    public TipoDocumento getIdTipodocumento() {
        return idTipodocumento;
    }

    public void setIdTipodocumento(TipoDocumento idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }

    public List<ControlDocumento> getControlDocumentoList() {
        return controlDocumentoList;
    }

    public void setControlDocumentoList(List<ControlDocumento> controlDocumentoList) {
        this.controlDocumentoList = controlDocumentoList;
    }

    public List<AsignacionRecursohumanodocumento> getAsignacionRecursohumanodocumentoList() {
        return asignacionRecursohumanodocumentoList;
    }

    public void setAsignacionRecursohumanodocumentoList(List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoList) {
        this.asignacionRecursohumanodocumentoList = asignacionRecursohumanodocumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Documento[ idDocumento=" + idDocumento + " ]";
    }
    
}
