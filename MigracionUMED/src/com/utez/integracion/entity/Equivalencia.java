/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "equivalencia")
@NamedQueries({
    @NamedQuery(name = "Equivalencia.findAll", query = "SELECT e FROM Equivalencia e"),
    @NamedQuery(name = "Equivalencia.findByIdProgramacion", query = "SELECT e FROM Equivalencia e WHERE e.idProgramacion = :idProgramacion"),
    @NamedQuery(name = "Equivalencia.findByNumero", query = "SELECT e FROM Equivalencia e WHERE e.numero = :numero"),
    @NamedQuery(name = "Equivalencia.findByFecha", query = "SELECT e FROM Equivalencia e WHERE e.fecha = :fecha")})
public class Equivalencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_programacion")
    private Long idProgramacion;
    @Column(name = "numero")
    private String numero;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_programacion", referencedColumnName = "id_programacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Programacion programacion;

    public Equivalencia() {
    }

    public Equivalencia(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Programacion getProgramacion() {
        return programacion;
    }

    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramacion != null ? idProgramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equivalencia)) {
            return false;
        }
        Equivalencia other = (Equivalencia) object;
        if ((this.idProgramacion == null && other.idProgramacion != null) || (this.idProgramacion != null && !this.idProgramacion.equals(other.idProgramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Equivalencia[ idProgramacion=" + idProgramacion + " ]";
    }
    
}
