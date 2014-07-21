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
@Table(name = "vista_calificacionesgeneral")
@NamedQueries({
    @NamedQuery(name = "VistaCalificacionesgeneral.findAll", query = "SELECT v FROM VistaCalificacionesgeneral v"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByMatricula", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByIdEsquemaalumnoasignatura", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByIdAsignatura", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByNombreAsignatura", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.nombreAsignatura = :nombreAsignatura"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByClaveSep", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.claveSep = :claveSep"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByNumeroAxo", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.numeroAxo = :numeroAxo"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByEsquema", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.esquema = :esquema"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByDescripcionTipoprogramacion", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.descripcionTipoprogramacion = :descripcionTipoprogramacion"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByIdGrupo", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.idGrupo = :idGrupo"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByIdTipoexamen", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByOrden", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.orden = :orden"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByTipoExamen", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.tipoExamen = :tipoExamen"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByTrabajo", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.trabajo = :trabajo"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByExamen", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.examen = :examen"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByIdTiponivelacademico", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.idTiponivelacademico = :idTiponivelacademico"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByPromedio", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.promedio = :promedio"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByFechaRegistro", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByObservacion", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.observacion = :observacion"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByAsesor", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.asesor = :asesor"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByIdAsesor", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.idAsesor = :idAsesor"),
    @NamedQuery(name = "VistaCalificacionesgeneral.findByPromedioDescripcion", query = "SELECT v FROM VistaCalificacionesgeneral v WHERE v.promedioDescripcion = :promedioDescripcion")})
public class VistaCalificacionesgeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "id_esquemaalumnoasignatura")
    private BigInteger idEsquemaalumnoasignatura;
    @Column(name = "id_asignatura")
    private BigInteger idAsignatura;
    @Column(name = "nombre_asignatura")
    private String nombreAsignatura;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "numero_axo")
    private Integer numeroAxo;
    @Column(name = "esquema")
    private BigInteger esquema;
    @Column(name = "descripcion_tipoprogramacion")
    private String descripcionTipoprogramacion;
    @Column(name = "id_grupo")
    private BigInteger idGrupo;
    @Column(name = "id_tipoexamen")
    private BigInteger idTipoexamen;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "tipo_examen")
    private String tipoExamen;
    @Column(name = "trabajo")
    private String trabajo;
    @Column(name = "examen")
    private String examen;
    @Column(name = "id_tiponivelacademico")
    private BigInteger idTiponivelacademico;
    @Column(name = "promedio")
    private String promedio;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "asesor")
    private String asesor;
    @Column(name = "id_asesor")
    private BigInteger idAsesor;
    @Column(name = "promedio_descripcion")
    private String promedioDescripcion;

    public VistaCalificacionesgeneral() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BigInteger getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(BigInteger idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public BigInteger getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(BigInteger idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getClaveSep() {
        return claveSep;
    }

    public void setClaveSep(String claveSep) {
        this.claveSep = claveSep;
    }

    public Integer getNumeroAxo() {
        return numeroAxo;
    }

    public void setNumeroAxo(Integer numeroAxo) {
        this.numeroAxo = numeroAxo;
    }

    public BigInteger getEsquema() {
        return esquema;
    }

    public void setEsquema(BigInteger esquema) {
        this.esquema = esquema;
    }

    public String getDescripcionTipoprogramacion() {
        return descripcionTipoprogramacion;
    }

    public void setDescripcionTipoprogramacion(String descripcionTipoprogramacion) {
        this.descripcionTipoprogramacion = descripcionTipoprogramacion;
    }

    public BigInteger getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigInteger idGrupo) {
        this.idGrupo = idGrupo;
    }

    public BigInteger getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(BigInteger idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public BigInteger getIdTiponivelacademico() {
        return idTiponivelacademico;
    }

    public void setIdTiponivelacademico(BigInteger idTiponivelacademico) {
        this.idTiponivelacademico = idTiponivelacademico;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public BigInteger getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(BigInteger idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getPromedioDescripcion() {
        return promedioDescripcion;
    }

    public void setPromedioDescripcion(String promedioDescripcion) {
        this.promedioDescripcion = promedioDescripcion;
    }
    
}
