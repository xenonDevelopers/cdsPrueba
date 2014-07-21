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
@Table(name = "historicogrupo")
@NamedQueries({
    @NamedQuery(name = "Historicogrupo.findAll", query = "SELECT h FROM Historicogrupo h"),
    @NamedQuery(name = "Historicogrupo.findByIdhistoricogrupo", query = "SELECT h FROM Historicogrupo h WHERE h.idhistoricogrupo = :idhistoricogrupo"),
    @NamedQuery(name = "Historicogrupo.findByFechaanterior", query = "SELECT h FROM Historicogrupo h WHERE h.fechaanterior = :fechaanterior"),
    @NamedQuery(name = "Historicogrupo.findByFechaactual", query = "SELECT h FROM Historicogrupo h WHERE h.fechaactual = :fechaactual"),
    @NamedQuery(name = "Historicogrupo.findByTipoexamen", query = "SELECT h FROM Historicogrupo h WHERE h.tipoexamen = :tipoexamen"),
    @NamedQuery(name = "Historicogrupo.findByMotivo", query = "SELECT h FROM Historicogrupo h WHERE h.motivo = :motivo"),
    @NamedQuery(name = "Historicogrupo.findByHora", query = "SELECT h FROM Historicogrupo h WHERE h.hora = :hora")})
public class Historicogrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistoricogrupo")
    private Integer idhistoricogrupo;
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
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "idfechaexamen", referencedColumnName = "idfechaexamen")
    @ManyToOne
    private Fechasexamen idfechaexamen;

    public Historicogrupo() {
    }

    public Historicogrupo(Integer idhistoricogrupo) {
        this.idhistoricogrupo = idhistoricogrupo;
    }

    public Integer getIdhistoricogrupo() {
        return idhistoricogrupo;
    }

    public void setIdhistoricogrupo(Integer idhistoricogrupo) {
        this.idhistoricogrupo = idhistoricogrupo;
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Fechasexamen getIdfechaexamen() {
        return idfechaexamen;
    }

    public void setIdfechaexamen(Fechasexamen idfechaexamen) {
        this.idfechaexamen = idfechaexamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistoricogrupo != null ? idhistoricogrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicogrupo)) {
            return false;
        }
        Historicogrupo other = (Historicogrupo) object;
        if ((this.idhistoricogrupo == null && other.idhistoricogrupo != null) || (this.idhistoricogrupo != null && !this.idhistoricogrupo.equals(other.idhistoricogrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Historicogrupo[ idhistoricogrupo=" + idhistoricogrupo + " ]";
    }
    
}
