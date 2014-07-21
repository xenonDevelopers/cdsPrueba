/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Grupo;
import com.utez.secretariaAcademica.entity.Periodo;
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
@Table(name = "calendario_escolar")
@NamedQueries({
    @NamedQuery(name = "CalendarioEscolar.findAll", query = "SELECT c FROM CalendarioEscolar c"),
    @NamedQuery(name = "CalendarioEscolar.findByIdCalendarioescolar", query = "SELECT c FROM CalendarioEscolar c WHERE c.idCalendarioescolar = :idCalendarioescolar"),
    @NamedQuery(name = "CalendarioEscolar.findByAxoCalendario", query = "SELECT c FROM CalendarioEscolar c WHERE c.axoCalendario = :axoCalendario"),
    @NamedQuery(name = "CalendarioEscolar.findByObservaciones", query = "SELECT c FROM CalendarioEscolar c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CalendarioEscolar.findByEstadoCalendario", query = "SELECT c FROM CalendarioEscolar c WHERE c.estadoCalendario = :estadoCalendario")})
public class CalendarioEscolar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calendarioescolar")
    private Long idCalendarioescolar;
    @Column(name = "axo_calendario")
    private Integer axoCalendario;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estado_calendario")
    private String estadoCalendario;
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo idPeriodo;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;
    @OneToMany(mappedBy = "idCalendarioescolar")
    private List<CalendarioAsignatura> calendarioAsignaturaList;

    public CalendarioEscolar() {
    }

    public CalendarioEscolar(Long idCalendarioescolar) {
        this.idCalendarioescolar = idCalendarioescolar;
    }

    public Long getIdCalendarioescolar() {
        return idCalendarioescolar;
    }

    public void setIdCalendarioescolar(Long idCalendarioescolar) {
        this.idCalendarioescolar = idCalendarioescolar;
    }

    public Integer getAxoCalendario() {
        return axoCalendario;
    }

    public void setAxoCalendario(Integer axoCalendario) {
        this.axoCalendario = axoCalendario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoCalendario() {
        return estadoCalendario;
    }

    public void setEstadoCalendario(String estadoCalendario) {
        this.estadoCalendario = estadoCalendario;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<CalendarioAsignatura> getCalendarioAsignaturaList() {
        return calendarioAsignaturaList;
    }

    public void setCalendarioAsignaturaList(List<CalendarioAsignatura> calendarioAsignaturaList) {
        this.calendarioAsignaturaList = calendarioAsignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendarioescolar != null ? idCalendarioescolar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendarioEscolar)) {
            return false;
        }
        CalendarioEscolar other = (CalendarioEscolar) object;
        if ((this.idCalendarioescolar == null && other.idCalendarioescolar != null) || (this.idCalendarioescolar != null && !this.idCalendarioescolar.equals(other.idCalendarioescolar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CalendarioEscolar[ idCalendarioescolar=" + idCalendarioescolar + " ]";
    }
    
}
