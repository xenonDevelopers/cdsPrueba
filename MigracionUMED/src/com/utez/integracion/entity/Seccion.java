/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "seccion")
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion"),
    @NamedQuery(name = "Seccion.findByNombreSeccion", query = "SELECT s FROM Seccion s WHERE s.nombreSeccion = :nombreSeccion"),
    @NamedQuery(name = "Seccion.findByInstruccionSeccion", query = "SELECT s FROM Seccion s WHERE s.instruccionSeccion = :instruccionSeccion"),
    @NamedQuery(name = "Seccion.findByIdSeccionsuperior", query = "SELECT s FROM Seccion s WHERE s.idSeccionsuperior = :idSeccionsuperior")})
public class Seccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seccion")
    private Long idSeccion;
    @Column(name = "nombre_seccion")
    private String nombreSeccion;
    @Column(name = "instruccion_seccion")
    private String instruccionSeccion;
    @Column(name = "id_seccionsuperior")
    private BigInteger idSeccionsuperior;
    @OneToMany(mappedBy = "idSeccion")
    private List<AsignacionSeccionencuesta> asignacionSeccionencuestaList;
    @OneToMany(mappedBy = "idSeccion")
    private List<AsignacionPreguntaseccion> asignacionPreguntaseccionList;

    public Seccion() {
    }

    public Seccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getInstruccionSeccion() {
        return instruccionSeccion;
    }

    public void setInstruccionSeccion(String instruccionSeccion) {
        this.instruccionSeccion = instruccionSeccion;
    }

    public BigInteger getIdSeccionsuperior() {
        return idSeccionsuperior;
    }

    public void setIdSeccionsuperior(BigInteger idSeccionsuperior) {
        this.idSeccionsuperior = idSeccionsuperior;
    }

    public List<AsignacionSeccionencuesta> getAsignacionSeccionencuestaList() {
        return asignacionSeccionencuestaList;
    }

    public void setAsignacionSeccionencuestaList(List<AsignacionSeccionencuesta> asignacionSeccionencuestaList) {
        this.asignacionSeccionencuestaList = asignacionSeccionencuestaList;
    }

    public List<AsignacionPreguntaseccion> getAsignacionPreguntaseccionList() {
        return asignacionPreguntaseccionList;
    }

    public void setAsignacionPreguntaseccionList(List<AsignacionPreguntaseccion> asignacionPreguntaseccionList) {
        this.asignacionPreguntaseccionList = asignacionPreguntaseccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Seccion[ idSeccion=" + idSeccion + " ]";
    }
    
}
