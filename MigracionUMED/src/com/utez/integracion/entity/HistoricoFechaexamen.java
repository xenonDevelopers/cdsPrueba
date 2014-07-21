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
@Table(name = "historico_fechaexamen")
@NamedQueries({
    @NamedQuery(name = "HistoricoFechaexamen.findAll", query = "SELECT h FROM HistoricoFechaexamen h"),
    @NamedQuery(name = "HistoricoFechaexamen.findByIdHistoricoFechaexamen", query = "SELECT h FROM HistoricoFechaexamen h WHERE h.idHistoricoFechaexamen = :idHistoricoFechaexamen"),
    @NamedQuery(name = "HistoricoFechaexamen.findByFechaExamen", query = "SELECT h FROM HistoricoFechaexamen h WHERE h.fechaExamen = :fechaExamen"),
    @NamedQuery(name = "HistoricoFechaexamen.findByFechaModificacion", query = "SELECT h FROM HistoricoFechaexamen h WHERE h.fechaModificacion = :fechaModificacion")})
public class HistoricoFechaexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico_fechaexamen")
    private Long idHistoricoFechaexamen;
    @Column(name = "fecha_examen")
    @Temporal(TemporalType.DATE)
    private Date fechaExamen;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "id_fechaexamen", referencedColumnName = "id_fechaexamen")
    @ManyToOne
    private FechaExamen idFechaexamen;

    public HistoricoFechaexamen() {
    }

    public HistoricoFechaexamen(Long idHistoricoFechaexamen) {
        this.idHistoricoFechaexamen = idHistoricoFechaexamen;
    }

    public Long getIdHistoricoFechaexamen() {
        return idHistoricoFechaexamen;
    }

    public void setIdHistoricoFechaexamen(Long idHistoricoFechaexamen) {
        this.idHistoricoFechaexamen = idHistoricoFechaexamen;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public FechaExamen getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(FechaExamen idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoFechaexamen != null ? idHistoricoFechaexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoFechaexamen)) {
            return false;
        }
        HistoricoFechaexamen other = (HistoricoFechaexamen) object;
        if ((this.idHistoricoFechaexamen == null && other.idHistoricoFechaexamen != null) || (this.idHistoricoFechaexamen != null && !this.idHistoricoFechaexamen.equals(other.idHistoricoFechaexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.HistoricoFechaexamen[ idHistoricoFechaexamen=" + idHistoricoFechaexamen + " ]";
    }
    
}
