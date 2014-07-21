/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "calificacion_modulo")
@NamedQueries({
    @NamedQuery(name = "CalificacionModulo.findAll", query = "SELECT c FROM CalificacionModulo c"),
    @NamedQuery(name = "CalificacionModulo.findByIdCalificacionevento", query = "SELECT c FROM CalificacionModulo c WHERE c.idCalificacionevento = :idCalificacionevento"),
    @NamedQuery(name = "CalificacionModulo.findByCalificacion", query = "SELECT c FROM CalificacionModulo c WHERE c.calificacion = :calificacion")})
public class CalificacionModulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calificacionevento")
    private Long idCalificacionevento;
    @Column(name = "calificacion")
    private Integer calificacion;
    @JoinColumn(name = "curp", referencedColumnName = "curp")
    @ManyToOne
    private Persona curp;
    @JoinColumn(name = "id_ofertaevento", referencedColumnName = "id_ofertaevento")
    @ManyToOne
    private OfertaEvento idOfertaevento;
    @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")
    @ManyToOne
    private Modulo idModulo;

    public CalificacionModulo() {
    }

    public CalificacionModulo(Long idCalificacionevento) {
        this.idCalificacionevento = idCalificacionevento;
    }

    public Long getIdCalificacionevento() {
        return idCalificacionevento;
    }

    public void setIdCalificacionevento(Long idCalificacionevento) {
        this.idCalificacionevento = idCalificacionevento;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Persona getCurp() {
        return curp;
    }

    public void setCurp(Persona curp) {
        this.curp = curp;
    }

    public OfertaEvento getIdOfertaevento() {
        return idOfertaevento;
    }

    public void setIdOfertaevento(OfertaEvento idOfertaevento) {
        this.idOfertaevento = idOfertaevento;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacionevento != null ? idCalificacionevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionModulo)) {
            return false;
        }
        CalificacionModulo other = (CalificacionModulo) object;
        if ((this.idCalificacionevento == null && other.idCalificacionevento != null) || (this.idCalificacionevento != null && !this.idCalificacionevento.equals(other.idCalificacionevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CalificacionModulo[ idCalificacionevento=" + idCalificacionevento + " ]";
    }
    
}
