/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "fecha_examenopcionc")
@NamedQueries({
    @NamedQuery(name = "FechaExamenopcionc.findAll", query = "SELECT f FROM FechaExamenopcionc f"),
    @NamedQuery(name = "FechaExamenopcionc.findByIdAsignaturaopcionc", query = "SELECT f FROM FechaExamenopcionc f WHERE f.fechaExamenopcioncPK.idAsignaturaopcionc = :idAsignaturaopcionc"),
    @NamedQuery(name = "FechaExamenopcionc.findByIdTipoexamen", query = "SELECT f FROM FechaExamenopcionc f WHERE f.fechaExamenopcioncPK.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "FechaExamenopcionc.findByFecha", query = "SELECT f FROM FechaExamenopcionc f WHERE f.fecha = :fecha")})
public class FechaExamenopcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FechaExamenopcioncPK fechaExamenopcioncPK;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoExamen tipoExamen;
    @JoinColumn(name = "id_asignaturaopcionc", referencedColumnName = "id_asignaturaopcionc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AsignaturaOpcionc asignaturaOpcionc;

    public FechaExamenopcionc() {
    }

    public FechaExamenopcionc(FechaExamenopcioncPK fechaExamenopcioncPK) {
        this.fechaExamenopcioncPK = fechaExamenopcioncPK;
    }

    public FechaExamenopcionc(long idAsignaturaopcionc, int idTipoexamen) {
        this.fechaExamenopcioncPK = new FechaExamenopcioncPK(idAsignaturaopcionc, idTipoexamen);
    }

    public FechaExamenopcioncPK getFechaExamenopcioncPK() {
        return fechaExamenopcioncPK;
    }

    public void setFechaExamenopcioncPK(FechaExamenopcioncPK fechaExamenopcioncPK) {
        this.fechaExamenopcioncPK = fechaExamenopcioncPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoExamen getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(TipoExamen tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public AsignaturaOpcionc getAsignaturaOpcionc() {
        return asignaturaOpcionc;
    }

    public void setAsignaturaOpcionc(AsignaturaOpcionc asignaturaOpcionc) {
        this.asignaturaOpcionc = asignaturaOpcionc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaExamenopcioncPK != null ? fechaExamenopcioncPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaExamenopcionc)) {
            return false;
        }
        FechaExamenopcionc other = (FechaExamenopcionc) object;
        if ((this.fechaExamenopcioncPK == null && other.fechaExamenopcioncPK != null) || (this.fechaExamenopcioncPK != null && !this.fechaExamenopcioncPK.equals(other.fechaExamenopcioncPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.FechaExamenopcionc[ fechaExamenopcioncPK=" + fechaExamenopcioncPK + " ]";
    }
    
}
