/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "mesopcionc")
@NamedQueries({
    @NamedQuery(name = "Mesopcionc.findAll", query = "SELECT m FROM Mesopcionc m"),
    @NamedQuery(name = "Mesopcionc.findByIdmesopcionc", query = "SELECT m FROM Mesopcionc m WHERE m.idmesopcionc = :idmesopcionc"),
    @NamedQuery(name = "Mesopcionc.findByFechainicio", query = "SELECT m FROM Mesopcionc m WHERE m.fechainicio = :fechainicio"),
    @NamedQuery(name = "Mesopcionc.findByFechafin", query = "SELECT m FROM Mesopcionc m WHERE m.fechafin = :fechafin")})
public class Mesopcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmesopcionc")
    private Integer idmesopcionc;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @JoinColumn(name = "idplantel", referencedColumnName = "idplantel")
    @ManyToOne
    private Plantel idplantel;
    @OneToMany(mappedBy = "idmesopcionc")
    private List<Fechasexamenopcionc> fechasexamenopcioncList;

    public Mesopcionc() {
    }

    public Mesopcionc(Integer idmesopcionc) {
        this.idmesopcionc = idmesopcionc;
    }

    public Integer getIdmesopcionc() {
        return idmesopcionc;
    }

    public void setIdmesopcionc(Integer idmesopcionc) {
        this.idmesopcionc = idmesopcionc;
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

    public Plantel getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Plantel idplantel) {
        this.idplantel = idplantel;
    }

    public List<Fechasexamenopcionc> getFechasexamenopcioncList() {
        return fechasexamenopcioncList;
    }

    public void setFechasexamenopcioncList(List<Fechasexamenopcionc> fechasexamenopcioncList) {
        this.fechasexamenopcioncList = fechasexamenopcioncList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmesopcionc != null ? idmesopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesopcionc)) {
            return false;
        }
        Mesopcionc other = (Mesopcionc) object;
        if ((this.idmesopcionc == null && other.idmesopcionc != null) || (this.idmesopcionc != null && !this.idmesopcionc.equals(other.idmesopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Mesopcionc[ idmesopcionc=" + idmesopcionc + " ]";
    }
    
}
