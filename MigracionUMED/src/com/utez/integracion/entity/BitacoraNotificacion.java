/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Grupo;
import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "bitacora_notificacion")
@NamedQueries({
    @NamedQuery(name = "BitacoraNotificacion.findAll", query = "SELECT b FROM BitacoraNotificacion b"),
    @NamedQuery(name = "BitacoraNotificacion.findByIdBitacoranotificacion", query = "SELECT b FROM BitacoraNotificacion b WHERE b.idBitacoranotificacion = :idBitacoranotificacion"),
    @NamedQuery(name = "BitacoraNotificacion.findByAnterior", query = "SELECT b FROM BitacoraNotificacion b WHERE b.anterior = :anterior"),
    @NamedQuery(name = "BitacoraNotificacion.findByNuevo", query = "SELECT b FROM BitacoraNotificacion b WHERE b.nuevo = :nuevo"),
    @NamedQuery(name = "BitacoraNotificacion.findByIdReferencia", query = "SELECT b FROM BitacoraNotificacion b WHERE b.idReferencia = :idReferencia"),
    @NamedQuery(name = "BitacoraNotificacion.findByIdReferencia2", query = "SELECT b FROM BitacoraNotificacion b WHERE b.idReferencia2 = :idReferencia2")})
public class BitacoraNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacoranotificacion")
    private Long idBitacoranotificacion;
    @Column(name = "anterior")
    private String anterior;
    @Column(name = "nuevo")
    private String nuevo;
    @Column(name = "id_referencia")
    private BigInteger idReferencia;
    @Column(name = "id_referencia2")
    private BigInteger idReferencia2;
    @JoinColumn(name = "id_tiponotificacion", referencedColumnName = "id_tiponotificacion")
    @ManyToOne
    private TipoNotificacion idTiponotificacion;
    @JoinColumn(name = "id_notificacioncalendario", referencedColumnName = "id_notificacioncalendario")
    @ManyToOne
    private NotificacionCalendario idNotificacioncalendario;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;

    public BitacoraNotificacion() {
    }

    public BitacoraNotificacion(Long idBitacoranotificacion) {
        this.idBitacoranotificacion = idBitacoranotificacion;
    }

    public Long getIdBitacoranotificacion() {
        return idBitacoranotificacion;
    }

    public void setIdBitacoranotificacion(Long idBitacoranotificacion) {
        this.idBitacoranotificacion = idBitacoranotificacion;
    }

    public String getAnterior() {
        return anterior;
    }

    public void setAnterior(String anterior) {
        this.anterior = anterior;
    }

    public String getNuevo() {
        return nuevo;
    }

    public void setNuevo(String nuevo) {
        this.nuevo = nuevo;
    }

    public BigInteger getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(BigInteger idReferencia) {
        this.idReferencia = idReferencia;
    }

    public BigInteger getIdReferencia2() {
        return idReferencia2;
    }

    public void setIdReferencia2(BigInteger idReferencia2) {
        this.idReferencia2 = idReferencia2;
    }

    public TipoNotificacion getIdTiponotificacion() {
        return idTiponotificacion;
    }

    public void setIdTiponotificacion(TipoNotificacion idTiponotificacion) {
        this.idTiponotificacion = idTiponotificacion;
    }

    public NotificacionCalendario getIdNotificacioncalendario() {
        return idNotificacioncalendario;
    }

    public void setIdNotificacioncalendario(NotificacionCalendario idNotificacioncalendario) {
        this.idNotificacioncalendario = idNotificacioncalendario;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacoranotificacion != null ? idBitacoranotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraNotificacion)) {
            return false;
        }
        BitacoraNotificacion other = (BitacoraNotificacion) object;
        if ((this.idBitacoranotificacion == null && other.idBitacoranotificacion != null) || (this.idBitacoranotificacion != null && !this.idBitacoranotificacion.equals(other.idBitacoranotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.BitacoraNotificacion[ idBitacoranotificacion=" + idBitacoranotificacion + " ]";
    }
    
}
