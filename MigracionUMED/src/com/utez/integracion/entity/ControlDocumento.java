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
@Table(name = "control_documento")
@NamedQueries({
    @NamedQuery(name = "ControlDocumento.findAll", query = "SELECT c FROM ControlDocumento c"),
    @NamedQuery(name = "ControlDocumento.findByIdControldocumento", query = "SELECT c FROM ControlDocumento c WHERE c.idControldocumento = :idControldocumento"),
    @NamedQuery(name = "ControlDocumento.findByFechaEntrega", query = "SELECT c FROM ControlDocumento c WHERE c.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "ControlDocumento.findByEstadoDocumento", query = "SELECT c FROM ControlDocumento c WHERE c.estadoDocumento = :estadoDocumento")})
public class ControlDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_controldocumento")
    private Long idControldocumento;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "estado_documento")
    private String estadoDocumento;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    @ManyToOne
    private Documento idDocumento;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public ControlDocumento() {
    }

    public ControlDocumento(Long idControldocumento) {
        this.idControldocumento = idControldocumento;
    }

    public Long getIdControldocumento() {
        return idControldocumento;
    }

    public void setIdControldocumento(Long idControldocumento) {
        this.idControldocumento = idControldocumento;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(String estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public Documento getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documento idDocumento) {
        this.idDocumento = idDocumento;
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
        hash += (idControldocumento != null ? idControldocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlDocumento)) {
            return false;
        }
        ControlDocumento other = (ControlDocumento) object;
        if ((this.idControldocumento == null && other.idControldocumento != null) || (this.idControldocumento != null && !this.idControldocumento.equals(other.idControldocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ControlDocumento[ idControldocumento=" + idControldocumento + " ]";
    }
    
}
