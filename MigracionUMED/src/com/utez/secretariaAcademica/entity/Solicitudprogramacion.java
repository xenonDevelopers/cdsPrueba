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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "solicitudprogramacion")
@NamedQueries({
    @NamedQuery(name = "Solicitudprogramacion.findAll", query = "SELECT s FROM Solicitudprogramacion s"),
    @NamedQuery(name = "Solicitudprogramacion.findByIdsolicitudprogramacion", query = "SELECT s FROM Solicitudprogramacion s WHERE s.idsolicitudprogramacion = :idsolicitudprogramacion"),
    @NamedQuery(name = "Solicitudprogramacion.findByMatricula", query = "SELECT s FROM Solicitudprogramacion s WHERE s.matricula = :matricula"),
    @NamedQuery(name = "Solicitudprogramacion.findByCasosecretaria", query = "SELECT s FROM Solicitudprogramacion s WHERE s.casosecretaria = :casosecretaria"),
    @NamedQuery(name = "Solicitudprogramacion.findByFechasolicitud", query = "SELECT s FROM Solicitudprogramacion s WHERE s.fechasolicitud = :fechasolicitud"),
    @NamedQuery(name = "Solicitudprogramacion.findByEstado", query = "SELECT s FROM Solicitudprogramacion s WHERE s.estado = :estado"),
    @NamedQuery(name = "Solicitudprogramacion.findBySolucion", query = "SELECT s FROM Solicitudprogramacion s WHERE s.solucion = :solucion"),
    @NamedQuery(name = "Solicitudprogramacion.findByCasoalumno", query = "SELECT s FROM Solicitudprogramacion s WHERE s.casoalumno = :casoalumno")})
public class Solicitudprogramacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsolicitudprogramacion")
    private Integer idsolicitudprogramacion;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "casosecretaria")
    private String casosecretaria;
    @Column(name = "fechasolicitud")
    @Temporal(TemporalType.DATE)
    private Date fechasolicitud;
    @Column(name = "estado")
    private String estado;
    @Column(name = "solucion")
    private String solucion;
    @Column(name = "casoalumno")
    private String casoalumno;
    @OneToMany(mappedBy = "idsolicitudprogramacion")
    private List<Cuadernoprogramacion> cuadernoprogramacionList;

    public Solicitudprogramacion() {
    }

    public Solicitudprogramacion(Integer idsolicitudprogramacion) {
        this.idsolicitudprogramacion = idsolicitudprogramacion;
    }

    public Integer getIdsolicitudprogramacion() {
        return idsolicitudprogramacion;
    }

    public void setIdsolicitudprogramacion(Integer idsolicitudprogramacion) {
        this.idsolicitudprogramacion = idsolicitudprogramacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCasosecretaria() {
        return casosecretaria;
    }

    public void setCasosecretaria(String casosecretaria) {
        this.casosecretaria = casosecretaria;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getCasoalumno() {
        return casoalumno;
    }

    public void setCasoalumno(String casoalumno) {
        this.casoalumno = casoalumno;
    }

    public List<Cuadernoprogramacion> getCuadernoprogramacionList() {
        return cuadernoprogramacionList;
    }

    public void setCuadernoprogramacionList(List<Cuadernoprogramacion> cuadernoprogramacionList) {
        this.cuadernoprogramacionList = cuadernoprogramacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsolicitudprogramacion != null ? idsolicitudprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudprogramacion)) {
            return false;
        }
        Solicitudprogramacion other = (Solicitudprogramacion) object;
        if ((this.idsolicitudprogramacion == null && other.idsolicitudprogramacion != null) || (this.idsolicitudprogramacion != null && !this.idsolicitudprogramacion.equals(other.idsolicitudprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secretariaAcademica.entity.Solicitudprogramacion[ idsolicitudprogramacion=" + idsolicitudprogramacion + " ]";
    }
    
}
