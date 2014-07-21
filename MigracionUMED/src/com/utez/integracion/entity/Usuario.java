/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByAliasUsuario", query = "SELECT u FROM Usuario u WHERE u.aliasUsuario = :aliasUsuario"),
    @NamedQuery(name = "Usuario.findByContrasenaUsuario", query = "SELECT u FROM Usuario u WHERE u.contrasenaUsuario = :contrasenaUsuario"),
    @NamedQuery(name = "Usuario.findByCurp", query = "SELECT u FROM Usuario u WHERE u.curp = :curp"),
    @NamedQuery(name = "Usuario.findByConexion", query = "SELECT u FROM Usuario u WHERE u.conexion = :conexion"),
    @NamedQuery(name = "Usuario.findByEstadoUsuario", query = "SELECT u FROM Usuario u WHERE u.estadoUsuario = :estadoUsuario"),
    @NamedQuery(name = "Usuario.findByTiempoConexion", query = "SELECT u FROM Usuario u WHERE u.tiempoConexion = :tiempoConexion")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "alias_usuario")
    private String aliasUsuario;
    @Column(name = "contrasena_usuario")
    private String contrasenaUsuario;
    @Id
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;
    @Column(name = "conexion")
    private Boolean conexion;
    @Column(name = "estado_usuario")
    private Boolean estadoUsuario;
    @Column(name = "tiempo_conexion")
    private Integer tiempoConexion;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Rol> rolList;
    @JoinColumn(name = "curp", referencedColumnName = "curp", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    @OneToMany(mappedBy = "idUsuario")
    private List<BitacoraMovimiento> bitacoraMovimientoList;

    public Usuario() {
    }

    public Usuario(String curp) {
        this.curp = curp;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Boolean getConexion() {
        return conexion;
    }

    public void setConexion(Boolean conexion) {
        this.conexion = conexion;
    }

    public Boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Integer getTiempoConexion() {
        return tiempoConexion;
    }

    public void setTiempoConexion(Integer tiempoConexion) {
        this.tiempoConexion = tiempoConexion;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<BitacoraMovimiento> getBitacoraMovimientoList() {
        return bitacoraMovimientoList;
    }

    public void setBitacoraMovimientoList(List<BitacoraMovimiento> bitacoraMovimientoList) {
        this.bitacoraMovimientoList = bitacoraMovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Usuario[ curp=" + curp + " ]";
    }
    
}
