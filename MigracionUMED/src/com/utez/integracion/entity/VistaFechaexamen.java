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
@Table(name = "vista_fechaexamen")
@NamedQueries({
    @NamedQuery(name = "VistaFechaexamen.findAll", query = "SELECT v FROM VistaFechaexamen v"),
    @NamedQuery(name = "VistaFechaexamen.findByIdFechaexamen", query = "SELECT v FROM VistaFechaexamen v WHERE v.idFechaexamen = :idFechaexamen"),
    @NamedQuery(name = "VistaFechaexamen.findByFechaExamenprogramado", query = "SELECT v FROM VistaFechaexamen v WHERE v.fechaExamenprogramado = :fechaExamenprogramado"),
    @NamedQuery(name = "VistaFechaexamen.findByFechaExamen", query = "SELECT v FROM VistaFechaexamen v WHERE v.fechaExamen = :fechaExamen"),
    @NamedQuery(name = "VistaFechaexamen.findByProgramadoFep", query = "SELECT v FROM VistaFechaexamen v WHERE v.programadoFep = :programadoFep"),
    @NamedQuery(name = "VistaFechaexamen.findByProgramado", query = "SELECT v FROM VistaFechaexamen v WHERE v.programado = :programado"),
    @NamedQuery(name = "VistaFechaexamen.findByIdTipoexamen", query = "SELECT v FROM VistaFechaexamen v WHERE v.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "VistaFechaexamen.findByDescripcionTipoexamen", query = "SELECT v FROM VistaFechaexamen v WHERE v.descripcionTipoexamen = :descripcionTipoexamen"),
    @NamedQuery(name = "VistaFechaexamen.findByOrden", query = "SELECT v FROM VistaFechaexamen v WHERE v.orden = :orden"),
    @NamedQuery(name = "VistaFechaexamen.findByIdCalendarioasignatura", query = "SELECT v FROM VistaFechaexamen v WHERE v.idCalendarioasignatura = :idCalendarioasignatura"),
    @NamedQuery(name = "VistaFechaexamen.findByIdCalendarioescolar", query = "SELECT v FROM VistaFechaexamen v WHERE v.idCalendarioescolar = :idCalendarioescolar"),
    @NamedQuery(name = "VistaFechaexamen.findByIdAsignatura", query = "SELECT v FROM VistaFechaexamen v WHERE v.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "VistaFechaexamen.findByIdPlanestudio", query = "SELECT v FROM VistaFechaexamen v WHERE v.idPlanestudio = :idPlanestudio"),
    @NamedQuery(name = "VistaFechaexamen.findByIdAsignaturaseriada", query = "SELECT v FROM VistaFechaexamen v WHERE v.idAsignaturaseriada = :idAsignaturaseriada"),
    @NamedQuery(name = "VistaFechaexamen.findByIdTipoasignatura", query = "SELECT v FROM VistaFechaexamen v WHERE v.idTipoasignatura = :idTipoasignatura"),
    @NamedQuery(name = "VistaFechaexamen.findByIdTiponivelasignatura", query = "SELECT v FROM VistaFechaexamen v WHERE v.idTiponivelasignatura = :idTiponivelasignatura"),
    @NamedQuery(name = "VistaFechaexamen.findByClaveSep", query = "SELECT v FROM VistaFechaexamen v WHERE v.claveSep = :claveSep"),
    @NamedQuery(name = "VistaFechaexamen.findByNombreAsignatura", query = "SELECT v FROM VistaFechaexamen v WHERE v.nombreAsignatura = :nombreAsignatura"),
    @NamedQuery(name = "VistaFechaexamen.findByCreditos", query = "SELECT v FROM VistaFechaexamen v WHERE v.creditos = :creditos"),
    @NamedQuery(name = "VistaFechaexamen.findByBloque", query = "SELECT v FROM VistaFechaexamen v WHERE v.bloque = :bloque"),
    @NamedQuery(name = "VistaFechaexamen.findByNumeroOrden", query = "SELECT v FROM VistaFechaexamen v WHERE v.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "VistaFechaexamen.findByIdHorario", query = "SELECT v FROM VistaFechaexamen v WHERE v.idHorario = :idHorario"),
    @NamedQuery(name = "VistaFechaexamen.findByEstadoBloque", query = "SELECT v FROM VistaFechaexamen v WHERE v.estadoBloque = :estadoBloque"),
    @NamedQuery(name = "VistaFechaexamen.findByAbreviaturaPersona", query = "SELECT v FROM VistaFechaexamen v WHERE v.abreviaturaPersona = :abreviaturaPersona"),
    @NamedQuery(name = "VistaFechaexamen.findByNombres", query = "SELECT v FROM VistaFechaexamen v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "VistaFechaexamen.findByApellidoPaterno", query = "SELECT v FROM VistaFechaexamen v WHERE v.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "VistaFechaexamen.findByApellidoMaterno", query = "SELECT v FROM VistaFechaexamen v WHERE v.apellidoMaterno = :apellidoMaterno")})
public class VistaFechaexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "id_fechaexamen")
    private BigInteger idFechaexamen;
    @Column(name = "fecha_examenprogramado")
    @Temporal(TemporalType.DATE)
    private Date fechaExamenprogramado;
    @Column(name = "fecha_examen")
    @Temporal(TemporalType.DATE)
    private Date fechaExamen;
    @Column(name = "programado_fep")
    private Boolean programadoFep;
    @Column(name = "programado")
    private Integer programado;
    @Column(name = "id_tipoexamen")
    private BigInteger idTipoexamen;
    @Column(name = "descripcion_tipoexamen")
    private String descripcionTipoexamen;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "id_calendarioasignatura")
    private BigInteger idCalendarioasignatura;
    @Column(name = "id_calendarioescolar")
    private BigInteger idCalendarioescolar;
    @Column(name = "id_asignatura")
    private BigInteger idAsignatura;
    @Column(name = "id_planestudio")
    private BigInteger idPlanestudio;
    @Column(name = "id_asignaturaseriada")
    private BigInteger idAsignaturaseriada;
    @Column(name = "id_tipoasignatura")
    private BigInteger idTipoasignatura;
    @Column(name = "id_tiponivelasignatura")
    private BigInteger idTiponivelasignatura;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "nombre_asignatura")
    private String nombreAsignatura;
    @Column(name = "creditos")
    private Integer creditos;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "numero_orden")
    private Integer numeroOrden;
    @Column(name = "id_horario")
    private BigInteger idHorario;
    @Column(name = "estado_bloque")
    private String estadoBloque;
    @Column(name = "abreviatura_persona")
    private String abreviaturaPersona;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    public VistaFechaexamen() {
    }

    public BigInteger getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(BigInteger idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public Date getFechaExamenprogramado() {
        return fechaExamenprogramado;
    }

    public void setFechaExamenprogramado(Date fechaExamenprogramado) {
        this.fechaExamenprogramado = fechaExamenprogramado;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Boolean getProgramadoFep() {
        return programadoFep;
    }

    public void setProgramadoFep(Boolean programadoFep) {
        this.programadoFep = programadoFep;
    }

    public Integer getProgramado() {
        return programado;
    }

    public void setProgramado(Integer programado) {
        this.programado = programado;
    }

    public BigInteger getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(BigInteger idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public String getDescripcionTipoexamen() {
        return descripcionTipoexamen;
    }

    public void setDescripcionTipoexamen(String descripcionTipoexamen) {
        this.descripcionTipoexamen = descripcionTipoexamen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public BigInteger getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(BigInteger idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    public BigInteger getIdCalendarioescolar() {
        return idCalendarioescolar;
    }

    public void setIdCalendarioescolar(BigInteger idCalendarioescolar) {
        this.idCalendarioescolar = idCalendarioescolar;
    }

    public BigInteger getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(BigInteger idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public BigInteger getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(BigInteger idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public BigInteger getIdAsignaturaseriada() {
        return idAsignaturaseriada;
    }

    public void setIdAsignaturaseriada(BigInteger idAsignaturaseriada) {
        this.idAsignaturaseriada = idAsignaturaseriada;
    }

    public BigInteger getIdTipoasignatura() {
        return idTipoasignatura;
    }

    public void setIdTipoasignatura(BigInteger idTipoasignatura) {
        this.idTipoasignatura = idTipoasignatura;
    }

    public BigInteger getIdTiponivelasignatura() {
        return idTiponivelasignatura;
    }

    public void setIdTiponivelasignatura(BigInteger idTiponivelasignatura) {
        this.idTiponivelasignatura = idTiponivelasignatura;
    }

    public String getClaveSep() {
        return claveSep;
    }

    public void setClaveSep(String claveSep) {
        this.claveSep = claveSep;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public BigInteger getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(BigInteger idHorario) {
        this.idHorario = idHorario;
    }

    public String getEstadoBloque() {
        return estadoBloque;
    }

    public void setEstadoBloque(String estadoBloque) {
        this.estadoBloque = estadoBloque;
    }

    public String getAbreviaturaPersona() {
        return abreviaturaPersona;
    }

    public void setAbreviaturaPersona(String abreviaturaPersona) {
        this.abreviaturaPersona = abreviaturaPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
}
