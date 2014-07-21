/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "solicitud_programacion")
@NamedQueries({
    @NamedQuery(name = "SolicitudProgramacion.findAll", query = "SELECT s FROM SolicitudProgramacion s"),
    @NamedQuery(name = "SolicitudProgramacion.findByIdSolicitudprogramacion", query = "SELECT s FROM SolicitudProgramacion s WHERE s.idSolicitudprogramacion = :idSolicitudprogramacion"),
    @NamedQuery(name = "SolicitudProgramacion.findByCasoSecretaria", query = "SELECT s FROM SolicitudProgramacion s WHERE s.casoSecretaria = :casoSecretaria"),
    @NamedQuery(name = "SolicitudProgramacion.findByCasoAlumno", query = "SELECT s FROM SolicitudProgramacion s WHERE s.casoAlumno = :casoAlumno"),
    @NamedQuery(name = "SolicitudProgramacion.findBySolucion", query = "SELECT s FROM SolicitudProgramacion s WHERE s.solucion = :solucion"),
    @NamedQuery(name = "SolicitudProgramacion.findByFechaSolicitud", query = "SELECT s FROM SolicitudProgramacion s WHERE s.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "SolicitudProgramacion.findByEstadoSolicitud", query = "SELECT s FROM SolicitudProgramacion s WHERE s.estadoSolicitud = :estadoSolicitud")})
public class SolicitudProgramacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitudprogramacion")
    private Long idSolicitudprogramacion;
    @Column(name = "caso_secretaria")
    private String casoSecretaria;
    @Column(name = "caso_alumno")
    private String casoAlumno;
    @Column(name = "solucion")
    private String solucion;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Column(name = "estado_solicitud")
    private String estadoSolicitud;
    @OneToMany(mappedBy = "idSolicitudprogramacion")
    private List<ArchivoSolicitudprogramacion> archivoSolicitudprogramacionList;
    @OneToMany(mappedBy = "idSolicitudprogramacion")
    private List<Programacion> programacionList;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public SolicitudProgramacion() {
    }

    public SolicitudProgramacion(Long idSolicitudprogramacion) {
        this.idSolicitudprogramacion = idSolicitudprogramacion;
    }

    public Long getIdSolicitudprogramacion() {
        return idSolicitudprogramacion;
    }

    public void setIdSolicitudprogramacion(Long idSolicitudprogramacion) {
        this.idSolicitudprogramacion = idSolicitudprogramacion;
    }

    public String getCasoSecretaria() {
        return casoSecretaria;
    }

    public void setCasoSecretaria(String casoSecretaria) {
        this.casoSecretaria = casoSecretaria;
    }

    public String getCasoAlumno() {
        return casoAlumno;
    }

    public void setCasoAlumno(String casoAlumno) {
        this.casoAlumno = casoAlumno;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public List<ArchivoSolicitudprogramacion> getArchivoSolicitudprogramacionList() {
        return archivoSolicitudprogramacionList;
    }

    public void setArchivoSolicitudprogramacionList(List<ArchivoSolicitudprogramacion> archivoSolicitudprogramacionList) {
        this.archivoSolicitudprogramacionList = archivoSolicitudprogramacionList;
    }

    public List<Programacion> getProgramacionList() {
        return programacionList;
    }

    public void setProgramacionList(List<Programacion> programacionList) {
        this.programacionList = programacionList;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitudprogramacion != null ? idSolicitudprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudProgramacion)) {
            return false;
        }
        SolicitudProgramacion other = (SolicitudProgramacion) object;
        if ((this.idSolicitudprogramacion == null && other.idSolicitudprogramacion != null) || (this.idSolicitudprogramacion != null && !this.idSolicitudprogramacion.equals(other.idSolicitudprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SolicitudProgramacion[ idSolicitudprogramacion=" + idSolicitudprogramacion + " ]";
    }
    
}
