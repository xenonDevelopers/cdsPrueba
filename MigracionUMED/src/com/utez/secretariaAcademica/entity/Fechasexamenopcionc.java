/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

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
@Table(name = "fechasexamenopcionc")
@NamedQueries({
    @NamedQuery(name = "Fechasexamenopcionc.findAll", query = "SELECT f FROM Fechasexamenopcionc f"),
    @NamedQuery(name = "Fechasexamenopcionc.findByIdfechasexamenopcionc", query = "SELECT f FROM Fechasexamenopcionc f WHERE f.idfechasexamenopcionc = :idfechasexamenopcionc"),
    @NamedQuery(name = "Fechasexamenopcionc.findByNumeroasignatura", query = "SELECT f FROM Fechasexamenopcionc f WHERE f.numeroasignatura = :numeroasignatura"),
    @NamedQuery(name = "Fechasexamenopcionc.findByFechaordinario", query = "SELECT f FROM Fechasexamenopcionc f WHERE f.fechaordinario = :fechaordinario"),
    @NamedQuery(name = "Fechasexamenopcionc.findByFechaextraordinario", query = "SELECT f FROM Fechasexamenopcionc f WHERE f.fechaextraordinario = :fechaextraordinario"),
    @NamedQuery(name = "Fechasexamenopcionc.findByFechatitulo", query = "SELECT f FROM Fechasexamenopcionc f WHERE f.fechatitulo = :fechatitulo")})
public class Fechasexamenopcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfechasexamenopcionc")
    private Integer idfechasexamenopcionc;
    @Column(name = "numeroasignatura")
    private Integer numeroasignatura;
    @Column(name = "fechaordinario")
    @Temporal(TemporalType.DATE)
    private Date fechaordinario;
    @Column(name = "fechaextraordinario")
    @Temporal(TemporalType.DATE)
    private Date fechaextraordinario;
    @Column(name = "fechatitulo")
    @Temporal(TemporalType.DATE)
    private Date fechatitulo;
    @JoinColumn(name = "idmesopcionc", referencedColumnName = "idmesopcionc")
    @ManyToOne
    private Mesopcionc idmesopcionc;

    public Fechasexamenopcionc() {
    }

    public Fechasexamenopcionc(Integer idfechasexamenopcionc) {
        this.idfechasexamenopcionc = idfechasexamenopcionc;
    }

    public Integer getIdfechasexamenopcionc() {
        return idfechasexamenopcionc;
    }

    public void setIdfechasexamenopcionc(Integer idfechasexamenopcionc) {
        this.idfechasexamenopcionc = idfechasexamenopcionc;
    }

    public Integer getNumeroasignatura() {
        return numeroasignatura;
    }

    public void setNumeroasignatura(Integer numeroasignatura) {
        this.numeroasignatura = numeroasignatura;
    }

    public Date getFechaordinario() {
        return fechaordinario;
    }

    public void setFechaordinario(Date fechaordinario) {
        this.fechaordinario = fechaordinario;
    }

    public Date getFechaextraordinario() {
        return fechaextraordinario;
    }

    public void setFechaextraordinario(Date fechaextraordinario) {
        this.fechaextraordinario = fechaextraordinario;
    }

    public Date getFechatitulo() {
        return fechatitulo;
    }

    public void setFechatitulo(Date fechatitulo) {
        this.fechatitulo = fechatitulo;
    }

    public Mesopcionc getIdmesopcionc() {
        return idmesopcionc;
    }

    public void setIdmesopcionc(Mesopcionc idmesopcionc) {
        this.idmesopcionc = idmesopcionc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfechasexamenopcionc != null ? idfechasexamenopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechasexamenopcionc)) {
            return false;
        }
        Fechasexamenopcionc other = (Fechasexamenopcionc) object;
        if ((this.idfechasexamenopcionc == null && other.idfechasexamenopcionc != null) || (this.idfechasexamenopcionc != null && !this.idfechasexamenopcionc.equals(other.idfechasexamenopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Fechasexamenopcionc[ idfechasexamenopcionc=" + idfechasexamenopcionc + " ]";
    }
    
}
