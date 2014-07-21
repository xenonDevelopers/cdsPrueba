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
@Table(name = "municipio")
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIdMunicipio", query = "SELECT m FROM Municipio m WHERE m.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "Municipio.findByDescripcionMunicipio", query = "SELECT m FROM Municipio m WHERE m.descripcionMunicipio = :descripcionMunicipio"),
    @NamedQuery(name = "Municipio.findByDescripcionMunicipioMayusculas", query = "SELECT m FROM Municipio m WHERE m.descripcionMunicipioMayusculas = :descripcionMunicipioMayusculas")})
public class Municipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_municipio")
    private Long idMunicipio;
    @Column(name = "descripcion_municipio")
    private String descripcionMunicipio;
    @Column(name = "descripcion_municipio_mayusculas")
    private String descripcionMunicipioMayusculas;
    @JoinColumn(name = "id_entidadfederativa", referencedColumnName = "id_entidadfederativa")
    @ManyToOne
    private EntidadFederativa idEntidadfederativa;
    @OneToMany(mappedBy = "idMunicipionacimiento")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idMunicipio")
    private List<Asentamiento> asentamientoList;

    public Municipio() {
    }

    public Municipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDescripcionMunicipio() {
        return descripcionMunicipio;
    }

    public void setDescripcionMunicipio(String descripcionMunicipio) {
        this.descripcionMunicipio = descripcionMunicipio;
    }

    public String getDescripcionMunicipioMayusculas() {
        return descripcionMunicipioMayusculas;
    }

    public void setDescripcionMunicipioMayusculas(String descripcionMunicipioMayusculas) {
        this.descripcionMunicipioMayusculas = descripcionMunicipioMayusculas;
    }

    public EntidadFederativa getIdEntidadfederativa() {
        return idEntidadfederativa;
    }

    public void setIdEntidadfederativa(EntidadFederativa idEntidadfederativa) {
        this.idEntidadfederativa = idEntidadfederativa;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public List<Asentamiento> getAsentamientoList() {
        return asentamientoList;
    }

    public void setAsentamientoList(List<Asentamiento> asentamientoList) {
        this.asentamientoList = asentamientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Municipio[ idMunicipio=" + idMunicipio + " ]";
    }
    
}
