/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Asignatura;
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
@Table(name = "calendario_asignatura")
@NamedQueries({
    @NamedQuery(name = "CalendarioAsignatura.findAll", query = "SELECT c FROM CalendarioAsignatura c"),
    @NamedQuery(name = "CalendarioAsignatura.findByIdCalendarioasignatura", query = "SELECT c FROM CalendarioAsignatura c WHERE c.idCalendarioasignatura = :idCalendarioasignatura"),
    @NamedQuery(name = "CalendarioAsignatura.findByBloque", query = "SELECT c FROM CalendarioAsignatura c WHERE c.bloque = :bloque"),
    @NamedQuery(name = "CalendarioAsignatura.findByNumeroOrden", query = "SELECT c FROM CalendarioAsignatura c WHERE c.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "CalendarioAsignatura.findByEstadoBloque", query = "SELECT c FROM CalendarioAsignatura c WHERE c.estadoBloque = :estadoBloque")})
public class CalendarioAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calendarioasignatura")
    private Long idCalendarioasignatura;
    @Column(name = "bloque")
    private Integer bloque;
    @Column(name = "numero_orden")
    private Integer numeroOrden;
    @Column(name = "estado_bloque")
    private String estadoBloque;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoList;
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private Horario idHorario;
    @JoinColumn(name = "id_calendarioescolar", referencedColumnName = "id_calendarioescolar")
    @ManyToOne
    private CalendarioEscolar idCalendarioescolar;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignatura;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<Asesoria> asesoriaList;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<HistoricoCalendarioasignatura> historicoCalendarioasignaturaList;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<FechaExamen> fechaExamenList;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<AsignaturaAsesor> asignaturaAsesorList;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<ProgramacionEspecifica> programacionEspecificaList;
    @OneToMany(mappedBy = "idCalendarioasignatura")
    private List<HistoricoAsesorasignatura> historicoAsesorasignaturaList;

    public CalendarioAsignatura() {
    }

    public CalendarioAsignatura(Long idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    public Long getIdCalendarioasignatura() {
        return idCalendarioasignatura;
    }

    public void setIdCalendarioasignatura(Long idCalendarioasignatura) {
        this.idCalendarioasignatura = idCalendarioasignatura;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public Integer getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(Integer numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getEstadoBloque() {
        return estadoBloque;
    }

    public void setEstadoBloque(String estadoBloque) {
        this.estadoBloque = estadoBloque;
    }

    public List<AsignacionIntegradoragrupo> getAsignacionIntegradoragrupoList() {
        return asignacionIntegradoragrupoList;
    }

    public void setAsignacionIntegradoragrupoList(List<AsignacionIntegradoragrupo> asignacionIntegradoragrupoList) {
        this.asignacionIntegradoragrupoList = asignacionIntegradoragrupoList;
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    public CalendarioEscolar getIdCalendarioescolar() {
        return idCalendarioescolar;
    }

    public void setIdCalendarioescolar(CalendarioEscolar idCalendarioescolar) {
        this.idCalendarioescolar = idCalendarioescolar;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public List<Asesoria> getAsesoriaList() {
        return asesoriaList;
    }

    public void setAsesoriaList(List<Asesoria> asesoriaList) {
        this.asesoriaList = asesoriaList;
    }

    public List<HistoricoCalendarioasignatura> getHistoricoCalendarioasignaturaList() {
        return historicoCalendarioasignaturaList;
    }

    public void setHistoricoCalendarioasignaturaList(List<HistoricoCalendarioasignatura> historicoCalendarioasignaturaList) {
        this.historicoCalendarioasignaturaList = historicoCalendarioasignaturaList;
    }

    public List<FechaExamen> getFechaExamenList() {
        return fechaExamenList;
    }

    public void setFechaExamenList(List<FechaExamen> fechaExamenList) {
        this.fechaExamenList = fechaExamenList;
    }

    public List<AsignaturaAsesor> getAsignaturaAsesorList() {
        return asignaturaAsesorList;
    }

    public void setAsignaturaAsesorList(List<AsignaturaAsesor> asignaturaAsesorList) {
        this.asignaturaAsesorList = asignaturaAsesorList;
    }

    public List<ProgramacionEspecifica> getProgramacionEspecificaList() {
        return programacionEspecificaList;
    }

    public void setProgramacionEspecificaList(List<ProgramacionEspecifica> programacionEspecificaList) {
        this.programacionEspecificaList = programacionEspecificaList;
    }

    public List<HistoricoAsesorasignatura> getHistoricoAsesorasignaturaList() {
        return historicoAsesorasignaturaList;
    }

    public void setHistoricoAsesorasignaturaList(List<HistoricoAsesorasignatura> historicoAsesorasignaturaList) {
        this.historicoAsesorasignaturaList = historicoAsesorasignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendarioasignatura != null ? idCalendarioasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendarioAsignatura)) {
            return false;
        }
        CalendarioAsignatura other = (CalendarioAsignatura) object;
        if ((this.idCalendarioasignatura == null && other.idCalendarioasignatura != null) || (this.idCalendarioasignatura != null && !this.idCalendarioasignatura.equals(other.idCalendarioasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CalendarioAsignatura[ idCalendarioasignatura=" + idCalendarioasignatura + " ]";
    }
    
}
