/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "vista_evento")
@NamedQueries({
    @NamedQuery(name = "VistaEvento.findAll", query = "SELECT v FROM VistaEvento v"),
    @NamedQuery(name = "VistaEvento.findByIdOfertaevento", query = "SELECT v FROM VistaEvento v WHERE v.idOfertaevento = :idOfertaevento"),
    @NamedQuery(name = "VistaEvento.findByFechahoraInicio", query = "SELECT v FROM VistaEvento v WHERE v.fechahoraInicio = :fechahoraInicio"),
    @NamedQuery(name = "VistaEvento.findByFechahoraFin", query = "SELECT v FROM VistaEvento v WHERE v.fechahoraFin = :fechahoraFin"),
    @NamedQuery(name = "VistaEvento.findByCupoMaximo", query = "SELECT v FROM VistaEvento v WHERE v.cupoMaximo = :cupoMaximo"),
    @NamedQuery(name = "VistaEvento.findByCupoMinimo", query = "SELECT v FROM VistaEvento v WHERE v.cupoMinimo = :cupoMinimo"),
    @NamedQuery(name = "VistaEvento.findByVigenciaInicio", query = "SELECT v FROM VistaEvento v WHERE v.vigenciaInicio = :vigenciaInicio"),
    @NamedQuery(name = "VistaEvento.findByVigenciaFin", query = "SELECT v FROM VistaEvento v WHERE v.vigenciaFin = :vigenciaFin"),
    @NamedQuery(name = "VistaEvento.findByEstadoOferta", query = "SELECT v FROM VistaEvento v WHERE v.estadoOferta = :estadoOferta"),
    @NamedQuery(name = "VistaEvento.findByIdInstructor", query = "SELECT v FROM VistaEvento v WHERE v.idInstructor = :idInstructor"),
    @NamedQuery(name = "VistaEvento.findByNombreInstructor", query = "SELECT v FROM VistaEvento v WHERE v.nombreInstructor = :nombreInstructor"),
    @NamedQuery(name = "VistaEvento.findByIdEvento", query = "SELECT v FROM VistaEvento v WHERE v.idEvento = :idEvento"),
    @NamedQuery(name = "VistaEvento.findByIdPlantel", query = "SELECT v FROM VistaEvento v WHERE v.idPlantel = :idPlantel"),
    @NamedQuery(name = "VistaEvento.findByNombrePlantel", query = "SELECT v FROM VistaEvento v WHERE v.nombrePlantel = :nombrePlantel"),
    @NamedQuery(name = "VistaEvento.findByIdTipoevento", query = "SELECT v FROM VistaEvento v WHERE v.idTipoevento = :idTipoevento"),
    @NamedQuery(name = "VistaEvento.findByDescripcionTipoevento", query = "SELECT v FROM VistaEvento v WHERE v.descripcionTipoevento = :descripcionTipoevento"),
    @NamedQuery(name = "VistaEvento.findByClaveSep", query = "SELECT v FROM VistaEvento v WHERE v.claveSep = :claveSep"),
    @NamedQuery(name = "VistaEvento.findByNombreEvento", query = "SELECT v FROM VistaEvento v WHERE v.nombreEvento = :nombreEvento")})
public class VistaEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "id_ofertaevento")
    private BigInteger idOfertaevento;
    @Column(name = "fechahora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraInicio;
    @Column(name = "fechahora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraFin;
    @Column(name = "cupo_maximo")
    private Integer cupoMaximo;
    @Column(name = "cupo_minimo")
    private Integer cupoMinimo;
    @Column(name = "vigencia_inicio")
    @Temporal(TemporalType.DATE)
    private Date vigenciaInicio;
    @Column(name = "vigencia_fin")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFin;
    @Column(name = "estado_oferta")
    private String estadoOferta;
    @Column(name = "id_instructor")
    private BigInteger idInstructor;
    @Column(name = "nombre_instructor")
    private String nombreInstructor;
    @Column(name = "id_evento")
    private BigInteger idEvento;
    @Column(name = "id_plantel")
    private BigInteger idPlantel;
    @Column(name = "nombre_plantel")
    private String nombrePlantel;
    @Column(name = "id_tipoevento")
    private BigInteger idTipoevento;
    @Column(name = "descripcion_tipoevento")
    private String descripcionTipoevento;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "nombre_evento")
    private String nombreEvento;

    public VistaEvento() {
    }

    public BigInteger getIdOfertaevento() {
        return idOfertaevento;
    }

    public void setIdOfertaevento(BigInteger idOfertaevento) {
        this.idOfertaevento = idOfertaevento;
    }

    public Date getFechahoraInicio() {
        return fechahoraInicio;
    }

    public void setFechahoraInicio(Date fechahoraInicio) {
        this.fechahoraInicio = fechahoraInicio;
    }

    public Date getFechahoraFin() {
        return fechahoraFin;
    }

    public void setFechahoraFin(Date fechahoraFin) {
        this.fechahoraFin = fechahoraFin;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Integer getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(Integer cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public Date getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public Date getVigenciaFin() {
        return vigenciaFin;
    }

    public void setVigenciaFin(Date vigenciaFin) {
        this.vigenciaFin = vigenciaFin;
    }

    public String getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(String estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    public BigInteger getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(BigInteger idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public BigInteger getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(BigInteger idEvento) {
        this.idEvento = idEvento;
    }

    public BigInteger getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(BigInteger idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getNombrePlantel() {
        return nombrePlantel;
    }

    public void setNombrePlantel(String nombrePlantel) {
        this.nombrePlantel = nombrePlantel;
    }

    public BigInteger getIdTipoevento() {
        return idTipoevento;
    }

    public void setIdTipoevento(BigInteger idTipoevento) {
        this.idTipoevento = idTipoevento;
    }

    public String getDescripcionTipoevento() {
        return descripcionTipoevento;
    }

    public void setDescripcionTipoevento(String descripcionTipoevento) {
        this.descripcionTipoevento = descripcionTipoevento;
    }

    public String getClaveSep() {
        return claveSep;
    }

    public void setClaveSep(String claveSep) {
        this.claveSep = claveSep;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    
}
