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
@Table(name = "historicoopcionc")
@NamedQueries({
    @NamedQuery(name = "Historicoopcionc.findAll", query = "SELECT h FROM Historicoopcionc h"),
    @NamedQuery(name = "Historicoopcionc.findByIdhistoricoopcionc", query = "SELECT h FROM Historicoopcionc h WHERE h.idhistoricoopcionc = :idhistoricoopcionc"),
    @NamedQuery(name = "Historicoopcionc.findByFechaanterior", query = "SELECT h FROM Historicoopcionc h WHERE h.fechaanterior = :fechaanterior"),
    @NamedQuery(name = "Historicoopcionc.findByFechaactual", query = "SELECT h FROM Historicoopcionc h WHERE h.fechaactual = :fechaactual"),
    @NamedQuery(name = "Historicoopcionc.findByTipoexamen", query = "SELECT h FROM Historicoopcionc h WHERE h.tipoexamen = :tipoexamen"),
    @NamedQuery(name = "Historicoopcionc.findByMotivo", query = "SELECT h FROM Historicoopcionc h WHERE h.motivo = :motivo")})
public class Historicoopcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistoricoopcionc")
    private Integer idhistoricoopcionc;
    @Column(name = "fechaanterior")
    @Temporal(TemporalType.DATE)
    private Date fechaanterior;
    @Column(name = "fechaactual")
    @Temporal(TemporalType.DATE)
    private Date fechaactual;
    @Column(name = "tipoexamen")
    private String tipoexamen;
    @Column(name = "motivo")
    private String motivo;
    @JoinColumn(name = "idprogramacionopcionc", referencedColumnName = "idprogramacionopcionc")
    @ManyToOne
    private Programacionopcionc idprogramacionopcionc;

    public Historicoopcionc() {
    }

    public Historicoopcionc(Integer idhistoricoopcionc) {
        this.idhistoricoopcionc = idhistoricoopcionc;
    }

    public Integer getIdhistoricoopcionc() {
        return idhistoricoopcionc;
    }

    public void setIdhistoricoopcionc(Integer idhistoricoopcionc) {
        this.idhistoricoopcionc = idhistoricoopcionc;
    }

    public Date getFechaanterior() {
        return fechaanterior;
    }

    public void setFechaanterior(Date fechaanterior) {
        this.fechaanterior = fechaanterior;
    }

    public Date getFechaactual() {
        return fechaactual;
    }

    public void setFechaactual(Date fechaactual) {
        this.fechaactual = fechaactual;
    }

    public String getTipoexamen() {
        return tipoexamen;
    }

    public void setTipoexamen(String tipoexamen) {
        this.tipoexamen = tipoexamen;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Programacionopcionc getIdprogramacionopcionc() {
        return idprogramacionopcionc;
    }

    public void setIdprogramacionopcionc(Programacionopcionc idprogramacionopcionc) {
        this.idprogramacionopcionc = idprogramacionopcionc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistoricoopcionc != null ? idhistoricoopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicoopcionc)) {
            return false;
        }
        Historicoopcionc other = (Historicoopcionc) object;
        if ((this.idhistoricoopcionc == null && other.idhistoricoopcionc != null) || (this.idhistoricoopcionc != null && !this.idhistoricoopcionc.equals(other.idhistoricoopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Historicoopcionc[ idhistoricoopcionc=" + idhistoricoopcionc + " ]";
    }
    
}
