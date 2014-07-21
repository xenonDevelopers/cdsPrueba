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
@Table(name = "tipo_telefono")
@NamedQueries({
    @NamedQuery(name = "TipoTelefono.findAll", query = "SELECT t FROM TipoTelefono t"),
    @NamedQuery(name = "TipoTelefono.findByIdTipotelefono", query = "SELECT t FROM TipoTelefono t WHERE t.idTipotelefono = :idTipotelefono"),
    @NamedQuery(name = "TipoTelefono.findByDescripcionTipotelefono", query = "SELECT t FROM TipoTelefono t WHERE t.descripcionTipotelefono = :descripcionTipotelefono")})
public class TipoTelefono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipotelefono")
    private Long idTipotelefono;
    @Column(name = "descripcion_tipotelefono")
    private String descripcionTipotelefono;
    @OneToMany(mappedBy = "idTipotelefono")
    private List<Telefono> telefonoList;

    public TipoTelefono() {
    }

    public TipoTelefono(Long idTipotelefono) {
        this.idTipotelefono = idTipotelefono;
    }

    public Long getIdTipotelefono() {
        return idTipotelefono;
    }

    public void setIdTipotelefono(Long idTipotelefono) {
        this.idTipotelefono = idTipotelefono;
    }

    public String getDescripcionTipotelefono() {
        return descripcionTipotelefono;
    }

    public void setDescripcionTipotelefono(String descripcionTipotelefono) {
        this.descripcionTipotelefono = descripcionTipotelefono;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipotelefono != null ? idTipotelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTelefono)) {
            return false;
        }
        TipoTelefono other = (TipoTelefono) object;
        if ((this.idTipotelefono == null && other.idTipotelefono != null) || (this.idTipotelefono != null && !this.idTipotelefono.equals(other.idTipotelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoTelefono[ idTipotelefono=" + idTipotelefono + " ]";
    }
    
}
