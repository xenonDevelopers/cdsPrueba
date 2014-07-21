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
@Table(name = "tipo_ambito")
@NamedQueries({
    @NamedQuery(name = "TipoAmbito.findAll", query = "SELECT t FROM TipoAmbito t"),
    @NamedQuery(name = "TipoAmbito.findByIdTipoambito", query = "SELECT t FROM TipoAmbito t WHERE t.idTipoambito = :idTipoambito"),
    @NamedQuery(name = "TipoAmbito.findByDescripcionTipoambito", query = "SELECT t FROM TipoAmbito t WHERE t.descripcionTipoambito = :descripcionTipoambito")})
public class TipoAmbito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoambito")
    private Long idTipoambito;
    @Column(name = "descripcion_tipoambito")
    private String descripcionTipoambito;
    @OneToMany(mappedBy = "idTipoambito")
    private List<Reactivo> reactivoList;

    public TipoAmbito() {
    }

    public TipoAmbito(Long idTipoambito) {
        this.idTipoambito = idTipoambito;
    }

    public Long getIdTipoambito() {
        return idTipoambito;
    }

    public void setIdTipoambito(Long idTipoambito) {
        this.idTipoambito = idTipoambito;
    }

    public String getDescripcionTipoambito() {
        return descripcionTipoambito;
    }

    public void setDescripcionTipoambito(String descripcionTipoambito) {
        this.descripcionTipoambito = descripcionTipoambito;
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
        hash += (idTipoambito != null ? idTipoambito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAmbito)) {
            return false;
        }
        TipoAmbito other = (TipoAmbito) object;
        if ((this.idTipoambito == null && other.idTipoambito != null) || (this.idTipoambito != null && !this.idTipoambito.equals(other.idTipoambito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoAmbito[ idTipoambito=" + idTipoambito + " ]";
    }
    
}
