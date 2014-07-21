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
@Table(name = "tipo_documento")
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipodocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipodocumento = :idTipodocumento"),
    @NamedQuery(name = "TipoDocumento.findByDescripcionTipodocumento", query = "SELECT t FROM TipoDocumento t WHERE t.descripcionTipodocumento = :descripcionTipodocumento")})
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipodocumento")
    private Long idTipodocumento;
    @Column(name = "descripcion_tipodocumento")
    private String descripcionTipodocumento;
    @OneToMany(mappedBy = "idTipodocumento")
    private List<Documento> documentoList;

    public TipoDocumento() {
    }

    public TipoDocumento(Long idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }

    public Long getIdTipodocumento() {
        return idTipodocumento;
    }

    public void setIdTipodocumento(Long idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }

    public String getDescripcionTipodocumento() {
        return descripcionTipodocumento;
    }

    public void setDescripcionTipodocumento(String descripcionTipodocumento) {
        this.descripcionTipodocumento = descripcionTipodocumento;
    }

    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipodocumento != null ? idTipodocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipodocumento == null && other.idTipodocumento != null) || (this.idTipodocumento != null && !this.idTipodocumento.equals(other.idTipodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoDocumento[ idTipodocumento=" + idTipodocumento + " ]";
    }
    
}
