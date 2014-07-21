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
@Table(name = "archivo_solicitudprogramacion")
@NamedQueries({
    @NamedQuery(name = "ArchivoSolicitudprogramacion.findAll", query = "SELECT a FROM ArchivoSolicitudprogramacion a"),
    @NamedQuery(name = "ArchivoSolicitudprogramacion.findByIdArchivosolicitudprogramacion", query = "SELECT a FROM ArchivoSolicitudprogramacion a WHERE a.idArchivosolicitudprogramacion = :idArchivosolicitudprogramacion"),
    @NamedQuery(name = "ArchivoSolicitudprogramacion.findByArchivo", query = "SELECT a FROM ArchivoSolicitudprogramacion a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "ArchivoSolicitudprogramacion.findByNombreArchivo", query = "SELECT a FROM ArchivoSolicitudprogramacion a WHERE a.nombreArchivo = :nombreArchivo")})
public class ArchivoSolicitudprogramacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_archivosolicitudprogramacion")
    private Long idArchivosolicitudprogramacion;
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @JoinColumn(name = "id_solicitudprogramacion", referencedColumnName = "id_solicitudprogramacion")
    @ManyToOne
    private SolicitudProgramacion idSolicitudprogramacion;

    public ArchivoSolicitudprogramacion() {
    }

    public ArchivoSolicitudprogramacion(Long idArchivosolicitudprogramacion) {
        this.idArchivosolicitudprogramacion = idArchivosolicitudprogramacion;
    }

    public Long getIdArchivosolicitudprogramacion() {
        return idArchivosolicitudprogramacion;
    }

    public void setIdArchivosolicitudprogramacion(Long idArchivosolicitudprogramacion) {
        this.idArchivosolicitudprogramacion = idArchivosolicitudprogramacion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public SolicitudProgramacion getIdSolicitudprogramacion() {
        return idSolicitudprogramacion;
    }

    public void setIdSolicitudprogramacion(SolicitudProgramacion idSolicitudprogramacion) {
        this.idSolicitudprogramacion = idSolicitudprogramacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivosolicitudprogramacion != null ? idArchivosolicitudprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArchivoSolicitudprogramacion)) {
            return false;
        }
        ArchivoSolicitudprogramacion other = (ArchivoSolicitudprogramacion) object;
        if ((this.idArchivosolicitudprogramacion == null && other.idArchivosolicitudprogramacion != null) || (this.idArchivosolicitudprogramacion != null && !this.idArchivosolicitudprogramacion.equals(other.idArchivosolicitudprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ArchivoSolicitudprogramacion[ idArchivosolicitudprogramacion=" + idArchivosolicitudprogramacion + " ]";
    }
    
}
