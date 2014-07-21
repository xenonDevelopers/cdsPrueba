/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import com.utez.secretariaAcademica.entity.Grupo;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignacion_evaluacion")
@NamedQueries({
    @NamedQuery(name = "AsignacionEvaluacion.findAll", query = "SELECT a FROM AsignacionEvaluacion a"),
    @NamedQuery(name = "AsignacionEvaluacion.findByIdAsignacionevaluacion", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.idAsignacionevaluacion = :idAsignacionevaluacion"),
    @NamedQuery(name = "AsignacionEvaluacion.findByVigenciaInicio", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.vigenciaInicio = :vigenciaInicio"),
    @NamedQuery(name = "AsignacionEvaluacion.findByUnidades", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.unidades = :unidades"),
    @NamedQuery(name = "AsignacionEvaluacion.findByVigenciaFin", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.vigenciaFin = :vigenciaFin"),
    @NamedQuery(name = "AsignacionEvaluacion.findByNumeroReactivos", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.numeroReactivos = :numeroReactivos"),
    @NamedQuery(name = "AsignacionEvaluacion.findByTiempo", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.tiempo = :tiempo"),
    @NamedQuery(name = "AsignacionEvaluacion.findByContrasena", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.contrasena = :contrasena"),
    @NamedQuery(name = "AsignacionEvaluacion.findByEstadoEvaluacion", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.estadoEvaluacion = :estadoEvaluacion"),
    @NamedQuery(name = "AsignacionEvaluacion.findByEvaluacionAsesor", query = "SELECT a FROM AsignacionEvaluacion a WHERE a.evaluacionAsesor = :evaluacionAsesor")})
public class AsignacionEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionevaluacion")
    private Long idAsignacionevaluacion;
    @Column(name = "vigencia_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciaInicio;
    @Column(name = "unidades")
    private String unidades;
    @Column(name = "vigencia_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciaFin;
    @Column(name = "numero_reactivos")
    private Integer numeroReactivos;
    @Column(name = "tiempo")
    private Integer tiempo;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "estado_evaluacion")
    private String estadoEvaluacion;
    @Column(name = "evaluacion_asesor")
    private Boolean evaluacionAsesor;
    @OneToMany(mappedBy = "idAsignacionevaluacion")
    private List<ResultadoEvaluacion> resultadoEvaluacionList;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_tipoevaluacion", referencedColumnName = "id_tipoevaluacion")
    @ManyToOne
    private TipoEvaluacion idTipoevaluacion;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "id_asignacionasignaturabanco", referencedColumnName = "id_asignacionasignaturabanco")
    @ManyToOne
    private AsignacionAsignaturabanco idAsignacionasignaturabanco;

    public AsignacionEvaluacion() {
    }

    public AsignacionEvaluacion(Long idAsignacionevaluacion) {
        this.idAsignacionevaluacion = idAsignacionevaluacion;
    }

    public Long getIdAsignacionevaluacion() {
        return idAsignacionevaluacion;
    }

    public void setIdAsignacionevaluacion(Long idAsignacionevaluacion) {
        this.idAsignacionevaluacion = idAsignacionevaluacion;
    }

    public Date getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(Date vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public Date getVigenciaFin() {
        return vigenciaFin;
    }

    public void setVigenciaFin(Date vigenciaFin) {
        this.vigenciaFin = vigenciaFin;
    }

    public Integer getNumeroReactivos() {
        return numeroReactivos;
    }

    public void setNumeroReactivos(Integer numeroReactivos) {
        this.numeroReactivos = numeroReactivos;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstadoEvaluacion() {
        return estadoEvaluacion;
    }

    public void setEstadoEvaluacion(String estadoEvaluacion) {
        this.estadoEvaluacion = estadoEvaluacion;
    }

    public Boolean getEvaluacionAsesor() {
        return evaluacionAsesor;
    }

    public void setEvaluacionAsesor(Boolean evaluacionAsesor) {
        this.evaluacionAsesor = evaluacionAsesor;
    }

    public List<ResultadoEvaluacion> getResultadoEvaluacionList() {
        return resultadoEvaluacionList;
    }

    public void setResultadoEvaluacionList(List<ResultadoEvaluacion> resultadoEvaluacionList) {
        this.resultadoEvaluacionList = resultadoEvaluacionList;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public TipoEvaluacion getIdTipoevaluacion() {
        return idTipoevaluacion;
    }

    public void setIdTipoevaluacion(TipoEvaluacion idTipoevaluacion) {
        this.idTipoevaluacion = idTipoevaluacion;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public AsignacionAsignaturabanco getIdAsignacionasignaturabanco() {
        return idAsignacionasignaturabanco;
    }

    public void setIdAsignacionasignaturabanco(AsignacionAsignaturabanco idAsignacionasignaturabanco) {
        this.idAsignacionasignaturabanco = idAsignacionasignaturabanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionevaluacion != null ? idAsignacionevaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionEvaluacion)) {
            return false;
        }
        AsignacionEvaluacion other = (AsignacionEvaluacion) object;
        if ((this.idAsignacionevaluacion == null && other.idAsignacionevaluacion != null) || (this.idAsignacionevaluacion != null && !this.idAsignacionevaluacion.equals(other.idAsignacionevaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionEvaluacion[ idAsignacionevaluacion=" + idAsignacionevaluacion + " ]";
    }
    
}
