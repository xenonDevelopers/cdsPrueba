/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
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
@Table(name = "compromiso_documentacion")
@NamedQueries({
    @NamedQuery(name = "CompromisoDocumentacion.findAll", query = "SELECT c FROM CompromisoDocumentacion c"),
    @NamedQuery(name = "CompromisoDocumentacion.findByIdCompromisodocumentacion", query = "SELECT c FROM CompromisoDocumentacion c WHERE c.idCompromisodocumentacion = :idCompromisodocumentacion"),
    @NamedQuery(name = "CompromisoDocumentacion.findByOtros", query = "SELECT c FROM CompromisoDocumentacion c WHERE c.otros = :otros"),
    @NamedQuery(name = "CompromisoDocumentacion.findByFechaEntrega", query = "SELECT c FROM CompromisoDocumentacion c WHERE c.fechaEntrega = :fechaEntrega")})
public class CompromisoDocumentacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compromisodocumentacion")
    private Long idCompromisodocumentacion;
    @Column(name = "otros")
    private String otros;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public CompromisoDocumentacion() {
    }

    public CompromisoDocumentacion(Long idCompromisodocumentacion) {
        this.idCompromisodocumentacion = idCompromisodocumentacion;
    }

    public Long getIdCompromisodocumentacion() {
        return idCompromisodocumentacion;
    }

    public void setIdCompromisodocumentacion(Long idCompromisodocumentacion) {
        this.idCompromisodocumentacion = idCompromisodocumentacion;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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
        hash += (idCompromisodocumentacion != null ? idCompromisodocumentacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompromisoDocumentacion)) {
            return false;
        }
        CompromisoDocumentacion other = (CompromisoDocumentacion) object;
        if ((this.idCompromisodocumentacion == null && other.idCompromisodocumentacion != null) || (this.idCompromisodocumentacion != null && !this.idCompromisodocumentacion.equals(other.idCompromisodocumentacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CompromisoDocumentacion[ idCompromisodocumentacion=" + idCompromisodocumentacion + " ]";
    }
    
}
