/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "notificacion_calendario")
@NamedQueries({
    @NamedQuery(name = "NotificacionCalendario.findAll", query = "SELECT n FROM NotificacionCalendario n"),
    @NamedQuery(name = "NotificacionCalendario.findByIdNotificacioncalendario", query = "SELECT n FROM NotificacionCalendario n WHERE n.idNotificacioncalendario = :idNotificacioncalendario"),
    @NamedQuery(name = "NotificacionCalendario.findByDescripcionNotificacioncalendario", query = "SELECT n FROM NotificacionCalendario n WHERE n.descripcionNotificacioncalendario = :descripcionNotificacioncalendario")})
public class NotificacionCalendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notificacioncalendario")
    private Long idNotificacioncalendario;
    @Column(name = "descripcion_notificacioncalendario")
    private String descripcionNotificacioncalendario;
    @OneToMany(mappedBy = "idNotificacioncalendario")
    private List<DestinatarioNotificacion> destinatarioNotificacionList;
    @OneToMany(mappedBy = "idNotificacioncalendario")
    private List<BitacoraNotificacion> bitacoraNotificacionList;

    public NotificacionCalendario() {
    }

    public NotificacionCalendario(Long idNotificacioncalendario) {
        this.idNotificacioncalendario = idNotificacioncalendario;
    }

    public Long getIdNotificacioncalendario() {
        return idNotificacioncalendario;
    }

    public void setIdNotificacioncalendario(Long idNotificacioncalendario) {
        this.idNotificacioncalendario = idNotificacioncalendario;
    }

    public String getDescripcionNotificacioncalendario() {
        return descripcionNotificacioncalendario;
    }

    public void setDescripcionNotificacioncalendario(String descripcionNotificacioncalendario) {
        this.descripcionNotificacioncalendario = descripcionNotificacioncalendario;
    }

    public List<DestinatarioNotificacion> getDestinatarioNotificacionList() {
        return destinatarioNotificacionList;
    }

    public void setDestinatarioNotificacionList(List<DestinatarioNotificacion> destinatarioNotificacionList) {
        this.destinatarioNotificacionList = destinatarioNotificacionList;
    }

    public List<BitacoraNotificacion> getBitacoraNotificacionList() {
        return bitacoraNotificacionList;
    }

    public void setBitacoraNotificacionList(List<BitacoraNotificacion> bitacoraNotificacionList) {
        this.bitacoraNotificacionList = bitacoraNotificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacioncalendario != null ? idNotificacioncalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificacionCalendario)) {
            return false;
        }
        NotificacionCalendario other = (NotificacionCalendario) object;
        if ((this.idNotificacioncalendario == null && other.idNotificacioncalendario != null) || (this.idNotificacioncalendario != null && !this.idNotificacioncalendario.equals(other.idNotificacioncalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.NotificacionCalendario[ idNotificacioncalendario=" + idNotificacioncalendario + " ]";
    }
    
}
