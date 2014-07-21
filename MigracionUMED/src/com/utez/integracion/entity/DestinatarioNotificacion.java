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
@Table(name = "destinatario_notificacion")
@NamedQueries({
    @NamedQuery(name = "DestinatarioNotificacion.findAll", query = "SELECT d FROM DestinatarioNotificacion d"),
    @NamedQuery(name = "DestinatarioNotificacion.findByIdDestinatarionotificacion", query = "SELECT d FROM DestinatarioNotificacion d WHERE d.idDestinatarionotificacion = :idDestinatarionotificacion")})
public class DestinatarioNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_destinatarionotificacion")
    private Long idDestinatarionotificacion;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @JoinColumn(name = "id_notificacioncalendario", referencedColumnName = "id_notificacioncalendario")
    @ManyToOne
    private NotificacionCalendario idNotificacioncalendario;

    public DestinatarioNotificacion() {
    }

    public DestinatarioNotificacion(Long idDestinatarionotificacion) {
        this.idDestinatarionotificacion = idDestinatarionotificacion;
    }

    public Long getIdDestinatarionotificacion() {
        return idDestinatarionotificacion;
    }

    public void setIdDestinatarionotificacion(Long idDestinatarionotificacion) {
        this.idDestinatarionotificacion = idDestinatarionotificacion;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public NotificacionCalendario getIdNotificacioncalendario() {
        return idNotificacioncalendario;
    }

    public void setIdNotificacioncalendario(NotificacionCalendario idNotificacioncalendario) {
        this.idNotificacioncalendario = idNotificacioncalendario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDestinatarionotificacion != null ? idDestinatarionotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DestinatarioNotificacion)) {
            return false;
        }
        DestinatarioNotificacion other = (DestinatarioNotificacion) object;
        if ((this.idDestinatarionotificacion == null && other.idDestinatarionotificacion != null) || (this.idDestinatarionotificacion != null && !this.idDestinatarionotificacion.equals(other.idDestinatarionotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.DestinatarioNotificacion[ idDestinatarionotificacion=" + idDestinatarionotificacion + " ]";
    }
    
}
