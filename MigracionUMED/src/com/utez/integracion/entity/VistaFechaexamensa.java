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
@Table(name = "vista_fechaexamensa")
@NamedQueries({
    @NamedQuery(name = "VistaFechaexamensa.findAll", query = "SELECT v FROM VistaFechaexamensa v"),
    @NamedQuery(name = "VistaFechaexamensa.findByMatricula", query = "SELECT v FROM VistaFechaexamensa v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaFechaexamensa.findByIdEsquemaalumnoasignatura", query = "SELECT v FROM VistaFechaexamensa v WHERE v.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "VistaFechaexamensa.findByIdAsignatura", query = "SELECT v FROM VistaFechaexamensa v WHERE v.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "VistaFechaexamensa.findByClaveSep", query = "SELECT v FROM VistaFechaexamensa v WHERE v.claveSep = :claveSep"),
    @NamedQuery(name = "VistaFechaexamensa.findByNumeroAxo", query = "SELECT v FROM VistaFechaexamensa v WHERE v.numeroAxo = :numeroAxo"),
    @NamedQuery(name = "VistaFechaexamensa.findByEsquema", query = "SELECT v FROM VistaFechaexamensa v WHERE v.esquema = :esquema"),
    @NamedQuery(name = "VistaFechaexamensa.findByDescripcionTipoprogramacion", query = "SELECT v FROM VistaFechaexamensa v WHERE v.descripcionTipoprogramacion = :descripcionTipoprogramacion"),
    @NamedQuery(name = "VistaFechaexamensa.findByIdGrupo", query = "SELECT v FROM VistaFechaexamensa v WHERE v.idGrupo = :idGrupo"),
    @NamedQuery(name = "VistaFechaexamensa.findByIdTipoexamen", query = "SELECT v FROM VistaFechaexamensa v WHERE v.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "VistaFechaexamensa.findByOrden", query = "SELECT v FROM VistaFechaexamensa v WHERE v.orden = :orden"),
    @NamedQuery(name = "VistaFechaexamensa.findByTipoExamen", query = "SELECT v FROM VistaFechaexamensa v WHERE v.tipoExamen = :tipoExamen"),
    @NamedQuery(name = "VistaFechaexamensa.findByTrabajo", query = "SELECT v FROM VistaFechaexamensa v WHERE v.trabajo = :trabajo"),
    @NamedQuery(name = "VistaFechaexamensa.findByExamen", query = "SELECT v FROM VistaFechaexamensa v WHERE v.examen = :examen"),
    @NamedQuery(name = "VistaFechaexamensa.findByPromedio", query = "SELECT v FROM VistaFechaexamensa v WHERE v.promedio = :promedio"),
    @NamedQuery(name = "VistaFechaexamensa.findByPromedioDescripcion", query = "SELECT v FROM VistaFechaexamensa v WHERE v.promedioDescripcion = :promedioDescripcion"),
    @NamedQuery(name = "VistaFechaexamensa.findByFechaRegistro", query = "SELECT v FROM VistaFechaexamensa v WHERE v.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "VistaFechaexamensa.findByObservacion", query = "SELECT v FROM VistaFechaexamensa v WHERE v.observacion = :observacion"),
    @NamedQuery(name = "VistaFechaexamensa.findByAsesor", query = "SELECT v FROM VistaFechaexamensa v WHERE v.asesor = :asesor"),
    @NamedQuery(name = "VistaFechaexamensa.findByIdAsesor", query = "SELECT v FROM VistaFechaexamensa v WHERE v.idAsesor = :idAsesor"),
    @NamedQuery(name = "VistaFechaexamensa.findByFechaExamen", query = "SELECT v FROM VistaFechaexamensa v WHERE v.fechaExamen = :fechaExamen"),
    @NamedQuery(name = "VistaFechaexamensa.findByFechaExamenindividual", query = "SELECT v FROM VistaFechaexamensa v WHERE v.fechaExamenindividual = :fechaExamenindividual")})
public class VistaFechaexamensa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "id_esquemaalumnoasignatura")
    private BigInteger idEsquemaalumnoasignatura;
    @Column(name = "id_asignatura")
    private BigInteger idAsignatura;
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
    @Column(name = "promedio")
    private String promedio;
    @Column(name = "promedio_descripcion")
    private String promedioDescripcion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "asesor")
    private String asesor;
    @Column(name = "id_asesor")
    private BigInteger idAsesor;
    @Column(name = "fecha_examen")
    @Temporal(TemporalType.DATE)
    private Date fechaExamen;
    @Column(name = "fecha_examenindividual")
    @Temporal(TemporalType.DATE)
    private Date fechaExamenindividual;

    public VistaFechaexamensa() {
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

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getPromedioDescripcion() {
        return promedioDescripcion;
    }

    public void setPromedioDescripcion(String promedioDescripcion) {
        this.promedioDescripcion = promedioDescripcion;
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

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Date getFechaExamenindividual() {
        return fechaExamenindividual;
    }

    public void setFechaExamenindividual(Date fechaExamenindividual) {
        this.fechaExamenindividual = fechaExamenindividual;
    }
    
}
