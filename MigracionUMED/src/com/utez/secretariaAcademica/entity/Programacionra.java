/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "programacionra")
@NamedQueries({
    @NamedQuery(name = "Programacionra.findAll", query = "SELECT p FROM Programacionra p"),
    @NamedQuery(name = "Programacionra.findByIdprogramacionra", query = "SELECT p FROM Programacionra p WHERE p.idprogramacionra = :idprogramacionra"),
    @NamedQuery(name = "Programacionra.findByIdgrupo", query = "SELECT p FROM Programacionra p WHERE p.idgrupo = :idgrupo"),
    @NamedQuery(name = "Programacionra.findByIdasignatura", query = "SELECT p FROM Programacionra p WHERE p.idasignatura = :idasignatura"),
    @NamedQuery(name = "Programacionra.findByIdcuadernoprogramacion", query = "SELECT p FROM Programacionra p WHERE p.idcuadernoprogramacion = :idcuadernoprogramacion"),
    @NamedQuery(name = "Programacionra.findByIdasignaturagrupo", query = "SELECT p FROM Programacionra p WHERE p.idasignaturagrupo = :idasignaturagrupo")})
public class Programacionra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprogramacionra")
    private Integer idprogramacionra;
    @Column(name = "idgrupo")
    private String idgrupo;
    @Column(name = "idasignatura")
    private Integer idasignatura;
    @Column(name = "idcuadernoprogramacion")
    private Integer idcuadernoprogramacion;
    @Column(name = "idasignaturagrupo")
    private Integer idasignaturagrupo;
    @JoinColumn(name = "idprogramacionra", referencedColumnName = "idcuadernoprogramacion", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cuadernoprogramacion cuadernoprogramacion;

    public Programacionra() {
    }

    public Programacionra(Integer idprogramacionra) {
        this.idprogramacionra = idprogramacionra;
    }

    public Integer getIdprogramacionra() {
        return idprogramacionra;
    }

    public void setIdprogramacionra(Integer idprogramacionra) {
        this.idprogramacionra = idprogramacionra;
    }

    public String getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(String idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Integer getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Integer idasignatura) {
        this.idasignatura = idasignatura;
    }

    public Integer getIdcuadernoprogramacion() {
        return idcuadernoprogramacion;
    }

    public void setIdcuadernoprogramacion(Integer idcuadernoprogramacion) {
        this.idcuadernoprogramacion = idcuadernoprogramacion;
    }

    public Integer getIdasignaturagrupo() {
        return idasignaturagrupo;
    }

    public void setIdasignaturagrupo(Integer idasignaturagrupo) {
        this.idasignaturagrupo = idasignaturagrupo;
    }

    public Cuadernoprogramacion getCuadernoprogramacion() {
        return cuadernoprogramacion;
    }

    public void setCuadernoprogramacion(Cuadernoprogramacion cuadernoprogramacion) {
        this.cuadernoprogramacion = cuadernoprogramacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprogramacionra != null ? idprogramacionra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionra)) {
            return false;
        }
        Programacionra other = (Programacionra) object;
        if ((this.idprogramacionra == null && other.idprogramacionra != null) || (this.idprogramacionra != null && !this.idprogramacionra.equals(other.idprogramacionra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Programacionra[ idprogramacionra=" + idprogramacionra + " ]";
    }
    
}
