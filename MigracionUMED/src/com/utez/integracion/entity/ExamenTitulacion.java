/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "examen_titulacion")
@NamedQueries({
    @NamedQuery(name = "ExamenTitulacion.findAll", query = "SELECT e FROM ExamenTitulacion e"),
    @NamedQuery(name = "ExamenTitulacion.findByIdExamentitulacion", query = "SELECT e FROM ExamenTitulacion e WHERE e.idExamentitulacion = :idExamentitulacion"),
    @NamedQuery(name = "ExamenTitulacion.findByDireccion", query = "SELECT e FROM ExamenTitulacion e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "ExamenTitulacion.findByVeredicto", query = "SELECT e FROM ExamenTitulacion e WHERE e.veredicto = :veredicto"),
    @NamedQuery(name = "ExamenTitulacion.findByFechahoraExamentitulacion", query = "SELECT e FROM ExamenTitulacion e WHERE e.fechahoraExamentitulacion = :fechahoraExamentitulacion"),
    @NamedQuery(name = "ExamenTitulacion.findByNumeroFoja", query = "SELECT e FROM ExamenTitulacion e WHERE e.numeroFoja = :numeroFoja"),
    @NamedQuery(name = "ExamenTitulacion.findByNumeroLibro", query = "SELECT e FROM ExamenTitulacion e WHERE e.numeroLibro = :numeroLibro"),
    @NamedQuery(name = "ExamenTitulacion.findByNumeroActa", query = "SELECT e FROM ExamenTitulacion e WHERE e.numeroActa = :numeroActa"),
    @NamedQuery(name = "ExamenTitulacion.findByTipoCeremonia", query = "SELECT e FROM ExamenTitulacion e WHERE e.tipoCeremonia = :tipoCeremonia")})
public class ExamenTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examentitulacion")
    private Long idExamentitulacion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "veredicto")
    private String veredicto;
    @Column(name = "fechahora_examentitulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahoraExamentitulacion;
    @Column(name = "numero_foja")
    private Integer numeroFoja;
    @Column(name = "numero_libro")
    private Integer numeroLibro;
    @Column(name = "numero_acta")
    private Integer numeroActa;
    @Column(name = "tipo_ceremonia")
    private String tipoCeremonia;
    @JoinColumn(name = "id_tramitetitulacion", referencedColumnName = "id_tramitetitulacion")
    @ManyToOne
    private TramiteTitulacion idTramitetitulacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "examenTitulacion")
    private MaterialTitulacion materialTitulacion;

    public ExamenTitulacion() {
    }

    public ExamenTitulacion(Long idExamentitulacion) {
        this.idExamentitulacion = idExamentitulacion;
    }

    public Long getIdExamentitulacion() {
        return idExamentitulacion;
    }

    public void setIdExamentitulacion(Long idExamentitulacion) {
        this.idExamentitulacion = idExamentitulacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getVeredicto() {
        return veredicto;
    }

    public void setVeredicto(String veredicto) {
        this.veredicto = veredicto;
    }

    public Date getFechahoraExamentitulacion() {
        return fechahoraExamentitulacion;
    }

    public void setFechahoraExamentitulacion(Date fechahoraExamentitulacion) {
        this.fechahoraExamentitulacion = fechahoraExamentitulacion;
    }

    public Integer getNumeroFoja() {
        return numeroFoja;
    }

    public void setNumeroFoja(Integer numeroFoja) {
        this.numeroFoja = numeroFoja;
    }

    public Integer getNumeroLibro() {
        return numeroLibro;
    }

    public void setNumeroLibro(Integer numeroLibro) {
        this.numeroLibro = numeroLibro;
    }

    public Integer getNumeroActa() {
        return numeroActa;
    }

    public void setNumeroActa(Integer numeroActa) {
        this.numeroActa = numeroActa;
    }

    public String getTipoCeremonia() {
        return tipoCeremonia;
    }

    public void setTipoCeremonia(String tipoCeremonia) {
        this.tipoCeremonia = tipoCeremonia;
    }

    public TramiteTitulacion getIdTramitetitulacion() {
        return idTramitetitulacion;
    }

    public void setIdTramitetitulacion(TramiteTitulacion idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public MaterialTitulacion getMaterialTitulacion() {
        return materialTitulacion;
    }

    public void setMaterialTitulacion(MaterialTitulacion materialTitulacion) {
        this.materialTitulacion = materialTitulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamentitulacion != null ? idExamentitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenTitulacion)) {
            return false;
        }
        ExamenTitulacion other = (ExamenTitulacion) object;
        if ((this.idExamentitulacion == null && other.idExamentitulacion != null) || (this.idExamentitulacion != null && !this.idExamentitulacion.equals(other.idExamentitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ExamenTitulacion[ idExamentitulacion=" + idExamentitulacion + " ]";
    }
    
}
