/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "vista_calificados")
@NamedQueries({
    @NamedQuery(name = "VistaCalificados.findAll", query = "SELECT v FROM VistaCalificados v"),
    @NamedQuery(name = "VistaCalificados.findByMatricula", query = "SELECT v FROM VistaCalificados v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VistaCalificados.findByIdEsquemaalumnoasignatura", query = "SELECT v FROM VistaCalificados v WHERE v.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "VistaCalificados.findByNombres", query = "SELECT v FROM VistaCalificados v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "VistaCalificados.findByApellidoPaterno", query = "SELECT v FROM VistaCalificados v WHERE v.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "VistaCalificados.findByApellidoMaterno", query = "SELECT v FROM VistaCalificados v WHERE v.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "VistaCalificados.findByIdGrupo", query = "SELECT v FROM VistaCalificados v WHERE v.idGrupo = :idGrupo"),
    @NamedQuery(name = "VistaCalificados.findByIdAsignatura", query = "SELECT v FROM VistaCalificados v WHERE v.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "VistaCalificados.findByIdTipoexamen", query = "SELECT v FROM VistaCalificados v WHERE v.idTipoexamen = :idTipoexamen"),
    @NamedQuery(name = "VistaCalificados.findByCalificar", query = "SELECT v FROM VistaCalificados v WHERE v.calificar = :calificar"),
    @NamedQuery(name = "VistaCalificados.findByIdAsesor", query = "SELECT v FROM VistaCalificados v WHERE v.idAsesor = :idAsesor"),
    @NamedQuery(name = "VistaCalificados.findByExamen", query = "SELECT v FROM VistaCalificados v WHERE v.examen = :examen"),
    @NamedQuery(name = "VistaCalificados.findBySeriadaAprobada", query = "SELECT v FROM VistaCalificados v WHERE v.seriadaAprobada = :seriadaAprobada")})
public class VistaCalificados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "id_esquemaalumnoasignatura")
    private BigInteger idEsquemaalumnoasignatura;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "id_grupo")
    private BigInteger idGrupo;
    @Column(name = "id_asignatura")
    private BigInteger idAsignatura;
    @Column(name = "id_tipoexamen")
    private BigInteger idTipoexamen;
    @Column(name = "calificar")
    private Boolean calificar;
    @Column(name = "id_asesor")
    private BigInteger idAsesor;
    @Column(name = "examen")
    private String examen;
    @Column(name = "seriada_aprobada")
    private Boolean seriadaAprobada;

    public VistaCalificados() {
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

    public BigInteger getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigInteger idGrupo) {
        this.idGrupo = idGrupo;
    }

    public BigInteger getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(BigInteger idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public BigInteger getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(BigInteger idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public Boolean getCalificar() {
        return calificar;
    }

    public void setCalificar(Boolean calificar) {
        this.calificar = calificar;
    }

    public BigInteger getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(BigInteger idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public Boolean getSeriadaAprobada() {
        return seriadaAprobada;
    }

    public void setSeriadaAprobada(Boolean seriadaAprobada) {
        this.seriadaAprobada = seriadaAprobada;
    }
    
}
