/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secretariaAcademica.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "calendario")
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c"),
    @NamedQuery(name = "Calendario.findByIdcalendario", query = "SELECT c FROM Calendario c WHERE c.idcalendario = :idcalendario"),
    @NamedQuery(name = "Calendario.findByObservacion", query = "SELECT c FROM Calendario c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "Calendario.findByEstado", query = "SELECT c FROM Calendario c WHERE c.estado = :estado"),
    @NamedQuery(name = "Calendario.findByAxo", query = "SELECT c FROM Calendario c WHERE c.axo = :axo")})
public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcalendario")
    private Integer idcalendario;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "axo")
    private Integer axo;
    @OneToMany(mappedBy = "idcalendario")
    private List<Asesoriaasignatura> asesoriaasignaturaList;
    @JoinColumn(name = "idplantel", referencedColumnName = "idplantel")
    @ManyToOne
    private Plantel idplantel;
    @JoinColumn(name = "periodo", referencedColumnName = "periodo")
    @ManyToOne
    private Periodo periodo;
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo")
    @ManyToOne
    private Grupo idgrupo;

    public Calendario() {
    }

    public Calendario(Integer idcalendario) {
        this.idcalendario = idcalendario;
    }

    public Integer getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(Integer idcalendario) {
        this.idcalendario = idcalendario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getAxo() {
        return axo;
    }

    public void setAxo(Integer axo) {
        this.axo = axo;
    }

    public List<Asesoriaasignatura> getAsesoriaasignaturaList() {
        return asesoriaasignaturaList;
    }

    public void setAsesoriaasignaturaList(List<Asesoriaasignatura> asesoriaasignaturaList) {
        this.asesoriaasignaturaList = asesoriaasignaturaList;
    }

    public Plantel getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Plantel idplantel) {
        this.idplantel = idplantel;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Grupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalendario != null ? idcalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.idcalendario == null && other.idcalendario != null) || (this.idcalendario != null && !this.idcalendario.equals(other.idcalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Calendario[ idcalendario=" + idcalendario + " ]";
    }
    
}
