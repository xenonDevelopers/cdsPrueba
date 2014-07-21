/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

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
@Table(name = "entrega_examen")
@NamedQueries({
    @NamedQuery(name = "EntregaExamen.findAll", query = "SELECT e FROM EntregaExamen e"),
    @NamedQuery(name = "EntregaExamen.findByIdEntregaexamen", query = "SELECT e FROM EntregaExamen e WHERE e.idEntregaexamen = :idEntregaexamen"),
    @NamedQuery(name = "EntregaExamen.findByFechaRecepcion", query = "SELECT e FROM EntregaExamen e WHERE e.fechaRecepcion = :fechaRecepcion"),
    @NamedQuery(name = "EntregaExamen.findByFechaEntrega", query = "SELECT e FROM EntregaExamen e WHERE e.fechaEntrega = :fechaEntrega")})
public class EntregaExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entregaexamen")
    private Long idEntregaexamen;
    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @JoinColumn(name = "id_fechaexamenprogramado", referencedColumnName = "id_fecha_examen")
    @ManyToOne
    private FechaExamenprogramado idFechaexamenprogramado;

    public EntregaExamen() {
    }

    public EntregaExamen(Long idEntregaexamen) {
        this.idEntregaexamen = idEntregaexamen;
    }

    public Long getIdEntregaexamen() {
        return idEntregaexamen;
    }

    public void setIdEntregaexamen(Long idEntregaexamen) {
        this.idEntregaexamen = idEntregaexamen;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public FechaExamenprogramado getIdFechaexamenprogramado() {
        return idFechaexamenprogramado;
    }

    public void setIdFechaexamenprogramado(FechaExamenprogramado idFechaexamenprogramado) {
        this.idFechaexamenprogramado = idFechaexamenprogramado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntregaexamen != null ? idEntregaexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntregaExamen)) {
            return false;
        }
        EntregaExamen other = (EntregaExamen) object;
        if ((this.idEntregaexamen == null && other.idEntregaexamen != null) || (this.idEntregaexamen != null && !this.idEntregaexamen.equals(other.idEntregaexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.EntregaExamen[ idEntregaexamen=" + idEntregaexamen + " ]";
    }
    
}
