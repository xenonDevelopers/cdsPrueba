/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "inscripcion_equivalencia")
@NamedQueries({
    @NamedQuery(name = "InscripcionEquivalencia.findAll", query = "SELECT i FROM InscripcionEquivalencia i"),
    @NamedQuery(name = "InscripcionEquivalencia.findByIdInscripcionequivalencia", query = "SELECT i FROM InscripcionEquivalencia i WHERE i.idInscripcionequivalencia = :idInscripcionequivalencia"),
    @NamedQuery(name = "InscripcionEquivalencia.findByFechaEquivalencia", query = "SELECT i FROM InscripcionEquivalencia i WHERE i.fechaEquivalencia = :fechaEquivalencia"),
    @NamedQuery(name = "InscripcionEquivalencia.findByNumeroEquivalencia", query = "SELECT i FROM InscripcionEquivalencia i WHERE i.numeroEquivalencia = :numeroEquivalencia")})
public class InscripcionEquivalencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inscripcionequivalencia")
    private Long idInscripcionequivalencia;
    @Column(name = "fecha_equivalencia")
    @Temporal(TemporalType.DATE)
    private Date fechaEquivalencia;
    @Column(name = "numero_equivalencia")
    private Integer numeroEquivalencia;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public InscripcionEquivalencia() {
    }

    public InscripcionEquivalencia(Long idInscripcionequivalencia) {
        this.idInscripcionequivalencia = idInscripcionequivalencia;
    }

    public Long getIdInscripcionequivalencia() {
        return idInscripcionequivalencia;
    }

    public void setIdInscripcionequivalencia(Long idInscripcionequivalencia) {
        this.idInscripcionequivalencia = idInscripcionequivalencia;
    }

    public Date getFechaEquivalencia() {
        return fechaEquivalencia;
    }

    public void setFechaEquivalencia(Date fechaEquivalencia) {
        this.fechaEquivalencia = fechaEquivalencia;
    }

    public Integer getNumeroEquivalencia() {
        return numeroEquivalencia;
    }

    public void setNumeroEquivalencia(Integer numeroEquivalencia) {
        this.numeroEquivalencia = numeroEquivalencia;
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
        hash += (idInscripcionequivalencia != null ? idInscripcionequivalencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionEquivalencia)) {
            return false;
        }
        InscripcionEquivalencia other = (InscripcionEquivalencia) object;
        if ((this.idInscripcionequivalencia == null && other.idInscripcionequivalencia != null) || (this.idInscripcionequivalencia != null && !this.idInscripcionequivalencia.equals(other.idInscripcionequivalencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.InscripcionEquivalencia[ idInscripcionequivalencia=" + idInscripcionequivalencia + " ]";
    }
    
}
