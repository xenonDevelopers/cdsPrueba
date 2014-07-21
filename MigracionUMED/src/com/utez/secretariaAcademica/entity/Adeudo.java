/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "adeudo")
@NamedQueries({
    @NamedQuery(name = "Adeudo.findAll", query = "SELECT a FROM Adeudo a"),
    @NamedQuery(name = "Adeudo.findByIdadeudo", query = "SELECT a FROM Adeudo a WHERE a.idadeudo = :idadeudo"),
    @NamedQuery(name = "Adeudo.findByAdeudo", query = "SELECT a FROM Adeudo a WHERE a.adeudo = :adeudo"),
    @NamedQuery(name = "Adeudo.findByFechasubida", query = "SELECT a FROM Adeudo a WHERE a.fechasubida = :fechasubida")})
public class Adeudo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadeudo")
    private Integer idadeudo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "adeudo")
    private Float adeudo;
    @Column(name = "fechasubida")
    @Temporal(TemporalType.DATE)
    private Date fechasubida;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ManyToOne
    private Alumno matricula;

    public Adeudo() {
    }

    public Adeudo(Integer idadeudo) {
        this.idadeudo = idadeudo;
    }

    public Integer getIdadeudo() {
        return idadeudo;
    }

    public void setIdadeudo(Integer idadeudo) {
        this.idadeudo = idadeudo;
    }

    public Float getAdeudo() {
        return adeudo;
    }

    public void setAdeudo(Float adeudo) {
        this.adeudo = adeudo;
    }

    public Date getFechasubida() {
        return fechasubida;
    }

    public void setFechasubida(Date fechasubida) {
        this.fechasubida = fechasubida;
    }

    public Alumno getMatricula() {
        return matricula;
    }

    public void setMatricula(Alumno matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadeudo != null ? idadeudo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adeudo)) {
            return false;
        }
        Adeudo other = (Adeudo) object;
        if ((this.idadeudo == null && other.idadeudo != null) || (this.idadeudo != null && !this.idadeudo.equals(other.idadeudo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Adeudo[ idadeudo=" + idadeudo + " ]";
    }
    
}
