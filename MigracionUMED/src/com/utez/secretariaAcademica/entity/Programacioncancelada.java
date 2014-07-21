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
@Table(name = "programacioncancelada")
@NamedQueries({
    @NamedQuery(name = "Programacioncancelada.findAll", query = "SELECT p FROM Programacioncancelada p"),
    @NamedQuery(name = "Programacioncancelada.findByIdprogramacioncancelada", query = "SELECT p FROM Programacioncancelada p WHERE p.idprogramacioncancelada = :idprogramacioncancelada"),
    @NamedQuery(name = "Programacioncancelada.findByFecha", query = "SELECT p FROM Programacioncancelada p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Programacioncancelada.findByMotivo", query = "SELECT p FROM Programacioncancelada p WHERE p.motivo = :motivo")})
public class Programacioncancelada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprogramacioncancelada")
    private Integer idprogramacioncancelada;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "motivo")
    private String motivo;
    @JoinColumn(name = "idcuadernoprogramacion", referencedColumnName = "idcuadernoprogramacion")
    @ManyToOne
    private Cuadernoprogramacion idcuadernoprogramacion;

    public Programacioncancelada() {
    }

    public Programacioncancelada(Integer idprogramacioncancelada) {
        this.idprogramacioncancelada = idprogramacioncancelada;
    }

    public Integer getIdprogramacioncancelada() {
        return idprogramacioncancelada;
    }

    public void setIdprogramacioncancelada(Integer idprogramacioncancelada) {
        this.idprogramacioncancelada = idprogramacioncancelada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Cuadernoprogramacion getIdcuadernoprogramacion() {
        return idcuadernoprogramacion;
    }

    public void setIdcuadernoprogramacion(Cuadernoprogramacion idcuadernoprogramacion) {
        this.idcuadernoprogramacion = idcuadernoprogramacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprogramacioncancelada != null ? idprogramacioncancelada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacioncancelada)) {
            return false;
        }
        Programacioncancelada other = (Programacioncancelada) object;
        if ((this.idprogramacioncancelada == null && other.idprogramacioncancelada != null) || (this.idprogramacioncancelada != null && !this.idprogramacioncancelada.equals(other.idprogramacioncancelada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Programacioncancelada[ idprogramacioncancelada=" + idprogramacioncancelada + " ]";
    }
    
}
