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
@Table(name = "entidad_federativa")
@NamedQueries({
    @NamedQuery(name = "EntidadFederativa.findAll", query = "SELECT e FROM EntidadFederativa e"),
    @NamedQuery(name = "EntidadFederativa.findByIdEntidadfederativa", query = "SELECT e FROM EntidadFederativa e WHERE e.idEntidadfederativa = :idEntidadfederativa"),
    @NamedQuery(name = "EntidadFederativa.findByDescripcionEntidadfederativa", query = "SELECT e FROM EntidadFederativa e WHERE e.descripcionEntidadfederativa = :descripcionEntidadfederativa"),
    @NamedQuery(name = "EntidadFederativa.findByDescripcionEntidadfederativaMayusculas", query = "SELECT e FROM EntidadFederativa e WHERE e.descripcionEntidadfederativaMayusculas = :descripcionEntidadfederativaMayusculas")})
public class EntidadFederativa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entidadfederativa")
    private Long idEntidadfederativa;
    @Column(name = "descripcion_entidadfederativa")
    private String descripcionEntidadfederativa;
    @Column(name = "descripcion_entidadfederativa_mayusculas")
    private String descripcionEntidadfederativaMayusculas;
    @OneToMany(mappedBy = "idEntidadfederativa")
    private List<Municipio> municipioList;

    public EntidadFederativa() {
    }

    public EntidadFederativa(Long idEntidadfederativa) {
        this.idEntidadfederativa = idEntidadfederativa;
    }

    public Long getIdEntidadfederativa() {
        return idEntidadfederativa;
    }

    public void setIdEntidadfederativa(Long idEntidadfederativa) {
        this.idEntidadfederativa = idEntidadfederativa;
    }

    public String getDescripcionEntidadfederativa() {
        return descripcionEntidadfederativa;
    }

    public void setDescripcionEntidadfederativa(String descripcionEntidadfederativa) {
        this.descripcionEntidadfederativa = descripcionEntidadfederativa;
    }

    public String getDescripcionEntidadfederativaMayusculas() {
        return descripcionEntidadfederativaMayusculas;
    }

    public void setDescripcionEntidadfederativaMayusculas(String descripcionEntidadfederativaMayusculas) {
        this.descripcionEntidadfederativaMayusculas = descripcionEntidadfederativaMayusculas;
    }

    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidadfederativa != null ? idEntidadfederativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadFederativa)) {
            return false;
        }
        EntidadFederativa other = (EntidadFederativa) object;
        if ((this.idEntidadfederativa == null && other.idEntidadfederativa != null) || (this.idEntidadfederativa != null && !this.idEntidadfederativa.equals(other.idEntidadfederativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.EntidadFederativa[ idEntidadfederativa=" + idEntidadfederativa + " ]";
    }
    
}
