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
@Table(name = "vista_esquemas")
@NamedQueries({
    @NamedQuery(name = "VistaEsquemas.findAll", query = "SELECT v FROM VistaEsquemas v"),
    @NamedQuery(name = "VistaEsquemas.findByIdAlumnoasignatura", query = "SELECT v FROM VistaEsquemas v WHERE v.idAlumnoasignatura = :idAlumnoasignatura"),
    @NamedQuery(name = "VistaEsquemas.findByIdEsquemaalumnoasignatura", query = "SELECT v FROM VistaEsquemas v WHERE v.idEsquemaalumnoasignatura = :idEsquemaalumnoasignatura"),
    @NamedQuery(name = "VistaEsquemas.findByFechaRegistro", query = "SELECT v FROM VistaEsquemas v WHERE v.fechaRegistro = :fechaRegistro")})
public class VistaEsquemas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "id_alumnoasignatura")
    private BigInteger idAlumnoasignatura;
    @Column(name = "id_esquemaalumnoasignatura")
    private BigInteger idEsquemaalumnoasignatura;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    public VistaEsquemas() {
    }

    public BigInteger getIdAlumnoasignatura() {
        return idAlumnoasignatura;
    }

    public void setIdAlumnoasignatura(BigInteger idAlumnoasignatura) {
        this.idAlumnoasignatura = idAlumnoasignatura;
    }

    public BigInteger getIdEsquemaalumnoasignatura() {
        return idEsquemaalumnoasignatura;
    }

    public void setIdEsquemaalumnoasignatura(BigInteger idEsquemaalumnoasignatura) {
        this.idEsquemaalumnoasignatura = idEsquemaalumnoasignatura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
