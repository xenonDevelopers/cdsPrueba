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
@Table(name = "solicitud_baja")
@NamedQueries({
    @NamedQuery(name = "SolicitudBaja.findAll", query = "SELECT s FROM SolicitudBaja s"),
    @NamedQuery(name = "SolicitudBaja.findByIdSolicitudbaja", query = "SELECT s FROM SolicitudBaja s WHERE s.idSolicitudbaja = :idSolicitudbaja"),
    @NamedQuery(name = "SolicitudBaja.findByCasoAlumno", query = "SELECT s FROM SolicitudBaja s WHERE s.casoAlumno = :casoAlumno"),
    @NamedQuery(name = "SolicitudBaja.findBySolucion", query = "SELECT s FROM SolicitudBaja s WHERE s.solucion = :solucion"),
    @NamedQuery(name = "SolicitudBaja.findByFechaSolicitudbaja", query = "SELECT s FROM SolicitudBaja s WHERE s.fechaSolicitudbaja = :fechaSolicitudbaja"),
    @NamedQuery(name = "SolicitudBaja.findByFechaAutorizacionbaja", query = "SELECT s FROM SolicitudBaja s WHERE s.fechaAutorizacionbaja = :fechaAutorizacionbaja"),
    @NamedQuery(name = "SolicitudBaja.findByEstadoSolicitudbaja", query = "SELECT s FROM SolicitudBaja s WHERE s.estadoSolicitudbaja = :estadoSolicitudbaja")})
public class SolicitudBaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitudbaja")
    private Integer idSolicitudbaja;
    @Column(name = "caso_alumno")
    private String casoAlumno;
    @Column(name = "solucion")
    private String solucion;
    @Column(name = "fecha_solicitudbaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitudbaja;
    @Column(name = "fecha_autorizacionbaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacionbaja;
    @Column(name = "estado_solicitudbaja")
    private String estadoSolicitudbaja;
    @JoinColumn(name = "id_tipobaja", referencedColumnName = "id_tipobaja")
    @ManyToOne
    private TipoBaja idTipobaja;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public SolicitudBaja() {
    }

    public SolicitudBaja(Integer idSolicitudbaja) {
        this.idSolicitudbaja = idSolicitudbaja;
    }

    public Integer getIdSolicitudbaja() {
        return idSolicitudbaja;
    }

    public void setIdSolicitudbaja(Integer idSolicitudbaja) {
        this.idSolicitudbaja = idSolicitudbaja;
    }

    public String getCasoAlumno() {
        return casoAlumno;
    }

    public void setCasoAlumno(String casoAlumno) {
        this.casoAlumno = casoAlumno;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Date getFechaSolicitudbaja() {
        return fechaSolicitudbaja;
    }

    public void setFechaSolicitudbaja(Date fechaSolicitudbaja) {
        this.fechaSolicitudbaja = fechaSolicitudbaja;
    }

    public Date getFechaAutorizacionbaja() {
        return fechaAutorizacionbaja;
    }

    public void setFechaAutorizacionbaja(Date fechaAutorizacionbaja) {
        this.fechaAutorizacionbaja = fechaAutorizacionbaja;
    }

    public String getEstadoSolicitudbaja() {
        return estadoSolicitudbaja;
    }

    public void setEstadoSolicitudbaja(String estadoSolicitudbaja) {
        this.estadoSolicitudbaja = estadoSolicitudbaja;
    }

    public TipoBaja getIdTipobaja() {
        return idTipobaja;
    }

    public void setIdTipobaja(TipoBaja idTipobaja) {
        this.idTipobaja = idTipobaja;
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
        hash += (idSolicitudbaja != null ? idSolicitudbaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudBaja)) {
            return false;
        }
        SolicitudBaja other = (SolicitudBaja) object;
        if ((this.idSolicitudbaja == null && other.idSolicitudbaja != null) || (this.idSolicitudbaja != null && !this.idSolicitudbaja.equals(other.idSolicitudbaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SolicitudBaja[ idSolicitudbaja=" + idSolicitudbaja + " ]";
    }
    
}
