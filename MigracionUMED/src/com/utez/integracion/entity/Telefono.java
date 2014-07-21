/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "telefono")
@NamedQueries({
    @NamedQuery(name = "Telefono.findAll", query = "SELECT t FROM Telefono t"),
    @NamedQuery(name = "Telefono.findByIdTelefono", query = "SELECT t FROM Telefono t WHERE t.idTelefono = :idTelefono"),
    @NamedQuery(name = "Telefono.findByTelefono", query = "SELECT t FROM Telefono t WHERE t.telefono = :telefono")})
public class Telefono implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_telefono")
    private Long idTelefono;
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "id_tipotelefono", referencedColumnName = "id_tipotelefono")
    @ManyToOne
    private TipoTelefono idTipotelefono;
    @JoinColumn(name = "curp", referencedColumnName = "curp")
    @ManyToOne
    private Persona curp;

    public Telefono() {
    }

    public Telefono(Long idTelefono) {
        this.idTelefono = idTelefono;
    }

    public Long getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Long idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoTelefono getIdTipotelefono() {
        return idTipotelefono;
    }

    public void setIdTipotelefono(TipoTelefono idTipotelefono) {
        this.idTipotelefono = idTipotelefono;
    }

    public Persona getCurp() {
        return curp;
    }

    public void setCurp(Persona curp) {
        this.curp = curp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefono != null ? idTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefono)) {
            return false;
        }
        Telefono other = (Telefono) object;
        if ((this.idTelefono == null && other.idTelefono != null) || (this.idTelefono != null && !this.idTelefono.equals(other.idTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Telefono[ idTelefono=" + idTelefono + " ]";
    }
    
}
