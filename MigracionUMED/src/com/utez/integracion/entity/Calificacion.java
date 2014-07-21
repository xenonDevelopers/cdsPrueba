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
@Table(name = "calificacion")
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll", query = "SELECT c FROM Calificacion c"),
    @NamedQuery(name = "Calificacion.findByIdTipoexamen", query = "SELECT c FROM Calificacion c WHERE c.calificacionPK.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "Calificacion.findByIdEsquemaalumnoasignatura", query = "SELECT c FROM Calificacion c WHERE c.calificacionPK.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "Calificacion.findByTrabajo", query = "SELECT c FROM Calificacion c WHERE c.trabajo = :trabajo"),
    @NamedQuery(name = "Calificacion.findByExamen", query = "SELECT c FROM Calificacion c WHERE c.examen = :examen"),
    @NamedQuery(name = "Calificacion.findByFechaRegistro", query = "SELECT c FROM Calificacion c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Calificacion.findByObservacion", query = "SELECT c FROM Calificacion c WHERE c.observacion = :observacion")})
public class Calificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CalificacionPK calificacionPK;
    @Column(name = "trabajo")
    private String trabajo;
    @Column(name = "examen")
    private String examen;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoExamen tipoExamen;
    @JoinColumn(name = "id_esquemaalumnoasignatura", referencedColumnName = "id_esquemaalumnoasignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EsquemaAlumnoasignatura esquemaAlumnoasignatura;

    public Calificacion() {
    }

    public Calificacion(CalificacionPK calificacionPK) {
        this.calificacionPK = calificacionPK;
    }

    public Calificacion(long idTipoexamen, long idEsquemaalumnoasignatura) {
        this.calificacionPK = new CalificacionPK(idTipoexamen, idEsquemaalumnoasignatura);
    }

    public CalificacionPK getCalificacionPK() {
        return calificacionPK;
    }

    public void setCalificacionPK(CalificacionPK calificacionPK) {
        this.calificacionPK = calificacionPK;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public TipoExamen getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(TipoExamen tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public EsquemaAlumnoasignatura getEsquemaAlumnoasignatura() {
        return esquemaAlumnoasignatura;
    }

    public void setEsquemaAlumnoasignatura(EsquemaAlumnoasignatura esquemaAlumnoasignatura) {
        this.esquemaAlumnoasignatura = esquemaAlumnoasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calificacionPK != null ? calificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificacion)) {
            return false;
        }
        Calificacion other = (Calificacion) object;
        if ((this.calificacionPK == null && other.calificacionPK != null) || (this.calificacionPK != null && !this.calificacionPK.equals(other.calificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Calificacion[ calificacionPK=" + calificacionPK + " ]";
    }
    
}
