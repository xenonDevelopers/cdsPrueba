/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "planestudios")
@NamedQueries({
    @NamedQuery(name = "Planestudios.findAll", query = "SELECT p FROM Planestudios p"),
    @NamedQuery(name = "Planestudios.findByRvoe", query = "SELECT p FROM Planestudios p WHERE p.rvoe = :rvoe"),
    @NamedQuery(name = "Planestudios.findByNombreplan", query = "SELECT p FROM Planestudios p WHERE p.nombreplan = :nombreplan"),
    @NamedQuery(name = "Planestudios.findByNivelacademico", query = "SELECT p FROM Planestudios p WHERE p.nivelacademico = :nivelacademico"),
    @NamedQuery(name = "Planestudios.findByEstado", query = "SELECT p FROM Planestudios p WHERE p.estado = :estado"),
    @NamedQuery(name = "Planestudios.findByFechaacuerdo", query = "SELECT p FROM Planestudios p WHERE p.fechaacuerdo = :fechaacuerdo"),
    @NamedQuery(name = "Planestudios.findByNumeroacuerdo", query = "SELECT p FROM Planestudios p WHERE p.numeroacuerdo = :numeroacuerdo"),
    @NamedQuery(name = "Planestudios.findByNumerocreditos", query = "SELECT p FROM Planestudios p WHERE p.numerocreditos = :numerocreditos"),
    @NamedQuery(name = "Planestudios.findByLetras", query = "SELECT p FROM Planestudios p WHERE p.letras = :letras"),
    @NamedQuery(name = "Planestudios.findByInstitucionregistro", query = "SELECT p FROM Planestudios p WHERE p.institucionregistro = :institucionregistro")})
public class Planestudios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rvoe")
    private String rvoe;
    @Column(name = "nombreplan")
    private String nombreplan;
    @Column(name = "nivelacademico")
    private String nivelacademico;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaacuerdo")
    @Temporal(TemporalType.DATE)
    private Date fechaacuerdo;
    @Column(name = "numeroacuerdo")
    private String numeroacuerdo;
    @Column(name = "numerocreditos")
    private Integer numerocreditos;
    @Column(name = "letras")
    private String letras;
    @Column(name = "institucionregistro")
    private String institucionregistro;
    @ManyToMany(mappedBy = "planestudiosList")
    private List<Asesor> asesorList;
    @OneToMany(mappedBy = "rvoe")
    private List<Asignatura> asignaturaList;
    @OneToMany(mappedBy = "rvoe")
    private List<Grupo> grupoList;
    @JoinColumn(name = "idplantel", referencedColumnName = "idplantel")
    @ManyToOne(optional = false)
    private Plantel idplantel;

    public Planestudios() {
    }

    public Planestudios(String rvoe) {
        this.rvoe = rvoe;
    }

    public String getRvoe() {
        return rvoe;
    }

    public void setRvoe(String rvoe) {
        this.rvoe = rvoe;
    }

    public String getNombreplan() {
        return nombreplan;
    }

    public void setNombreplan(String nombreplan) {
        this.nombreplan = nombreplan;
    }

    public String getNivelacademico() {
        return nivelacademico;
    }

    public void setNivelacademico(String nivelacademico) {
        this.nivelacademico = nivelacademico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaacuerdo() {
        return fechaacuerdo;
    }

    public void setFechaacuerdo(Date fechaacuerdo) {
        this.fechaacuerdo = fechaacuerdo;
    }

    public String getNumeroacuerdo() {
        return numeroacuerdo;
    }

    public void setNumeroacuerdo(String numeroacuerdo) {
        this.numeroacuerdo = numeroacuerdo;
    }

    public Integer getNumerocreditos() {
        return numerocreditos;
    }

    public void setNumerocreditos(Integer numerocreditos) {
        this.numerocreditos = numerocreditos;
    }

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }

    public String getInstitucionregistro() {
        return institucionregistro;
    }

    public void setInstitucionregistro(String institucionregistro) {
        this.institucionregistro = institucionregistro;
    }

    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public Plantel getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Plantel idplantel) {
        this.idplantel = idplantel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rvoe != null ? rvoe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planestudios)) {
            return false;
        }
        Planestudios other = (Planestudios) object;
        if ((this.rvoe == null && other.rvoe != null) || (this.rvoe != null && !this.rvoe.equals(other.rvoe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Planestudios[ rvoe=" + rvoe + " ]";
    }
    
}
