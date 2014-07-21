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
@Table(name = "fecha_induccion")
@NamedQueries({
    @NamedQuery(name = "FechaInduccion.findAll", query = "SELECT f FROM FechaInduccion f"),
    @NamedQuery(name = "FechaInduccion.findByIdFechainduccion", query = "SELECT f FROM FechaInduccion f WHERE f.idFechainduccion = :idFechainduccion"),
    @NamedQuery(name = "FechaInduccion.findByFecha", query = "SELECT f FROM FechaInduccion f WHERE f.fecha = :fecha")})
public class FechaInduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fechainduccion")
    private Long idFechainduccion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private Horario idHorario;
    @JoinColumn(name = "id_grupoinduccion", referencedColumnName = "id_grupoinduccion")
    @ManyToOne
    private GrupoInduccion idGrupoinduccion;

    public FechaInduccion() {
    }

    public FechaInduccion(Long idFechainduccion) {
        this.idFechainduccion = idFechainduccion;
    }

    public Long getIdFechainduccion() {
        return idFechainduccion;
    }

    public void setIdFechainduccion(Long idFechainduccion) {
        this.idFechainduccion = idFechainduccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    public GrupoInduccion getIdGrupoinduccion() {
        return idGrupoinduccion;
    }

    public void setIdGrupoinduccion(GrupoInduccion idGrupoinduccion) {
        this.idGrupoinduccion = idGrupoinduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFechainduccion != null ? idFechainduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaInduccion)) {
            return false;
        }
        FechaInduccion other = (FechaInduccion) object;
        if ((this.idFechainduccion == null && other.idFechainduccion != null) || (this.idFechainduccion != null && !this.idFechainduccion.equals(other.idFechainduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FechaInduccion[ idFechainduccion=" + idFechainduccion + " ]";
    }
    
}
