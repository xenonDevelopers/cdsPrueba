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
@Table(name = "fotografia")
@NamedQueries({
    @NamedQuery(name = "Fotografia.findAll", query = "SELECT f FROM Fotografia f"),
    @NamedQuery(name = "Fotografia.findByIdFotografia", query = "SELECT f FROM Fotografia f WHERE f.idFotografia = :idFotografia"),
    @NamedQuery(name = "Fotografia.findByExtension", query = "SELECT f FROM Fotografia f WHERE f.extension = :extension")})
public class Fotografia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fotografia")
    private Long idFotografia;
    @Column(name = "extension")
    private String extension;
    @JoinColumn(name = "curp", referencedColumnName = "curp")
    @ManyToOne
    private Persona curp;

    public Fotografia() {
    }

    public Fotografia(Long idFotografia) {
        this.idFotografia = idFotografia;
    }

    public Long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Long idFotografia) {
        this.idFotografia = idFotografia;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
        hash += (idFotografia != null ? idFotografia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotografia)) {
            return false;
        }
        Fotografia other = (Fotografia) object;
        if ((this.idFotografia == null && other.idFotografia != null) || (this.idFotografia != null && !this.idFotografia.equals(other.idFotografia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Fotografia[ idFotografia=" + idFotografia + " ]";
    }
    
}
