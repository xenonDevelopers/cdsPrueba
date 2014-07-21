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
@Table(name = "fechasexamenbloque")
@NamedQueries({
    @NamedQuery(name = "Fechasexamenbloque.findAll", query = "SELECT f FROM Fechasexamenbloque f"),
    @NamedQuery(name = "Fechasexamenbloque.findByIdfechasexamenbloque", query = "SELECT f FROM Fechasexamenbloque f WHERE f.idfechasexamenbloque = :idfechasexamenbloque"),
    @NamedQuery(name = "Fechasexamenbloque.findByTipoevaluacion", query = "SELECT f FROM Fechasexamenbloque f WHERE f.tipoevaluacion = :tipoevaluacion"),
    @NamedQuery(name = "Fechasexamenbloque.findByFecha", query = "SELECT f FROM Fechasexamenbloque f WHERE f.fecha = :fecha")})
public class Fechasexamenbloque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfechasexamenbloque")
    private Integer idfechasexamenbloque;
    @Basic(optional = false)
    @Column(name = "tipoevaluacion")
    private String tipoevaluacion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idfechaaseseoriabloque", referencedColumnName = "idfechaaseseoriabloque")
    @ManyToOne(optional = false)
    private Fechaasesoriabloquelinea idfechaaseseoriabloque;

    public Fechasexamenbloque() {
    }

    public Fechasexamenbloque(Integer idfechasexamenbloque) {
        this.idfechasexamenbloque = idfechasexamenbloque;
    }

    public Fechasexamenbloque(Integer idfechasexamenbloque, String tipoevaluacion) {
        this.idfechasexamenbloque = idfechasexamenbloque;
        this.tipoevaluacion = tipoevaluacion;
    }

    public Integer getIdfechasexamenbloque() {
        return idfechasexamenbloque;
    }

    public void setIdfechasexamenbloque(Integer idfechasexamenbloque) {
        this.idfechasexamenbloque = idfechasexamenbloque;
    }

    public String getTipoevaluacion() {
        return tipoevaluacion;
    }

    public void setTipoevaluacion(String tipoevaluacion) {
        this.tipoevaluacion = tipoevaluacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Fechaasesoriabloquelinea getIdfechaaseseoriabloque() {
        return idfechaaseseoriabloque;
    }

    public void setIdfechaaseseoriabloque(Fechaasesoriabloquelinea idfechaaseseoriabloque) {
        this.idfechaaseseoriabloque = idfechaaseseoriabloque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfechasexamenbloque != null ? idfechasexamenbloque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechasexamenbloque)) {
            return false;
        }
        Fechasexamenbloque other = (Fechasexamenbloque) object;
        if ((this.idfechasexamenbloque == null && other.idfechasexamenbloque != null) || (this.idfechasexamenbloque != null && !this.idfechasexamenbloque.equals(other.idfechasexamenbloque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Fechasexamenbloque[ idfechasexamenbloque=" + idfechasexamenbloque + " ]";
    }
    
}
