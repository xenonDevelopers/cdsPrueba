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
import javax.persistence.Id;
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
@Table(name = "cicloescolar")
@NamedQueries({
    @NamedQuery(name = "Cicloescolar.findAll", query = "SELECT c FROM Cicloescolar c"),
    @NamedQuery(name = "Cicloescolar.findByCiclo", query = "SELECT c FROM Cicloescolar c WHERE c.ciclo = :ciclo"),
    @NamedQuery(name = "Cicloescolar.findByFechainicio", query = "SELECT c FROM Cicloescolar c WHERE c.fechainicio = :fechainicio"),
    @NamedQuery(name = "Cicloescolar.findByFechafin", query = "SELECT c FROM Cicloescolar c WHERE c.fechafin = :fechafin")})
public class Cicloescolar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ciclo")
    private String ciclo;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;

    public Cicloescolar() {
    }

    public Cicloescolar(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciclo != null ? ciclo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cicloescolar)) {
            return false;
        }
        Cicloescolar other = (Cicloescolar) object;
        if ((this.ciclo == null && other.ciclo != null) || (this.ciclo != null && !this.ciclo.equals(other.ciclo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Cicloescolar[ ciclo=" + ciclo + " ]";
    }
    
}
