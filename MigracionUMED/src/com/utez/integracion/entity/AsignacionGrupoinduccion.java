/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
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
@Table(name = "asignacion_grupoinduccion")
@NamedQueries({
    @NamedQuery(name = "AsignacionGrupoinduccion.findAll", query = "SELECT a FROM AsignacionGrupoinduccion a"),
    @NamedQuery(name = "AsignacionGrupoinduccion.findByIdAsignacionGrupoinduccion", query = "SELECT a FROM AsignacionGrupoinduccion a WHERE a.idAsignacionGrupoinduccion = :idAsignacionGrupoinduccion")})
public class AsignacionGrupoinduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacion_grupoinduccion")
    private Long idAsignacionGrupoinduccion;
    @JoinColumn(name = "id_grupoinduccion", referencedColumnName = "id_grupoinduccion")
    @ManyToOne
    private GrupoInduccion idGrupoinduccion;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public AsignacionGrupoinduccion() {
    }

    public AsignacionGrupoinduccion(Long idAsignacionGrupoinduccion) {
        this.idAsignacionGrupoinduccion = idAsignacionGrupoinduccion;
    }

    public Long getIdAsignacionGrupoinduccion() {
        return idAsignacionGrupoinduccion;
    }

    public void setIdAsignacionGrupoinduccion(Long idAsignacionGrupoinduccion) {
        this.idAsignacionGrupoinduccion = idAsignacionGrupoinduccion;
    }

    public GrupoInduccion getIdGrupoinduccion() {
        return idGrupoinduccion;
    }

    public void setIdGrupoinduccion(GrupoInduccion idGrupoinduccion) {
        this.idGrupoinduccion = idGrupoinduccion;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionGrupoinduccion != null ? idAsignacionGrupoinduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionGrupoinduccion)) {
            return false;
        }
        AsignacionGrupoinduccion other = (AsignacionGrupoinduccion) object;
        if ((this.idAsignacionGrupoinduccion == null && other.idAsignacionGrupoinduccion != null) || (this.idAsignacionGrupoinduccion != null && !this.idAsignacionGrupoinduccion.equals(other.idAsignacionGrupoinduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionGrupoinduccion[ idAsignacionGrupoinduccion=" + idAsignacionGrupoinduccion + " ]";
    }
    
}
