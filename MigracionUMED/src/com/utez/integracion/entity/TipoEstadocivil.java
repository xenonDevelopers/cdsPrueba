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
@Table(name = "tipo_estadocivil")
@NamedQueries({
    @NamedQuery(name = "TipoEstadocivil.findAll", query = "SELECT t FROM TipoEstadocivil t"),
    @NamedQuery(name = "TipoEstadocivil.findByIdTipoestadocivil", query = "SELECT t FROM TipoEstadocivil t WHERE t.idTipoestadocivil = :idTipoestadocivil"),
    @NamedQuery(name = "TipoEstadocivil.findByDescripcionTipoestadocivil", query = "SELECT t FROM TipoEstadocivil t WHERE t.descripcionTipoestadocivil = :descripcionTipoestadocivil")})
public class TipoEstadocivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoestadocivil")
    private Long idTipoestadocivil;
    @Column(name = "descripcion_tipoestadocivil")
    private String descripcionTipoestadocivil;
    @OneToMany(mappedBy = "idTipoestadocivil")
    private List<Persona> personaList;

    public TipoEstadocivil() {
    }

    public TipoEstadocivil(Long idTipoestadocivil) {
        this.idTipoestadocivil = idTipoestadocivil;
    }

    public Long getIdTipoestadocivil() {
        return idTipoestadocivil;
    }

    public void setIdTipoestadocivil(Long idTipoestadocivil) {
        this.idTipoestadocivil = idTipoestadocivil;
    }

    public String getDescripcionTipoestadocivil() {
        return descripcionTipoestadocivil;
    }

    public void setDescripcionTipoestadocivil(String descripcionTipoestadocivil) {
        this.descripcionTipoestadocivil = descripcionTipoestadocivil;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoestadocivil != null ? idTipoestadocivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstadocivil)) {
            return false;
        }
        TipoEstadocivil other = (TipoEstadocivil) object;
        if ((this.idTipoestadocivil == null && other.idTipoestadocivil != null) || (this.idTipoestadocivil != null && !this.idTipoestadocivil.equals(other.idTipoestadocivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoEstadocivil[ idTipoestadocivil=" + idTipoestadocivil + " ]";
    }
    
}
