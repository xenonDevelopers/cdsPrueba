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
@Table(name = "vista_esquemaalumno")
@NamedQueries({
    @NamedQuery(name = "VistaEsquemaalumno.findAll", query = "SELECT v FROM VistaEsquemaalumno v"),
    @NamedQuery(name = "VistaEsquemaalumno.findByMatricula", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaEsquemaalumno.findByIdAsignatura", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "VistaEsquemaalumno.findByClaveSep", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.claveSep = :claveSep"),
    @NamedQuery(name = "VistaEsquemaalumno.findByNombreAsignatura", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.nombreAsignatura = :nombreAsignatura"),
    @NamedQuery(name = "VistaEsquemaalumno.findByIdAlumnoasignatura", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.idAlumnoasignatura = :idAlumnoasignatura"),
    @NamedQuery(name = "VistaEsquemaalumno.findByEsquema", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.esquema = :esquema"),
    @NamedQuery(name = "VistaEsquemaalumno.findByFechaRegistro", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "VistaEsquemaalumno.findByIdEsquemaalumnoasignatura", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "VistaEsquemaalumno.findByDescripcionTipoprogramacion", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.descripcionTipoprogramacion = :descripcionTipoprogramacion"),
    @NamedQuery(name = "VistaEsquemaalumno.findByIdGrupo", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.idGrupo = :idGrupo"),
    @NamedQuery(name = "VistaEsquemaalumno.findByGrupo", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.grupo = :grupo"),
    @NamedQuery(name = "VistaEsquemaalumno.findByIdPlanestudio", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.idPlanestudio = :idPlanestudio"),
    @NamedQuery(name = "VistaEsquemaalumno.findByIdAsignaturaseriada", query = "SELECT v FROM VistaEsquemaalumno v WHERE v.idAsignaturaseriada = :idAsignaturaseriada")})
public class VistaEsquemaalumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "id_asignatura")
    private BigInteger idAsignatura;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "nombre_asignatura")
    private String nombreAsignatura;
    @Column(name = "id_alumnoasignatura")
    private BigInteger idAlumnoasignatura;
    @Column(name = "esquema")
    private BigInteger esquema;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "id_esquemaalumnoasignatura")
    private BigInteger idEsquemaalumnoasignatura;
    @Column(name = "descripcion_tipoprogramacion")
    private String descripcionTipoprogramacion;
    @Column(name = "id_grupo")
    private BigInteger idGrupo;
    @Column(name = "grupo")
    private String grupo;
    @Column(name = "id_planestudio")
    private BigInteger idPlanestudio;
    @Column(name = "id_asignaturaseriada")
    private BigInteger idAsignaturaseriada;

    public VistaEsquemaalumno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BigInteger getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(BigInteger idAsignatura) {
        this.idAsignatura = idAsignatura;
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

    public BigInteger getIdAlumnoasignatura() {
        return idAlumnoasignatura;
    }

    public void setIdAlumnoasignatura(BigInteger idAlumnoasignatura) {
        this.idAlumnoasignatura = idAlumnoasignatura;
    }

    public BigInteger getEsquema() {
        return esquema;
    }

    public void setEsquema(BigInteger esquema) {
        this.esquema = esquema;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigInteger getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(BigInteger idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
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
    
}
