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
@Table(name = "tipo_acta")
@NamedQueries({
    @NamedQuery(name = "TipoActa.findAll", query = "SELECT t FROM TipoActa t"),
    @NamedQuery(name = "TipoActa.findByIdTipoacta", query = "SELECT t FROM TipoActa t WHERE t.idTipoacta = :idTipoacta"),
    @NamedQuery(name = "TipoActa.findByDescripcionTipoacta", query = "SELECT t FROM TipoActa t WHERE t.descripcionTipoacta = :descripcionTipoacta")})
public class TipoActa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoacta")
    private Integer idTipoacta;
    @Column(name = "descripcion_tipoacta")
    private String descripcionTipoacta;
    @OneToMany(mappedBy = "idTipoacta")
    private List<Acta> actaList;

    public TipoActa() {
    }

    public TipoActa(Integer idTipoacta) {
        this.idTipoacta = idTipoacta;
    }

    public Integer getIdTipoacta() {
        return idTipoacta;
    }

    public void setIdTipoacta(Integer idTipoacta) {
        this.idTipoacta = idTipoacta;
    }

    public String getDescripcionTipoacta() {
        return descripcionTipoacta;
    }

    public void setDescripcionTipoacta(String descripcionTipoacta) {
        this.descripcionTipoacta = descripcionTipoacta;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoacta != null ? idTipoacta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActa)) {
            return false;
        }
        TipoActa other = (TipoActa) object;
        if ((this.idTipoacta == null && other.idTipoacta != null) || (this.idTipoacta != null && !this.idTipoacta.equals(other.idTipoacta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoActa[ idTipoacta=" + idTipoacta + " ]";
    }
    
}
