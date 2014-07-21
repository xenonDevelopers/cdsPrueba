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
@Table(name = "seguimiento_alumno")
@NamedQueries({
    @NamedQuery(name = "SeguimientoAlumno.findAll", query = "SELECT s FROM SeguimientoAlumno s"),
    @NamedQuery(name = "SeguimientoAlumno.findByIdSeguimiento", query = "SELECT s FROM SeguimientoAlumno s WHERE s.idSeguimiento = :idSeguimiento"),
    @NamedQuery(name = "SeguimientoAlumno.findByFechaSeguimiento", query = "SELECT s FROM SeguimientoAlumno s WHERE s.fechaSeguimiento = :fechaSeguimiento"),
    @NamedQuery(name = "SeguimientoAlumno.findByObservaciones", query = "SELECT s FROM SeguimientoAlumno s WHERE s.observaciones = :observaciones")})
public class SeguimientoAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_seguimiento")
    private Long idSeguimiento;
    @Column(name = "fecha_seguimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguimiento;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_responsable", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idResponsable;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public SeguimientoAlumno() {
    }

    public SeguimientoAlumno(Long idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Long getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Long idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public RecursoHumano getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(RecursoHumano idResponsable) {
        this.idResponsable = idResponsable;
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
        hash += (idSeguimiento != null ? idSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoAlumno)) {
            return false;
        }
        SeguimientoAlumno other = (SeguimientoAlumno) object;
        if ((this.idSeguimiento == null && other.idSeguimiento != null) || (this.idSeguimiento != null && !this.idSeguimiento.equals(other.idSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SeguimientoAlumno[ idSeguimiento=" + idSeguimiento + " ]";
    }
    
}
