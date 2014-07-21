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
@Table(name = "fechaasesoria")
@NamedQueries({
    @NamedQuery(name = "Fechaasesoria.findAll", query = "SELECT f FROM Fechaasesoria f"),
    @NamedQuery(name = "Fechaasesoria.findByIdfechaasesoria", query = "SELECT f FROM Fechaasesoria f WHERE f.idfechaasesoria = :idfechaasesoria"),
    @NamedQuery(name = "Fechaasesoria.findByFecha", query = "SELECT f FROM Fechaasesoria f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Fechaasesoria.findByHorainicio", query = "SELECT f FROM Fechaasesoria f WHERE f.horainicio = :horainicio"),
    @NamedQuery(name = "Fechaasesoria.findByHorafin", query = "SELECT f FROM Fechaasesoria f WHERE f.horafin = :horafin"),
    @NamedQuery(name = "Fechaasesoria.findByNumeroasesoria", query = "SELECT f FROM Fechaasesoria f WHERE f.numeroasesoria = :numeroasesoria")})
public class Fechaasesoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfechaasesoria")
    private Integer idfechaasesoria;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "horainicio")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "horafin")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    @Column(name = "numeroasesoria")
    private Integer numeroasesoria;
    @JoinColumn(name = "idasesoriaasignatura", referencedColumnName = "idasesoriaasignatura")
    @ManyToOne
    private Asesoriaasignatura idasesoriaasignatura;

    public Fechaasesoria() {
    }

    public Fechaasesoria(Integer idfechaasesoria) {
        this.idfechaasesoria = idfechaasesoria;
    }

    public Integer getIdfechaasesoria() {
        return idfechaasesoria;
    }

    public void setIdfechaasesoria(Integer idfechaasesoria) {
        this.idfechaasesoria = idfechaasesoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public Integer getNumeroasesoria() {
        return numeroasesoria;
    }

    public void setNumeroasesoria(Integer numeroasesoria) {
        this.numeroasesoria = numeroasesoria;
    }

    public Asesoriaasignatura getIdasesoriaasignatura() {
        return idasesoriaasignatura;
    }

    public void setIdasesoriaasignatura(Asesoriaasignatura idasesoriaasignatura) {
        this.idasesoriaasignatura = idasesoriaasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfechaasesoria != null ? idfechaasesoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechaasesoria)) {
            return false;
        }
        Fechaasesoria other = (Fechaasesoria) object;
        if ((this.idfechaasesoria == null && other.idfechaasesoria != null) || (this.idfechaasesoria != null && !this.idfechaasesoria.equals(other.idfechaasesoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Fechaasesoria[ idfechaasesoria=" + idfechaasesoria + " ]";
    }
    
}
