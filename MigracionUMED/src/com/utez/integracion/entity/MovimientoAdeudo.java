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
@Table(name = "movimiento_adeudo")
@NamedQueries({
    @NamedQuery(name = "MovimientoAdeudo.findAll", query = "SELECT m FROM MovimientoAdeudo m"),
    @NamedQuery(name = "MovimientoAdeudo.findByMatricula", query = "SELECT m FROM MovimientoAdeudo m WHERE m.matricula = :matricula"),
    @NamedQuery(name = "MovimientoAdeudo.findByAdeudo", query = "SELECT m FROM MovimientoAdeudo m WHERE m.adeudo = :adeudo"),
    @NamedQuery(name = "MovimientoAdeudo.findByFechaAdeudo", query = "SELECT m FROM MovimientoAdeudo m WHERE m.fechaAdeudo = :fechaAdeudo")})
public class MovimientoAdeudo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "adeudo")
    private Double adeudo;
    @Column(name = "fecha_adeudo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdeudo;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Alumno alumno;

    public MovimientoAdeudo() {
    }

    public MovimientoAdeudo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getAdeudo() {
        return adeudo;
    }

    public void setAdeudo(Double adeudo) {
        this.adeudo = adeudo;
    }

    public Date getFechaAdeudo() {
        return fechaAdeudo;
    }

    public void setFechaAdeudo(Date fechaAdeudo) {
        this.fechaAdeudo = fechaAdeudo;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientoAdeudo)) {
            return false;
        }
        MovimientoAdeudo other = (MovimientoAdeudo) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.MovimientoAdeudo[ matricula=" + matricula + " ]";
    }
    
}
