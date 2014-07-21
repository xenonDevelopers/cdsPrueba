/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asesor;
import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignatura_asesor")
@NamedQueries({
    @NamedQuery(name = "AsignaturaAsesor.findAll", query = "SELECT a FROM AsignaturaAsesor a"),
    @NamedQuery(name = "AsignaturaAsesor.findByIdAsignaturaasesor", query = "SELECT a FROM AsignaturaAsesor a WHERE a.idAsignaturaasesor = :idAsignaturaasesor"),
    @NamedQuery(name = "AsignaturaAsesor.findByHistorico", query = "SELECT a FROM AsignaturaAsesor a WHERE a.historico = :historico")})
public class AsignaturaAsesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignaturaasesor")
    private Long idAsignaturaasesor;
    @Column(name = "historico")
    private Boolean historico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "asignaturaAsesor")
    private MotivoAsignaturaasesor motivoAsignaturaasesor;
    @JoinColumn(name = "id_calendarioasignatura", referencedColumnName = "id_calendarioasignatura")
    @ManyToOne
    private CalendarioAsignatura idCalendarioasignatura;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor")
    @ManyToOne
    private Asesor idAsesor;

    public AsignaturaAsesor() {
    }

    public AsignaturaAsesor(Long idAsignaturaasesor) {
        this.idAsignaturaasesor = idAsignaturaasesor;
    }

    public Long getIdAsignaturaasesor() {
        return idAsignaturaasesor;
    }

    public void setIdAsignaturaasesor(Long idAsignaturaasesor) {
        this.idAsignaturaasesor = idAsignaturaasesor;
    }

    public Boolean getHistorico() {
        return historico;
    }

    public void setHistorico(Boolean historico) {
        this.historico = historico;
    }

    public MotivoAsignaturaasesor getMotivoAsignaturaasesor() {
        return motivoAsignaturaasesor;
    }

    public void setMotivoAsignaturaasesor(MotivoAsignaturaasesor motivoAsignaturaasesor) {
        this.motivoAsignaturaasesor = motivoAsignaturaasesor;
    }

    public CalendarioAsignatura getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(CalendarioAsignatura idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    public Asesor getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Asesor idAsesor) {
        this.idAsesor = idAsesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignaturaasesor != null ? idAsignaturaasesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaturaAsesor)) {
            return false;
        }
        AsignaturaAsesor other = (AsignaturaAsesor) object;
        if ((this.idAsignaturaasesor == null && other.idAsignaturaasesor != null) || (this.idAsignaturaasesor != null && !this.idAsignaturaasesor.equals(other.idAsignaturaasesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignaturaAsesor[ idAsignaturaasesor=" + idAsignaturaasesor + " ]";
    }
    
}
