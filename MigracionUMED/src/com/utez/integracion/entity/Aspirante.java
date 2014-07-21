/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Alumno;
import com.utez.secretariaAcademica.entity.Periodo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "aspirante")
@NamedQueries({
    @NamedQuery(name = "Aspirante.findAll", query = "SELECT a FROM Aspirante a"),
    @NamedQuery(name = "Aspirante.findByIdAspirante", query = "SELECT a FROM Aspirante a WHERE a.idAspirante = :idAspirante"),
    @NamedQuery(name = "Aspirante.findByFechaContacto", query = "SELECT a FROM Aspirante a WHERE a.fechaContacto = :fechaContacto"),
    @NamedQuery(name = "Aspirante.findByComentarios", query = "SELECT a FROM Aspirante a WHERE a.comentarios = :comentarios")})
public class Aspirante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aspirante")
    private Long idAspirante;
    @Column(name = "fecha_contacto")
    @Temporal(TemporalType.DATE)
    private Date fechaContacto;
    @Column(name = "comentarios")
    private String comentarios;
    @OneToMany(mappedBy = "idAspirante")
    private List<SeguimientoAspirante> seguimientoAspiranteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante")
    private List<AspiranteOpcion> aspiranteOpcionList;
    @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio")
    @ManyToOne
    private PlanEstudio idPlanestudio;
    @JoinColumn(name = "curp", referencedColumnName = "curp")
    @ManyToOne
    private Persona curp;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo idPeriodo;
    @OneToMany(mappedBy = "idAspirante")
    private List<Alumno> alumnoList;

    public Aspirante() {
    }

    public Aspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Date getFechaContacto() {
        return fechaContacto;
    }

    public void setFechaContacto(Date fechaContacto) {
        this.fechaContacto = fechaContacto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<SeguimientoAspirante> getSeguimientoAspiranteList() {
        return seguimientoAspiranteList;
    }

    public void setSeguimientoAspiranteList(List<SeguimientoAspirante> seguimientoAspiranteList) {
        this.seguimientoAspiranteList = seguimientoAspiranteList;
    }

    public List<AspiranteOpcion> getAspiranteOpcionList() {
        return aspiranteOpcionList;
    }

    public void setAspiranteOpcionList(List<AspiranteOpcion> aspiranteOpcionList) {
        this.aspiranteOpcionList = aspiranteOpcionList;
    }

    public PlanEstudio getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(PlanEstudio idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public Persona getCurp() {
        return curp;
    }

    public void setCurp(Persona curp) {
        this.curp = curp;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAspirante != null ? idAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aspirante)) {
            return false;
        }
        Aspirante other = (Aspirante) object;
        if ((this.idAspirante == null && other.idAspirante != null) || (this.idAspirante != null && !this.idAspirante.equals(other.idAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Aspirante[ idAspirante=" + idAspirante + " ]";
    }
    
}
