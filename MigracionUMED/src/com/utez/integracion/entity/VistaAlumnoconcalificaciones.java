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
@Table(name = "vista_alumnoconcalificaciones")
@NamedQueries({
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findAll", query = "SELECT v FROM VistaAlumnoconcalificaciones v"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByMatricula", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByIdEsquemaalumnoasignatura", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByIdAsignatura", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByNombreAsignatura", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.nombreAsignatura = :nombreAsignatura"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByClaveSep", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.claveSep = :claveSep"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByNumeroAxo", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.numeroAxo = :numeroAxo"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByEsquema", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.esquema = :esquema"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByDescripcionTipoprogramacion", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.descripcionTipoprogramacion = :descripcionTipoprogramacion"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByIdGrupo", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.idGrupo = :idGrupo"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByGrupo", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.grupo = :grupo"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByIdTipoexamen", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByOrden", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.orden = :orden"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByTipoExamen", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.tipoExamen = :tipoExamen"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByTrabajo", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.trabajo = :trabajo"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByExamen", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.examen = :examen"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByFechaRegistro", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByObservacion", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.observacion = :observacion"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByAsesor", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.asesor = :asesor"),
    @NamedQuery(name = "VistaAlumnoconcalificaciones.findByIdAsesor", query = "SELECT v FROM VistaAlumnoconcalificaciones v WHERE v.idAsesor = :idAsesor")})
public class VistaAlumnoconcalificaciones implements Serializable {
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
    @Column(name = "grupo")
    private String grupo;
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
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "asesor")
    private String asesor;
    @Column(name = "id_asesor")
    private BigInteger idAsesor;

    public VistaAlumnoconcalificaciones() {
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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
    
}
