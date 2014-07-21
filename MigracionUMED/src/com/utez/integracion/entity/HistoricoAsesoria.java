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
@Table(name = "historico_asesoria")
@NamedQueries({
    @NamedQuery(name = "HistoricoAsesoria.findAll", query = "SELECT h FROM HistoricoAsesoria h"),
    @NamedQuery(name = "HistoricoAsesoria.findByIdHistoricoasesoria", query = "SELECT h FROM HistoricoAsesoria h WHERE h.idHistoricoasesoria = :idHistoricoasesoria"),
    @NamedQuery(name = "HistoricoAsesoria.findByFechaAsesoria", query = "SELECT h FROM HistoricoAsesoria h WHERE h.fechaAsesoria = :fechaAsesoria"),
    @NamedQuery(name = "HistoricoAsesoria.findByFechaModificacion", query = "SELECT h FROM HistoricoAsesoria h WHERE h.fechaModificacion = :fechaModificacion")})
public class HistoricoAsesoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historicoasesoria")
    private Long idHistoricoasesoria;
    @Column(name = "fecha_asesoria")
    @Temporal(TemporalType.DATE)
    private Date fechaAsesoria;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "id_asesoria", referencedColumnName = "id_asesoria")
    @ManyToOne
    private Asesoria idAsesoria;

    public HistoricoAsesoria() {
    }

    public HistoricoAsesoria(Long idHistoricoasesoria) {
        this.idHistoricoasesoria = idHistoricoasesoria;
    }

    public Long getIdHistoricoasesoria() {
        return idHistoricoasesoria;
    }

    public void setIdHistoricoasesoria(Long idHistoricoasesoria) {
        this.idHistoricoasesoria = idHistoricoasesoria;
    }

    public Date getFechaAsesoria() {
        return fechaAsesoria;
    }

    public void setFechaAsesoria(Date fechaAsesoria) {
        this.fechaAsesoria = fechaAsesoria;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Asesoria getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(Asesoria idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoasesoria != null ? idHistoricoasesoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoAsesoria)) {
            return false;
        }
        HistoricoAsesoria other = (HistoricoAsesoria) object;
        if ((this.idHistoricoasesoria == null && other.idHistoricoasesoria != null) || (this.idHistoricoasesoria != null && !this.idHistoricoasesoria.equals(other.idHistoricoasesoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.HistoricoAsesoria[ idHistoricoasesoria=" + idHistoricoasesoria + " ]";
    }
    
}
