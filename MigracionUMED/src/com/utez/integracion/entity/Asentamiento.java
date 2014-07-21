/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Plantel;
import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "asentamiento")
@NamedQueries({
    @NamedQuery(name = "Asentamiento.findAll", query = "SELECT a FROM Asentamiento a"),
    @NamedQuery(name = "Asentamiento.findByIdAsentamiento", query = "SELECT a FROM Asentamiento a WHERE a.idAsentamiento = :idAsentamiento"),
    @NamedQuery(name = "Asentamiento.findByCodigopostal", query = "SELECT a FROM Asentamiento a WHERE a.codigopostal = :codigopostal"),
    @NamedQuery(name = "Asentamiento.findByDescripcionAsentamiento", query = "SELECT a FROM Asentamiento a WHERE a.descripcionAsentamiento = :descripcionAsentamiento"),
    @NamedQuery(name = "Asentamiento.findByDescripcionAsentamientoMayusculas", query = "SELECT a FROM Asentamiento a WHERE a.descripcionAsentamientoMayusculas = :descripcionAsentamientoMayusculas")})
public class Asentamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asentamiento")
    private Long idAsentamiento;
    @Column(name = "codigopostal")
    private BigInteger codigopostal;
    @Column(name = "descripcion_asentamiento")
    private String descripcionAsentamiento;
    @Column(name = "descripcion_asentamiento_mayusculas")
    private String descripcionAsentamientoMayusculas;
    @OneToMany(mappedBy = "idAsentamiento")
    private List<Plantel> plantelList;
    @OneToMany(mappedBy = "idAsentamiento")
    private List<Persona> personaList;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne
    private Municipio idMunicipio;
    @OneToMany(mappedBy = "idAsentamiento")
    private List<FormacionAcademica> formacionAcademicaList;

    public Asentamiento() {
    }

    public Asentamiento(Long idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public Long getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(Long idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public BigInteger getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(BigInteger codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getDescripcionAsentamiento() {
        return descripcionAsentamiento;
    }

    public void setDescripcionAsentamiento(String descripcionAsentamiento) {
        this.descripcionAsentamiento = descripcionAsentamiento;
    }

    public String getDescripcionAsentamientoMayusculas() {
        return descripcionAsentamientoMayusculas;
    }

    public void setDescripcionAsentamientoMayusculas(String descripcionAsentamientoMayusculas) {
        this.descripcionAsentamientoMayusculas = descripcionAsentamientoMayusculas;
    }

    public List<Plantel> getPlantelList() {
        return plantelList;
    }

    public void setPlantelList(List<Plantel> plantelList) {
        this.plantelList = plantelList;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public List<FormacionAcademica> getFormacionAcademicaList() {
        return formacionAcademicaList;
    }

    public void setFormacionAcademicaList(List<FormacionAcademica> formacionAcademicaList) {
        this.formacionAcademicaList = formacionAcademicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsentamiento != null ? idAsentamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asentamiento)) {
            return false;
        }
        Asentamiento other = (Asentamiento) object;
        if ((this.idAsentamiento == null && other.idAsentamiento != null) || (this.idAsentamiento != null && !this.idAsentamiento.equals(other.idAsentamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Asentamiento[ idAsentamiento=" + idAsentamiento + " ]";
    }
    
}
