/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "esquema_alumnoasignatura")
@NamedQueries({
    @NamedQuery(name = "EsquemaAlumnoasignatura.findAll", query = "SELECT e FROM EsquemaAlumnoasignatura e"),
    @NamedQuery(name = "EsquemaAlumnoasignatura.findByIdEsquemaalumnoasignatura", query = "SELECT e FROM EsquemaAlumnoasignatura e WHERE e.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "EsquemaAlumnoasignatura.findByFechaRegistro", query = "SELECT e FROM EsquemaAlumnoasignatura e WHERE e.fechaRegistro = :fechaRegistro")})
public class EsquemaAlumnoasignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_esquemaalumnoasignatura")
    private Long idEsquemaalumnoasignatura;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @ManyToMany(mappedBy = "esquemaAlumnoasignaturaList")
    private List<Asesor> asesorList;
    @JoinColumn(name = "esquema", referencedColumnName = "id_tipoprogramacion")
    @ManyToOne
    private TipoProgramacion esquema;
    @JoinColumn(name = "id_alumnoasignatura", referencedColumnName = "id_alumnoasignatura")
    @ManyToOne
    private AlumnoAsignatura idAlumnoasignatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esquemaAlumnoasignatura")
    private List<Calificacion> calificacionList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "esquemaAlumnoasignatura")
    private GrupoAlumnoesquema grupoAlumnoesquema;
    @OneToMany(mappedBy = "idEsquemaalumnoasignatura")
    private List<HistoricoCalificacion> historicoCalificacionList;

    public EsquemaAlumnoasignatura() {
    }

    public EsquemaAlumnoasignatura(Long idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public Long getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(Long idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    public TipoProgramacion getEsquema() {
        return esquema;
    }

    public void setEsquema(TipoProgramacion esquema) {
        this.esquema = esquema;
    }

    public AlumnoAsignatura getIdAlumnoasignatura() {
        return idAlumnoasignatura;
    }

    public void setIdAlumnoasignatura(AlumnoAsignatura idAlumnoasignatura) {
        this.idAlumnoasignatura = idAlumnoasignatura;
    }

    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    public GrupoAlumnoesquema getGrupoAlumnoesquema() {
        return grupoAlumnoesquema;
    }

    public void setGrupoAlumnoesquema(GrupoAlumnoesquema grupoAlumnoesquema) {
        this.grupoAlumnoesquema = grupoAlumnoesquema;
    }

    public List<HistoricoCalificacion> getHistoricoCalificacionList() {
        return historicoCalificacionList;
    }

    public void setHistoricoCalificacionList(List<HistoricoCalificacion> historicoCalificacionList) {
        this.historicoCalificacionList = historicoCalificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEsquemaalumnoasignatura != null ? idEsquemaalumnoasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsquemaAlumnoasignatura)) {
            return false;
        }
        EsquemaAlumnoasignatura other = (EsquemaAlumnoasignatura) object;
        if ((this.idEsquemaalumnoasignatura == null && other.idEsquemaalumnoasignatura != null) || (this.idEsquemaalumnoasignatura != null && !this.idEsquemaalumnoasignatura.equals(other.idEsquemaalumnoasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.EsquemaAlumnoasignatura[ idEsquemaalumnoasignatura=" + idEsquemaalumnoasignatura + " ]";
    }
    
}
